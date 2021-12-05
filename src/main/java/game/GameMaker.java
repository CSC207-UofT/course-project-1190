package game;

import java.util.Scanner;
import java.io.File;

public class GameMaker {
    private Scanner reader;

    public GameMaker() {
        this.reader = new Scanner(System.in);
    }

    public int[] getBoardSize() {
        System.out.println("\nIt's time to make your own game!");

        File file = new File("game.ser");
        boolean hasSavedGame = file.exists();

        if (hasSavedGame) {
            System.out.println("\nSave state detected, do you want to load it? Enter y or n.");
            String useSavedGame = reader.next();
            while (! (useSavedGame.equals("y") || (useSavedGame.equals("n")))) {
                System.out.println("\nInvalid input. Please type in y or n");

                useSavedGame = reader.nextLine();
            }

            int[] values = new int[2];


            if (useSavedGame.equals("y")) {

                values[1] = 1;
                return values;
            }
            else {
                System.out.println("\nWhat is the size of your game board?");

                int boardSize = reader.nextInt();

                System.out.println("\nNice! Your board size is " + boardSize + ".");
                values[0] = boardSize;
                return values;
             }
        }
        else  {
            System.out.println("\nNo Save State Detected. Loading game");
            System.out.println("\nWhat is the size of your game board?");

            int boardSize = reader.nextInt();

            System.out.println("\nNice! Your board size is " + boardSize + ".");

            int[] values = new int[2];
            values[0] = boardSize;
            return values;
        }
    }
}

