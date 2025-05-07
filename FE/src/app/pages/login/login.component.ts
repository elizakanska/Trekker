import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  styleUrls: ['./login.component.scss'],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  formGroup: FormGroup;
  errorMessage = '';
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
    this.formGroup = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  submit() {
    if (this.formGroup.invalid) return;
    this.isSubmitting = true;

    const email = this.formGroup.get('email')!.value;
    const password = this.formGroup.get('password')!.value;

    this.auth.login(email, password).subscribe({
      next: () => {
        this.router.navigate(['/welcome']);
      },
      error: () => {
        this.errorMessage = 'Nepareizs e-pasts vai parole';
        this.isSubmitting = false;
      }
    });
  }
}
