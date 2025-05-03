import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from './constants/constants';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = `${API_URL}/auth`;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${this.authUrl}/login`, {}, {
      headers: {
        Authorization: 'Basic ' + btoa(`${username}:${password}`)
      },
      responseType: 'text'
    });
  }

  signup(username: string, password: string, email: string): Observable<any> {
    return this.http.post(`${this.authUrl}/signup`, { username, password, email });
  }
}
