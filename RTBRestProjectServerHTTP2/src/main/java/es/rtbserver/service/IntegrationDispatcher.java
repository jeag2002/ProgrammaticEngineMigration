package es.rtbserver.service;

import es.rtbserver.service.integration.RTBIntegration;

public interface IntegrationDispatcher {
	
	public String processIntegration(int id);

}
