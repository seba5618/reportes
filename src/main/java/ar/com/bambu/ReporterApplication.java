package ar.com.bambu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ReporterApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(ReporterApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ReporterApplication.class, args);
		LOGGER.info("Socotrecu version 1.0.14.");
	}

}
