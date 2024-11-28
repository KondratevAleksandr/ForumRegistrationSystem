package com.company;

import java.util.Map;

public class LogoutHandler implements RequestHandler {
    @Override
    public void handle(Map<RequestParameter, String> request, UserService userService) {
        String login = request.get(RequestParameter.LOGIN);
        userService.logout(login);
    }
}
