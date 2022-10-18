import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Password} from "../models/password";
import {Router} from "@angular/router";
import {PasswordResponse} from "../models/password-response";
import {ChangeMainPasswordPayload} from "../models/change-main-password-payload";

@Injectable({
  providedIn: "root"
})
export class PasswordControllerService {

  constructor(private http: HttpClient,
              private router: Router) {}

  public getPasswords(): Observable<Password[]>{
    const url = `http://localhost:8080/api/passwords`;
    return this.http.get<Password[]>(url);
  }

  public addPassword(newPassword: any): void {
    const url = `http://localhost:8080/api/passwords`;
    this.http.post(url, newPassword).subscribe(() => this.router.navigate(['passwords']));
  }

  public getDecryptedPassword(passwordId: number): Observable<PasswordResponse> {
    const url = `http://localhost:8080/api/passwords/`+passwordId;
    return this.http.get<PasswordResponse>(url);
  }

  editPassword(editedPassword: Password): Observable<any> {
    const url = `http://localhost:8080/api/passwords`;
    return this.http.put(url, editedPassword)
  }

  deletePassword(passwordId: number): Observable<any> {
    const url = `http://localhost:8080/api/passwords/${passwordId}`;
    return this.http.delete(url);
  }

  changeMainPassword(changePassword: ChangeMainPasswordPayload) {
    const url = `http://localhost:8080/api/auth/change-main-password`;


  }
}
