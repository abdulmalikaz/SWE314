public class LoginPage {
    private AuthenticationController authController = new AuthenticationController();

    public void displayLogin(String username, String password) {
        System.out.println("Attempting to log in...");
        boolean success = authController.handleLogin(username, password);
        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void displaySignUp(String username, String password) {
        System.out.println("Attempting to sign up...");
        boolean success = authController.handleRegistration(username, password);
        if (success) {
            System.out.println("Sign-up successful!");
        } else {
            System.out.println("Sign-up failed. Username may already exist.");
        }
    }
}
