package com.evoting;

import java.util.Scanner;

import com.evoting.model.Admin;
import com.evoting.model.Candidate;
import com.evoting.service.AdminService;
import com.evoting.service.AdminServiceImpl;
import com.evoting.exception.InvalidCredentialsException;
import com.evoting.exception.DuplicateCandidateException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    
        Admin admin = new Admin(1, "System Admin", "admin", "admin123");

        
        AdminService adminService = new AdminServiceImpl(admin);

        boolean running = true;

        while (running) {
            System.out.println("\n================================");
            System.out.println("   BLOCKCHAIN E-VOTING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int mainChoice = readInt(sc);

            switch (mainChoice) {
                case 1:
                    handleAdminLogin(sc, adminService, admin);
                    break;
                case 2:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    // Handles admin login and, if successful, shows the admin menu
    private static void handleAdminLogin(Scanner sc, AdminService adminService, Admin admin) {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // EXCEPTION HANDLING: try-catch around login so the app never crashes
        try {
            boolean success = adminService.login(username, password);
            if (success) {
                System.out.println("\nLogin Successful!");
                System.out.println("Welcome " + admin.getName());
                admin.displayRole(); // POLYMORPHISM: calls Admin's overridden method
                showAdminMenu(sc, adminService);
            }
        } catch (InvalidCredentialsException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    // Displays the admin menu and handles candidate management options
    private static void showAdminMenu(Scanner sc, AdminService adminService) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n---------- ADMIN MENU ----------");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. Remove Candidate");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    addCandidateFlow(sc, adminService);
                    break;
                case 2:
                    adminService.viewCandidates();
                    break;
                case 3:
                    System.out.print("Enter Candidate ID to remove: ");
                    int removeId = readInt(sc);
                    adminService.removeCandidate(removeId);
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Reads candidate details from the user and attempts to add the candidate
    private static void addCandidateFlow(Scanner sc, AdminService adminService) {
        System.out.print("\nEnter Candidate ID: ");
        int id = readInt(sc);
        System.out.print("Enter Candidate Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Party Name: ");
        String party = sc.nextLine();

        Candidate candidate = new Candidate(id, name, party);

        // EXCEPTION HANDLING: try-catch around addCandidate so duplicate IDs
        // don't crash the program
        try {
            adminService.addCandidate(candidate);
            System.out.println("Candidate added successfully!");
        } catch (DuplicateCandidateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Utility method to safely read an integer and consume the trailing newline
    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return value;
    }
}
