package com.company;

public class RegisterHandler extends AbstractAuthRequestHandler {
    @Override
    public void handle(Request request, UserRepository userRepository) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        if (isValid(login) || isValid(password)) {
            System.out.println("fail: incorrect username or password");
            return;
        }

        if (userRepository.userExist(login)) {
            System.out.println("fail: user already exists");
            return;
        }

        userRepository.addUser(new User(login, password));
        System.out.println("success: new user added");
    }
}
