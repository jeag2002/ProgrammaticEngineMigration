package es.rtbserver.service.impl;

import es.rtbserver.service.IntegrationDispatcher;
import es.rtbserver.service.RTBMVCService;
import es.rtbserver.service.integration.RTBIntegration;
import es.rtbserver.service.integration.inner.impl.DefaultRTBIntegration;
import es.rtbserver.service.integration.keenkale.impl.KeenkaleRTBIntegration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import es.rtbserver.constants.*;

@Service
public class RTBMVCServiceImpl implements RTBMVCService {
	
	@Autowired
	IntegrationDispatcher iD;
	
	private final Logger logger = LoggerFactory.getLogger(RTBMVCServiceImpl.class);

	private static final ExecutorService ex = Executors.newCachedThreadPool();
	
	@Override
	public String processNotCacheService(int id) {
		logger.info("[RTBMVCServiceNotCacheSYNC] -- process integration (" + id + ")");
		return iD.processIntegrationNoCache(id);
	}
	
	
	@Override
	public String processService(int id) {
		logger.info("[RTBMVCServiceSYNC] -- process integration (" + id + ")");
		return iD.processIntegration(id);
	}
	

	
	@Override
	public void processAsyncService(int id, DeferredResult<ResponseEntity<String>> dr) {
		logger.info("[RTBMVCServiceASYNC] -- process integration (" + id + ")");
		try {
		CompletableFuture.supplyAsync(()->{return iD.processIntegration(id);},ex).thenAccept((String response) ->{dr.setResult(new ResponseEntity<String>(response,HttpStatus.OK));});
		}catch(Exception e) {
			logger.error("[RTBMVCServiceASYNC] ERROR " +  e.getMessage(),e);
		}	
	}



	

}
