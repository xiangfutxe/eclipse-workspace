package com.pojo;

/**
 * @author 高远</n> 邮箱：wgyscsf@163.com</n> 博客 http://blog.csdn.net/wgyscsf</n>
 *         编写时期 2016-4-6 下午6:35:21
 */
/*
 * access_token类
 */
public class Access_token {
	private String access_token;

	public String getAccess_token() {
		return access_token;
	}

	@Override
	public String toString() {
		return "Access_token [access_token=" + access_token + ", expires_in="
				+ expires_in + "]";
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	private String expires_in;
}
