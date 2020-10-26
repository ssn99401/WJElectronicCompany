package com.wj.employees.model.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentVO {
	private int cono;
	private int asno;
	private String id;
	private String date;
	private int parent;
	private String content;
	public int getCono() {
		return cono;
	}
	public void setCono(int cono) {
		this.cono = cono;
	}
	public int getAsno() {
		return asno;
	}
	public void setAsno(int asno) {
		this.asno = asno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd");
		this.date = s.format(date);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	
	
}
