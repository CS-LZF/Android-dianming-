<%@ page contentType="text/html;charset=utf-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安卓考勤系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script type="text/javascript" src="js/js.js"></script>
<%@ include file="commons/taglibs.jsp"%>
</head>
<body>
<%
if(request.getAttribute("s") != null){

%>
<script type="text/javascript">
	alert('用户名或者密码错误!');
</script>
<%
}
%>
<div id="top"> </div>
<form id="LogonForm" method="post" action="login.do"  >
  <div id="center">
    <!-- <div id="center_left"></div> -->
    <div id="center_middle">
      <div class="user">
        <label>用户名：
 				<input type='text' name='name' dataType="Require" msg="不能为空">
        </label>
      </div>
      <div class="user">
        <label>密　码：
			<input type='password' name='password' dataType="Require" msg="不能为空"> 
        </label>
      </div>
      
      
      	<input type='hidden' name='type' value="1" > 
      
      

      
    </div>
    <!-- <div id="center_middle_right"></div> -->
    <div id="center_submit" style="width: 197px; ">
      <div class="button">
				<img src="images/dl.gif" width="50" height="20" onclick="form_submit()" > 
	  </div>
      <div class="button"> 
			<img src="images/cz.gif" width="50" height="20" onclick="form_reset()">
	  </div>
    </div>
    <!-- <div id="center_right"></div> -->
  </div>
</form>
<div id="footer"></div>

<script>
	function form_submit(){
 
			LogonForm.submit(); 		
	}
	
	function form_reset(){
		LogonForm.reset();
	}
</script>

</body>
</html>

