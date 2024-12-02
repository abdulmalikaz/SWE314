public class AuthenticationController {
    private UserDatabase userDatabase = new UserDatabase(); // Stores user credentials
    private InputValidator validator = new InputValidator(); // Validates inputs
    private EncryptionManager encryptionManager = new EncryptionManager(); // Encrypts passwords
    private AccessControlManager accessControlManager = new AccessControlManager(); // Logs access

    // Handle user registration
    public boolean handleRegistration(String username, String password) {
        if (!validator.isValidUsername(username)) {
            System.out.println("Invalid username. Must be alphanumeric and 5-15 characters.");
            return false;
        }
        if (!validator.isValidPassword(password)) {
            System.out.println("Invalid password. Must include uppercase, number, and special character.");
            return false;
        }

        // Encrypt the password using Vigenère Cipher
        String passwordHash = encryptionManager.encrypt(password);

        // Add user to the database
        return userDatabase.addUser(username, passwordHash);
    }

    // Handle user login
    public boolean handleLogin(String username, String password) {
        User user = userDatabase.getUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        // Encrypt the input password and compare it with the stored hash
        String encryptedInputPassword = encryptionManager.encrypt(password);
        if (encryptedInputPassword.equals(user.getPasswordHash())) {
            accessControlManager.logAccess(username, "Login");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }
}
