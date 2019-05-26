package main.accessDevice.util.security;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

class EncoderTest {

	@Test
	void md5() {
		try (FileOutputStream fileOutputStream = new FileOutputStream("md5.txt")) {
			fileOutputStream.write(Encoder.md5("admin")
			                              .getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}