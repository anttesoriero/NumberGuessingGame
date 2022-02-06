/**
 * A guessing game where the user has 3 tries to guess the correct integer. 
 * 
 * By Anthony Tesoriero - 6 Feb 2022
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private int leftParam;
    private int rightParam;
    private int count = 0;
    private int rand;
    private boolean guessing = true;

    public NumberGuessingGame() {
        leftParam = 1;
        rightParam = 100;
        setRand();
    }

    public NumberGuessingGame(int leftIn, int rightIn) {
        leftParam = Math.min(leftIn, rightIn);
        rightParam = Math.max(leftIn, rightIn);
        setRand();
    }

    public void setRand() {
        Random r = new Random();
        rand = r.nextInt(leftParam, rightParam);
    }

    
    /** 
     * @return int randomly generated number
     */
    public int getRand() {
        return rand;
    }

    
    /** 
     * @return int count of how many tries the user has used
     */
    public int getCount() {
        return count;
    }

    
    /** 
     * @return boolean is the user is still able to guess
     */
    public boolean getGuessing() {
        return guessing;
    }

    /**
     * Returns a String informing the outcome of their guess. 
     * Checks if their guess is valid within the params, as well as showing if it's too high or low. 
     * Also counts how many guesses the user has used. 
     * @param user  the users guessed integer
     * @param rand  the randomy generated integer
     * @return      the string of outcome
     */
    public String guess(int user, int rand) {
        if(user < leftParam || user > rightParam)
            return "Invalid guess. Please try again.";

        count++;
        if(count == 3)
            guessing = false;
        
        if(user == rand)
            return "Correct! You guessed " + rand + " in " + count + " guesses!";
        else if(user < rand)
            return "Too low! Guess again.";
        else
            return "Too high! Guess again.";
    }

    // Main
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // --- Params ---

        int left, right;

        // Left param
        while(true) {
            try {
                System.out.print("Choose a left param intager: ");
                left = sc.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.err.println("Wrong input! Integers only please.");
            }
        }

        // Right param
        while(true) {
            try {
                System.out.print("Choose a right param intager: ");
                right = sc.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.err.println("Wrong input! Integers only please.");
            }
        }

        NumberGuessingGame game = new NumberGuessingGame(Math.min(left, right), Math.max(left, right));

        // --- Game ---

        int user = 0;
        int rand = game.getRand();

        System.out.println("");
        System.out.println("Welcome to number guessing! Guess a number in 3 tries");

        while(user != rand && game.getGuessing()) {
            try {
                System.out.print("Please guess a number: ");
                user = sc.nextInt();
                System.out.println(game.guess(user, rand) + "\n");
            }
            catch(InputMismatchException e) {
                System.err.println("Wrong input! Integers only please.");
                sc.nextLine();
            }
        }
        
        if(game.getCount() == 3)
            System.out.println("GAME OVER" + "\n" + "Winning number: " + rand);

        sc.close();
    }
}
