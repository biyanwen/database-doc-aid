package com.github.biyanwen.databasedocaid.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PdfCreatorTest {

	@Test
	void createDocument() {
		PdfCreator pdfCreator = new PdfCreator();
		pdfCreator.createDocument(new ArrayList<>());
	}
}