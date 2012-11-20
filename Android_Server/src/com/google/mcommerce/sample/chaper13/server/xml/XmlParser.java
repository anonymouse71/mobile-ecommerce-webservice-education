package com.google.mcommerce.sample.chaper13.server.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ����xml������ DOM��ʽ
 * 
 * @author chixin
 * @date 20120811
 */
public class XmlParser {
	private static final String TRANS_HEAD = "TransHead";
	private static final String TRANS_BODY = "TransBody";
	private static final String TRANS_SOURCE = "TransSource";
	private static final String TRANS_CODE = "TransCode";
	private static final String TRANS_DATE = "TransDate";
	private static final String TRANS_TIME = "TransTime";
	private static final String TRANS_EXT1 = "TransExt1";
	private static final String TRANS_EXT2 = "TransExt2";
	private static final String TRANS_EXT3 = "TransExt3";

	private static final String USER_ID = "UserID";
	private static final String USER_PW = "UserPW";
	private static final String EMAIL = "EMail";

	private Element root = null;// ��ڵ�
	private Document doc = null;// �´�����DOM

	private String code = "";
	private String msg = "";
	private String source = "";
	private String date = "";
	private String time = "";
	private Node returnXMLBody = null;

	public XmlParser() {

	}

	public String getCode() {
		return code;
	}



	public String getMsg() {
		return msg;
	}



	public String getSource() {
		return source;
	}

	
	public String getTrDate() {
		return date;
	}

	public String getTrTime() {
		return time;
	}


	public Node getReturnXMLBody() {
		return returnXMLBody;
	}

