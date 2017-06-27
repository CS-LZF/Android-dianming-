<%@ page contentType="text/html;charset=utf-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>

<div  style="height:100%;">
  <ul id="navigation">
  
  	<li> <a class="head">教师管理</a>
      <ul>
        <li> <a href="user.do?method=list" target="rightFrame"> 教师管理</a>  </li>
        <li> <a href="user_add.jsp" target="rightFrame"> 新增教师</a>    </li>
      </ul>
    </li>

  	<li> <a class="head"> 学生管理</a>
      <ul>
        <li> <a href="user_tb_list.jsp" target="rightFrame"> 学生管理</a></li>
      </ul>
    </li>

    <li> <a class="head">课程管理</a>
      <ul> 
        <li> <a href="foodList.do?method=goAdd" target="rightFrame"> 新增课程</a>    </li>
        <li> <a href="foodList.do" target="rightFrame"> 显示课程</a>  </li>
      </ul>
    </li>
        
    <li> <a class="head">考勤管理</a>
      <ul> 
        <li> <a href="discuss_list2.jsp" target="rightFrame"> 显示考勤</a>  </li> 
      </ul>
    </li>
    
	

    
	
	<li> <a class="head">请假信息管理</a>
      <ul>
        <li> <a href="qingjia_list.jsp" target="rightFrame"> 请假信息管理</a>    </li>
        <li> <a href="qingjia_add.jsp"  target="rightFrame"> 新增请假信息</a>  </li>
      </ul>
    </li>



    
    
  </ul>
</div>
</body>
</html>
