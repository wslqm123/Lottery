package com.github.lottery.net.protocal;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.github.lottery.ConstantValue;

/**
 * 封装协议
 * @author LQM
 *
 */
public class Message {

	private Header header = new Header();
	private Body body = new Body();
	

	/**
	 * 序列化协议
	 */
	public void serializerMessage(XmlSerializer serializer) {

		try {
			//<message version = "1.0">
			serializer.startTag(null, "message");
			// MUST follow a call to startTag() immediately
			serializer.attribute(null, "version", "1.0");
			header.serializerHeader(serializer, body.getWholeBody());//获取完整的body
			//body.serializerBody(serializer);
			serializer.startTag(null, "body");
			serializer.text(body.getBodyInsideDesInfo());
			serializer.endTag(null, "body");
			serializer.endTag(null, "message");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 获取请求的Xml文件
	 * @return
	 */
	public String getXml(Element element){
		if (element == null) {
			throw new IllegalArgumentException("element is null");
		}
		
		//需要设置请求标识与请求内容
		header.getTransactiontype().setTagValue(element.getTransactionType());
		body.getElements().add(element);
		
		// 序列化
				XmlSerializer serializer = Xml.newSerializer();
				StringWriter writer = new StringWriter();
				try {
					serializer.setOutput(writer);
					// This method can only be called just after setOutput
					serializer.startDocument(ConstantValue.ENCONDING, null);
					
					this.serializerMessage(serializer);

					serializer.endDocument();
					return writer.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}
	
}
