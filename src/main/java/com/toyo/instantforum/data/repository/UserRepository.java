package com.toyo.instantforum.data.repository;

import org.springframework.data.repository.Repository;

import com.toyo.instantforum.data.model.User;

public interface UserRepository extends Repository<User, Long> {

    User findByUsername(String username);

    User save(User user);

}
