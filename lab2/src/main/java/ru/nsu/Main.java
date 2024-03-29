package ru.nsu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanIn = new Scanner(System.in);
    public static String str;
    public static StringBuilder alphabet = new StringBuilder();

    public static String readRegExpr() {
        System.out.println("Enter a regular expression:");
        while (true) {
            str = scanIn.nextLine();
            String newStr = str.replaceAll("[a-zA-Z0-9*()|&]", "");
            if (newStr.length() > 0) {
                System.out.println("Invalid character used, try again\n");
            }
            else{
                break;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String str = readRegExpr();
        List<Word> wordList = Tokenizer.parse(str);
        if (wordList == null) {
            return;
        }
        NFA nfa = new NFA();
        nfa.makeNFA(wordList.get(0));
        for (Character c: nfa.getAlphabet() ) {
            alphabet.append(" ").append(c);
        }
    }
}
