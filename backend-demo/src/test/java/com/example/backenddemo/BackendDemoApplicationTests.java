package com.example.backenddemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendDemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void applicationContextTest() {
		BackendDemoApplication.main(new String[] {});
	}

}
