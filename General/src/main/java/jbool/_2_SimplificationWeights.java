package jbool;

import com.bpodgursky.jbool_expressions.ExprUtil;
import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Galatyuk Ilya
 */
public class _2_SimplificationWeights {

    public static void main(String[] args) {
        resolveInMinimumSteps("(c1 | c2 | c3 | c4) & (c5 | c6 | c7) & c8");
    }


    private static void resolveInMinimumSteps(String stringExpression) {
        Expression<String> initialExpression = RuleSet.simplify(ExprParser.parse(stringExpression));

        List<String> byWeight = ExprUtil.getConstraintsByWeight(initialExpression);
        System.out.println("Starting cycle with expression: " + initialExpression + ", by weight: " + byWeight);
        for (int i = 0; i < 5; i++) {
            Expression<String> expression = initialExpression;
            System.out.println("Iteration " + (i + 1));
            int step = 0;
            while (expression.toLexicographicString().contains("c")) {
                step++;
                boolean resolving = randomBool();
                Map<String, Boolean> assignMap = Collections.singletonMap(byWeight.get(step - 1), resolving);

                expression = RuleSet.simplify(RuleSet.assign(expression, assignMap));
                System.out.println("Resolved " + byWeight.get(step - 1) + " as " + resolving + ", expression now: " + expression);
                System.out.println("Dymanic weight map: " + ExprUtil.getConstraintsByWeight(expression));
            }

        }

    }

    private static boolean randomBool() {
        if (Math.random() > 0.5) return true;
        return false;
    }
}
