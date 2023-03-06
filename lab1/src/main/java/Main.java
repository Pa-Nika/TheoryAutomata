import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Scanner;
import java.util.HashSet;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static HashSet<Character> N = new HashSet<Character>();
    private static HashSet<Character> T = new HashSet<Character>();
    private static Multimap<Character, char[]> P = ArrayListMultimap.create();
    private static Character S;

    public static void main (String[] args) {
        System.out.println("Enter Context Free Grammar:");
        parsParams();
        StepIterations stepIteration = new StepIterations(N,T,P,S);
        System.out.println("\nEnter a word");
        String str;
        while (true) {
            boolean flag = false;
            str = scanner.nextLine();
            for (char c:str.toCharArray()) {
                if (!T.contains(c)) {
                    System.out.println("∑ doesn't contain this char");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        boolean isGetAnswer = stepIteration.syntaxAnalyzer(str);
        System.out.println(isGetAnswer ? "YES" : "NO");
    }

    private static void parsParams() {
        readN();
        readT();
        readP();
        readS();
    }

    private static void readN() {
        String str;
        while (true) {
            boolean flag = false;
            System.out.print("N: ");
            str = scanner.nextLine();
            String newStr = str.replaceAll("[^->e'\"]", "");
            if (newStr.length() > 0) {
                System.out.println("Enter else char");
            } else {
                String[] nChars = str.split(",");
                for (String value : nChars) {
                    char[] arr = value.toCharArray();
                    if (arr.length > 1) {
                        System.out.println("Enter else char");
                        flag = true;
                        N.clear();
                        break;
                    } else
                        N.add(arr[0]);
                }
                if(!flag){
                    break;
                }
            }
        }
    }

    public static void readS() {
        String str;
        while (true) {
            System.out.print("S: ");
            str = scanner.nextLine();
            char[] chars = str.toCharArray();
            if (chars.length != 1) {
                System.out.println("Write only one element");
            } else if (!N.contains(chars[0])) {
                System.out.println("This symbol doesn't in the arr N");
            } else {
                S = chars[0];
                break;
            }
        }
    }

    public static void readT() {
        String str;
        while (true) {
            boolean flag = false;
            boolean flag1 = false;
            System.out.print("∑: ");
            str = scanner.nextLine();
            String newStr = str.replaceAll("[^->e'\"]", "");
            if (newStr.length() > 0) {
                System.out.println("Enter else char");
            } else {
                for (Character c:str.toCharArray()) {
                    if (N.contains(c)) {
                        System.out.println("Wrong char " + c + " from N");
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                String[] terminalsChar = str.split(",");
                for (String value : terminalsChar) {
                    char[] arr = value.toCharArray();
                    if(arr.length > 1) {
                        System.out.println("Enter else char");
                        flag1 = true;
                        T.clear();
                        break;
                    } else {
                        T.add(arr[0]);
                    }
                }
                if (!flag1) {
                    break;
                }
            }
        }
    }

    public static void readP() {
        String str;
        while (true) {
            boolean flag = false;
            System.out.print("P: ");
            str = scanner.nextLine();
            if (!str.matches("[^'\"]*")) {
                System.out.println("Enter else char");
                continue;
            }
            String[] pChars = str.split(",");
            for (String s:pChars) {
                String[] current = s.split("->");
                if (current.length == 2) {
                    char[] A = current[0].toCharArray();
                    if (A.length == 1 && N.contains(A[0])) {
                        char[] a = current[1].toCharArray();
                        for (char c:a) {
                            if ((!N.contains(c) && !T.contains(c)) && c != 'e') {
                                System.out.println("What is this symbol?");
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            break;
                        } else
                            P.put(A[0],a);
                    } else {
                        System.out.println("This symbol doesn't in arr N");
                        flag = true;
                        break;
                    }
                } else {
                    System.out.println("Rules doesn't look like A->a");
                    flag = true;
                    break;
                }
            }
            if (!flag)
                break;
        }
    }
}
