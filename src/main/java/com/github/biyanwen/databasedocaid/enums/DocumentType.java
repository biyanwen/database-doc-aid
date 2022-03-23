package com.github.biyanwen.databasedocaid.enums;

/**
 * 文档类型
 *
 * @author byw
 * @date 2022/03/22
 */
public enum DocumentType {
	/**
	 * pdf
	 */
	PDF(1),
	;

	private final int id;

	DocumentType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
