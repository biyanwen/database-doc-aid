package com.github.biyanwen.databasedocaid.controller;

import com.github.biyanwen.databasedocaid.api.DataBaseOperator;
import com.github.biyanwen.databasedocaid.api.DocumentCreator;
import com.github.biyanwen.databasedocaid.api.TableMapping;
import com.github.biyanwen.databasedocaid.bean.MysqlDataBaseOperator;
import com.github.biyanwen.databasedocaid.bean.PdfCreator;
import com.github.biyanwen.databasedocaid.controller.request.DatabaseRequest;
import com.github.biyanwen.databasedocaid.utils.AesUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * 创建pdf控制器
 *
 * @author byw
 * @date 2022/03/26
 */
@Controller
public class CreatePdfController {

	@Value("${server.port}")
	private String port;

	@PostMapping("/createPdf")
	@ResponseBody
	public void createPdf(@RequestBody DatabaseRequest databaseRequest, HttpServletRequest request, HttpServletResponse response) {
		DataBaseOperator dataBaseOperator = new MysqlDataBaseOperator();
		String password = AesUtil.decrypt(databaseRequest.getPassword(), "database-doc-aid");
		try {
			response.reset();
			List<TableMapping> tableMappingList = dataBaseOperator.createTableMappings(databaseRequest.getUserName(), password, databaseRequest.getIp(),
					databaseRequest.getPort(), databaseRequest.getDatabaseName());
			DocumentCreator pdfCreator = new PdfCreator();
			byte[] document = pdfCreator.createDocument(tableMappingList);
			response.setHeader("Access-Control-Expose-Headers", "content-disposition");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Origin","http://localhost:" + port);
			response.setContentType("application/pdf");
			response.setCharacterEncoding("utf-8");
			response.setHeader("content-disposition", "attachment;filename*=utf-8" +
					databaseRequest.getDatabaseName() + ".pdf");
			try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());) {
				outputStream.write(document);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}
}
