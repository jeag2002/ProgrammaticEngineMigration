package es.rtbclient.simpleserver.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseDataBean {
	

	private int numCall;
	private int numIt;
	private int responseCode;
	private String response;
	private String console;
	private long time;
	
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getNumCall() {
		return numCall;
	}
	
	public void setNumCall(int numCall) {
		this.numCall = numCall;
	}
	
	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public int getNumIt() {
		return numIt;
	}
	
	public void setNumIt(int numIt) {
		this.numIt = numIt;
	}
	
	public String getResponse() {
		return response;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sDF.format(new Date()) + ";" + console + ";" + numIt + ";" + numCall + ";" + response + ";" + responseCode + ";"+ time;
	}

}
