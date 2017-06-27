<%@ page contentType="text/html;charset=utf-8" language="java" %>

 <%
	if( request.getSession().getAttribute("type") !=null   ){
		String type = request.getSession().getAttribute("type").toString() ;
		if( type.equals("3") ){
	 // 教师
  			response.sendRedirect("left3.jsp");
  			return;
		}else if( type.equals("2") ){
	 // 学生
  			response.sendRedirect("left2.jsp");
  			return;
		}
	} 
	// 管理员
	response.sendRedirect("left1.jsp");
%>

