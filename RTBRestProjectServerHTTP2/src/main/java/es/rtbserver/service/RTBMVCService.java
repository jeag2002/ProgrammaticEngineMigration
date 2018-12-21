package es.rtbserver.service;

import org.springframework.web.context.request.async.DeferredResult;


public interface RTBMVCService {
	
	public String processService(int id);
	public void processAsyncService(int id, DeferredResult<org.springframework.http.ResponseEntity<String>> dr);

}
