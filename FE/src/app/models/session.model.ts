export interface Session {
  user1Id: number;
  user2Id?: number;
  inviteCode: string;
  state: 'CREATED' | 'JOINED' | 'FILTERED' | 'IN_PROGRESS' | 'RANKING' | 'COMPLETE';
}
