package com.jr.rest.jersey.my._4_conflictedConfig.byJava;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Ilya Galatyuk
 */

@Path("/hello")
public class MyService {
    @GET
    public Response getHW() {
        return Response.status(200).entity("Hello java conflicted!").build();
    }

    @GET
    @Path("/java")
    public Response getJavaHi(){
        return Response.status(200).entity("JAVA sub-path here!").build();
    }
}
