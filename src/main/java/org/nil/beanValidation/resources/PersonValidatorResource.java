package org.nil.beanValidation.resources;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.nil.beanValidation.core.Person;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("")
@Api(value="Person Validator API")
public class PersonValidatorResource {

	@PUT
	@Path("/validatedPerson")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses({
		@ApiResponse(code=HttpStatus.OK_200, message="Valid person data"),
		@ApiResponse(code=HttpStatus.BAD_REQUEST_400, message="Invalid person data")
	})
	public Response validatePerson(@Valid @ApiParam(name="person", value="Person form data") @BeanParam Person person) {
		return Response.status(HttpStatus.OK_200).entity(person).build();
	}
	
}
