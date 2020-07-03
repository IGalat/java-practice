package eval;

class Solution {
    private static final String IMPOSSIBLE = "IMPOSSIBLE";
    private static final String NOTHING = "NOTHING";
    private static final String INSERT = "INSERT ";
    private static final String REMOVE = "REMOVE ";
    private static final String MOVE = "MOVE ";

    public String solution(String S, String T) {
        final char[] charsS = S.toCharArray();
        final char[] charsT = T.toCharArray();
        int lenS = charsS.length;
        int lenT = charsT.length;
        if (Math.abs(lenS - lenT) > 1) return IMPOSSIBLE;

        if (lenS > lenT) {
            final int diffCharIndex = findDiffCharIndex(charsS, charsT);
            if (diffCharIndex == -1) {
                return IMPOSSIBLE;
            } else {
                return REMOVE + charsS[diffCharIndex];
            }
        }

        if (lenT > lenS) {
            final int diffCharIndex = findDiffCharIndex(charsT, charsS);
            if (diffCharIndex == -1) {
                return IMPOSSIBLE;
            } else {
                return INSERT + charsT[diffCharIndex];
            }
        }

        int firstDiff = -1;
        for (int i = 0; i < lenS; i++) {
            if (charsS[i] != charsT[i]) {
                firstDiff = i;
                break;
            }
        }
        if (firstDiff == -1) return NOTHING;

        int lastDiff = -1;
        for (int i = lenS - 1; i >= 0; i--) {
            if (charsS[i] != charsT[i]) {
                lastDiff = i;
                break;
            }
        }

        if (firstDiff == lastDiff) return IMPOSSIBLE;

        for (int i = firstDiff; i < lastDiff; i++) {
            boolean same = charsS[i + 1] == charsT[i];
            if (!same) return IMPOSSIBLE;
        }

        if (charsS[firstDiff] != charsT[lastDiff]) return IMPOSSIBLE;
        return MOVE + charsS[firstDiff];
    }

    // -1 if more than 1 char diff
    private int findDiffCharIndex(char[] longerCh, char[] shorterCh) {
        int index = -1;
        for (int i = 0; i < longerCh.length; i++) {
            boolean diffExists = index != -1;
            boolean same = diffExists ? longerCh[i] == shorterCh[i - 1] : longerCh[i] == shorterCh[i];
            if (!same) {
                if (diffExists) return -1;
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("vdorgjhhvuioehv", "vdogjhhvuioehvr"));
    }
}
