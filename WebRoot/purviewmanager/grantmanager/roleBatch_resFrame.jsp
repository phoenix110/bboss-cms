<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.frameworkset.platform.config.model.ResourceInfo"%>
<%
/*
 * <p>Title:角色下权限授予</p>
 * <p>Description: 角色下的权限授予</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: chinacreator</p>
 * @Date 2008-4-2
 * @author liangbing.tao
 * @version 1.0
 */
%>
<%     
	response.setHeader("Cache-Control", "no-cache"); 
	response.setHeader("Pragma", "no-cache"); 
	response.setDateHeader("Expires", -1);  
	response.setDateHeader("max-age", 0); 
%>




<%@ taglib prefix="tab" uri="/WEB-INF/tabpane-taglib.tld" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="com.frameworkset.platform.resource.ResourceManager,
				 com.frameworkset.platform.sysmgrcore.manager.SecurityDatabase,
				 com.frameworkset.platform.sysmgrcore.manager.RoleManager,
				 com.frameworkset.platform.sysmgrcore.entity.Role,
				 com.frameworkset.platform.config.ConfigManager,
				 com.frameworkset.platform.security.AccessControl,
				 java.util.List,
				 java.util.ArrayList"%>
				

<%

	AccessControl accesscontroler = AccessControl.getInstance();
	accesscontroler.checkManagerAccess(request,response);
	//当前系统标识
	String curSystem = accesscontroler.getCurrentSystem();
	
	
	String[] roleIds = request.getParameter("roleIds").split(",");
	

	RoleManager roleManager = SecurityDatabase.getRoleManager();
	String roleId = "";
	String roleName = "";
	for(int i = 0; i < roleIds.length; i++){
		Role role = roleManager.getRoleById(roleIds[i]);
		if("".equals(roleName)){
			roleName = role.getRoleName();
		}else{
			roleName += "," + role.getRoleName();
		}
		if("".equals(roleId)){
			roleId = roleIds[i];
		}else{
			roleId += "," + roleIds[i];
		}
	}
	
	

	
	ResourceManager resManager = new ResourceManager();
	
	List list = resManager.getResourceInfos();
	
	//加开关，如果不允许超级管理员之外的用户进行菜单授权，当前用户又不是超级管理员，
	//则去掉菜单项，请注意，是超级管理员，重复，不是啰嗦！
	
	boolean state = ConfigManager.getInstance().getConfigBooleanValue("enablecolumngrant", true)
				 && !accesscontroler.getUserID().equals("1");
	
	if(list == null)
		list = new ArrayList();
	request.setAttribute("resTypeList",list);
	
%>

<html>
	<head>
		<tab:tabConfig/>
		<title>批量权限授予给角色【<%=roleName%>】</title>
	</head> 
	
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
	
			<tr>
				<td colspan="2">
					<tab:tabContainer id="role_resFrame" selectedTabPaneId="role-manage" skin="sany">
					<pg:list requestKey="resTypeList" needClear="false">
					
		      			<pg:equal colName="auto" value="true">
		      			<%
		      			ResourceInfo resourceInfo = (ResourceInfo) dataSet.getOrigineObject();
		      			String id = "purview-" + dataSet.getString("id");
		      			String iframeid = "iframe-" + dataSet.getString("id");
		      			String name = resourceInfo.getName(request);
		      			StringBuffer link = new StringBuffer()
		      				.append(dataSet.getString("resource")).append("?currRoleId=").append(roleId)
			      			.append("&role_type=role")
			      			.append("&isBatch=true");
		      			//String link = dataSet.getString("resource")+"?roleId="+roleId;
		      			boolean isCurSystem = resourceInfo.containSystem(curSystem); 
		      			if(isCurSystem && !(dataSet.getString("resource").equals("resColumnTree.jsp") && !accesscontroler.getUserID().equals("1")))
		      			{
	      				%>
		      			
	<!-- ------------------------------------------------------------------------------------------------------------------------------------------>
						<tab:tabPane id="<%=id%>" tabTitle="<%=name%>" lazeload="true">
							<tab:iframe id="<%=iframeid%>" src="<%=link.toString()%>" frameborder="0" scrolling="no" width="98%" height="600">
							</tab:iframe>
						</tab:tabPane>
						<%

							}
						%>
	<!-------------------------------------------------------------------------------------------------------------------------------->
					  </pg:equal>					
		  			</pg:list>	
					</tab:tabContainer>			
				</td>
			</tr>
		</table>	
	  <iframe name="exeman" width="0" height="0" style="display:none"></iframe>
	</body>
</html>


