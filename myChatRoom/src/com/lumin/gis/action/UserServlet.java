package com.lumin.gis.action;
import com.lumin.gis.service.UserService;
import com.lumin.gis.utility.*;
import com.lumin.gis.vo.User;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;


public class UserServlet extends BaseServlet {
    public UserServlet() {

    }

    /*
     * 登陆功能
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("UserServlet");
    	Map<String, String[]> map = request.getParameterMap();
    	for(Entry<String, String[]> entry : map.entrySet()) {
    		System.out.println(entry.getKey()+":");
    		for(String value:entry.getValue()) {
    			System.out.println(value+", ");
    		}
    	}
    	User user = new User();
    	try {
    		BeanUtils.populate(user, map);
    		System.out.println(user);
    		UserService us = new UserService();
    		User existUser = us.login(user);
    		if(existUser == null) {
    			System.out.println("existUser null");
    			request.setAttribute("msg", "登陆失败！！");
    			return "/index.jsp";
    		}
    		else {
    			request.getSession().invalidate();
    			Map<User, HttpSession> userMap = (Map<User, HttpSession>)getServletContext().getAttribute("userMap");
    			if(userMap.containsKey(existUser)) {
    				HttpSession session = userMap.get(existUser);
    				session.invalidate();
    			}
    			
    			request.getSession().setAttribute("existUser", existUser);
    			ServletContext application = getServletContext();
    			
    			String srcMsg = "";
    			if(null != application.getAttribute("message")) {
    				srcMsg = application.getAttribute("message").toString();
    			}
    				srcMsg += "<font color='gray'>"
    						+ existUser.getUsername() + "走进了聊天室！</font><br>";
    				application.setAttribute("message", srcMsg);
    				
    				response.sendRedirect(request.getContextPath() + "/room.jsp");
    				System.out.println(request.getContextPath() + "/room.jsp");
    				return null;  
    			
    			
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public String check(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession();
    	User existUser = (User) session.getAttribute("existUser");
    	if(existUser == null) {
    		response.getWriter().println("1");
    	}
    	else {
    		response.getWriter().println("2");
    	}
    	return null;
    }
    
    public String kick(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return null;
    }
    
    public String getMessage(HttpServletRequest req, HttpServletResponse resp)  throws IOException {
    	String message = (String)getServletContext().getAttribute("message");
    	if(message != null) {
    		resp.getWriter().println(message);
    	}
    	return null;
    }
    
    public String sendMessage(HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    	System.out.println("sendMessage invoke...");
    	String from = req.getParameter("from");
    	String to = req.getParameter("to");
    	String face = req.getParameter("face");
    	String color = req.getParameter("color");
    	System.out.println(color);
    	String content = req.getParameter("content");
    	String sendTime = new Date().toLocaleString();
    	
    	ServletContext application = req.getServletContext();
    	String srcMsg = (String)application.getAttribute("message");
    	
    	srcMsg += "<font color='gray'>" + sendTime + ":</font><br>"
    			+ "<font color='white'><strong>" + from
    			+ "</strong></font><font color='#CC0000'>" + face
    			+ "</font>对<font color='green'>[ " + to + "]</font>说：" 
    			+ "<font style='" + color + "'>" + content + "</font>"
    			+ "<br>";
    	
    	application.setAttribute("message", srcMsg);
    	return getMessage(req, resp);
    }
    
    /**
	 *  退出聊天室
	 * @throws IOException 
	 */
	public String exit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 获得session对象
		HttpSession session = request.getSession();
		// 将session销毁.
		session.invalidate();
		// 页面转向.
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return null;
	}
	
}
