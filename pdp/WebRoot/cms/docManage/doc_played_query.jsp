<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frameworkset.platform.cms.documentmanager.*"%>
<%@ include file="../../sysmanager/include/global1.jsp"%>
<%@ include file="../../sysmanager/base/scripts/panes.jsp"%>
<%@ taglib uri="/WEB-INF/dictionary.tld" prefix="dict"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="com.frameworkset.platform.security.*"%>
<%
	AccessControl accesscontroler = AccessControl.getInstance();
	accesscontroler.checkAccess(request, response);
	String docId = request.getParameter("docId");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../inc/css/cms.css" rel="stylesheet" type="text/css">
		<title>内容管理主框架</title>
		<script src="../inc/js/func.js"></script>
		<script language="JavaScript" src="../../sysmanager/include/pager.js" type="text/javascript"></script>
		<script language="JavaScript" src="../../sysmanager/scripts/selectTime.js" type="text/javascript"></script>
		<script type="text/javascript" src="../../public/datetime/calender.js" language="javascript"></script>
		<script type="text/javascript" src="../../public/datetime/calender_date.js" language="javascript"></script>
		<script language="javascript">
			function queryCitedDoc(queryFlag){
				queryForm.action = "<%=rootpath%>/cms/docManage/doc_played_list.jsp?docId=<%=docId%>";
				queryForm.target = "docPlayedDocListF";
				queryForm.submit();
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	 <form name="queryForm" method="post">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="5" valign="top"><img src="../images/querybox_left.gif" width="5" height="62"></td>
    <td  style="background:url(../images/querybox_bg.gif) repeat-x top">
	<table width="100%"  border="0" align="center" cellpadding="3" cellspacing="0"  style="margin-top:5px"   class="query_table">
		  <tr>
			<td  height="30" align="right">用户名：</td>
			<td><input type="text" name="userName" class="cms_text"></td>
			<td  height="30" align="right">IP地址：</td>
			<td><input type="text" name="ipAddress" class="cms_text"></td>
		  </tr>
		  <tr>
		  	<td  height="30" align="right">播放时间：</td>
		  	<td><input type="text" name="subTimeBgin"  size="18"  class="cms_text" >
					<input name="button1" type="button" onClick="showdatetime(document.all('subTimeBgin'))" value="时间" >
				-
				<input type="text" name="subTimeEnd"  size="18"  class="cms_text" >
				<input name="button2" type="button" onClick="showdatetime(document.all('subTimeEnd'))" value="时间"></td>
		  	<td colspan="3" align="right">
				<input type="button" name="query" class = "cms_button" value="查询" onClick="queryCitedDoc('part')">
				<input type="button" name="back" class = "cms_button" value="返回" onClick="window.close()">			
			</td>	
		  </tr>
	   </table></td>
    <td width="6" valign="top"><img src="../images/querybox_right.gif" width="6" height="62"></td>
  </tr>
</table>

	 </form>
</body>
</html>
