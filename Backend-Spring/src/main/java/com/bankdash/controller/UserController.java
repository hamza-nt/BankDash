package com.bankdash.controller;

import com.bankdash.dto.UserCreateDTO;
import com.bankdash.dto.UserDTO;
import com.bankdash.security.JwtService;
import com.bankdash.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${user.profile-picture.upload-dir}")
    private String uploadDir;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam String role,
                              @RequestParam(required = false) MultipartFile profilePicture) throws IOException {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername(username);
        userCreateDTO.setPassword(password);
        userCreateDTO.setEmail(email);
        userCreateDTO.setRole(role);
        String profilePictureName = null;
        if (profilePicture != null && !profilePicture.isEmpty()) {
            profilePictureName = saveProfilePicture(profilePicture);
        }
        userCreateDTO.setProfilePicture(profilePictureName);
        return userService.createUser(userCreateDTO);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable String id,
                              @RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam String role,
                              @RequestParam(required = false) MultipartFile profilePicture) throws IOException {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername(username);
        userCreateDTO.setPassword(password);
        userCreateDTO.setEmail(email);
        userCreateDTO.setRole(role);
        String profilePictureName = null;
        if (profilePicture != null && !profilePicture.isEmpty()) {
            profilePictureName = saveProfilePicture(profilePicture);
        }
        userCreateDTO.setProfilePicture(profilePictureName);
        return userService.updateUser(id, userCreateDTO);
    }

    private String saveProfilePicture(MultipartFile profilePicture) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + profilePicture.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);
        Files.createDirectories(path.getParent());
        profilePicture.transferTo(path);
        return fileName;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PostMapping("/getToken")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(Collections.singletonMap("token", jwtService.generateToken(authRequest.getUsername())));
        }

        throw new UsernameNotFoundException("Invalid user details.");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Refresh token is required");
        }

        try {
            String newAccessToken = jwtService.generateTokenFromRefreshToken(refreshToken);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(Collections.singletonMap("message", "Déconnexion réussie"));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthenticationRequest {
        private String username;
        private String password;
    }
}