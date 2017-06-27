
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
    
    <title>作业信息记录</title><script language="javascript" src="js/Calendar.js"></script>
    <LINK href="CSS.css" type=text/css rel=stylesheet>

  </head>
<script language="javascript">
function check()
{
	
	if(document.form1.mingcheng.value==""){alert("请输入作业名称"); document.form1.mingcheng.focus();return false;}
	if(document.form1.miaoshu.value==""){alert("请输入描述"); document.form1.miaoshu.focus();return false;}
	if(document.form1.shijian.value==""){alert("请输入时间"); document.form1.shijian.focus();return false;}
	if(document.form1.kecheng.value==""){alert("请输入课程"); document.form1.kecheng.focus();return false;}
}
</script>
  <body >
  <%
  String id=request.getParameter("id");
   %>
  <form name="form1" id="form1" method="post" action="zuoye_updt_post.jsp?id=<%=id %>">
  修改作业信息记录:
  <br><br>
  <%
  String sql="select * from  zuoye  where id="+id;
     	 		String mingcheng = "" ;  
     	 		String miaoshu = "" ;  
     	 		String shijian = "" ;  
     	 		String kecheng = "" ;  
  ResultSet RS_result=connDbBean.executeQuery(sql);
  while(RS_result.next()){
     	 		mingcheng = RS_result.getString("mingcheng")  ;  
     	 		miaoshu = RS_result.getString("miaoshu")  ;  
     	 		shijian = RS_result.getString("shijian")  ;  
     	 		kecheng = RS_result.getString("kecheng")  ;  
  }
   %>
   <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#00FFFF" style="border-collapse:collapse">  
   
   <tr><td>作业名称：</td><td><input type="text" name='mingcheng' value='<%=mingcheng%>'  id='mingcheng'></input></td></tr>
   <tr><td>描述：</td><td><input type="text" name='miaoshu' value='<%=miaoshu%>'  id='miaoshu'></input></td></tr>
   <tr><td>时间：</td><td><input type="text" name='shijian' value='<%=shijian%>'  id='shijian'></input></td></tr>
   <tr><td>课程：</td><td><input type="text" name='kecheng' value='<%=kecheng%>'  id='kecheng'></input></td></tr>
	 
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="Submit" value="提交" onClick="return check();" />
      <input type="reset" name="Submit2" value="重置" /></td>
    </tr>
  </table>
</form>

  </body>
</html>


