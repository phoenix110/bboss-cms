<%
String sUsername, sPassword, aStyle, aToolbar;

sUsername = "admin";
sPassword = "admin";

aStyle = "gray|||gray|||office|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "full|||blue|||coolblue|||siteResource/|||550|||400|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||酷蓝样式，全部功能按钮|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "light|||gray|||light|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格按钮+淡色，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "blue|||gray|||blue|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格按钮+蓝色，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "green|||gray|||green|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格按钮+绿色，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "red|||gray|||red|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格按钮+红色，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "yellow|||gray|||yellow|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格按钮+黄色，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "3d|||gray|||office3d|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||Office标准风格3D凹凸按钮，部分常用按钮，标准适合界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "coolblue|||blue|||coolblue|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|png|wmz|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|wmz|||50000|||10000|||10000|||10000|||10000|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||COOL界面，蓝色主调，标准风格，部分常用按钮，标准适合界面宽度，默认样式|||1|||zh-cn|||0|||500|||300|||0|||版权所有...|||000000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||1|||gif|jpg|bmp|wmz|||10000|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "mini|||blue|||coolblue|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||mini全菜单风格，全部功能按钮，工具栏占位小，标准界面宽度|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";
aStyle = "popup|||blue|||coolblue|||siteResource/|||550|||350|||rar|zip|exe|doc|xls|chm|hlp|||swf|||gif|jpg|jpeg|bmp|||rm|mp3|wav|mid|midi|ra|avi|mpg|mpeg|asf|asx|wma|mov|||gif|jpg|jpeg|bmp|||500|||100|||100|||100|||100|||1|||1|||EDIT|||1|||0|||0|||||||||1|||0|||酷蓝样式，主要用于标准弹窗，增加弹窗返回按钮。|||1||||||0|||500|||300|||0|||版权所有...|||FF0000|||12|||宋体||||||0|||jpg|jpeg|||100|||FFFFFF|||1|||0|||gif|jpg|bmp|wmz|||100|||100|||1|||66|||17|||5|||5|||0|||100|||100|||1|||5|||5|||88|||31|||1|||0";

aToolbar = "0|||TBHandle|FormatBlock|FontName|FontSize|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "0|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "0|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "1|||TBHandle|FormatBlock|FontName|FontSize|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "1|||TBHandle|Cut|Copy|Paste|PasteText|PasteWord|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|absolutePosition|zIndexForward|zIndexBackward|||常用工具栏|||2";
aToolbar = "1|||TBHandle|Image|Flash|Media|File|GalleryMenu|RemoteUpload|LocalUpload|ImportWord|ImportExcel|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|TBSep|Quote|Code|||高级工具栏|||3";
aToolbar = "1|||TBHandle|ShowBorders|Paragraph|BR|TBSep|TableInsert|TableProp|TBSep|TableCellProp|TableCellSplit|TBSep|TableRowProp|TableRowInsertAbove|TableRowInsertBelow|TableRowMerge|TableRowSplit|TableRowDelete|TableColInsertLeft|TableColInsertRight|TableColMerge|TableColSplit|TableColDelete|TBSep|FormText|FormTextArea|FormRadio|FormCheckbox|FormDropdown|FormButton|||表格与表单|||4";
aToolbar = "1|||TBHandle|EditMenu|FontMenu|ParagraphMenu|ComponentMenu|ObjectMenu|ToolMenu|TableMenu|FormMenu|FileMenu|ZoomMenu|TBSep|Refresh|Save|TBSep|Print|TBSep|ModeCode|ModeEdit|ModeText|ModeView|TBSep|SizePlus|SizeMinus|TBSep|Excel|EQ|TBSep|Maximize|About|||菜单与状态|||5";
aToolbar = "1|||TBHandle|SpellCheck|UpperCase|LowerCase|TBSep|GalleryMenu|GalleryImage|GalleryFlash|GalleryMedia|GalleryFile|TBSep|PrintBreak|TBSep|Site|TBSep|ZoomSelect|||工具栏6|||6";
aToolbar = "2|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "2|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "2|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "3|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "3|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "3|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "4|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "4|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "4|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "5|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "5|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "5|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "6|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "6|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "6|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "7|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "7|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "7|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "8|||TBHandle|FormatBlock|FontName|FontSize|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "8|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "8|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Maximize|About|||高级工具栏|||3";
aToolbar = "9|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|TBSep|EditMenu|FontMenu|ParagraphMenu|ComponentMenu|ObjectMenu|ToolMenu|FormMenu|TableMenu|FileMenu|Maximize|||mini工具栏|||1";
aToolbar = "10|||TBHandle|FormatBlock|FontName|FontSize|ZoomSelect|Bold|Italic|UnderLine|StrikeThrough|TBSep|SuperScript|SubScript|UpperCase|LowerCase|TBSep|JustifyLeft|JustifyCenter|JustifyRight|JustifyFull|||格式工具栏|||1";
aToolbar = "10|||TBHandle|Cut|Copy|Paste|PasteText|FindReplace|Delete|RemoveFormat|TBSep|UnDo|ReDo|SelectAll|UnSelect|TBSep|OrderedList|UnOrderedList|Indent|Outdent|ParagraphAttr|TBSep|ForeColor|BgColor|BackColor|BackImage|TBSep|RemoteUpload|LocalUpload|ImportWord|ImportExcel|||常用工具栏|||2";
aToolbar = "10|||TBHandle|Image|Flash|Media|File|GalleryMenu|TBSep|TableMenu|FormMenu|ShowBorders|TBSep|Fieldset|Iframe|HorizontalRule|Marquee|CreateLink|Anchor|Map|Unlink|TBSep|Symbol|Emot|Art|NowDate|NowTime|Quote|TBSep|Save|About|||高级工具栏|||3";
%>