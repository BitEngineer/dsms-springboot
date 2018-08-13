package com.lonton.dsms.base.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * 资源树节点
 * @author 邓键
 */
public class ResTreeNode {
	private String resId;
	private String resCode;
	private String text;
	private String resType;
	private String resUrl;
	private String relative;
	private String parentId;
	private String treePath;
	private String resOrder;
	private String resIcon;
	private String available;
	private String visible;
	
	private List<ResTreeNode> children;
	
	public ResTreeNode(Res res){
		this.resId = res.getResId();
		this.resCode = res.getResCode();
		this.text = res.getResName();
		this.resType = res.getResType();
		this.resUrl = res.getResUrl();
		this.relative = res.getRelative();
		this.parentId = res.getParentId();
		this.treePath = res.getTreePath();
		this.resOrder = res.getResOrder();
		this.resIcon = res.getResIcon();
		this.available = res.getAvailable();
		this.visible = res.getVisible();
		
		this.children = new LinkedList<ResTreeNode>();
	}
	
	/**添加子节点*/
	public void addChild(ResTreeNode resTreeNode) {
		this.children.add(resTreeNode);
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
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

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public List<ResTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ResTreeNode> children) {
		this.children = children;
	}
	
}
