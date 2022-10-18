import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  constructor(private authService: AuthService,
              private router: Router) {}

  public onLogout(): void{
    this.authService.logout();
  }

  public isLoggedIn(): boolean {
    return this.authService.isLoggedIn()
  }

  public showPasswords(): void{
    this.router.navigate(["passwords"]);
  }

  public changeMainPassword(): void{
    this.router.navigate(["change-main-password"]);
  }
}
