package com.company;

import java.util.regex.Pattern;

public abstract class AbstractAuthRequestHandler implements RequestHandler {
    protected boolean isValid(String credentials) {
        String regex = "^[\\x21-\\x7E]+$";
        return credentials == null ||
                credentials.length() <= 0 || credentials.length() > 30 ||
                !Pattern.matches(regex, credentials);
    }
}
