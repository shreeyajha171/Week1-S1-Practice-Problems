import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {

    static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

    private static final String[] VALID_MOVES = {"Rock", "Paper", "Scissors"};
    private static final int TOTAL_ROUNDS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] playerMoveHistory = new String[TOTAL_ROUNDS];
        String[] computerMoveHistory = new String[TOTAL_ROUNDS];
        String[] resultHistory = new String[TOTAL_ROUNDS];

        int winCount = 0;
        int lossCount = 0;
        int drawCount = 0;

        System.out.println("=== Rock-Paper-Scissors: College Coding Arcade ===");

        for (int round = 0; round < TOTAL_ROUNDS; round++) {
            String computerMove = generateComputerMove();
            String playerMove;

            try {
                System.out.print("Round " + (round + 1) + " - Enter your move (Rock/Paper/Scissors): ");
                playerMove = readValidatedMove(scanner);

                String result = determineRoundResult(playerMove, computerMove);

                playerMoveHistory[round] = playerMove;
                computerMoveHistory[round] = computerMove;
                resultHistory[round] = result;

                if (result.equals("Player Wins")) {
                    winCount++;
                } else if (result.equals("Computer Wins")) {
                    lossCount++;
                } else {
                    drawCount++;
                }

                System.out.println("  -> " + result);

            } catch (InvalidMoveException e) {
                System.out.println("  -> Invalid move skipped: " + e.getMessage());
                playerMoveHistory[round] = "Invalid";
                computerMoveHistory[round] = computerMove;
                resultHistory[round] = "Skipped";
            } catch (RuntimeException e) {
                System.out.println("  -> Unexpected input error: " + e.getMessage());
            }
        }

        printScoreboard(playerMoveHistory, computerMoveHistory, resultHistory);
        printFinalSummary(winCount, lossCount, drawCount, TOTAL_ROUNDS);

        scanner.close();
    }

    private static String generateComputerMove() {
        Random random = new Random();
        int index = random.nextInt(VALID_MOVES.length);
        return VALID_MOVES[index];
    }

    private static String readValidatedMove(Scanner scanner) throws InvalidMoveException {
        String rawInput = scanner.nextLine();
        String normalizedMove = rawInput.trim();

        for (String validMove : VALID_MOVES) {
            if (validMove.equalsIgnoreCase(normalizedMove)) {
                return validMove; // returns properly-cased move
            }
        }
        throw new InvalidMoveException("\"" + rawInput + "\" is not Rock, Paper, or Scissors");
    }

    public static String determineRoundResult(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        boolean playerWins =
                (playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"));

        return playerWins ? "Player Wins" : "Computer Wins";
    }

    private static void printScoreboard(String[] playerMoves, String[] computerMoves, String[] results) {
        System.out.println("\nRound | Player Move | Computer Move | Result");
        for (int i = 0; i < playerMoves.length; i++) {
            System.out.printf("%-5d | %-11s | %-13s | %s%n",
                    (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
    }

    private static void printFinalSummary(int wins, int losses, int draws, int totalRounds) {
        double winPercentage = (wins * 100.0) / totalRounds;
        System.out.println("\nFinal Summary (after " + totalRounds + " rounds)");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);
    }
}
