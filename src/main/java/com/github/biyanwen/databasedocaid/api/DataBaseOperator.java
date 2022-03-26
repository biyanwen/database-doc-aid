package com.github.biyanwen.databasedocaid.api;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据基本操作者
 *
 * @author byw
 * @date 2022/03/21
 */
public interface DataBaseOperator {

	/**
	 * 测试连接
	 *
	 * @param username     用户名
	 * @param password     密码
	 * @param ip           ip
	 * @param port         端口
	 * @param databaseName 数据库名称
	 * @return boolean
	 */
	boolean testConnect(String username, String password, String ip, String port, String databaseName);

	/**
	 * 创建表映射
	 *
	 * @param username     用户名
	 * @param password     密码
	 * @param ip           ip
	 * @param port         端口
	 * @param databaseName 数据库名称
	 * @return {@link List}<{@link TableMapping}>
	 */
	List<TableMapping> createTableMappings(String username, String password, String ip, String port, String databaseName) throws SQLException;
}
