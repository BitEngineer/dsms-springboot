package com.lonton.dsms.base.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * 菜单项
 */
public class Menu {
	private String resId;
	private String text;
	private String resUrl;
	private String parentId;
	private String treePath;
	private String resOrder;
	private String resIcon;
	private List<Menu> children = new LinkedList<Menu>();
	
	public Menu(Res res){
		this.resId = res.getResId();
		this.text = res.getResName();
		this.resUrl = res.getResUrl();
		this.parentId = res.getParentId();
		this.resOrder = res.getResOrder();
		this.resIcon = res.getResIcon();
		this.treePath = res.getTreePath();
	}
	
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResOrder() {
		return resOrder;
	}
	public void setResOrder(String resOrder) {
		this.resOrder = resOrder;
	}
	public String getResIcon() {
		return resIcon;
	}
	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	public void addChild(Menu child){
		if(!this.children.contains(child)){
			this.children.add(child);
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
}
