package com.github.lottery.test;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import com.github.lottery.ConstantValue;
import com.github.lottery.bean.User;
import com.github.lottery.engine.UserEngine;
import com.github.lottery.engine.UserEngineImpl;
import com.github.lottery.net.CurrentIssueElement;
import com.github.lottery.net.protocal.Element;
import com.github.lottery.net.protocal.Message;
import com.github.lottery.util.BeanFactory;

import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Xml;

public class XmlTest extends AndroidTestCase {

	private static final String TAG = "XmlTest";
	public void creatXml() {
		Message message = new Message();
		CurrentIssueElement element = new CurrentIssueElement();
		element.getLotteryid().setTagValue("118");
		String xml = message.getXml(element);
		Log.i(TAG, xml);
	}
	public void creatXml2() {
		// 序列化
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			// This method can only be called just after setOutput
			serializer.startDocument(ConstantValue.ENCONDING, null);
			
			Message message = new Message();
			message.serializerMessage(serializer);
			
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void creatXml1() {
		// 序列化
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			// This method can only be called just after setOutput
			serializer.startDocument(ConstantValue.ENCONDING, null);
			
			serializer.startTag(null, "message");
			serializer.startTag(null, "header");
			
			serializer.startTag(null, "agenterid");
			serializer.text(ConstantValue.AGENTERID);
			serializer.endTag(null, "agenterid");
			
			
			serializer.startTag(null, "agenterid");
			serializer.text(ConstantValue.AGENTERID);
			serializer.endTag(null, "agenterid");
			
			
			serializer.startTag(null, "agenterid");
			serializer.text(ConstantValue.AGENTERID);
			serializer.endTag(null, "agenterid");
			
			serializer.endTag(null, "header");
			serializer.startTag(null, "body");
			serializer.endTag(null, "body");
			serializer.endTag(null, "message");
			
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testUserLogin(){
		
//		UserEngineImpl impl = new UserEngineImpl();
//		User user = new User();
//		user.setUsername("12005555");
//		user.setPassword("000000");
//		Message login = impl.login(user);
//		Log.i(TAG, login.getBody().getOelement().getErrorcode());
	
		UserEngine engine = BeanFactory.getImpl(UserEngine.class);
		User user = new User();
		user.setUsername("12005555");
		user.setPassword("000000");
		Message login = engine.login(user);
		Log.i(TAG, login.getBody().getOelement().getErrorcode());
		
		
	}
	
	
}
