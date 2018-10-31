package _6_javaConfig;

/**
 * Replacement for web.xml config. If java-config is present, web.xml doesn't work!
 *
 * @author Ilya Galatyuk
 */

//disabled to allow web.xml to work. After un-commenting change  "/com.jr.rest/spring/javaconfig"  back to  "/com.jr.rest/spring/javaconfig/*"

/*
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/com.jr.rest/spring/javaconfig"};
    }
}
*/
