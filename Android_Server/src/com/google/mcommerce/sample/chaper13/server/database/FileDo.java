package com.google.mcommerce.sample.chaper13.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.mcommerce.sample.chaper13.server.entity.FileInfo;

/*��ݿ��ṹ
 fileNo                         varchar(40)                    not null,
 fileName                       varchar(40),
 Type                           varchar(40),
 remark                         varchar(200),
 fileSize                       int                            not null,
 FileUpDate                     date                           not null,
 FileUpTime                     time                           not null,
 Uper                           varchar(40)                    not null,
 UpFileName                     varchar(100)                   not null,
 DownPath                       varchar(500)                   not null,
 DownTimer                      int                            not null,
 primary key (fileNo)
 */
/**
 * �ļ��ϴ����ش���
 * 
 * @author Administrator chixin 20120826
 */
public class FileDo extends BaseDo {
	/**
	 * ��ݿ��ҳʱ�� ÿҳ��ʾ��¼����   chixin  20120831
	 */
	private static final int SHOW_NUM=10;
	public FileDo() {

	}

	/**
	 * �������� chixin 20120826
	 * 
	 * @param fileName
	 *            �ؼ���
	 * @param queryType
	 *            �������� 1 �ؼ������� 2�������� 3��������
	 * @param pageNum
	 *            ��ǰҳ��        
	 * @return list
	 */
	public ArrayList<FileInfo> getAllResult(String fileName, int queryType,int pageNum) {
		ArrayList<FileInfo> list = new ArrayList<FileInfo>();
	
		FileInfo file = null;
		String sql = null;
		String key="%" + fileName + "%";
		int currentPage=(pageNum-1)*SHOW_NUM;
		int itemTotal=pageNum*SHOW_NUM;
		Object[] obj = null;
		switch (queryType) {
		case 1:
			obj=new Object[]{key,currentPage,itemTotal};
			sql = "select * from FileTable where fileName like ? limit ?,?";
			break;

		case 2:
			sql = "select * from (select * from FileTable order by FileUpDate desc ,FileUpTime desc) as tab limit ?,?";
			obj=new Object[]{currentPage,itemTotal};
			break;
		case 3:
			obj=new Object[]{currentPage,itemTotal};
//			sql = "select * from FileTable  order by DownTimer desc limit ?,?";
			sql = "select * from (select * from FileTable  order by DownTimer desc) as tab limit ?,?";
			break;
		}

		System.out.println("getAllResult --- sql=" + sql);
		ResultSet rs = super.getResultSet(sql, obj);
		try {
			while (rs.next()) {
				String fileNo = rs.getString(1);
				String fileDescName = rs.getString(2);
				String type = rs.getString(3);
				String remark = rs.getString(4);
				String fileSize = rs.getString(5);
				String upDate = "" + rs.getDate(6);
				String upTime = "" + rs.getTime(7);
				String uper = rs.getString(8);
				String upFileName = rs.getString(9);
				String downPath = rs.getString(10);
				int downTimer = rs.getInt(11);

				file = new FileInfo(fileNo, fileDescName, type, remark,
						fileSize, upDate, upTime, uper, upFileName, downPath,
						downTimer);
				list.add(file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getAllResult --- sql�쳣" + sql);
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ������¼������   ��ݿ��ҳʱʹ�� chixin 20120831
	 * 
	 * @param fileName
	 *            �ؼ���
	 * @param queryType
	 *            �������� 1 �ؼ������� 2�������� 3��������
	 * @return count
	 */
	public int getResultCount(String fileName, int queryType) {
		int count=0;
		String sql = null;
		Object[] obj = { "%" + fileName + "%" };
		switch (queryType) {
		case 1:
			sql = "select count(1) from FileTable where fileName like ?";
			break;

		case 2:
			sql = "select count(1) from FileTable  order by FileUpDate desc ,FileUpTime desc";
			obj = null;
			break;
		case 3:
			sql = "select count(1) from FileTable  order by DownTimer desc";
			obj = null;
			break;
		}

		System.out.println(" getResultCount --- sql=" + sql);
		ResultSet rs = super.getResultSet(sql, obj);
		try {
			if (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getResultCount --- sql�쳣" + sql);
			e.printStackTrace();
		}
		return count;
	}
	
//	/**
//	 * �������� chixin 20120826
//	 * 
//	 * @param fileName
//	 *            �ؼ���
//	 * @param queryType
//	 *            �������� 1 �ؼ������� 2�������� 3��������
//	 * @return list
//	 */
//	public ArrayList<FileInfo> getAllResult(String fileName, int queryType) {
//		ArrayList<FileInfo> list = new ArrayList<FileInfo>();
//		FileInfo file = null;
//		String sql = null;
//		Object[] obj = { "%" + fileName + "%" };
//		switch (queryType) {
//		case 1:
//			sql = "select * from FileTable where fileName like ?";
//			break;
//
//		case 2:
//			sql = "select * from FileTable  order by FileUpDate desc ,FileUpTime desc";
//			obj = null;
//			break;
//		case 3:
//			sql = "select * from FileTable  order by DownTimer desc";
//			obj = null;
//			break;
//		}
//
//		System.out.println("sql=" + sql);
//		ResultSet rs = super.getResultSet(sql, obj);
//		try {
//			while (rs.next()) {
//				String fileNo = rs.getString(1);
//				String fileDescName = rs.getString(2);
//				String type = rs.getString(3);
//				String remark = rs.getString(4);
//				String fileSize = rs.getString(5);
//				String upDate = "" + rs.getDate(6);
//				String upTime = "" + rs.getTime(7);
//				String uper = rs.getString(8);
//				String upFileName = rs.getString(9);
//				String downPath = rs.getString(10);
//				int downTimer = rs.getInt(11);
//
//				file = new FileInfo(fileNo, fileDescName, type, remark,
//						fileSize, upDate, upTime, uper, upFileName, downPath,
//						downTimer);
//				list.add(file);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("sql�쳣" + sql);
//			e.printStackTrace();
//		}
//		return list;
//	}
	/**
	 * �ļ�������ݿ� chixin 20120823
	 * 
	 * @param file
	 *            �ļ�����
	 * @return rows
	 */
	public int addFile(FileInfo file) {
		int rows = 0;
		String sql = "insert into FileTable values(?,?,?,?,?,curdate(),curtime(),?,?,?,?)";
		System.out.println("sql=" + sql);
		Object[] obj = { file.getFileNo(), file.getFileDescName(),
				file.getType(), file.getRemark(), file.getFileSize(),
				file.getUper(), file.getUpFileName(), file.getDownPath(),
				file.getDownTimer() };
		try {
			rows = super.Update(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("addFile --- sql�쳣" + sql);
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * ���ص�ǰ�еļ�¼������
	 * 
	 * @param colunm
	 *            ��
	 * @return ����
	 */
	public int newFileNo(int colunm) {
		int count = 0;
		String sql = "select count(" + colunm + ") from filetable";
		System.out.println("sql=" + sql);
		ResultSet rs = super.getResultSet(sql, null);
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("newFileNo --- sql�쳣" + sql);
		} finally {
			closeAll();
		}
		return count;
	}
	
	/**
	 * �������ش���
	 * @param fileNo �ļ����
	 * @return rows 
	 */
	public int addDownTime(String fileNo) {
		int rows = 0,time=0;
		String sql_Time_Select = "select DownTimer from FileTable where fileNo=?";
		Object[]obj_select={fileNo};
		ResultSet rs=getResultSet(sql_Time_Select, obj_select);
		try {
			if(rs.next()){
				time=rs.getInt(1);
				System.out.println("����ǰ�Ĵ���"+time);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("sql_Time_Select�쳣" + sql_Time_Select);
			e1.printStackTrace();
		}
		String sql = "update filetable set DownTimer=? where fileNo=?";
		System.out.println("sql=" + sql);
		Object[]obj={time+1,fileNo};
		try {
			rows=Update(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("addDownTime --- sql�쳣" + sql);
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return rows;
	}

//	/**
//	 * �ļ�������ݿ� chixin 20120823
//	 * 
//	 * @param file
//	 *            �ļ�����
//	 * @return rows
//	 */
//	public int addFiles(String fileName) {
//		int rows = 0;
//		fileName=encode(fileName);
//		String sql = "insert into FileTable values('fileNo','"
//				+ fileName
//				+ "','Type','remark','fileSize',curdate(),curtime(),'Uper','UpFileName','DownPath',1)";
//		System.out.println("sql=" + sql);
//
//		rows = super.Update(sql, null);
//		return rows;
//	}
//public static void main(String[] args) {
//	System.out.println(new FileDo().addFiles("i'm:\r\\"));
//}
//	/**
//	 * �ַ���
//	 * 
//	 * @param strInValue
//	 *            String
//	 * @return String
//	 */
//	public  String encode(String strInValue) {
//		// String strOutValue = "";
//		StringBuffer tSBql = new StringBuffer();
//		char c;
//
//		for (int i = 0; i < strInValue.length(); i++) {
//			c = strInValue.charAt(i);
//			switch (c) {
//			case ':':
//
//				// hardcode ͬCommon.js�� NAMEVALUEDELIMITER //��������ֵ�ķָ���
//				tSBql.append("��");
//
//				// strOutValue += "��";
//				break;
//			case '|':
//
//				// hardcode ͬCommon.js�� FIELDDELIMITER //��֮��ķָ���
//				tSBql.append("��");
//
//				// strOutValue += "��";
//				break;
//			case '\n':
//				tSBql.append("\\n");
//
//				// strOutValue += "\\n";
//				break;
//			case '\r':
//				tSBql.append("\\r");
//
//				// strOutValue += "\\r";
//				break;
//			case '\"':
//				tSBql.append("\\\"");
//
//				// strOutValue += "\\\"";
//				break;
//			case '\'':
//				tSBql.append("\\\'");
//
//				// strOutValue += "\\\'";
//				break;
//			case '\b':
//				tSBql.append("\\b");
//
//				// strOutValue += "\\b";
//				break;
//			case '\t':
//				tSBql.append("\\t");
//
//				// strOutValue += "\\t";
//				break;
//			case '\f':
//				tSBql.append("\\f");
//
//				// strOutValue += "\\f";
//				break;
//			case '\\':
//				tSBql.append("\\\\");
//
//				// strOutValue += "\\\\";
//				break;
//			case '<':
//				tSBql.append("\\<");
//
//				// strOutValue += "\\<";
//				break;
//			case '>':
//				tSBql.append("\\>");
//
//				// strOutValue += "\\>";
//				break;
//			default:
//				tSBql.append(c);
//
//				// strOutValue += c;
//				break;
//			}
//		}
//		return tSBql.toString();
//	}
}
