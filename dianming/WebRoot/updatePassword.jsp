<%@ page language="java" pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="common/css.css" rel="stylesheet" type="text/css" />
</head>

<body>   
<%
	String result = ""; 
	if(request.getAttribute("result") != null){
		result = request.getAttribute("result").toString();
	}

	if(result.equals("0")){
		result = "原密码错误.";
	}
	if(result.equals("1")){

%>
<script type="text/javascript">
		alert('修改密码成功.');
		window.close();
</script>
<%
		return;
	}
%>
<center>
<div id="body">

	<form method="post" id="demo"  action="updatePassword.do" >
		<br></br>
		<label><%=result%></label>
		<p>	原密码	<input name="oldPassword" id="oldPassword" type="password"  /> </p>
		<p>	新密码	<input name="newPassword" id="newPassword" type="password" /> </p>
		<p>	重复新密码	<input name="secondPassword" dataType="Repeat" to="newPassword" msg="两次输入的密码不一致"  type="password"  />  </p>
		<p>	

			<input name="Submit" type="submit" value="确定" onclick="return onSubmit();"/>
			<input type="button" value="关闭" onclick="javascript:window.close()"/>
		</p>
	</form>
</div>
<script language="javascript">
	function onSubmit(){
			if(document.getElementById('oldPassword').value == ''){
				alert('请输入原密码');
				return false;
			}
			if(document.getElementById('newPassword').value != document.getElementById('secondPassword').value){
				alert('新密码和二次输入的新密码不一致');
				return false;
			}
			return true;
	}	
</script>
</body>
</html>