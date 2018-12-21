package es.rtbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//https://www.callicoder.com/configuring-spring-boot-application/

@SpringBootApplication
@EnableCaching
public class RTBMainServer {
	public static void main(String[] args) {
        SpringApplication.run(RTBMainServer.class, args);
    }
}
