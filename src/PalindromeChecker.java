import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase to check: \t"); 
        String userInput = scanner.nextLine();

        try {
            String cleanedText = normalizeText(userInput);

            boolean iterativeResult = isPalindromeIterative(cleanedText);
            boolean recursiveResult = isPalindromeRecursive(cleanedText);
            boolean arrayReversalResult = isPalindromeArrayReversal(cleanedText);

            System.out.println("\nInput: \"" + userInput + "\"");
            System.out.println("Iterative: " + describeResult(iterativeResult) +
                    " | Recursive: " + describeResult(recursiveResult) +
                    " | Array Reversal: " + describeResult(arrayReversalResult));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static String normalizeText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        return text.replaceAll("\\s+", "").toLowerCase();
    }

    public static boolean isPalindromeIterative(String text) {
        int leftIndex = 0;
        int rightIndex = text.length() - 1;

        while (leftIndex < rightIndex) {
            if (text.charAt(leftIndex) != text.charAt(rightIndex)) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        return isPalindromeRecursiveHelper(text, 0, text.length() - 1);
    }

    private static boolean isPalindromeRecursiveHelper(String text, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return true; // base case
        }
        if (text.charAt(leftIndex) != text.charAt(rightIndex)) {
            return false;
        }
        return isPalindromeRecursiveHelper(text, leftIndex + 1, rightIndex - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] originalChars = text.toCharArray();
        char[] reversedChars = new char[originalChars.length];

        for (int i = 0; i < originalChars.length; i++) {
            reversedChars[i] = originalChars[originalChars.length - 1 - i];
        }

        String reversedText = new String(reversedChars);
        return text.equals(reversedText);
    }

    private static String describeResult(boolean isPalindrome) {
        return isPalindrome ? "Palindrome" : "Not Palindrome";
    }
}
