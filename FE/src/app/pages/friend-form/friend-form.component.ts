import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { FriendService } from '../../services/friend.service';
import { AuthService } from '../../services/auth.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-friend-form',
  templateUrl: './friend-form.component.html',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  styleUrls: ['./friend-form.component.scss']
})
export class FriendFormComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private friendService: FriendService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      message: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {}

  submit(): void {
    if (this.form.valid) {
      const userId = this.authService.getCurrentUserId();
      const friendUsername = this.form.get('name')?.value;
      const message = this.form.get('message')?.value;

      this.friendService.addFriend(friendUsername).subscribe(
        () => {
          console.log('Friend added');
          this.router.navigate(['/friends']);
        },
        (error) => {
          console.error('Error adding friend:', error);
        }
      );
    }
  }

  cancel(): void {
    this.router.navigate(['/friends']);
  }
}
