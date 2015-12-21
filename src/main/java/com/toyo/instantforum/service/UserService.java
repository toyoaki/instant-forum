package com.toyo.instantforum.service;

import com.toyo.instantforum.data.model.User;

public interface UserService {

    User findByUsername(String username);

    User save(User user);

}
