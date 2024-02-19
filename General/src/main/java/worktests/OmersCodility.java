package worktests;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class OmersCodility {
    static OmersCodility omersCodility = new OmersCodility();

    public int solution(String S) {
        int[] digits = toIntArray(S);
        char[] consistentBiggestTwo = getConsistentBiggest(digits, 2);
        return Integer.parseInt(new String(consistentBiggestTwo));
    }

    private int[] toIntArray(String s) {
        int[] result = new int[s.length()];
        CharacterIterator iter = new StringCharacterIterator(s);
        for (int i = 0; i < s.length(); i++) {
            result[i] = iter.current();
            iter.next();
        }
        return result;
    }

    private char[] getConsistentBiggest(int[] digitCodes, int n) {
        char[] result = new char[n];

        List<Integer> maxPositions = getPositionsOfMax(digitCodes, n - 1);
        result[0] = (char) digitCodes[maxPositions.get(0)];

        for (int i = 1; i < n; i++) {
            int nextMax = 0;
            for (int pos : maxPositions) {
                int nextCode = digitCodes[pos + i];
                if (nextCode > nextMax) {
                    nextMax = nextCode;
                }
            }
            result[i] = (char) nextMax;
        }

        return result;
    }

    private List<Integer> getPositionsOfMax(int[] ints, int endPadding) {
        int max = ints[1];
        List<Integer> maxPos = new ArrayList<>();
        for (int i = 0; i < ints.length - (endPadding); i++) {
            int code = ints[i];
            if (code > max) {
                max = ints[i];
                maxPos = new ArrayList<>();
            }
            if (code == max) {
                maxPos.add(i);
            }
        }
        return maxPos;
    }

    public static void main(String[] args) {
        test("50552", 55);
        test("10101", 10);
        test("88", 88);
        test("12345", 45);
        test("54321", 54);
        test("1234567890", 90);
        test("1000000000000000000000000000000000000000000000000000000000", 10);
        test("100000002", 10);
        test("10120", 20);
        test("9999199", 99);
    }

    public static void test(String input, int expected) {
        int result = omersCodility.solution(input);
        if (result != expected) {
            System.out.println(input + " , res = " + result + " != " + expected);
        }
    }
}
