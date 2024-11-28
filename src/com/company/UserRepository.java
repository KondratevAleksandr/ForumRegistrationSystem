package com.company;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User findUser(String username) {
        return users.get(username);
    }

    public boolean userExist(String username) {
        return users.containsKey(username);
    }
}
