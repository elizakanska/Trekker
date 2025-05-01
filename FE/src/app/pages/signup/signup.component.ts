import { Component }            from '@angular/core';
import { CommonModule }         from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { Router }               from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,                // <-- mark as standalone
  imports: [
    CommonModule,                  // <-- ngIf, ngFor, etc.
    ReactiveFormsModule            // <-- formGroup, formControlName, etc.
  ],
  templateUrl: './signup.component.html',
  styleUrls:   ['./signup.component.scss']
})
export class SignupComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      email: ['']   // no validators needed if you always allow
    });
  }

  submit() {
    // unconditionally navigate
    this.router.navigate(['/welcome']);
  }

  continueWithGoogle() {
    this.router.navigate(['/welcome']);
  }
}
