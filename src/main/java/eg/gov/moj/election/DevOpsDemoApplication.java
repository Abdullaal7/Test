package eg.gov.moj.election;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DevOpsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevOpsDemoApplication.class, args);
	}

}
