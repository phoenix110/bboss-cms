<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>


<%--
	描述：浏览统计计数数据查询页面
	作者：gw_hel
	版本：1.0
	日期：2012-08-27
	 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>浏览统计数据</title>
<%@ include file="/common/jsp/css-lhgdialog.jsp"%>
<script type="text/javascript">
	
	//页面加载时查询列表数据
	$(document).ready(function() {
	 	queryModuleInfo("${param.siteId}");
	 	queryList(); 
	  });
	
	//查询浏览统计列表数据(页面加载时会加载这个方法，页面调用查询时也会调用)
	 function queryList() {	
	 	var siteId = "${param.siteId}";
		var channelId = $("#channelId").val();
		var docName = $("#docName").val();
		var browserType = $("#browserType").val();
		var browserIp = $("#browserIp").val();
		var browserUser = $("#browserUser").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
	   	$("#custombackContainer").load("showBrowserCounterList.page #customContent", { siteId:siteId, channelId:channelId, docName:docName, browserType:browserType, browserIp:browserIp, browserUser:browserUser, startTime:startTime, endTime:endTime }, function(){loadjs()});
	}
	//查询相应的模块
	function queryModuleInfo(appId) {
	  if (appId != null && appId != "") {
	  	$.ajax({
	 	 	type: "POST",
			url : "../sanylog/getModuleBySiteId.page",
			data :{"appId":appId},
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
				 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
			success : function(data){
				$.each(data,function(i,o){
					document.getElementById("channelId").options.add(new Option(data[i].moduleName, data[i].moduleId));
				});	
			}	
		 });
	  }
	}
	//查询频道信息(这个可以用来查询本站点下面的模块名称，要做适当的改动)
	function queryChannelInfo(siteId) {
	  if (siteId != null && siteId != "") {
	  	$.ajax({
	 	 	type: "POST",
			url : "../channel/getChannelBySiteId.page",
			data :{"siteId":siteId},
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
				 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
			success : function(data){
				$.each(data,function(i,o){
					document.getElementById("channelId").options.add(new Option(data[i].name, data[i].channelId));
				});	
			}	
		 });
	  }
	}
	function checkBrowserDetail(browserId){
	    var url='';
	  	  title="浏览记录明细";
	  	  url='<%=request.getContextPath()%>/sanylog/checkBrowserDetail.page?browserId='+browserId;
	  		$.dialog({ id:'iframeNewId', title:title,width:1200,height:550, content:'url:'+url});
	  	
 }
	 //重置查询条件
	 function doreset() {
   		$("#reset").click();
   	}
</script>
</head>
<body>
	<div class="mcontent">
		<div id="searchblock">
			<div class="search_top">
				<div class="right_top"></div>
				<div class="left_top"></div>
			</div>
			<div class="search_box">
				<form id="queryForm" name="queryForm">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="left_box"></td>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="table2">
									<tr>
										<th>菜单路径：</th>
										<td>
											<select id="channelId" name="channelId"  maxlength="40"><!-- class="w120" -->
												<option value="">无限制</option>
											<select>
										</td>
									 <th style="display: none">页面：</th>
										<td style="display: none"><input id="docName" name="docName"
											type="text" value="" class="w120" /></td> 	
										<th>用户：</th>
										<td><input id="browserUser" name="browserUser"
											type="text" value="" class="w120" /></td>
									</tr>
									<tr>
									<th>统计时间：</th>
										<td><input  id="startTime"  name="startTime" class="Wdate" type="text" onclick="WdatePicker()" />
													&nbsp;&nbsp;---&nbsp;&nbsp;
												<input id="endTime" name="endTime"  class="Wdate" type="text" onclick="WdatePicker()" /></td>
										
										<th>IP地址：</th>
										<td><input id="browserIp" name="browserIp" type="text"
											value="" class="w120" /></td>
										<th>浏览器类型：</th>
										<td>
											<select id="browserType" name="browserType" class="w120">
												<option value="">无限制</option>
												<option value="MSIE">IE</option>
												<option value="Firefox">Firefox</option>
												<option value="Safari">Safari</option>
												<option value="Chrome">Chrome</option>
											<select>
										</td>
										<th>&nbsp;</th>
										<td><a href="javascript:void(0)" class="bt_1"
											id="queryButton" onclick="queryList()"><span>查询</span> </a> <a
											href="javascript:void(0)" class="bt_2" id="resetButton"
											onclick="doreset()"><span>重置</span> </a> <input type="reset"
											id="reset" style="display: none" /></td>
									</tr>
								</table>
							</td>
							<td class="right_box"></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="search_bottom">
				<div class="right_bottom"></div>
				<div class="left_bottom"></div>
			</div>
		</div>
		<div class="title_box">
			<strong>浏览计数统计数据</strong>
		</div>
		<div id="custombackContainer"></div>
	</div>
</body>
</html>
