package jbool;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.parsers.ExprParser;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

import java.util.Collections;

/**
 * @author Galatyuk Ilya
 */
public class _1_Basics {

    public static void main(String[] args) {
        stringConvertAndSimplifySOUT("((!e1 | e1) & e2 & e3)");
        toCNF("((A & B) | (C & D))");
        calculations(false, false, true, true, false);
    }

    private static void stringConvertAndSimplifySOUT(String expressionString) {
        System.out.println("----------- start stringConvertAndSimplifySOUT");
        Expression<String> parsed = RuleSet.simplify(ExprParser.parse(expressionString));
        System.out.println(parsed);
    }

    private static Expression<String> toCNF(String expressionString) {
        System.out.println("----------- start toCNF");
        Expression<String> simplifiedParsed = RuleSet.simplify(ExprParser.parse(expressionString));
        Expression<String> cnfForm = RuleSet.toCNF(simplifiedParsed);
        System.out.println(cnfForm);
        System.out.println("simplified: " + RuleSet.simplify(cnfForm));
        return cnfForm;
    }

    private static void calculations(boolean f0, boolean f1, boolean f2, boolean f3, boolean f4) {
        String expressionString = "((f0 & f1) | (f2 & f3)) & f4";
        System.out.println("----------- start calculations with " + expressionString);
        Expression<String> expression = ExprParser.parse(expressionString);

        expression = RuleSet.simplify(RuleSet.assign(expression, Collections.singletonMap("f0", f0)));
        System.out.println("After f0 -> " + f0 + ", expression is: " + expression);

        expression = RuleSet.simplify(RuleSet.assign(expression, Collections.singletonMap("f1", f1)));
        System.out.println("After f1 -> " + f1 + ", expression is: " + expression);

        expression = RuleSet.simplify(RuleSet.assign(expression, Collections.singletonMap("f2", f2)));
        System.out.println("After f2 -> " + f2 + ", expression is: " + expression);

        expression = RuleSet.simplify(RuleSet.assign(expression, Collections.singletonMap("f3", f3)));
        System.out.println("After f3 -> " + f3 + ", expression is: " + expression);

        expression = RuleSet.simplify(RuleSet.assign(expression, Collections.singletonMap("f4", f4)));
        System.out.println("After f4 -> " + f4 + ", expression is: " + expression);
    }
}
