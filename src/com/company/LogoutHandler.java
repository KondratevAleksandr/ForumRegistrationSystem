package com.company;

import java.util.Optional;

public class LogoutHandler extends AbstractAuthRequestHandler {
    public static final LogoutHandler INSTANCE = new LogoutHandler();

    private LogoutHandler() {
    }

    @Override
    public void handle(Request request) throws AuthException {
        String login = request.getParameter(RequestParameter.LOGIN);

        if (isValid(login)) {
            throw new AuthException("fail: incorrect username");
        }

        Optional<User> optionalUser = userRepository.findUser(login);
        if (optionalUser.isEmpty()) {
            throw new AuthException("fail: no such user");
        }

        User user = optionalUser.get();
        if (!user.onlineStatus()) {
            throw new AuthException("fail: already logged out");
        } else {
            user.setOnLine(false);
            System.out.println("success: user logged out");
        }
    }
}
