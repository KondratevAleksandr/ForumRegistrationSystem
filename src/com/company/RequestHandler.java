package com.company;

public interface RequestHandler {
    void handle(Request request) throws AuthException;
}
