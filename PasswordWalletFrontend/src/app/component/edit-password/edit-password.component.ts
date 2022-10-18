import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Password} from "../../models/password";
import {PasswordControllerService} from "../../services/password-controller.service";
import {Router} from "@angular/router";
import {StoreService} from "../../store/store.service";

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.css']
})
export class EditPasswordComponent implements OnInit {

  form: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: Password,
              private passwordService: PasswordControllerService,
              private store: StoreService,
              private dialogRef: MatDialogRef<EditPasswordComponent>) { }

  ngOnInit(): void {
    this.createForm();

  }

  private createForm():void{

    this.form= new FormGroup({
      login: new FormControl(this.dialogData.login, [Validators.required]),
      password: new FormControl("", [Validators.required]),
      webAddress: new FormControl(this.dialogData.webAddress, [Validators.required]),
      description: new FormControl(this.dialogData.description)
    })

    this.passwordService.getDecryptedPassword(this.dialogData.id).subscribe(
      data => {
        this.form.controls['password'].setValue(data.message);
      });

  }

  public onSubmit(): void {
    this.store.editPassword({...this.form.value, id: this.dialogData.id}, this.dialogRef);
  }
}
