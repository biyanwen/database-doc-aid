package com.github.biyanwen.databasedocaid.api;

import java.util.List;

/**
 * 文档创建者
 *
 * @author byw
 * @date 2022/03/22
 */
public interface DocumentCreator {

	/**
	 * 创建文档
	 *
	 * @param tableMappings 表映射
	 * @return {@link byte[]}
	 */
	byte[] createDocument(List<TableMapping> tableMappings);
}
