import com.google.common.collect.Multimap;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        System.out.println("Enter Context Free Grammar:");

        Parser parser = new Parser();
        HashSet<Character> n = parser.readN();
        HashSet<Character> t = parser.readT();
        Multimap<String, Character> p = parser.readP();
        Character s = parser.readS();

        StoreMachineMemory storeMachineMemory = new StoreMachineMemory(n, t, p, s);

        System.out.println("\nString to recognize from the characters:");
        String innerStr;
        while (true) {
            boolean flagContinue = false;
            innerStr = scanner.nextLine();
            for (char c:innerStr.toCharArray()) {
                if (!t.contains(c)) {
                    System.out.println("String doesn't contain the specified character");
                    flagContinue = true;
                    break;
                }
            }
            if (!flagContinue) {
                break;
            }
        }
        boolean result = storeMachineMemory.syntaxAnalyzer(innerStr);
        System.out.println("\n____________________________________________");
        System.out.println(result ? "Success" : "Fail");

    }
}