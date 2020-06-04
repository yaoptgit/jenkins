package com.gomro.page;

import java.util.ArrayList;
import java.util.List;

public class Page {
	//页面名称
	private String pageName;
	//页面所有元素
	private List<UIElement> uiElements = new ArrayList<UIElement>();
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public List<UIElement> getUiElements() {
		return uiElements;
	}
	public void setUiElements(List<UIElement> uiElements) {
		this.uiElements = uiElements;
	}
	@Override
	public String toString() {
		return "Page [pageName=" + pageName + ", uiElements=" + uiElements + "]";
	}
	
}
