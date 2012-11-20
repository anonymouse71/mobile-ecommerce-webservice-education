package com.google.mcommerce.sample.chaper13.server.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.mcommerce.sample.chaper13.server.entity.UserInfo;

public class UserDo extends BaseDo{
	
	/**
	 * ͨ���˺Ų�ѯ�û�
	 * @param userId
	 * @return
	 */
	public UserInfo getUserById(String userId){
		UserInfo user=null;
		try {
		//User user=null;
		String sql="select * from userInfo where UserID=?";
		System.out.println(sql);
		Object[]obj={userId};
		ResultSet rs=this.getResultSet(sql, obj);
//		try {
			while(rs.next()){
				String userPw=rs.getString(2);
				String email=rs.getString(3);
				user=new UserInfo(userId, userPw, email);
				user.setUserName(rs.getString(4));
				user.setCountry(rs.getString(5));
				user.setVip(rs.getString(6));
				user.setRegDate(rs.getString(7));
				user.setRegTime(rs.getString(8));
				System.out.println("UserID="+user.getUserID()+" "+user.getUserPW()+" "+user.getEmail());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Sql�쳣... ...");
		}finally{
			closeAll();			
		}
		return user;
	}
	/**
	 * ע���û�
	 * @param user �û���Ϣ
	 * @return rows
	 */
	public int addUser(UserInfo user){
		int rows=0;
		String sql="insert into userinfo values(?,?,?,?,?,?,curdate(),curtime())";
		System.out.println(sql);
		Object[]obj={user.getUserID(),user.getUserPW(),user.getEmail(),user.getUserName(),
				user.getCountry(),user.getVip()};
		try {
			rows=Update(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql�쳣" + sql);
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return rows;
	}
//	public static void main(String[] args) {
//		UserDo us=new UserDo();
//			UserInfo user=null;
//			try {
//			//User user=null;
//			String sql="select * from userInfo";
//			
//			ResultSet rs=us.getResultSet(sql, null);
////			try {
//				while(rs.next()){
//					String userId=rs.getString(1);
////					userId=new String(userId.getBytes(),"GBK");
//					String userPw=rs.getString(2);
//					String email=rs.getString(3);
////					user=new UserInfo(userId, userPw, email);
//					user=new UserInfo();
//					user.setUserName(rs.getString(4));
//					user.setCountry(rs.getString(5));
//					user.setVip(rs.getString(6));
//					user.setRegDate(rs.getString(7));
//					user.setRegTime(rs.getString(8));
//					System.out.println("UserID="+userId+" "+userPw+" "+email
//							+" "+user.getUserName()+" "+user.getCountry()+" "+user.getVip()+" "+user.getRegDate()+" "+user.getRegTime());
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Sql�쳣... ...");
//			}finally{
//				us.closeAll();			
//			}
//			
//		
//	}

}
