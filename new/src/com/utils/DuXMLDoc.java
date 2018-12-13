package com.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class DuXMLDoc {

	public List xmlElements(String xmlDoc) {
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            System.out.println("root.getName():"+root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            Namespace ns = root.getNamespace();
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
                System.out.println("users_id:"+et.getChild("users_id",ns).getText());
                System.out.println("users_address:"+et.getChild("users_address",ns).getText());
            }
           
            et = (Element) jiedian.get(0);
            List zjiedian = et.getChildren();
            for(int j=0;j<zjiedian.size();j++){
                Element xet = (Element) zjiedian.get(j);
                System.out.println("Element:"+xet.getName());
            }
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return null;
    }
	
	public static String xmlElement(String xmlDoc,String str) {
		String msg="";
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            System.out.println("root.getName():"+root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            Namespace ns = root.getNamespace();
            System.out.println("size:"+jiedian.size());
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
                //System.out.println(str+":"+et.getChild("code",ns).getText());
            }
            et = (Element) jiedian.get(0);
            List zjiedian = et.getChildren();
            for(int j=0;j<zjiedian.size();j++){
                Element xet = (Element) zjiedian.get(j);
                System.out.println("Element:"+xet.getName());
            }
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return msg;
    }
    public static void main(String[] args){
        DuXMLDoc doc = new DuXMLDoc();
        String xml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"+
        "<Result xmlns=\"http://www.fiorano.com/fesb/activity/DBQueryOnInput2/Out\">"+
           "<row resultcount=\"1\">"+
              "<users_id>1001     </users_id>"+
              "<users_name>wangwei   </users_name>"+
              "<users_group>80        </users_group>"+
              "<users_address>1001号   </users_address>"+
           "</row>"+
           "<row resultcount=\"1\">"+
              "<users_id>1002     </users_id>"+
              "<users_name>wangwei   </users_name>"+
              "<users_group>80        </users_group>"+
              "<users_address>1002号   </users_address>"+
           "</row>"+
        "</Result>";
        
        String xml1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
"<SubmitResult xmlns=\"http://106.ihuyi.cn/\">"+
"<code>2</code>"+
"<msg>提交成功</msg>"+
"<smsid>14919905892844138415</smsid>"+
"</SubmitResult>";
        doc.xmlElements(xml1);
        //System.out.println("msg:"+DuXMLDoc.xmlElement(xml1, "msg"));
    }

}
