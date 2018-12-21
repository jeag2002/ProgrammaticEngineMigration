package es.rtbservereactive.component;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import es.rtbservereactive.service.IntegrationDispatcher;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;

@Component
public class RTBClientHandler {
	
	@Autowired
	IntegrationDispatcher iD;
	
	public Mono<ServerResponse> processRTB(ServerRequest request) {
		int personId = Integer.valueOf(request.pathVariable("id"));
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject(iD.processIntegration(personId)));
	}
	
	
}
