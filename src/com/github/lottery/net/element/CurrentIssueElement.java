package com.github.lottery.net.element;

import org.xmlpull.v1.XmlSerializer;

import com.github.lottery.net.protocal.Element;
import com.github.lottery.net.protocal.Leaf;

/**
 * 获取当前销售期的请求
 * @author LQM
 *
 */
public class CurrentIssueElement extends Element {

	// <lotteryid>118</lotteryid>
	private Leaf lotteryid = new Leaf("lotteryid");

	// <issues>1</issues>
	private Leaf issues = new Leaf("issues", "1");
	
	@Override
	public void serializerElement(XmlSerializer serializer) {
		// TODO Auto-generated method stub
		try {
			serializer.startTag(null, "element");
			lotteryid.serializerLeaf(serializer);
			issues.serializerLeaf(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactionType() {
		// TODO Auto-generated method stub
		return "12002";
	}

	public Leaf getLotteryid() {
		return lotteryid;
	}

}
