<%
/**
 * 
 * <p>Title: 菜单授权页面</p>
 *
 * <p>Description: 菜单授权页面</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: chinacreator</p>
 * @Date 2006-9-15
 * @author gao.tang
 * @version 1.0
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/treetag.tld" prefix="tree"%>
<%@ page import="com.frameworkset.platform.security.AccessControl"%>
<%
	AccessControl accesscontroler = AccessControl.getInstance();
	accesscontroler.checkManagerAccess(request,response);
	
	String resTypeId = "column";
	String currRoleId = request.getParameter("userId");
	String role_type = "user";
	String currOrgId = request.getParameter("orgId");

%>
<html>
<frameset name="dictFrame" cols="100" frameborder="no" border="0" framespacing="0" >
  	<frame src="columnSetVisible.jsp?resTypeId=<%=resTypeId%>&currRoleId=<%=currRoleId%>&role_type=<%=role_type%>&currOrgId=<%=currOrgId%>" name="globalOperList" id="globalOperList" scrolling="No" noresize="noresize" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>

