import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {confirmPasswordValidator} from "../../validators/confirm-password.validator";
import {PasswordService} from "../../services/password.service";

@Component({
  selector: 'app-add-new-password',
  templateUrl: './add-new-password.component.html',
  styleUrls: ['./add-new-password.component.css']
})
export class AddNewPasswordComponent implements OnInit {

  form: FormGroup;

  constructor(private passwordService: PasswordService) { }

  ngOnInit(): void {
    this.createForm();
  }


  private createForm():void{
    this.form= new FormGroup({
      login: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required]),
      webAddress: new FormControl(null, [Validators.required]),
      description: new FormControl(null)
    })
  }


  onSubmit() {
    this.passwordService.addPassword(this.form.value);
  }
}
