package com.github.lottery.net;

import org.xmlpull.v1.XmlSerializer;

import com.github.lottery.net.protocal.Element;
import com.github.lottery.net.protocal.Leaf;

/**
 * 用户登录用请求
 * @author LQM
 *
 */
public class UserLoginElement extends Element {

	private Leaf actpassword = new Leaf("actpassword");
	
	public Leaf getActpassword() {
		return actpassword;
	}

	@Override
	public void serializerElement(XmlSerializer serializer) {
		
		try {
			serializer.startTag(null, "element");
			actpassword.serializerLeaf(serializer);
			serializer.endTag(null, "element");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTransactionType() {
		// TODO Auto-generated method stub
		return "14001";
	}

}
