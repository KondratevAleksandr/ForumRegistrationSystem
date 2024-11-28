package com.company;

import java.util.Map;

public class RegisterHandler implements RequestHandler {
    @Override
    public void handle(Map<RequestParameter, String> request, UserService userService) {
        String login = request.get(RequestParameter.LOGIN);
        String password = request.get(RequestParameter.PASSWORD);
        userService.register(login, password);
    }
}
