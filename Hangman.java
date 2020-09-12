package schoolwork;
/*Hannah Hepburn
 * Ms Navabi
 * ICS 3U
 * Friday May 17, 2018
 */
//importing libraries
import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Method variables
        int index1T; //1st index of topic (2nd is always 0)
        int index2W; //2nd index of word

        String userPlay = "Y"; //State of whether user wants to play
        char userGuessInput;

        String topic; //Topic chosen for the game
        String word; //Word chosen for the game
        String dashedWord; //Word replaced with dashes and spaces Ex. hello world -> '----- -----'
        String wordBack; //Word returned from dashes partially Ex. '--ll- ---l-' (user guesses l)

        

        String[][] hangmanWords = new String[10][11];

        hangmanWords = new String[][]{
            {"Movie", "MOONRISE KINGDOM", "THE INCREDIBLES", "FRANKENSTINE", "STAR WARS", "THE CONJURING", "TOY STORY", "BACK TO THE FUTURE", "SPIDERVERSE", "CALL ME BY YOUR NAME", "ENDGAME"},
            {"Video Games", "MARIO KART", "MINECRAFT", "GRAND THEFT AUTO", "POKEMON", "FALLOUT", "LEFT FOUR DEAD", "LEGEND OF ZELDA", "PORTAL", "STARDEW VALLEY", "RAYMAN"},
            {"School Subject", "BIOLOGY", "CHEMISTRY", "COMPUTER SCIENCE", "CALCULUS", "ENGLISH", "PHYSICS", "DATA MANAGEMENT", "GEOGRAPHY", "INSTRUMENTAL BAND", "ROBOTICS"},
            {"Vegtables", "ASPARAGUS", "PEA", "CARROT", "BROCCOLI", "BEAN", "LETTUCE", "ONION", "PUMPKIN", "POTATO", "YAM"},
            {"Drink", "LEMONADE", "WATER", "COCA COLA", "SPRITE", "NESTEA", "CHOCOLATE MILK", "MILKSHAKE", "MOUNTAIN DEW", "JUICE", "GREEN TEA"},
            {"Food", "SALAD", "SPAGHETTI", "TOAST", "CANDY", "CHOCOLATE", "PIZZA", "DUMPLINGS", "HUMMUS", "ICE CREAM", "GARLIC BREAD"},
            {"Country", "CANADA", "UNITED KINGDOM", "MADAGASGAR", "ARGENTINA", "AUSTRALIA", "MOZAMBIQUE", "ECUADOR", "DENMARK", "SCANDANAVIA", "KOREA"},
            {"Fruit", "BANANA", "DRAGONFRUIT", "ORANGE", "AVOCADO", "PINEAPPLE", "BLUEBERRY", "STARFRUIT", "LEMON", "WATERMELON", "STRAWBERRY"},
            {"Vehicles", "SUBMARINE", "HELICOPTER", "TRACTOR", "SNOW MOBILE", "YACHT", "MOTORCYCLE", "HOT AIR BALLOON", "SPEED BOAT", "SEA DOO", "CARRIAGE"},
            {"Animals", "RABBIT", "GOLDFISH", "ELEPHANT", "PARROT", "SQUIRREL", "FLAMINGO", "ALLIGATOR", "SALAMANDAR", "CARDINAL", "CHIMPANZEE"}
        };

        while ("Y".equalsIgnoreCase(userPlay)) {
            System.out.print(ANSI_RED + "WE" + ANSI_RESET);
            System.out.print(ANSI_YELLOW + "LC" + ANSI_RESET);
            System.out.print(ANSI_GREEN + "OM" + ANSI_RESET);
            System.out.print(ANSI_CYAN + "E " + ANSI_RESET);
            System.out.print(ANSI_BLUE + "TO" + ANSI_RESET);
            System.out.print(ANSI_PURPLE + " H" + ANSI_RESET);
            System.out.print(ANSI_MAGENTA + "AN" + ANSI_RESET);
            System.out.print(ANSI_RED + "GM" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "AN" + ANSI_RESET);

            //local variables + topic and word generation
            int userGuesses = 6; //number of guesses the user can get wrong
            index1T = generateTopicID(hangmanWords);
            topic = hangmanWords[index1T][0];

            index2W = generateWordID(hangmanWords);
            word = hangmanWords[index1T][index2W];

            dashedWord = replaceWithDashes(word);

            String lettersNotGuessed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //Letters user has/has not used
            String lettersGuessed = "                          "; //Letters the user has already used
            
            boolean alreadyGuessed = false;
            
            String[] hangmanGuy = new String[6];
            hangmanGuy[0] = ANSI_BLUE + "('-')" + ANSI_RESET;
            hangmanGuy[1] = ANSI_BLUE + "  |" + ANSI_RESET;
            hangmanGuy[2] = ANSI_BLUE + " / " + ANSI_RESET;
            hangmanGuy[3] = ANSI_BLUE + "\\" + ANSI_RESET;
            hangmanGuy[4] = ANSI_BLUE + "_/" + ANSI_RESET;
            hangmanGuy[5] = ANSI_BLUE + " \\_" + ANSI_RESET;
            
            while (0 < userGuesses) {

                System.out.println("=============================================================================");
                System.out.println(hangmanGuy[0] + "\tTopic:\t\t\t\t\t" + topic);
                System.out.println(hangmanGuy[1] + "\tSecret Word:\t\t\t\t" + dashedWord);
                System.out.println(hangmanGuy[2] + hangmanGuy[3]);
                System.out.println(hangmanGuy[4] + hangmanGuy[5] + "\tLetters Remaining:\t\t\t" + lettersNotGuessed);
                System.out.println("\tLetters Used:\t\t\t\t" + lettersGuessed);
                System.out.println("\tGuesses Remaining:\t\t\t" + userGuesses);
                System.out.println("=============================================================================");
                System.out.print(ANSI_CYAN + "Enter a letter (! to guess entire word)" + ANSI_RESET);
                userGuessInput = scan.next().charAt(0);
                
                //If user wants to guess full word:
                if ('!' == userGuessInput) {
                    System.out.print("Enter the secret word:");

                    //Glitch in java, needs multiple scan.nextLine statements to work
                    String userGuessFull = scan.nextLine();
                    userGuessFull = scan.nextLine();

                    if (userGuessFull.equalsIgnoreCase(word)) {
                        System.out.println(ANSI_GREEN + "Congratulations... " + word + " is correct!" + ANSI_RESET);
                    } else {
                        System.err.println(ANSI_RED + "GAME OVER! The secret word was " + word + "!" + ANSI_RESET);
                    }
                    //in either scenario the game ends and this loop has to end
                    userGuesses = 0;
                } 
                
                
                //else if statement to see if user has not entered a letter or !
                else if (!Character.isLetter(userGuessInput)) {
                    System.out.println(ANSI_RED + "Please enter a letter or \"!\"" + ANSI_RESET);

                } 
                
                String s = String.valueOf(userGuessInput);
                s = s.toUpperCase();
                
                //else if statement to see if user has already guessed the letter
                if (lettersGuessed.contains(s)){
                    System.out.println(ANSI_RED + "You have already guessed this letter, guess again!" + ANSI_RESET);
                }
                
                
                else if (Character.isLetter(userGuessInput)){
                    userGuessInput = Character.toUpperCase(userGuessInput);

                    wordBack = dashedWord;

                    dashedWord = replaceWithLetters(userGuessInput, dashedWord, word);

                    lettersGuessed = lettersUsed(userGuessInput, lettersGuessed, lettersNotGuessed);

                    lettersNotGuessed = lettersNotUsed(userGuessInput, lettersNotGuessed);

                    if (word.equals(dashedWord)) {
                        System.out.println(ANSI_GREEN + "Congratulations you win! " + word + " was correct!" + ANSI_RESET);
                        userGuesses = 0;
                    } //if dashed word is the same as before, points deducted > if points deducted are 0 lose game
                    else if (dashedWord.equals(wordBack)) {
                        userGuesses--;
                        
                        if(userGuesses == 5){
                            hangmanGuy[5] = "   ";  
                        }
                        
                        else if (userGuesses == 4){
                            hangmanGuy[4] = "  ";  
                        }
                        
                        else if (userGuesses == 3){
                            hangmanGuy[3] = "  ";
                            
                        }
                        
                        else if (userGuesses == 2){
                            hangmanGuy[2] = "   "; 
                        }
                        
                        else if (userGuesses == 1){
                            hangmanGuy[1] = "   ";
                            hangmanGuy[0] = (ANSI_RED + "('O')" + ANSI_RESET);
                        }
                        
                        if (userGuesses == 0) {
                            System.out.println(ANSI_RED + "You lose! the word was " + word + ". Try again!" + ANSI_RESET);

                        }
                    } //if dashed word is not completed, but is different than before, continue on with game and replace letters used and letters not used

                }
                
            }

            System.out.println(ANSI_PURPLE + "Would you like to play again? (enter Y or N)" + ANSI_RESET);
            userPlay = scan.next();
        }
        System.out.println(ANSI_CYAN + "Thank you for playing... goodbye!" + ANSI_RESET);
    }

    //methods
    //Generates topic index for game
    public static int generateTopicID(String[][] hangmanWords) {
        Random rand = new Random();
        int index1T = rand.nextInt(10);
        return index1T;
    }

    //Generates word index for game
    public static int generateWordID(String[][] hangmanWords) {
        Random rand = new Random();
        //1st index of word, will be equivalent to the topic index1T
        int index2W; //2nd index of word
        index2W = rand.nextInt(10) + 1;
        return index2W;
    }

    //Replaces randomly generated word with dashes where there are letters
    public static String replaceWithDashes(String word) {
        /*REFERENCE CODE:
         * String sentence = "hello world! 722";
          String str = sentence.replaceAll("[a-zA-Z]", "@");
          System.out.println(str); // "@@@@@ @@@@@! 722"
         */

        String dashedWord = word.replaceAll("[A-Z]", "-");
        return dashedWord;
    }

    //Replaces dashed word with letters if the user makes a correct guess
    public static String replaceWithLetters(char userGuessInput, String dashedWord, String word) {

        String temp = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userGuessInput) {
                temp += userGuessInput;
            } else {
                temp += dashedWord.charAt(i);
            }
        }
        return temp;
    }

    //Adds the letter that the user guessed to the list of letters already used
    public static String lettersUsed(char userGuessInput, String lettersGuessed, String lettersNotGuessed) {

        String temp = "";

        for (int i = 0; i < lettersNotGuessed.length(); i++) {
            if (lettersNotGuessed.charAt(i) == userGuessInput) {
                temp += userGuessInput;
            } else {
                temp += lettersGuessed.charAt(i);
            }
        }
        return temp;
    }

    //Removes the letter that the user guessed correctly from the list of letters not yet used
    public static String lettersNotUsed(char userGuessInput, String lettersNotGuessed) {
        String temp = "";

        for (int i = 0; i < lettersNotGuessed.length(); i++) {
            if (lettersNotGuessed.charAt(i) == userGuessInput) {
                temp += " ";
            } else {
                temp += lettersNotGuessed.charAt(i);
            }
        }
        return temp;
    }
    
    //Fun colours for the game!
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001b[35m";
}

