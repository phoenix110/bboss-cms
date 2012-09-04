<%@ include file="/sysmanager/include/global1.jsp"%>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ include file="/common/jsp/csscontextmenu-lhgdialog.jsp"%>

<%
	response.setHeader("Cache-Control", "no-cache"); 
	response.setHeader("Pragma", "no-cache"); 
	response.setDateHeader("Expires", -1);  
	response.setDateHeader("max-age", 0);
%>

<html>
<head>
<title>资源管理</title>
<script language="javascript">
function action11(){
	getPropertiesContent().location.href="<%=rootpath%>/sysmanager/orgmanager/organsearch.jsp";
}
</script>
<link rel="stylesheet" type="text/css" href="<%=rootpath%>/sysmanager/css/treeview.css">
</head>
<body scroll="auto">
<table >
	<tr><td>
	<tree:tree tree="res_tree" 
			node="res_tree.node" imageFolder="/sysmanager/images/tree_images/" 
			collapse="true" includeRootNode="true" 
			href="res_list.jsp" target="res_list" mode="static-dynamic">
							

			<tree:treedata treetype="com.frameworkset.platform.menu.ResourceTypeCMSTree" scope="request" rootid="0" rootName="资源分类" rootNameCode="sany.pdp.resource.tree.name" expandLevel="1" 
			showRootHref="false" 
			needObserver="false" 
			
			/>
		</tree:tree>
	 </td></tr>
</table>
</body>
</html>

