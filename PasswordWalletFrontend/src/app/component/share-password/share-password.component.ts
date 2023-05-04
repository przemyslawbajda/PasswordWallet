import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Password} from "../../models/password";
import {PasswordControllerService} from "../../services/password-controller.service";
import {StoreService} from "../../store/store.service";
import {User} from "../../models/user";

@Component({
  selector: 'share-password',
  templateUrl: './share-password.component.html',
  styleUrls: ['./share-password.component.css']
})
export class SharePasswordComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: Password,
              private passwordService: PasswordControllerService,
              public store: StoreService,
              private dialogRef: MatDialogRef<SharePasswordComponent>) { }

  public ngOnInit():void {
    this.store.getUsers();
  }

  public sharePassword(user: User): void {
    this.store.sharePassword({userId: user.id, passwordId: this.dialogData.id});
  }

}
