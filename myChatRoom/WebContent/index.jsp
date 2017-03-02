<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/login_style.css"/>
<title>登录页面</title>
<script src='${ pageContext.request.contextPath }/js/jquery.js'></script>
<script type="text/javascript">
$(function(){
	//得到焦点
	$("#password").focus(function(){
		$("#left_hand").animate({
			left: "150",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		$("#right_hand").animate({
			right: "-64",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -70){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});
	//失去焦点
	$("#password").blur(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:100px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-112px;top:-12px");
	});
});
</script>
</head>
<body>
	<div class="top_div"></div>
	<div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
		<div class="tou"></div>
		<div class="initial_left_hand" id="left_hand"></div>
		<div class="initial_right_hand" id="right_hand"></div></div>
		<form id="form1" name="form2" method="post" action="${pageContext.request.contextPath}/user">
		<p style="padding: 30px 0px 10px; position: relative;">
			<span class="u_logo"></span>
			<input class="login" type="text" name="username" placeholder="请输入用户名或邮箱" value="">
		</p>
		<p style="position: relative;">
			<span class="p_logo"></span>      
			<input class="login" type="password" id="password" name="userpwd" placeholder="请输入密码" value="">   
		</p>
		<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
			<p style="margin: 0px 35px 20px 45px;">
				<span style="float: left;">
					<a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
				</span> 
				<span style="float: right;">
					<span style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;">
					注册
					</span>&nbsp;
					<input type="submit" name="submit" class="btn_login" value="登录" style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;">
					<input type="hidden" name="method" value="login"> 
				</span>
			</p>
		</div>
		</form>
	</div>
	<center style="color:red"><h3>${ msg }</h3></center>
</body>
</html>