<%@page contentType="text/html;charset=UTF-8"%>
<%
	String userId = request.getParameter("userId");
%>
<style id="popupmanager">
/*******************************************************
popupmanger CSS
author: dotey()
copy write: cnforums.net
*******************************************************/
.popup{
	font-size:9pt;
	width: 329px;
	height: 74px;
	border: 1px solid #0A246A;
	filter: Alpha(opacity=80);
}
.popup td{
	font-size:9pt;
}
.popupCaption{
	background-image: url(images/popup_bg_caption.gif);
	height: 7px;
}
.popupCaptionText{
	overflow:hidden;
	width: 260;
	height: 100%;
	padding-right: 4px;
	padding-top: 4px;
	text-decoration: underline;
	color: blue;
}

.popupBody{
	background-image: url(images/popup_bg_body.gif);
}
.popupBodyText{
	overflow:hidden;
	width: 100%;
	height: 100%;
	word-break: break-all;
	word-wrap :break-word;
	line-height: 1.1em;
	padding-top: 1px;
	text-decoration: underline;
	color: blue;
}

.popupButton{

}
.popupButtonHover{
	background-color: #0A246A;
}
.popupButtonHover TD{
	background-color: #B6BDD2;
}

.popupMenu{
	width: 177px;
	border: 1px solid #666666;
	background-color: #F9F8F7;
	padding: 1px;
}
.popupMenuTable{
	background-image: url(images/popup_bg_menu.gif);
	background-repeat: repeat-y;
}
.popupMenuTable TD{
	font-size: 9pt;
	cursor: default;
}
.popupMenuRow{
	height: 22px;
	padding: 1px;
}
.popupMenuRowHover{
	height: 22px;
	border: 1px solid #0A246A;
	background-color: #B6BDD2;
}
.popupMenuSep{
	background-color: #A6A6A6;
	height:1px;
	width: expression(parentElement.offsetWidth-27);
	position: relative;
	left: 28;
}
</style>

<script language="JavaScript" type="text/javascript">


/***************************************************
//
// 说明：Popup管理
// 日期：2005-1-5
// 作者：dotey(宝玉)
// 版权：CnForums.Net，请保留此版权信息
***************************************************/
// 队列
function Queue()
{
    var items = new Array();

    var first = 0;
    var count = 0;

	// 队列大小
    this.Count = function()
    {
        return count;
    } ;

	// 取队列头/尾
    this.Peek = function(last)
    {
        var result = null;

        if (count > 0)
        {
            if (null != last && true == last)
                result = items[first + (count - 1)];
            else
                result = items[first];
        }

        return result;
    };

	// 入列
    this.Enqueue = function(x)
    {
        items[first + count] = x;

        count++;
        return x;
    };

	// 出列
	//
    this.Dequeue = function()
    {
        var result = null;

        if (count > 0)
        {
            result = items[first];

            delete items[first];
            first++;
            count--;
        }

        return result;
    };
}


var newlineRegExp = new RegExp("(\r\n|\n|\r)", "g");

function NewlineReplace(str)
{
    result = "";

    if (str != null)
        result = str.replace(newlineRegExp, "<br>");

    return result;
}

var entityList = new Array();
entityList["<"] = "&lt;";
entityList["\uff1c"] = "&lt;";
entityList[">"] = "&gt;";
entityList["\uff1e"] = "&gt;";
entityList["&"] = "&amp;";
entityList["\uff06"] = "&amp;";
entityList["\""] = "&quot;";
entityList["\uff02"] = "&quot;";

function EntityReplace(str)
{
    var result = "";

    if (str != null)
    {
        var len = str.length;

        var i = 0;

        while (i < len)
        {
            var j = i;

            var e = entityList[str.charAt(j)];

            while (j < len && null == e)
            {
                j++;

                e = entityList[str.charAt(j)];
            }

            result += str.substr(i, j - i);

            if (e != null)
            {
                result += e;

                j++;
            }

            i = j;
        }
    }

    return result;
}

function GetPopupCssText()
{
	var styles = document.styleSheets;
	var csstext = "";
	for(var i=0; i<styles.length; i++)
	{
		if (styles[i].id == "popupmanager")
			csstext += styles[i].cssText;
	}
	return csstext;
}

