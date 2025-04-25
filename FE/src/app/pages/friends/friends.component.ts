import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

interface Friend {
  id:      number;
  name:    string;
  avatar?: string;
}

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {
  friends: Friend[] = [];
  placeholder = 'https://via.placeholder.com/160x120?text=No+Image';

  constructor(private router: Router) {}

  ngOnInit() {
    // TODO: fetch from your backend instead
    this.friends = [
      { id: 1, name: 'Jānis Programmētājs' },
      { id: 2, name: 'Pēteris Testētājs' },
      { id: 3, name: 'Māris Sistēmanalītiķis' },
      { id: 4, name: 'Frīdrihs Klients' },
      { id: 5, name: 'Nauris Vadītājs' },
      { id: 6, name: 'Guntis Juniors' }
    ];
  }

  /** navigate to friend details or open edit */
  viewFriend(friend: Friend) {
    this.router.navigate(['/friends', friend.id]);
  }

  /** add a new friend */
  addFriend() {
    this.router.navigate(['/friends', 'new']);
  }
}



// import { Component } from '@angular/core';
//
// @Component({
//   selector: 'app-friends',
//   imports: [],
//   templateUrl: './friends.component.html',
//   styleUrl: './friends.component.scss'
// })
// export class FriendsComponent {
//
// }
