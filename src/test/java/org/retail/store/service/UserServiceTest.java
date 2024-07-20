package org.retail.store.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.retail.store.mapper.UserMapper;
import org.retail.store.model.UserFixture;
import org.retail.store.model.entity.User;
import org.retail.store.model.request.UserRequest;
import org.retail.store.repository.UserRepository;
import org.retail.store.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void testGetUserWhenUserExists() {
        UserRequest request = UserFixture.getUserRequest();
        User existingUser = UserFixture.getUserFixture();

        when(userRepository.findByPhoneNumber(request.getPhoneNumber())).thenReturn(Optional.of(existingUser));

        User user = userServiceImpl.getUser(request);

        assertEquals(existingUser,
                     user);
        verify(userRepository,
               times(1)).findByPhoneNumber(request.getPhoneNumber());
    }

    @Test
    void testCreateUser() {
        UserRequest userRequest = UserFixture.getUserRequest();

        User user = UserFixture.getUserFixture();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User userData = userServiceImpl.createUser(userRequest);

        assertNotNull(userData);
        assertEquals(userData.getId(),
                     user.getId());
        assertEquals(userData.getPhoneNumber(),
                     user.getPhoneNumber());
    }

}
