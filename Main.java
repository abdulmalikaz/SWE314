import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        InputValidator validator = new InputValidator(); // Instance of InputValidator
        Scanner scanner = new Scanner(System.in);

        final int MAX_TRIES = 3; // Maximum allowed login attempts
        HashMap<String, Integer> loginAttempts = new HashMap<>(); // Track login attempts per username

        while (true) {
            try {
                System.out.println("Choose an option: 1) Sign Up  2) Login  3) Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter username (alphanumeric and 5-15 characters): ");
                        String signUpUsername = scanner.nextLine();
                        if (validator.hasSqlInjectionRisk(signUpUsername)) {
                            System.out.println("Invalid username: Contains SQL injection risk. Please try again.");
                            break;
                        }
                        if (!validator.isValidUsername(signUpUsername)) {
                            System.out.println("Invalid username. Must be alphanumeric and 5-15 characters.");
                            break;
                        }

                        System.out.print("Enter password at least 5 characters (Must include uppercase, number, and special character): ");
                        String signUpPassword = scanner.nextLine();
                        if (validator.hasSqlInjectionRisk(signUpPassword)) {
                            System.out.println("Invalid password: Contains SQL injection risk. Please try again.");
                            break;
                        }
                        if (!validator.isValidPassword(signUpPassword)) {
                            System.out.println("Invalid password. Must include uppercase, number, and special character.");
                            break;
                        }

                        loginPage.displaySignUp(signUpUsername, signUpPassword);
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        if (validator.hasSqlInjectionRisk(loginUsername)) {
                            System.out.println("Invalid username: Contains SQL injection risk. Please try again.");
                            break;
                        }

                        // Initialize login attempts for the username if not already tracked
                        loginAttempts.putIfAbsent(loginUsername, 0);

                        // Check if the user is locked out
                        if (loginAttempts.get(loginUsername) >= MAX_TRIES) {
                            System.out.println("Your account is locked due to too many failed login attempts. Please contact customer service.");
                            break;
                        }

                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
                        if (validator.hasSqlInjectionRisk(loginPassword)) {
                            System.out.println("Invalid password: Contains SQL injection risk. Please try again.");
                            break;
                        }

                        // Call displayLogin and check the result
                        if (loginPage.displayLogin(loginUsername, loginPassword)) {

                            loginAttempts.put(loginUsername, 0); // Reset the counter on successful login
                        } else {

                            loginAttempts.put(loginUsername, loginAttempts.get(loginUsername) + 1); // Increment failed attempts

                            // Lock account if maximum attempts are reached
                            if (loginAttempts.get(loginUsername) >= MAX_TRIES) {
                                System.out.println("You exceeded the maximum number of attempts. Your account is locked. Please contact customer service.");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
