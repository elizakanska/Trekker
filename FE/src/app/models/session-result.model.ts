import {Session} from './session.model';
import {Trail} from './trail.model';

export interface SessionResult {
  id: number;
  session: Session;
  trail: Trail;
  finalRank: number;
}
