package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.DocumentCreator;
import com.github.biyanwen.databasedocaid.api.TableFieldMapping;
import com.github.biyanwen.databasedocaid.api.TableMapping;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.tagging.StandardRoles;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * pdf创造者
 *
 * @author byw
 * @date 2022/03/22
 */
public class PdfCreator implements DocumentCreator {

	@Override
	public byte[] createDocument(List<TableMapping> tableMappings) {
		try (ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();) {
			Document doc = createDocObj(byteArrayOutput);
			Table overviewTable = createCommonTableObj(2);
			overviewTable.addHeaderCell("表名");
			overviewTable.addHeaderCell("注释");
			List<FieldTableBean> fieldTableBeanList = new ArrayList<>();
			for (TableMapping tableMapping : tableMappings) {
				//设置一个链接，可以跳转到对应的锚点
				Cell anchorCell = new Cell(1, 1);
				anchorCell.add(new Paragraph(tableMapping.tableName()));
				anchorCell.setAction(PdfAction.createGoTo(tableMapping.tableName()));
				overviewTable.addCell(anchorCell);
				overviewTable.addCell(tableMapping.comment());
				FieldTableBean fieldTableBean = createFieldTableBean(tableMapping.tableName(), tableMapping.tableFields());
				fieldTableBeanList.add(fieldTableBean);
			}
			doc.add(overviewTable);
			fieldTableBeanList.forEach(fieldTableBean -> {
				doc.add(fieldTableBean.getTitle());
				doc.add(fieldTableBean.getFieldTable());
			});
			doc.close();
			return byteArrayOutput.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private FieldTableBean createFieldTableBean(String tableName, List<TableFieldMapping> tableFields) {
		//设置标题
		Paragraph title = new Paragraph(tableName);
		title.setMarginLeft(40);
		title.getAccessibilityProperties().setRole(StandardRoles.H1);
		title.setMarginTop(10);
		title.setDestination(tableName);
		Table fieldTable = createCommonTableObj(5);
		fieldTable.addHeaderCell("字段名");
		fieldTable.addHeaderCell("字段类型");
		fieldTable.addHeaderCell("是否可以为空");
		fieldTable.addHeaderCell("默认值");
		fieldTable.addHeaderCell("注释");
		tableFields.forEach(tableFieldMapping -> {
			fieldTable.addCell(new Cell(1, 1).setMaxWidth(100)
					.add(new Paragraph(tableFieldMapping.fieldName())));
			fieldTable.addCell(tableFieldMapping.fieldType());
			fieldTable.addCell(new Cell(1, 1).setMinWidth(50).add(new Paragraph(tableFieldMapping.ifCanBeNull())));
			fieldTable.addCell(new Cell(1, 1).setMinWidth(50)
					.add(new Paragraph(tableFieldMapping.defaultValue() == null ? "" : tableFieldMapping.defaultValue())));
			fieldTable.addCell(new Cell(1, 1).setWidth(80)
					.add(new Paragraph(tableFieldMapping.comment() == null ? "" : tableFieldMapping.comment())));
		});
		FieldTableBean tableBean = new FieldTableBean();
		tableBean.setTitle(title);
		tableBean.setFieldTable(fieldTable);
		return tableBean;
	}

	/**
	 * 创建概览表obj
	 *
	 * @return {@link Table}
	 */
	private Table createCommonTableObj(int size) {
		Table table = new Table(size);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		table.setTextAlignment(TextAlignment.CENTER);
		table.setWidth(480);
		table.setMarginRight(30);
		table.setMarginBottom(35);
		return table;
	}

	/**
	 * 创建文档obj
	 *
	 * @param byteArrayOutput
	 * @return {@link Document}
	 */
	@SneakyThrows
	private Document createDocObj(ByteArrayOutputStream byteArrayOutput) {
//		String dest = "D:\\tmp\\addingTable.pdf";
		PdfFont font = PdfFontFactory.createFont("templates/font/simhei.ttf");
		PdfWriter writer = new PdfWriter(byteArrayOutput);
		PdfDocument pdfDocument = new PdfDocument(writer);
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterHandler(font));
		Document doc = new Document(pdfDocument, PageSize.A4);
		doc.setFont(font);
		doc.setMargins(50, 10, 10, 10);
		return doc;
	}

	/**
	 * bean字段表
	 *
	 * @author byw
	 * @date 2022/03/23
	 */
	private static class FieldTableBean {
		/**
		 * 标题
		 */
		private Paragraph title;

		/**
		 * 字段表
		 */
		private Table fieldTable;

		public Paragraph getTitle() {
			return title;
		}

		public void setTitle(Paragraph title) {
			this.title = title;
		}

		public Table getFieldTable() {
			return fieldTable;
		}

		public void setFieldTable(Table fieldTable) {
			this.fieldTable = fieldTable;
		}
	}
}
