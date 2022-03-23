package com.github.biyanwen.databasedocaid.api;

/**
 * 表字段映射
 * operator
 *
 * @author byw
 * @date 2022/03/21
 */
public interface TableFieldMapping {
	/**
	 * 字段名
	 *
	 * @return {@link String}
	 */
	String fieldName();

	/**
	 * 字段类型
	 *
	 * @return {@link String}
	 */
	String fieldType();

	/**
	 * 是否可以为空
	 *
	 * @return {@link String}
	 */
	String ifCanBeNull();

	/**
	 * 默认值
	 *
	 * @return {@link String}
	 */
	String defaultValue();

	/**
	 * 注释
	 *
	 * @return {@link String}
	 */
	String comment();

}
