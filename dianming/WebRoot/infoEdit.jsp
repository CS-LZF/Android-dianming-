<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<link href="common/css.css" rel="stylesheet" type="text/css" />
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
<body>

	<c:if test="${(not empty s) && (s == 0 )}" >
		<script> 
			alert('新增成功！') ;
		</script>
	</c:if> 
	
	
	<form name="form1" action="foodEdit.do?method=save" method="post" enctype="multipart/form-data">
		<div class="loglist">
				<table width="90%" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td colspan="2">
							<h1> 新增课程 </h1>&nbsp; 
						</td>
					</tr>
					
					<tr align="left">
						<td width="30%" font-size: 16px height="30">
							名称:
						</td>
						<td width="70%" font-size: 16px height="30">
							<input type="hidden"" name="foodId" value="${map.food_id}" >
							<input type="text" name="foodName" value="${map.food_name}" size="50" >
						</td>
					</tr>
					
					<input type="hidden" name="pic" value="<%=session.getAttribute("sid") %>" >
					<input type="hidden" name="description" value="<%=session.getAttribute("screen_name") %>" >
					<input type="hidden" name="price" value="" >
					
					<input type="hidden" name="discountPrice" value="100">
					<input type="hidden" name="flag" value="2">
				 
				
					
				</table>
				<div class="list">
					<table width="90%" border="0" height="20" align="center">
							<tbody>
								<tr>
									<td>
										<input type="button"" value="提交" onclick="goSubmit();"/> &nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" name="cancel" value="取消" onclick="history.go(-1);" />
									</td>
								</tr>
							</tbody>
					</table>
				</div>
		</div>
	</form>
	
	
<script>
	function goSubmit(){
		form1.submit();
	}
</script>
	
	
</body>

</html>
