<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<HTML>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>login</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body>
	  <a href="home">返回主页</a><br>
	  <br/>	
      list.... 这部分：当不同权限的角色，显示不同的资源功能
      <shiro:hasPermission name="emp:save">
		 <a href="app/save">员工权限显示</a><br>
	  </shiro:hasPermission>
	  
	  <shiro:hasPermission name="manager/save">
		 <a href="manager/save">经理权限显示</a><br>
	  </shiro:hasPermission>
		
		
      <ul>
      	<li><a href="app/save">员工添加</a></li>
      	<li><a href="app/edit">员工修改</a></li>
      	<li><a href="app/delete">员工删除</a></li>
      </ul>
      
      <br/>
      
      <ul>
      	<li><a href="manager/save">经理添加</a></li>
      	<li><a href="manager/edit">经理修改</a></li>
      	<li><a href="manager/delete">经理删除</a></li>
      </ul>
</body>
</HTML>
<script type="text/javascript">

</script>

