import {Injectable} from "@angular/core";
import {AuthControllerService} from "./auth-controller.service";
import {LoginPayload} from "../models/login-payload";
import {LocalStorageEnum} from "../enum/local-storage.enum";
import {Login} from "../models/login";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {RegisterPayload} from "../models/register-payload";

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
          this.snackBar.open(data.message, "OK", {
            horizontalPosition: "end",
            verticalPosition: "top",
          });
          this.router.navigate(['passwords'])
        },
        error: () => {}
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
        error: () => {}
      })
  }

  public isLoggedIn() {
    return localStorage.getItem(LocalStorageEnum.JWT_TOKEN) !== null;
  }

  public logout():void{
    localStorage.clear();
    this.router.navigate(["login"]);

  }

}
