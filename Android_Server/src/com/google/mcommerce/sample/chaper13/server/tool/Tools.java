package com.google.mcommerce.sample.chaper13.server.tool;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * �ļ������� ��Դ�ļ��Ķ�ȡ SDcard�ļ�д��
 * 
 * @author Administrator chixin 20100804
 * 
 */
public class Tools {

	public Tools() {

	}

	/**
	 * ����ת��GBK 
	 * @param getStr
	 * @return transStr
	 */
	public static String getGBK_Str(String getStr){
		String transStr=null;
		try {
			transStr = new String(getStr.getBytes(),"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transStr;
	}

	/**
	 * �õ���ǰϵͳ����
	 * 
	 * @return ��ǰ���ڵĸ�ʽ�ַ�,���ڸ�ʽΪ"yyyy-MM-dd"
	 */
	public static String getCurrentDate() {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}

	/**
	 * �õ���ǰϵͳʱ��
	 * 
	 * @return ��ǰʱ��ĸ�ʽ�ַ�ʱ���ʽΪ"HH:mm:ss"
	 */
	public static String getCurrentTime() {
		String pattern = "HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date today = new Date();
		String tString = df.format(today);
		return tString;
	}
	
	/**
	 * ����ļ�·�� �����ļ���
	 * @param mkdirName
	 */
    public static boolean makeFolder(String folderPath)
	{
		try
		{
			StringTokenizer s = new StringTokenizer(folderPath, "/");
			s.countTokens();
			String pathName = "";
			while (s.hasMoreElements()) {
				pathName = pathName + "/" + (String) s.nextElement();
				File folder = new File(pathName);
				if(!folder.exists()){
					if(!folder.mkdir())
					{
						System.out.println(" �ļ��д���ʧ�ܣ���ȷ�ϴ���û��д�������ҿռ��㹻");
						return false;
					}
				}
			}
		}
		catch (Exception err)
		{
			System.err.println("�ļ��д��������쳣");
			err.printStackTrace();
			return false;
		}
		return true;
	}

}
