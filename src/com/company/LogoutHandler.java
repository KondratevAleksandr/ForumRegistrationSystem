package com.company;

public class LogoutHandler extends AbstractAuthRequestHandler {
    @Override
    public void handle(Request request, UserRepository userRepository) {
        String login = request.getParameter(RequestParameter.LOGIN);

        if (isValid(login)) {
            System.out.println("fail: incorrect username");
            return;
        }

        User user = userRepository.findUser(login);
        if (user == null) {
            System.out.println("fail: no such user");
        } else if (!user.isOnLine()) {
            System.out.println("fail: already logged out");
        } else {
            user.setOnLine(false);
            System.out.println("success: user logged out");
        }
    }
}
