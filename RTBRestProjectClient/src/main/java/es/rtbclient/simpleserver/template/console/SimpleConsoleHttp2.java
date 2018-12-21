package es.rtbclient.simpleserver.template.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import es.rtbclient.simpleserver.template.ConsoleTemplate;

public class SimpleConsoleHttp2 implements ConsoleTemplate {
	
	private final Logger logger = LoggerFactory.getLogger(SimpleConsoleHttp2.class);
	
	public static final String DEFAULT_URL = "https://localhost:8443/rtbdispatcher/rtb/271";
	
	private String URL;
	
	public SimpleConsoleHttp2() {
		URL = DEFAULT_URL;
	}
	
	public SimpleConsoleHttp2(String _URL) {
		URL = _URL;
	}
	

	@Override
	public String processCall() throws Exception {
		String responseStr = "";
		
		System.setProperty("javax.net.ssl.trustStore","C:\\workspaces\\workEclipse\\RTBRestProjectClient\\src\\main\\resources\\sample.jks");
		System.setProperty("javax.net.ssl.trustStorePassword","secret");
		RestTemplate http2Template = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
		ResponseEntity<String> out = http2Template.getForEntity(URL, String.class);
		responseStr = out.getBody();
		
		
		return responseStr;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleConsoleHttp2 sC = new SimpleConsoleHttp2();
		String result = sC.processCall();
		System.out.println("[RESULT] " +  result);
	}

	
}
