package org.retail.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.retail.store.model.entity.User;
import org.retail.store.model.request.UserRequest;
import org.retail.store.repository.UserRepository;
import org.retail.store.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User getUser(UserRequest request) {
        Optional<User> user = userRepository.findByPhoneNumber(request.getPhoneNumber());
        return user.orElseGet(() -> createUser(request));
    }

    @Override
    public User createUser(UserRequest request) {
        User newUser = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .type(request.getType())
                .build();
        return userRepository.save(newUser);

    }

}
