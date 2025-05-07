import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-setup',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./setup.component.scss'],
  templateUrl: './setup.component.html'
})
export class SetupComponent {
  formGroup: FormGroup;
  email: string;
  errorMessage = '';
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
    this.email = history.state.email || '';
    this.formGroup = this.fb.group(
      {
        username: ['', Validators.required],
        password: ['', Validators.required],
        confirm: ['', Validators.required]
      },
      { validators: this.matchPasswords }
    );
  }

  submit() {
    if (this.formGroup.invalid || !this.email) return;
    this.isSubmitting = true;

    const username = this.formGroup.get('username')!.value;
    const password = this.formGroup.get('password')!.value;

    this.auth.setup(this.email, username, password).subscribe({
      next: () => {
        this.router.navigate(['/welcome']);
      },
      error: () => {
        this.errorMessage = 'Neizdevās izveidot kontu';
        this.isSubmitting = false;
      }
    });
  }

  private matchPasswords(group: FormGroup) {
    return group.get('password')!.value === group.get('confirm')!.value
      ? null
      : { mismatch: true };
  }
}
