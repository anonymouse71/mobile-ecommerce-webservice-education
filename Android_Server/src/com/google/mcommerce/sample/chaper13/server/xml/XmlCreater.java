package com.google.mcommerce.sample.chaper13.server.xml;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * ����xml������   DOM��ʽ
 * @author chixin
 * @date 20120811
 */
public class XmlCreater
{
	
    private Document doc=null;//�´�����DOM
    private Element root = null;//��ڵ�
    private String encoding = "UTF-8";//Ĭ��ΪUTF-8
    
    public XmlCreater()
    {
        init();
    }
    
    private void init()
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.newDocument();//�½�DOM
        }catch(ParserConfigurationException e)
        {
        	e.printStackTrace();
        }
    }
    

    /**
     * ����xml�ĸ�ڵ�
     * @param rootTagName
     * @return
     */
    public Element createRootElement(String rootTagName)
    {     
        if(doc.getDocumentElement()==null)
        {
            root=doc.createElement(rootTagName);
            doc.appendChild(root);
            return root;
        }
        return doc.getDocumentElement();
    }
    
     /**
      * ����һ����ΪtagName,ֵΪvalue�Ľڵ�
     * @param tagName
     * @param value
     * @return
     */
    public Element createElement(String tagName,String value)
     {
         Document doc=root.getOwnerDocument();
         Element child=doc.createElement(tagName);
         child.setTextContent(value);
         return child;
     }
     
     /**
      * ����һ����ΪtagName�Ŀսڵ㡣
     * @param tagName
     * @return
     */
    public Element createElement(String tagName){
         Document doc=root.getOwnerDocument();
         Element child=doc.createElement(tagName);
         return child;
     }
    
    

    /**
     * �����ļ���ַ·������ɶ�Ӧ��xml�ļ�
     * @param path
     */
    public void buildXmlFile(String path)
    {
        TransformerFactory tfactory=TransformerFactory.newInstance();
        try
        {
            Transformer transformer=tfactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result=new StreamResult(new File(path));
            transformer.setOutputProperty("encoding",this.encoding);
            transformer.transform(source,result);
        }catch(TransformerConfigurationException e)
        {
        	e.printStackTrace();
        }catch(TransformerException e)
        {
        	e.printStackTrace();
        }
    }
    
    /**
     * ����xml��Ӧ��String�ַ�
     * @return
     */
    public String xmlToString()
    {
    	String xmlString ="";
        TransformerFactory tfactory=TransformerFactory.newInstance();
        try
        {
            Transformer transformer=tfactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result=new StreamResult(writer);
            transformer.setOutputProperty("encoding",this.encoding);
            transformer.transform(source,result);
            xmlString = writer.getBuffer().toString();
        }catch(TransformerConfigurationException e)
        {
        	e.printStackTrace();
        }catch(TransformerException e)
        {
        	e.printStackTrace();
        }
        return xmlString;
    }
    
    /**
     * @return ���� doc��
     */
    public Document getDoc()
    {
        return doc;
    }

	/**
	 *  ���õ�ǰxml�ı����ʽ
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

    
    
    

}

