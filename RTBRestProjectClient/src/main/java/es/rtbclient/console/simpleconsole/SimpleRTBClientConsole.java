package es.rtbclient.console.simpleconsole;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.rtbclient.console.simpleconsole.response.bean.App;
import es.rtbclient.console.simpleconsole.response.bean.Banner;
import es.rtbclient.console.simpleconsole.response.bean.Device;
import es.rtbclient.console.simpleconsole.response.bean.Geo;
import es.rtbclient.console.simpleconsole.response.bean.Geo_;
import es.rtbclient.console.simpleconsole.response.bean.Imp;
import es.rtbclient.console.simpleconsole.response.bean.Publisher;
import es.rtbclient.console.simpleconsole.response.bean.ResponseBean;
import es.rtbclient.console.simpleconsole.response.bean.User;

public class SimpleRTBClientConsole {
	
	private static final String URL = "http://rtb-useast.keenkale.com/rtb?zone=55764";
	//private static final String URL = "http://rtb-eu.keenkale.com/rtb?zone=55764";
	
	/*
	 "Content-Type: application:json",
	 "Connection: Close",
	 "Accept-Charset: utf-8",
	 "Expect:","x-openrtb-version: 2.3",
	 "x-forwarded-for: 88.1.48.62",
	 "x-device-user-agent: Mozilla:5.0 (Windows NT 10.0; Win64; x64) AppleWebKit:537.36 (KHTML, like Gecko) Chrome:70.0.3538.110 Safari:537.36","x-original-user-agent: Mozilla:5.0 (Windows NT 10.0; Win64; x64) AppleWebKit:537.36 (KHTML, like Gecko) Chrome:70.0.3538.110 Safari:537.36"
	 */
	
	/*
	 $this->m_swapip_arr["tip"] = "216.15.125.142";
	 $this->m_swapip_arr["lat"] = 42.3424;
	 $this->m_swapip_arr["lon"] = -71.0878;
     $this->m_swapip_arr["tcc"] = "US";
	 */
	
	
	
