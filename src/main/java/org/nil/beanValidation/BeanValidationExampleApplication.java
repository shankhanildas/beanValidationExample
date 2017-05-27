package org.nil.beanValidation;

import org.nil.beanValidation.resources.PersonValidatorResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class BeanValidationExampleApplication extends Application<BeanValidationExampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BeanValidationExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "BeanValidationExample";
    }

    @Override
    public void initialize(final Bootstrap<BeanValidationExampleConfiguration> bootstrap) {
		bootstrap.addBundle(new SwaggerBundle<BeanValidationExampleConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
					BeanValidationExampleConfiguration conf) {
				return conf.getSwaggerBundleConfiguration();
			}
		});
    }

    @Override
    public void run(final BeanValidationExampleConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new PersonValidatorResource());
    }

}
