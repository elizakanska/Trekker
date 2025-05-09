import {Trail} from './trail.model';

export interface Favorite {
  id: number;
  userId: number;
  trailId: number;
  trail: Trail;
}
