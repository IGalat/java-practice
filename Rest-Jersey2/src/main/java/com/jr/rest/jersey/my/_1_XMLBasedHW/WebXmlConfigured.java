package com.jr.rest.jersey.my._1_XMLBasedHW;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * This class doesn't know about global config in web.xml, no link to it from here
 * <p/>
 * request in browser/com.jr.service to get response:
 * http://{Server IP:port}/{Project name}/{Path, described in web.xml}/{@Path of this class}    in our case:
 * http://localhost:8080/HomePractice/rest/jersey2/my/xmlhw/hello                         - for accessing class in general. for param method:
 * http://localhost:8080/HomePractice/rest/jersey2/my/xmlhw/hello/{any param here}      example:
 * http://localhost:8080/HomePractice/rest/jersey2/my/xmlhw/hello/myInput
 * <p/>
 * when deploying tomcat(with project) on idea, project name isn't being specified:
 * http://localhost:8080/rest/jersey2/my/xmlhw/hello
 * <p/>
 * For REST com.jr.service you need at least 1 'global' config(java-based or web.xml), that maps packages to path,
 * in case of XML config <init-param> is also necessary
 *
 * @author Ilya Galatyuk
 */

//for this to be useful package must be described <param-value>this.package</param-value>  in web-xml <init-param>
//it's sub-path of what is in <servlet-mapping> in web.xml
@Path("/hello")
public class WebXmlConfigured {
    //no additional path here, so this will respond to "GET .../hello"  request
    @GET
    public Response getLalaMessage() {
        // 200 is HTTP response code "OK"
        return Response.status(200).entity("Lala XML configured!").build();
    }

    //this will respond to "GET .../hello/SOMETHING"
    @GET
    @Path("/{parameter}")
    // , and SOMETHING will be used thanks to @PathParam
    public Response getParametrisedMessage(@PathParam("parameter") String param) {
        String output = "Parametrised XML configured, parameter: " + param;
        return Response.status(200).entity(output).build();
    }

}
