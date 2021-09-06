package com.lax;

import java.io.IOException;
import java.util.Scanner;

public class Main {
  /*
  _    _                   _  __ _     ___
 | |  | |                 (_)/ _| |   |__ \
 | |  | |_ __  _ __   __ _ _| |_| |_     ) |
 | |  | | '_ \| '_ \ / _` | |  _| __|   / /
 | |__| | |_) | |_) | (_| | | | | |_   / /_
  \____/| .__/| .__/ \__, |_|_|  \__| |____|
        | |   | |     __/ |
        |_|   |_|    |___/
*/
    /**
     *Programmet fungerar som så att du läser in en textfil och sedan får du göra 3 val.
     * Val 1: söka efter hur många gånger ett ord förekommer i textfilen.
     * val 2: söka efter hur många meningar och stycken ett ord förekommer i textfilen.
     * val 3: Modifiera texten i textfilen och sedan spara till ny fil.
     *
     * Val 3 är under konstruktion.
     **/

    public static int count;


    public static void main(String[] args) throws IOException {
        /** Deklaration av variabler **/
        String inputWord;
        int inputInt;
        String userInput;
        String choiceCaseSensitive = "";
        boolean choiceMade = false;
        boolean caseSensitiveChoice = false;
        WordSearch wordSearch = new WordSearch();
        SentenceSearch sentenceSearch = new SentenceSearch();
        ModifyFile modifyFile = new ModifyFile();

        /** Initiera input avläsning i konsolen **/
        Scanner input = new Scanner(System.in);

        /** Interface med input **/
        System.out.println("Loading file: textfil_apendo.text");
        try {
            Thread.sleep(1600);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Loading file: file Loaded.");
        while (!choiceMade) {
            System.out.println("What do you want to do?\n1.Search for a word\n2.Search for a word in a sentence\n3.Modify and create a new file");
            userInput = input.next();

            if (userInput.equals("1")) {
                System.out.println("Type the word you want to search for");
                inputWord = input.next();
                while (!caseSensitiveChoice){
                    System.out.println("Do you want the search to be case-sensitive? Yes or No (Y/N)");
                    choiceCaseSensitive = input.next().toUpperCase();
                if (choiceCaseSensitive.equals("Y")) {
                    System.out.println(wordSearch.FindWord(inputWord,true));
                    caseSensitiveChoice = true;
                    choiceMade = true;
                }
                if (choiceCaseSensitive.equals("N")) {
                    System.out.println(wordSearch.FindWord(inputWord, false));
                    caseSensitiveChoice = true;
                    choiceMade = true;
                    }
                }
            }

            if (userInput.equals("2")) {
                System.out.println("Type the word you want to search for");
                inputWord = input.next();
                System.out.println(sentenceSearch.FindSentences(inputWord));
                choiceMade = true;
            }

            if (userInput.equals("3")) {
                System.out.println("Type the word you want to search for");
                inputWord = input.next();
                System.out.println("After how many words should the modification appear");
                inputInt = input.nextInt();
                modifyFile.Modify(inputWord,inputInt);
                choiceMade = true;
            }
        }


    }



}
