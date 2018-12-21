package es.rtbservereactive.service.integration.keenkale.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.rtbservereactive.service.integration.RTBIntegration;
import es.rtbservereactive.service.integration.keenkale.bean.ResponseBean;

public class KeenkaleRTBIntegration extends RTBIntegration {
	
	private static final String URL = "http://rtb-useast.keenkale.com/rtb?zone=55764";
	
	private final Logger logger = LoggerFactory.getLogger(KeenkaleRTBIntegration.class);

	@Override
	public String rtbClientProcess() {
		
		String response = "";
		
		try {
			
	        //HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	        //httpRequestFactory.setConnectionRequestTimeout(1000);
	        //httpRequestFactory.setConnectTimeout(1000);
	        //httpRequestFactory.setReadTimeout(1000);
			
		
			RestTemplate rest = new RestTemplate();
			
			((SimpleClientHttpRequestFactory)rest.getRequestFactory()).setConnectTimeout(1000);
			((SimpleClientHttpRequestFactory)rest.getRequestFactory()).setReadTimeout(1000);
			
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			
			List<MediaType> arr = new ArrayList<MediaType>();
			headers.setConnection("Keep-Alive");
			List<Charset> charsets = new ArrayList<Charset>();
			charsets.add(Charset.forName("UTF-8"));
			headers.setAcceptCharset(charsets);
			headers.set("Expect", "x-openrtb-version: 2.4");
			//headers.set("x-forwarded-for","88.1.48.62");
			headers.set("x-forwarded-for","216.15.125.142");
			headers.set("x-device-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
			headers.set("x-original-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		
			ObjectMapper mapper = new ObjectMapper();
			ResponseBean rBean = processBean();
			String json = mapper.writeValueAsString(rBean);
			
			logger.info("[KEENKALE INTEGRATION] PRE HEADER \r\n [" +  headers.toString() + "]");
			logger.info("[KEENKALE INTEGRATION] PRE BODY \r\n[" + json + "]");
			
			HttpEntity<String> entity = new HttpEntity<String>(json,headers);
			ResponseEntity<String> resp = rest.exchange(URL, HttpMethod.POST, entity, String.class);
			
			if (resp.getStatusCode() == HttpStatus.OK) {
				logger.info("[KEENKALE INTEGRATION] POST Status: (" + resp.getStatusCode() + ") \r\n Body: (" + resp.getBody() + ")");
				response = resp.getBody();
			}else {
				logger.info("[KEENKALE INTEGRATION] POST Status: (" + resp.getStatusCode() + ")");
				response = "status : " + resp.getStatusCodeValue() + " body (" + (resp.getBody()==null?"<empty>":resp.getBody()) + ")";
				//response = String.valueOf(resp.getStatusCodeValue());
			}
			
		}catch(Exception e) {
			
			logger.warn("[KEENKALE INTEGRATION] ERROR (" + e.getMessage() + ")");
			response = "KEENKALE ERROR " + e.getMessage();
			
		}finally{
			return response;
		}
		
		
	}
	
	
	
	private ResponseBean processBean() {
		
		ResponseBean rBean = new ResponseBean();
		
		rBean.setId("dca01512ae9ce5b95712794dcd677d80");
		rBean.setAt(1L);
		rBean.setTest(0L);
		rBean.setTmax(1500L);
		List<Object> data = new ArrayList<Object>();
		rBean.setBadv(data);
		
		es.rtbservereactive.service.integration.keenkale.bean.Imp imp = new es.rtbservereactive.service.integration.keenkale.bean.Imp();
		imp.setId("6bbc5bc3eb7081e1b7f4f7cc29b815f9");
		imp.setInstl(0L);
		imp.setTagid("tx500674895");
		imp.setBidfloor(0.052500000000000005);
		imp.setBidfloorcur("USD");
		imp.setSecure(1L);
		
		es.rtbservereactive.service.integration.keenkale.bean.Banner banner = new es.rtbservereactive.service.integration.keenkale.bean.Banner();  
		banner.setId("d06ca4a034793852b24a6bbc66e14690");
		banner.setH(50L);
		banner.setW(320L);
		banner.setWmax(320L);
		banner.setHmax(50L);
		banner.setWmin(300L);
		banner.setHmin(49L);
		List<Long> apis = new ArrayList<Long>();
		apis.add(3L);
		apis.add(5L);
		banner.setApi(apis);
		List<String> mimes = new ArrayList<String>();
		mimes.add("image/jpg");
		mimes.add("image/gif");
		banner.setMimes(mimes);
		List<Object> battr = new ArrayList<Object>();
		banner.setBattr(battr);
		
		imp.setBanner(banner);
		
		es.rtbservereactive.service.integration.keenkale.bean.Device device = new es.rtbservereactive.service.integration.keenkale.bean.Device();
		
		device.setUa("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		device.setDnt(0L);
		device.setLmt(0L);
		//device.setIp("88.1.48.62");
		device.setIp("216.15.125.142");
		device.setDevicetype(4L);
		device.setMake("Samsung");
		device.setModel("gt-9300");
		device.setOs("Android");
		device.setOsv("4.4.4");
		device.setIfa("1a58da58-4930-4adc-b1a4-2dc1ba386a96");
		device.setConnectiontype(2L);
		device.setJs(1L);
		//device.setLanguage("es");
		device.setLanguage("en");
		
		es.rtbservereactive.service.integration.keenkale.bean.Geo geo = new es.rtbservereactive.service.integration.keenkale.bean.Geo();
		//geo.setCountry("ESP");
		//geo.setLat(41.427564);
		//geo.setLon(2.185005);
		//geo.setType(1L);
		
		geo.setCountry("USA");
		geo.setLat(42.3424);
		geo.setLon(-71.0878);
		geo.setType(1L);
		
		
		device.setGeo(geo);
		
		es.rtbservereactive.service.integration.keenkale.bean.App app = new es.rtbservereactive.service.integration.keenkale.bean.App();
		
		app.setBundle("com.secondlemon.whatsdogpremium");
		app.setId("10308");
		app.setName("Whatsdog");
		app.setStoreurl("https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium");
		
		es.rtbservereactive.service.integration.keenkale.bean.Publisher pub = new es.rtbservereactive.service.integration.keenkale.bean.Publisher();
		pub.setId("67fbd985230c665850075df702d12c5e");
		pub.setName("tappx");
		pub.setDomain("tappx.com");
		app.setPublisher(pub);
		
		es.rtbservereactive.service.integration.keenkale.bean.User user = new es.rtbservereactive.service.integration.keenkale.bean.User();
		
		es.rtbservereactive.service.integration.keenkale.bean.Geo_ geo_1 = new es.rtbservereactive.service.integration.keenkale.bean.Geo_();
		
		//geo_1.setCountry("ESP");
		//geo_1.setLat(41.427564);
		//geo_1.setLon(2.185005);
		//geo_1.setType(1L);
		
		geo_1.setCountry("USA");
		geo_1.setLat(42.3424);
		geo_1.setLon(-71.0878);
		geo_1.setType(1L);
		
		user.setGeo(geo_1);
		
		
		List<es.rtbservereactive.service.integration.keenkale.bean.Imp> impLst = new ArrayList<es.rtbservereactive.service.integration.keenkale.bean.Imp>();
		impLst.add(imp);
		rBean.setImp(impLst);
		
		rBean.setDevice(device);
		rBean.setApp(app);
		rBean.setUser(user);
		
		
		
		
		return rBean;
	}
	
	

}