	public static void main(String[] args) throws Exception{
		
		//http://www.uswebproxy.com/
		//System.setProperty("http.proxyHost", value);
		//System.setProperty("http.proxyPort", value);
		
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		List<MediaType> arr = new ArrayList<MediaType>();
		headers.setConnection("Close");
		List<Charset> charsets = new ArrayList<Charset>();
		charsets.add(Charset.forName("UTF-8"));
		headers.setAcceptCharset(charsets);
		headers.set("Expect", "x-openrtb-version: 2.4");
		//headers.set("x-forwarded-for","88.1.48.62");
		headers.set("x-forwarded-for","216.15.125.142");
		headers.set("x-device-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		headers.set("x-original-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
	
		
		ResponseBean rBean = new ResponseBean();
		
		rBean.setId("dca01512ae9ce5b95712794dcd677d80");
		rBean.setAt(1L);
		rBean.setTest(0L);
		rBean.setTmax(1500L);
		List<Object> data = new ArrayList<Object>();
		rBean.setBadv(data);
		
		Imp imp = new Imp();
		imp.setId("6bbc5bc3eb7081e1b7f4f7cc29b815f9");
		imp.setInstl(0L);
		imp.setTagid("tx500674895");
		imp.setBidfloor(0.052500000000000005);
		imp.setBidfloorcur("USD");
		imp.setSecure(1L);
		
		Banner banner = new Banner();  
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
		
		Device device = new Device();
		
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
		
		Geo geo = new Geo();
		//geo.setCountry("ESP");
		//geo.setLat(41.427564);
		//geo.setLon(2.185005);
		//geo.setType(1L);
		
		geo.setCountry("USA");
		geo.setLat(42.3424);
		geo.setLon(-71.0878);
		geo.setType(1L);
		
		
		device.setGeo(geo);
		
		App app = new App();
		
		app.setBundle("com.secondlemon.whatsdogpremium");
		app.setId("10308");
		app.setName("Whatsdog");
		app.setStoreurl("https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium");
		
		Publisher pub = new Publisher();
		pub.setId("67fbd985230c665850075df702d12c5e");
		pub.setName("tappx");
		pub.setDomain("tappx.com");
		app.setPublisher(pub);
		
		User user = new User();
		
		Geo_ geo_1 = new Geo_();
		
		//geo_1.setCountry("ESP");
		//geo_1.setLat(41.427564);
		//geo_1.setLon(2.185005);
		//geo_1.setType(1L);
		
		geo_1.setCountry("USA");
		geo_1.setLat(42.3424);
		geo_1.setLon(-71.0878);
		geo_1.setType(1L);
		
		user.setGeo(geo_1);
		
		
		List<Imp> impLst = new ArrayList<Imp>();
		impLst.add(imp);
		rBean.setImp(impLst);
		
		rBean.setDevice(device);
		rBean.setApp(app);
		rBean.setUser(user);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(rBean);
		
		System.out.println("ResponseBean (" + json + ")");
		
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		
		ResponseEntity<String> response = rest.exchange(URL, HttpMethod.POST, entity, String.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Status: (" + response.getStatusCode() + ") \r\n Body: (" + response.getBody() + ")");
		}else {
			System.out.println("Status: (" + response.getStatusCode() + ")");
		}
		
		
	}
	
	/*
	 {
  "id": "dca01512ae9ce5b95712794dcd677d80",
  "imp": [
    {
      "id": "6bbc5bc3eb7081e1b7f4f7cc29b815f9",
      "instl": 0,
      "tagid": "tx500674895",
      "bidfloor": 0.052500000000000005,
      "bidfloorcur": "USD",
      "secure": 1,
      "banner": {
        "id": "d06ca4a034793852b24a6bbc66e14690",
        "w": 320,
        "h": 50,
        "mimes": [
          "image\/jpg",
          "image\/gif"
        ],
        "battr": [
          
        ],
        "wmax": 320,
        "hmax": 50,
        "wmin": 300,
        "hmin": 49,
        "api": [
          3,
          5
        ]
      }
    }
  ],
  "device": {
    "ua": "Mozilla\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\/537.36 (KHTML, like Gecko) Chrome\/70.0.3538.110 Safari\/537.36",
    "geo": {
      "country": "ESP",
      "lat": 41.427564,
      "lon": 2.185005,
      "type": 1
    },
    "dnt": 0,
    "lmt": 0,
    "ip": "88.1.48.62",
    "devicetype": 4,
    "make": "Samsung",
    "model": "gt-9300",
    "os": "Android",
    "osv": "4.4.4",
    "ifa": "1a58da58-4930-4adc-b1a4-2dc1ba386a96",
    "connectiontype": 2,
    "js": 1,
    "language": "es"
  },
  "test": 0,
  "at": 1,
  "tmax": 1500,
  "badv": [
    
  ],
  "app": {
    "id": "10308",
    "name": "Whatsdog",
    "publisher": {
      "id": "67fbd985230c665850075df702d12c5e",
      "name": "tappx",
      "domain": "tappx.com"
    },
    "bundle": "com.secondlemon.whatsdogpremium",
    "storeurl": "https:\/\/play.google.com\/store\/apps\/details?id=com.secondlemon.whatsdogpremium"
  },
  "user": {
    "geo": {
      "country": "ESP",
      "lat": 41.427564,
      "lon": 2.185005,
      "type": 1
    }
  }
} 
	 
	 */
	
	
	
	
	/*
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		RequestBean rB = new RequestBean();
		rB.setId("a9ba9d899dc2eddcc389f90634e8382e");
		rB.setCur("USD");
		
		Seatbid sB = new Seatbid();
		
		List<Bid> bids = new ArrayList<Bid>();
		
		Bid bid = new Bid();
		bid.setAdid("b91755");
		bid.setAdm("\n\n");
		bid.setCid("c59223");
		bid.setCrid("b91755");
		bid.setH(250L);
		bid.setW(300L);
		bid.setPrice(0.15000005424869656);
		bid.setId("ytyjOoPZW8U_0");
		bid.setImpid("527d8051d92fd6f7971a909989c7718f");
		
		bids.add(bid);
		
		sB.setSeat("a79263");
		sB.setBid(bids);
			
		
		List<Seatbid> lists = new ArrayList<Seatbid>();
		lists.add(sB);
		
		
		rB.setSeatbid(lists);
		
		RestTemplate rest = new RestTemplate();
		String response = rest.postForObject(URL, rB, String.class);
		
		System.out.println(response);
	}
	*/
}
