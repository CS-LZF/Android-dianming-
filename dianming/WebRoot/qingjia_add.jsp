
<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="db.db"/>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请假信息</title><LINK href="CSS.css" type=text/css rel=stylesheet>
    <script language="javascript" src="js/Calendar.js"></script>

  </head>
<script language="javascript">
function check()
{

	if(document.form1.biaoti.value==""){alert("请输入标题"); document.form1.biaoti.focus();return false;}
	if(document.form1.miaoshu.value==""){alert("请输入描述"); document.form1.miaoshu.focus();return false;}
	if(document.form1.shijian.value==""){alert("请输入请假时间"); document.form1.shijian.focus();return false;}
	if(document.form1.zhuangtai.value==""){alert("请输入请假状态"); document.form1.zhuangtai.focus();return false;}
	if(document.form1.xusheng.value==""){alert("请输入学生"); document.form1.xusheng.focus();return false;}
	if(document.form1.kecheng.value==""){alert("请输入课程"); document.form1.kecheng.focus();return false;}
}


</script>

<body >
  
  <form name="form1" id="form1" method="post" action="qingjia_add_post.jsp">
  添加请假信息:
  <br><br>
   <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="d3eaef">    
   
		   <tr><td font-size: 16px height="30">标题：</td><td>
			<input type="text" name='biaoti'  id='biaoti'   ></input>
   			</td></tr>
		   <tr><td font-size: 16px height="30">描述：</td><td>
			<input type="text" name='miaoshu'  id='miaoshu'   ></input>
   			</td></tr>
		   <tr><td font-size: 16px height="30">请假时间：</td><td>
			<input type="text" name='shijian'  id='shijian'  readonly='readonly' onClick="getDate(document.form1.shijian,'2')" need='1'   ></input>
   			</td></tr>
		   <tr><td font-size: 16px height="30">请假状态：</td><td>
			<input type="text" name='zhuangtai'  id='zhuangtai'   ></input>
   			</td></tr>
		   <tr><td font-size: 16px height="30">学生：</td><td>
				<select name='xusheng' id='xusheng'>
						<%	String sql = "select  user_username  from  user_tb  order by uid desc";
			   		ResultSet RS_result=connDbBean.executeQuery(sql);while(RS_result.next()){%>
			   		<option value="<%= RS_result.getString("user_username")%>" ><%=RS_result.getString("user_username")%></option><%}%>
			   	</select>&nbsp;*
   			</td></tr>
		   <tr><td height="30" font-size: 16px>课程：</td><td>
				<select name='kecheng' id='kecheng'  >
						<%	String sql2 = "select  info_name  from  info_info  order by info_id desc";
			   		ResultSet RS_result2=connDbBean.executeQuery(sql2);while(RS_result2.next()){%>
			   		<option value="<%= RS_result2.getString("info_name")%>" ><%=RS_result2.getString("info_name")%></option><%}%>
			   	</select>&nbsp;*
   			</td></tr>
   
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="提交" />
      <input type="reset" name="Submit2" value="重置" /></td>
    </tr>
  </table>
</form>

  </body>
</html>

