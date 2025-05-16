import {Session} from './session.model';
import {Trail} from './trail.model';
import {User} from './user.model';

export interface SessionLike {
  id: number;
  session: Session;
  trail: Trail;
  user: User;
  liked: boolean;
  round: number;
}
