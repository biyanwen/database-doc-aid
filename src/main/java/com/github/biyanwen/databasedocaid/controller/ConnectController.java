package com.github.biyanwen.databasedocaid.controller;

import com.github.biyanwen.databasedocaid.api.DataBaseOperator;
import com.github.biyanwen.databasedocaid.bean.MysqlDataBaseOperator;
import com.github.biyanwen.databasedocaid.controller.request.DatabaseRequest;
import com.github.biyanwen.databasedocaid.utils.AesUtil;
import org.springframework.web.bind.annotation.*;

/**
 * 连接控制器
 *
 * @author byw
 * @date 2022/03/23
 */
@RestController
public class ConnectController {

	@PostMapping("/testConnect")
	public Boolean testConnect(@RequestBody DatabaseRequest databaseRequest) {
		String password = AesUtil.decrypt(databaseRequest.getPassword(), "database-doc-aid");
		DataBaseOperator dataBaseOperator = new MysqlDataBaseOperator();
		return dataBaseOperator.testConnect(databaseRequest.getUserName(), password, databaseRequest.getIp(),
				databaseRequest.getPort(), databaseRequest.getDatabaseName());
	}

}
