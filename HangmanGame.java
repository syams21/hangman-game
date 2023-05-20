import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner keyboard = new Scanner(System.in);

        System.out.print("1 or 2 Players? ");
        String player = keyboard.nextLine();
        String word;

        if (player.equals("1")) {
            System.out.println("\nChoose difficulty level:");
            System.out.println("1. Easy");
            System.out.println("2. Normal");
            System.out.println("3. Hard");
            System.out.println("4. Expert");
            System.out.print("\nLevel: ");
    
            int difficultyLevel = Integer.parseInt(keyboard.nextLine());
            int wordLength;

            if (difficultyLevel == 1) {
                wordLength = 3 + (int)(Math.random() * 2);
            }
            else if (difficultyLevel == 2) {
                wordLength = 5 + (int)(Math.random() * 2);
            }
            else if (difficultyLevel == 3) {
                wordLength = 7 + (int)(Math.random() * 2);
            }
            else {
                wordLength = 9 + (int)(Math.random() * 6);
            }

            Scanner scanner = new Scanner(new File("words.txt"));
    
            List<String> words = new ArrayList<>();
    
            while (scanner.hasNext()) {
                String nextWord = scanner.nextLine();
                if (nextWord.length() == wordLength) {
                    words.add(nextWord);
                }
            }
    
            Random rand = new Random();
    
            word = words.get(rand.nextInt(words.size()));
        }
        else {
            System.out.println("\nPlayer 1, please enter your word: ");
            word = keyboard.nextLine();

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good Luck!");
        }
        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();
        
        int wrongCount = 0;

        while (true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("\nYou Lose!");
                System.out.println("\nThe word was: " + word);
                System.out.println();
                break;
            }

            printWordState(word, playerGuesses);

            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }
        
            if (printWordState(word, playerGuesses)) {
                System.out.println("\nYou win!\n");
                break;
            }

            System.out.println("\nPlease enter your guess for the word: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("\nYou win!\n");
                break;
            }
            else {
                System.out.println("\nUpss... Try again!");
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
