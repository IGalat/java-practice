package com.jr.rest.jersey.tutorialsPoint._1_HWWithResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Ilya Galatyuk
 */

@Path("/UserService")
public class UserService {
    UserDao userDao = new UserDao();

    //returns naked XML, no HTTP wrap. "User" class must be @XmlRootElement, or 500 "internal server error". List<String> as response would give 500 error
    @GET
    @Path("/users")
    // @Produces and @Consumes specify MIME-types that are returned/taken. Why can't I change XML for JSON here without exception?..
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers(){
        return userDao.getAllUsers();
    }

}
