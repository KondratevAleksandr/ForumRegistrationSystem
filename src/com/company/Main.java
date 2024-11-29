package com.company;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operationCount = getOperationCount(scanner);

        for (int i = 0; i < operationCount; i++) {
            Map<RequestParameter, String> request = getRequest(scanner);
            Request subRequest = new Request(request);
            UserService.INSTANCE.handleRequest(subRequest);
        }
    }

    public static int getOperationCount(Scanner scanner) {
        int operationCount = 0;
        while (true) {
            try {
                System.out.println("Введите количество операций от 1 до 100: ");
                operationCount = scanner.nextInt();
                scanner.nextLine();
                if (operationCount < 1 || operationCount > 100) {
                    System.out.println("Введено не корректное количество операций.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некоректный ввод.");
                scanner.next();
            }
        }
        return operationCount;
    }

    public static Map<RequestParameter, String> getRequest(Scanner scanner) {
        System.out.println("Введите запрос: ");
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Неверный ввод. Попробуйте снова.");
        }

        String[] parts = input.split(" ");

        switch (parts.length) {
            case 2 -> {
                return Map.of(
                        RequestParameter.REQUEST_TYPE, parts[0],
                        RequestParameter.LOGIN, parts[1]);
            }
            case 3 -> {
                return Map.of(
                        RequestParameter.REQUEST_TYPE, parts[0],
                        RequestParameter.LOGIN, parts[1],
                        RequestParameter.PASSWORD, parts[2]);
            }
            default -> throw new IllegalArgumentException("Ошибка: недостаточно параметров.");
        }
    }
}
