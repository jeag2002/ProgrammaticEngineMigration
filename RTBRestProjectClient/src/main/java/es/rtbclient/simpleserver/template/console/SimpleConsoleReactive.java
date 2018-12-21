package es.rtbclient.simpleserver.template.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import es.rtbclient.simpleserver.template.ConsoleTemplate;

public class SimpleConsoleReactive implements ConsoleTemplate {
	
	
	private final Logger logger = LoggerFactory.getLogger(SimpleConsoleReactive.class);
	public static final String DEFAULT_URL = "http://localhost:8081/rtbservereactive/271";
	
	private String URL;
	
	public SimpleConsoleReactive() {
		URL = DEFAULT_URL;
	}
	
	public SimpleConsoleReactive(String _URL) {
		URL = _URL;
	}
	
	@Override
	public String processCall() throws Exception {
		String result = "";
		WebClient webClient = WebClient.create(URL);
		/*
		 webClient.get().retrieve().bodyToMono(String.class).subscribe(SimpleConsoleReactive::handleResponse);
		  Thread.sleep(1000);
		  System.out.println("Data");
		 */
		result = webClient.get().retrieve().bodyToMono(String.class).block();
		return result;
	}
	
	private static void handleResponse(String s) {
		System.out.println(s);
	}
	
	

	public static void main(String[] args) throws Exception{
		SimpleConsoleReactive sCR = new SimpleConsoleReactive();
		String result = sCR.processCall();
		System.out.println("[RESULT] " +  result);
	}

	

}
