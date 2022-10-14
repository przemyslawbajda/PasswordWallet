import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatToolbarModule} from "@angular/material/toolbar";
import { LoginComponent } from './component/login/login.component';
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {TokenInterceptor} from "./interceptors/token.interceptor";
import {PasswordListComponent} from "./component/password-list/password-list.component";
import {ToolbarComponent} from "./component/toolbar/toolbar.component";
import {PasswordComponent} from "./component/password/password.component";
import {MatIconModule} from "@angular/material/icon";
import {RegisterComponent} from "./component/register/register.component";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { AddNewPasswordComponent } from './component/add-new-password/add-new-password.component';
import { EditPasswordComponent } from './component/edit-password/edit-password.component';
import {MatDialogModule} from "@angular/material/dialog";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PasswordListComponent,
    ToolbarComponent,
    PasswordComponent,
    RegisterComponent,
    AddNewPasswordComponent,
    EditPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    NgbModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatIconModule,
    MatButtonToggleModule,
    MatDialogModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
