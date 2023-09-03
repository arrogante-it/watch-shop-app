package com.watch.shop.app.view;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
