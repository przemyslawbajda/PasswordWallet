import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {StoreService} from "../../store/store.service";
import {MatSlideToggleChange} from "@angular/material/slide-toggle";

@Component({
  selector: 'toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  public lastSuccessful: string = "";
  public lastFailed: string = "";

  constructor(private authService: AuthService,
              private passwordStore: StoreService,
              private router: Router) {}

  public onLogout(): void{
    this.authService.logout();
  }

  public onModeChange(mode: MatSlideToggleChange): void {
    this.passwordStore.setEditMode(mode.checked);
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

  public showSharedPasswords(): void{
    this.router.navigate(["shared-passwords"]);
  }

  public changeMainPassword(): void{
    this.router.navigate(["change-main-password"]);
  }
}
