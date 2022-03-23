package com.github.biyanwen.databasedocaid.bean;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * 页眉页脚处理程序
 *
 * @author byw
 * @date 2022/03/22
 */
public class HeaderFooterHandler implements IEventHandler {


	private final PdfFont font;

	public HeaderFooterHandler(final PdfFont font) {
		this.font = font;
	}

	@Override
	public void handleEvent(final Event event) {
		try {
			final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
			final PdfDocument pdfDoc = docEvent.getDocument();
			final Document doc = new Document(pdfDoc);
			final PdfPage page = docEvent.getPage();
			final int pageNumber = pdfDoc.getPageNumber(page);
			final Rectangle pageSize = page.getPageSize();
			final float pdfWidth = pageSize.getWidth();
			final float pdfHeight = pageSize.getHeight();
			final PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
			final float tableWidth = pdfWidth - doc.getRightMargin() - doc.getLeftMargin();
			// 页眉
			final float x0 = doc.getRightMargin(), y0 = pdfHeight - doc.getTopMargin();
			pdfCanvas.moveTo(x0, y0).lineTo(pdfWidth - doc.getRightMargin(), y0).stroke();
			final Table headerTable = new Table(2);
			headerTable.setHeight(30);
			headerTable.setFixedLayout();
			headerTable.setWidth(tableWidth);
			headerTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

			final Paragraph pageNameParagraph = new Paragraph();
			pageNameParagraph.setVerticalAlignment(VerticalAlignment.BOTTOM);
			pageNameParagraph.add("数据库文档");

			final Cell pageNameCell = new Cell();
			pageNameCell.setBorder(Border.NO_BORDER);
			pageNameCell.add(pageNameParagraph);
			pageNameCell.setFont(this.font);
			pageNameCell.setFontSize(12f);
			pageNameCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
			headerTable.addCell(pageNameCell);

			final Paragraph realnameParagraph = new Paragraph();
			realnameParagraph.setVerticalAlignment(VerticalAlignment.BOTTOM);
			realnameParagraph.add(new Tab()).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
			realnameParagraph.add("https://github.com/biyanwen");
			final Cell realnameCell = new Cell();
			realnameCell.setFont(this.font);
			realnameCell.setBorder(Border.NO_BORDER);
			realnameCell.add(realnameParagraph);
			realnameCell.setFontSize(12f);
			realnameCell.setVerticalAlignment(VerticalAlignment.BOTTOM);
			headerTable.addCell(realnameCell);
			headerTable.setFixedPosition(doc.getLeftMargin(), pdfHeight - doc.getTopMargin(), tableWidth);
			doc.add(headerTable);

			// 页脚
			pdfCanvas.moveTo(x0, doc.getBottomMargin()).lineTo(pdfWidth - doc.getRightMargin(), doc.getBottomMargin())
					.stroke();

			final Table table = new Table(2);
			table.setHeight(40);
			table.setFixedLayout();
			table.setWidth(tableWidth);
			table.setHorizontalAlignment(HorizontalAlignment.CENTER);

			final String pageNo = String.format("%d", pageNumber);
			final Paragraph pageNoParagraph = new Paragraph();
			pageNoParagraph.setFontColor(new DeviceRgb(166, 166, 166));
			pageNoParagraph.setFontSize(12f);
			pageNoParagraph.add(new Tab()).addTabStops(new TabStop(1000, TabAlignment.RIGHT));
			pageNoParagraph.add(pageNo);
			table.addCell(new Cell().add(pageNoParagraph).setBorder(Border.NO_BORDER));
			table.setFixedPosition(doc.getLeftMargin(), doc.getBottomMargin() - table.getHeight().getValue(),
					tableWidth);
			doc.add(table);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}