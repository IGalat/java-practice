package com.jr.rest.jersey.tutorialsPoint._2_CRUD;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Ilya Galatyuk
 */

@ApplicationPath("/com/jr/rest/jersey/tPoint/2/*")
public class PathConfig extends ResourceConfig {
    public PathConfig(){
        packages("com.jr.rest.jersey.tutorialsPoint._2_CRUD");
    }
}
