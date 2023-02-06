package address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AddressbookApplication {
	private static final Logger log = LoggerFactory.getLogger(AddressbookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AddressbookApplication.class, args);
	}


}
