package com.bankdash.service;

import com.bankdash.dto.UserDTO;
import com.bankdash.entity.User;
import com.bankdash.mapper.UserMapper;
import com.bankdash.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toUserEntity(userDTO);
        return UserMapper.INSTANCE.toUserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());
        existingUser.setProfilePicture(userDTO.getProfilePicture());

        return UserMapper.INSTANCE.toUserDTO(userRepository.save(existingUser));
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
