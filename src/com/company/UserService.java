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

    public void handleRequest(Request request) {
        String requestType = request.getParameter(RequestParameter.REQUEST_TYPE);
        RequestHandler handler = requestHandlers.get(requestType);
        if (handler != null) {
            handler.handle(request, userRepository);
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
