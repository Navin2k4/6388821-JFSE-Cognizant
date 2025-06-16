import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        int number = rand.nextInt(100) + 1;
        int guess = 0;

        System.out.println("Guess a number between 1 and 100:");

        while (guess != number) {
            guess = scanner.nextInt();
            if (guess < number)
                System.out.println("Too low. Try again:");
            else if (guess > number)
                System.out.println("Too high. Try again:");
            else
                System.out.println("Correct! The number was " + number);
        }
    }
}