	/**
	 * ����xml ��ȡ������Ϣ�ı���ͷ���жϽ��׵��ǳɹ�������ʧ�ܡ� true-�ɹ� false-ʧ�ܡ�
	 * 
	 * @return
	 */
	public boolean getSendHead(String xmlString) {
		// String returncode="";//���ر���
		try {
			InputStream inputStream = new ByteArrayInputStream(
					xmlString.getBytes("UTF-8"));
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);// ���document
			Element root = document.getDocumentElement();// ��ȡdocument��ڵ�

			NodeList nodelist = root.getChildNodes();
			int size = nodelist.getLength();
			for (int i = 0; i < size; i++) {
				Node element = (Node) nodelist.item(i);
				if (TRANS_HEAD.equals(element.getNodeName())) {// ����ͷ
					// �����������ͷ
					NodeList headlist = element.getChildNodes();
					int hsize = headlist.getLength();
					for (int m = 0; m < hsize; m++) {
						Node head = (Node) headlist.item(m);
						if (TRANS_SOURCE.equals(head.getNodeName())) {
							source = head.getTextContent();// -- �����Դ --
							// System.out.println("source=" + source);
						} else if (TRANS_CODE.equals(head.getNodeName())) {
							code = head.getTextContent();// -- ���ױ��� --
							// String code = head.getTextContent();// -- ���ױ��� --
							// System.out.println("source=" + code);
						} else if (TRANS_DATE.equals(head.getNodeName())) {
							date = head.getTextContent();// -- ���׷������� --
							// System.out.println("source=" + date);
						} else if (TRANS_TIME.equals(head.getNodeName())) {
							time = head.getTextContent();// -- ���׷���ʱ�� --
							// System.out.println("source=" + time);
						}
					}
				}
				if (TRANS_BODY.equals(element.getNodeName())) {// ������
					// �ѷ��صı�����洢��������Ҫ����ʱ�ٵ���
					returnXMLBody = element;
					// getTransBody(transCode);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
// /**
// *
// * @param xmlToString �ַ���ʽ��xml�ļ�
// * @return
// */
// public String getReturnHead(String xmlToString) {
// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
// DocumentBuilder builder = null;
// InputStream input = null;
//
// try {
// input = new ByteArrayInputStream(xmlToString.getBytes("UTF-8"));
// builder = factory.newDocumentBuilder();
// this.doc = builder.parse(input);
// String code = null;
// System.out.println("Head---->> START!");
// Element root = doc.getDocumentElement();
// NodeList nodeList = root.getChildNodes();
// int len = nodeList.getLength();
// for (int i = 0; i < len; i++) {
// Node aNode = nodeList.item(i);
// // ��������
// if (TRANS_HEAD.equals(aNode.getNodeName())) {
// // String trHead = aNode.getTextContent();
// // System.out.println("trHead=" + trHead);
// // ���������ͷ
// NodeList temNode = aNode.getChildNodes();
// int length = temNode.getLength();
// for (int j = 0; j < length; j++) {
// Node head = temNode.item(j);
// if (TRANS_SOURCE.equals(head.getNodeName())) {
// String source = head.getTextContent();// -- �����Դ --
// // System.out.println("source=" + source);
// } else if (TRANS_CODE.equals(head.getNodeName())) {
// code = head.getTextContent();// -- ���ױ��� --
// // String code = head.getTextContent();// -- ���ױ��� --
// // System.out.println("source=" + code);
// } else if (TRANS_DATE.equals(head.getNodeName())) {
// String date = head.getTextContent();// -- ���׷������� --
// // System.out.println("source=" + date);
// } else if (TRANS_TIME.equals(head.getNodeName())) {
// String time = head.getTextContent();// -- ���׷���ʱ�� --
// // System.out.println("source=" + time);
// }
//
// }
// }
// // else if (TRANS_BODY.equals(aNode.getNodeName())) {
// // String trBody = aNode.getTextContent();
// // System.out.println("trBody=" + trBody);
// //
// // getReturnBody();// �������
// // }
// }
// } catch (ParserConfigurationException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (UnsupportedEncodingException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (SAXException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// System.out.println("Head---->> END! code=" + code);
// return code;
//
// }
//
// /**
// * �������
// *
// * @return
// */
// public String getReturnBody() {
// String id = null;
// System.out.println("Body---->> START!");
// Element root = doc.getDocumentElement();
// NodeList nodeList = root.getChildNodes();
// int len = nodeList.getLength();
// for (int i = 0; i < len; i++) {
// Node aNode = nodeList.item(i);
// // ��������
// // if (TRANS_HEAD.equals(aNode.getNodeName())) {
// // String trHead = aNode.getTextContent();
// // System.out.println("trHead=" + trHead);
// // getReturnHead();// ��ͷ����
// //
// // } else
// if (TRANS_BODY.equals(aNode.getNodeName())) {
// // String trBody = aNode.getTextContent();
// // System.out.println("trBody=" + trBody);
// // �����������
// NodeList temNode = aNode.getChildNodes();
// int length = temNode.getLength();
// for (int j = 0; j < length; j++) {
// Node head = temNode.item(j);
// if (USER_ID.equals(head.getNodeName())) {
// id = head.getTextContent();// -- �˺� --
// // String id = head.getTextContent();// -- �˺� --
// // System.out.println("source=" + id);
// } else if (USER_PW.equals(head.getNodeName())) {
// String pass = head.getTextContent();// -- ���� --
// // System.out.println("source=" + pass);
// } else if (EMAIL.equals(head.getNodeName())) {
// String email = head.getTextContent();// -- ���� --
// // System.out.println("source=" + email);
// }
//
// }
// }
// }
// System.out.println("Body---->> END! id=" + id);
// return id;
//
// }
//
// public static void main(String[] args) {
// String xmlToString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><TransData>"
// + "<TransHead><TransSource>Android</TransSource> <!-- �����Դ -->"
// + "<TransCode>0000</TransCode> <!-- ���ױ��� -->"
// + "<TransDate>2012-02-28</TransDate><!-- ���׷������� -->"
// + "<TransTime>15:00:00</TransTime><!-- ���׷���ʱ�� -->"
// + "<TransExt1/><!-- �����ֶ�1 -->"
// + "<TransExt2/><!-- �����ֶ�2 -->"
// + "<TransExt3/><!-- �����ֶ�3 -->"
// + "</TransHead>	"
// + "<TransBody>"
// + "<UserID>smileroy111</UserID>"
// + "<UserPW>111111</UserPW>"
// + "<EMail>s2mileroy147@yahoo.com.cn</EMail>"
// + "</TransBody>"
// + "</TransData>";
// XmlParser xmlParser = new XmlParser();
// xmlParser.getReturnHead(xmlToString);
// System.out.println();
// xmlParser.getReturnBody();
// }

