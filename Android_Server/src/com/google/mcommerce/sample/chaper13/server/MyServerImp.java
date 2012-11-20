package com.google.mcommerce.sample.chaper13.server;

import org.w3c.dom.Node;

import com.google.mcommerce.sample.chaper13.server.app.ImyBusiness;
import com.google.mcommerce.sample.chaper13.server.database.ClassNameDo;
import com.google.mcommerce.sample.chaper13.server.xml.XmlParser;

/**
 * webServer �ӿ�ʵ���࣬���ڿͻ���������������Ϣ����
 * @author Administrator chixin 20120813
 *
 */
public class MyServerImp implements IMyServer {
	public static String statuMsg=null;
	public static String error=null;

	/* (non-Javadoc)
	 * @see com.sirius.chix.server.IMyServer#transFun(java.lang.String)
	 */
	@Override
	public String transFun(String XMLString) {
		System.out.println("XMLString-->>"+XMLString);
		try {
			XmlParser parser = new XmlParser();
			boolean is = parser.getSendHead(XMLString);
			if(is){
			Node element=parser.getReturnXMLBody();
			String transCode = parser.getCode();// ȡ�ý�����
			System.out.println("transCode-->>"+transCode);
			ClassNameDo classNameDo = new ClassNameDo();
			/*��ȡclass��·��*/
			String classPath = classNameDo.getClassPath(transCode);
			//�������ʵ�����
			ImyBusiness busy=(ImyBusiness) Class.forName(classPath).newInstance();
			
			statuMsg=busy.business(element,parser.getTrDate(),parser.getTrTime());
			}else{
				System.out.println("����ʧ��");
			}
			

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���Ҳ�����·������");
		}

		return statuMsg;
	}
}
