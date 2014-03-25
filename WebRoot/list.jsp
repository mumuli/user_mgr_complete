<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mfn" uri="http://www.vzhang.net/jsp/jstl/functions/util" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		
		function allChecked() {
			var uidscbs = $("input[name='uids']");
			//alert(uidscbs.length);
			uidscbs.attr("checked", true);
		}
		
		function allCancelChecked() {
			var uidscbs = $("input[name='uids']");
			//alert(uidscbs.length);
			uidscbs.attr("checked", false);
		}
		
		function reverseChecked() {
			var uidscbs = $("input[name='uids']");
			uidscbs.each(function () { 
                $(this).attr("checked",!$(this).attr("checked")); 
            }); 
		}
		
		function checkedDelete() {
			var uidscbs = $("input[name='uids'][checked='checked']");
			if(uidscbs.length == 0) {
				alert("请选择要删除的记录！");
				return;
			}
			if(confirm("真的要删除吗？")) {
				//alert("执行删除！");
				$("#user_list_form").attr("action", "user_delete.do");
				$("#user_list_form").submit();
			}
		}
		
		$().ready(function() {
			$("#allIdsCheckBox").click(function() {
				if($(this).attr("checked")){
					allChecked();
				} else {
					allCancelChecked();
				};
			});
		});
		
		function goFirstPage() {
			$("#page").val("1");
			$("#pageSize").val("${pageModel.pageSize}");
			$("#user_query_form").submit();
		}
		
		function goLastPage() {
			$("#page").val("${pageModel.pageCount}");
			$("#pageSize").val("${pageModel.pageSize}");
			$("#user_query_form").submit();
		}
		
		function goNextPage() {
			var page = ${pageModel.page};
			var pageCount = ${pageModel.pageCount};
			
			if(page == pageCount) {
				page = pageCount;
			} else {
				page++;
			}
			$("#page").val(page);
			$("#pageSize").val("${pageModel.pageSize}");
			$("#user_query_form").submit();
		}
		
		function goPreviousPage() {
			var page = ${pageModel.page};
			var pageCount = ${pageModel.pageCount};
			
			if(page == 1) {
				page = 1;
			} else {
				page--;
			}
			$("#page").val(page);
			$("#pageSize").val("${pageModel.pageSize}");
			$("#user_query_form").submit();
		}
		
	</script>
	<c:if test="${not empty msg }">
	<script type="text/javascript">
		alert("${msg}");
	</script>
	</c:if>
  </head>
  
  <body>
    <h1>用户列表</h1>
    <a href="user_add.do">添加用户</a>
    <br/>
    <form id="user_query_form" action="user_list.do" method="post"">
    		<input type="hidden" id="page" name="page"/>
    		<input type="hidden" id="pageSize" name="pageSize"/>
           	用户名：<input name="queryUserName" type="text" value="${queryUserName}" />
           	 组名：<input name="queryGroupName" type="text" value="${queryGroupName}"/><input type="submit" value="查询"/>
    </form>
    <form id="user_list_form" method="post">
    <table border="1" cellspacing="0" cellpadding="0">
    	<tr>
    		<th>
    		<input type="checkbox" id="allIdsCheckBox" />
    		</th>
    		<th>ID</th>
    		<th>NAME</th>
    		<th>BIRTHDAY</th>
    		<th>AGE</th>
    		<th>HIREDATE</th>
    		<th>GROUP</th>
    		<th>OPERATION</th>
    	</tr>
    	<c:choose>
    		<c:when test="${empty userList}">
    			<tr>
    				<td colspan="8"><li>暂无相关数据</li></td>
    			</tr>
    		</c:when>
    		<c:otherwise>
    			<c:forEach items="${userList}" var="user">
    				<tr>
    					<td><input type="checkbox" name="uids" value="${user.id}"/></td>
    					<td>${user.id }</td>
    					<td>${user.name }</td>
    					<td><fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日"/></td>
    					<td>${mfn:getAge(user.birthday)}周岁</td>
    					<td><fmt:formatDate value="${user.hiredate}" pattern="yyyy年MM月dd日"/></td>
    					<td>${user.group.name }</td>
    					<td>
    						<a href="user_load.do?uids=${user.id}">DETAIL</a> | <a href="user_delete.do?uids=${user.id}&page=${pageModel.page}&pageSize=${pageModel.pageSize}">DELETE</a>
    					</td>
    				</tr>
    			</c:forEach>
    		</c:otherwise>
    	</c:choose>
    	<tr>
    		<td colspan="8">
    		<input type="button" value="DELETE" onclick="javascript:checkedDelete();"/>
    		<input type="hidden" name="page" value="${pageModel.page}"/>
    		<input type="hidden" name="pageSize" value="${pageModel.pageSize}"/>
    		<a href="javascript:void(0)" onclick="javascript:allChecked();">全选</a>
    		<a href="javascript:void(0)" onclick="javascript:allCancelChecked();">取消</a>
    		<a href="javascript:void(0)" onclick="javascript:reverseChecked();">反选</a>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="8" align="right">
    		总共${pageModel.count}条, 每页${pageModel.pageSize}条, 当前第${pageModel.page}/${pageModel.pageCount}页, 
    		<a href="javascript:void(0);" onclick="javascript:goFirstPage();">首页</a>
    		 | 
    		<a href="javascript:void(0);" onclick="javascript:goPreviousPage();">上一页</a>
    		 | 
    		<a href="javascript:void(0);" onclick="javascript:goNextPage();">下一页</a>
    		 |
    		<a href="javascript:void(0);" onclick="javascript:goLastPage();">尾页</a>
    		</td>
    	</tr>
    </table>
    </form>
    <s:debug/>
  </body>
</html>
