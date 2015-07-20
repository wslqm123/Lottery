package com.github.lottery.net.protocal;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * 简单的叶子
 * @author LQM
 *
 */
public class Leaf {
	//<agenterid>889931</agenterid>
	//思路：1.包含的内容；2、序列化Xml
	private String tagName;
	private String tagValue;
	
	//每个叶子都需要指定标签名称
	public Leaf(String tagName) {
		super();
		this.tagName = tagName;
	}
	
	//处理常量
	public Leaf(String tagName, String tagValue) {
		super();
		this.tagName = tagName;
		this.tagValue = tagValue;
	}

	
	
	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * 序列化叶子
	 * @param serializer
	 */
	public void serializerLeaf(XmlSerializer serializer){
		
		try {
			serializer.startTag(null, tagName);
			if (tagValue==null) {
				tagValue = "";
			}
			serializer.text(tagValue);
			serializer.endTag(null, tagName);
			//System.out.println("叶子");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
