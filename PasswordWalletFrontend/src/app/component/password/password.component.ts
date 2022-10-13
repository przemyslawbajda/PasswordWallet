import {Component, Input, OnInit} from '@angular/core';
import {StoreService} from "../../store/store.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../../models/password";

@Component({
  selector: 'password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {

  @Input() password: Password;

  constructor() {}

  public onDelete(): void {}

  public onEdit(): void {}
}
