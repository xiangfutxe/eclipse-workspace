package com.ssm.weixin.pojo.menu;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FatherButton  extends ViewButton{
	private String button;//可能直接一个父按钮做响应
	
	@SerializedName("sub_button")//为了保证Gson解析后子按钮的名字是“sub_button”，具体用法请搜索
	private List<ComplexButton> sonButtons;//可能有多个子按钮
	
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public List<ComplexButton> getSonButtons() {
		return sonButtons;
	}
	public void setSonButtons(List<ComplexButton> sonButtons) {
		this.sonButtons = sonButtons;
	}
	
}
