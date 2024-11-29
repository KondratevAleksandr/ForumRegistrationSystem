package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    public static final UserRepository INSTANCE = new UserRepository();

    private final Map<String, User> users = new HashMap<>();

    private UserRepository() {}

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public Optional<User> findUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public boolean isUserExist(String username) {
        return users.containsKey(username);
    }
}
