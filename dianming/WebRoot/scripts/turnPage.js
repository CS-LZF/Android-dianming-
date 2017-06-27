/*
  
  参数：
    VIEW_SIZE 每页显示多少条记录
    VIEW_INDEX 当前页的序号（序号从0开始，即第1页的VIEW_INDEX是 0 ）
    LIST_SIZE 总记录数
    REQ 页面对应的的url
        
  返回值：
    返回一个HTML表格的字符串（表格宽度98%）
      
  使用方法：
    1.在页面文件中引用此文件，如<script type="text/JScript" language="JScript" src="../script/turnPage.js"></script>
    2.在适当的位置调用函数：getTurnPageRegion，如
    <script language="javascript">
      document.write(getTurnPageRegion(<%=viewSize%>,<%=viewIndex%>,<%=listSize%>,"clerkSearchResult"));
    </script>
    
*/


var turnPageRegion =""  //返回字符串
var pageCount  //总页数
var VIEW_SIZE  //每页显示多少条记录
var VIEW_INDEX //当前页的序号
var LIST_SIZE  //总记录数
var REQ  //页面对应的的url
var PARAM

//计算总页数
function getPageCount(){
  pageCount = LIST_SIZE / VIEW_SIZE
  pageCount = Math.floor(pageCount) 
  if(LIST_SIZE % VIEW_SIZE != 0) pageCount = pageCount+1
  return pageCount
}

//生成数字链接
function getNumberLink(){
  var numberLink = ""
  
  var style1 = "color: #000000;"
  var style2 = "color: blue;text-decoration: none;"
  var style = style1
  if(pageCount <= 1) 
    return numberLink
  else if(pageCount <= 7){
    for(i=1;i<pageCount+1;i++){
      if(i == VIEW_INDEX+1) 
        numberLink += i +"&nbsp;"
      else
        numberLink += "<a style='"+style+"' href='"+REQ+"?VIEW_SIZE="+VIEW_SIZE+"&VIEW_INDEX="+(i-1)+PARAM+"'>["+i+"]</a>&nbsp;"  
    }
  }
  else{
    if(VIEW_INDEX+1 <=4){
      for(i=1;i<=7;i++){
        if(i == VIEW_INDEX+1) 
          numberLink += i +"&nbsp;"
        else
          numberLink += "<a style='"+style+"' href='"+REQ+"?VIEW_SIZE="+VIEW_SIZE+"&VIEW_INDEX="+(i-1)+PARAM+"'>["+i+"]</a>&nbsp;"  
      }
    }
    else{
      h = pageCount
      if(VIEW_INDEX+1+3 <= pageCount)
        h = VIEW_INDEX+1+3
      k = 1
      do{
        if(h == VIEW_INDEX+1) 
          numberLink = h +"&nbsp;" +numberLink
        else          
          numberLink = "<a style='"+style+"' href='"+REQ+"?VIEW_SIZE="+VIEW_SIZE+"&VIEW_INDEX="+(h-1)+PARAM+"'>["+h+"]</a>&nbsp;"+numberLink  
        k++
        h--
      }while(k <= 7)  
    }    
  }
  numberLink = "&nbsp;" + numberLink 
  
  return numberLink
}

//计算上边界
function getLowIndex(){
  return  VIEW_INDEX * VIEW_SIZE
}

//计算下边界
function getHighIndex(){
  var highIndex = (VIEW_INDEX + 1) * VIEW_SIZE
  if (LIST_SIZE < highIndex)
    highIndex = LIST_SIZE
  return highIndex	
}

//设置公共变量
function setVar(VIEW_SIZE,VIEW_INDEX,LIST_SIZE,REQ,PARAM){
  this.VIEW_SIZE = VIEW_SIZE
  this.VIEW_INDEX = VIEW_INDEX
  this.LIST_SIZE = LIST_SIZE
  this.REQ = REQ
  this.pageCount = getPageCount()
  if(PARAM !=null && PARAM != "")
    this.PARAM = "&"+PARAM
  else
    this.PARAM = "" 
}

function getTurnPageRegion(VIEW_SIZE,VIEW_INDEX,LIST_SIZE,REQ){
  return getTurnPage(VIEW_SIZE,VIEW_INDEX,LIST_SIZE,REQ,null)
}

