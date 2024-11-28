package com.company;

public interface RequestHandler {
    void handle(Request request, UserRepository userRepository);
}
