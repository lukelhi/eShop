<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/admin/css/Style.css"
          rel="stylesheet" type="text/css" />
    <script language="javascript"
            src="${pageContext.request.contextPath}/admin/js/public.js"></script>
    <script type="text/javascript">
        function addUser() {
            window.location.href = "${pageContext.request.contextPath}/admin/user/add.jsp";
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1" action="" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <tbody>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3"><strong>用 户 列 表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="&#28155;&#21152;"
                        class="button_add" onclick="addUser()">&#28155;&#21152;<%--添加--%>
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="12%">编号</td>
                        <td align="center" width="12%">姓名</td>
                        <td align="center" width="8%">性别</td>
                        <td align="center" width="12%">邮箱</td>
                        <td width="8%" align="center">电话</td>
                        <td width="8%" align="center">状态</td>
                        <td width="8%" align="center">角色</td>
                        <td width="8%" align="center">注册时间</td>
                        <td width="8%" align="center">编辑</td>
                        <td width="8%" align="center">删除</td>
                    </tr>
                    <c:forEach items="${users}" var="u">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="12%">${u.id }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.username }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.gender }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.email }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.telephone }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.state }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${u.role }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%"><fmt:formatDate value="${u.registTime }" pattern="yyyy-MM-dd"/></td>
                            <td align="center" style="HEIGHT: 22px" width="7%">
                                <a href="${pageContext.request.contextPath}/admin/user/findUserById?id=${u.id}">
                                    <img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0" style="CURSOR: hand">
                                </a>
                            </td>
                            <td align="center" style="HEIGHT: 22px" width="7%"><a
                                    href="${pageContext.request.contextPath}/admin/user/removeUserById?id=${u.id}">
                                <img
                                        src="${pageContext.request.contextPath}/admin/images/i_del.gif"
                                        width="16" height="16" border="0" style="CURSOR: hand">
                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>

