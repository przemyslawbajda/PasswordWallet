import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Password} from "../../models/password";
import {PasswordService} from "../../services/password.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.css']
})
export class EditPasswordComponent implements OnInit {

  form: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: Password,
              private passwordService: PasswordService,
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

  onSubmit() {

    let editedPassword = {
      description: this.form.controls['description'].value,
      id: this.dialogData.id,
      login: this.form.controls['login'].value,
      password: this.form.controls['password'].value,
      webAddress: this.form.controls['webAddress'].value
    }

    this.passwordService.editPassword(editedPassword).subscribe(
      () => {
        this.dialogRef.close();
      }
    );
  }
}
