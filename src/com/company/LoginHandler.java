package com.company;

import java.util.Optional;

public class LoginHandler extends AbstractAuthRequestHandler {
    public static final LoginHandler INSTANCE = new LoginHandler();

    private LoginHandler() {
    }

    @Override
    public void handle(Request request) throws AuthException {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        if (isValid(login) || isValid(password)) {
            throw new AuthException("fail: incorrect username or password");
        }

        Optional<User> optionalUser = userRepository.findUser(login);
        if (optionalUser.isEmpty()) {
            throw new AuthException("fail: no such user");
        }

        User user = optionalUser.get();
        if (!user.getPasswordHash().equals(PasswordHashing.hashPassword(password))) {
            throw new AuthException("fail: incorrect password");
        } else if (user.onlineStatus()) {
            throw new AuthException("fail: already logged in");
        } else {
            user.setOnLine(true);
            System.out.println("success: user logged in");
        }
    }
}
