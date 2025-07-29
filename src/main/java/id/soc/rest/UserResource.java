package id.soc.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import id.soc.dto.user.CreateUserRequest;
import id.soc.dto.user.UpdateUserRequest;
import id.soc.dto.user.UserResponse;
import id.soc.entity.User;
import id.soc.repository.UserRepository;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        List<User> users = userRepository.listAll();

        List<UserResponse> userResponses = users.stream()
                .map(user -> new UserResponse(
                        user.id,
                        user.getName(),
                        user.getEmail(),
                        user.getDateOfBirth()))
                .collect(Collectors.toList());

        return Response.ok(userResponses).build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid CreateUserRequest userData) {
        User user = new User();

        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setDateOfBirth(userData.getDateOfBirth());

        userRepository.persist(user);
        return Response.created(null).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, @Valid UpdateUserRequest userData) {
        Optional<User> userOptional = userRepository.findByIdOptional(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            user.setDateOfBirth(userData.getDateOfBirth());

            userRepository.persist(user);

            return Response.ok("").build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        Optional<User> userOptional = userRepository.findByIdOptional(id);

        if (userOptional.isPresent()) {
            userOptional.get().delete();
            return Response.ok().build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/age")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserAge(@PathParam("id") Long id) {
        return userRepository.calculateAgeByUserId(id)
                .map(Response::ok)
                .orElse(Response.status(Status.NOT_FOUND))
                .build();
    }

}
