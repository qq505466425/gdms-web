package com.oracle.gdms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GoodsEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6117858178478383233L;
	private Integer goodsid;
	private String rowflag;
	private String name;
	private String spec;
	private GoodsType type; // 一方对象
	private int gtid;// 为推送商品类别的id
	private String unit;
	private float amount;
	private float price;
	private float agio;
	private String area;
	private String color;
	private String size;
	private float weight;
	private String photo;
	private long makedate;
	private String expirydate;
	private short status;
	private boolean push;
	
	public Timestamp getScrq() {
		return new Timestamp(makedate);
	}
	
	public int getGtid() {
		return gtid;
	}

	public void setGtid(int gtid) {
		this.gtid = gtid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public String getRowflag() {
		return rowflag;
	}

	public void setRowflag(String rowflag) {
		this.rowflag = rowflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAgio() {
		return agio;
	}

	public void setAgio(float agio) {
		this.agio = agio;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public long getMakedate() {
		return makedate;
	}

	public void setMakedate(long makedate) {
		this.makedate = makedate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public boolean isPush() {
		return push;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

}
