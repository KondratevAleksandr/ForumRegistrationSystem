package com.company;

public class LoginHandler extends AbstractAuthRequestHandler {
    @Override
    public void handle(Request request, UserRepository userRepository) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        if (isValid(login) || isValid(password)) {
            System.out.println("fail: incorrect username or password");
            return;
        }

        User user = userRepository.findUser(login);
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
}
