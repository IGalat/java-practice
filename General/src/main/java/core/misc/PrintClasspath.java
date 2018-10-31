package core.misc;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Ilya Galatyuk
 */
public class PrintClasspath {
    public static void main(String[] args) {

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        for(URL url : urls) {
            System.out.println(url.getFile());
        }

    }
}
