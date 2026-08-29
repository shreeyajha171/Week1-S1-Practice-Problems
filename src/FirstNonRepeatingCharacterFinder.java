import java.util.Scanner;

public class FirstNonRepeatingCharacterFinder {

    static class NoNonRepeatingCharacterException extends Exception {
        public NoNonRepeatingCharacterException(String message) {
            super(message);
        }
    }

    private static final int ASCII_TABLE_SIZE = 128;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String userInput = scanner.nextLine();

        try {
            char firstUniqueChar = findFirstNonRepeatingChar(userInput);
            System.out.println("First Non-Repeating Character: '" + firstUniqueChar + "'");
        } catch (NoNonRepeatingCharacterException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static char findFirstNonRepeatingChar(String text) throws NoNonRepeatingCharacterException {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        int[] characterFrequency = new int[ASCII_TABLE_SIZE];

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int asciiCode = (int) currentChar;
            if (asciiCode < ASCII_TABLE_SIZE) {
                characterFrequency[asciiCode]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int asciiCode = (int) currentChar;
            if (asciiCode < ASCII_TABLE_SIZE && characterFrequency[asciiCode] == 1) {
                return currentChar;
            }
        }

        throw new NoNonRepeatingCharacterException("No Non-Repeating Character Found");
    }
}
