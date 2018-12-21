package es.rtbserver.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import es.rtbserver.constants.Constants;
import es.rtbserver.service.IntegrationDispatcher;
import es.rtbserver.service.integration.inner.impl.DefaultRTBIntegration;
import es.rtbserver.service.integration.keenkale.impl.KeenkaleRTBIntegration;


@Service
public class IntegrationDispatcherImpl implements IntegrationDispatcher {

	@Override
	@Cacheable(value="queryCache",key="#id") 
	public String processIntegration(int id) {
		if (id == Constants.KEENKALE_ID) {
			return (new KeenkaleRTBIntegration()).rtbClientProcess();
		}else {
			return (new DefaultRTBIntegration()).rtbClientProcess();
		}
		
	}
	
	@Scheduled(fixedRate = 10000)
	@CacheEvict(value = "queryCache" , allEntries = true)
	public void clearCache() {}
	

}
