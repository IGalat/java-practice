package tutorialsPoint._11_javaBasedConfig.callbacks;

public class Foo {
    public void init() {
        System.out.println("Foo going through init");
    }

    public void cleanup() {
        System.out.println("Foo gonna destroy");
    }
}
