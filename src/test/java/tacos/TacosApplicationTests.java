package tacos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tacos.web.DesignTacoController;

@SpringBootTest
class TacosApplicationTests {

	@Autowired
	DesignTacoController tacoController; // any bean that should be present in the Application Context

	@Test
	void contextLoads() {
		Assertions.assertNotNull(tacoController);
	}

}
