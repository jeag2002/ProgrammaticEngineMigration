package es.rtbclient.simpleserver.template.console.others;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import es.rtbclient.simpleserver.template.console.SimpleConsole;

public class SimpleConsoleCSharp extends SimpleConsole {
	
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
	
	
	public SimpleConsoleCSharp() {
		
		
		super();
		
	}
	
	public String processCall() throws Exception{
		
		System.setProperty("java.net.preferIPv6Addresses", "true");
		
		String responseStr = "";
		java.net.URL urlP = new java.net.URL(super.DEFAULT_URL);
		HttpURLConnection connectionP = (HttpURLConnection) urlP.openConnection();
		connectionP.setReadTimeout(15*1000);
		connectionP.setConnectTimeout(15*1000);
		connectionP.setRequestMethod("GET");
		connectionP.setRequestProperty("User-Agent", USER_AGENT);
		connectionP.setDoInput(true);
		 
		int responseCode = connectionP.getResponseCode();
		 
		BufferedReader in = new BufferedReader( new InputStreamReader(connectionP.getInputStream()));
		String inputLine = "";
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
		}
		in.close();
		 
		 
		responseStr = response.toString();
		return responseCode +"#"+ responseStr;
	}


}
