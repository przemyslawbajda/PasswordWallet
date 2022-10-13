import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {confirmPasswordValidator} from "../../validators/confirm-password.validator";
import {ConfirmPasswordErrorStateMatcher} from "../../validators/confirm-password-error-state-matcher.validator";

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  confirmPasswordErrorStateMatcher = new ConfirmPasswordErrorStateMatcher();

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.createForm();
  }

  private createForm():void{
    this.form= new FormGroup({
      login: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
      confirmPassword: new FormControl(null, [Validators.required]),
      isHashed: new FormControl(true, [Validators.required])
    }, [confirmPasswordValidator])
  }

  public onRegister(): void{
    this.authService.register({login: this.form.value.login,
                              password: this.form.value.password,
                              isHash: this.form.value.isHash});
  }

  setIsHashFormValue(formValue: boolean): void {
    this.form.controls['isHashed'].setValue(formValue);
  }
}
