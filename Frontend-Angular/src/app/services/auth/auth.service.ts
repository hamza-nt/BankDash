import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/users';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const body = { username, password };
    return this.http.post<any>(`${this.apiUrl}/getToken`, body);
  }

  logout(): void {
    const token = this.getToken();
    if (token) {
      this.http
        .post(`${this.apiUrl}/logout`, {}, { headers: { Authorization: `Bearer ${token}` } })
        .subscribe({
          next: () => console.log('Déconnexion côté serveur réussie'),
          error: (err) => console.error('Erreur lors de la déconnexion côté serveur', err),
        });
    }
    this.clearToken();
  }

  saveToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  signup(formData: FormData): Observable<any> {
    return this.http.post<any>(this.apiUrl, formData);
  }

  clearToken(): void {
    localStorage.removeItem('jwtToken');
  }

}
