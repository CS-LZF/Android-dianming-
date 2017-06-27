<%@ page language="java" pageEncoding="gb2312"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
<%@ include file="/commons/taglibs.jsp"%>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 16px;
}
.STYLE6 {color: d3eaef; font-size: 16px; }
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
	color:d3eaef;
}
-->
</style>
</head>

<body class="mainbody">
 
		

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1">
							<c:choose>
								<c:when test="${(not empty item.info_flag) && (item.info_flag == 0 )}">
						    		<label>显示推荐</label>
								</c:when>
								<c:when test="${(not empty item.info_flag) && (item.info_flag == 1 )}">
						    		<label>显示热门</label>
								</c:when>
								<c:otherwise>
									<label>显示全部</label>
								</c:otherwise>
							</c:choose>
					</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1"> 
                 &nbsp;&nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; <span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="30" bgcolor="d3eaef" class="STYLE10"><div align="center">
          		序号 
        </div></td>
        <td width="15%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"> 课程 </span></div></td>
         <td width="15%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">教师ＩＤ </span></div></td>
        <td width="49%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"> 教师姓名</span></div></td>
        
        <td width="5%" height="30" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
    

			<c:forEach var="item" items="${list}" varStatus="idx"  >  
			
		      <tr>
		        <td height="30" bgcolor="#FFFFFF">
		        	<div align="center"> ${idx.index+1} </div>
		        </td>
		        
	
		        <td height="30" bgcolor="#FFFFFF" class="STYLE19"><div align="center"> ${item.info_name}  </div></td>
		        <td height="30" bgcolor="#FFFFFF" class="STYLE19"><div align="center"> ${item.info_pic}  </div></td>
		        <td height="30" bgcolor="#FFFFFF" class="STYLE19"><div align="center"> ${item.info_description}  </div></td>
				
		        <td height="30" bgcolor="#FFFFFF"><div align="center" class="STYLE21">  
		         		<a href="foodList.do?method=del&id=${item.info_id}&f=${item.info_name}&all=${all}" onClick="return confirm('真的要删除？')"> 删除</a>  </div>
		         </td>
		      </tr>
	 
    	 	</c:forEach>	
	 <display:table name="list" pagesize="5"   id="element" sort="external" partialList="true"  size="total" requestURI="foodList.do?method=list" >
		   		<display:column  > </display:column>
		   </display:table>
    </table></td>
  </tr>
</table>
 

</body>

</html>