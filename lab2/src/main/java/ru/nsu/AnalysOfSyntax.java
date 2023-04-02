package ru.nsu;

import java.util.Deque;
import java.util.HashSet;

public class AnalysOfSyntax {
    public static boolean analyseSyntax(NFA nfa, String str) {
        HashSet<CurState> treeLevel = new HashSet<>();
        CurState step0 = new CurState(0,str);
        treeLevel.add(step0);
        while (!checkTree(treeLevel)) {
            for (CurState c:treeLevel) {
                System.out.println("( " + c.getFrom() + " , " + c.getS() + " )");
            }
            System.out.println("\n\n");
            treeLevel = step(nfa.getNfa(), treeLevel);
        }
        if (treeLevel.isEmpty()) {
            for (CurState c:treeLevel) {
                System.out.println("( " + c.getFrom() + " , " + c.getS() + " )");
            }
            return false;
        } else {
            for (CurState c:treeLevel) {
                if (c.getS().equals("")) {
                    System.out.println("( " + c.getFrom() + " , ε )   == read chain");
                } else {
                    System.out.println("( " + c.getFrom() + " , " + c.getS() + " )");
                }
            }
            return true;
        }
    }

    private static HashSet<CurState> step(Deque<Path> nfa, HashSet<CurState> currentTreeLevel){
        HashSet<CurState> nextTreeLevel = new HashSet<>();
        for (CurState cc: currentTreeLevel) {
            if (cc.getS().length() == 0) {
                continue;
            }
            char currentChar = cc.getS().charAt(0);
            for (Path p:nfa) {
                if (p.getFrom() == cc.getFrom()) {
                    if (p.getW() == currentChar) {
                        CurState nextCondition;
                        if (cc.getS().length() > 1) {
                            nextCondition = new CurState(p.getTo(), cc.getS().substring(1));
                        } else {
                            nextCondition = new CurState(p.getTo(), "");
                        }
                        nextTreeLevel.add(nextCondition);
                    } else if (p.getW() == '\0') {
                        CurState nextCondition = new CurState(p.getTo(), cc.getS());
                        nextTreeLevel.add(nextCondition);
                    }
                }
            }
        }
        return nextTreeLevel;
    }

    private static boolean checkTree(HashSet<CurState> currentTreeLevel){
        for (CurState cc: currentTreeLevel) {
            if (cc.getS().equals("")) {
                return true;
            }
        }
        return currentTreeLevel.isEmpty();
    }
}