function PopupWin(winID)
{
	this.Win = document.getElementById(winID);
	this.Menu = document.getElementById(winID + "_Menu");
	this.Icon = document.getElementById(winID + "_Icon");
	this.MenuButton = document.getElementById(winID + "_MenuButton");
	this.CloseButton = document.getElementById(winID + "_CloseButton");
	this.Caption = document.getElementById(winID + "_Caption");
	this.CaptionText = document.getElementById(winID + "_CaptionText");
	this.Body = document.getElementById(winID + "_Body");
	this.BodyText = document.getElementById(winID + "_BodyText");
}

function Popup(winID, message, icon, title, func)
{
	this.PostID;
	this.URL;
	this.Type = "Mail";	// Mail,Thread,Post,Message
	this.win = new PopupWin(winID);
	this.popup = null;
	this.popupMenu = null;
	this.PopupManager = null;
	this.showTime = null;
	this.func = func;
	this.isMouseOver = false;
	this.CreateBody = Popup_CreateBody;
	this.Close = Popup_Close;
	this.Hide = Popup_Hide;
	this.Show = Popup_Show;
	this.ShowTime = Popup_ShowTime;
	this.aspxl = this.CreateBody(message, icon, title);
}

function Popup_Close()
{
	if (this.popup != null)
		this.popup.document.onmouseover = null;
	else
		this.win.Win.onmouseover = null;

	this.isMouseOver = false;
	this.ShowTime = function()
	{
		return 7;
	}

	this.Hide();
}

function Popup_Hide()
{
	if (this.popup != null && this.popup.isOpen)
	{
		this.popup.hide();
	}

	this.popup = null;
}

function Popup_ShowTime()
{
	var result = null;

	if (this.showTime != null)
	{
		var now = new Date();

		result = (now - this.showTime)/1000;
	}

	return result;
}

// 
function OnClickClose_Popup()
{
	var p = this.getAttribute("popup");
	p.Close();
}

function OnClickMenu_Popup()
{
	var p = this.getAttribute("popup");
	if (p.popupMenu == null)
	{
		p.popupMenu = p.popup.document.parentWindow.createPopup(); 
		var d = p.popupMenu.document;
		var s = d.createStyleSheet();
		s.cssText = GetPopupCssText();
		var b = d.body;
		b.rightmargin = 0;
		b.leftmargin = 0;
		b.topmargin = 0;
		b.bottommargin = 0;
		b.innerHTML = p.win.Menu.outerHTML;
		b.style.cursor = "default";
		b.oncontextmenu = OnContextMenu_Popup;
		b.onmouseover = OnMouseOver_PopupMenu;
		b.onmouseout = OnMouseOut_PopupMenu;
		b.setAttribute("popup", p);
		var menuDisable = d.getElementById(p.win.Win.id + "_Menu_Disable");
		menuDisable.onclick = OnClickDisable_PopupMenu;
		menuDisable.setAttribute("popup", p);
		//var menuSetting = d.getElementById(p.win.Win.id + "_Menu_Setting");
		//menuSetting.onclick = OnClickSetting_PopupMenu;
		//menuSetting.setAttribute("popup", p);
	}
	var toastWidth = p.win.Menu.offsetWidth;
	var toastHeight = p.win.Menu.offsetHeight;
	var toastVerticalMargin = 20;
	var toastHorizontalMargin = 0;
	p.popupMenu.show(toastHorizontalMargin, -toastVerticalMargin-toastHeight, toastWidth, toastHeight, p.win.MenuButton);
}

function OnClick_Popup()
{
	var p = this.getAttribute("popup");

	if (p != null && p.func != null)
	{
		p.func(p);
	}
}

function OnClickDisable_PopupMenu()
{
	var p = this.getAttribute("popup");

	if (p != null)
	{
		p.PopupManager.Disabled = true;
		p.Close();
	}
}

function OnClickSetting_PopupMenu()
{
	var url = this.getAttribute("URL");
	var p = this.getAttribute("popup");

	if (url != null)
	{
		window.open(url);
		if (p != null)
		{
			p.Close();
		}
	}
}

