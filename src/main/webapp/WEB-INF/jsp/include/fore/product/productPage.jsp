<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<title>云香优品 ${p.name}</title>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg">
</div>

<div class="productPageDiv">

    <%@include file="imgAndInfo.jsp" %> // 单个图片和基本信息

    <%@include file="productReview.jsp" %> // 评价信息

    <%@include file="productDetail.jsp" %> // 详情图片
</div>