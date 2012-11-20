package com.google.mcommerce.sample.chaper13.server.app;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.mcommerce.sample.chaper13.server.database.UserDo;
import com.google.mcommerce.sample.chaper13.server.entity.UserInfo;
import com.google.mcommerce.sample.chaper13.server.tool.Check;
import com.google.mcommerce.sample.chaper13.server.tool.Tools;
import com.google.mcommerce.sample.chaper13.server.xml.XmlCreater;
import com.google.mcommerce.sample.chaper13.server.xml.XmlReturn;

public class LoginImp implements ImyBusiness {
	private static final String USER_ID = "UserID";
	private static final String USER_PW = "UserPW";
	private String id = "";
	private String statusMsg = "";// ״̬��Ϣ
	private String error="";//�쳣��Ϣ
	private XmlReturn xmlReturn=null;
	
	public String getUserId(){
		return id;
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sirius.chix.app.ImyBusiness#business(org.w3c.dom.Node) ��¼ҵ���߼�
	 */
	@Override
	public String business(Node element,String TransDate,String TransTime) {
		
		// TODO Auto-generated method stub
		if (getReturnBody(element)) {
			xmlReturn=new XmlReturn();
			statusMsg =xmlReturn.xmlToString(TransCodeChooser.LOGIN, TransCodeChooser.RE_LOGIN,
					"��¼�ɹ�!", TransDate, TransTime, getUserId());
//			statusMsg = "��¼�ɹ�!";
			System.out.println("����������¼�ɹ�");
		} else {
			xmlReturn=new XmlReturn();
			statusMsg =xmlReturn.xmlToString(TransCodeChooser.LOGIN, TransCodeChooser.RE_LOGIN,
					"��¼ʧ��!", TransDate, TransTime, getUserId());
//			statusMsg = "��¼ʧ��!";
		}
		System.out.println(statusMsg);
		return statusMsg;
	}

	/**
	 * �������
	 *
	 * @return
	 */
	public boolean getReturnBody(Node element) {
		String pass = "";

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
			}

		}

		System.out.println("Body---->> END! id=" + id);
		System.out.println("Body---->> END! pass=" + pass);
		UserDo uDo = new UserDo();
		UserInfo user = uDo.getUserById(id);
		if(user==null){//�˺Ų�����
			return false;
		}else if (!user.getUserPW().equals(pass)) {// �������
			return false;
		} else {
			return true;
		}

	}


}
