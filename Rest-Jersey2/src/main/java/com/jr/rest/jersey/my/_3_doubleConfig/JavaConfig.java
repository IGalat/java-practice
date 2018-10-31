package com.jr.rest.jersey.my._3_doubleConfig;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @see ResponderConfigured
 * @author Ilya Galatyuk
 */

//real path, gives warning of conflicting configs:
//@ApplicationPath("/com.jr.rest/jersey/my/doubleconfig/*")

//mock path, used to not get warning every time:
@ApplicationPath("/com/jr/rest/jersey/my/doubleconfig123/*")
public class JavaConfig extends ResourceConfig {
    public JavaConfig() {
        packages("com.jr.rest.jersey.my._3_doubleConfig");
    }
}
