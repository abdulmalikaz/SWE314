public class InputValidator {
    public boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9]{5,15}$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }
}
