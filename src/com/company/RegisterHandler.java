package com.company;

public class RegisterHandler extends AbstractAuthRequestHandler {
    public static final RegisterHandler INSTANCE = new RegisterHandler();

    private RegisterHandler() {
    }

    @Override
    public String handle(Request request) throws AuthException {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        if (!isValid(login) || !isValid(password)) {
            throw new AuthException("fail: incorrect username or password");
        }

        if (userRepository.isUserExist(login)) {
            throw new AuthException("fail: user already exists");
        }

        userRepository.addUser(new User(login, password));
        return "success: new user added";
    }
}
