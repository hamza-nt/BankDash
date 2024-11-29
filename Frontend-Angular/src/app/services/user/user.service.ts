import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  // Importer HttpClient
import { Observable } from 'rxjs';  // Pour gérer les réponses asynchrones
import { UserDTO } from '../../models/userDTO';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = `${environment.apiBaseUrl}/users`;  // L'URL de l'API (assurez-vous que cela correspond à votre backend)

  constructor(private http: HttpClient) { }

  // Récupérer tous les utilisateurs
  getAllUsers(): Observable<UserDTO[]> {
    return this.http.get<UserDTO[]>(this.apiUrl);  // Requête GET pour obtenir la liste des utilisateurs
  }

  // Récupérer un utilisateur par son ID
  getUserById(id: string): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.apiUrl}/${id}`);  // Requête GET avec un paramètre d'ID
  }

  // Créer un utilisateur
  createUser(userCreateDTO: any): Observable<UserDTO> {
    return this.http.post<UserDTO>(this.apiUrl, userCreateDTO);  // Requête POST pour créer un utilisateur
  }

  // Mettre à jour un utilisateur
  updateUser(id: string, userUpdateDTO: any): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.apiUrl}/${id}`, userUpdateDTO);  // Requête PUT pour mettre à jour un utilisateur
  }

  // Supprimer un utilisateur
  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);  // Requête DELETE pour supprimer un utilisateur
  }
}
