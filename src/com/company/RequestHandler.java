package com.company;

import java.util.Map;

public interface RequestHandler {
    void handle(Map<RequestParameter, String> request, UserService userService);
}
