package com.oracle.gdms.entity;

import javax.xml.bind.annotation.XmlRootElement;
//表示可以与xml转换作为根节点
@XmlRootElement
public class ResponseEntity {

	private int code;
	private String msg;
	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
