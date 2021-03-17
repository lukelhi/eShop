<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/my.js"></script> --%>
<script type="text/javascript">
/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
//鼠标点击搜索框时执行
function my_click(obj, myid){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
	  document.getElementById(myid).value = '';
	  obj.style.color='#000';
	}
}
//鼠标不聚焦在搜索框时执行
function my_blur(obj, myid){
	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == ''){
	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
	 obj.style.color='#999';
 }
}

/**
 * 点击搜索按钮执行的函数
 */
function search(){
	document.getElementById("searchform").submit();
}
</script>

<div id="divmenu">
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=服装">服装</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=书籍">书籍</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=电器">电器</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=零食">零食</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=医药">医药</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=文具">文具</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=家用">家用</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=手机">手机</a>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=少儿">少儿</a>
<%--		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=艺术">艺术</a>--%>
<%--		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=原版">原版</a>--%>
<%--		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=科技">科技</a>--%>
		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=考试">考试</a>
<%--		<a href="${pageContext.request.contextPath}/client/product/findProductByCategory?category=书籍百科">书籍百科</a>--%>
		<a href="${pageContext.request.contextPath}/client/product/findProductByName" style="color:#FFFF00">全部商品目录</a>
		
</div>
<div id="divsearch">
<form action="${pageContext.request.contextPath }/client/product/findProductByName" id="searchform">
	<table width="100%" border="0" cellspacing="0">
		<tr>
			<td style="text-align:right; padding-right:220px">
				Search 
				<input type="text" name="name" class="inputtable" id="textfield" value="${name == null?'请输入商品名':name}"
				onmouseover="this.focus();"
				onclick="my_click(this, 'name');"
				onBlur="my_blur(this, 'name');"/>
				<a href="#">
					<img src="${pageContext.request.contextPath}/client/images/serchbutton.gif" border="0" style="margin-bottom:-4px" onclick="search()"/> 
				</a>
			</td>
		</tr>
	</table>
</form>
</div>