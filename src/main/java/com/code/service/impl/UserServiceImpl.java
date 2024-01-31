package com.code.service.impl;

import com.code.dto.request.UserRequest;
import com.code.dto.response.UserResponse;
import com.code.mapper.UserMapper;
import com.code.model.User;
import com.code.repository.UserRepository;
import com.code.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
// @AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.userRequestToUser(request);
        user = userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        updateUserFromRequest(user, request);
        user = userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);

        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.userToUserResponse(user);
    }

    @Transactional
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    private void updateUserFromRequest(User user, UserRequest request) {
        // Actualiza los campos del usuario
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Considera encriptar la contraseña
        if (user.getUserProfile() != null) {
            user.getUserProfile().setFirstName(request.getFirstName());
            user.getUserProfile().setLastName(request.getLastName());
            user.getUserProfile().setDni(request.getDni());
        }
    }
}
