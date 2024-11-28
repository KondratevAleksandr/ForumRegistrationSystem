package com.company;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static final UserService INSTANCE = new UserService();

    private Map<String, RequestHandler> requestHandlers = new HashMap<String, RequestHandler>();

    private UserService() {
        requestHandlers.put("register", RegisterHandler.INSTANCE);
        requestHandlers.put("login", LoginHandler.INSTANCE);
        requestHandlers.put("logout", LogoutHandler.INSTANCE);
    }

    public void handleRequest(Request request) {
        String requestType = request.getParameter(RequestParameter.REQUEST_TYPE);
        RequestHandler handler = requestHandlers.get(requestType);
        if (handler != null) {
            try {
                handler.handle(request);
            } catch (AuthException e) {
                logError(e.getMessage());
            }
        } else {
            logError("Неверная команда. Попробуйте снова");
        }
    }

    private void logError(String message) {
        System.out.println(message);
    }
}
