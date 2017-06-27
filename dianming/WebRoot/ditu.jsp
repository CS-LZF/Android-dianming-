<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zw6oId7dP9WYeAm6oeGy0taI"></script>
<title>地图</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point( <%=request.getParameter("jd")%> , <%=request.getParameter("wd")%>  ), 14);
var marker1 = new BMap.Marker( new BMap.Point( <%=request.getParameter("jd")%> , 
                                   <%=request.getParameter("wd")%>  ) );  // 创建标注
map.addOverlay(marker1);              // 将标注添加到地图中
</script>

