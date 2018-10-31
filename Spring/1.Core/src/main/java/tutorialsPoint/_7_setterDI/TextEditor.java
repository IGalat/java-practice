package tutorialsPoint._7_setterDI;

/**
 * Created by GREGTECH on 17.05.2017.
 */
public class TextEditor {
    private SpellChecker spellChecker;
    private String justString;

    // a setter method to inject the dependency
    public void setSpellChecker(SpellChecker spellChecker) {
        System.out.println("Inside setSpellChecker.");
        this.spellChecker = spellChecker;
    }

    public void setJustString(String justString) {
        this.justString = justString;
    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }

    public void printJustString() {
        System.out.println(justString);
    }
}
