package com.jr.rest.jersey.my._2_javaBasedHW;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Alternative to web.xml config. (web.xml config doesn't influence this one). Really much more simple and better
 * <p/>
 * For REST com.jr.service you need at least 1 'global' config(java-based or web.xml), that maps packages to path
 *
 * @author Ilya Galatyuk
 */

//analogy to <url-pattern> in web.xml.  {server IP:port}/{this path, without '*'}/{@Path of class}
@ApplicationPath("/com/jr/rest/jersey/my/javahw/*")
public class JavaBasedConfig extends ResourceConfig {
    public JavaBasedConfig() {
        //packages that will respond to path above. multiple packages are separated by comma
        packages("com.jr.rest.jersey.my._2_javaBasedHW");
    }
}
