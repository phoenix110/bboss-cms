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
 * <p>Title: 选择机构</p>
 *
 * <p>Description: 选择机构</p>
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

<%@ page import="org.frameworkset.web.servlet.support.RequestContextUtils"%>

<%
	AccessControl accessControl = AccessControl.getInstance();
	if(!accessControl.checkManagerAccess(request, response)){
		return;
	}
	
	String roleId = request.getParameter("role_id");
	roleId = roleId == null?"":roleId;
%>

<%
	request.setAttribute("rootName", RequestContextUtils.getI18nMessage("sany.pdp.role.tree.name", request));
%>

<html>
<head>
	<title></title>
	<script type="text/javascript" language="Javascript">
		var api = frameElement.api, W = api.opener;
	
		function okadd(){	
			selectNode = document.all.item("roleid");
		
			if(selectNode==null){
 				W.$.dialog.alert('<pg:message code="sany.pdp.sysmanager.resource.type.role.select"/>'); return;
 			}    
 			var ret ;
 	
 			if(selectNode.length)
 			{
 				for(var i = 0;  i < selectNode.length; i ++)
 				{
 					if(selectNode[i].checked)
 					{
 						ret = selectNode[i].value;
 					}
 			
 				}
 			}
 			else if(selectNode)
 			{
 				if(selectNode.checked)
				{
					ret = selectNode.value;
				}
 			}
 			if(!ret)
 			{
 				W.$.dialog.alert('<pg:message code="sany.pdp.sysmanager.resource.type.role.select"/>'); return;
 			}    
 			
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
<div class="btnarea" >
	<a href="javascript:void(0)" class="bt_1" id="add" name="add" onclick="okadd()"><span><pg:message code="sany.pdp.common.operation.ok"/></span></a>
	<a href="javascript:void(0)" class="bt_2" id="cancel" name="cancel" onclick="closeDlg()"><span><pg:message code="sany.pdp.common.operation.cancel"/></span></a>
</div>
<div id="contentborder" style="width:100%;height:495;overflow:auto">

    <table >
    	<tr>
    	<td><input name="roleid" type="radio" value="globalrole:globalrole:global"/><pg:message code="sany.pdp.sysmanager.resource.role.global"/></td>
    	</tr>
        <tr><td>
         <tree:tree tree="selectrolenode"
    	           node="selectrolenode.node"
    	           imageFolder="../../images/tree_images/"
    	           collapse="true"
    			   includeRootNode="true"
    			   href=""
    			   target="abc"
    			   mode="static-dynamic">  			                           
				   <tree:radio name="roleid"/>
				   
				   
    			   <tree:treedata treetype="com.frameworkset.platform.sysmgrcore.purviewmanager.menu.ChargeRoleTypeTree"
    	                   scope="request"
    	                   rootid="0"  
    	                   rootName="${rootName}"
    	                   expandLevel="1"
    	                   showRootHref="false"
    	                   needObserver="false"
    	                   />

    	</tree:tree>
         </td></tr>
    </table>
</div>
<script type="text/javascript" language="javascript">
		var roleId = "<%=roleId%>";
		
		if(roleId != ""){
			var selectNode = document.all.item("roleid");
			if(selectNode.length)
 			{
 				for(var i = 0;  i < selectNode.length; i ++)
 				{
 					var val = selectNode[i].value.split(":")[0]; 
 					if(val == roleId)
 					{
 						selectNode[i].checked = true;
 					}
 			
 				}
 			}
		}
</script>
</body>

</html>
