import {Injectable} from "@angular/core";
import {PasswordControllerService} from "../services/password-controller.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../models/password";
import {MatDialogRef} from "@angular/material/dialog";
import {EditPasswordComponent} from "../component/edit-password/edit-password.component";
import {PasswordResponse} from "../models/password-response";

@Injectable({
  providedIn: "root"
})
export class StoreService {

  passwords$: BehaviorSubject<Password[]> = new BehaviorSubject<Password[]>([]);

  constructor(private passwordService: PasswordControllerService) {}

  public getPasswords(){
    this.passwordService.getPasswords()
      .subscribe((data: Password[]) => this.passwords$.next(data));
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

  public getDecryptedPassword(passwordId: number): void {
    this.passwordService.getDecryptedPassword(passwordId)
      .subscribe((password: PasswordResponse) => this.setPassword(password.message, passwordId));
  }

  public setPassword(password: string, passwordId: number): void {
    const passwords: Password[] = this.passwords$.getValue();
    passwords.find(password => password.id === passwordId).password = password;
    this.passwords$.next(passwords);
  }
}
