package tutorialsPoint._7_setterDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dependency injection via setter
 * same as with constructor, but instead use <property name="spellChecker" ref="spellChecker"/>
 * also p-namespace is short form. check out Beans7.xml
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/tutorialspoint/Beans7.xml");
        TextEditor te1 = (TextEditor) context.getBean("textEditor1");
        te1.spellCheck();
        te1.printJustString();

        TextEditor te2 = (TextEditor) context.getBean("textEditor2");
        te2.printJustString();
    }
}
