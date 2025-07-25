package id.soc.rest;

import java.util.List;
import java.util.Optional;

import id.soc.dto.UserData;
import id.soc.entity.User;
import jakarta.ws.rs.Produces;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        List<User> users = User.listAll();
        return Response.ok(users).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserData userData) {
        User user = new User();

        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());

        user.persist();
        return Response.created(null).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, UserData userData) {
        Optional<User> userOptional = User.findByIdOptional(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());

            user.persist();

            return Response.ok("").build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        Optional<User> userOptional = User.findByIdOptional(id);

        if (userOptional.isPresent()) {
            userOptional.get().delete();
            return Response.ok().build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }
}
