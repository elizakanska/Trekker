import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(`${API_URL}/users`);
  }

  getById(id: number): Observable<User> {
    return this.http.get<User>(`${API_URL}/users/${id}`);
  }

  create(user: User): Observable<User> {
    return this.http.post<User>(`${API_URL}/users`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/users/${id}`);
  }
}
