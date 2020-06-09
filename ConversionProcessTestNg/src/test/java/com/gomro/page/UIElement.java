package com.gomro.page;

public class UIElement {
	//元素名称
	private String name;
	//定位方式
	private String by;
	//定位值
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "UIElement [name=" + name + ", by=" + by + ", value=" + value + "]";
	}

}
