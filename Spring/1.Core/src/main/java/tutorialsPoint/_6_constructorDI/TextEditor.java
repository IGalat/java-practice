package tutorialsPoint._6_constructorDI;

/**
 * Created by GREGTECH on 17.05.2017.
 */
public class TextEditor {
    private SpellChecker spellChecker;
    private String justString;

    public TextEditor(SpellChecker spellChecker, String justString) {
        System.out.println("Inside TextEditor constructor.");
        this.spellChecker = spellChecker;
        this.justString = justString;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public void printJustString() {
        System.out.println(justString);
    }
}
