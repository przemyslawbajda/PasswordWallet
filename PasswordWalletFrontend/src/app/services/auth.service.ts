import {Injectable} from "@angular/core";
import {AuthControllerService} from "./auth-controller.service";
import {LoginPayload} from "../models/login-payload";
import {LocalStorageEnum} from "../enum/local-storage.enum";
import {Login} from "../models/login";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {RegisterPayload} from "../models/register-payload";
import {ChangeMainPasswordPayload} from "../models/change-main-password-payload";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  constructor(private authControllerService: AuthControllerService,
              private snackBar: MatSnackBar,
              private router: Router) {
  }

  public login(loginPayload: LoginPayload): void{
    this.authControllerService.login(loginPayload)
      .subscribe({
        next: (data: Login) => {
          localStorage.setItem(LocalStorageEnum.LOGIN, data.userLogin);
          localStorage.setItem(LocalStorageEnum.JWT_TOKEN, data.jwt);
          localStorage.setItem(LocalStorageEnum.LAST_SUCCESSFUL, data.lastSuccessfulAttempt);
          localStorage.setItem(LocalStorageEnum.LAST_FAILED, data.lastFailedAttempt);

          this.snackBar.open(data.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
          this.router.navigate(['passwords'])
        },
        error: (data: any) => {

          this.snackBar.open(data.error.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
        }
      })
  }

  public register(registerPayload: RegisterPayload): void {
    this.authControllerService.register(registerPayload)
      .subscribe({
        next: (data: any) => {
          this.snackBar.open(data.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
          this.router.navigate(['login'])
        },
        error: (data: any) => {
          this.snackBar.open(data.error.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
        }
      })
  }

  public isLoggedIn() {
    return localStorage.getItem(LocalStorageEnum.JWT_TOKEN) !== null;
  }

  public logout():void{
    localStorage.clear();
    this.router.navigate(["login"]);

  }

  changeMainPassword(changePassword: ChangeMainPasswordPayload) {

    this.authControllerService.changeMainPassword(changePassword)
      .subscribe( {
        next: (data:any) =>{
          this.snackBar.open(data.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
          this.router.navigate(['passwords'])
        },
        error: (data: any) => {
          this.snackBar.open(data.error.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
        }
      }

    );
  }
}
