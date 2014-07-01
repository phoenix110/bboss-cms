<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>
<%--
描述：流程实例列表
作者：谭湘
版本：1.0
日期：2014-05-08
 --%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>流程实例信息</title>
<%@ include file="/common/jsp/css.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/html/js/dialog/lhgdialog.js?self=false&skin=sany"></script>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#wait").hide();
	
	queryList();
			
	$('#delBatchButton').click(function() {
		delInst();
    });
	
	$('#stBatchButton').click(function() {  
		startInst();	 		  
    });
	
	$('#upBatchButton').click(function() {  
		upBatchButton();	 		  
    });
	
	$("#businessType").combotree({
	   url:"<%=request.getContextPath()%>/workflow/businesstype/showComboxBusinessTree.page"
	});
	
});

// 删除实例
function delInst(){
	var ids="";
	
	$("#tb tr:gt(0)").each(function() {
		if ($(this).find("#CK").get(0).checked == true) {
             ids=ids+$(this).find("#PROC_INST_ID_").val()+",";
        }
    });
	
    if(ids==""){
       $.dialog.alert('请选择需要删除的流程！');
       return false;
    }
    
    var url="<%=request.getContextPath()%>/workflow/repository/delProcessInstance.jsp?ids="+ids;
    $.dialog({ id:'iframeNewId', title:'填写删除原因',width:400,height:200, content:'url:'+url});  
}

// 开始实例
function startInst(){
	var processKey = $("#wf_key").val();
	
	var url = "<%=request.getContextPath()%>/workflow/repository/toStartProcessInst.page?processKey="
			+ processKey;
	
	$.dialog({ title:'流程节点参数配置-'+processKey,width:1000,height:620, content:'url:'+url});     
	 
}

// 升级
function upBatchButton() {
	$.dialog.confirm('确定升级？', function(){
    	$.ajax({
	 	 	type: "POST",
			url : "<%=request.getContextPath()%>/workflow/repository/upgradeInstances.page",
			data :{"processKey":'${processKey}'},
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
				 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
			success : function(data){
				if (data != 'success') {
					alert("流程实例升级出错："+data);
				}else {
					modifyQueryData();
					close();	
				}
			}	
		 });
     },function(){
     		
     });
}

// 查询数据
function queryList(){	
	var wf_key = $("#wf_key").val();
	var wf_Inst_Id = $("#wf_Inst_Id").val();
	var wf_start_time1 = $("#wf_start_time1").val();
	var wf_start_time2 = $("#wf_start_time2").val();
	var wf_end_time1 = $("#wf_end_time1").val();
	var wf_end_time2 = $("#wf_end_time2").val();
	var wf_state = $("#wf_state").val();
	var wf_business_key = $("#wf_business_key").val();
	var wf_version = $("#wf_version").combobox('getValues');
	var businessTypeId = $("#businessType").combotree('getValue');
	
	$("#instanceContainer").load("<%=request.getContextPath()%>/workflow/repository/queryProcessIntsByKey.page? #customContent", 
		{"wf_key":wf_key,"wf_Inst_Id":wf_Inst_Id,"wf_start_time1":wf_start_time1,"wf_start_time2":wf_start_time2,
		"wf_end_time1":wf_end_time1,"wf_end_time2":wf_end_time2,"wf_state":wf_state,"wf_versions":wf_version,
		"wf_business_key":wf_business_key,"businessTypeId":businessTypeId},
		function(){loadjs();});
}

function modifyQueryData(){
	$("#instanceContainer").load("<%=request.getContextPath()%>/workflow/repository/queryProcessIntsByKey.page?"+$("#querystring").val()+" #customContent",function(){loadjs()});
}

//查看流程实例详情
function viewDetailInfo(processInstId) {
	var url="<%=request.getContextPath()%>/workflow/taskManage/viewTaskDetailInfo.page?processInstId="+processInstId;
	$.dialog({ title:'明细查看-'+$("#wf_key").val(),width:1100,height:620, content:'url:'+url});
}

//流程实例挂起
function suspendProcess(processInstId){
	$.dialog.confirm('确定将实例挂起？', function(){
    	$.ajax({
	 	 	type: "POST",
			url : "<%=request.getContextPath()%>/workflow/repository/suspendProcessInst.page",
			data :{"processInstId":processInstId},
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
				 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
			success : function(data){
				if (data != 'success') {
					alert("流程实例挂起出错："+data);
				}else {
					modifyQueryData();
					close();	
				}
			}	
		 });
     },function(){
     		
     });
}

