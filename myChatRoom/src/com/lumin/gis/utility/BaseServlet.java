package com.lumin.gis.utility;

import java.io.IOException;
import java.lang.reflect.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html;charset=utf-8");
    	
    	// 例如：http://localhost:8080/demo1/xxx?method=login
    	String methodName = req.getParameter("method");
    	
    	// 当没用指定要调用的方法时，那么默认请求的是execute()方法。
    	if(methodName == null || methodName.isEmpty()) {
    		methodName = "excute";
    	}
    	System.out.println(methodName);
    	Class<? extends BaseServlet> c = this.getClass();
    	try {
    		// 通过方法名称获取方法的反射对象
    		Method m = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
    		
    		// 反射方法目标方法，也就是说，如果methodName为add，那么就调用add方法。
    		String result = (String)m.invoke(this, req, res);
    		
    		// 通过返回值完成请求转发
    		if(result != null && !result.isEmpty()) {
    			req.getRequestDispatcher(result).forward(req, res);
    			
    			//会有forward()和redirect()两种情况，forward()是request中的参数继续传递，redirect()则是重新生成request
    		}
    	}catch(Exception e) {
    		throw new ServletException(e);
    	}
    	
    }
    
    //默认调用该方法
	public void excute() {
		System.out.println("excute");
	}

}
