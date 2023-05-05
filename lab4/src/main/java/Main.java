import com.google.common.collect.Multimap;

import java.util.HashSet;

public class Main {

    public static void main (String[] args) {
        System.out.println("Enter Context Free Grammar:");

        Parser parser = new Parser();
        HashSet<Character> n = parser.readN();
        HashSet<Character> t = parser.readT();
        Multimap<Character, char[]> p = parser.readP();
        Character s = parser.readS();

        StoreMachineMemory storeMemoryMachine = new StoreMachineMemory(n, t, p, s);

        boolean result = storeMemoryMachine.checkLL1();
        System.out.println("\n____________________________________________");
        System.out.println(result ? "LL(1) grammar" : "Isn't LL(1) grammar");
    }
}