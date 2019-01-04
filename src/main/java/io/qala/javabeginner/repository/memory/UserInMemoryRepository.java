package io.qala.javabeginner.repository.memory;

import io.qala.javabeginner.domain.User;
import io.qala.javabeginner.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserInMemoryRepository implements UserRepository {
    private final Map<String, User> usersById = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();

    @Override
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
    @Override
    public User findByEmail(String email) {
        return usersByEmail.get(email);
    }
}