function getTurnPage(VIEW_SIZE,VIEW_INDEX,LIST_SIZE,REQ,PARAM){  
  setVar(VIEW_SIZE,VIEW_INDEX,LIST_SIZE,REQ,PARAM)
  
  if(pageCount == 0) 
    return "&nbsp;暂无数据";
  
  turnPageRegion = ""+ 
  "<table width='98%' border=0 cellspacing='0' cellpadding='0' height='33' align='center'>"
  +"<tr>"
  
  //第1个TD
  +"<td width='27%' height='33'>"
  +"第<font color='blue'>"+(VIEW_INDEX+1)+"</font>/"+pageCount+"页&nbsp;"
  
  +"&nbsp;<font color='blue'>"+(getLowIndex()+1) + "-" +getHighIndex()+"</font>/"+LIST_SIZE+"条" 
  +"</td>"
   
  //第2个TD
  +"<td width='9%' height='33' align='right'>"
  if(VIEW_INDEX > 0){  
    turnPageRegion +=""+ 
     "<img border='0' src='images/pageup.jpg' style='cursor:hand' title='首页' onclick=window.location.href='"+REQ+"?VIEW_SIZE=" + VIEW_SIZE + "&VIEW_INDEX=0"+this.PARAM+"'>"
    +" <img border='0' src='images/back.jpg' style='cursor:hand' title='上一页' onclick=window.location.href='"+REQ+"?VIEW_SIZE=" + VIEW_SIZE + "&VIEW_INDEX=" + (VIEW_INDEX-1)+this.PARAM+"'>"
  }         
  turnPageRegion +=""
  +"</td>"
  
  //第3个TD
  +"<td width='36%' height='33' align='center'>"  
  turnPageRegion += getNumberLink() 
  turnPageRegion +=""
  +"</td>"
  
  //第4个TD
  +"<td width='9%' height='33'>"
  if(VIEW_INDEX < pageCount-1){  
    turnPageRegion +=""+  
    "<img border='0' src='images/next.jpg' style='cursor:hand' title='下一页' onclick=window.location.href='"+REQ+"?VIEW_SIZE=" + VIEW_SIZE + "&VIEW_INDEX="+(VIEW_INDEX+1)+this.PARAM+"'>"
    +" <img border='0' src='images/pagedown.jpg' style='cursor:hand' title='尾页' onclick=window.location.href='"+REQ+"?VIEW_SIZE=" + VIEW_SIZE + "&VIEW_INDEX=" + (pageCount-1)+this.PARAM+"'>"
   }          
   turnPageRegion +=""+
   "</td>"
   
   //第5个TD
   +"<td width='21%' height='33' align='center'>"
   if(pageCount > 1){
     turnPageRegion +=""
     +"&nbsp;&nbsp;转到 "
     +"<input type='text' id='toThePage' size='2' style='border-style: solid; border-width: 1'>"
     +" 页 "
     +"<img border='0' src='images/go.jpg' style='cursor:hand' title='跳转' onclick='goToPage()'>"	
   }else
     turnPageRegion +="&nbsp;"
    
    turnPageRegion +=""
    +"</td>"
    
    +"</tr>"
    +"</table>"
    
    return turnPageRegion
}

//"跳转"
function goToPage(){
  var toThePage = document.getElementById("toThePage")
  var v = toThePage.value
  if(!isNumberString(v,"0123456789")){
    alert("请输入数字！")
    toThePage.select()
    return;
  }
  
  if(v > pageCount || v == 0){
    alert("超出页码范围！")
    toThePage.select()
    return;
  }
    
  v = v.replace(/(^\s*)|(\s*$)/g, "")
  v = v-1
  window.location.href=""+REQ+"?VIEW_SIZE=" + VIEW_SIZE + "&VIEW_INDEX="+v+PARAM
}

//判断输入串是否合法数字
function isNumberString (InString,RefString)
{       
  var xsdgs=0
  if(InString.length==0) return false
  
  for (Count=0; Count < InString.length; Count++)  
  {
    TempChar= InString.substring (Count, Count+1)
    if (RefString.indexOf (TempChar, 0)==-1)  
      return false
    else if(TempChar==".")
    {  
      xsdgs=xsdgs+1
      if (xsdgs>1) return false    //小数点超过一个
      if (Count==InString.length-1) return false  //小数点是最后一位 
    }   
  }	
  return true
}