// src/app/pages/signup/signup.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  submit() {
    if (this.form.invalid) return;
    console.log('Signing up with', this.form.value.email);
    // TODO: call backend signup endpoint
  }

  continueWithGoogle() {
    console.log('Google OAuth flow');
    // TODO: trigger OAuth
  }
}
