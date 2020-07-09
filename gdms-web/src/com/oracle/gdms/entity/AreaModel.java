package com.oracle.gdms.entity;

import java.io.Serializable;

public class AreaModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2603035952980527580L;
	private Integer areaid;
	private String name;
	private String type;
	private Integer parentid;
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

}
