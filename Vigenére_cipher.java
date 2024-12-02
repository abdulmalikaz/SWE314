import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Scanner;
public class Vigenére_cipher {
    Scanner input=new Scanner(System.in);
    //Attributes:
    private String key;
    private int keyLength;
    private int textLength;
    private String encryptedText;
    private int keyIndex;
    private char userInput;
    private int userValue;
    private char keyInput;
    private int keyValue;
    private int CValue;
    private char encryptedChar;
    private String decryptedText;
    private char cipherInput;
    private int cipherValue;
    private int PValue;
    private char decryptedChar;




    public String Vigenere_Cipher(String plainText) {

        System.out.print("Enter Key (in capital): ");
        key = "ENCRYPT";
        //store the given key
        System.out.print("P's Values (A=0,B=1,.....,Z=25): ");


        keyLength = key.length(); // Length of the given key
        textLength = plainText.length();
        encryptedText = "";  // To store the final result
        keyIndex = 0; // To track the current position in the key

        for (int i = 0; i < textLength; i++) {
            // Get current character from the plaintext
            userInput = plainText.charAt(i);

            //if its a letter and not space_bar
            if (Character.isLetter(userInput)) { // Encrypt only if it's a letter
                // Convert plaintext character to uppercase (ASCII A-Z is 65-90)
                userInput = Character.toUpperCase(userInput);
                userValue = (int) userInput-65; // Get ASCII value of the character starting from 0
                System.out.print(userValue+" || ");

                // Get the corresponding character from the key (mod (%) to repeat key)
                keyInput = key.charAt(keyIndex % keyLength);
                keyInput=Character.toUpperCase(keyInput);
                keyValue = (int) keyInput- 65; // Get ASCII value of the key character


                // (userValue) + (keyValue) ==> to get the cipher text
                // %26 is to ensure it's within the values of A-Z
                // +65 is to return it to the ASCII value for final encrypted char
                CValue = (userValue + keyValue) % 26 + 65;
                encryptedChar = (char) CValue; //turn value of askii to letter

                // Add the encrypted character to the result
                encryptedText += encryptedChar;

                // Move to the next character in the key only if it's a letter
                keyIndex++;
            }

        }
        System.out.println();
        System.out.println("Key's stream (A=0,B=1,.....,Z=25): "+KeyStream(key));
        System.out.println("Now we add the P's value with the Key stream to get the cyphertext");
        System.out.print("CypherText:");
        return encryptedText; // Return the final encrypted text

    }
    public String Vigenere_Decipher(String cipherText) {
        System.out.println("Enter Key(in capital): ");
        key=input.nextLine(); //Take the Key input
        System.out.println("Key Stream (A=0,B=1,......,Z=25): "+KeyStream(key));

        System.out.print("C's values (A=0,B=1,......,Z=25): ");
        keyLength = key.length();
        textLength = cipherText.length();
        decryptedText = ""; //to store the final result
        keyIndex = 0; //To track the current position in the key

        for (int i = 0; i < textLength; i++) { //go through the cipher text
            cipherInput = cipherText.charAt(i);

            if (Character.isLetter(cipherInput)) {
                cipherInput = Character.toUpperCase(cipherInput);
                cipherValue = (int) cipherInput-65; // Subtract 65 to start from 0 (A=0, B=1, ..., Z=25
                System.out.print(cipherValue+" || "); //print values of cipher text
                keyInput = key.charAt(keyIndex % keyLength);
                keyValue = (int) keyInput -65;

                // get the original plain text by subtracting key value from cipher text value
                //+26 to avoid negative numbers
                //+65 to get the ascii value
                PValue = (cipherValue - keyValue + 26) % 26 + 65;
                decryptedChar = (char) PValue;

                decryptedText += decryptedChar;
                keyIndex++;
            }
        }
        System.out.println();
        System.out.println("Now we subtract C's value from Key's stream to get the cypher text");
        System.out.print("Plaintext: ");
        return decryptedText;
    }
    String temp="";
    //private methode to print Key Stream
    private String KeyStream(String key) {
        for(int i=0;i<key.length();i++) {
            keyInput=key.charAt(i);
            keyValue=(int)(keyInput)-65;
            temp+=keyValue+" || ";
        }
        return temp;
    }

}
