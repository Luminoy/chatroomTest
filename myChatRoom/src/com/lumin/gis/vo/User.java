package com.lumin.gis.vo;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener{
	public int uid;
	private String username;
	private String userpwd;
	private String usertype;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// 将JavaBean与session绑定，session.setAttribute()执行
		HttpSession session = event.getSession();
		Map<User, HttpSession> userMap = (Map<User, HttpSession>)session.getServletContext().getAttribute("userMap");
		userMap.put(this, session); 
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// 将JavaBean与session解除绑定
		HttpSession session = event.getSession();
		Map<User, HttpSession> userMap = (Map<User, HttpSession>)session.getServletContext().getAttribute("userMap");
		userMap.remove(this);
	}
	
}
