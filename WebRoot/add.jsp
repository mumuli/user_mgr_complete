<%@page import="javax.swing.text.TabableView"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  <h1>添加用户</h1>
  <form action="user_add.do" method="post">
  姓名：<input type="text" name="name" value="${user.name }"/><br/>
  <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" var="birthday"/>
 生日：<input type="text" name="birthday" class="Wdate" onClick="WdatePicker({dateFmt: 'yyyy年MM月dd日'})" value="${birthday}" readonly="readonly"/> <br/>
  <fmt:formatDate value="${user.hiredate}" pattern="yyyy年MM月dd日" var="hiredate"/>
组别：<select name="group.id">
		<c:forEach items="${groupList}" var="group">
			<option value="${group.id}" ${user.group.id == group.id ? 'selected=\'selected\'' : ''}>${group.name}</option>
		</c:forEach>
	</select>
	<br/>
<input type="submit" value="提交"/><input type="reset" value="重置"/>
  </form>
  <a href="user_list.do">返回列表</a>
  <s:debug/>
  </body>
</html>
