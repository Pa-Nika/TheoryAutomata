package ru.nsu;

import java.util.Deque;
import java.util.HashSet;

public class NFA {
    private Deque<Path> nfa;
    private final HashSet<Character> alphabet;

    public NFA() {
        alphabet = new HashSet<>();
    }

    public Deque<Path> getNfa() {
        return nfa;
    }

    public HashSet<Character> getAlphabet() {
        return alphabet;
    }

    public void makeNFA(Word w) {
        Deque<Path> pathDeque = maker(w);
        for (Path p : pathDeque) {
            char c = (p.getW() == '\0') ? 'e' : p.getW();
            System.out.println("| " + p.getFrom() + " | " + c + " | " + p.getTo() + " |");
        }
        this.nfa =  pathDeque;
    }

    public Deque<Path> maker(Word w) {
        switch (w.getClass().getCanonicalName()) {
            case "ru.nsu.CharSymbol" : {
                alphabet.add(((CharSymbol) w).getS());
                return Path.sym((CharSymbol) w);
            }
            case "ru.nsu.ConcatStr": {
                Word w1 = ((ConcatStr)w).getS1();
                Word w2 = ((ConcatStr)w).getS2();
                Deque<Path> pathDeque1 = maker(w1);
                Deque<Path> pathDeque2 = maker(w2);
                return Path.concat(pathDeque1,pathDeque2);
            }
            case "ru.nsu.KleeneThStar": {
                Word w1 = ((KleeneThStar)w).getS();
                Deque<Path> pathDeque1 = maker(w1);
                return Path.kleene(pathDeque1);
            }
            case "ru.nsu.Or": {
                Word w1 = ((Or)w).getS1();
                Word w2 = ((Or)w).getS2();
                Deque<Path> pathDeque1 = maker(w1);
                Deque<Path> pathDeque2 = maker(w2);
                return Path.orOp(pathDeque1,pathDeque2);
            }
            default:
                System.out.println(w.getClass().getCanonicalName());
                return null;

        }

    }
}
