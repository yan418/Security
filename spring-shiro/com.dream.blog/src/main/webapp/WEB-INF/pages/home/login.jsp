<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<HTML>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>login</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body>
    login登录页
    <span style="color: red;">${errorMsg}</span>
    <form class="m-t" method="post" action="login" id="login">
        <div class="form-group">
            <input type="text" placeholder="用户名"  name="username" maxlength="20" >
        </div>
        <div class="form-group">
            <input type="password" placeholder="密码"  name="password" maxlength="20">
        </div>
        <button type="submit" >登 录</button>
    </form>   
    <div>
      <br/>
      <!--  
      <a href="reload">添加权限列表</a>
      -->
    </div>
</body>
</HTML>
<script type="text/javascript">

</script>

