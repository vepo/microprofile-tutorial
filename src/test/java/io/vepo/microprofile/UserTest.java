package io.vepo.microprofile;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import io.restassured.http.ContentType;
import io.vepo.microprofile.model.User;

@RunWith(Arquillian.class)
@DefaultDeployment
public class UserTest {

	private MongoServer server;

	@Before
	public void setUp() {
		server = new MongoServer(new MemoryBackend());
		server.bind("localhost", 27017);
	}

	@After
	public void tearDown() {
		server.shutdown();
	}

	@Test
	@RunAsClient
	public void testNothing() {
		User user = new User();
		user.setEmail("victor.perticarrari@gmail.com");
		user.setPassword("1234");
		user.setUsername("vepo");
		given().contentType(ContentType.JSON).body(user).when().post("/user").then().statusCode(200)
				.contentType(ContentType.JSON).body("id", notNullValue()).body("username", equalTo("vepo"));

	}
}