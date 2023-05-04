import {Injectable} from "@angular/core";
import {PasswordControllerService} from "../services/password-controller.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../models/password";
import {MatDialogRef} from "@angular/material/dialog";
import {EditPasswordComponent} from "../component/edit-password/edit-password.component";
import {PasswordResponse} from "../models/password-response";
import {User} from "../models/user";
import {AuthService} from "../services/auth.service";
import {SharePassword} from "../models/share-password";

@Injectable({
  providedIn: "root"
})
export class StoreService {

  passwords$: BehaviorSubject<Password[]> = new BehaviorSubject<Password[]>([]);
  sharedPasswords$: BehaviorSubject<Password[]> = new BehaviorSubject<Password[]>([]);
  users$: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);
  isEditMode$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private passwordService: PasswordControllerService,
              public auth: AuthService,
  ) {}

  public getPasswords(){
    this.passwordService.getPasswords()
      .subscribe((data: Password[]) => {
        data.forEach( (password: Password) => password.password = null );
        this.passwords$.next(data)
      });
  }

  public getSharedPasswords(){
    this.passwordService.getSharedPasswords()
      .subscribe((data: Password[]) => this.sharedPasswords$.next(data));
  }

  public editPassword(editedPassword: Password, dialogRef: MatDialogRef<EditPasswordComponent>): void {
    this.passwordService.editPassword(editedPassword)
      .subscribe( () => {
        this.getPasswords();
        dialogRef.close();
      });
  }

  public deletePassword(passwordId: number){
    this.passwordService.deletePassword(passwordId)
      .subscribe(() => {
        this.getPasswords();
      })
  }

  public getUsers(){
    this.passwordService.getUsers()
      .subscribe((users: User[]) => {
        const index = users.findIndex(user => user.login === this.auth.getUsername())
        users.splice(index, 1);
        this.users$.next(users)
      });
  }

  public sharePassword(share: SharePassword){
    this.passwordService.sharePassword(share).subscribe();
  }

  public getDecryptedPassword(passwordId: number): void {
    this.passwordService.getDecryptedPassword(passwordId)
      .subscribe((password: PasswordResponse) => this.setPassword(password.message, passwordId));
  }

  public setPassword(password: string, passwordId: number): void {
    const passwords: Password[] = this.passwords$.getValue();
    passwords.find(password => password.id === passwordId).password = password;
    this.passwords$.next(passwords);
  }

  public setEditMode(mode: boolean) {
    this.isEditMode$.next(mode);
  }
}
