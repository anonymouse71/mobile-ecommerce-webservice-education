<%@page import="java.text.DecimalFormat"%>
<%
//**************************************************************************************************
//Name��UploadFile.jsp
//Function�������ϴ��ļ� 
//Author��chixin
//Date: 20120821 
//**************************************************************************************************
%>
<%@page contentType="text/html;charset=gbk" %>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="com.google.mcommerce.sample.chaper13.server.entity.*"%>
<%@page import="com.google.mcommerce.sample.chaper13.server.tool.*"%>
<%@page import="com.google.mcommerce.sample.chaper13.server.database.*" %>
<%@page import="com.google.mcommerce.sample.chaper13.server.app.TransCodeChooser" %>
<%

	//��ȡ����·��
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println("save:"+path);
	System.out.println("save:"+basePath);
	
	System.out.println("---Upload File Start---");
	//��ȡ�����ļ���ز���  
	String downPath = "";
	//�����ļ���·�� ��ʱĬ��Ϊ��������E����ĳ·��
//  String saveBasePath = "E:/Documents and Settings/Administrator/Androidworkspace/AndroidServer/WebContent/uploadfile/";
//	String saveBasePath = "f:/AndroidServer/WebContent/uploadfile/";
	String saveBasePath = "E:/workspace/game/AndroidServer/WebContent/uploadfile/";
	FileInfo fileInfoSchema = new FileInfo();

	String saveName = "";//�ļ��洢�� ��ϵͳ����
	String getUpFileName =null;//�ļ�������
	String fileNo = "";
	String type="";//�ļ���չ��
	DiskFileUpload fu = new DiskFileUpload();

	//���������û��ϴ��ļ���С,��λ:�ֽ�,10M
	fu.setSizeMax(100*1024*1024);
	//�������ֻ�������ڴ��д洢������,��λ:�ֽ�
	fu.setSizeThreshold(1024*1024);
	//����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼
	fu.setRepositoryPath(saveBasePath);
	

	//��ʼ��ȡ�ϴ���Ϣ
		
		List fileItems = fu.parseRequest(request);
		Iterator iter = fileItems.iterator();
	
		FileDo fileDo=new FileDo();
		while (iter.hasNext()){	
			FileItem item = (FileItem) iter.next();
//	        System.out.println("request     ****"+item.getFieldName());
			//�������������ļ�������б���Ϣ
			if (!item.isFormField())
			{
				String upFileName = item.getName();//��ȡ�ļ�������ļ���(����������)	
				upFileName=(UUID.randomUUID()).toString()+"."+type;//���������Ϊ�ļ��洢����һ����
			/*	if(getUpFileName!=null){
					upFileName=getUpFileName;
				}else{
					continue;
				}
			*/
				//byte[]by=item.getName().getBytes();
				//System.out.println(Arrays.toString(by));			
			//upFileName = upFileName.replace('\\','/');
			//upFileName = upFileName.substring(upFileName.lastIndexOf("/") + 1);
//				upFileName = upFileName.replace("'"," ");
//				upFileName=new String(upFileName.getBytes("ISO-8859-1"),"utf-8");			
				System.out.println("�ļ���Ϊ=" + upFileName);
				long size = item.getSize();				
				if((upFileName==null||upFileName.equals("")) && size==0)
				continue;
			
				fileInfoSchema.setFileSize(String.valueOf(size));//�ļ���С	
				
//				fileName = upFileName.replace('\\','/');				
//				fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
				String mCurrentDate = Tools.getCurrentDate();
	  			String mCurrentTime = Tools.getCurrentTime();
	  			fileInfoSchema.setUpDate(mCurrentDate);//�ϴ�����
	  			fileInfoSchema.setUpTime(mCurrentTime);//�ϴ�ʱ��
	  			
	  			mCurrentDate = mCurrentDate.replace("-","");
				mCurrentTime = mCurrentTime.replace(":","");
				fileNo=mCurrentDate+mCurrentTime;//���ǰ�벿�� 20120821142334 (����+ʱ��)
				//��ѯ�ļ����¼������
				int no=fileDo.newFileNo(1)+1;
				//�ļ���ŵ�100000000ʱ ����
				if(no==100000000){
					no=0;
				}
				fileNo=fileNo+new DecimalFormat("00000000").format(no);//��ʽ���ļ����
				
				System.out.println(no+"--->>fileNo="+fileNo);
				
				fileInfoSchema.setFileNo(fileNo);//�ļ����
				saveName = "("+mCurrentDate+"_"+mCurrentTime+")"+upFileName;//�ļ��洢��
				saveBasePath = saveBasePath + mCurrentDate+"/";
				
				String filePath=saveBasePath + saveName;//�ļ��洢·��
				String fileDownPath=basePath+"uploadfile/"+mCurrentDate+"/"+saveName;
				fileInfoSchema.setDownPath(fileDownPath);//����·��
				//�����ϴ����ļ���ָ����Ŀ¼
				try
				{
					//01 ���жϴ洢Ŀ¼�Ƿ���ڣ����������ȴ����ļ���
					File temp = new File(saveBasePath);
					if(!temp.exists()){
						Tools.makeFolder(saveBasePath);
					}
					//02 �洢�ļ�
					item.write(new File(filePath));
				}
				catch(Exception e){
					System.out.println("upload file error ...");
					return;
				}
				System.out.println("�ļ��洢�ɹ���"+saveBasePath + saveName);
			}else{

				if("descName".equals(item.getFieldName())){				
					String GBK_Name=Tools.getGBK_Str(item.getString());	//����ת���������������
					fileInfoSchema.setFileDescName(GBK_Name);//�ļ�������
					System.out.println("descName="+GBK_Name);
				}
				if("remark".equals(item.getFieldName())){
					
					System.out.println("uper   ***="+item.getString()+"========="+item.getContentType());
					String GBK_Mark=Tools.getGBK_Str(item.getString()+"");
					fileInfoSchema.setRemark(GBK_Mark);//�ļ�����
					System.out.println("remark=**888***="+GBK_Mark);
				}
				if("uper".equals(item.getFieldName())){
		
					String GBK_Uper=Tools.getGBK_Str(item.getString());
					fileInfoSchema.setUper(GBK_Uper);//�ϴ���
					System.out.println("uper=*******="+GBK_Uper);
				}
				if("upfileName".equals(item.getFieldName())){
					getUpFileName=Tools.getGBK_Str(item.getString().trim());
					fileInfoSchema.setUpFileName(getUpFileName);//�����ļ���ʵ��
					type=getUpFileName.substring(getUpFileName.lastIndexOf(".")+1);
					fileInfoSchema.setType(type);//�����ļ�����
					System.out.println("upfileName=***="+getUpFileName);
				}
				
			}	
			fileInfoSchema.setDownTimer(0);//���ش���
		}
			//�ļ���Ϣ�������ݿ�
			
			
			int rows=fileDo.addFile(fileInfoSchema);
			if(rows>0){
				System.out.println("�ϴ��ɹ�");
 %>					
				<b>true</b><br>
<%				
			}else{
				
				File file=new File(saveBasePath + saveName);
				file.delete();
				System.out.println("�ϴ�ʧ��");
 %>				
				<b>false</b>
<%			
			}
 %>	
