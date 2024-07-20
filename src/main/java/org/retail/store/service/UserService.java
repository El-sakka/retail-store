package org.retail.store.service;

import org.retail.store.model.entity.User;
import org.retail.store.model.request.UserRequest;

public interface UserService {

    User getUser(UserRequest request);
    User createUser(UserRequest request);
}
