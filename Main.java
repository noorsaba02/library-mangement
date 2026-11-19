package com.library;

import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.Member;
import com.library.service.LibraryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LibraryService library = new LibraryService();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        System.out.println("=== Library Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.hasNextLine() ? in.nextLine().trim() : "0";
            try {
                switch (choice) {
                    case "1" -> addBook();
                    case "2" -> registerMember();
                    case "3" -> print(library.listBooks());
                    case "4" -> print(library.listMembers());
                    case "5" -> search();
                    case "6" -> issueBook();
                    case "7" -> returnBook();
                    case "8" -> print(library.listLoans());
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("""

                1. Add book
                2. Register member
                3. List books
                4. List members
                5. Search books
                6. Issue book
                7. Return book
                8. List active loans
                0. Exit""");
        System.out.print("Choose: ");
    }

    private static void addBook() {
        String title = ask("Title: ");
        String author = ask("Author: ");
        System.out.println("Added: " + library.addBook(title, author));
    }

    private static void registerMember() {
        System.out.println("Registered: " + library.registerMember(ask("Name: ")));
    }

    private static void search() {
        print(library.search(ask("Keyword: ")));
    }

    private static void issueBook() {
        int bookId = askInt("Book id: ");
        int memberId = askInt("Member id: ");
        Loan loan = library.issueBook(bookId, memberId, LocalDate.now());
        System.out.println("Issued: " + loan);
    }

    private static void returnBook() {
        int bookId = askInt("Book id: ");
        long fine = library.returnBook(bookId, LocalDate.now());
        System.out.println(fine > 0 ? "Returned late. Fine: Rs " + fine : "Returned on time. No fine.");
    }

    private static <T> void print(List<T> items) {
        if (items.isEmpty()) {
            System.out.println("(nothing to show)");
            return;
        }
        items.forEach(System.out::println);
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private static int askInt(String prompt) {
        String s = ask(prompt);
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a number.");
        }
    }

    private static void seedData() {
        library.addBook("Effective Java", "Joshua Bloch");
        library.addBook("Clean Code", "Robert C. Martin");
        library.addBook("Head First Java", "Kathy Sierra");
        library.registerMember("Noor Saba");
        library.registerMember("Demo Member");
    }
}
