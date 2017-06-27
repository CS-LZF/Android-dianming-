<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="db.db"/>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>考勤管理</title><LINK href="CSS.css" type=text/css 
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
<script type="text/javascript"> 
<!-- 
function winOpen( jd , wd ) 
{ 
       window.open ("ditu.jsp?jd="+jd + "&wd=" + wd , "", "height=400, width=400"); 
} 
--> 
</script> 

  </head>

  <body >
 
  <form name="form1" id="form1" method="post" action="">
   搜索:   &nbsp; 
	姓名：<input name="mingcheng" type="text" id="mingcheng" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
	时间：<input name="date" type="text" id="date" />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
	课程：<input name="kecheng" type="text" id="kecheng" />
     <input type="submit" name="Submit" value="查找" />
</form>

<table width="100%" border="0" cellspacing="0" cellpadding="0" >
          <tr >
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr height="24" bgcolor="#353c44">
                <td width="6%" height="19" valign="bottom" ><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom" ><span class="STYLE1">考勤管理</span></td>
              </tr>
            </table>
<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#a8c7ce" style="border-collapse:collapse">  
  <tr>
    <td width="6%"align="center" bgcolor="d3eaef" height="30">序号</td>
    
        	<td bgcolor='d3eaef' align="center" width="14%">学生</td>  
        	<td bgcolor='d3eaef' align="center" width="14%">课程</td>  
        	<td bgcolor='d3eaef' align="center" width="20%">签到日期</td>  
        	<td bgcolor='d3eaef' align="center" width="14%">经度</td>  
        	<td bgcolor='d3eaef' align="center" width="14%">纬度</td> 
    
    <td width="100" align="center" bgcolor="d3eaef">操作</td>
  </tr>
  <%
  String sql="";
  sql="select * from   discuss2  where 1=1";
				  if(request.getParameter("mingcheng")=="" ||request.getParameter("mingcheng")==null )
				  {}
				  else
				  {
				  	sql=sql+" and username like '%"+request.getParameter("mingcheng")+"%'";
				  }
				   if(request.getParameter("date")=="" ||request.getParameter("date")==null )
				  {}
				  else
				  {
				  	sql=sql+" and tbl_date like '%"+request.getParameter("date")+"%'";
				  }
				   if(request.getParameter("kecheng")=="" ||request.getParameter("kecheng")==null )
				  {}
				  else
				  {
				  	sql=sql+" and content like '%"+request.getParameter("kecheng")+"%'";
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
        	<td align="center"><%=RS_result.getString("username")%></td> 
        	<td align="center"><%=RS_result.getString("content")%></td> 
        	<td align="center"><%=RS_result.getString("tbl_date")%></td> 
        	<td align="center"><%=RS_result.getString("answer")%></td> 
        	<td align="center"><%=RS_result.getString("uid")%></td> 
    
    
    <td width="60" align="center"><a href="discuss_list2.jsp" target="rightFrame"  onclick="winOpen(<%=RS_result.getString("answer")%> ,<%=RS_result.getString("uid")%> );">  地图查看</a> &nbsp; 
                  <a href="del.jsp?id=<%=id %>&tablename=discuss2" onClick="return confirm('真的要删除？')">删除</a>  </td>
  </tr>
  	<%
  }
   %>
</table><br>
<display:table name="list" pagesize="5"   id="element" sort="external" partialList="true"  size="total" requestURI="discuss2.do?method=list" >
		   		<display:column  > </display:column>
		   </display:table>
以上数据共<%=i %>条,<a style="cursor:hand" onClick="javascript:window.print();">打印本页</a>
  </body>
</html>

