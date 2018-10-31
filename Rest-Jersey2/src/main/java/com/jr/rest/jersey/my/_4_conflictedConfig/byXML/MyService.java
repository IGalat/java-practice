package com.jr.rest.jersey.my._4_conflictedConfig.byXML;

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
        return Response.status(200).entity("Hello XML conflicted!").build();
    }

    @GET
    @Path("/xml")
    public Response getXMLHi(){
        return Response.status(200).entity("XML sub-path here!").build();
    }
}
