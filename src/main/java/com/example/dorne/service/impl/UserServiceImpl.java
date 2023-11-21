package com.example.dorne.service.impl;

import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.model.service.UserServiceModel;
import com.example.dorne.repository.UserRepository;
import com.example.dorne.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceModel findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        this.userRepository.save(map(userServiceModel));
    }

    private UserEntity map(UserServiceModel userServiceModel) {

        if (userRepository.count() == 0) {
            userServiceModel.setRole(UserRoleEnum.valueOf(UserRoleEnum.ADMIN.name()));
        } else {
            userServiceModel.setRole(UserRoleEnum.valueOf(UserRoleEnum.USER.name()));
        }

        return new UserEntity(userServiceModel.getUsername(),
                passwordEncoder.encode(userServiceModel.getPassword()),
                userServiceModel.getEmail(),
                userServiceModel.getRole());
    }
}
