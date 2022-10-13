import {ErrorStateMatcher} from "@angular/material/core";
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

export class ConfirmPasswordErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidControl = control.invalid && control?.touched;
    const invalidNotTheSame = form.errors && form.errors['notSame'];

    return invalidControl || invalidNotTheSame;
  }
}
