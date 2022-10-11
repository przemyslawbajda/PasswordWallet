import {Injectable} from "@angular/core";
import {PasswordService} from "../services/password.service";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: "root"
})
export class StoreService {

  passwords$: BehaviorSubject<any[]> = new BehaviorSubject<any[]>([]);

  constructor(private passwordService: PasswordService) {}

  public getPasswords(){
    this.passwordService.getPasswords().subscribe({
        next: (data: any) => this.passwords$.next(data),
      }

    )
  }
}
