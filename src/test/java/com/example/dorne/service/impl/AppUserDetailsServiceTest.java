package com.example.dorne.service.impl;

import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    private AppUserDetailsService appUserDetailsServiceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        appUserDetailsServiceToTest = new AppUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> appUserDetailsServiceToTest.loadUserByUsername("example@oracle.com"));
    }

    @Test
    void testUserFoundException() {
        //Arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        //Act
        UserDetails userDetails =
                appUserDetailsServiceToTest.loadUserByUsername(testUserEntity.getEmail());

        //Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername(), "Username is not mapped to email!");
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER), "The user is not user!");

    }

    private static UserEntity createTestUser() {
        return new UserEntity("Ivan", "ivan1234", "ivan1234@gmail.com", UserRoleEnum.USER);
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
            .getAuthorities()
            .stream().anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

}
