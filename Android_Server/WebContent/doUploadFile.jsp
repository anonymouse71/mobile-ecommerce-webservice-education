<%@page import="java.text.DecimalFormat"%>
<%
//**************************************************************************************************
//Name：UploadFile.jsp
//Function：接收上传文件 
//Author：chixin
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

	//获取工程路径
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println("save:"+path);
	System.out.println("save:"+basePath);
	
	System.out.println("---Upload File Start---");
	//获取上载文件相关参数  
	String downPath = "";
	//基础文件夹路径 暂时默认为服务器的E盘下某路径
//  String saveBasePath = "E:/Documents and Settings/Administrator/Androidworkspace/AndroidServer/WebContent/uploadfile/";
//	String saveBasePath = "f:/AndroidServer/WebContent/uploadfile/";
	String saveBasePath = "E:/workspace/game/AndroidServer/WebContent/uploadfile/";
	FileInfo fileInfoSchema = new FileInfo();

	String saveName = "";//文件存储名 由系统生成
	String getUpFileName =null;//文件自身名
	String fileNo = "";
	String type="";//文件扩展名
	DiskFileUpload fu = new DiskFileUpload();

	//设置允许用户上传文件大小,单位:字节,10M
	fu.setSizeMax(100*1024*1024);
	//设置最多只允许在内存中存储的数据,单位:字节
	fu.setSizeThreshold(1024*1024);
	//设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
	fu.setRepositoryPath(saveBasePath);
	

	//开始读取上传信息
		
		List fileItems = fu.parseRequest(request);
		Iterator iter = fileItems.iterator();
	
		FileDo fileDo=new FileDo();
		while (iter.hasNext()){	
			FileItem item = (FileItem) iter.next();
//	        System.out.println("request     ****"+item.getFieldName());
			//忽略其他不是文件域的所有表单信息
			if (!item.isFormField())
			{
				String upFileName = item.getName();//获取文件本身的文件名(有中文乱码)	
				upFileName=(UUID.randomUUID()).toString()+"."+type;//随机名字作为文件存储名的一部分
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
				System.out.println("文件名为=" + upFileName);
				long size = item.getSize();				
				if((upFileName==null||upFileName.equals("")) && size==0)
				continue;
			
				fileInfoSchema.setFileSize(String.valueOf(size));//文件大小	
				
//				fileName = upFileName.replace('\\','/');				
//				fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
				String mCurrentDate = Tools.getCurrentDate();
	  			String mCurrentTime = Tools.getCurrentTime();
	  			fileInfoSchema.setUpDate(mCurrentDate);//上传日期
	  			fileInfoSchema.setUpTime(mCurrentTime);//上传时间
	  			
	  			mCurrentDate = mCurrentDate.replace("-","");
				mCurrentTime = mCurrentTime.replace(":","");
				fileNo=mCurrentDate+mCurrentTime;//编号前半部分 20120821142334 (日期+时间)
				//查询文件表记录的条数
				int no=fileDo.newFileNo(1)+1;
				//文件编号到100000000时 清零
				if(no==100000000){
					no=0;
				}
				fileNo=fileNo+new DecimalFormat("00000000").format(no);//格式化文件编号
				
				System.out.println(no+"--->>fileNo="+fileNo);
				
				fileInfoSchema.setFileNo(fileNo);//文件编号
				saveName = "("+mCurrentDate+"_"+mCurrentTime+")"+upFileName;//文件存储名
				saveBasePath = saveBasePath + mCurrentDate+"/";
				
				String filePath=saveBasePath + saveName;//文件存储路径
				String fileDownPath=basePath+"uploadfile/"+mCurrentDate+"/"+saveName;
				fileInfoSchema.setDownPath(fileDownPath);//下载路径
				//保存上传的文件到指定的目录
				try
				{
					//01 先判断存储目录是否存在，不存在则先创建文件夹
					File temp = new File(saveBasePath);
					if(!temp.exists()){
						Tools.makeFolder(saveBasePath);
					}
					//02 存储文件
					item.write(new File(filePath));
				}
				catch(Exception e){
					System.out.println("upload file error ...");
					return;
				}
				System.out.println("文件存储成功："+saveBasePath + saveName);
			}else{

				if("descName".equals(item.getFieldName())){				
					String GBK_Name=Tools.getGBK_Str(item.getString());	//编码转换，解决中文乱码
					fileInfoSchema.setFileDescName(GBK_Name);//文件描述名
					System.out.println("descName="+GBK_Name);
				}
				if("remark".equals(item.getFieldName())){
					
					System.out.println("uper   ***="+item.getString()+"========="+item.getContentType());
					String GBK_Mark=Tools.getGBK_Str(item.getString()+"");
					fileInfoSchema.setRemark(GBK_Mark);//文件描述
					System.out.println("remark=**888***="+GBK_Mark);
				}
				if("uper".equals(item.getFieldName())){
		
					String GBK_Uper=Tools.getGBK_Str(item.getString());
					fileInfoSchema.setUper(GBK_Uper);//上传者
					System.out.println("uper=*******="+GBK_Uper);
				}
				if("upfileName".equals(item.getFieldName())){
					getUpFileName=Tools.getGBK_Str(item.getString().trim());
					fileInfoSchema.setUpFileName(getUpFileName);//设置文件真实名
					type=getUpFileName.substring(getUpFileName.lastIndexOf(".")+1);
					fileInfoSchema.setType(type);//设置文件类型
					System.out.println("upfileName=***="+getUpFileName);
				}
				
			}	
			fileInfoSchema.setDownTimer(0);//下载次数
		}
			//文件信息存入数据库
			
			
			int rows=fileDo.addFile(fileInfoSchema);
			if(rows>0){
				System.out.println("上传成功");
 %>					
				<b>true</b><br>
<%				
			}else{
				
				File file=new File(saveBasePath + saveName);
				file.delete();
				System.out.println("上传失败");
 %>				
				<b>false</b>
<%			
			}
 %>	
