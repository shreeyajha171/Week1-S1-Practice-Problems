import java.util.Scanner;

public class CustomerNameReverser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        try {
            String reversedName = reverseCustomerName(customerName);

            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversedName);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static String reverseCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }

        char[] nameCharacters = customerName.toCharArray();
        char[] reversedCharacters = new char[nameCharacters.length];

        for (int i = 0; i < nameCharacters.length; i++) {
            reversedCharacters[i] = nameCharacters[nameCharacters.length - 1 - i];
        }

        return new String(reversedCharacters);
    }
}
