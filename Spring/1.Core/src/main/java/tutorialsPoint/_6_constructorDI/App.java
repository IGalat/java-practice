package tutorialsPoint._6_constructorDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency injection via constructor
 * in .xml we make:
 * <constructor-arg index="0" ref="spellChecker"/>   meaning 1st argument is reference to bean "spellChecker"
 * <constructor-arg index="1" type="java.lang.String" value="just String"/>  2nd argument: we pass new String("just String");
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans6.xml");

        TextEditor te = (TextEditor) context.getBean("textEditor");
        te.spellCheck();
        System.out.println();
    }
}
