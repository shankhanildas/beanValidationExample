/**
 * 
 */
package org.nil.beanValidation.resources;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.jersey.validation.ValidationErrorMessage;
import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * @author nil
 *
 */
public class PersonValidatorResourceTest {
	
	Form personForm;

	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonValidatorResource())
            .build();
	
	@Test
	public void testPersonWithoutNameFails() {			
		personForm = new Form();
		personForm.param("name", "");
		personForm.param("age", "33");
		personForm.param("height", "5.11");
		personForm.param("address", "Bengaluru");
		personForm.param("hobbies", "badminton");
		personForm.param("hobbies", "photography");
		
		final Response putResp = resources.client().target("/validatedPerson")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.form(personForm));

	    assertThat(putResp.getStatus()).isEqualTo(400);

	    ValidationErrorMessage msg = putResp.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).allMatch(t -> t.indexOf("name cannot be empty") != -1);
	}
	
	@Test
	public void testPersonWithAgeBeyondRangeFails() {			
		personForm = new Form();
		personForm.param("name", "xyz");
		personForm.param("age", "136");
		personForm.param("height", "5.11");
		personForm.param("address", "Bengaluru");
		personForm.param("hobbies", "badminton");
		personForm.param("hobbies", "photography");
		
		final Response putResp = resources.client().target("/validatedPerson")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.form(personForm));

	    assertThat(putResp.getStatus()).isEqualTo(400);

	    ValidationErrorMessage msg = putResp.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).allMatch(t -> t.indexOf("age above 135 is not possible") != -1);
	}
	
	@Test
	public void testPersonWithInvalidHeightFails() {			
		personForm = new Form();
		personForm.param("name", "xyz");
		personForm.param("age", "33");
		personForm.param("height", "35");
		personForm.param("address", "Bengaluru");
		personForm.param("hobbies", "badminton");
		personForm.param("hobbies", "photography");
		
		final Response putResp = resources.client().target("/validatedPerson")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.form(personForm));

		assertThat(putResp.getStatus()).isEqualTo(400);

	    ValidationErrorMessage msg = putResp.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).allMatch(t -> t.indexOf("height more than 15 ft not allowed") != -1);	    
	}
	
	@Test
	public void testPersonWithManyHobbiesFails() {			
		personForm = new Form();
		personForm.param("name", "xyz");
		personForm.param("age", "33");
		personForm.param("height", "5.11");
		personForm.param("address", "Bengaluru");
		personForm.param("hobbies", "badminton");
		personForm.param("hobbies", "photography");
		personForm.param("hobbies", "trekking");
		personForm.param("hobbies", "swimming");
		
		
		final Response putResp = resources.client().target("/validatedPerson")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.form(personForm));

		assertThat(putResp.getStatus()).isEqualTo(400);

	    ValidationErrorMessage msg = putResp.readEntity(ValidationErrorMessage.class);
	    assertThat(msg.getErrors()).allMatch(t -> t.indexOf("more than 3 hobbies cannot be entered") != -1);	    
	}
	
	@Test
	public void testValidPerson() {			
		personForm = new Form();
		personForm.param("name", "xyz");
		personForm.param("age", "33");
		personForm.param("height", "5.11");
		personForm.param("address", "Bengaluru");
		personForm.param("hobbies", "badminton");
		personForm.param("hobbies", "photography");
		
		final Response putResp = resources.client().target("/validatedPerson")
				.request(MediaType.APPLICATION_FORM_URLENCODED)
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.form(personForm));

	    assertThat(putResp.getStatus()).isEqualTo(200);	    
	}

}
