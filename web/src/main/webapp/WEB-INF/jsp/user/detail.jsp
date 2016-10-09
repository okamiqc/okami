<%--
  Created by IntelliJ IDEA.
  User: qqs
  Date: 2016/10/9
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
用户：
  ${login_user_info.name}
密码：${login_user_info.password}
</body>
</html>
