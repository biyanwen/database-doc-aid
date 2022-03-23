package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.TableFieldMapping;

/**
 * 默认映射表
 *
 * @author byw
 * @date 2022/03/21
 */
public class DefaultTableFieldMapping implements TableFieldMapping {
	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 是否可以为空
	 */
	private String ifCanBeNull;
	/**
	 * 默认值
	 */
	private String defaultValue;
	/**
	 * 注释
	 */
	private String comment;

	@Override
	public String fieldName() {
		return fieldName;
	}

	@Override
	public String fieldType() {
		return fieldType;
	}

	@Override
	public String ifCanBeNull() {
		return ifCanBeNull;
	}

	@Override
	public String defaultValue() {
		return defaultValue;
	}

	@Override
	public String comment() {
		return comment;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public void setIfCanBeNull(String ifCanBeNull) {
		this.ifCanBeNull = ifCanBeNull;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
