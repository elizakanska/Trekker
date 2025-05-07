import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./signup.component.scss'],
  templateUrl: './signup.component.html'
})
export class SignupComponent {
  formGroup: FormGroup;
  isSubmitting = false;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
    this.formGroup = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  submit() {
    if (this.formGroup.invalid) return;
    this.isSubmitting = true;

    const email = this.formGroup.get('email')!.value;
    this.auth.signup(email).subscribe({
      next: () => {
        this.router.navigate(['/setup'], { state: { email } });
      },
      error: (err) => {
        this.isSubmitting = false;
        if (err.status === 409) {
          this.errorMessage = 'E-pasts jau reģistrēts. Lūdzu, piesakieties vai izmantojiet citu e-pastu.';
        } else {
          this.errorMessage = 'Radās kļūda. Mēģiniet vēlreiz.';
        }
      }
    });
  }

  continueWithGoogle(){}
}
