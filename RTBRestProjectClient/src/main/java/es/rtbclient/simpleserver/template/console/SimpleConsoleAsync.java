package es.rtbclient.simpleserver.template.console;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import es.rtbclient.simpleserver.template.ConsoleTemplate;
import es.rtbclient.simpleserver.thread.TaskThread;



public class SimpleConsoleAsync implements ConsoleTemplate {
	
	
	private final Logger logger = LoggerFactory.getLogger(SimpleConsoleAsync.class);
	
	public static final String DEFAULT_URL = "http://localhost:8082/rtbdispatcher/rtbasync/271";
	
	private String URL;
	
	public SimpleConsoleAsync() {
		URL = DEFAULT_URL;
	}
	
	public SimpleConsoleAsync(String _URL) {
		URL = _URL;
	}
	
	@Override
	public String processCall() throws Exception{
		String responseStr = "";
		String responseStatus = "";
		
		AsyncRestTemplate asycTemp = new AsyncRestTemplate();
		HttpMethod method = HttpMethod.GET;
		Class<String> responseType = String.class;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);
		ListenableFuture<ResponseEntity<String>> future = asycTemp.exchange(URL, method, requestEntity, responseType);
		try {
			//waits for the result
			ResponseEntity<String> entity = future.get();
			//prints body source code for the given URL
			responseStatus = String.valueOf(entity.getStatusCodeValue());
			responseStr = entity.getBody();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return responseStatus + "#" + responseStr;
	}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleConsoleAsync sCA = new SimpleConsoleAsync();
		String result = sCA.processCall();
		System.out.println("[RESULT] " +  result);

	}

}
