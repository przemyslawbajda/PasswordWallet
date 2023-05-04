import {Component, Input} from '@angular/core';
import {Password} from "../../models/password";
import {MatDialog} from "@angular/material/dialog";
import {EditPasswordComponent} from "../edit-password/edit-password.component";
import {StoreService} from "../../store/store.service";
import {SharePasswordComponent} from "../share-password/share-password.component";

@Component({
  selector: 'password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {

  @Input() password: Password;
  @Input() isEditMode: boolean = false;

  constructor(public store: StoreService,
              private editPasswordDialog: MatDialog) {}

  public onDelete(): void {
    this.store.deletePassword(this.password.id);
  }

  public onEdit(): void {
    this.editPasswordDialog.open(EditPasswordComponent, {
      data: this.password,
      width: '500px'
    });
  }

  public onShare(): void {
    this.editPasswordDialog.open(SharePasswordComponent, {
      data: this.password,
      width: '500px'
    });
  }

  public onTogglePassword() {
    this.password.password
      ? this.store.setPassword(null,this.password.id)
      : this.store.getDecryptedPassword(this.password.id);

  }
}
