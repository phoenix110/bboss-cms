<% 
  response.setHeader("Cache-Control", "no-cache"); 
  response.setHeader("Pragma", "no-cache"); 
  response.setDateHeader("Expires", -1);  
  response.setDateHeader("max-age", 0); 
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
/**
 * 
 * <p>Title: 输入资源</p>
 *
 * <p>Description: 输入资源</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: bboss</p>
 * @Date 2008-11-4
 * @author gao.tang
 * @version 1.0
 */
 %>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree" %>
<%@ page import="com.frameworkset.platform.security.AccessControl"%>

<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ include file="/common/jsp/csscontextmenu-lhgdialog.jsp"%>

<%
	AccessControl accessControl = AccessControl.getInstance();
	if(!accessControl.checkManagerAccess(request, response)){
		return;
	}
%>

<html>
<head>
	<title></title>
	<script type="text/javascript" language="Javascript">
		var api = frameElement.api, W = api.opener;
		
		function okadd(){
 			var ret = document.all.resid.value;
 			
 			if(ret == ""){
 				W.$.dialog.alert('<pg:message code="sany.pdp.sysmanager.resource.type.resourceid"/>');
 				return;
 			}
 			ret += ":" + ret;
 			
 			W.document.getElementById("${param.tag1}").value = ret.split(":")[0];
 			W.document.getElementById("${param.tag2}").value = ret.split(":")[1];
 			if (ret.split(":").length == 3) {
 				W.document.getElementById("getopergroup").src = "resChange.jsp?restypeId=${param.restypeId}&global="+ret[2];
 			} else {
 				W.document.getElementById("getopergroup").src = "resChange.jsp?restypeId=${param.restypeId}&global=op";
 			}
 	
 			api.close();
 			
			//window.returnValue = ret;   
			//window.close();
		}
	
	</script>
</head>

<body class="contentbodymargin"  scroll="no">
<div class="form_box">
	<table border="0" cellpadding="0" cellspacing="0" class="table4" align="left">
		<tr>
			<th><pg:message code="sany.pdp.sysmanager.resource.type.resourceid"/>：</th>
			<td><input name="resid" type="text" value="" class="w120" /></td>
		</tr>
	</table>
</div>
<div class="btnarea" >
	<a href="javascript:void(0)" class="bt_1" id="add" name="add" onclick="okadd()"><span>确定</span></a>
	<a href="javascript:void(0)" class="bt_2" id="cancel" name="cancel" onclick="closeDlg()"><span>取消</span></a>
</div>
</body>

</html>
