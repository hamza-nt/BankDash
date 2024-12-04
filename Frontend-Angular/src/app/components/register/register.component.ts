import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username = '';
  password = '';
  confirmPassword = '';
  email = '';
  profilePicture: File | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  onRegister() {
    if (this.password !== this.confirmPassword) {
      alert('Les mots de passe ne correspondent pas');
      return;
    }
  
    const formData = new FormData();
    formData.append('username', this.username);
    formData.append('password', this.password);
    formData.append('email', this.email);
    formData.append('role', 'USER');
    if (this.profilePicture) {
      formData.append('profilePicture', this.profilePicture);
    }
  
    this.authService.signup(formData).subscribe({
      next: (response) => {
        console.log('Inscription réussie :', response);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Erreur lors de l\'inscription :', error);
        alert('Erreur lors de l\'inscription. Consultez la console pour plus de détails.');
      }
    });
  }

  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input?.files?.[0]) {
      this.profilePicture = input.files[0];
    }
  }
}