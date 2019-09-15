package graal.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Primary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;

@Singleton
public class KeenkaleRTBService implements RTBService {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(KeenkaleRTBService.class);
	
	private static final String URL = "http://rtb-useast.keenkale.com";


	public String processRTB() {
		
		log.info("[KeenkaleRTBService ("+Thread.currentThread().hashCode()+")] INI");
		
		String response = "";
				
		try {
			
			HttpClient client = HttpClient.create(new java.net.URL(URL));
			
			String request = "{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}";

			Map<CharSequence, CharSequence> namesAndValues = new HashMap<CharSequence, CharSequence>();
			
			namesAndValues.put("Content-Type","application/json");
			namesAndValues.put("Connection","Keep-Alive");
			namesAndValues.put("Accept-Charset","UTF-8");
			namesAndValues.put("Expect","x-openrtb-version: 2.4");
			namesAndValues.put("x-forwarded-for","216.15.125.142");
			namesAndValues.put("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
			namesAndValues.put("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
			
			HttpResponse<String> resp = client.toBlocking().exchange(HttpRequest.POST("/rtb?zone=55764", request).headers(namesAndValues));
			
			response = "Status code (" + resp.code() + ") Body (" + (resp.body()==null?"<empty>":resp.body()) + ")";
			
			log.info("[KeenkaleRTBService ("+Thread.currentThread().hashCode()+")] response (" + response + ")");
			
			
			
		}catch(Exception e) {
			response = e.getMessage();
			log.warn("[KeenkaleRTBService ("+Thread.currentThread().hashCode()+")] error (" + response + ")");
		}
		
		
		log.info("[KeenkaleRTBService ("+Thread.currentThread().hashCode()+")] END");
		
		return response;
	}

}
