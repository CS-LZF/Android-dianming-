
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
    
    <title>请假信息记录</title><LINK href="CSS.css" type=text/css 
rel=stylesheet>
    <style type="text/css">
<!--

.STYLE1 {
	color: #e1e2e3;
	font-size: 16px;
}
.STYLE6 {color:d3eaef; font-size: 16px; }
.STYLE10 {color: d3eaef; font-size: 16px; }
.STYLE19 {
	color: d3eaef;
	font-size: 16px;
}
.STYLE21 {
	font-size: 16px;
	color: d3eaef;
}
.STYLE22 {
	font-size: 16px;
	color: d3eaef;
}
-->
</style>

  </head>

  <body >
  <form name="form1" id="form1" method="post" action="">
   搜索:   &nbsp; 
	姓名：<input name="biaoti" type="text" id="biaoti" /> 
	课程：<input name="miaoshu" type="text" id="miaoshu" /> 
	请假时间：<input name="shijian" type="text" id="shijian" /> 
	请假状态：<input name="zhuangtai" type="text" id="zhuangtai" /> 
     <input type="submit" name="Submit" value="查找" />
</form>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
          <tr >
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr height="24" bgcolor="#353c44">
                <td width="6%" height="19" valign="bottom" ><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom" ><span class="STYLE1">请假管理</span></td>
              </tr>
            </table>
<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#a8c7ce" style="border-collapse:collapse">  
  <tr>
    <td width="30" align="center" bgcolor="d3eaef" height="30">序号</td>
    
        	<td bgcolor='d3eaef'>标题</td>  
        	<td bgcolor='d3eaef'>描述</td>  
        	<td bgcolor='d3eaef'>请假时间</td>  
        	<td bgcolor='d3eaef'>请假状态</td>  
        	<td bgcolor='d3eaef'>学生</td>  
        	<td bgcolor='d3eaef'>课程</td>  
    
    <td width="60" align="center" bgcolor="d3eaef">操作</td>
  </tr>
  <%
  String sql="";
  sql="select * from   qingjia  where 1=1";
				  if(request.getParameter("biaoti")=="" ||request.getParameter("biaoti")==null )
				  {}
				  else
				  {
				  	sql=sql+" and xusheng like '%"+ request.getParameter("biaoti")+"%'";
				  }
				  if(request.getParameter("miaoshu")=="" ||request.getParameter("miaoshu")==null )
				  {}
				  else
				  {
				  	sql=sql+" and kecheng like '%"+ request.getParameter("miaoshu")+"%'";
				  }
				  if(request.getParameter("shijian")=="" ||request.getParameter("shijian")==null )
				  {}
				  else
				  {
				  	sql=sql+" and shijian like '%"+ request.getParameter("shijian")+"%'";
				  }
				  if(request.getParameter("zhuangtai")=="" ||request.getParameter("zhuangtai")==null )
				  {}
				  else
				  {
				  	sql=sql+" and zhuangtai like '%"+request.getParameter("zhuangtai")+"%'";
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
        	<td><%=RS_result.getString("biaoti")%></td> 
        	<td><%=RS_result.getString("miaoshu")%></td> 
        	<td><%=RS_result.getString("shijian")%></td> 
        	<td><%=RS_result.getString("zhuangtai")%></td> 
        	<td><%=RS_result.getString("xusheng")%></td> 
        	<td><%=RS_result.getString("kecheng")%></td> 
    
    <td width="60" align="center"><a href="qingjia_updt.jsp?id=<%=id%>">修改</a>  <a href="del.jsp?id=<%=id %>&tablename=qingjia" onClick="return confirm('真的要删除？')">删除</a></td>
  </tr>
  	<%
  }
   %>
</table><br>
以上数据共<%=i %>条,<a style="cursor:hand" onClick="javascript:window.print();">打印本页</a>
  </body>
</html>