function OnContextMenu_Popup()
{
	var p = this.getAttribute("popup");
	p.Close();
}

function OnMouseOver_Popup()
{
	var p = this.getAttribute("popup");
	p.isMouseOver = true;
}

function OnMouseOut_Popup()
{
	var p = this.getAttribute("popup");
	p.isMouseOver = false;
}

function OnMouseOver_PopupMenu()
{
	var p = this.getAttribute("popup");
	p.isMouseOver = true;
}

function OnMouseOut_PopupMenu()
{
	var p = this.getAttribute("popup");
	p.isMouseOver = false;
}

function Popup_Show()
{
	this.showTime = new Date();
	this.popup = window.createPopup();
	var d = this.popup.document;

	//	d.createStyleSheet("CSS/style.css");
	var s=d.createStyleSheet();
	s.cssText = GetPopupCssText();
	var b = d.body;
	b.rightmargin = 0;
	b.leftmargin = 0;
	b.topmargin = 0;
	b.bottommargin = 0;
	b.innerHTML = this.aspxl;
	b.style.cursor = "default";
	b.oncontextmenu = OnContextMenu_Popup;
	b.onmouseover = OnMouseOver_Popup;
	b.onmouseout = OnMouseOut_Popup;
	b.setAttribute("popup", this);
	var closeButton = d.getElementById(this.win.Win.id + "_CloseButton");
	closeButton.onclick = OnClickClose_Popup;
	closeButton.setAttribute("popup", this);

	var menuButton = d.getElementById(this.win.Win.id + "_MenuButton");
	menuButton.onclick = OnClickMenu_Popup;
	menuButton.setAttribute("popup", this);

	var popupMessage = d.getElementById(this.win.Win.id + "_BodyText");
	popupMessage.style.cursor = "hand";
	popupMessage.onclick = OnClick_Popup;
	popupMessage.setAttribute("popup", this);
	var toastWidth = this.win.Win.offsetWidth;
	var toastHeight = this.win.Win.offsetHeight;
	var toastVerticalMargin = 28;
	var toastHorizontalMargin = 16;
	var screenWidth = window.screen.width;
	var screenHeight = window.screen.height;
	this.popup.show(screenWidth - toastWidth - toastHorizontalMargin, screenHeight - toastHeight - toastVerticalMargin,
					toastWidth,                                       toastHeight);

}

function Popup_CreateBody(msg, icon, title)
{
	if (icon != null && icon != "")
		this.win.Icon.src = icon;
	this.win.BodyText.innerHTML = NewlineReplace(EntityReplace(msg));
	this.win.CaptionText.innerHTML = title;
	var win = this.win.Win.cloneNode(true);
	win.style.display = "";
	return win.outerHTML;
}


function PopupManager()
{
	var queue = new Queue();
	this.Disabled = false;

	var canShow = (window.createPopup != null);
	this.Heartbeat = function()
	{
	
		if (queue.Count() > 0)
		{
			var p = queue.Peek();

			var delta = p.ShowTime();

			if (delta == null)
			{
				if (!this.Disabled)
					p.Show();
			}
			else if ((p.popup == null) || (!p.popup.isOpen) || (!p.isMouseOver && delta >= 7))
			{
				p.Hide();

				queue.Dequeue();
			}
		}
	}

	this.AddPopup = function(winid, message, icon, title, func)
	{
		var result = null;
		do
		{
			if (canShow)
			{
				result = new Popup(winid, message, icon, title, func);
				result.PopupManager = this;

				queue.Enqueue(result);
				this.Heartbeat();
			}
		}
		while (false);

		return result;
	}
}
	
		function remind_showmessage()
       {        		
       		popupManager.Heartbeat();
       		if(!popupManager.Disabled)
       		{
   				parent.popup_iframe1.location.href = '<%=request.getContextPath()%>/sysmanager/frame_daySchedularList.jsp?userId=<%=userId%>';
   			}
   			
       		
       }
       
       function addPopupbyIframe(contents)
		{
			if(contents == "")
			{
				return;
			}
			else
			{
				
				var idx = contents.indexOf("$$");
				if(idx == -1)
				{
					
						var infos = contents.split("@@");
						var p = null;
						try
						{
							
							p = popupManager.AddPopup("popupWin", infos[0], "images/popup_icon_Post.gif", "系统提示", func);
							
							
							p.URL = infos[1];
						}
						catch(e)
						{
							//alert("aa0:" + e.description);
						}
				}
				else
				{
					var mgs = contents.split("$$");
					var content = "";
					for(var i = 0; i < mgs.length; i ++)
					{
						var infos = mgs[i].split("@@");
						
						var p = popupManager.AddPopup("popupWin", infos[0], "images/popup_icon_Post.gif", "系统提示", func);
						if(infos[1] != "null")
							p.URL = infos[1];
					}
					
				}
			}
		}
