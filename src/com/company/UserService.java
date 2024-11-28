package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private Map<String, RequestHandler> requestHandlers = new HashMap<String, RequestHandler>();

    public UserService() {
        requestHandlers.put("register", new RegisterHandler());
        requestHandlers.put("login", new LoginHandler());
        requestHandlers.put("logout", new LogoutHandler());
    }


    public void register(String username, String password) {
        if (isValid(username) || isValid(password)) {
            System.out.println("fail: incorrect username or password");
            return;
        }

        if (userRepository.userExist(username)) {
            System.out.println("fail: user already exists");
            return;
        }

        userRepository.addUser(new User(username, password));
        System.out.println("success: new user added");
    }

    public void login(String username, String password) {
        if (isValid(username) || isValid(password)) {
            System.out.println("fail: incorrect username or password");
            return;
        }
        User user = userRepository.findUser(username);
        if (user == null) {
            System.out.println("fail: no such user");
        } else if (!user.getPasswordHash().equals(PasswordHashing.hashPassword(password))) {
            System.out.println("fail: incorrect password");
        } else if (user.isOnLine()) {
            System.out.println("fail: already logged in");
        } else {
            user.setOnLine(true);
            System.out.println("success: user logged in");
        }
    }

    public void logout(String username) {
        if (isValid(username)) {
            System.out.println("fail: incorrect username");
            return;
        }
        User user = userRepository.findUser(username);
        if (user == null) {
            System.out.println("fail: no such user");
        } else if (!user.isOnLine()) {
            System.out.println("fail: already logged out");
        } else {
            user.setOnLine(false);
            System.out.println("success: already logged out");
        }
    }

    public void handleRequest(Map<RequestParameter, String> request) {
        String requestType = request.get(RequestParameter.REQUEST_TYPE);
        RequestHandler handler = requestHandlers.get(requestType);
        if (handler != null) {
            handler.handle(request, this);
        } else {
            System.out.println("Неверная команда. Попробуйте снова");
        }
    }

    private boolean isValid(String credentials) {
        String regex = "^[\\x21-\\x7E]+$";
        return credentials == null ||
                credentials.length() <= 0 || credentials.length() > 30 || !Pattern.matches(regex, credentials);
    }
}
