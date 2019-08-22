import java.util.Arrays;
import java.util.List;

/**
 * https://arxiv.org/pdf/1808.07501.pdf
 */
public class ScoringRuleLog {
    private static final int LOG_BASE = 2;
    private static final int MAX_SCORE = 100;

    public static void main(String[] args) {
        List<Integer> credences = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30, 33, 40, 50, 60, 70, 80, 85, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99);

        for (int credence : credences) {
            System.out.print(credence + "% | ");
            for (int answersN = 2; answersN <= 4; answersN++) {
                System.out.print(answersN + " ans, scoreFormer = " + scoreFormer(credence, answersN)
                        + ", scoreLater = " + scoreLater(credence, answersN) + " | ");
            }
            System.out.println();
        }
    }

    private static double scoreFormer(int credence, int answersN) {
        if (credence <= 0 || credence >= 100) throw new RuntimeException();

        double pCredence = credence / 100D;

        double score = MAX_SCORE * log(pCredence * answersN, answersN);

        return (double) Math.round(score * 100) / 100;
    }

    private static double scoreLater(int credence, int answersN) {
        if (credence <= 0 || credence >= 100) throw new RuntimeException();

        double pCredence = credence / 100D;

        double pRandom = 1D / answersN;
        double logRandom = log(pRandom);
        double logMax = log(0.99);
        double logCredence = log(pCredence);
        double logOneMinusRandom = log(1 - pRandom);
        double logOneMinusCredence = log(1 - pCredence);

        double score = MAX_SCORE / (logMax - logRandom);

        double scoreIfCorrect = score * (logCredence - logRandom);
        double scoreIfIncorrect = score * (logOneMinusCredence - logOneMinusRandom);

        if (true) {
            score = scoreIfCorrect;
        } else {
            score = scoreIfIncorrect;
        }

        return (double) Math.round(score * 100) / 100;
    }

    private static double log(double x) {
        return Math.log(x) / Math.log(LOG_BASE);
    }

    private static double log(double x, double base) {
        return Math.log(x) / Math.log(base);
    }
}
