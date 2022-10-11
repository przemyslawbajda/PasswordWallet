import { Component, OnInit } from '@angular/core';
import {StoreService} from "../../store/store.service";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'password-list',
  templateUrl: './password-list.component.html',
  styleUrls: ['./password-list.component.css']
})
export class PasswordListComponent implements OnInit {

  passwords$: BehaviorSubject<any[]> = this.store$.passwords$

  constructor(private store$: StoreService) {
  }

  public ngOnInit():void {
    this.store$.getPasswords();
  }


}
