package com.github.biyanwen.databasedocaid.api;

import java.util.List;

/**
 * 表映射
 *
 * @author byw
 * @date 2022/03/21
 */
public interface TableMapping {

	/**
	 * 表名
	 *
	 * @return {@link String}
	 */
	String tableName();

	/**
	 * 注释
	 *
	 * @return {@link String}
	 */
	String comment();

	/**
	 * 表字段数据
	 *
	 * @return {@link List}<{@link TableFieldMapping}>
	 */
	List<TableFieldMapping> tableFields();
}
