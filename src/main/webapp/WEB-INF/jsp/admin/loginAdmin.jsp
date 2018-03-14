<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
</head>
<form name="form1" action="/admin/login.do" method="post" >
    24   <table width="300" border="1" align="center">
    25   <tr>
    26     <td colspan="2">登入窗口</td>
    27   </tr>
    28   <tr>
    29       <td>用户名:</td>
    30       <td><input type="text" name="username">
    31       </td>
    32   </tr>
    33   <tr>
    34       <td>密码:</td>
    35       <td><input  type="password" name="password"/>
    36       </td>
    37   </tr>
    38   <tr>
    39     <td colspan="2">
    40       <input type="submit" name="submit" value="登录"/>
    41     </td>
    42
    43
    44   </tr>
    45   </table>
    46 </form>
</body>
</html>