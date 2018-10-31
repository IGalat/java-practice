package com.jr.rest.jersey.my._4_conflictedConfig.byJava;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Ilya Galatyuk
 */

//real path, gives warning of conflicting configs:
//@ApplicationPath("/com.jr.rest/jersey/my/conflicted/*")

//mock path, used to not get warning every time:
@ApplicationPath("/com/jr/rest/jersey/my/conflicted123/*")
public class JavaConfig extends ResourceConfig {
    public JavaConfig() {
        packages("com.jr.rest.jersey.my._4_conflictedConfig.byJava");
    }
}
