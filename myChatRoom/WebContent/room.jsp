<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lumin.gis.service.UserService" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/room_style.css"/>

<script type="text/javascript">
	window.setInterval("showMsg();",1000);
	window.setInterval("check();", 1000);
	
	// Jquery:JS框架.
	// 相当于window.onload
	window.onload = function(){
		showMsg();
		check();
	};
	
	function check() {
		$.post("${pageContext.request.contextPath}/user?method=check", function(data) {
			if(data == 1) {
				alert("用户已被踢下线！请重新登录！");
				window.location="index.jsp";
			}
		});
	}
	
	function showMsg() {
		$.post("${pageContext.request.contextPath}/user?method=getMessage", function(data) {
			$("#sys_board").html("<span>"+data+"</span>");
		});
	}
	
	function set(selectedPerson) {
		form2.to.value = selectedPerson;
	}
	
	function send() {
		if(form2.to.value == "") {
			alert("请选择聊天对象！");
			return false;
		}
		if(form2.content.value == "") {
			alert("聊天内容不能为空！");
			form2.content.focus();
			return false;
		}
		$.post("${pageContext.request.contextPath}/user",$("#form1").serialize(),function(data) {
			$("#sys_board").html("<span>"+data+"</span>");
		});
	}
</script>
<title>CHATROOM 007</title>
</head>
<body>
  
  <div class="header">
     欢迎来到 007 聊天室！<br><br>
  </div>
  <div class="body">
  	<div class="left">
	  	<table width="100%" border="0" cellpadding="0" cellspacing="0">

		<tr>
			<td height="30" align="center">当前在线[<font color="#FF6600">${ fn:length(userMap) }</font>]人&nbsp;<a href="">刷新</a></td>
			
		</tr>
		<c:forEach var="entry" items="${ userMap }">
			<tr>
				<td height="23" align="center">
					<a href="#" onclick="set('${ entry.key.username }')">${ entry.key.username }</a>
					<c:choose>
					<c:when test="${ existUser.usertype == 'admin' and entry.key.usertype != 'admin' }">
						<a href="${ pageContext.request.contextPath }/user?method=kick&uid=${entry.key.uid}"><font style="color:grey;font-size:12px;">踢</font></a>
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
		</c:forEach>
		</table>
  	</div>
  	<div class="content"> 
		<ul class="chat-thread" id="chat_win">
			<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
			<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		  	<li>Are we meeting today?</li>
			<li>yes, what time suits you?</li>
			<li>I was thinking after lunch, I have a meeting in the morning</li>
		</ul>
  	</div>
  	<div class="right">
  	<p>系统公告</p><br>
  	<div  id="sys_board"></div>
  	</div>
  </div>
  <div class="footer"><br><br>
  <form action="" id="form1" name="form2" method="post">
  	<input type="hidden" name="method" value="sendMessage" />
  	<input name="from" type="hidden" value="${existUser.username }">[${existUser.username }]对
  	<input name="to" type="text" value="" width="35px" readonly="readonly">
  	<select name="face" class="wenbenkuang">
  		<option value="面无表情">面无表情</option>
  		<option value="微笑着">微笑着</option>
  		<option value="悲伤地">悲伤地</option>
  	</select>说：
  	<select name="color" class="wenbenkuang">
  		<option style="color:#000000" value="color:#000000" selected>默认颜色</option>
  		<option style="color:#ff0000" value="color:#ff0000">红色</option>
  		<option style="color:#00ff00" value="color:#00ff00">绿色</option>
  		<option style="color:#0000ff" value="color:#0000ff">蓝色</option>
  	</select>
  	<input name="scrollScreen" type="checkbox" class="noborder" id="scrollScreen"
					onClick="checkScrollScreen()" value="1" checked><br>
	<input name="content" type="text" size="70" onKeyDown="if(event.keyCode==13 && event.ctrlKey) {send();}">
	<input name="submit2" type="button" class="btn_grey" value="发送" onClick="send()"> 
  </form>
  <p>Copyright @Lumin inc.</p>
  </div>
  
  <script src='${ pageContext.request.contextPath }/js/jquery.js'></script>
</body>
</html>