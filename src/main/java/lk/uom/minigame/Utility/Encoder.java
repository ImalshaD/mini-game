package lk.uom.minigame.Utility;

public class Encoder {

    public static String encoded(String input,int shift){
        StringBuilder encodedString = new StringBuilder();

        for (char ch : input.toCharArray()) {
            char A = 'A';
            // Add the shift value to the ASCII value of the character
            char encodedChar = (char) ((ch + shift-A)%27+A);
            encodedString.append(encodedChar);
        }

        return encodedString.toString();
    }
}
