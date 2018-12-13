package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;  
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;  
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.ServletException;  
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.db.DBConn;
import com.pojo.Article;
import com.pojo.InputMessage;
import com.pojo.MsgType;
import com.pojo.OutputMessage;
import com.pojo.PicAndTextMsg;
import com.pojo.TextOutputMessage;
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.core.util.QuickWriter;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.utils.MyX509TrustManager;
import com.weixin.menu.Button;
import com.weixin.menu.ClickButton;
import com.weixin.menu.ComplexButton;
import com.weixin.menu.Menu;
import com.weixin.menu.ViewButton;
import com.weixin.util.SignUtil;

import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;
  

public class WeiXinServlet extends HttpServlet {  
  
    private static final long serialVersionUID = 7534232612712558319L;  
    
    private Connection conn = null;
  
    public WeiXinServlet() {  
        super();  
    }  
  
        	  @Override
        	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        	      throws ServletException, IOException {
        		  try {  
        	            // 微信加密签名  
        	            String signature = request.getParameter("signature");  
        	            // 时间戳  
        	            String timestamp = request.getParameter("timestamp");  
        	            // 随机数  
        	            String nonce = request.getParameter("nonce");  
        	            // 随机字符串  
        	            String echostr = request.getParameter("echostr");  
        	  
        	            PrintWriter out = response.getWriter();  
        	            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        	            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
        	                out.print(echostr);  
        	            }  
        	            out.close();  
        	            out = null;  
        	           
        	  
        	        } catch (Exception e) {  
        	        }  
        	  }
        	
        	  @Override
        	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
        	      throws ServletException, IOException {
        	    // 设置编码
        		  request.setCharacterEncoding("utf-8");
        		  response.setContentType("html/text;charset=utf-8");
        		  response.setCharacterEncoding("utf-8");
        	 
                System.out.println("获得微信请求:" + request.getMethod() + " 方式");  
               
                    //处理接收消息    
                    ServletInputStream in = request.getInputStream();  
                    //将POST流转换为XStream对象  
                    XStream xs = new XStream(new DomDriver());  
                    //将指定节点下的xml节点数据映射为对象  
                    xs.alias("xml", InputMessage.class);  
                    //将流转换为字符串  
                    StringBuilder xmlMsg = new StringBuilder();  
                    byte[] b = new byte[4096];  
                    for (int n; (n = in.read(b)) != -1;) {  
                        xmlMsg.append(new String(b, 0, n, "UTF-8"));  
                    }  
                    //将xml内容转换为InputMessage对象  
                    InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());  
                    // 取得消息类型  
                    String msgType = inputMsg.getMsgType();  
                  
                  //根据消息类型获取对应的消息内容  
                    if (msgType.equals(MsgType.Text.toString())) {  
                    	try {  
                            //文本消息  
                            System.out.println("开发者微信号：" + inputMsg.getToUserName());  
                            System.out.println("发送方帐号：" + inputMsg.getFromUserName());  
                            System.out.println("消息创建时间：" + inputMsg.getCreateTime());  
                            System.out.println("消息内容：" + inputMsg.getContent());  
                            System.out.println("消息Id：" + inputMsg.getMsgId());  
                            //发送文本消息 start  
                            XStream xstream = new XStream(new XppDriver() {  
                                @Override  
                                public HierarchicalStreamWriter createWriter(Writer out) {  
                                    return new PrettyPrintWriter(out) {  
                                        @Override  
                                        protected void writeText(QuickWriter writer,  
                                                                        String text) {  
                                            if (!text.startsWith("<![CDATA[")) {  
                                                text = "<![CDATA[" + text + "]]>";  
                                            }  
                                            writer.write(text);  
                                        }  
                                    };  
                                }  
                            });  
                            //创建文本发送消息对象  
                            TextOutputMessage outputMsg = new TextOutputMessage();  
                            outputMsg.setContent("你的消息已经收到，谢谢！");  
                            setOutputMsgInfo(outputMsg, inputMsg);  
                            //设置对象转换的XML根节点为xml  
                            xstream.alias("xml", outputMsg.getClass());  
                            //将对象转换为XML字符串  
                            String xml = xstream.toXML(outputMsg);  
                            //将内容发送给微信服务器，发送到用户手机  
                            response.getWriter().write(xml);  
                        } catch (Exception ex) {  
                            System.out.println("消息接受和发送出现异常!");  
                            ex.printStackTrace();  
                        }  
                    }  else if(msgType.equals(MsgType.Image.toString())) {  
                    	try {  
                    		 List<Article> articles = new ArrayList<Article>();
                    		    Article a = new Article();
                    		    a.setTitle("我是图片标题");
                    		    a.setUrl("www.baidu.com");// 该地址是点击图片跳转后
                    		    a.setPicUrl("http://b.hiphotos.baidu.com/image/pic/item/08f790529822720ea5d058ba7ccb0a46f21fab50.jpg");// 该地址是一个有效的图片地址
                    		    a.setDescription("我是图片的描述");
                    		    articles.add(a);
                    		    PicAndTextMsg outputMsg = new PicAndTextMsg();  
                    		    setOutputMsgInfo(outputMsg, inputMsg);  
                    		    outputMsg.setMsgType("news");// 图文类型消息
                    		    outputMsg.setArticleCount(1);
                    		    outputMsg.setArticles(articles);
                    		    // 第二步，将构造的信息转化为微信识别的xml格式【百度：xstream bean转xml】
                    		    XStream xStream = new XStream();
                    		    xStream.alias("xml", outputMsg.getClass());
                    		    xStream.alias("item", a.getClass());
                    		    String picAndTextMsg2Xml = xStream.toXML(outputMsg);
                                //将内容发送给微信服务器，发送到用户手机  
                                response.getWriter().write(picAndTextMsg2Xml);  
                                try {
                                	ClickButton btn1=new ClickButton();
                            		btn1.setKey("image");
                            		btn1.setName("回复图片");
                            		btn1.setType("click");
                            		
                            		
                            		ViewButton btn2=new ViewButton();
                            		btn2.setUrl("http://www.cuiyongzhi.com");
                            		btn2.setName("博客");
                            		btn2.setType("view");
                            		
                            		ClickButton btn31=new ClickButton();
                            		btn31.setKey("image");
                            		btn31.setName("赞一下我们");
                            		btn31.setType("click");
                            		
                            		ClickButton btn32=new ClickButton();
                            		btn32.setKey("image");
                            		btn32.setName("赞一下我们");
                            		btn32.setType("click");
                            		
                            		ComplexButton btn3= new ComplexButton();
                            		btn3.setName("菜单");
                            		btn3.setSub_button(new Button[]{btn31,btn32});
                            		
                            		
                            		Menu menu = new Menu();
                            		menu.setButton(new Button[]{btn1,btn2,btn3});
                            		String jsonMenu = JSONObject.fromObject(menu).toString();
                                String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
                                URL url = new URL(menuCreateUrl);
                                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                                TrustManager[] tm = { new MyX509TrustManager()};
                               
            						SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            						sslContext.init(null, tm, new SecureRandom());
            						SSLSocketFactory ssf = sslContext.getSocketFactory();
            						conn.setSSLSocketFactory(ssf);
            						conn.setDoOutput(true);
            						conn.setDoInput(true);
            						conn.setRequestMethod("POST");
            						
            						//向输出流写菜单结构
            						OutputStream outputStream = conn.getOutputStream();
            						outputStream.write(jsonMenu.getBytes("UTF-8"));
            						outputStream.close();
            						
            						//取得输入流
            						InputStream inputStream = conn.getInputStream();
            						InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            						
            						//读取相应内容
            						StringBuffer buffer = new StringBuffer();
            						String str = null;
            						while((str=bufferedReader.readLine())!=null){
            							buffer.append(str);
            						}
            						
            						//关闭 ／释放资源
            						bufferedReader.close();
            						inputStreamReader.close();
            						inputStream.close();
            						conn.disconnect();
            						
            						//输出菜单创建结果
            						System.out.println(buffer);
            					} catch (NoSuchAlgorithmException e) {
            						// TODO 自动生成的 catch 块
            						e.printStackTrace();
            					} catch (NoSuchProviderException e) {
            						// TODO 自动生成的 catch 块
            						e.printStackTrace();
            					} catch (KeyManagementException e) {
            						// TODO 自动生成的 catch 块
            						e.printStackTrace();
            					}
                    	} catch (Exception ex) {  
                            System.out.println("图文消息接受和发送出现异常!");  
                            ex.printStackTrace();  
                        }  
                    }
                   
                    
    }  
//设置详细信息  
private static void setOutputMsgInfo(OutputMessage oms,  
                        InputMessage msg) throws Exception {  
    // 设置发送信息  
    Class<?> outMsg = oms.getClass().getSuperclass();  
    Field CreateTime = outMsg.getDeclaredField("CreateTime");  
    Field ToUserName = outMsg.getDeclaredField("ToUserName");  
    Field FromUserName = outMsg.getDeclaredField("FromUserName");  

    ToUserName.setAccessible(true);  
    CreateTime.setAccessible(true);  
    FromUserName.setAccessible(true);  

    CreateTime.set(oms, new Date().getTime());  
    ToUserName.set(oms, msg.getFromUserName());  
    FromUserName.set(oms, msg.getToUserName());  
}  
  
   
}  