</script>
<table id="popupWin" class="popup" cellspacing="0" cellpadding="0" border="0" onselectstart="return false;" ondragstart="return false;" style="display:;">
	<tr class="popupCaption" id="popupWin_Caption">
		<td align="center"><img src="images/popup_caption.gif" border="0" alt="系统提示" /></td>
	</tr>
	<tr class="popupBody" id="popupWin_Body">
		<td valign="top">
			<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
				<tr>
					<td align="center" valign="top" width="46" style="padding-top: 2px;width:46px;" nowrap>
						<img src="images/popup_icon_mail.gif" border="0" alt="" id="popupWin_Icon"/>
					</td>
					<td>
						<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
							<tr height="18">
								<!--biaoping.yin <td valign="bottom"><div id="popupWin_CaptionText" class="popupCaptionText">title1</div></td>-->
								<td valign="bottom"><div id="popupWin_CaptionText" class="popupCaptionText">title1</div></td>
								<td align="right" width="18">
									<table cellspacing="1" cellpadding="0" border="0" width="18" height="18" class="popupButton" onmouseover="this.className='popupButtonHover';" onmouseout="this.className='popupButton';" onmousedown="var img=this.rows[0].cells[0].children[0];img.src=img.src.replace('_black','_white');" onmouseup="var img=this.rows[0].cells[0].children[0];img.src=img.src.replace('_white','_black');" id="popupWin_MenuButton">
										<tr>
											<td align="center"><img src="images/popup_icon_arrow_black.gif" border="0" alt="配置" /></td>
										</tr>
									</table>
								</td>
								<td align="right" width="18">
									<table cellspacing="1" cellpadding="0" border="0" width="18" height="18" class="popupButton" onmouseover="this.className='popupButtonHover';" onmouseout="this.className='popupButton';" id="popupWin_CloseButton">
										<tr>
											<td align="center"><img src="images/popup_icon_close.gif" border="0" alt="" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2" valign="top">
								
								<div id="popupWin_BodyText" class="popupBodyText">
								
								</div>
								</td>
							</tr>
							<tr height="8">
								<td/>
								<td/>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<div class="popupMenu" id="popupWin_Menu">
	<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%" class="popupMenuTable">
		<tr height="22">
			<td class="popupMenuRow" onmouseover="this.className='popupMenuRowHover';" onmouseout="this.className='popupMenuRow';" id="popupWin_Menu_Disable">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
					<tr>
						<td width="28">&nbsp;</td>
						<td><span>Disable Popup</span></td>
					</tr>
				</table>
			</td>
		</tr>
		<!--<tr height="3">
			<td>
				<div class="popupMenuSep"><img height="1px"></div>
			</td>
		</tr>-->
		<!-- <tr height="22">
			<td class="popupMenuRow" onmouseover="this.className='popupMenuRowHover';" onmouseout="this.className='popupMenuRow';" id="popupWin_Menu_Setting" URL="#">
				<table cellspacing="0" cellpadding="0" border="0" width="100%" height="100%">
					<tr>
						<td width="28">&nbsp;</td>
						<td><span>Popup Setting</span></td>
					</tr>
				</table>
			</td>
		</tr>-->
	</table>
</div>

<script language="javascript">
var popupManager = new PopupManager();
window.setInterval("remind_showmessage()", 8000);	

function func(popup)
{
	if(popup.URL)
		window.open(popup.URL,'msgs_info','menubar=no,status=no,toolbar=no,width=800,height=400');
}


</script>

