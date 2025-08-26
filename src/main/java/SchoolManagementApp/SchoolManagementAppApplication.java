package SchoolManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SchoolManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementAppApplication.class, args);
	}

}
