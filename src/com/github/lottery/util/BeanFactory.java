package com.github.lottery.util;

import java.io.IOException;
import java.util.Properties;

import com.github.lottery.engine.UserEngine;

/**
 * 工厂类
 * @author LQM
 *
 */
public class BeanFactory {
	//依据配置文件加载实例
	
	private static Properties properties;
	static{
		properties = new Properties();
		//bean.properties必须在src目录下
		try {
			properties.load(BeanFactory.class.getResourceAsStream("bean.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 加载需要的实现类
	 * @param clazz
	 * @return
	 */
	public static<T> T getImpl(Class<T> clazz){
		
		String key = clazz.getSimpleName();
		String className = properties.getProperty(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
