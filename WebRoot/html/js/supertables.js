var superTable = function(tableId, options) {
    options = options || {};
    this.cssSkin = options.cssSkin || "";
    this.headerRows = parseInt(options.headerRows || "1");
    this.fixedCols = parseInt(options.fixedCols || "0");
	this.tableNumber=parseInt(options.tableNumber || "0" );
    this.colWidths = options.colWidths || [];
    this.initFunc = options.onStart || null;
    this.callbackFunc = options.onFinish || null;
    this.initFunc && this.initFunc();
    this.sBase = document.createElement("DIV");
    this.sFHeader = this.sBase.cloneNode(false);
    this.sHeader = this.sBase.cloneNode(false);
    this.sHeaderInner = this.sBase.cloneNode(false);
    this.sFData = this.sBase.cloneNode(false);
    this.sFDataInner = this.sBase.cloneNode(false);
    this.sData = this.sBase.cloneNode(false);
    this.sColGroup = document.createElement("COLGROUP");
    this.sDataTable = document.getElementById(tableId);
    this.sDataTable.style.margin = "0px";
	this.sDataTable.style.width = "100%";
    if (this.cssSkin !== "") {
        this.sDataTable.className += " " + this.cssSkin
    }
    if (this.sDataTable.getElementsByTagName("COLGROUP").length > 0) {
        this.sDataTable.removeChild(this.sDataTable.getElementsByTagName("COLGROUP")[0])
    }
    this.sParent = this.sDataTable.parentNode;
    this.sParentHeight = this.sParent.offsetHeight;
    this.sParentWidth = this.sParent.offsetWidth;
    this.sBase.className = "sBase";
    this.sFHeader.className = "sFHeader";
    this.sHeader.className = "sHeader";
    this.sHeaderInner.className = "sHeaderInner";
    this.sFData.className = "sFData";
    this.sFDataInner.className = "sFDataInner";
    this.sData.className = "sData";
    var alpha,
    beta,
    touched,
    clean,
    cleanRow,
    i,
    j,
    k,
    m,
    n,
    p;
    this.sHeaderTable = this.sDataTable.cloneNode(false);
    if (this.sDataTable.tHead) {
        alpha = this.sDataTable.tHead;
        this.sHeaderTable.appendChild(alpha.cloneNode(false));
        beta = this.sHeaderTable.tHead
    } else {
        alpha = this.sDataTable.tBodies[0];
        this.sHeaderTable.appendChild(alpha.cloneNode(false));
        beta = this.sHeaderTable.tBodies[0]
    }
    alpha = alpha.rows;
    for (i = 0; i < this.headerRows; i++) {
        beta.appendChild(alpha[i].cloneNode(true))
    }
    this.sHeaderInner.appendChild(this.sHeaderTable);
    if (this.fixedCols > 0) {
        this.sFHeaderTable = this.sHeaderTable.cloneNode(true);
        this.sFHeader.appendChild(this.sFHeaderTable);
        this.sFDataTable = this.sDataTable.cloneNode(true);
        this.sFDataInner.appendChild(this.sFDataTable)
    }
    alpha = this.sDataTable.tBodies[0].rows;
    for (i = 0, j = alpha.length; i < j; i++) {
        clean = true;
        for (k = 0, m = alpha[i].cells.length; k < m; k++) {
            if (alpha[i].cells[k].colSpan !== 1 || alpha[i].cells[k].rowSpan !== 1) {
                i += alpha[i].cells[k].rowSpan - 1;
                clean = false;
                break
            }
        }
        if (clean === true) break
    }
    cleanRow = (clean === true) ? i: 0;
    for (i = 0, j = alpha[cleanRow].cells.length; i < j; i++) {
        if (i === this.colWidths.length || this.colWidths[i] === -1) {
            this.colWidths[i] = alpha[cleanRow].cells[i].offsetWidth
        }
    }
    for (i = 0, j = this.colWidths.length; i < j; i++) {
        this.sColGroup.appendChild(document.createElement("COL"));
        this.sColGroup.lastChild.setAttribute("width", this.colWidths[i])
    }
    this.sDataTable.insertBefore(this.sColGroup.cloneNode(true), this.sDataTable.firstChild);
    this.sHeaderTable.insertBefore(this.sColGroup.cloneNode(true), this.sHeaderTable.firstChild);
    if (this.fixedCols > 0) {
        this.sFDataTable.insertBefore(this.sColGroup.cloneNode(true), this.sFDataTable.firstChild);
        this.sFHeaderTable.insertBefore(this.sColGroup.cloneNode(true), this.sFHeaderTable.firstChild)
    }
    if (this.cssSkin !== "") {
        this.sDataTable.className += " " + this.cssSkin + "-Main";
        this.sHeaderTable.className += " " + this.cssSkin + "-Headers";
        if (this.fixedCols > 0) {
            this.sFDataTable.className += " " + this.cssSkin + "-Fixed";
            this.sFHeaderTable.className += " " + this.cssSkin + "-FixedHeaders"
        }
    }
    if (this.fixedCols > 0) {
        this.sBase.appendChild(this.sFHeader)
    }
    this.sHeader.appendChild(this.sHeaderInner);
    this.sBase.appendChild(this.sHeader);
    if (this.fixedCols > 0) {
        this.sFData.appendChild(this.sFDataInner);
        this.sBase.appendChild(this.sFData)
    }
    this.sBase.appendChild(this.sData);
    this.sParent.insertBefore(this.sBase, this.sDataTable);
    this.sData.appendChild(this.sDataTable);
    var sDataStyles,
    sDataTableStyles;
    this.sHeaderHeight = this.sDataTable.tBodies[0].rows[(this.sDataTable.tHead) ? 0: this.headerRows].offsetTop;
    sDataTableStyles = "margin-top: " + (this.sHeaderHeight * -1) + "px;";
    sDataStyles = "margin-top: " + (this.sHeaderHeight) + "px;";
    sDataStyles += "height: " + (this.sParentHeight - this.sHeaderHeight) + "px;";
    if (this.fixedCols > 0) {
        this.sFHeaderWidth = this.sDataTable.tBodies[0].rows[cleanRow].cells[this.fixedCols].offsetLeft;
        if (window.getComputedStyle) {
            alpha = document.defaultView;
            beta = this.sDataTable.tBodies[0].rows[0].cells[0];
            if (navigator.taintEnabled) {
                this.sFHeaderWidth += Math.ceil(parseInt(alpha.getComputedStyle(beta, null).getPropertyValue("border-right-width")) / 2)
            } else {
                this.sFHeaderWidth += parseInt(alpha.getComputedStyle(beta, null).getPropertyValue("border-right-width"))
            }
        } else if (
        /*@cc_on!@*/
        0) {
            alpha = this.sDataTable.tBodies[0].rows[0].cells[0];
            beta = [alpha.currentStyle["borderRightWidth"], alpha.currentStyle["borderLeftWidth"]];
            if (/px/i.test(beta[0]) && /px/i.test(beta[1])) {
                beta = [parseInt(beta[0]), parseInt(beta[1])].sort();
                this.sFHeaderWidth += Math.ceil(parseInt(beta[1]) / 2)
            }
        }
        if (window.opera) {
            this.sFData.style.height = this.sParentHeight + "px"
        }
        this.sFHeader.style.width = this.sFHeaderWidth + "px";
        sDataTableStyles += "margin-left: " + (this.sFHeaderWidth * -1) + "px;";
        sDataStyles += "margin-left: " + this.sFHeaderWidth + "px;";
        sDataStyles += "width: " + (this.sParentWidth - this.sFHeaderWidth) + "px;"
    } else {
        sDataStyles += "width: " + this.sParentWidth + "px;"
    }
    this.sData.style.cssText = sDataStyles;
    this.sDataTable.style.cssText = sDataTableStyles; (function(st) {
        if (st.fixedCols > 0) {
            st.sData.onscroll = function() {
                st.sHeaderInner.style.right = st.sData.scrollLeft + "px";
                st.sFDataInner.style.top = (st.sData.scrollTop * -1) + "px"
            }
        } else {
            st.sData.onscroll = function() {
                st.sHeaderInner.style.right = st.sData.scrollLeft + "px"
            }
        }
        if (
        /*@cc_on!@*/
        0) {
            window.attachEvent("onunload", 
            function() {
                st.sData.onscroll = null;
                st = null
            })
        }
    })(this);
    this.callbackFunc && this.callbackFunc()
};
var superTable_demo ; 
var superTable_demo_sData_width ;
var superTable_demo_sHeader_width ;
$(document).ready(function() {
superTable_demo = new superTable("tb", {
    cssSkin: "sDefault",
    fixedCols: 4,
	tableNumber:10
  });
  
  superTable_demo_sData_width = parseInt(superTable_demo.sData.style.width);
  superTable_demo_sHeader_width = parseInt(superTable_demo.sFHeader.style.width);
  var width = screen.width;
  var height = screen.height;
  var tableNumberH=superTable_demo.tableNumber*21+49;
  
 

if(width<=1024){
	
var container = document.getElementById("changeColor");
if(tableNumberH>232)
{
	container.style.height="257px";
	superTable_demo.sData.style.height = "232px";
}
else
{
	container.style.height=tableNumberH+"px";
	superTable_demo.sData.style.height=(tableNumberH-25)+"px";
}

}
if(width>1024&&width<=1440){


var a = document.getElementById("changeColor");
if(tableNumberH>362)
{
	a.style.height = '362px';
	
    superTable_demo.sData.style.height = "337px";
}
else
{
    a.style.height=tableNumberH+"px";
    superTable_demo.sData.style.height=(tableNumberH-25)+"px";
}


}
if(width>1440){
var container2 = document.getElementById("changeColor");
if(tableNumberH>600)
{
    container2.style.height = "600px";
    superTable_demo.sData.style.height = "575px";
}
else
{
	container2.style.height=tableNumberH+"px";
    superTable_demo.sData.style.height=(tableNumberH-25)+"px";
	
}
}
});// JavaScript Document