import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {
        int rounds = 0;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");

        while (true) {
            rounds++;
            int score = playRound();
            totalScore += score;

            System.out.println("Round " + rounds + " complete! Your score: " + score);
            System.out.println("Total score after " + rounds + " rounds: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over! You played " + rounds + " rounds with a total score of " + totalScore + ".");
    }

    private static int playRound() {
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int score = 0;

        System.out.println("I have generated a number between 1 and 100. Try to guess it!");

        while (attempts < MAX_ATTEMPTS) {
            attempts++;
            System.out.print("Attempt " + attempts + ": Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == numberToGuess) {
                score = (MAX_ATTEMPTS - attempts + 1) * 10; // Points based on attempts remaining
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts. Your score for this round is: " + score);
                return score;
            } else if (userGuess < numberToGuess) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }

        System.out.println("Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The number was: " + numberToGuess);
        return score; // Score is 0 if all attempts are used
    }
}
