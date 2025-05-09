import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { API_URL } from './constants/constants';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly baseUrl = `${API_URL}/auth`;
  private readonly tokenKey = 'auth_token';

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  signup(email: string) {
    return this.http.post<number>(`${this.baseUrl}/signup`, { email });
  }

  setup(email: string, username: string, password: string) {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/setup`, { email, username, password })
      .pipe(
        tap(res => {
          this.setToken(res.token);
          this.storeUserIdAfterAuth(email);
        })
      );
  }

  login(email: string, password: string) {
    return this.http
      .post<{ token: string }>(`${this.baseUrl}/login`, { email, password })
      .pipe(
        tap(res => {
          this.setToken(res.token);
          this.storeUserIdAfterAuth(email);
        })
      );
  }

  private storeUserIdAfterAuth(email: string): void {
    if (isPlatformBrowser(this.platformId)) {
      this.http.get<{ id: number }>(
        `${API_URL}/users/email/${email}`,
        { headers: { Authorization: `Bearer ${this.getToken()}` } }
      )
        .subscribe({
          next: (user) => localStorage.setItem('user_id', user.id.toString()),
          error: (err) => console.error('Failed to store user ID:', err)
        });
    }
    }

  private setToken(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(this.tokenKey, token);
      sessionStorage.setItem(this.tokenKey, token);
    }
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(this.tokenKey);
      sessionStorage.removeItem(this.tokenKey);
      localStorage.removeItem('user_id');
    }
  }

  isLoggedIn(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      return !!localStorage.getItem(this.tokenKey) || !!sessionStorage.getItem(this.tokenKey);
    }
    return false;
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(this.tokenKey) || sessionStorage.getItem(this.tokenKey);
    }
    return null;
  }

  getCurrentUserId(): number {
    if (!isPlatformBrowser(this.platformId)) {
      throw new Error('Authentication required');
    }

    const token = this.getToken();
    if (!token) throw new Error('Not authenticated');

    try {
      const userId = localStorage.getItem('user_id');
      //@ts-ignore
      if (!userId) throw new Error('User ID not found - please log in again');

      return parseInt(userId, 10);
    } catch (e) {
      console.error('Auth error:', e);
      throw new Error('Failed to get user ID');
    }
  }
}
