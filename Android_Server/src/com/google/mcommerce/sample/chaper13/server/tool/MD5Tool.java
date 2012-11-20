package com.google.mcommerce.sample.chaper13.server.tool;

import java.io.*;
import java.security.*;

public class MD5Tool {

	// ����������ϢժҪ���㷨��
	private String alg;
	// ��ϢժҪ�ַ�
	private String md;

	public MD5Tool() {
		// ����ڹ�������ָ���㷨��Ĭ�Ͻ�ʹ��MD5�㷨
		this.alg = "MD5";
	}

	public MD5Tool(String alg) {
		this.alg = alg;
	}

	/**
	 * ��һ���ļ���ָ���㷨����ַ��ʽ����ϢժҪ
	 * 
	 * @param fName
	 * @return
	 */
	public String getFileMD(String fName) {
		try {
			byte[] inByte = getFileBytes(fName);
			byte[] outByte = getMD(inByte);
			md = byteToStr(outByte);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return md;
	}

	/**
	 * ��һ���ַ���ָ���㷨����ַ��ʽ����ϢժҪ
	 * 
	 * @param str
	 * @return String
	 */
	public String getStrMD(String str) {
		if (str == null)
			return null;
		byte[] inByte = str.getBytes();
		byte[] outByte = getMD(inByte);
		md = byteToStr(outByte);
		return md;
	}

	/**
	 * ��һ���ֽ�������ֽ�����ʽ����ϢժҪ
	 * 
	 * @param inByte
	 * @return byte[]
	 */
	public byte[] getMD(byte[] inByte) {
		if (inByte == null) {
			return null;
		}
		byte[] hash = null;
		MessageDigest tMessageDigest;
		try {
			tMessageDigest = MessageDigest.getInstance(alg);
			tMessageDigest.update(inByte);
			hash = tMessageDigest.digest();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return hash;
	}

	/**
	 * ���ļ���õ�������Ƶ����
	 * 
	 * @param fName
	 * @return
	 * @throws IOException
	 */
	public byte[] getFileBytes(String fName) throws IOException {
		FileInputStream in = null;
		byte[] outByte = null;
		try {
			in = new FileInputStream(fName);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1)
				buffer.write(ch);
			outByte = buffer.toByteArray();
			return outByte;
		} catch (FileNotFoundException ex) {
			System.out.println("the specified file " + fName
					+ " does not exist.");
			return null;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * ������ת�ַ���
	 * 
	 * @param b
	 * @return
	 */
	public String byteToStr(byte[] b) {
		if (b == null)
			return null;
		String hs = "";
		for (int n = 0; n < b.length; n++) {
			int v = b[n] & 0xFF;
			if (v < 16)
				hs += "0";
			hs += Integer.toString(v, 16).toUpperCase();
		}
		return hs;
	}

	public String getAlg() {
		return this.alg;
	}
}
