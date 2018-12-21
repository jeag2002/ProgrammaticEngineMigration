package es.rtbservereactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RTBMainApp {
	
	public static void main(String[] args) {
        SpringApplication.run(RTBMainApp.class, args);
    }

}
