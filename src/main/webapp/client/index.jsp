<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>eShop首页</title>
	<%-- 导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<!-- 导入首页轮播图css和js脚本 -->
	<link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoplay.js"></script>
</head>

<body class="main" >
	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	
	<!-- 商品商场首页轮播图  start -->
	<div id="box_autoplay">
    	<div class="list">
        	<ul>
            	<li><a href="${pageContext.request.contextPath}/client/product/findProductById?id=857a6c8e-24c5-4e79-a5b9-ba0a4939288a"><img src="${pageContext.request.contextPath }/client/ad/index_ad1.jpg" width="900" height="335" /></a></li>
            	<li><a href="${pageContext.request.contextPath}/client/product/findProductById?id=4723382e-0e2d-49e5-8096-18a1f9e17c80"><img src="${pageContext.request.contextPath }/client/ad/index_ad2.jpg" width="900" height="335" /></a></li>
            	<li><a href="${pageContext.request.contextPath}/client/product/findProductById?id=eaae7265-91b7-45e1-a3dc-776d83d664e2"><img src="${pageContext.request.contextPath }/client/ad/index_ad3.jpg" width="900" height="335" /></a></li>
            	<li><a href="${pageContext.request.contextPath}/client/product/findProductById?id=6c4f5322-5845-4556-806d-a17fcb51bf33"><img src="${pageContext.request.contextPath }/client/ad/index_ad4.jpg" width="900" height="335" /></a></li>
            	<li><a href="${pageContext.request.contextPath}/client/product/findProductById?id=596a0354-5c92-4d96-970e-864680cc93db"><img src="${pageContext.request.contextPath }/client/ad/index_ad5.jpg" width="900" height="335" /></a></li>
        	</ul>
    	</div>
	</div>
	<!-- 商品商场首页轮播图  end -->
	
	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td width="497">
					<img src="${pageContext.request.contextPath}/client/images/billboard.gif" width="497" height="38" />
					<table cellspacing="0" class="ctl">
						<tr>
							<td width="485" height="29">${n.details }</td>
						</tr>
					</table>
				</td>
				<td style="padding:5px 15px 10px 40px">
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/client/images/hottitle.gif" width="126" height="29" />
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0">
						<tr>
						<c:forEach items="${pList }" var="pArray">
							<td style="width:80px; text-align:center">
								<a href="${pageContext.request.contextPath}/client/product/findProductById?id=${pArray.id}">
									<img src="${pageContext.request.contextPath }${pArray.imgurl}" width="102" height="130" border="0" />
								</a>
								<br /> 
								<a href="${pageContext.request.contextPath}/client/product/findProductById?id=${pArray.id}">${pArray.name}<br> 价格：${pArray.price}</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
						</c:forEach>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>
