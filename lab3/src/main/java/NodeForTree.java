import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class NodeForTree {
    @Getter @Setter
    private String stack;
    @Getter
    private final Deque<Character> word;

    public NodeForTree(String word) {
        this.stack = "";
        this.word = strToStack(word);
    }

    public NodeForTree(Deque<Character> word, String stack) {
        this.stack = stack;
        this.word = word;
    }

    public Deque<Character> strToStack(String str) {
        Deque<Character> newStack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            newStack.addLast(c);
        }
        return newStack;
    }

    public String deq2str(Deque<Character> deque) {
        Iterator i = deque.iterator();
        String str = "";
        while (i.hasNext()) {
            str += i.next();
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NodeForTree treeNode = (NodeForTree) obj;
        return stack.equals(treeNode.stack) && word.equals(treeNode.word);
    }

    @Override
    public String toString() {
        return "(" +
                "word = " + deq2str(word) +
                ", stack = " + stack +
                ')';
    }
}
