<%@ page contentType="text/html;charset=UTF-8"%>

<HTML>
<HEAD>
<TITLE>eWebEditor ： 标准调用示例</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='example.css'>
</HEAD>
<BODY>

<p><b>导航 ： <a href="default.jsp">示例首页</a> &gt; 标准调用示例</b></p>
<p>此例演示了eWebEditor的标准调用方法，也是最常用的调用方法。</p>
<p>本样式使用系统的默认样式(coolblue)，最佳调用宽度550px，最佳调用高度350px。</p>


<FORM method="post" name="myform" action="retrieve.jsp">
<TABLE border="0" cellpadding="2" cellspacing="1">
<TR>
	<TD>编辑内容：</TD>
	<TD>
		<INPUT type="hidden" name="content1" value="&lt;P align=center&gt;&lt;FONT color=#ff0000&gt;&lt;FONT face='Arial Black' size=7&gt;&lt;STRONG&gt;eWeb&lt;FONT color=#0000ff&gt;Editor&lt;/FONT&gt;&lt;FONT color=#000000&gt;&lt;SUP&gt;&trade;&lt;/SUP&gt;&lt;/FONT&gt;&lt;/STRONG&gt;&lt;/FONT&gt;&lt;/FONT&gt;&lt;/P&gt;&lt;P align=right&gt;&lt;FONT style='BACKGROUND-COLOR: #ffff00' color=#ff0000&gt;&lt;STRONG&gt;eWebEditor V4.8 for JSP 简体中文商业版&lt;/STRONG&gt;&lt;/FONT&gt;&lt;/P&gt;&lt;P&gt;本样式为系统默认样式（coolblue），最佳调用宽度550px，高度350px！&lt;/FONT&gt;&lt;/P&gt;&lt;P&gt;还有一些高级调用功能的例子，你可以通过导航进入示例首页查看。&lt;/P&gt;&lt;P&gt;&lt;B&gt;&lt;TABLE borderColor=#ff9900 cellSpacing=2 cellPadding=3 align=center bgColor=#ffffff border=1 heihgt=''&gt;&lt;TBODY&gt;&lt;TR&gt;&lt;TD bgColor=#00ff00&gt;&lt;STRONG&gt;&nbsp;看到这些内容，且没有错误提示，说明安装已经正确完成！&lt;/STRONG&gt;&lt;/TD&gt;&lt;/TR&gt;&lt;/TBODY&gt;&lt;/TABLE&gt;&lt;/B&gt;&lt;/P&gt;">
		<IFRAME ID="eWebEditor1" src="../ewebeditor.htm?id=content1&style=coolblue&cusdir=a/b" frameborder="0" scrolling="no" width="550" height="350"></IFRAME>
	</TD>
</TR>
<TR>
	<TD colspan=2 align=right>
	<INPUT type=submit value="提交"> 
	<INPUT type=reset value="重填"> 
	<INPUT type=button value="查看源文件" onclick="location.replace('view-source:'+location)"> 
	</TD>
</TR>
</TABLE>
</FORM>


</BODY>
</HTML>