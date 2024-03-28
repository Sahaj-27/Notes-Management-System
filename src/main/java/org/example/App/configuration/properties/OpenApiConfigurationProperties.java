package org.example.App.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "org.example.app.swagger")
public class OpenApiConfigurationProperties {

	private Properties properties = new Properties();

	@Data
	public static class Properties {
		private String title;
		private String description;
		private String apiVersion;

		private Contact contact = new Contact();
		private Security security = new Security();

		@Data
		public static class Contact {
			private String email;
			private String name;
			private String url;
		}

		@Data
		public static class Security {
			private String name;
			private String scheme;
			private String bearerFormat;
		}
	}

}