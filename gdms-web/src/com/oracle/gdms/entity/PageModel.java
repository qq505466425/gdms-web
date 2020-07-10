package com.oracle.gdms.entity;

import java.util.List;

public class PageModel<T> { //  T 泛型 通配类型
	private int current; // 当前页码
	private int total; // 总页数
	private List<T> data; // 数据集

	public static final int ROWS = 5; // 显示行数的常量 在配置文件写更好

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
