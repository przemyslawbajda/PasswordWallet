import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  constructor(private authService: AuthService) {}

  public onLogout(): void{
    this.authService.logout();
  }

  public isLoggedIn(): boolean {
    return this.authService.isLoggedIn()
  }

}
