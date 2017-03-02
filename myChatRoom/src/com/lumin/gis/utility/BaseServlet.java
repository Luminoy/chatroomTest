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
    	
    	// ���磺http://localhost:8080/demo1/xxx?method=login
    	String methodName = req.getParameter("method");
    	
    	// ��û��ָ��Ҫ���õķ���ʱ����ôĬ���������execute()������
    	if(methodName == null || methodName.isEmpty()) {
    		methodName = "excute";
    	}
    	System.out.println(methodName);
    	Class<? extends BaseServlet> c = this.getClass();
    	try {
    		// ͨ���������ƻ�ȡ�����ķ������
    		Method m = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
    		
    		// ���䷽��Ŀ�귽����Ҳ����˵�����methodNameΪadd����ô�͵���add������
    		String result = (String)m.invoke(this, req, res);
    		
    		// ͨ������ֵ�������ת��
    		if(result != null && !result.isEmpty()) {
    			req.getRequestDispatcher(result).forward(req, res);
    			
    			//����forward()��redirect()���������forward()��request�еĲ����������ݣ�redirect()������������request
    		}
    	}catch(Exception e) {
    		throw new ServletException(e);
    	}
    	
    }
    
    //Ĭ�ϵ��ø÷���
	public void excute() {
		System.out.println("excute");
	}

}
