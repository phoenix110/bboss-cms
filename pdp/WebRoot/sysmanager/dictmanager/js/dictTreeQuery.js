/*
	字典树查询，只适用于单级树查询。
*/

/**查询出来的节点集合**/
var a = document.getElementsByTagName("a");
/**保存定位到的元素集合**/
var eleArray = new Array();
/*模糊查询下一条记录*/
var next = 0;

function blurryQuary(){
	var count = 0;
	var blurryValue = document.all("blurryValue").value;
	if(blurryValue == ""){
		alert("查找内容不能为空!");
		return ;
	}
	for(var i = 0; i < a.length; i++){
		if(a[i].name != "" && a[i].name != "0"){
			document.all(a[i].name).parentElement.childNodes[0].style.backgroundColor="";
			if(a[i].innerText.indexOf(blurryValue) != -1){
				eleArray[count] = a[i].name;
				count ++;
			}
		}
	}
	if(eleArray.length > 0){
		document.all(eleArray[0]).parentElement.childNodes[0].style.backgroundColor="cornflowerblue";
		document.all(eleArray[0]).parentElement.focus();
		document.all.queryBtn.style.display="none";
 		document.all.nextBtn.style.display="";
 		next ++;
 		
	}else{
		onBlurryQueryChange();
		alert("没有找到您需要的相关信息！");
		return;
	}
	
}

/**文本属性改变**/
function onBlurryQueryChange(){
 count = 0;
 eleArray.length = 0;
 document.all.queryBtn.style.display="";
 document.all.nextBtn.style.display="none";
}

function nextNodes(){
	if(eleArray.length > next){
		document.all(eleArray[next-1]).parentElement.childNodes[0].style.backgroundColor="";
		document.all(eleArray[next]).parentElement.childNodes[0].style.backgroundColor="cornflowerblue";
		document.all(eleArray[next]).parentElement.focus();
		next ++;
	}else{
		alert("查询完成！");
		onBlurryQueryChange();
		next = 0;
	}
}

function enterKeydowngo(){ 
	if(window.event.keyCode == 13){
		if(document.all.queryBtn.style.display==""){
			blurryQuary();
		}else{
			nextNodes();
		}
	}
}

/*
	调用该js的页面必须这样写
*/
/*
<tr>
	<td align="left">
	<input name="blurryValue" type="text" onpropertychange="onBlurryQueryChange()" onkeydown="enterKeydowngo()"/>
	</td>
</tr>
<tr>
<td align="right">
	<input type="button" name="queryBtn" value="查询" onclick="blurryQuary()"  class="input">
	<input style="display:none" name="nextBtn" type="button" value="查找下一个" onclick="nextNodes()" class="input">
	</td>
</tr>
*/