package com.example.dorne.util;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.repository.CategoryRepository;
import com.example.dorne.repository.DestinationRepository;
import com.example.dorne.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private DestinationRepository destinationRepository;

    public TestDataUtils(UserRepository userRepository,
                         CategoryRepository categoryRepository,
                         DestinationRepository destinationRepository) {

        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.destinationRepository = destinationRepository;
    }

    public UserEntity createTestUser() {
        var user = new UserEntity()
                .setUsername("testuser")
                .setPassword("test")
                .setEmail("testuser@gmail.com")
                .setRole(UserRoleEnum.USER);

        return userRepository.save(user);
    }
    public Destination createTestDestination() {
        var destination = new Destination("Sofia, Bulgaria",
                "https://lp-cms-production.imgix.net/2019-06/6303650c1b029ccf013c3c59d260fd9f-aleksander-nevski-cathedral.jpg");

        return destinationRepository.save(destination);
    }

    public void cleanUpDatabase() {
        userRepository.deleteAll();
        categoryRepository.deleteAll();
        destinationRepository.deleteAll();
    }

}