//流程实例激活
function activateProcess(processInstId){
	$.dialog.confirm('确定将实例激活？', function(){
    	$.ajax({
	 	 	type: "POST",
			url : "<%=request.getContextPath()%>/workflow/repository/activateProcessInst.page",
			data :{"processInstId":processInstId},
			dataType : 'json',
			async:false,
			beforeSend: function(XMLHttpRequest){
				 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
				},
			success : function(data){
				if (data != 'success') {
					alert("流程实例激活出错："+data);
				}else {
					modifyQueryData();
					close();	
				}
			}	
		 });
     },function(){
     		
     });
}

function doreset(){
	$("#wf_version").combobox("setValues","");
   	$("#reset").click();
}
	
</script>
</head>
	
<body>
	<div class="mcontent" style="width:98%;margin:0 auto;overflow:auto;">
	
	<pg:empty actual="${processKey}">
		<sany:menupath menuid="processInsts"/>
	</pg:empty>	
	
		<div id="rightContentDiv">
			
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
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
										<tr>
											<th>流程实例ID：</th>
											<td><input id="wf_Inst_Id" name="wf_Inst_Id" type="text" class="w120" /></td>
											<th>状态：</th>
											<td>
												<select id="wf_state" name="wf_state" class="select1" style="width: 125px;">
												    <option value="0" selected>全部</option>
													<option value="1">进行中</option>
													<option value="2">挂起</option>
													<option value="3">完成</option>
												</select>
											</td>
											<th>开启时间：</th>
											<td><input id="wf_start_time1" name="wf_start_time1" type="text"
												 onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="w120" />
												 ~<input id="wf_start_time2" name="wf_start_time2" type="text"
												 onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="w120" />
											</td>
											<td rowspan="3" style="text-align:right">
												<a href="javascript:void(0)" class="bt_1" id="queryButton" onclick="queryList()"><span><pg:message code="sany.pdp.common.operation.search"/></span></a>
												<a href="javascript:void(0)" class="bt_2" id="resetButton" onclick="doreset()"><span><pg:message code="sany.pdp.common.operation.reset"/></span></a>
												<input type="reset" id="reset" style="display:none"/>
											</td>
										</tr>
										<tr>
											<th>流程key：</th>
											<td>
												<input id="wf_key" name="wf_key" type="text" class="w120" value="${processKey}"
												<pg:notempty actual="${processKey}" > disabled</pg:notempty>/>
											</td>
											<th>业务主题：</th>
											<td>
												<input id="wf_business_key" name="wf_business_key" type="text" class="w120" />
											</td>
											<th>结束时间：</th>
											<td><input id="wf_end_time1" name="wf_end_time1" type="text"
												 onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="w120" />
												 ~<input id="wf_end_time2" name="wf_end_time2" type="text"
												 onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="w120" />
											</td>
										</tr>
										<tr>
											<th>版本：</th>
											<td>
												<select id="wf_version" name="wf_version" class="easyui-combobox" style="width: 120px;" editable="false" panelHeight="100px;" multiple="multiple">
													<pg:list actual="${versionList}" >            
														<option value="<pg:cell colName="VERSION_"/>" ><pg:cell colName="VERSION_"/> </option>
													</pg:list>  
												</select>
											</td>
											<th>业务类型：</th>
											<td>
												<select class="easyui-combotree" id='businessType' name="businessType" required="false"
														style="width: 120px;">
											</td>
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
				<div class="rightbtn">
					<pg:notempty actual="${processKey}" >
						<a href="#" class="bt_small" id="stBatchButton"><span>开启</span></a>
						<a href="#" class="bt_small" id="upBatchButton"><span>升级</span></a>
					</pg:notempty>
					<a href="#" class="bt_small" id="delBatchButton"><span>删除</span></a>
				</div>
					
				<strong>实例列表</strong>
				<img id="wait" src="<%=request.getContextPath()%>/common/images/wait.gif" />				
			</div>
			
			<div id="instanceContainer" style="overflow:auto"></div>
		</div>
	</div>
</body>
</html>