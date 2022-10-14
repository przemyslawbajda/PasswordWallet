import {Component, Input, OnInit} from '@angular/core';
import {Password} from "../../models/password";
import {PasswordService} from "../../services/password.service";
import {MatDialog} from "@angular/material/dialog";
import {EditPasswordComponent} from "../edit-password/edit-password.component";
import {StoreService} from "../../store/store.service";

@Component({
  selector: 'password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {

  @Input() password: Password;

  constructor(private passwordService: PasswordService,
              private editPasswordDialog: MatDialog) {}

  public onDelete(): void {
    this.passwordService.deletePassword(this.password.id);
  }

  public onEdit(): void {
    this.editPasswordDialog.open(EditPasswordComponent, {
      data: this.password,
      width: '500px'
    });
  }

  public onTogglePassword() {
  }
}
