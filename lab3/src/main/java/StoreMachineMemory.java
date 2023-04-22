import com.google.common.collect.Multimap;

import java.util.*;

public class StoreMachineMemory {
    private HashSet<Character> N ;
    private HashSet<Character> T;
    private static Multimap<String, Character> P;
    private Character S;
    private HashSet<NodeForTree> treeLevel;
    private boolean result;

    public StoreMachineMemory(HashSet<Character> n, HashSet<Character> t, Multimap<String, Character> p, Character s) {
        N = n;
        T = t;
        P = p;
        S = s;
        treeLevel = new HashSet<>();
    }

    private void printTreeLevel(){
        System.out.println("\n____________________________________________");
        for (NodeForTree treeNode : treeLevel) {
            System.out.println(treeNode);
        }
    }

    private void step() {
        HashSet<NodeForTree> newTreeLevel = new HashSet<>();
        for (NodeForTree treeNode : treeLevel) {
            Deque<Character> word = new ArrayDeque<>(treeNode.getWord());
            String stack = treeNode.getStack();

            if (!word.isEmpty()) {
                Character character = word.peekFirst();
                Deque<Character> newWord = new ArrayDeque<>(word);
                newWord.removeFirst();
                NodeForTree newTreeNode = new NodeForTree(newWord, stack.concat(String.valueOf(character)));
                newTreeLevel.add(newTreeNode);
            }

            for (String string : P.keySet()) {
                List<Character> list = (List<Character>) P.get(string);
                if (string.equals("e")) {
                    for (Character character : list) {
                        int fromIndex = 0;
                        int Index = stack.length();
                        while (fromIndex < Index - 1) {
                            String newStack = stack.substring(0, fromIndex + 1) + character + stack.substring(fromIndex + 1, fromIndex + 2);
                            NodeForTree newTreeNode = new NodeForTree(word, newStack);
                            newTreeLevel.add(newTreeNode);
                            fromIndex++;
                        }
                        String newStack = stack + character;
                        NodeForTree newTreeNode = new NodeForTree(word, newStack);
                        newTreeLevel.add(newTreeNode);
                    }
                }
                if (stack.contains(string)) {
                    for (Character character : list) {
                        int fromIndex = 0;
                        int Index = stack.indexOf(string, fromIndex);
                        while (Index != -1) {
                            String newStack = stack.replaceFirst(string, String.valueOf(character));
                            NodeForTree newTreeNode = new NodeForTree(word, newStack);
                            newTreeLevel.add(newTreeNode);
                            fromIndex = Index + 1;
                            Index = stack.indexOf(string, fromIndex);
                        }

                    }
                }
            }
        }
        treeLevel = newTreeLevel;
    }

    private boolean checkTreeLevel() {
        boolean flag = false;
        if (treeLevel.isEmpty()) {
            result = false;
            return true;
        }
        for (NodeForTree treeNode : treeLevel) {
            if (treeNode.getWord().isEmpty() && treeNode.getStack().equals(String.valueOf(S))) {
                flag = true;
                result = true;
                break;
            }
        }
        return flag;
    }

    public boolean syntaxAnalyzer(String str) {
        treeLevel.add(new NodeForTree(str));
        printTreeLevel();
        while (!checkTreeLevel()) {
            step();
            printTreeLevel();
        }
        return result;
    }

}