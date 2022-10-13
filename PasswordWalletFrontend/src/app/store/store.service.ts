import {Injectable} from "@angular/core";
import {PasswordService} from "../services/password.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../models/password";

@Injectable({
  providedIn: "root"
})
export class StoreService {

  passwords$: BehaviorSubject<Password[]> = new BehaviorSubject<Password[]>([]);

  constructor(private passwordService: PasswordService) {}

  public getPasswords(){
    this.passwordService.getPasswords().subscribe({
        next: (data: Password[]) => this.passwords$.next(data),
      }

    )
  }
}
