package com.marmitech.Marmitech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
<<<<<<< HEAD
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
//@TestPropertySource(locations = "classpth:aplication.properties")
=======

@SpringBootTest
@ActiveProfiles("test")
>>>>>>> marinaTest
class MarmitechApplicationTests {



	@Test
	void contextLoads() {
	}






}
