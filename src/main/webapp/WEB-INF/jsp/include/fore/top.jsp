<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
	<a href="forehome">
		<%--<span style="color:#7dc473;margin:0px" class=" glyphicon glyphicon-home greenColor"></span>--%>
		<span style="color:#7dc473" class=" iconfont icon-shouye greenColor"></span>
		云香首页
	</a>

	<span>Hi，欢迎来云香优品</span>

	<c:if test="${!empty user}">
		<a href="#nowhere">${user.name}</a>
		<a href="foreloginout">退出</a>
	</c:if>

	<c:if test="${empty user}">
		<a href="loginPage">请登录</a>
		<a href="registerPage">免费注册</a>
	</c:if>


	<span class="pull-right">
			<a href="forebought">我的订单</a>
			<a href="forecart">
			<span style="color:#7dc473" class="iconfont icon-gouwuche greenColor"></span>
			购物车<strong>${cartTotalItemNumber}</strong>件</a>
		</span>


</nav>



