package com.google.mcommerce.sample.chaper13.server.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 
 * @author Administrator
 *
 */
public class BaseDo {
//	private static final String DRIVER = "com.mysql.jdbc.Driver";
//	private static final String URL = "jdbc:mysql://localhost:3306/test_web";
//	private static final String USER_NAME = "root";
//	private static final String USER_PW = "mysql";
	//������ ��ݿ�
	/*private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.7.100:3306/cx";
	private static final String USER_NAME = "chix";
	private static final String USER_PW = "chix";*/
//	
//	//������ݿ�
 	private static final String DRIVER = "com.mysql.jdbc.Driver";
 	private static final String URL = "jdbc:mysql://localhost:3306/test";
 	private static final String USER_NAME = "root";
 	private static final String USER_PW = "zaqwsx";


	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	/**
	 * ��������
	 * 
	 * @return con
	 */
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			con = (Connection) DriverManager.getConnection(URL, USER_NAME,
					USER_PW);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * ��ò�ѯ���
	 * 
	 * @param sql
	 * @param obj
	 * @return rs
	 */
	public ResultSet getResultSet(String sql, Object[] obj) {
		Connection con = getConnection();
		try {
			pst = con.prepareStatement(sql);
			// ����?
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					pst.setObject(i + 1, obj[i]);
				}
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * ��ݸ���
	 * 
	 * @param sql
	 * @param obj
	 * @return rows
	 * @throws SQLException 
	 */
	public int Update(String sql, Object[] obj) throws SQLException {
		int rows = 0;
		Connection con = getConnection();
		try {
			pst = con.prepareStatement(sql);
			// ����?
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					
						pst.setObject(i + 1, obj[i]);
					} 
				}

			}catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("sql�쳣"+sql);
				e.printStackTrace();
			}
			rows = pst.executeUpdate();
		
		return rows;
	}

	/**
	 * �ر���������
	 */
	public void closeAll() {
	try {
		if (!con.isClosed() &&  null!=con ) {
			con.isClosed();
		}
			if (null!=this.rs ) {

				rs.close();
			}
			if ( null!=this.pst ) {
				pst.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
