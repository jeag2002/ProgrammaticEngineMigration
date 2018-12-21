package es.rtbserver.service;

import es.rtbserver.service.integration.RTBIntegration;

public interface IntegrationDispatcher {
	
	
	public String processIntegrationNoCache(int id);
	public String processIntegration(int id);

}
