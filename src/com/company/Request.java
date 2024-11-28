package com.company;

import java.util.Map;

public class Request {
    private Map<RequestParameter, String> parameters;

    public Request(Map<RequestParameter, String> parameters) {
        this.parameters = parameters;
    }

    public String getParameter(RequestParameter key) {
        if (!parameters.containsKey(key)) {
            throw new IllegalArgumentException("Параметр не найден: " + key);
        }
        return parameters.get(key);
    }
}
