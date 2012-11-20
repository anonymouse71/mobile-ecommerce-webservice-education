package com.google.mcommerce.sample.chaper13.server.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��̨��֤
 * @author Administrator  chixin  20120727
 *
 */
public class Check {
	public Check() {

	}
	/**
	 * ��֤�û���
	 * @param name
	 * @return
	 */

	public static boolean userName(String name){	
		Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]");
		Matcher m = p.matcher(name);
		if(m.find()||name.length()<4||name==null||name.equals("")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * �û���Ϊ��
	 * @param name
	 * @return
	 */
	public static boolean nullUserName(String name){	
	
		if(name.equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * �û�������֤
	 * @param email
	 * @return
	 */
	public static boolean userEmail (String email){		
		
		Pattern p = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
		Matcher m = p.matcher(email);
		if(m.find()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ���벻Ϊ����֤
	 * @param pass
	 * @return
	 */
	public static boolean nullUserPass (String pass){		
		
		
		if(pass.equals("")){
			return true;
		}else{
			return false;
		}
}
	
}
