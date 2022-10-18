import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {confirmPasswordValidator} from "../../validators/confirm-password.validator";
import {PasswordControllerService} from "../../services/password-controller.service";
import {ConfirmPasswordErrorStateMatcher} from "../../validators/confirm-password-error-state-matcher.validator";
import {AuthService} from "../../services/auth.service";
import {AuthControllerService} from "../../services/auth-controller.service";

@Component({
  selector: 'change-main-password.component',
  templateUrl: './change-main-password.component.html',
  styleUrls: ['./change-main-password.component.css']
})
export class ChangeMainPasswordComponent implements OnInit {

  form: FormGroup;
  confirmPasswordErrorStateMatcher = new ConfirmPasswordErrorStateMatcher();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.createForm();
  }


  private createForm():void{
    this.form= new FormGroup({
      oldPassword: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
      confirmPassword: new FormControl(null, [Validators.required])
    }, [confirmPasswordValidator])
  }


  onSubmit() {
    this.authService.changeMainPassword({newPassword: this.form.value.password, oldPassword: this.form.value.oldPassword});
  }
}
