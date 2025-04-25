import { Component }          from '@angular/core';
import {ReactiveFormsModule, FormBuilder, Validators, FormGroup} from '@angular/forms';
import { CommonModule }       from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-friend-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './friend-form.component.html',
  styleUrls:   ['./friend-form.component.scss']
})
export class FriendFormComponent {
  form!: FormGroup;   // declare but don’t inline-init
  constructor(private fb: FormBuilder, private router: Router) {
    this.form = this.fb.group({
      name:    ['', Validators.required],
      message: ['Savienojamies un izvēlamies taku!', Validators.required]
    });
  }

  // form = this.fb.group({
  //   name:    ['', Validators.required],
  //   message: ['Savienojamies un izvēlamies taku!', Validators.required]
  // });

  cancel() {
    this.router.navigate(['/friends']);
  }

  submit() {
    if (this.form.invalid) return;
    const { name, message } = this.form.value;
    console.log('Invite sent to', name, 'with message', message);
    // TODO: call your backend
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
//   // form: FormGroup;
//
// }
