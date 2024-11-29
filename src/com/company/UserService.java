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
                String response = handler.handle(request);
                System.out.println(response);
            } catch (AuthException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Неверная команда. Попробуйте снова");
        }
    }
}
