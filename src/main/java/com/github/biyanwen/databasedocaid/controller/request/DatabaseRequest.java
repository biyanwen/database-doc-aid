package com.github.biyanwen.databasedocaid.controller.request;

import lombok.Data;

/**
 * 数据库请求
 *
 * @author byw
 * @date 2022/03/23
 */
@Data
public class DatabaseRequest {

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;
	/**
	 * ip
	 */
	private String ip;

	/**
	 * 港口
	 */
	private String port;

	/**
	 * 数据库名称
	 */
	private String databaseName;
}
