package id.soc.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import id.soc.dto.UserData;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    private Map<String, UserData> users = new HashMap<>();

    @GET
    @Path("/all")
    public Response getAllUsers() {
        return Response.ok(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserData user) {
        users.put(user.getEmail(), user);
        return Response.created(URI.create("users/" + user.getEmail())).build();
    }

    @GET
    @Path("{email}")
    public Response getUser(String email) {
        UserData user = users.get(email);
        if (Objects.isNull(user)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }
}
