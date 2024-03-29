package ru.nsu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tokenizer {

    public static List<Word> parse(String str) {
        List<Word> internalVar = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        boolean flagForEach = true;

        for (char c:str.toCharArray()) {

            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == ')' || c == '&' || c == '|' || c == '*') {
                switch (c) {
                    case '(': {
                        stack.push('(');
                        break;
                    }
                    case '&': {
                        if (stack.peek() == '(') {
                            stack.push('&');
                        } else {
                            System.out.println("Incorrect expression: &");
                            flagForEach = false;
                        }
                        break;
                    }
                    case '|': {
                        if (stack.peek() == '(') {
                            stack.push('|');
                        } else {
                            System.out.println("Incorrect expression: |");
                            flagForEach = false;
                        }
                        break;
                    }
                    case '*': {
                        if (stack.peek() == ')') {
                            stack.pop();stack.pop();
                        } else {
                            System.out.println("Incorrect expression: *");
                            flagForEach = false;
                            break;
                        }
                        Word w = internalVar.remove(internalVar.size() - 1);
                        internalVar.add(new KleeneThStar(w));
                        break;
                    }
                    case ')': {
                        if (stack.peek() == '(') {
                            stack.push(')');
                        } else if (stack.peek() == '&') {
                            Word w1 = internalVar.remove(internalVar.size() - 1);
                            Word w2 = internalVar.remove(internalVar.size() - 1);
                            internalVar.add(new ConcatStr(w1,w2));
                            stack.pop();
                            stack.pop();
                        } else if(stack.peek() == '|') {
                            Word w1 = internalVar.remove(internalVar.size() - 1);
                            Word w2 = internalVar.remove(internalVar.size() - 1);
                            internalVar.add(new Or(w2,w1));
                            stack.pop();
                            stack.pop();
                        } else {
                            System.out.println("Incorrect expression: )");
                            flagForEach = false;
                        }
                        break;
                    }
                    default: {
                        System.out.println("Incorrect expression");
                        flagForEach = false;
                    }
                }
            } else {
                internalVar.add(new CharSymbol(c));
            }
            if (!flagForEach) {
                break;
            }
        }
        if (!stack.isEmpty() || !flagForEach) {
            System.out.println("Incorrect expression");
            return null;
        }
        return internalVar;
    }
}
