package tutorialsPoint._11_javaBasedConfig.callbacks;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(Foo.class);
        ctx.registerShutdownHook();
    }
}
