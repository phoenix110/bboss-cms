<%@ page contentType="text/html;charset=UTF-8"%>

<HTML>
<HEAD>
<TITLE>eWebEditor ： 客户端API示例</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>导航 ： <a href="default.jsp">示例首页</a> &gt; 客户端API示例</b></p>
<p>您可以用eWebEditor提供的客户端API，对编辑器执行高级操作。更多API，请参见开发手册。</p>



<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>编辑内容：</TD>
	<TD>
		<INPUT type="hidden" name="content1" value="&lt;p&gt;eWebEditor - 在线HTML编辑器，HTML在线编辑好帮手&lt;/p&gt;">
		<IFRAME ID="eWebEditor1" src="../ewebeditor.htm?id=content1&style=coolblue" frameborder="0" scrolling="no" width="550" height="350"></IFRAME>
	</TD>
</TR>
<TR>
	<TD colspan=2 align=right>
	<INPUT type=submit value="提交"> 
	<INPUT type=reset value="重填"> 
	<INPUT type=button value="查看源文件" onclick="location.replace('view-source:'+location)"> 
	</TD>
</TR>
<TR>
	<TD>HTML代码：</TD>
	<TD><TEXTAREA cols=50 rows=5 id=myTextArea style="width:550px">点击“取值”按钮，看一下效果！</TEXTAREA></TD>
</TR>
<TR>
	<TD colspan=2 align=right>
	<INPUT type=button value="取值(HTML)" onclick="myTextArea.value=eWebEditor1.getHTML()"> 
	<INPUT type=button value="取值(纯文本)" onclick="myTextArea.value=eWebEditor1.getText()"> 
	<INPUT type=button value="赋值" onclick="eWebEditor1.setHTML('<b>Hello My World!</b>')">
	<INPUT type=button value="当前位置插入" onclick="eWebEditor1.insertHTML('This is insertHTML function!')">
	<INPUT type=button value="尾部追加" onclick="eWebEditor1.appendHTML('This is appendHTML function!')">
	<br>
	<INPUT type=button value="代码状态" onclick="eWebEditor1.setMode('CODE')">
	<INPUT type=button value="设计状态" onclick="eWebEditor1.setMode('EDIT')">
	<INPUT type=button value="文本状态" onclick="eWebEditor1.setMode('TEXT')">
	<INPUT type=button value="预览状态" onclick="eWebEditor1.setMode('VIEW')">
	<br>
	<INPUT type=button value="英文字数" onclick="alert(eWebEditor1.getCount(0))">
	<INPUT type=button value="中文字数" onclick="alert(eWebEditor1.getCount(1))">
	<INPUT type=button value="中英文字数(中文加1)" onclick="alert(eWebEditor1.getCount(2))">
	<INPUT type=button value="中英文字数(中文加2)" onclick="alert(eWebEditor1.getCount(3))">
	</TD>
</TR>
</TABLE>
</FORM>


</BODY>
</HTML>