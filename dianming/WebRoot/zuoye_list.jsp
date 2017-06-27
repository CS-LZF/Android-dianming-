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
    
    <title>作业信息记录</title><LINK href="CSS.css" type=text/css 
rel=stylesheet>
    

  </head>

  <body >
  <p>已有作业信息记录列表：</p>
  <form name="form1" id="form1" method="post" action="">
   搜索:   &nbsp; 
	作业名称：<input name="mingcheng" type="text" id="mingcheng" /> 
	描述：<input name="miaoshu" type="text" id="miaoshu" /> 
	时间：<input name="shijian" type="text" id="shijian" /> 
     <input type="submit" name="Submit" value="查找" />
</form>

<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="00FFFF" style="border-collapse:collapse">  
  <tr>
    <td width="30" align="center" bgcolor="CCFFFF">序号</td>
    
        	<td bgcolor='#CCFFFF'>作业名称</td>  
        	<td bgcolor='#CCFFFF'>描述</td>  
        	<td bgcolor='#CCFFFF'>时间</td>  
        	<td bgcolor='#CCFFFF'>课程</td>  
    
    <td width="60" align="center" bgcolor="CCFFFF">操作</td>
  </tr>
  <%
  String sql="";
  sql="select * from   zuoye  where 1=1";
				  if(request.getParameter("mingcheng")=="" ||request.getParameter("mingcheng")==null )
				  {}
				  else
				  {
				  	sql=sql+" and mingcheng like '%"+new String(request.getParameter("mingcheng").getBytes("8859_1"))+"%'";
				  }
				  if(request.getParameter("miaoshu")=="" ||request.getParameter("miaoshu")==null )
				  {}
				  else
				  {
				  	sql=sql+" and miaoshu like '%"+new String(request.getParameter("miaoshu").getBytes("8859_1"))+"%'";
				  }
				  if(request.getParameter("shijian")=="" ||request.getParameter("shijian")==null )
				  {}
				  else
				  {
				  	sql=sql+" and shijian like '%"+new String(request.getParameter("shijian").getBytes("8859_1"))+"%'";
				  }
 
  sql=sql+" order by id desc";
  
 ResultSet RS_result=connDbBean.executeQuery(sql);
 String id="";
  
  								
 int i=0;
 
 while(RS_result.next()){
	 i=i+1;
	 id=RS_result.getString("id");  
%>
  <tr>
    <td width="30" align="center"><%=i %></td>
        	<td><%=RS_result.getString("mingcheng")%></td> 
        	<td><%=RS_result.getString("miaoshu")%></td> 
        	<td><%=RS_result.getString("shijian")%></td> 
        	<td><%=RS_result.getString("kecheng")%></td> 
    
    <td width="60" align="center"><a href="zuoye_updt.jsp?id=<%=id%>">修改</a>  <a href="del.jsp?id=<%=id %>&tablename=zuoye" onClick="return confirm('真的要删除？')">删除</a></td>
  </tr>
  	<%
  }
   %>
</table><br>
以上数据共<%=i %>条,<a style="cursor:hand" onClick="javascript:window.print();">打印本页</a>
  </body>
</html>

