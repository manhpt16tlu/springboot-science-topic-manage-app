package sokhoahoccongnghe.phutho.gov.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import sokhoahoccongnghe.phutho.gov.vn.entity.Organ;

@SpringBootApplication
@EnableScheduling
public class SpringbootScienceTopicManageAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScienceTopicManageAppApplication.class, args);
	}

}
