package tutorialsPoint._11_javaBasedConfig.scopes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Foo foo1 = ctx.getBean(Foo.class);
        Foo foo2 = ctx.getBean(Foo.class);

        foo1.setBar("Makarena!");
        foo1.printBar();
        foo2.printBar();

        ctx.registerShutdownHook();
    }
}
