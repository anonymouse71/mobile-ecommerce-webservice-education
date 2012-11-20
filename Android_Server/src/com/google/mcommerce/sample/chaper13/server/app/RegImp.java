package com.google.mcommerce.sample.chaper13.server.app;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.mcommerce.sample.chaper13.server.database.UserDo;
import com.google.mcommerce.sample.chaper13.server.entity.UserInfo;
import com.google.mcommerce.sample.chaper13.server.tool.Check;
import com.google.mcommerce.sample.chaper13.server.xml.XmlReturn;

/**
 * ע��ҵ������
 * 
 * @author Administrator chixin 20120814
 * 
 */
public class RegImp implements ImyBusiness {
	private static final String USER_ID = "UserID";
	private static final String USER_PW = "UserPW";
	private static final String EMAIL = "EMail";
	private XmlReturn xmlReturn=null;
//	private static final String REG_DATE = "RegDate";
//	private static final String REG_TIME = "RegTime";
	// public static String data="";
	private String statusMsg = "";// ״̬��Ϣ

	public String getStatusMsg() {
		return statusMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sirius.chix.app.ImyBusiness#business(org.w3c.dom.Node)
	 */
	@Override
	public String business(Node element,String TransDate,String TransTime) {
		// TODO Auto-generated method stub
		if (getReturnBody(element)) {
			xmlReturn=new XmlReturn();
			statusMsg =xmlReturn.xmlToString(TransCodeChooser.REG, TransCodeChooser.RE_REG,
					 "��ϲ�㣡ע��ɹ�", TransDate, TransTime,null);
//			statusMsg = "��ϲ�㣡ע��ɹ�";
			System.out.println("����������ϲ�㣡ע��ɹ�");
		} else {
			xmlReturn=new XmlReturn();
			statusMsg =xmlReturn.xmlToString(TransCodeChooser.REG, TransCodeChooser.RE_REG,
					"�û��˺��Ѵ��ڣ�", TransDate, TransTime, null);
//			statusMsg = "�û��˺��Ѵ��ڣ�";
		}
		System.out.println(statusMsg);
		return statusMsg;
	}

	/**
	 * 
	 * �������
	 * 
	 * @return
	 */
	public boolean getReturnBody(Node element) {
		boolean flag=false;
		String id = "";
		String pass = "";
		String email = "";
//		String date = "";
//		String time = "";
		System.out.println("Body---->> START!");
		NodeList nodeList = element.getChildNodes();
		int len = nodeList.getLength();
		for (int i = 0; i < len; i++) {
			Node head = nodeList.item(i);
			if (USER_ID.equals(head.getNodeName())) {
				id = head.getTextContent();// -- �˺� --
				if (!Check.userName(id) || Check.nullUserName(id)) {
					return false;
				}
				// String id = head.getTextContent();// -- �˺� --
				// System.out.println("source=" + id);
			} else if (USER_PW.equals(head.getNodeName())) {
				pass = head.getTextContent();// -- ���� --
				if (Check.nullUserPass(pass)) {
					return false;
				}
				// System.out.println("source=" + pass);
			} else if (EMAIL.equals(head.getNodeName())) {
				email = head.getTextContent();// -- ���� --
				if (!Check.userEmail(email)) {
					return false;
				}
				// System.out.println("source=" + email);
//			} else if (REG_DATE.equals(head.getNodeName())) {
//				date = head.getTextContent();// -- ע������ --
//
//				// System.out.println("source=" + email);
//			} else if (REG_TIME.equals(head.getNodeName())) {
//				time = head.getTextContent();// -- ע��ʱ�� --
//				// System.out.println("source=" + email);
			}

		}
//		try {
//			id=new String(id.getBytes(),"utf-8");
//			pass=new String(pass.getBytes(),"utf-8");
//			email=new String(email.getBytes(),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("Body---->> END! id=" + id);
		System.out.println("Body---->> END! pass=" + pass);
		System.out.println("Body---->> END! email=" + email);
//		System.out.println("Body---->> END! date=" + date);
//		System.out.println("Body---->> END! time=" + time);

		UserDo uDo = new UserDo();
		UserInfo user = uDo.getUserById(id);
		if (user == null) {
			user=new UserInfo();//��ʼ���û���Ϣ�࣬����ע����Ϣ
			user.setUserID(id);
			user.setUserPW(pass);
			user.setEmail(email);
//			user.setRegDate(date);
//			user.setRegTime(time);

			int row = uDo.addUser(user);// ����0ע��ɹ�
			if (row > 0) {
				flag=true;
			} else {
				flag=false;
			}
		}
		return flag;

	}

}
