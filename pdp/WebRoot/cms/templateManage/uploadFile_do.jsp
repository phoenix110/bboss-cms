<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="com.frameworkset.platform.cms.util.*"%>
<%@ page import="com.frameworkset.platform.security.AccessControl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
try{
	AccessControl control = AccessControl.getAccessControl();

	//建立一个临时目录
	File temp = new File(application.getRealPath("/"),System.currentTimeMillis()+""+Math.random());
	temp.mkdirs();
 	 
	
	String coverFlag = null;
	String uri = null;
	//处理文件上传
	FileItemFactory fileitemfactory = new DefaultFileItemFactory(2048000,temp);
	FileUpload fu = new FileUpload(fileitemfactory);
	fu.setHeaderEncoding("UTF-8");
	List fields = fu.parseRequest(request);
	int n=0;
	String pathContext = null;
	FileItem file = null;
	try
	{
		
		for(int i=0;fields!=null&&i<fields.size();i++){
			FileItem field = (FileItem)fields.get(i);
			if(field.isFormField())
			{
				if("uri".equals(field.getFieldName())){
					uri = field.getString();
				}
		
				else if("coverFlag".equals(field.getFieldName())){
					coverFlag = field.getString();
				}
				
				else if("pathContext".equals(field.getFieldName())){
					pathContext = field.getString();
					if(pathContext==null || pathContext.trim().length()==0){
						%>
								<script type="text/javascript">
									alert("没有提供资源的上下文路径,无法管理资源.");
									top.close();
								</script>
						<%		
								return;
							}
				}
			}
			else
			{
				
				
				file = field;
				
					
				
				
			}
			
			
			
		
		}
		//建个文件用来获取名字
		if(file != null)
		{
			File parentFolder = null;
			if(uri!=null && uri.trim().length()!=0){
				parentFolder = new File(pathContext,uri);
			}else{
				parentFolder = new File(pathContext);
			}
			 
			File tempFile = new File(file.getName());
		File f = new File(parentFolder.getAbsoluteFile(),tempFile.getName());
			if(coverFlag==null && f.exists()){				
				out.println("<script language=\"javascript\">");
				out.println("alert('文件已经存在,请在上传之前修改好文件名!');");
				out.println("</script>");
				return;	
			}
			f.getParentFile().mkdirs();
			f.createNewFile();
			file.write(f);
			
		}
		FileUtil.deleteSubfiles(temp.getAbsolutePath());
		FileUtil.deleteFile(temp.getAbsolutePath());
	}
	finally
	{
		if(file != null)
			file.delete();
	}
	
}catch(Exception e){
	out.println("<script language=\"javascript\">");
	out.println("alert('上传的文件发生异常,异常为"+e.getMessage()+"');");
	out.println("top.close();");
	out.println("</script>");			
	e.printStackTrace();
}
%>
<script type="text/javascript">
	alert("上传文件成功.");
	parent.win.reloadView();
	top.close();
</script>
</body>
</html>
