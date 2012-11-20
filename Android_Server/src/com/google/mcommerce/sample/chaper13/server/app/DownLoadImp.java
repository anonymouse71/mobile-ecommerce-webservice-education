package com.google.mcommerce.sample.chaper13.server.app;

import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.mcommerce.sample.chaper13.server.database.FileDo;
import com.google.mcommerce.sample.chaper13.server.xml.XmlReturn;

public class DownLoadImp implements ImyBusiness {
private static final String FILE_NO="FileNo";
private String fileNo="";
private String statusMsg = "";// ״̬��Ϣ
private String error="";//�쳣��Ϣ
private XmlReturn xmlReturn=null;
	@Override
	public String business(Node element, String TransDate, String TransTime) {
		// TODO Auto-generated method stub
		if (getReturnBody(element)) {
			xmlReturn=new XmlReturn();
			statusMsg =xmlReturn.xmlToString(TransCodeChooser.DOWMLOAD, TransCodeChooser.RE_LOGIN,
					"�������!", TransDate, TransTime, null);
		} else {
			System.out.println("��ݿ����ʧ�ܣ�");
		}
		System.out.println(statusMsg);
		return statusMsg;
	}
	
	
	/**
	 * �������  ����ļ���� 
	 * �������ش���   
	 * @return    true ���³ɹ�     false ����ʧ��
	 */
	public boolean getReturnBody(Node element) {
		System.out.println("Body---->> START!");
		NodeList nodeList = element.getChildNodes();
		int len = nodeList.getLength();
		for (int i = 0; i < len; i++) {
			Node head = nodeList.item(i);
			if (FILE_NO.equals(head.getNodeName())) {
				fileNo = head.getTextContent();// -- �ļ���� --
				// String id = head.getTextContent();// -- �ļ���� --
				// System.out.println("source=" + id);
			}

		}

		System.out.println("Body---->> END! fileNo=" + fileNo);
		
		/**
		 * �������ش���
		 */
		
		FileDo fileDo=new FileDo();
		int rows=fileDo.addDownTime(fileNo);
		if(rows>0){
			return true;
		}else{
			return false;
			
		}
	}

}
