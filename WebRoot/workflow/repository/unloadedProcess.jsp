<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<%--
描述：台账列表信息设置
作者：许石玉
版本：1.0
日期：2012-02-08
 --%>	
<%@ include file="/common/jsp/css.jsp"%>
<body>	<form id="processform" method="post">
		<div class="mcontent">
		<div class="title_box">
				<div class="rightbtn">
				
				<a href="#" class="bt_1" id="loadButton"><span><pg:message code="sany.pdp.common.load"/></span></a>
				
				</div>
				
				<strong><pg:message code="sany.pdp.workflow.manage"/></strong>
				<img id="wait" src="../../common/images/wait.gif" />				
			</div>
			<div id="custombackContainer" >

  
	<!-- 加入 class="tableOutline" 可控制表格宽度，滚动条展示 -->

		<div id="changeColor">
		 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="stable" id="tb">
	        <pg:header>
	           
				
	       		
	       		<th><pg:message code="sany.pdp.common.name"/></th>
	       		<th><pg:message code="sany.pdp.workflow.process.key"/></th>
	       		<th><pg:message code="sany.pdp.workflow.deploy.version"/></th>
	       		<th><pg:message code="sany.pdp.workflow.deploy.name"/></th>
	       		<th><pg:message code="sany.pdp.workflow.deploy.time"/></th>
	       		<th><pg:message code="sany.pdp.workflow.picture.resource.path"/></th>
	       		<th><pg:message code="sany.pdp.workflow.picture.resource.name"/></th>
	       		<th><pg:message code="sany.pdp.workflow.business.select"/></th>
	       		<th>应用</th>
	       		
	       	</pg:header>	
	
			<pg:empty actual="${unloadProcesses}">
				<tr><td colspan="100" align="center">
				<img src="${pageContext.request.contextPath}<pg:message code='sany.pdp.common.list.nodata.path'/>"/></td></tr>
			</pg:empty> 
			<pg:notempty actual="${unloadProcesses}" >
			
			      <pg:list actual="${unloadProcesses}">
			   		<tr>
			                    
			                <td><pg:cell colName="NAME_" maxlength="8" replace="..."/><input id="id" type="hidden" name="id" value="<pg:cell colName="DEPLOYMENT_ID_" />"/>
			                    <input id="procdef_id" type="hidden" name="procdef_id" value="<pg:cell colName="ID_" />"/>
			                    <input id="processKey" type="hidden" name="processKey" value="<pg:cell colName="KEY_" />"/>
			                    <input id="processName" type="hidden" name="processName" value="<pg:cell colName="NAME_" />"/></td>
			        		<td><pg:cell colName="KEY_" /></td>       
			                <td><span class="toolTip" title="<pg:cell colName="VERSION_"/>"><pg:cell colName="VERSION_" maxlength="8" replace="..."/></span></td>  
			           		<td><pg:cell colName="DEPLOYMENT_NAME_" /></td>   
			           		<td><pg:cell colName="DEPLOYMENT_TIME_"  dateformat="yyyy-MM-dd hh:mm:ss"/></td>
			           		<td><pg:cell colName="RESOURCE_NAME_"/></td>	   
			           		<td><pg:cell colName="DGRM_RESOURCE_NAME_"/></td>
			           		<td><select class="easyui-combotree" id='businessType' name='businessType'
												style="width: 120px;"></td>
							<td><select id='wf_app_id' name='wf_app_id'  
												style="width: 120px;">
								</select>
							</td>
			        </tr>
				 </pg:list>
				 </pg:notempty >
				
			    </table>
			    </div>  
			</div>
		</div>
	 </form>
</body>
<script type="text/javascript">
var api = frameElement.api, W = api.opener;
$(document).ready(function() {
	
 	 $("#wait").hide();
 	$("[id=businessType]").each(
 			function(){ 
		 		$(this).combotree({
					url:"../businesstype/showComboxBusinessTree.page"
				}
		 		);
 			}
 		); 
 	
 	 $('#loadButton').click(function() {  
  	   loadProcess();
       });
 	 
 	getTreeDate();
 	initWfAppSelect();
 	
});

var treeData = null;

function getTreeDate(){
	$.ajax({
 	 	type: "POST",
		url : "getWfAppData.page",
		dataType : 'json',
		async:false,
		beforeSend : function(XMLHttpRequest){
			 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			},
		success : function(data){
			if (data) {
				treeData = data;
			} else {
				W.$.dialog.alert('查询应用菜单异常');
			}
		}	
	 });
}

function initWfAppSelect(){
	if(treeData){
		var treeModuleHtml = "<option value=''></option>";
		var seq = 1;
		for(var i=0; i<treeData.length; i++){
			treeModuleHtml += "<option value='"+treeData[i].id+"'>"+treeData[i].system_id+" "+treeData[i].system_name+"</option>";
		}
		$("[id=wf_app_id]").each(
	 			function(){ 
			 		$(this).append(treeModuleHtml);
	 			}
	 		);
	}
}

function loadProcess() {
	
	if("${empty unloadProcesses}" == "true") {
		W.$.dialog.alert("没有需要装载的数据");
		return ;
	}
	
	
	if($('#businessType').combotree('getValue')==''){
		W.$.dialog.alert("请选择所属业务类型");
	  	return;
	  }
	
	$.ajax({
 	 	type: "POST",
		url : "loadProcess.page",
		data :formToJson("#processform"),
		dataType : 'json',
		async:false,
		beforeSend: function(XMLHttpRequest){
			 	XMLHttpRequest.setRequestHeader("RequestType", "ajax");
			 	blockUI('正在装载流程信息...');
			},
		success : function(data){
			unblockUI();
			if (data.success) {
				W.$.dialog.alert(data.message);
				W.modifyQueryData();
				api.close();
			} else {
				W.$.dialog.alert(data.message);
			}
		}	
	 });
}

</script>
