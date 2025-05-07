import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { API_URL } from './constants/constants';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly baseUrl = `${API_URL}/auth`;
  private readonly tokenKey = 'auth_token';

  constructor(private http: HttpClient) {}

  signup(email: string) {
    return this.http.post<number>(`${this.baseUrl}/signup`, { email });
  }

  setup(email: string, username: string, password: string) {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/setup`, { email, username, password })
      .pipe(tap(res => this.setToken(res.token)));
  }

  login(email: string, password: string) {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/login`, { email, password })
      .pipe(tap(res => this.setToken(res.token)));
  }

  private setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
    sessionStorage.setItem(this.tokenKey, token);
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    sessionStorage.removeItem(this.tokenKey);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.tokenKey) || !!sessionStorage.getItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey) || sessionStorage.getItem(this.tokenKey);
  }
}
