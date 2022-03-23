package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.DocumentCreator;
import com.github.biyanwen.databasedocaid.enums.DocumentType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 文档创建者上下文
 *
 * @author byw
 * @date 2022/03/22
 */
public class DocumentCreatorContext {

	private static final Map<DocumentType, Supplier<DocumentCreator>> CONTEXT = new HashMap<>();

	static {
		CONTEXT.put(DocumentType.PDF, PdfCreator::new);
	}

	/**
	 * 得到创造者
	 *
	 * @param type 类型
	 * @return {@link DocumentCreator}
	 */
	public static DocumentCreator getCreator(DocumentType type) {
		return CONTEXT.get(type).get();
	}

}
