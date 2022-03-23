package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.TableFieldMapping;
import com.github.biyanwen.databasedocaid.api.TableMapping;

import java.util.List;

/**
 * 默认映射表
 *
 * @author byw
 * @date 2022/03/21
 */
public class DefaultTableMapping implements TableMapping {
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 注释
	 */
	private String comment;

	/**
	 * 表字段映射
	 */
	private List<TableFieldMapping> tableFieldMappings;

	@Override
	public String tableName() {
		return tableName;
	}

	@Override
	public String comment() {
		return comment;
	}

	@Override
	public List<TableFieldMapping> tableFields() {
		return tableFieldMappings;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTableFieldMappings(List<TableFieldMapping> tableFieldMappings) {
		this.tableFieldMappings = tableFieldMappings;
	}
}
