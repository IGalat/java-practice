package com.jr.rest.jersey.tutorialsPoint._1_HWWithResource;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Ilya Galatyuk
 */

@ApplicationPath("/com/jr/rest/jersey/tPoint/1/*")
public class PathConfig extends ResourceConfig {
    public PathConfig(){
        packages("com.jr.rest.jersey.tutorialsPoint._1_HWWithResource");
    }
}
