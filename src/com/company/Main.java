package com.company;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        int operationCount = getOperationCount(scanner);

        for (int i = 0; i < operationCount; i++) {
            Map<RequestParameter, String> requestMap = getRequest(scanner);
            Request request = new Request(requestMap);
            userService.handleRequest(request);
        }
    }

    public static int getOperationCount(Scanner scanner) {
        int operationCount = 0;
        try {
            System.out.println("Введите количество операций от 1 до 100: ");
            operationCount = scanner.nextInt();
            scanner.nextLine();
            if (operationCount < 1 || operationCount > 100) {
                System.out.println("Введено не корректное количество операций.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Некоректный ввод.");
            scanner.next();
        }
        return operationCount;
    }

    public static Map<RequestParameter, String> getRequest(Scanner scanner) {
        System.out.println("Введите запрос: ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Неверный ввод. Попробуйте снова.");
            return getRequest(scanner);
        }

        String[] parts = input.split(" ");

        if (parts.length < 2 || parts.length > 3) {
            System.out.println("Ошибка: недостаточно параметров.Попробуйте снова.");
            return getRequest(scanner);
        }
        Map<RequestParameter, String> request;

        if (parts.length == 3) {
            request = Map.of(
                    RequestParameter.REQUEST_TYPE, parts[0],
                    RequestParameter.LOGIN, parts[1],
                    RequestParameter.PASSWORD, parts[2]);
        } else {
            request = Map.of(
                    RequestParameter.REQUEST_TYPE, parts[0],
                    RequestParameter.LOGIN, parts[1]);
        }
        return request;
    }
}
