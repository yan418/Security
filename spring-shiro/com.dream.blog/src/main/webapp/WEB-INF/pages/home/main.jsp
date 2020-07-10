<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- shiro的 -->
<HTML>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>login</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body>
      主页....<shiro:principal property="username"></shiro:principal>
      <a href="logout">注销</a>
      <a href="list">列表页</a>

</body>
</HTML>
<script type="text/javascript">

</script>

