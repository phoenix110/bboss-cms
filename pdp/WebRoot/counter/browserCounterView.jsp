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
	function doChange() {
		var startTime = document.getElementById("startTime1").value;
		
		if (startTime == null || startTime == "") {
			$.dialog.alert("请选择日期！");
			return;
		}
		
		document.getElementById("span1").innerHTML = "<span>"+startTime+"</span>";
		document.getElementById("frame1").src="showBrowserCounterDistribute.freepage?type=custom&siteId=${param.siteId}&startTime="+startTime+"&endTime="+startTime;
	}
</script>
</head>
<body>
		<div class="tabbox" >
			<ul class="tab" id="menu1">
				<li><a href="javascript:void(0)" class="current" onclick="setTab(1,0)" id="span1"><span>今日</span></a></li>
				<li><a href="javascript:void(0)" onclick='setTab(1,1,{frameid:"mm.frame2",framesrc:"showBrowserCounterDistribute.freepage?type=yesterday&siteId=${param.siteId}"})'><span>昨日</span></a></li>
				<li><a href="javascript:void(0)" onclick='setTab(1,2,{frameid:"mm.frame3",framesrc:"showBrowserCounterDistribute.freepage?type=week&siteId=${param.siteId}"})'><span>本周</span></a></li>
				<li><a href="javascript:void(0)" onclick='setTab(1,3,{frameid:"mm.frame4",framesrc:"showBrowserCounterDistribute.freepage?type=7days&siteId=${param.siteId}"})'><span>最近7天</span></a></li>
				<li><a href="javascript:void(0)" onclick='setTab(1,4,{frameid:"mm.frame5",framesrc:"showBrowserCounterDistribute.freepage?type=month&siteId=${param.siteId}"})'><span>本月</span></a></li>
				<li><a href="javascript:void(0)" onclick='setTab(1,5,{frameid:"mm.frame6",framesrc:"showBrowserCounterDistribute.freepage?type=30days&siteId=${param.siteId}"})'><span>最近30天</span></a></li>
			</ul>
		</div>
		<div style="height: 26px">
			<input  id="startTime1"  name="startTime1" class="Wdate" type="text" onclick="WdatePicker()"/>
				<a href="javascript:void(0)" class="bt_1"
												id="queryButton" onclick="doChange()"><span>查看</span> </a>
		</div>
		<div id="main1">
			<ul  id="tab1" style="display:block;">
				<iframe id="mm.frame1" src="showBrowserCounterDistribute.freepage?type=today&siteId=${param.siteId}"  frameborder="0" width="100%"  height="900" ></iframe>
			</ul>
			<ul id="tab2" style="display: none;">
				<iframe id="mm.frame2" frameborder="0" width="100%" height="900" ></iframe>
			</ul>
			<ul id="tab3" style="display: none;">
				<iframe id="mm.frame3" frameborder="0" width="100%" height="900" ></iframe>
			</ul>
			<ul id="tab4" style="display: none;">
				<iframe id="mm.frame4" frameborder="0" width="100%" height="900" ></iframe>
			</ul>
			<ul id="tab5" style="display: none;">
				<iframe id="mm.frame5"  frameborder="0" width="100%" height="900" ></iframe>
			</ul>
			<ul id="tab6" style="display: none;">
				<iframe id="mm.frame6"   frameborder="0" width="100%" height="900" ></iframe>
			</ul>
		</div>
</body>
</html>
