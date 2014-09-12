<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/jsp/importtaglib.jsp"%>

<ul>
	<div class="module ta pd_10 mr_10">
		<pg:empty requestKey="task" >
  			<img src="${pageContext.request.contextPath}/workflowBusiness/business/getProccessPic.page?processKey=${processKey}" />
  		</pg:empty>
  		<pg:notempty requestKey="task" >
  			<pg:beaninfo requestKey="task">
  				<img src="${pageContext.request.contextPath}/workflowBusiness/business/getProccessActivePic.page?processInstId=<pg:cell colName="instanceId" />" />
  			</pg:beaninfo>
  		</pg:notempty>
  	</div>
  
   <pg:notempty actual="${actList}"> 
		<table id="protable"  border="0" cellpadding="0" cellspacing="0" class="tablePro" width="100%" style="margin-top:10px;">
		
			<input id="processKey" name="processKey" type="hidden" value="${processKey }"/>
			
			<tr>
				<th width="10%">节点名</th>
				<th width="20%">处理人员</th>
				<th width="10%">处理方式</th>
				<th width="30%">节点描述</th>
				<th width="10%">处理工时(小时)</th>
				<th width="10%">节点状态</th>
			</tr>
				
			<input type="hidden" id="tempnum" name="tempnum" value=""/>
			
			<input type="hidden" id="actListSize" name="actListSize" value="<pg:size requestKey="actList"/>"/>
			
        	<pg:list requestKey="actList">
	            <tr id="protr<pg:rowid increament="1" />" >
		            <input type="hidden" name="actId" id="actId<pg:rowid increament="1" />" nvl="<pg:cell colName="isEdit" />"
		               value="<pg:cell colName="actId" />"  con="<pg:rowid increament="1" />" />
		             <input type="hidden" name="actName" id="actName<pg:rowid increament="1" />" value="<pg:cell colName="actName" />"/>
		             <input type="hidden" name="candidateName" id="candidateName<pg:rowid increament="1" />" value="<pg:cell colName="candidateName" />" /> 
		             <input type="hidden" name="realName" id="realName<pg:rowid increament="1" />" value="<pg:cell colName="realName" />" />
		             <input type="hidden" name="isValid" id="isValid<pg:rowid increament="1" />" value="<pg:cell colName="isValid" />"/>
		             <input type="hidden" name="isEdit" id="isEdit<pg:rowid increament="1" />" value="<pg:cell colName="isEdit" />"/>
		             <input type="hidden" name="isEditAfter" id="isEditAfter<pg:rowid increament="1" />" value="<pg:cell colName="isEditAfter" />"/>
		             <input type="hidden" name="isAuto" id="isAuto<pg:rowid increament="1" />" value="<pg:cell colName="isAuto" />"/>
		             <input type="hidden" name="isAutoAfter" id="isAutoAfter<pg:rowid increament="1" />" value="<pg:cell colName="isAutoAfter" />"/>
		             <input type="hidden" name="isRecall" id="isRecall<pg:rowid increament="1" />" value="<pg:cell colName="isRecall" />"/>
		             <input type="hidden" name="isCancel" id="isCancel<pg:rowid increament="1" />" value="<pg:cell colName="isCancel" />"/>
		             <input type="hidden" name="isDiscard" id="isDiscard<pg:rowid increament="1" />" value="<pg:cell colName="isDiscard" />"/>
		             <input type="hidden" name="isCopy" id="isCopy<pg:rowid increament="1" />" value="<pg:cell colName="isCopy" />"/>
		             <input type="hidden" name="nodeDescribe" id="nodeDescribe<pg:rowid increament="1" />" value="<pg:cell colName="nodeDescribe" />"/>
		             <input type="hidden" name="nodeWorkTime" id="nodeWorkTime<pg:rowid increament="1" />" value="<pg:cell colName="nodeWorkTime" />"/>
		             <input type="hidden" name="taskUrl" id="taskUrl<pg:rowid increament="1" />" value="<pg:cell colName="taskUrl" />"/>
		             <input type="hidden" name="bussinessControlClass" id="bussinessControlClass<pg:rowid increament="1" />" value="<pg:cell colName="bussinessControlClass" />"/>
		             <input type="hidden" name="approveType" id="approveType<pg:rowid increament="1" />" value="<pg:cell colName="approveType" />"/>
			      <td>
				      <pg:cell colName="actName" /> 
			      </td>
			      <td>
				      <span id="realnames<pg:rowid increament="1" />">
					     <pg:cell colName="realName" />
				      </span> 
					  <a href="javascript:setCandidate(<pg:rowid increament="1" />)" name="chooseUsersa" style="display: none;"><font color="#0a70ed">[选择]</font></a>
				      <span class="requiredstar" style="color: red;display: none;">*</span>
			      </td>
			      <td>审批：
		       		<pg:in colName="approveType" scope="0,10" >串行</pg:in>
		       		<pg:equal colName="approveType" value="20" >并行</pg:equal>
		       		<pg:equal colName="approveType" value="40" >抄送</pg:equal>
			      </td>
			      <td>
				      <pg:empty colName="nodeDescribe">&nbsp;</pg:empty>
				      <pg:notempty colName="nodeDescribe"><pg:cell colName="nodeDescribe" /></pg:notempty>
			      </td>
			      <td>
			      	  <pg:empty colName="nodeWorkTime">&nbsp;</pg:empty>
				      <pg:notempty colName="nodeWorkTime"><pg:cell colName="nodeWorkTime" /></pg:notempty>
				  </td>
			      <td id="zx<pg:cell colName="actId" />">未执行</td>
		       </tr>
    		</pg:list>
	    </table>
    </pg:notempty>
    
    <table id="protable"  border="0" cellpadding="0" cellspacing="0" width="80%" class="table2">
	<pg:list requestKey="taskHistorList">
		<tr>
			<td><pg:cell colName="END_TIME_"  dateformat="yyyy-MM-dd HH:mm:ss"/></td>
			<td><pg:cell colName="NAME_" /></td>
			<td><pg:cell colName="ASSIGNEE_NAME" /></td>
			<td><pg:cell colName="DELETE_REASON_" /></td>
		</tr>
	</pg:list>
	</table>

	<br>
</ul>