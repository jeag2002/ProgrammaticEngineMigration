package es.rtbserver.config;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.undertow.UndertowOptions;

@Configuration
public class RTBServerConfig {
	
	 @Bean
	 UndertowServletWebServerFactory embeddedServletContainerFactory() {
		    UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
			factory.addBuilderCustomizers(
		 			builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
			return factory;
	 }
	
}
