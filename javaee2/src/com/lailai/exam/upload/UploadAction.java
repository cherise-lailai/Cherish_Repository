package com.lailai.exam.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class UploadAction extends ActionSupport {
	private File ppt;
	//�ļ�����
	private String pptContentType;
	//�ļ���
	private String pptFileName;
	
	private String pptDesc;

	public File getPpt() {
		return ppt;
	}

	public void setPpt(File ppt) {
		this.ppt = ppt;
	}

	public String getPptContentType() {
		return pptContentType;
	}

	public void setPptContentType(String pptContentType) {
		this.pptContentType = pptContentType;
	}

	public String getPptFileName() {
		return pptFileName;
	}

	public void setPptFileName(String pptFileName) {
		this.pptFileName = pptFileName;
	}

	public String getPptDesc() {
		return pptDesc;
	}

	public void setPptDesc(String pptDesc) {
		this.pptDesc = pptDesc;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		

		System.out.println(ppt);
		System.out.println(pptContentType);
		System.out.println(pptFileName);
		System.out.println(pptDesc);
		
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String dir = servletContext.getRealPath("/files/" + pptFileName);
		System.out.println(dir);
		
		FileOutputStream out = new FileOutputStream(dir);
		FileInputStream in = new FileInputStream(ppt);
		
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}	
		out.close();
		in.close();
		
		return "success";
	}
	
	

}
