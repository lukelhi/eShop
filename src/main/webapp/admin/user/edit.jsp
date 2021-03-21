<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
		  type="text/css" rel="stylesheet">
	<script language="javascript"
			src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</HEAD>

<body>
<form id="userAction_save_do" name="Form1"
	  action="${pageContext.request.contextPath}/admin/user/editUser" method="post">
	<input type="hidden" name="id" value="${user.id}" /> &nbsp;
	<table cellSpacing="1" cellPadding="5" width="100%" align="center"
		   bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
		<tr>
			<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
				height="26"><strong><STRONG>编辑用户</STRONG> </strong></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">用户名：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="username" class="bg" value="${user.username }" /></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">密码：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="password" class="bg" value="${user.password}" /></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">性别：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="gender" class="bg" value="${user.gender}" /></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">邮箱：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="email" class="bg" value="${user.email}" /></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">激活码：</td>
			<td bgColor="#ffffff" size="30" ><input type="button"
													   name="activeCode" class="bg" value="${user.activeCode}" /></td>
		</tr>

		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">电话：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="telephone" class="bg" value="${user.telephone}" /></td>
		</tr>

		<tr>
			<TD class="ta_01" align="center" bgColor="#f5fafe">用户描述：</TD>
			<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
					name="introduce" cols="30" rows="3" style="WIDTH: 96%">${user.introduce}</textarea>
			</TD>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">状态：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="state" class="bg" value="${user.state}" /></td>
		</tr>
		<tr>
			<td align="center" bgColor="#f5fafe" class="ta_01">角色：</td>
			<td class="ta_01" bgColor="#ffffff"><input type="text"
													   name="role" class="bg" value="${user.role}" /></td>
		</tr>


		<TR>
			<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
			</td>
		</TR>
		<tr>
			<td class="ta_01" style="WIDTH: 100%" align="center"
				bgColor="#f5fafe" colSpan="4">
				<input type="submit" class="button_ok" value="确定">
				<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
				<span id="Label1"> </span>
			</td>
		</tr>
	</table>
</form>
</body>
</HTML>