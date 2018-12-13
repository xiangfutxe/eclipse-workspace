package com.weixin.menu;

import net.sf.json.JSONObject;


/**
 * @author 高远</n> 邮箱：wgyscsf@163.com</n> 博客 http://blog.csdn.net/wgyscsf</n>
 *         编写时期 2016-4-6 下午8:44:43
 */
/*
 * 该类测试了直接获取Access_token和保存后判断Access_token存活时间后再获取两种方式【使用时，使用后者】。
 */
/*
 * 使用：createCommMenu()//常见自定义菜单；createSpecialMenuJson()//创建个性化菜单
 */
public class CopyOfMenu {

	public static void main(String[] args) {
		
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
		System.out.println(jsonMenu);
	
	}
}
