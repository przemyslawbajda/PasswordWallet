import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.createForm();
  }

  private createForm():void{
    this.form= new FormGroup({
      login: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required])
    })
  }

  public onLogin(): void{
    this.authService.login(this.form.value)
  }
}
