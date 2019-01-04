package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
    private final Map<String, User> usersById = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();

    public void saveOrUpdate(User newUser) {
        if(newUser.getId() == null)
            newUser.setId(UUID.randomUUID().toString());
        else {
            String oldEmail = usersById.get(newUser.getId()).getEmail();
            if(!oldEmail.equals(newUser.getEmail()))
                usersByEmail.remove(oldEmail);
        }
        usersById.put(newUser.getId(), newUser);
        usersByEmail.put(newUser.getEmail(), newUser);
    }
    public User findByEmail(String email) {
        return usersByEmail.get(email);
    }
}
