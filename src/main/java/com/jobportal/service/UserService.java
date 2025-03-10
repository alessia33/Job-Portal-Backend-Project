package com.jobportal.service;

import com.jobportal.mapper.UserMapper;
import com.jobportal.model.dto.UserDto;
import com.jobportal.model.entity.User;
import com.jobportal.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserDto> getAllUsers(int page, int size, String sortBy, String sortDir, String username) {
        Sort sort = sortDir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage;

        if (username != null && !username.trim().isEmpty()) {
            userPage = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }

        return userPage.map(UserMapper.INSTANCE::userToUserDto);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userToUserDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
