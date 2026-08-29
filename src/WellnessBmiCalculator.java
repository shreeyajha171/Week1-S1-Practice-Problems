import java.util.Random;

public class WellnessBmiCalculator {

    static class InvalidBiometricDataException extends Exception {
        public InvalidBiometricDataException(String message) {
            super(message);
        }
    }

    private static final int TEAM_SIZE = 10;

    public static void main(String[] args) {
        double[] employeeHeightsInMeters = new double[TEAM_SIZE];
        double[] employeeWeightsInKg = new double[TEAM_SIZE];

        generateSampleTeamData(employeeHeightsInMeters, employeeWeightsInKg);
        printWellnessReport(employeeHeightsInMeters, employeeWeightsInKg);
    }

    private static void generateSampleTeamData(double[] heights, double[] weights) {
        Random random = new Random();
        for (int i = 0; i < heights.length; i++) {
            heights[i] = 1.50 + (random.nextDouble() * 0.40); // 1.50m - 1.90m
            weights[i] = 50 + (random.nextDouble() * 50);     // 50kg - 100kg
        }
    }

    public static double calculateBmi(double heightInMeters, double weightInKg)
            throws InvalidBiometricDataException {
        if (heightInMeters <= 0) {
            throw new InvalidBiometricDataException("Height must be greater than zero");
        }
        return weightInKg / (heightInMeters * heightInMeters);
    }

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("=== Corporate Wellness Program Report ===");
        System.out.println("Person   | Height (m) | Weight (kg) | BMI    | Status");

        for (int i = 0; i < heights.length; i++) {
            try {
                double bmi = calculateBmi(heights[i], weights[i]);
                String status = getBmiStatus(bmi);

                System.out.printf("Person %-2d| %-10.2f | %-11.2f | %-6.2f | %s%n",
                        (i + 1), heights[i], weights[i], bmi, status);

            } catch (InvalidBiometricDataException e) {
                System.out.printf("Person %-2d| Error: %s%n", (i + 1), e.getMessage());
            }
        }
    }
}
