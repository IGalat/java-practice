package tutorialsPoint._11_javaBasedConfig.beanDependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {
    @Bean
    public TextEditor textEditor() {
        return new TextEditor(spellChecker());
    }

    //annotation here not really needed for this example. It just won't be inner bean
    @Bean
    public SpellChecker spellChecker() {
        return new SpellChecker();
    }
}