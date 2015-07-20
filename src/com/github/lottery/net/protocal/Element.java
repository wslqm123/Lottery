package com.github.lottery.net.protocal;


import org.xmlpull.v1.XmlSerializer;

/**
 * 请求数据的封装
 * 
 * @author LQM
 *
 */
public abstract class Element {
	/**
	 * Element作为公共部分，所有请求需要自己实现
	 * 1.序列化自己
	 * 2.有自己的标识
	 */
	//序列化自己
	public abstract void serializerElement(XmlSerializer serializer);
	
	//有自己的标识
	public abstract String getTransactionType();
	
	/**
	 * 包含内容
	 * 序列化
	 * 特有：请求标识
	 */

//	// <lotteryid>118</lotteryid>
//	private Leaf lotteryid = new Leaf("lotteryid");
//
//	// <issues>1</issues>
//	private Leaf issues = new Leaf("issues", "1");
//
//	public Leaf getLotteryid() {
//		return lotteryid;
//	}

//	/**
//	 * 序列化请求
//	 */
//	public void serializerElement(XmlSerializer serializer) {
//
//		try {
//			serializer.startTag(null, "element");
//			lotteryid.serializerLeaf(serializer);
//			issues.serializerLeaf(serializer);
//			serializer.endTag(null, "element");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	/**
//	 * 获取请求标识
//	 */
//	public String getTransactionType(){
//		return "12002";
//		
//	}
	
}
