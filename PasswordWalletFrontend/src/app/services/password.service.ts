import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Password} from "../models/password";

@Injectable({
  providedIn: "root"
})
export class PasswordService{

  constructor(private http: HttpClient) {}

  public getPasswords(): Observable<Password[]>{
    const url = `http://localhost:8080/api/passwords`;
    return this.http.get<Password[]>(url);
  }
}
