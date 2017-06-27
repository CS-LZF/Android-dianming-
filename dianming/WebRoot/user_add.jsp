<%@ page language="java" pageEncoding="utf-8" %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增音乐</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link href="common/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="scripts/validator.js"></script>
<script language="javascript" type="text/javascript" src="scripts/datepicker/WdatePicker.js"></script> 
	</head>
<style type="text/css">
table td {
	padding-bottom: 2px;
	padding-left: 4px;
	padding-right: 4px;
	padding-top: 4px;
	font-size: 12px;
	border-bottom: #c7c7c7 1px solid;
}

body {
	margin: 0;
	font-size: 12px;
	background: url('images/30.gif') repeat-x 0px 0px;
	background-color: #f8f8f8;
}

.loglist {
	width: 90%;
	margin-top: 8px;
	margin-left: 50px;
	padding-bottom: 1px;
	border-bottom: #c7c7c7 1px solid;
}

.list {
	cursor: default;
	margin-top: 1px;
	height: 298px;
	overflow-y: auto;
	overflow-x: hidden;
	scrollbar-shadow-color: #ffffff;
	scrollbar-highlight-color: #ffffff;
	scrollbar-face-color: #d9d9d9;
	scrollbar-3dlight-color: #d9d9d9;
	scrollbar-darkshadow-color: #d9d9d9;
	scrollbar-track-color: #f6f6f6;
	scrollbar-arrow-color: #ffffff;
}
</style>

<%
	if( request.getSession().getAttribute("type") !=null   ){
		String type = request.getSession().getAttribute("type").toString() ;
		if( type.equals("1") ){
%>		
			<script>alert('非法访问，权限不足！') ;</script>
<%
			return;
		}
	}
%>

<body>
<c:if test="${(not empty s) && (s == 0 )}" >
		<script> 
			alert('保存成功！') ;
		</script>
	</c:if> 
	
	
	<form  name="form1" id="form1" action="user.do?method=save" method="post"  >
		<div class="loglist">
				<table width="90%" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td colspan="2">
							<h3> 新增教师 </h3>&nbsp; 
						</td>
					</tr>
					
					<tr align="left">
						<td width="20%">
							 登录名
						</td>
						<td width="70%">
				        	<input name="id" type="hidden"   value="${map.id}"/>
				        	<input name="name" type="text"   value="${map.name}" dataType="Require" msg="用户名不能为空"  /> 
						</td>
					</tr>
					<tr>
						<td  width="20%">
							密码：
						</td>
						<td  width="70%">
							<input name="password" type="text"  value="${map.password}" dataType="Require" msg="密码不能为空"  /> 
						</td>
					</tr>
					 
					<tr>
						<td  width="20%">
							昵称
						</td>
						<td  width="70%">
							<input name="screen_name" type="text"   value="${map.screen_name}" dataType="Require" msg="昵称不能为空"  />
						</td>
					</tr>		 
	 
					<tr>
						<td  width="20%">
							类型
						</td>
						<td  width="70%">
							 <input type="radio" name="type" value="0" 
					        <c:if test="${(not empty map.type ) && (map.type == '0' )}" > checked="checked" </c:if> />超级管理员
					        <input type="radio" name="type" value="1" 
					        <c:if test="${(not empty map.type ) && (map.type == '1' )}" > checked="checked" </c:if> />普通管理员
						</td>
					</tr>		 
	 
				</table>
				<div class="list">
					<table width="90%" border="0" height="20" align="center">
							<tbody>
								<tr>
									<td>
										<input type="button"" value="提交" onclick="goSubmit();"/> &nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;<input  type="reset"  value="重置" /> 
									</td>
								</tr>
							</tbody>
					</table>
				</div>
		</div>
	</form>
	
	
<script>
	function goSubmit(){
		 if(!Validator.Validate(form1,1))
				return;
		 form1.submit();
	}
</script>
	
	
</body>

</html>
