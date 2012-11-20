package com.google.mcommerce.sample.chaper13.server.entity;

/**
 * �ļ���Ϣ�� ����ݿ���FileInfo���ֶζ�Ӧ
 * 
 * @author Administrator chixin 20120822
 * 
 */
public class FileInfo {
	private String fileNo = null;// �ļ����
	private String fileDescName = null;// �ļ�������
	private String remark = null;// �ļ�����
	private String fileSize = null;
	private String upDate = null;
	private String upTime = null;
	private String uper = null;// �ϴ���
	private String upFileName = null;// �ļ���ʵ��
	private String downPath = null;
	private int downTimer = 0;//
    private int fileNum=0;
    private String type=null;//�ļ�����
    
	public FileInfo() {
	}

	public FileInfo(String fileNo,String fileDescName,String type,String remark,String fileSize,String upDate
			,String upTime,String uper,String upFileName,String downPath,int downTimer) {
		this.fileNo=fileNo;
		this.fileDescName=fileDescName;
		this.remark=remark;
		this.fileSize=fileSize;
		this.upDate=upDate;
		this.upTime=upTime;
		this.uper=uper;
		this.upFileName=upFileName;
		this.downPath=downPath;
		this.downTimer=downTimer;
		this.type=type;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileDescName() {
		return fileDescName;
	}

	public void setFileDescName(String fileDescName) {
		this.fileDescName = fileDescName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getUper() {
		return uper;
	}

	public void setUper(String uper) {
		this.uper = uper;
	}

	public String getUpFileName() {
		return upFileName;
	}

	public void setUpFileName(String upFileName) {
		this.upFileName = upFileName;
	}

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}

	public int getDownTimer() {
		return downTimer;
	}

	public void setDownTimer(int downTimer) {
		this.downTimer = downTimer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
