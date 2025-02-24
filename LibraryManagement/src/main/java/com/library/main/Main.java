package com.library.main;

import com.library.ui.ConsoleUI;
import com.library.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        ConsoleUI consoleUI = new ConsoleUI(libraryService);
        consoleUI.start();
    }
}
