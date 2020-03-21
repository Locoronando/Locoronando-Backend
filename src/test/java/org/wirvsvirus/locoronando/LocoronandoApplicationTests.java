package org.wirvsvirus.locoronando;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wirvsvirus.locoronando.dealer.DealerService;

@SpringBootTest
class LocoronandoApplicationTests {

  @Autowired
  DealerService dealerService;

	@Test
	void contextLoads() {

	}

}
