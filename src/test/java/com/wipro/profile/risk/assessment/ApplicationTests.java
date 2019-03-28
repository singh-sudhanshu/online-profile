package com.wipro.profile.risk.assessment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	@Test
	public void contextLoads() {
		logger.debug("Application context loaded successfully");
	}
}
