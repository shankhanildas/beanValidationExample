package org.nil.beanValidation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class BeanValidationExampleConfiguration extends Configuration {
	@NotNull
	@Valid
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

	@JsonProperty("swagger")
	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		return swaggerBundleConfiguration;
	}
}
