package es.rtbservereactive.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;


import org.springframework.web.reactive.function.server.ServerResponse;

import es.rtbservereactive.component.RTBClientHandler;


//https://dzone.com/articles/creating-multiple-routerfunctions-in-spring-webflux

@Configuration
public class RTBRestRouter {
	
	@Bean
	public RouterFunction<ServerResponse> route(RTBClientHandler rtbClientHandler){
		return RouterFunctions.route(GET("/rtbservereactive/{id}").and(accept(MediaType.APPLICATION_JSON)), rtbClientHandler:: processRTB);
	}

}
