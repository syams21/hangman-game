import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.text.html.parser.Element;
import javax.swing.undo.StateEdit;

public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("E:/Document/0MevMe/java/project/hangmanGame/words.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size()));

        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();
        
        int wrongCount = 0;

        while (true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You Lose!");
                break;
            }

            printWordState(word, playerGuesses);

            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }
        
            if (printWordState(word, playerGuesses)) {
                System.out.println("You win!");
                break;
            }

            System.out.println("\nPlease enter your guess for the word: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Uppss.. Try again!");
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" _____________");
            System.out.println(" |	     |");

            if (wrongCount >= 1) {
                System.out.println(" |           O");
            }

            if (wrongCount >= 2) {
                System.out.print(" |          \\ ");

                if (wrongCount >= 3) {
                    System.out.println("/");
                }
                else {
                    System.out.println("");
                }
            }

            if (wrongCount >= 4) {
                System.out.println(" |           |");
            }

            if (wrongCount >= 5) {
                System.out.print(" |          / ");

                if (wrongCount >= 6) {
                    System.out.println("\\");
                }
                else {
                    System.out.println("");
                }

            }
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("\nPlease enter a letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses){
        int correctCount = 0;

        System.out.println();

        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i) + " ");
                correctCount++;
            }
            else {
                System.out.print("_ ");
            }
        }
        System.out.println("");

        return (word.length() == correctCount);
    }
}
