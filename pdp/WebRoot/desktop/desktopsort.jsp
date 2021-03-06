<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.frameworkset.platform.security.AccessControl"%>
<%@ include file="/common/jsp/accessControl.jsp"%>
<%@ include file="/common/jsp/importAndTaglib.jsp"%>
<%@ taglib prefix="tab" uri="/WEB-INF/tabpane-taglib.tld"%>
	<%--
	描述：桌面快捷排序设置
	作者：qian.wang
	版本：1.0
	日期：2011-09-20
	 --%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
		<%@ include file="/common/jsp/css-lhgdialog.jsp"%>
		<script type="text/javascript">
		//这是扩展Firefox下moveRows()这个方法，否则moveRows()是不被firefox支持
		 if (typeof Element != 'undefined') Element.prototype.moveRow = function (sourceRowIndex, targetRowIndex) {//执行扩展操作
	        if (!/^(table|tbody|tfoot|thead)$/i.test(this.tagName) || sourceRowIndex === targetRowIndex) return false;
	        var pNode = this;
	        if (this.tagName == 'TABLE') pNode = this.getElementsByTagName('tbody')[0]; 
	        //firefox会自动加上tbody标签，所以需要取tbody，直接table.insertBefore会error
	        var sourceRow = pNode.rows[sourceRowIndex], targetRow = pNode.rows[targetRowIndex];
	        if (sourceRow == null || targetRow == null) return false;
	        var targetRowNextRow = sourceRowIndex > targetRowIndex ? false : getTRNode(targetRow, 'nextSibling');
	        if (targetRowNextRow === false) pNode.insertBefore(sourceRow, targetRow); //后面行移动到前面，直接insertBefore即可
	        else {//移动到当前行的后面位置，则需要判断要移动到的行的后面是否还有行，有则insertBefore，否则appendChild
	            if (targetRowNextRow == null) pNode.appendChild(sourceRow);
	            else pNode.insertBefore(sourceRow, targetRowNextRow);
	        }
	    }
	    //Firefox下表格里面的空白，回车也算一个节点，所以需要过滤一下节点类型。
	    function getTRNode(nowTR, sibling) { while (nowTR = nowTR[sibling]) if (nowTR.tagName == 'TR') break; return nowTR; }
		</script>	
		
	
	<script type="text/javascript">
	function blockUI(msgContent){
	if (!msgContent){
		msgContent = '正在处理，请稍候...';
	}
	$("<div class=\"datagrid-mask\"></div>").css({display:"block", width:"100%",height:$(window).height()}).appendTo("body");
	$("<div class=\"datagrid-mask-msg\"></div>").html(msgContent).appendTo("body").css({display:"block",left: ($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2}); 
	}
	
	
	function unblockUI(){
		$(".datagrid-mask-msg").remove();
		$(".datagrid-mask").remove();
	}
	function saveSort(){
	    var item_order = $(".item_order");
	    item_order.each(function(i){
	       $(this).val(i);
		 });
	    	$("#menusort").form('submit', {
				    "url": "updatemenusort.page",
				    onSubmit:function(){			
						//显示遮罩							
						blockUI();	
				    },
				    success:function(responseText){	
				    	//去掉遮罩	
						unblockUI();
						if(responseText == "ok")
						$.messager.alert("提示对话框" , "保存成功!");
						else 
						$.messager.alert("提示对话框" , "保存失败!");	
				    }
				});	
	    }
	function sortByMenuName(){
     
           $.post("updatemenusortBymenuName.page",function(data){
                 if(data == "ok")
					$.messager.alert("提示对话框" , "按目录名排序成功!");
				 else
				    $.messager.alert("提示对话框" , "按目录名排序失败!");
			});

	}
	
	var contents = {
	error : "移动按钮异常",
	ico_top : "↑",
	ico_bottom : "↓",
	button_top : "<INPUT type=\"button\" value=\"↑\" onclick=\"trMove(this)\">",
	button_bottom : "<INPUT type=\"button\" value=\"↓\" onclick=\"trMove(this)\">"
	}
	
	var trMove = function(obj) {
	//var thisTr = obj.parentNode.parentNode;
	//var topTr = thisTr.previousSibling;
	//var bottomTr = thisTr.nextSibling;
	var thisTr = obj.parentNode.parentNode;
	var localIndex = thisTr.rowIndex;
	var trArr = document.getElementById("tab").rows;
	if(obj.value == contents.ico_top) {
	    
	  if(localIndex != 1)
		//thisTr.swapNode(topTr);
		document.getElementById("tab").moveRow(localIndex,localIndex-1);
	} else if(obj.value == contents.ico_bottom){
	    if(localIndex != trArr.length-1)
		//thisTr.swapNode(bottomTr);
		document.getElementById("tab").moveRow(localIndex,localIndex+1);
	} else {
		alert(contents.error);
	}
	//tabHide(1);
	}
	
	var tabHide = function(start) {
	var tab = document.getElementById("tab");
	var trs = tab.rows;
	var firstTr = trs[start];
	var lasterTR = trs[trs.length - 1];
	//for(var i = start + 1; i < trs.length; i++) {
	//    trs[i].childNodes[2].innerHTML = contents.button_top;
	//	trs[i].childNodes[3].innerHTML = contents.button_bottom;
   // }
	
	//alert(firstTr.children[3].children[0].innerHTML);
	//firstTr.children[3].childNodes[0].innerHTML = "X";
	//firstTr.children[3].childNodes[1].innerHTML = "X";
	//lasterTR.childNodes[3].innerHTML = "X";
	}
	
	function trMoveTofirst(obj){
	  var thisTr = obj.parentNode.parentNode;
	  var localIndex = thisTr.rowIndex;
	  if(localIndex != 1)
	  document.getElementById("tab").moveRow(localIndex,1);
	 // alert(thisTr.rowIndex);
	  
	}
	function trMoveToend(obj){
	  var thisTr = obj.parentNode.parentNode;
	  var localIndex = thisTr.rowIndex;
	  var trArr = document.getElementById("tab").rows;
	  if(localIndex != trArr.length-1)
	  document.getElementById("tab").moveRow(localIndex,trArr.length-1);
	  
	}
	
	// 新增桌面快捷菜单
	function addItem() {
		var url = "";
		if ('${isdefault}' ==  'true') {
			 url = "<%=path%>/desktop/deskmenu.jsp?customtype=default";
		}else {
			 url = "<%=path%>/desktop/deskmenu.jsp";
		}
		$.dialog({ id:'addItem', title:'新增桌面快捷菜单',width:400,height:450, content:'url:'+url}); 
	}
	
	// 删除桌面快捷菜单
	function delItem() {
		var userid = '';
		if ('${isdefault}' ==  'false') {
			userid = '<%=accesscontroler.getUserID()%>';
		}else {
			userid = '-1';
		}
		
		var ids="";
		
		$("#tab tr:gt(0)").each(function() {
			if ($(this).find("#CK").get(0).checked == true) {
	             ids=ids+$(this).find("#CK").val()+",";
	        }
	    });
		
	    if(ids==""){
	       $.dialog.alert('请选择需要删除的菜单！');
	       return false;
	    }
	    
		$.dialog.confirm('确定要删除吗？', function(){
			
			$.ajax({
		 	 	type: "POST",
				url : "deldeskmenu.page",
				data :{"ids":ids,"userid":userid},
				dataType : 'json',
				async:false,
				beforeSend: function(XMLHttpRequest){
					XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
				success : function(data){
					if(data=="success"){
						 window.location.href = window.location.href ; 
					}else{
						$.dialog.alert("删除桌面快捷菜单出错："+data,function(){});
					}
				}	
			 });
		},function(){});    
	}
	
	
	</script>
</head>
<body>
	<button onclick="addItem()" class="button">新增</button>
	<button onclick="delItem()" class="button">删除</button>
	<button onclick="saveSort()" class="button">保存</button>
	<button onclick="sortByMenuName()" class="button" >按名称升序</button>
	
   <form action="" id="menusort" method="post" >
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tab" class="stable">
	     <tr>
	     	 <th align=center>
	     	 	<input id="CKA" name="CKA" type="checkbox" onClick="checkAll('CKA','CK')">
	     	 </th>
		     <th nowrap="nowrap" >菜单名称</td>
		     <th nowrap="nowrap" >菜单路径</td>
		     <th nowrap="nowrap" >子系统</td>
		     <th nowrap="nowrap" >操作</td>
	     </tr>
	     
	     <pg:list requestKey="menulist">
	       <tr >
	       	 <td class="td_center">
                <input id="CK" type="checkbox" name="CK" onClick="checkOne('CKA','CK')" value="<pg:cell colName="menupath" />"/>
             </td>
	         <td >
	         	<pg:cell colName="menuname"/>
	         	<input type="hidden" name="menuname" value="<pg:cell colName="menuname"/>"/>
	         	<input type="hidden" name="userid"  value="<pg:cell colName="userid"/>"/>
	         </td>
	         <td >
	         	<pg:cell colName="menupath"/>
	         	<input type="hidden" name="menupath" value="<pg:cell colName="menupath"/>"/>
	         </td>
	         <td >
	         	<pg:cell colName="subsystem"/>
	         	<input type="hidden" name="subsystem" value="<pg:cell colName="subsystem"/>"/>
	         	<input type="hidden" name="item_order" class="item_order" value="<pg:cell colName="item_order"/>"/>
	         </td>
	         <td >
		         <INPUT type="button" value="顶" onclick="trMoveTofirst(this)" />
		         <INPUT type="button" value="↑"  onclick="trMove(this)" />
		         <INPUT type="button" value="↓"  onclick="trMove(this)" />
		         <INPUT type="button" value="底" onclick="trMoveToend(this)" />
	         </td>
	       </tr> 
	     </pg:list>
	   </table>
   </form>
   
	<!-- <SCRIPT LANGUAGE="JavaScript">tabHide(1)</SCRIPT> -->
</body>
</html>
