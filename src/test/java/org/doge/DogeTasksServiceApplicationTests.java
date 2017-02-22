package org.doge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogeTasksServiceApplicationTests {

    @Autowired
    private WebApplicationContext context;

	@Test
	public void contextLoads() {
		Assert.notNull(context);
    }

}
