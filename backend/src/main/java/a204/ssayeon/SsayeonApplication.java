package a204.ssayeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SsayeonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsayeonApplication.class, args);
	}
		
}
