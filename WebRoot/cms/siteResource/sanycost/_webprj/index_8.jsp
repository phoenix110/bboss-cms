<%@ page contentType="text/html; charset=UTF-8"%><%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="cms"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>三壹基金会</title>

<link type="text/css" href="css/base.css" rel="stylesheet" />

<style type="text/css">

#kinMaxShow{visibility:hidden;width:100%; height:500px; overflow:hidden;}
a:link {
	color: #333333;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #333333;
}
a:hover {
	text-decoration: none;
	color: #EA0000;
}
a:active {
	text-decoration: none;
}
.STYLE1 {
	font-size: 18px;
	color: #002e61;
}
.STYLE2 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #666666;
}
.STYLE3 {font-size: 14px; color: #333333; }
a {
	font-size: 14px;
}
</style>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/slide.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/jquery.kinMaxShow-1.1.min.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
<!--
$(function(){
	
	$("#kinMaxShow").kinMaxShow();


});

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<body onload="MM_preloadImages('images/p1 - 2.png','images/p4 - 2.png','images/p2 - 2.png','images/p3 - 2.png')">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="100"><div align="center">
      <table width="998" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="396" rowspan="2" align="left" valign="middle"><img src="images/logo.png" width="396" height="85" /></td>
          <td width="602" align="right" valign="middle"><a href="#">English</a>&nbsp;|&nbsp;<a href="#">中文</a> </td>
        </tr>
        <tr>
          <td align="left" valign="middle">&nbsp;</td>
        </tr>
      </table>
    </div></td>
  </tr>
  <tr>
    <td height="35" background="images/slide-panel_03.png"><div align="center">
      <table width="998" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div id="menu">
            <ul id="nav">
              <li class="jquery_out">
                <div class="jquery_inner">
                  <div class="jquery"> <span class="text"><a href="index.html">首页</a></span></div>
                </div>
              </li>
              <li class="mainlevel" id="mainlevel_01"><a href="shiming.html" >我们</a>
                  <ul id="sub_01">
                    <li><a href="shiming.html" >使命与宗旨</a></li>
                    <li><a href="lishihui.html">理事会</a></li>
                    <li><a href="niandu.html">年度报告</a></li>
                    <li><a href="caiwu.html">财务报表</a></li>
                    <li><a href="guanli.html">管理团队</a></li>
                    <li><a href="gongzuorenyuan.html">工作人员</a></li>
					<li><a href="gongzuojihui.html">工作机会</a></li>
                  </ul>
              </li>
              <li class="mainlevel" id="mainlevel_02"><a href="jiaoyu.html" >项目</a>
                  <ul id="sub_02">
                    <li><a href="jiaoyu.html" >教育</a></li>
                    <li><a href="chuangxin.html" >创新</a></li>
                    <li><a href="jiankang.html" >健康</a></li>
                    <li><a href="jiuye.html" >就业</a></li>
					<li><a href="zhenzai.html" >赈灾</a></li>
                  </ul>
              </li>
              <li class="mainlevel" id="mainlevel_03"><a href="keti.html" >研究</a>
                  <ul id="sub_03">
                    <li><a href="keti.html" >课题</a></li>
                    <li><a href="xueshu.html" >学术顾问</a></li>
                    <li><a href="yanjiuy.html" >研究员</a></li>
                    <li><a href="chanchu.html" >产出</a></li>
                    <li><a href="shujuk.html" >数据库</a></li>
                  </ul>
              </li>
              <li class="mainlevel" id="mainlevel_04"><a href="xinwen.html" >活动</a>
                  <ul id="sub_04">
                    <li><a href="xinwen.html" >新闻</a></li>
                    <li><a href="zhuanti.html" >专题</a></li>
                  </ul>
              </li>
              <li class="mainlevel" id="mainlevel_05"><a href="lianxi.html" >联系</a>
                  <ul id="sub_05">
                    <li><a href="lianxi.html" >联系我们</a></li>
                    <li><a href="weizhi.html" >位置地图</a></li>
                  </ul>
              </li>
              <div class="clear"></div>
            </ul>
          </div></td>
        </tr>
      </table>
    </div></td>
  </tr>
  <tr>
    <td>
	<!-- 代码 开始 -->
	<div id="kinMaxShow"  >
      <div> <a href="jiaoyu - yuanm.html" target="_blank"><img src="images/demo_default_images/1.jpg"   /></a> </div>
	  <div> <a href="jiankang - shui.html" target="_blank"><img src="images/demo_default_images/2.jpg"  /></a> </div>
	  <div> <a href="jiuye - rencai.html" target="_blank"><img src="images/demo_default_images/3.jpg"   /></a> </div>
	  <div> <a href="zhenzai.html" target="_blank"><img src="images/demo_default_images/4.jpg"   /></a> </div>
	  <div> <a href="jiaoyu - yuanm.html" target="_blank"><img src="images/demo_default_images/5.jpg"   /></a> </div>
	  <div> <a href="jiaoyu - yuanm.html" target="_blank"><img src="images/demo_default_images/6.jpg"   /></a> </div>
    </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center" valign="middle"><table width="896" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td width="77" rowspan="2" align="left" valign="top"><img src="images/education.png" width="77" height="72" /></td>
        <td width="233" height="33" align="left" valign="bottom"><span class="STYLE1">教育</span></td>
        <td width="77" rowspan="2" align="left" valign="middle"><img src="images/emp.png" width="77" height="72" /></td>
        <td width="240" align="left" valign="bottom" class="STYLE1">就业</td>
        <td width="77" rowspan="2" align="left" valign="middle"><img src="images/disa.png" width="77" height="72" /></td>
        <td width="157" align="left" valign="bottom" class="STYLE1">赈灾</td>
      </tr>
      <tr>
        <td align="left" valign="top"><span class="STYLE2">Education</span></td>
        <td width="240" align="left" valign="top" class="STYLE2">Employment</td>
        <td width="157" align="left" valign="top" class="STYLE2">Disaster Relief</td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top">“梦”基金系列，帮助寒门学子实现中国梦、世界梦<br />
&nbsp;高中助学金-点燃梦想<br />
&nbsp;大学奖学金-构筑梦想<br />
&nbsp;留学奖学金-超越梦想<br /></td>
        <td colspan="2" align="left" valign="top">圆梦行动校友网络建设，人际关系分享与就业促进<br />
          面向困难就业群体的重工机械操作培训项目<br />
          大学生实习及社会实践帮扶项目<br />
          农民工就业帮扶、农村低收入家庭微金融服务项目<br /></td>
        <td colspan="2" align="left" valign="top">贷款、无偿资助与有条件现金支持在赈灾<br />
          及灾后救助中的作用比较</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="298" align="center"><table width="998" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="69"><img src="images/pro.png" width="151" height="43" /></td>
        <td width="929" background="images/pro - 2.png">&nbsp;</td>
      </tr>
      <tr>
        <td height="246" colspan="2" align="center" valign="middle"><table width="990" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2"><a href="jiaoyu - yuanm.html" target="_blank" onmouseover="MM_swapImage('Image12','','images/p1 - 2.png',1)" onmouseout="MM_swapImgRestore()"><img src="images/p1.png" name="Image12" width="219" height="149" border="0" id="Image12" /></a></td>
            <td width="38" rowspan="2">&nbsp;</td>
            <td colspan="2"><a href="chuangxin - geng.html" target="_blank" onmouseover="MM_swapImage('Image24','','images/p2 - 2.png',1)" onmouseout="MM_swapImgRestore()"><img src="images/p2.png" name="Image24" width="219" height="149" border="0" id="Image24" /></a><a href="chuangxin - geng.html" target="_blank"></a></td>
            <td width="38" rowspan="2">&nbsp;</td>
            <td colspan="2"><a href="jiankang - shui.html" target="_blank" onmouseover="MM_swapImage('Image25','','images/p3 - 2.png',1)" onmouseout="MM_swapImgRestore()"><img src="images/p3.png" name="Image25" width="219" height="149" border="0" id="Image25" /></a><a href="jiankang - shui.html" target="_blank"></a></td>
            <td width="38" rowspan="2">&nbsp;</td>
            <td colspan="2"><a href="jiuye - rencai.html" target="_blank" onmouseover="MM_swapImage('Image26','','images/p4 - 2.png',1)" onmouseout="MM_swapImgRestore()"><img src="images/p4.png" name="Image26" width="219" height="149" border="0" id="Image26" /></a><a href="jiuye - rencai.html" target="_blank" onmouseover="MM_swapImage('Image15','','images/p4 - 2.png',1)" onmouseout="MM_swapImgRestore()"></a></td>
          </tr>
          <tr>
            <td width="14" height="67" align="left" valign="middle" bgcolor="#f4f4f4" class="STYLE3">&nbsp;</td>
            <td width="205" align="left" valign="middle" bgcolor="#f4f4f4" class="STYLE3"><a href="jiaoyu - yuanm.html" target="_blank">圆梦助学项目</a></td>
            <td width="14" align="left" valign="middle" bgcolor="#f4f4f4">&nbsp;</td>
            <td width="205" align="left" valign="middle" bgcolor="#f4f4f4" class="STYLE3"><a href="chuangxin - geng.html" target="_blank">“根基金”<br />
——MIT中国创新论坛项目</a></td>
            <td width="14" align="left" valign="middle" bgcolor="#f4f4f4">&nbsp;</td>
            <td width="205" align="left" valign="middle" bgcolor="#f4f4f4" class="STYLE3"><a href="jiankang - shui.html" target="_blank">净水计划</a></td>
            <td width="14" align="left" valign="middle" bgcolor="#f4f4f4">&nbsp;</td>
            <td width="205" align="left" valign="middle" bgcolor="#f4f4f4" class="STYLE3"><a href="jiuye - rencai.html" target="_blank">人才培养方向<br />
——青年领袖计划</a></td>
          </tr>

        </table></td>
      </tr>
      <tr>      </tr>
    </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="123" align="center" background="images/di.png"><table width="990" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td colspan="2" align="left" valign="top"><strong>站内信息搜索：</strong></td>
        <td width="268" rowspan="3" align="left" valign="middle">地址：北京市昌平区北清路8号 <br />
          电话：8610-60737458 <br />
          电子邮件：mail@sanyfoundation.org</td>
        <td width="199" rowspan="3" align="left" valign="middle"><img src="images/weibo.png" width="99" height="99" /></td>
      </tr>
      <tr>
        <td width="280" align="left" valign="top"><input name="textfield" type="text" class="STYLE3" size="40" /></td>
        <td width="233" align="left" valign="top"><img src="images/serch.png" width="42" height="20" /></td>
        </tr>
      <tr>
        <td height="22" colspan="2" align="left" valign="middle"><img src="images/中文主页_45.png" width="72" height="20" /><img src="images/0a.png" width="22" height="22" /><img src="images/0b.png" width="22" height="22" /><img src="images/0c.png" width="22" height="22" /><img src="images/0d.png" width="22" height="22" /><img src="images/0e.png" width="22" height="22" /><img src="images/0f.png" width="22" height="22" /></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#EDEDED"><table width="990" border="0" cellspacing="2" cellpadding="0">
      <tr>
        <td align="left" valign="top">三壹基金会版权所有&nbsp;京ICP备&nbsp;10005672号&nbsp;2001-2014 SANY GROUP ALL RIGHTS RESERVED</td>
      </tr>
    </table></td>
  </tr>
</table>

    
	 

</body>
</html>