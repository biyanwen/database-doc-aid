package com.github.biyanwen.databasedocaid.bean;

import com.github.biyanwen.databasedocaid.api.DataBaseOperator;
import com.github.biyanwen.databasedocaid.api.TableFieldMapping;
import com.github.biyanwen.databasedocaid.api.TableMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql数据库操作者
 *
 * @author byw
 * @date 2022/03/21
 */
public class MysqlDataBaseOperator implements DataBaseOperator {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseOperator.class);
	private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

	static {
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}
	}

	@Override
	public boolean testConnect(String username, String password, String ip, String port, String databaseName) {
		String url = createUrl(ip, port, databaseName);
		try (Connection conn = DriverManager.getConnection(url, username, password);) {
			conn.isValid(100);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<TableMapping> createTableMappings(String username, String password, String ip, String port, String databaseName) {
		try (Connection conn = DriverManager.getConnection(createUrl(ip, port, databaseName), username, password)) {
			List<TableMessage> tableMessages = getTableNames(conn,databaseName);
			return createTableMappings(tableMessages, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


	/**
	 * 关闭数据库连接
	 *
	 * @param conn
	 */
	private void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure", e);
			}
		}
	}

	/**
	 * 获取数据库下的所有表名
	 */
	private List<TableMessage> getTableNames(Connection conn, String databaseName) {
		List<TableMessage> tableMessages = new ArrayList<>();
		ResultSet rs = null;
		try {
			//获取数据库的元数据
			DatabaseMetaData db = conn.getMetaData();
			//从元数据中获取到所有的表名
			rs = db.getTables(databaseName, null, null, new String[]{"TABLE"});
			while (rs.next()) {
				String tableName = rs.getString(3);
				String remarks = rs.getString("REMARKS");
				TableMessage tableMessage = new TableMessage();
				tableMessage.setTableName(tableName);
				tableMessage.setComment(remarks);
				tableMessages.add(tableMessage);
			}
		} catch (SQLException e) {
			LOGGER.error("getTableNames failure", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOGGER.error("close ResultSet failure", e);
			}
		}
		return tableMessages;
	}

	/**
	 * 获取表中字段的所有注释
	 *
	 * @param tableMessages
	 * @return
	 */
	private List<TableMapping> createTableMappings(List<TableMessage> tableMessages, Connection conn) {
		List<TableMapping> tableFieldMappings = new ArrayList<>();
		try (Statement statement = conn.createStatement()) {
			for (TableMessage tableMessage : tableMessages) {
				List<TableFieldMapping> tableFieldMappingList = createTableMapping(statement, tableMessage.getTableName());
				DefaultTableMapping tableMapping = new DefaultTableMapping();
				tableMapping.setTableFieldMappings(tableFieldMappingList);
				tableMapping.setTableName(tableMessage.getTableName());
				tableMapping.setComment(tableMessage.getComment());
				tableFieldMappings.add(tableMapping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableFieldMappings;
	}

	private List<TableFieldMapping> createTableMapping(Statement statement, String tableName) {
		List<TableFieldMapping> tableFieldMappingList = new ArrayList<>();
		try (ResultSet rs = statement.executeQuery("show full columns from " + tableName)) {
			while (rs.next()) {
				DefaultTableFieldMapping tableFieldMapping = new DefaultTableFieldMapping();
				tableFieldMapping.setFieldName(rs.getString("Field"));
				tableFieldMapping.setFieldType(rs.getString("Type"));
				tableFieldMapping.setIfCanBeNull(rs.getString("Null"));
				tableFieldMapping.setDefaultValue(rs.getString("Default"));
				tableFieldMapping.setComment(rs.getString("Comment"));
				tableFieldMappingList.add(tableFieldMapping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableFieldMappingList;
	}

	private String createUrl(String ip, String port, String databaseName) {
		return "jdbc:mysql://" + ip + ":" + port + "/" + databaseName + "?" + "useInformationSchema=true";
	}

	private static class TableMessage {
		/**
		 * 表名
		 */
		private String tableName;

		/**
		 * 注释
		 */
		private String comment;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}
}
