package com.weixin.pojo.test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		StringBuffer buffer  = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<person>");
		buffer.append("<name>张三李四</name>");
		buffer.append("<sex>男</sex>");
		buffer.append("<address>广东省广州市海珠区</address>");
		buffer.append("</person>");
		//通过解析XML字符串创建Document对象
		Document document = DocumentHelper.parseText(buffer.toString());
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for(Element e:elementList){
			System.out.println(e.getName()+"=>"+e.getText());
		}
	}

}
