import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int roundsPlayed = 0;
        int totalScore = 0;

        System.out.println("********************************************");
        System.out.println("    Welcome to the Number Guessing Game!    ");
        System.out.println("********************************************");

        while (true) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            System.out.println("\nI have selected a number between " + minRange + " and " + maxRange + ". Try to guess it!");
            int roundNumber = roundsPlayed + 1;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                int userGuess;

                try {
                    userGuess = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    continue;
                }

                scanner.nextLine();

                if (userGuess < minRange || userGuess > maxRange) {
                    System.out.println("Your guess is out of the valid range. Please guess again.");
                    continue;
                }

                if (userGuess == targetNumber) {
                    System.out.println("\n********************************************************");
                    System.out.println("  Congratulations! You guessed the correct number: " + targetNumber );
                    System.out.println("********************************************************");
                    totalScore += (maxAttempts - attempts);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is low. Try again.");
                } else {
                    System.out.println("Your guess is high. Try again.");
                }

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("You've run out of attempts. The correct number was: " + targetNumber);
            }

            roundsPlayed++;

            DecimalFormat df = new DecimalFormat("0.00");
            double averageScore = roundsPlayed > 0 ? (double) totalScore / roundsPlayed : 0.0;

            System.out.println("\n--------------------------------------");
            System.out.println("  Round " + roundNumber + " Score: " + (maxAttempts - attempts));
            System.out.println("  Total Score: " + totalScore);
            System.out.println("  Average Score: " + df.format(averageScore));
            System.out.println("--------------------------------------");

            System.out.print("\n Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        double averageScore = roundsPlayed > 0 ? (double) totalScore / roundsPlayed : 0.0;

        System.out.println("\n******************************************");
        System.out.println("  Total Final Score: " + totalScore );
        System.out.println("  Total Average Score: " + df.format(averageScore) );
        System.out.println("  Game Over. Thank you for playing!    ");
        System.out.println("******************************************");

        scanner.close();
    }
}
