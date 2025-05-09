import {User} from './user.model';

export interface Friend {
  id: number;
  user1Id: number;
  friendId: number;
  friend: User;
}
