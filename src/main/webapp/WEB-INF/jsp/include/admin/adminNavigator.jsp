<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<div>
	<img src="img/site/yunx_big.png">
	<span class="pull-right" style="padding-top: 70px;padding-right: 20px;">
		<c:if test="${!empty admin}">
			Hello administrator，<a class="admin" href="#nowhere">${admin.name}</a>
			<a href="admin_loginout">退出</a>
		</c:if>
	</span>
</div>
	<div navitagorDiv>
		<nav class="navbar navbar-default navbar-inverse">
			<img style="margin-left:10px;margin-right:0px" class="pull-left" src="img/site/yunx.png" height="50px">
			<a class="navbar-brand" href="#nowhere">云香后台</a>

			<a class="navbar-brand" href="admin_category_list">分类管理</a>
			<a class="navbar-brand" href="admin_user_list">用户管理</a>
			<a class="navbar-brand" href="admin_order_list">订单管理</a>
		</nav>
	</div>