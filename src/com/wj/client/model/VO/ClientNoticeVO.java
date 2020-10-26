package com.wj.client.model.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientNoticeVO {
	private String clno;
	private String id;
	private String title;
	private String content;
	private String sdate;
	
	public String getClno() {
		return clno;
	}
	public void setClno(String clno) {
		this.clno = clno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd");
		this.sdate = s.format(sdate);
	}
	
	
}
