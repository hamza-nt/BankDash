import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../../models/userDTO';
import { Router } from '@angular/router';
import { UserService } from '../../services/user/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  users: UserDTO[] = [];
  isLoading: boolean = true;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des utilisateurs', error);
        this.isLoading = false;
      }
    });
  }

  onEdit(userId: string): void {
    this.router.navigate(['/edit-user', userId]);
  }

  onDelete(userId: string): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
      this.userService.deleteUser(userId).subscribe({
        next: () => {
          this.users = this.users.filter(user => user.id !== userId);
          alert('Utilisateur supprimé avec succès');
        },
        error: (error) => {
          console.error('Erreur lors de la suppression de l\'utilisateur', error);
          alert('Erreur lors de la suppression de l\'utilisateur');
        }
      });
    }
  }
}

