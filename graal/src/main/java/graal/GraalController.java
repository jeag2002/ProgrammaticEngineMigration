package graal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graal.service.KeenkaleRTBService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/rtbdispatcher/rtb")
public class GraalController {
	
	private static final String KEENKALE_CODE = "271";
	
	private static final Logger log = LoggerFactory.getLogger(GraalController.class);
	
	@Inject
	KeenkaleRTBService keenkaleRTB;
	
	GraalController() {}
	
	@Get("/{id}")
	Single<String> rtbController(String id) {
			
			log.info("[GraalController ("+Thread.currentThread().hashCode()+")] INI (" + id + ")");
			
			if (id.equalsIgnoreCase(KEENKALE_CODE)) {
				return Single.just(keenkaleRTB.processRTB());
			}else {
				return Single.just("Code (" + id + ") not found");
			}
	}
}
