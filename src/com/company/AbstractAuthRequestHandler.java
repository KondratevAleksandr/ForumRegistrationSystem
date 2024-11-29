package com.company;

import java.util.regex.Pattern;

public abstract class AbstractAuthRequestHandler implements RequestHandler {
    private static final Pattern VALID_CREDENTIALS_PATTERN = Pattern.compile("^[\\x21-\\x7E]+$");
    private static final Integer MAX_CREDENTIAL_LENGTH = 30;

    protected final UserRepository userRepository = UserRepository.INSTANCE;

    protected boolean isValid(String credentials) {
        return credentials != null &&
                !credentials.isEmpty() &&
                credentials.length() <= MAX_CREDENTIAL_LENGTH &&
                VALID_CREDENTIALS_PATTERN.matcher(credentials).matches();
    }
}