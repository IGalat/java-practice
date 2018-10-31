package com.jr.rest.jersey.my._3_doubleConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Package is configured in both web.xml and java to the same path. They conflict, and Tomcat warns, but
 * no effect because they're similar. XML wins the conflict(see part _4_)
 * <p/>
 * Also, test to see how additional '/paths' are resolved. Result:
 * First are defined more specific, then less specific, even if less specific response method is above in class
 * e.g. '/test1' and '/test2', if not them -> '/{any param}'
 *
 * @author Ilya Galatyuk
 */

@Path("/hello")
public class ResponderConfigured {
    @GET
    public Response getHW() {
        return Response.status(200).entity("Hello double config!").build();
    }

    @GET
    @Path("/test1")
    public Response getTest1() {
        return Response.status(200).entity("Hello test1!").build();
    }

    @GET
    @Path("/{anyParam}")
    public Response getParametrized(@PathParam("anyParam") String input) {
        return Response.status(200).entity("Yo! param : " + input).build();
    }

    @GET
    @Path("/test2")
    public Response getTest2() {
        return Response.status(200).entity("Hello test2!").build();
    }


    @GET
    @Path("/song")
    public Response getList() {
        List<String> list = new ArrayList<String>();
        list.add("Never gonna");
        list.add("give you up!");
        return Response.status(200).entity(list).build();
    }
}
