<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/admin/jquery/jquery-1.4.2.js"></script>
</head>
<script type="text/javascript">
	// var myCheck = function(){
	function myCheck(){
		var years = $("#inputBox").val();	//获取input框内的值
		if(years == null || years === ""){
			alert("请填写年份");	//如果值为空，提示用户填写
			return;
		}
		var months = $("#months").val();
		// alert(months);
		if(months === "0"){
			alert("请选择月份");	//如果值为空，提示用户填写
			return;
		}
		document.getElementById('Form1').submit();
	};
</script>
<body>
	<br>
	<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/admin/product/download" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" 
		bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请输入年份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="year" size="15" value="" 
									id="inputBox" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请选择月份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="month" id="months">
										<option value="0">--选择月份--</option>
										<option value="1">一月</option>
										<option value="2">二月</option>
										<option value="3">三月</option>
										<option value="4">四月</option>
										<option value="5">五月</option>
										<option value="6">六月</option>
										<option value="7">七月</option>
										<option value="8">八月</option>
										<option value="9">九月</option>
										<option value="10">十月</option>
										<option value="11">十一月</option>
										<option value="12">十二月</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" 
								bgColor="#f5fafe" class="ta_01">
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br><br>
								</td>
								<td align="center" bgColor="#ffffff" class="ta_01">
<%--									<a href="javascript:void(0);" onclick="myCheck()">下载</a>--%>
									<input type="button" value="下载" class="button_view" onclick="myCheck()">
									<input type="reset" name="reset" value="重置" class="button_view" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>

