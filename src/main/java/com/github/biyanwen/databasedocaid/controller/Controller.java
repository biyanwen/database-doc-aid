package com.github.biyanwen.databasedocaid.controller;

import com.github.biyanwen.databasedocaid.api.DataBaseOperator;
import com.github.biyanwen.databasedocaid.api.TableMapping;
import com.github.biyanwen.databasedocaid.bean.MysqlDataBaseOperator;
import com.github.biyanwen.databasedocaid.bean.PdfCreator;
import com.github.biyanwen.databasedocaid.controller.request.DatabaseRequest;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 控制器
 *
 * @author byw
 * @date 2022/03/23
 */
@RestController
public class Controller {

	@SneakyThrows
	@GetMapping("/getPdf")
	public void getPdf(HttpServletRequest request, HttpServletResponse response) {
		//@RequestBody DatabaseRequest databaseRequest,
		DataBaseOperator operator = new MysqlDataBaseOperator();
		List<TableMapping> tableMappings = operator.createTableMappings("root", "123456",
				"127.0.0.1", "3306", "cnpt");
		PdfCreator pdfCreator = new PdfCreator();
		byte[] document = pdfCreator.createDocument(tableMappings);
		response.reset();
		response.setContentType("application/pdf");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "cnpt.pdf");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(document);
		outputStream.flush();
	}

}
