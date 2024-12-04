package com.bankdash.service;

import com.bankdash.dto.UserCreateDTO;
import com.bankdash.dto.UserDTO;
import com.bankdash.entity.User;
import com.bankdash.mapper.UserMapper;
import com.bankdash.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final UserRepository userRepository;
    private int currentId;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User lastUser = userRepository.findTopByOrderByIdDesc();
        int newId = (lastUser == null) ? 1 : Integer.parseInt(lastUser.getId()) + 1;
        userCreateDTO.setId(String.valueOf(newId));
        String hashedPassword = encoder.encode(userCreateDTO.getPassword());

        User user = new User();
        user.setId(String.valueOf(newId));
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(userCreateDTO.getEmail());
        user.setRole(userCreateDTO.getRole());
        user.setCreatedAt(LocalDateTime.now());
        if (userCreateDTO.getProfilePicture() != null) {
            user.setProfilePicture(userCreateDTO.getProfilePicture());
        }

        user = userRepository.save(user);
        return UserMapper.INSTANCE.toUserDTO(user);
    }


    public UserDTO updateUser(String id, UserCreateDTO userCreateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userCreateDTO.getPassword() != null && !userCreateDTO.getPassword().isEmpty()) {
            String hashedPassword = encoder.encode(userCreateDTO.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        existingUser.setUsername(userCreateDTO.getUsername());
        existingUser.setEmail(userCreateDTO.getEmail());
        existingUser.setRole(userCreateDTO.getRole());

        if (userCreateDTO.getProfilePicture() != null) {
            existingUser.setProfilePicture(userCreateDTO.getProfilePicture());
        }

        existingUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.toUserDTO(existingUser);
    }


    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
