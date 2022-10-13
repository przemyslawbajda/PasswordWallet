import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../store/store.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../../models/password";

@Component({
  selector: 'password-list',
  templateUrl: './password-list.component.html',
  styleUrls: ['./password-list.component.css']
})
export class PasswordListComponent implements OnInit {

  passwords$: BehaviorSubject<Password[]> = this.store$.passwords$

  constructor(private store$: StoreService) {}

  public ngOnInit():void {
    this.store$.getPasswords();
  }


}
