package org.example.App.security.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "org.example.app")
@Data
public class JwtConfiguration {

	private Configuration jwt = new Configuration();

	@Data
	public static class Configuration {
		private String secretKey;
	}

}