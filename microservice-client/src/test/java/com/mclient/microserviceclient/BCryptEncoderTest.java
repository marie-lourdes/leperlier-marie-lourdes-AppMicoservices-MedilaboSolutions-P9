package com.mclient.microserviceclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {
	
	@Test
	public void testEncodeUserPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {	
		String result = encoder.encode("rootroot");
		
		assertThat(result.equals("rootroot")).isFalse();
		assertThat(encoder.matches("rootroot", result)).isTrue();
		}catch(AssertionError e) {
			fail(e.getMessage());
		}
	}
}
