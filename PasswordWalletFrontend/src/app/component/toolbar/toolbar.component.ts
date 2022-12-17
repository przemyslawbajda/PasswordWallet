import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  private lastSuccessful: string = "";
  private lastFailed: string = "";


  constructor(private authService: AuthService,
              private router: Router) {

  }

  public onLogout(): void{
    this.authService.logout();
  }

  public isLoggedIn(): boolean {

    if(this.authService.isLoggedIn()){
      this.lastFailed = localStorage.getItem("LAST_FAILED_ATTEMPT");
      this.lastSuccessful = localStorage.getItem("LAST_SUCCESSFUL_ATTEMPT");
      return true;
    }
     return false;
  }

  public showPasswords(): void{
    this.router.navigate(["passwords"]);
  }

  public changeMainPassword(): void{
    this.router.navigate(["change-main-password"]);
  }
}
