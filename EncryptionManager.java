public class EncryptionManager {
    private Vigenére_cipher cipher = new Vigenére_cipher(); // Use your provided class

    // Encrypt the password using your Vigenère Cipher implementation
    public String encrypt(String plainTextPassword) {
        return cipher.Vigenere_Cipher(plainTextPassword); // Call your encrypt method
    }


    public String decrypt(String encryptedPassword) {
        return cipher.Vigenere_Decipher(encryptedPassword); // Call your decrypt method
    }
}
