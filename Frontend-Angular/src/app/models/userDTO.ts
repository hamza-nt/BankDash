export interface UserDTO {
    id: string;            // Identifiant unique de l'utilisateur (généralement un String)
    username: string;      // Nom d'utilisateur
    email: string;         // Adresse e-mail de l'utilisateur
    role: string;          // Le rôle de l'utilisateur (par exemple, "USER" ou "ADMIN")
    profilePicture?: string; // URL de la photo de profil (optionnelle)
  }