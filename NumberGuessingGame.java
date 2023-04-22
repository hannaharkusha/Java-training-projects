import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int num = random.nextInt(max - min + 1) + min;

        System.out.println("I'm thinking of a number between " + min + " and " + max + ".");

        int guess;
        boolean guessed = false;
        int tries = 0;

        while (!guessed) {
            System.out.print("Enter your guess: ");
            guess = input.nextInt();
            tries++;

            if (guess == num) {
                guessed = true;
            } else if (guess < num) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        System.out.println("Congrats! You guessed the number in " + tries + " guesses.");
    }
}
