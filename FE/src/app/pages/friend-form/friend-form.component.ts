// src/app/pages/friends/friend-form.component.ts
import { Component }             from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                from '@angular/router';

@Component({
  selector: 'app-friend-form',
  templateUrl: './friend-form.component.html',
  styleUrls: ['./friend-form.component.scss']
})
export class FriendFormComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      name:    ['', Validators.required],
      message: ['', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) return;
    const { name, message } = this.form.value;

    // TODO: call your backend to send the invite
    console.log('Inviting', name, 'with message:', message);

    // After success, navigate back to friends list
    this.router.navigate(['/friends']);
  }

  cancel() {
    this.router.navigate(['/friends']);
  }
}



// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-friend-form',
//   imports: [],
//   templateUrl: './friend-form.component.html',
//   styleUrl: './friend-form.component.scss'
// })
// export class FriendFormComponent {
//
// }
