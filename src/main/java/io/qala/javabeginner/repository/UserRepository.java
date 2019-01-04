package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.User;

public interface UserRepository {
    void saveOrUpdate(User newUser);

    User findByEmail(String email);
}
