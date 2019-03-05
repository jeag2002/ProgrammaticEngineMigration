package es.rtbclient.simpleserver.template.console.others;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



import es.rtbclient.simpleserver.template.console.SimpleConsole;

public class SimpleConsoleBrainFuck extends SimpleConsole{

	private static final String URL_TEST = "http://localhost:3000";
	
	public SimpleConsoleBrainFuck() {
		super(URL_TEST);
	}
	
	public String processCall() throws Exception{
	    return super.processCall();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleConsoleBrainFuck sC = new SimpleConsoleBrainFuck();
		String result = sC.processCall();
		System.out.println("[RESULT] " +  result);
	}
	
	
}
