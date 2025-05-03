import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  signup(user: User): Observable<User> {
    return this.http.post<User>(`${API_URL}/auth/signup`, user);
  }

  login(credentials: { username: string; password: string }): Observable<void> {
    return this.http.post<void>(`${API_URL}/auth/login`, credentials);
  }
}
