package com.company;

public interface RequestHandler {
    String handle(Request request) throws AuthException;
}
