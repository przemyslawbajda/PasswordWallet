import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Password} from "../models/password";
import {Router} from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class PasswordService{

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

}
