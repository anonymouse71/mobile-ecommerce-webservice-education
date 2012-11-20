package com.google.mcommerce.sample.chaper13.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassNameDo extends BaseDo {

	/**
	 * ��ݽ������ѯclass·��
	 * 
	 * @param transCode
	 *            ������
	 * @return classPath
	 * @throws SQLException
	 */
	public String getClassPath(String transCode) {
		String classPath = null;
		String sql = "select * from classname where TransCode=?";
		Object[] obj = { transCode };
		ResultSet rs = this.getResultSet(sql, obj);
		try {
			if (rs.next()) {
				classPath = rs.getString(2).trim();
				System.out.println("classPath="+classPath);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sql�쳣---->>"+sql);
			e.printStackTrace();
		}finally{
			closeAll();
		}
		
		return classPath;

	}
}
