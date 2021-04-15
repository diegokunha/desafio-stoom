package com.deeconsulting.desafiostoom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.deeconsulting.desafiostoom.controllers.AddressController;

@SpringBootTest
class DesafioStoomApplicationTests {
	
	@Autowired
	private AddressController controller;
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGETAddressController() throws Exception {
		ResponseEntity<?> findAll = controller.findAll(0, 12, "asc");
		assertEquals(HttpStatus.OK, findAll.getStatusCode());
	}
	
}
