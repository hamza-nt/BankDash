import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}
  onLogin() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Réponse reçue :', response);
        if (response && response.token) {
          console.log('Token à sauvegarder :', response.token);
          this.authService.saveToken(response.token);
          this.router.navigate(['/dashboard']);
        } else {
          console.error('Token manquant dans la réponse');
        }
      },
      error: (err) => {
        console.error('Erreur lors de la connexion :', err);
      }
    });
  }

  onSignUp() {
    this.router.navigate(['/register']);
  }
  
}