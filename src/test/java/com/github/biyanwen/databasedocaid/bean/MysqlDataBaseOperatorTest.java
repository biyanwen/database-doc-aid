package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.DataBaseOperator;
import com.github.biyanwen.databasedocaid.api.TableMapping;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MysqlDataBaseOperatorTest {

	@Test
	void testConnect() {
		DataBaseOperator operator = new MysqlDataBaseOperator();
		boolean result = operator.testConnect("root", "123456",
				"127.0.0.1", "3306", "cnpt");
		assertTrue(result);

		boolean result2 = operator.testConnect("admin", "123456",
				"127.0.0.1", "3306", "cnpt");
		assertFalse(result2);
	}

	@SneakyThrows
	@Test
	void createTableMappings() {
		DataBaseOperator operator = new MysqlDataBaseOperator();
		List<TableMapping> tableMappings = operator.createTableMappings("root", "123456",
				"127.0.0.1", "3306", "cnpt");

		PdfCreator pdfCreator = new PdfCreator();
		byte[] document = pdfCreator.createDocument(tableMappings);
		System.out.println(Arrays.toString(document));
	}
}