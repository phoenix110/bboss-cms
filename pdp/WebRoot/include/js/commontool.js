(function(a){a.fn.aToolTip=function(b){var d={clickIt:false,closeTipBtn:"aToolTipCloseBtn",fixed:false,inSpeed:400,outSpeed:100,tipContent:"",toolTipClass:"aToolTip",xOffset:5,yOffset:5},c=a.extend({},d,b);return this.each(function(){var f=a(this);if(f.attr("title")){var e=f.attr("title")}else{var e=c.tipContent}if(e&&!c.clickIt){f.hover(function(h){f.attr({title:""});a("body").append("<div class='"+c.toolTipClass+"'><p class='aToolTipContent'>"+e+"</p></div>");var g=f.offset().left+f.outerWidth()+c.xOffset;a("."+c.toolTipClass).css({position:"absolute",display:"none",zIndex:"5000",clear:"both",top:(f.offset().top-a("."+c.toolTipClass).outerHeight()-c.yOffset)+"px"}).stop().fadeIn(c.inSpeed);if(f.offset().left+2*f.outerWidth()+c.xOffset>a(document.body).width()){a("."+c.toolTipClass).css({right:a(document.body).width()-f.offset().left+c.xOffset+"px"})}else{a("."+c.toolTipClass).css({left:g+"px"})}},function(){a("."+c.toolTipClass).stop().remove()})}if(!c.fixed&&!c.clickIt){f.mousemove(function(g){a("."+c.toolTipClass).css({top:(g.pageY-a("."+c.toolTipClass).outerHeight()-c.yOffset),left:(g.pageX+c.xOffset)})})}if(e&&c.clickIt){f.click(function(g){f.attr({title:""});a("body").append("<div class='"+c.toolTipClass+"'><p class='aToolTipContent'>"+e+"</p></div>");a("."+c.toolTipClass).append("<a class='"+c.closeTipBtn+"' href='#' alt='close'>close</a>");a("."+c.toolTipClass).css({position:"absolute",display:"none",zIndex:"50000",top:(f.offset().top-a("."+c.toolTipClass).outerHeight()-c.yOffset)+"px",left:(f.offset().left+f.outerWidth()+c.xOffset)+"px"}).fadeIn(c.inSpeed);a("."+c.closeTipBtn).click(function(){a("."+c.toolTipClass).fadeOut(c.outSpeed,function(){a(this).remove()});return false});return false})}});return this}})(jQuery);$(function(){loadjs()});function loadjs(){if($(".toolTip").length>0){$(".toolTip").aToolTip({fixed:true})}$("#changeColor").each(function(){$(this).find("tr:even").addClass("tr_gray");$(this).find("tr:odd").addClass("tr_white");$(this).find("tr").mouseover(function(){$(this).addClass("tr_highlight")}).mouseout(function(){$(this).removeClass("tr_highlight")});$(this).find("tr").click(function(){$(this).toggleClass("tr_selected")})});if(window.parent.frames.rightFrame){window.parent.loadFrame("rightFrame")}if(window.top.frames.indexFrame){window.top.loadFrame("indexFrame")}}function closeDlg(){this.frameElement.api.close()}function setTab(a,e,c){var b=document.getElementById("menu"+a).getElementsByTagName("a");var d=document.getElementById("main"+a).getElementsByTagName("ul");for(i=0;i<b.length;i++){b[i].className=i==e?"current":"";if(c){if(c.framesrc&&c.framesrc!=""){if(document.getElementById(c.frameid).src==null||document.getElementById(c.frameid).src==""){document.getElementById(c.frameid).src=c.framesrc}}}d[i].style.display=i==e?"block":"none"}};