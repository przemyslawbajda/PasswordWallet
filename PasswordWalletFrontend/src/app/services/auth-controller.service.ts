import {HttpClient} from "@angular/common/http";
import {LoginPayload} from "../models/login-payload";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {Login} from "../models/login";
import {RegisterPayload} from "../models/register-payload";
import {ChangeMainPasswordPayload} from "../models/change-main-password-payload";

@Injectable({
  providedIn: "root"
})

export class AuthControllerService {

  constructor(private http: HttpClient) {
  }

  public login(loginPayload: LoginPayload):Observable<Login>{
    const url = "http://localhost:8080/api/auth/login";
    return this.http.post<Login>(url, loginPayload);
  }

  public register(registerPayload: RegisterPayload):Observable<any>{
    const url = "http://localhost:8080/api/auth/register";
    return this.http.post<any>(url, registerPayload);
  }

  changeMainPassword(changePassword: ChangeMainPasswordPayload):Observable<any> {
    const url = "http://localhost:8080/api/auth/change-main-password";
    return this.http.put(url, changePassword);
  }
}
