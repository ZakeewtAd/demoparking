package be.matthieu.demoparking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("local")
@SpringBootTest
class DemoParkingApplicationTests {

	@Test
	void contextLoads() {
	}

}
