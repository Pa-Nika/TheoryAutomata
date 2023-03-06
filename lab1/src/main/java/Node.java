import lombok.Getter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

@Getter
public class Node {
    public Deque<Character> listGrammar = new ArrayDeque<>();
    public Deque<Character> wordGrammar = new ArrayDeque<>();

    public Node(Deque<Character> word, Deque<Character> list) {
        this.listGrammar = list;
        this.wordGrammar = word;
    }
    public Node(String word, Character s) {
        this.listGrammar.addLast(s);
        this.wordGrammar = str2list(word);
    }

    public String deq2str(Deque<Character> deque) {
        Iterator i = deque.iterator();
        String str = new String();
        while (i.hasNext()) {
            str += i.next();
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Node node = (Node)o;
        return listGrammar.equals(node.listGrammar) && wordGrammar.equals(node.wordGrammar);
    }

    public Deque<Character> str2list(String str) {
        Deque<Character> newListGrammar = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            newListGrammar.addLast(c);
        }
        return newListGrammar;
    }

    public boolean isRight(HashSet<Character> N) {
        int iterationStr = 0;
        int iterationWord = wordGrammar.size();
        for (Character myChar : listGrammar) {
            if (!N.contains(myChar) && !wordGrammar.contains(myChar)) {
                return false;
            }
            if (!N.contains(myChar)) {
                iterationStr++;
            }
        }
        return (iterationStr <= iterationWord);
    }

    @Override
    public String toString() {
        return "(" + "word = " + deq2str(wordGrammar) + ", list = " + deq2str(listGrammar) + ')';
    }

    public Deque<Character> getList() {
        return listGrammar;
    }
    public Deque<Character> getWordGrammar() {
        return wordGrammar;
    }
}
