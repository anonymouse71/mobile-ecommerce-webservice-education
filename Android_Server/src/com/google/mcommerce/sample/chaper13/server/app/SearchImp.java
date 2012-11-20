package com.google.mcommerce.sample.chaper13.server.app;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.mcommerce.sample.chaper13.server.database.FileDo;
import com.google.mcommerce.sample.chaper13.server.entity.FileInfo;
import com.google.mcommerce.sample.chaper13.server.xml.XmlReturn;

/**
 * ����ҵ����
 * @author Administrator chixin 21020826
 *
 */
public class SearchImp implements ImyBusiness {
	private static final String KEY_WORD = "KeyWord";
	private static final String QUERY_TYPE = "QueryType";
	private static final String PAGE_NUM="CurrentPage";
//	private XmlReturn xmlReturn = null;
	private String searchResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
			"<TansData><FileNum>-1</FileNum></TansData>";// ���������Ϣ
	private String keyWord = "";
	private String currentPage="";
	/**
	 *  1 �ؼ�������
	 *  2 ��������
	 *  3��������
	 */
	private int queryType = 0;
	public String getSearchResult() {
		return searchResult;
	}

	@Override
	public String business(Node element, String TransDate, String TransTime) {
		// TODO Auto-generated method stub
		if (getReturnBody(element)) {
			searchResult=search();
		}
		return searchResult;
	}


	/**
	 * ���������
	 * @param element 
	 * @return 
	 */
	private boolean getReturnBody(Node element) {	
		System.out.println("Body---->> START!");
		NodeList nodeList = element.getChildNodes();
		int len = nodeList.getLength();
		for (int i = 0; i < len; i++) {
			Node head = nodeList.item(i);
			if (QUERY_TYPE.equals(head.getNodeName())) {
				queryType = Integer.parseInt(head.getTextContent());// -- �������Ͳ�����
				System.out.println("queryType----*****----"+queryType);												
				if (QUERY_TYPE.equals("")) {
					return false;
				}
			}
			if (KEY_WORD.equals(head.getNodeName())) {
				keyWord = head.getTextContent();// -- �����ؼ���Ϊ�� --
				if (keyWord.equals("")&&queryType==1) {
					return false;
				}
				// System.out.println("keyWord=" + keyWord);
			}
			if (PAGE_NUM.equals(head.getNodeName())) {
				currentPage = head.getTextContent();// -- �����ؼ���Ϊ�� --
				
				 System.out.println("pageNum=" + currentPage);
			}

		}

		System.out.println("Body---->> END! keyWord=" + keyWord);
		return true;

	}

	/**
	 * ��ѯ���
	 * @return xml ����
	 */
	private String search(){
		String xml=null;
		System.out.println("----queryType--->>"+queryType);
		FileDo fileDo=new FileDo();
//		int num=fileDo.newFileNo(1);
		int num=fileDo.getResultCount(keyWord, queryType);
		ArrayList<FileInfo> list=fileDo.getAllResult(keyWord,queryType,Integer.parseInt(currentPage));
		XmlReturn xmlReturn=new XmlReturn();
		xml=xmlReturn.xmlToString_search(list,num);
		System.out.println("----xml--->>"+xml);

		return xml;
		
	}
	
//public static void main(String[] args) {
//	SearchImp searchImp=new SearchImp();
//	searchImp.queryType=1;
//	searchImp.keyWord="ʹ�ý̳�";
//	searchImp.keyWordSearch();
//	
//}
}
