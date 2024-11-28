package com.company;

import java.util.Map;

public class LoginHandler implements RequestHandler {
    @Override
    public void handle(Map<RequestParameter, String> request, UserService userService) {
        String login = request.get(RequestParameter.LOGIN);
        String password = request.get(RequestParameter.PASSWORD);
        userService.login(login, password);
    }
}
