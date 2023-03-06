import com.google.common.collect.Multimap;
import java.util.*;

public class StepIterations {
    private HashSet<Node> treeHeight;
    private boolean isGetAnswer;
    private Character S;
    private HashSet<Character> N ;
    private static Multimap<Character, char[]> P;

    public StepIterations(HashSet<Character> n, HashSet<Character> t, Multimap<Character, char[]> p, Character s) {
        treeHeight = new HashSet<>();
        N = n;
        P = p;
        S = s;
    }

    private void iterationProcess() {
        HashSet<Node> newTreeHeight = new HashSet<>();
        for (Node node : treeHeight) {
            Deque<Character> listGrammar  = new ArrayDeque<>(node.getList());
            Deque<Character> wordGrammar = new ArrayDeque<>(node.getWordGrammar());
            boolean flag = false;

            while (!wordGrammar.isEmpty() && !listGrammar.isEmpty() && wordGrammar.peekFirst() == listGrammar.peekFirst()) {
                wordGrammar.removeFirst();
                listGrammar.removeFirst();
                flag = true;
            }
            if (flag) {
                Node newNode = new Node(wordGrammar,listGrammar);
                if (node.isRight(N))
                    newTreeHeight.add(newNode);
                continue;
            }

            if (P.containsKey(listGrammar.peekFirst())) {
                List<char[]> list = (List<char[]>) P.get(listGrammar.peekFirst());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    char[] chars = (char[]) it.next();
                    Deque<Character> newListGrammar  = new ArrayDeque<>(listGrammar);
                    newListGrammar.removeFirst();
                    if (chars.length == 1 && chars[0] == 'e') {
                        Node newTreeNode = new Node(wordGrammar,newListGrammar);
                        newTreeHeight.add(newTreeNode);
                        continue;
                    }
                    for (int i = chars.length - 1; i >= 0; i--)
                        newListGrammar.addFirst(chars[i]);
                    Node newTreeNode = new Node(wordGrammar,newListGrammar);
                    if (node.isRight(N))
                        newTreeHeight.add(newTreeNode);
                }
            }
        }
        treeHeight = newTreeHeight;
    }

    private boolean checkTreeHeight() {
        boolean flag = false;
        if (treeHeight.isEmpty()) {
            isGetAnswer = false;
            return true;
        }
        for (Node treeNode : treeHeight) {
            if (treeNode.getWordGrammar().isEmpty() && treeNode.getList().isEmpty()) {
                flag = true;
                isGetAnswer = true;
                break;
            }
        }
        return flag;
    }

    public boolean syntaxAnalyzer(String str) {
        treeHeight.add(new Node(str,this.S));
        while (!checkTreeHeight())
            iterationProcess();
        return isGetAnswer;
    }
}
