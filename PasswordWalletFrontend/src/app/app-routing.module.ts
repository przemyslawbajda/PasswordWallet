import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {PasswordListComponent} from "./component/password-list/password-list.component";
import {RegisterComponent} from "./component/register/register.component";
import {AddNewPasswordComponent} from "./component/add-new-password/add-new-password.component";
import {EditPasswordComponent} from "./component/edit-password/edit-password.component";
import {ChangeMainPasswordComponent} from "./component/change-main-password/change-main-password.component";
import {SharedPasswordListComponent} from "./component/shared-password-list/shared-password-list.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'passwords', component: PasswordListComponent},
  { path: 'add-new-password', component: AddNewPasswordComponent},
  { path: 'edit-password', component: EditPasswordComponent},
  { path: 'change-main-password', component: ChangeMainPasswordComponent},
  { path: 'shared-passwords', component: SharedPasswordListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
