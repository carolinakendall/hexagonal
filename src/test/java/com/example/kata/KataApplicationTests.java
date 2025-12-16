package com.example.kata;

import com.example.kata.ports.in.CompteTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class KataApplicationTests {

	@Autowired
	private CompteTransaction compteTransaction;

	@Test
	void contextLoads() {
		assertNotNull(compteTransaction);
	}
}