package es.rtbclient.simpleserver.template.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import es.rtbclient.simpleserver.template.ConsoleTemplate;

public class SimpleConsoleNoCache  implements ConsoleTemplate{

private final Logger logger = LoggerFactory.getLogger(SimpleConsole.class);
	
	public static final String DEFAULT_URL = "http://localhost:8082/rtbdispatcher/rtbnotcache/271";
	
	private String URL;
	
	public SimpleConsoleNoCache() {
		URL = DEFAULT_URL;
	}
	
	public SimpleConsoleNoCache(String _URL) {
		URL = _URL;
	}
	
	
	public static void main(String[] args) throws Exception{
		SimpleConsoleNoCache sC = new SimpleConsoleNoCache();
		String result = sC.processCall();
		System.out.println("[RESULT] " +  result);

	}

	@Override
	public String processCall() throws Exception {
		String responseStr = "";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> out = restTemplate.getForEntity(URL, String.class);
		responseStr = out.getBody();
		return responseStr;
	}

}
