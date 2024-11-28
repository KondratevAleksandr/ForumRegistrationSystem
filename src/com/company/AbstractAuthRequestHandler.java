package com.company;

import java.util.regex.Pattern;

public abstract class AbstractAuthRequestHandler implements RequestHandler {
    private static final Pattern VALID_CREDENTIALS_PATTERN = Pattern.compile("^[\\x21-\\x7E]+$");
    protected UserRepository userRepository;

    protected boolean isValid(String credentials) {
        return credentials != null &&
                credentials.length() != 0 && credentials.length() <= 30 &&
                VALID_CREDENTIALS_PATTERN.matcher(credentials).matches();
    }
}
