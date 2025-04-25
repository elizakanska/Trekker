import { Component }    from '@angular/core';
import { CommonModule } from '@angular/common';

interface Friend {
  name:  string;
  image: string;
}

@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './friends.component.html',
  styleUrls:   ['./friends.component.scss']
})
export class FriendsComponent {
  // static list
  friends: Friend[] = [
    { name: 'Jānis Programmētājs',    image: 'https://via.placeholder.com/180?text=Img' },
    { name: 'Pēteris Testētājs',       image: 'https://via.placeholder.com/180?text=Img' },
    { name: 'Māris Sistēmanalītiķis', image: 'https://via.placeholder.com/180?text=Img' },
    { name: 'Frīdrihs Klients',       image: 'https://via.placeholder.com/180?text=Img' },
    { name: 'Nauris Vadītājs',        image: 'https://via.placeholder.com/180?text=Img' },
    { name: 'Guntis Juniors',         image: 'https://via.placeholder.com/180?text=Img' }
  ];
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
