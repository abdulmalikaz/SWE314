public class LoginPage {
    private AuthenticationController authController = new AuthenticationController();

    public boolean displayLogin(String username, String password) {
        System.out.println("Attempting to log in...");
        boolean success = authController.handleLogin(username, password);
        if (success) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed. Please try again.");
            return false;
        }
    }

    public void displaySignUp(String username, String password) {
        System.out.println("Attempting to sign up...");
        boolean success = authController.handleRegistration(username, password);
        if (success) {
            System.out.println("Sign-up successful!");
        } else {
            System.out.println("Sign-up failed.");
        }
    }
}
