package io.vepo.microprofile.controllers;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import io.vepo.microprofile.model.User;
import io.vepo.microprofile.model.UserRepository;

@ApplicationScoped
@Path("/user")
public class UserController {

	@Inject
	@Database(DatabaseType.DOCUMENT)
	private UserRepository userRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		return Collections.emptyList();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User newUser) {
		return userRepository.save(newUser);
	}
}