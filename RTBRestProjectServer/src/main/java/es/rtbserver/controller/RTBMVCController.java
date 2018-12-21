package es.rtbserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import es.rtbserver.service.RTBMVCService;

@Controller
@RequestMapping("/rtbdispatcher")
public class RTBMVCController {
	
	private final Logger logger = LoggerFactory.getLogger(RTBMVCController.class);
	
	@Autowired
	RTBMVCService service;
	
	
	@RequestMapping(value = "/rtbnotcache/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> processNotCacheRTB(@PathVariable("id") int id){
		logger.info("[RTBDISPATCHER] -- RTB/(" + id + ") -- INI");
		long start_time = System.currentTimeMillis();
		String response = service.processService(id);	
		long stop_time = System.currentTimeMillis();
		stop_time = stop_time - start_time;
		logger.info("[RTBDISPATCHER] -- RTB/(" + id + ") time (" + stop_time + ") ms -- END");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/rtb/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> processRTB(@PathVariable("id") int id){
		logger.info("[RTBDISPATCHER] -- RTB/(" + id + ") -- INI");
		long start_time = System.currentTimeMillis();
		String response = service.processService(id);	
		long stop_time = System.currentTimeMillis();
		stop_time = stop_time - start_time;
		logger.info("[RTBDISPATCHER] -- RTB/(" + id + ") time (" + stop_time + ") ms -- END");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rtbasync/{id}", method = RequestMethod.GET, produces="application/json")
	public DeferredResult<ResponseEntity<String>> processRTBASYNC(@PathVariable("id") int id){
		logger.info("[RTBDISPATCHER] -- RTBASYNC/(" + id + ") -- INI");
		long start_time = System.currentTimeMillis();
		final DeferredResult<ResponseEntity<String>> dr = new DeferredResult<ResponseEntity<String>>();
		service.processAsyncService(id, dr);	
		long stop_time = System.currentTimeMillis();
		stop_time = stop_time - start_time;
		logger.info("[RTBDISPATCHER] -- RTBASYNC/(" + id + ") time (" + stop_time + ") ms -- END");
		return dr;
	}
	
	

	
	
}
