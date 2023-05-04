import {Component, OnInit} from '@angular/core';
import {StoreService} from "../../store/store.service";
import {BehaviorSubject} from "rxjs";
import {Password} from "../../models/password";

@Component({
  selector: 'shared-password-list',
  templateUrl: './shared-password-list.component.html',
  styleUrls: ['./shared-password-list.component.css']
})
export class SharedPasswordListComponent implements OnInit {

  passwords$: BehaviorSubject<Password[]> = this.store$.sharedPasswords$

  constructor(private store$: StoreService) {}

  public ngOnInit():void {
    this.store$.getSharedPasswords();
  }

}
