package com.google.mcommerce.sample.chaper13.server.xml;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.Element;

import com.google.mcommerce.sample.chaper13.server.entity.FileInfo;
import com.google.mcommerce.sample.chaper13.server.tool.Tools;

/**
 * ���׷��ر���
 * @author Administrator
 *
 */
public class XmlReturn {

	
	
	/**
	 * ���׷��ر���
	 * @param TrCode ������
	 * @param ReCode ���׷�����
	 * @param ReturnMsg ������Ϣ
	 * @param TrDate ���׷�������
	 * @param TrTime ���׷���ʱ��
	 * @param userId �����׵��û�
	 * @return  ����xml����
	 */
	public String xmlToString(String TrCode,String ReCode, String ReturnMsg,
			String TrDate,String TrTime,String userId) {
		XmlCreater creater = new XmlCreater();
		// �����ڵ�
		Element root = creater.createRootElement("TransData");
		Element TransHead = creater.createElement("TransHead");// ����ͷ
		Element TransSource = creater.createElement("TransSource", "Android");
		Element TransCode = creater.createElement("TransCode", TrCode);// Ϊ�û�������
		//���׵����к�
		Element TransSerial = creater.createElement("TransSerial",
				TrDate.replace("-", "")+TrTime.replace(":", "")+new DecimalFormat("00000").format(new Random().nextInt(100000)));
		
		Element TransDate = creater.createElement("TransDate",
				TrDate);
		Element TransTime = creater.createElement("TransTime",
				TrTime);
		
		Element ReturnCode = creater.createElement("ReturnCode",ReCode
				);
		
		Element ReturnMessage = creater.createElement("ReturnMessage",ReturnMsg
				);
		
		Element ReturnDate = creater.createElement("ReturnDate",
				Tools.getCurrentDate());
		Element ReturnTime = creater.createElement("ReturnTime",
				Tools.getCurrentTime());
		
		Element TransExt1 = creater.createElement("TransExt1");// �����ֶ�
		Element TransExt2 = creater.createElement("TransExt2");
		Element TransExt3 = creater.createElement("TransExt3");

		Element TransBody = creater.createElement("TransBody");// ������
		Element UserID = creater.createElement("UserID",userId);

		// ���ýڵ��Ĺ�ϵ
		root.appendChild(TransHead);
		root.appendChild(TransBody);
		// ����ͷ
		TransHead.appendChild(TransSource);
		TransHead.appendChild(TransCode);
		TransHead.appendChild(TransSerial);	
		TransHead.appendChild(TransDate);
		TransHead.appendChild(TransTime);
		TransHead.appendChild(ReturnCode);
		TransHead.appendChild(ReturnMessage);
		TransHead.appendChild(ReturnDate);
		TransHead.appendChild(ReturnTime);
		TransHead.appendChild(TransExt1);
		TransHead.appendChild(TransExt2);
		TransHead.appendChild(TransExt3);
		// ������
		TransBody.appendChild(UserID);
		

		return creater.xmlToString();
	}
	
	
	/**
	 * ��������� chixin 20120826
	 * @param list
	 * @return xmlToString_search
	 */
	public String xmlToString_search(ArrayList<FileInfo> list,int num){
		
		int len=list.size();
		System.out.println("len="+len);
		XmlCreater creater = new XmlCreater();
		Element tansData=creater.createRootElement("TansData");
		Element fileNum=creater.createElement("FileNum",String.valueOf(num));//��ѯ���ܼ�¼����
		Element ReturnFileNum=creater.createElement("ReturnFileNum",String.valueOf(len));//��ѯ���صļ�¼����
		tansData.appendChild(fileNum);
		tansData.appendChild(ReturnFileNum);
		Element file=null;
		Element fileName=null;
		Element reMark=null;
		Element size=null;
		Element upDate=null;
		Element uper=null;
		Element downTime=null;
		Element downPath=null;
		Element type=null;
		Element nO=null;
		//�����ļ���Ϣ���Ľ��
		for(int i=0;i<len;i++){			
			file=creater.createElement("File");
			nO=creater.createElement("FileNo",list.get(i).getFileNo());
			fileName=creater.createElement("FileName",list.get(i).getFileDescName());
			reMark=creater.createElement("ReMark", list.get(i).getRemark());
			size=creater.createElement("Size",list.get(i).getFileSize());
			upDate=creater.createElement("UpDate",list.get(i).getUpDate());
			uper=creater.createElement("Uper",list.get(i).getUper());
			downTime=creater.createElement("DownTime",String.valueOf(list.get(i).getDownTimer()));
			downPath=creater.createElement("DownPath",list.get(i).getDownPath());
			type=creater.createElement("Type",list.get(i).getType());
			//���ý���Ĺ�ϵ
			file.appendChild(nO);
			file.appendChild(fileName);
			file.appendChild(reMark);
			file.appendChild(size);
			file.appendChild(upDate);
			file.appendChild(uper);
			file.appendChild(downTime);
			file.appendChild(downPath);
			file.appendChild(type);
			tansData.appendChild(file);
		}
		return creater.xmlToString();
		
		
	}

}
