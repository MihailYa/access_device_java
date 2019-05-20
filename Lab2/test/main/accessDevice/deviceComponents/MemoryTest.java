package main.accessDevice.deviceComponents;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MemoryTest {
	private static String PROPERTIES_FILE_NAME = "rsc/memory.properties";

	@Test
	void initTest() {
		assertDoesNotThrow(() -> {FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_NAME);});
		//
	}

	@Test
	void initTest2() {
		Memory memory = new Memory();
	}
}