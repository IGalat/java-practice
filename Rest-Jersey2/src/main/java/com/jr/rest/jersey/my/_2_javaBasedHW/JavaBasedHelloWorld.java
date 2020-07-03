package com.jr.rest.jersey.my._2_javaBasedHW;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * This class doesn't know about global config
 * <p/>
 * request in client to get response:
 * http://localhost:8080/java-practice/rest/jersey2/my/javahw/hello  -  http://{Serv IP}/{Project name}/{@ApplicationPath from config class}/{@Path of this class}
 * http://localhost:8080/java-practice/rest/jersey2/my/javahw/hello/SOME_PARAMETER     - to get response from method with "param"
 * <p/>
 * when deploying tomcat(with project) on idea, project name isn't being specified:
 * http://localhost:8080/rest/jersey2/my/javahw/hello
 *
 * @author Ilya Galatyuk
 * @see JavaBasedConfig
 */

//for this to be useful package must be described in JavaBasedConfig(AnyThat extends ResourceConfig) class, in 'packages'
//it's sub-path of what is in @ApplicationPath in JavaBasedConfig(AnyThat extends ResourceConfig)
@Path("/hello")
public class JavaBasedHelloWorld {

    @GET
    public Response getMessage() {
        return Response.status(200).entity("Java based config, NO param !1").build();
    }

    @GET
    @Path("/{param}")
    public Response getMessage(@PathParam("param") String message) {
        String output = "Java based config, param: " + message;
        return Response.status(200).entity(output).build();
    }
}
