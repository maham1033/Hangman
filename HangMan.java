package com.Computer_Engineer_2020_CE_10;

import java.util.*;

public class HangMan {


    private final String[] wordCollection = {"banana", "apple", "mango", "pinapple",
            "grapefruit", "kiwi", "custardapple", "dragonfruit", "coconut",
            "strawberry", "blueberry", "melon", "tangerine", "apricot",
            "peach", "orange", "cherry", "grape", "watermelon", "pear"};
    private String compPick = null;
    private int guesses;
    private int warning = 4;

    private String alphabets = "abcdefghijklmnopqrstuvwxyz";

    private char[] guessedWord = null;
    private int[] indices;


    public char inputChar() {
        System.out.print("Enter Your Guess: ");
        Scanner num = new Scanner(System.in);
        return num.next().charAt(0);

    }

    public void pickWord() {
        int range = wordCollection.length;
        Random num = new Random();
        int i = num.nextInt(range);
        this.compPick = wordCollection[i];
    }

    public void setGuessedWord() {
        int length = this.compPick.length();
        this.guessedWord = new char[length];
        for (int i = 0; i < length; i++) {
            this.guessedWord[i] = '_';
        }
    }

    public void setGuess(String word) {
        this.guesses = (int) (word.length() * 1.5);
    }

    public Boolean isInPick(char letter) {
        // if the letter is in computer picked word
        return this.compPick.indexOf(letter) != -1;

    }


    public void setIndices(char letter) {
        int length = this.compPick.length();
        int ind = 0;
        char[] comPickArray = this.compPick.toCharArray();
        this.indices = new int[length];
        for (int i = 0; i < length; i++) {
            if (letter == comPickArray[i]) {
                this.indices[ind] = i;
                ind++;
            }
        }
    }

    public void updateGuessed(char letter, int[] loc) {
        // if the lguess is correct update the guessed word
        int length = this.guessedWord.length;
        int ind = 0;
        for (int i = 0; i < length; i++) {
            if (i == loc[ind]) {
                ind++;
                this.guessedWord[i] = letter;
            }
        }

    }

    public void removeAlphabet(char letter) {
        this.alphabets = this.alphabets.replace(letter, ' ');
    }

    public boolean alreadyGuessed(char letter) {
        if (this.alphabets.indexOf(letter) == -1) {
            this.warning--;
            return true;
        } else {
            return false;
        }
    }

    public boolean wordGuessed() {
        for (char c : this.guessedWord) {
            if (c == '_') return false;
        }
        return true;
    }

    public static String isInletter() {


        return (String) ("you guesssed the right word");
    }


    //****** complete the game using the unfinished functions ******//
    public void play() {

        /* few things to do here
        1. pick secret word and set guessed accordingly and available guesses
        2. tell user number of charecters in it and total guess and warnings and available charecters
        3. ask user for guess and if guess isInPick then updateGuessed and total guesses and warnings in case of invalid input
        4. continue the game unless user wins or have consumed all guesses
         */

        pickWord();
        setGuessedWord();
        setGuess(this.compPick);
        System.out.println("Welcome to the HANGMAN Game!!");
        System.out.println("I am thinking of a " + this.compPick.length() + " letter word.");
        System.out.println(this.compPick);
        System.out.println("-----------------------");
        boolean boolcounter = true;
        System.out.println("You have " + this.guesses + " guesses.");
        System.out.println("You have " + this.warning + " warnings.");
        System.out.println("---------------");
        do {
            if ((this.warning == 0 || this.guesses == 0) || wordGuessed()) {
                if (wordGuessed()) {
                    System.out.println(this.guessedWord);
                    System.out.println("Congratulations you win the Game!!");
                } else {
                    System.out.println("You failed. The word was: " + this.compPick);
                }
                boolcounter = false;
            } else {
                System.out.println("Available letters : " + this.alphabets);
                System.out.println(this.guessedWord);
                char guessLetter = inputChar();

                if (isInPick(guessLetter)) {
                    isInletter();
                    if (alreadyGuessed(guessLetter)) {
                        System.out.println("Oops!You have already guessed this letter.");
                    } else {

                        setIndices(guessLetter);
                        updateGuessed(guessLetter, this.indices);
                        removeAlphabet(guessLetter);
                    }

                } else {
                    System.out.println("this word/letter is not a part of the guessed word!");
                    System.out.println("Wrong Guess!");
                    this.guesses--;
                    System.out.print("Available guesses:" + " ");
                    System.out.println(this.guesses);
                }
            }
        } while (boolcounter);
    }

    public static void main(String[] args) {
        HangMan h = new HangMan();
        h.play();
    }

}


