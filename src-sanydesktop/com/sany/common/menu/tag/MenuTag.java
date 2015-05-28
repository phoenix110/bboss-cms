package com.sany.common.menu.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.frameworkset.web.servlet.support.RequestContextUtils;

import com.frameworkset.common.tag.BaseTag;
import com.frameworkset.orm.transaction.TransactionManager;
import com.frameworkset.platform.framework.Framework;
import com.frameworkset.platform.framework.Item;
import com.frameworkset.platform.framework.ItemQueue;
import com.frameworkset.platform.framework.MenuHelper;
import com.frameworkset.platform.framework.Module;
import com.frameworkset.platform.framework.ModuleQueue;
import com.frameworkset.platform.security.AccessControl;

/**
 * <div id="menubar">
  <ul id="menus" class="menus">
    <li><a class="select" href="#">首页</a></li>
    <li><a href="#">费用申请</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>新建申请单</div>
        </a></li>
        <li><a href="#"><span></span>
          <div>申请单草稿箱</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我的申请单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我经手的申请单</div>
        </a></li>
      </ul>
    </li>
    <li><a href="#">借款交款</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>在线填单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>借交款草稿箱</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我的借款单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我的交款单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我经手的借交款</div>
        </a></li>
      </ul>
    </li>
    <li><a href="#">费用报销</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>新建报销单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>报销单草稿箱</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我的报销单</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我经手的报销单</div>
        </a></li>
      </ul>
    </li>
    <li><a href="#">业务审批</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>待我审批</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我已审批</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>待我批阅</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我已批阅</div>
        </a></li>
      </ul>
    </li>
    <li><a href="#">财务管理</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>待我审核</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我已审核</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>付款管理</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>收款管理</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>账务管理</div>
          </a>
          <ul class="third">
            <li><a  href="#"><span></span>单据处理</a></li>
            <li><a  href="#"><span></span>单号对应凭证号</a></li>
          </ul>
        </li>
      </ul>
    </li>
    <li><a href="#">机票</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>国内机票查询</a></div>
        </li>
        <li><a  href="#"><span></span>
          <div>机票订单</a></div>
        </li>
      </ul>
    </li>
    <li><a href="#">个人中心</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>个人信息</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>我的审批意见</a></div>
        </li>
        <li><a  href="#"><span></span>
          <div>提单授权</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>职位授权</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>系统消息</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>公司公告</div>
        </a></li>
      </ul>
    </li>
    <li><a href="#">统计分析</a>
      <ul class="second">
        <li><a  href="#"><span></span>
          <div>查询分析</div>
        </a></li>
        <li><a  href="#"><span></span>
          <div>费用分析报表</a></div>
        </li>
        <li><a  href="#"><span></span>
          <div>商旅分析报表</div>
        </a></li>
      </ul>
    </li>
  </ul>
</div>
 * @author yinbp
 *
 */
public class MenuTag extends BaseTag {
	private static String header = "<div id=\"menubar\" class=\"ddsmoothmenu \"><ul>"; 
	private static String header_common = "<div id=\"menubar\">  <ul id=\"menus\" class=\"menus\">";
	private static String rooter = "</ul><br style=\"clear: left\" /></div>";
	private static String rooter_common = "</ul></div>";
	public static final String personcenter = "个人中心";
	
	
	
	private boolean enableindex = true;
	private int level = 3;
	private String web = "false";
//	private String style="default";// default common
//	
//	public String getStyle() {
//		return style;
//	}
//
//
//
//	public void setStyle(String style) {
//		this.style = style;
//	}



	@Override
	public int doStartTag() throws JspException {	
		int ret = super.doStartTag();
		AccessControl control = AccessControl.getAccessControl();
		MenuHelper menuHelper =  MenuHelper.getMenuHelper(request);
		String tokenurl = request.getContextPath() + "/token/getParameterToken.freepage"; 
		String personcenter = Framework.getInstance(control.getCurrentSystemID()).getMessage("sany.pdp.module.personcenter", RequestContextUtils.getRequestContextLocal(request));
		StringBuffer datas = new StringBuffer();
		String selectedmenuid = request.getParameter(MenuHelper.sanyselectedmodule);//查找选择的菜单项path
		String loginstyle = AccessControl.getLoginStyle(request);
		if(loginstyle.equals("3"))
			datas.append(header);
		else
			datas.append(header_common);

		TransactionManager tm = new TransactionManager(); 
		try{
			tm.begin();
			String contextpath = request.getContextPath();
			ItemQueue itemQueue = menuHelper.getItems();
			ModuleQueue moduleQueue = menuHelper.getModules();
			Item publicitem = menuHelper.getPublicItem();
			
			String framepath = web != null && web.equals("true")?contextpath + "/sanydesktop/webframe.page":contextpath + "/sanydesktop/frame.page";
			if(this.enableindex && publicitem != null && publicitem.isMain())
			{
	
				String target = publicitem.getTarget() == null ?"mainFrame":publicitem.getTarget();
				String url = MenuHelper.getRealUrl(contextpath,publicitem.getWorkspacecontentExtendAttribute("isany"));
				String selectedclass = "";
				if(selectedmenuid == null || selectedmenuid.equals("publicitem"))
				{
					selectedclass = "class=\"select\"";
				}
				String mname = publicitem.getName(request);
				if(target.equals("mainFrame"))
				{
					//tokenurl
					datas.append("<li><a href=\"javascript:void(0)\" id=\"anchor_").append("publicitem").append("\"  ").append(selectedclass).append(" onClick=\"navto_sany_MenuItem('").append(tokenurl)
					.append("','").append("publicitem")
					.append("','").append(url).append("','")
					.append(target).append("',").append(publicitem.getOption()).append(",'").append(mname).append("')\">")
					.append(mname)
					.append("</a></li>");
				}
				else
				{
					
					datas.append("<li><a href=\"javascript:void(0)\" id=\"anchor_").append("publicitem").append("\"  ").append(selectedclass).append(" onClick=\"navto_sany_MenuItem_window('").append(tokenurl)
					.append("','").append(publicitem.getName(request))
					.append("','").append("publicitem")
					.append("','','").append(contextpath)
					 .append("','")
					.append(target).append("')\">")
					.append(mname)
					.append("</a></li>");
				}
			}
			
			
			
			for (int i = 0; moduleQueue != null && i < moduleQueue.size(); i++) {
				Module module = moduleQueue.getModule(i);
				if (!module.isUsed()) {
					continue;
				}
				String selectedclass = "";
				if(selectedmenuid != null && selectedmenuid.startsWith(module.getId()))
				{
					selectedclass = "class=\"select\"";
				}
				if(!module.isShowleftmenu())
				{
					if(module.getUrl() == null)
					{
						datas.append("<li><a id=\"anchor_").append(module.getId()).append("\"  ").append(selectedclass).append(" href=\"javascript:void(0)\">").append(module.getName(request)).append("</a>");
					}
					else
					{
						String target = module.getTarget() == null ?"mainFrame":module.getTarget();
						String url = MenuHelper.getModuleUrl(module, contextpath,  control);
						{
							String mname = module.getName(request);
							datas.append("<li><a href=\"javascript:void(0)\"  ").append(selectedclass).append("  id=\"anchor_")
								 .append(module.getId())
								 .append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
								 .append("','")
								 .append(module.getId())
								 .append("','")
								 .append(url)
								 .append("','")
								 .append(target)
								 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">")
								 .append(mname)
								 .append("</a>");
						}					
					}
				}
				else
				{
					String target = module.getTarget() == null ?"mainFrame":module.getTarget();
					if(target.equals("mainFrame"))
					{
						boolean hasson = module.hasSonOfModule();
						String mname = module.getName(request);
						if(hasson)
						{
							
							datas.append("<li><a href=\"javascript:void(0)\"  ").append(selectedclass).append("  id=\"anchor_")
								 .append(module.getId())
								 .append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
								 .append("','")
								 .append(module.getId())
								 .append("','")
								 .append(framepath).append("?")
								 .append(MenuHelper.sanymenupath)
								 .append("=")
								 .append(module.getPath())
								 .append("','")
								 .append(target)
								 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">")
								 .append(mname)
								 .append("</a>");
						}
						else
						{
							if(module.getUrl() == null)
							{
								datas.append("<li><a id=\"anchor_").append(module.getId()).append("\"  ").append(selectedclass).append(" href=\"javascript:void(0)\">").append(module.getName(request)).append("</a>");
							}
							else
							{
								String url = MenuHelper.getModuleUrl(module, contextpath,  control);
								{
									datas.append("<li><a href=\"javascript:void(0)\"  ").append(selectedclass).append("  id=\"anchor_")
										 .append(module.getId())
										 .append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
										 .append("','")
										 .append(module.getId())
										 .append("','")
										 .append(url)
										 .append("','")
										 .append(target)
										 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">")
										 .append(mname)
										 .append("</a>");
								}					
							}
						}
					}
					else
					{
						datas.append("<li><a href=\"javascript:void(0)\"  ").append(selectedclass).append(" id=\"anchor_")
						 .append(module.getId())
						 .append("\" onClick=\"navto_sany_MenuItem_window('").append(tokenurl)
					.append("','")
						 .append(module.getName(request))
						 .append("','")
						 .append(module.getId())
						 .append("','")
						 .append(module.getPath())
						 .append("','").append(contextpath)
						 .append("','")
						 .append(target)
						 .append("')\">")
						 .append(module.getName(request))
						 .append("</a>");
					}
				}
				boolean hasson = (module.getItems() != null && module.getItems().size() > 0)||(module.getSubModules() != null && module.getSubModules().size() > 0) ;
				if(hasson)
				{
					if( loginstyle.equals("3"))
					{
						datas.append("<ul >");  //class=\"second\"
					}
					else
					{
						datas.append("<ul class=\"second\">");  //class=\"second\"
					}
					
					ItemQueue subitems = module.getItems() != null ?module.getItems():null;
					for(int j = 0; subitems != null && j < subitems.size(); j ++)
					{
						Item subitem = subitems.getItem(j);
						
						
						String target = subitem.getTarget() == null ?"mainFrame":subitem.getTarget();	
						
						
						
						
						if(target.equals("mainFrame"))
						{
		
							String mname = subitem.getName(request);
							String url = MenuHelper.getItemUrl(subitem, contextpath, framepath, control);
							datas.append("<li><a id=\"anchor_").append(subitem.getId()).append("\" href=\"javascript:void(0)\" onclick=\"navto_sany_MenuItem('").append(tokenurl)
						.append("','").append(module.getId()).append("','").append(url).append("','").append(target).append("',").append(subitem.getOption()).append(",'").append(mname).append("')\"><span></span>")
								.append("<div>").append(mname).append("</div>")
								.append("</a></li>");
						}
						else
						{
							datas.append("<li><a id=\"anchor_").append(subitem.getId())
							.append("\" href=\"javascript:void(0)\" onclick=\"navto_sany_MenuItem_window('").append(tokenurl)
							.append("','")
							.append(subitem.getName(request)).append("','")
							.append(module.getId()).append("','")
							.append(subitem.getPath()).append("','").append(contextpath)
							 .append("','")
							.append(target).append("')\"><span></span>")
							.append("<div>").append(subitem.getName(request)).append("</div>")
							.append("</a></li>");
						}
						
					}
					ModuleQueue submodules = module.getSubModules() != null?  module.getSubModules():null;
					for(int j = 0; submodules != null && j < submodules.size(); j ++)
					{
						Module submodule = submodules.getModule(j);
		
						String target = submodule.getTarget() == null ?"mainFrame":submodule.getTarget();
						
						renderSubMenus(submodule, datas, contextpath, target,control,module.getId(),framepath,2,tokenurl, loginstyle);
		
					}
					
					
					datas.append("</ul>");
				}
				datas.append("</li>");
				
			}
			
			/**
			 * 个人中心
			 */
			if(itemQueue != null && itemQueue.size() > 0)
			{
				String selectedclass = "";
				if(selectedmenuid != null && selectedmenuid.equals("isany_personcenter"))
				{
					selectedclass = "class=\"select\"";
				}
				if(!menuHelper.isShowrootmenuleft())
				{
					datas.append("<li><a id=\"anchor_").append("isany_personcenter").append("\" ").append(selectedclass).append(" href=\"javascript:void(0)\">").append(personcenter).append("</a>");
				}
				else
				{
					String target = "mainFrame";	
					datas.append("<li><a href=\"javascript:void(0)\" id=\"anchor_")
					 .append("isany_personcenter")
					 .append("\" ").append(selectedclass).append(" onClick=\"navto_sany_MenuItem('").append(tokenurl)
					.append("','")
					 .append("isany_personcenter")
					 .append("','")
					 .append(framepath).append("?")
					 .append(MenuHelper.sanymenupath)
					 .append("=")
					 .append("isany_personcenter")
					 .append("','")
					 .append(target)
					 .append("',{})\">")
					 .append(personcenter)
					 .append("</a>");
				}
				datas.append("<ul class=\"second\">");
				Item item = null ;
				for(int i = 0; i < itemQueue.size(); i ++)
				{
					item = itemQueue.getItem(i);
	
					String target = item.getTarget() == null ?"mainFrame":item.getTarget();	
					if(target.equals("mainFrame"))
					{
						String mname = item.getName(request);
						String url = MenuHelper.getItemUrl(item, contextpath, framepath, control);
						datas.append("<li><a href=\"javascript:void(0)\" id=\"anchor_").append(item.getId()).append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
					.append("','").append("isany_personcenter").append("','").append(url).append("','").append(target).append("',").append(item.getOption()).append(",'").append(mname).append("')\"><span></span>")
						.append("<div>").append(mname).append("</div>")
						.append("</a></li>");
					}
					else
					{
						datas.append("<li><a href=\"javascript:void(0)\" id=\"anchor_").append(item.getId()).append("\" onClick=\"navto_sany_MenuItem_window('").append(tokenurl)
					.append("','")
						.append("isany_personcenter").append("','")
						.append(item.getPath())
						.append("','").append(contextpath)
						 .append("','")
						.append(target)
						.append("')\"><span></span>")
						.append("<div>").append(item.getName(request)).append("</div>")
						.append("</a></li>");
					}
			        
				}
			    datas.append("</ul></li>");
			}
			tm.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			tm.release();
		}
			
		  
		datas.append(rooter);
		if( loginstyle.equals("3"))
			datas.append(rooter);
		else
			datas.append(rooter_common);
		try {
//			System.out.println(datas.toString());
			this.out.write(datas.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			datas = null;
		}
		return ret;
	}
	
	
	
	private void renderSubMenus(Module module,StringBuffer datas,String contextpath,String target,AccessControl control,String selectedID,String framepath,int current_level,String tokenurl,String loginstyle)
	{
//		String tokenurl = request.getContextPath() + "/token/getParameterToken.freepage"; 
		if (!module.isUsed()) {
			return ;
		}			
	
		if(!module.isShowleftmenu())
		{
			
			if(module.getUrl() == null)
			{
				datas.append("<li><a  href=\"javascript:void(0)\"><span></span><div>").append(module.getName(request)).append("</div></a>");
			}
			else//如果配置了链接，就必须要能够将链接显示出来
			{
				String mname = module.getName(request);
				String url = MenuHelper.getModuleUrl(module, contextpath,  control);
				datas.append("<li><a  href=\"javascript:void(0)\" ").append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
				.append("','")
				 .append(selectedID)
				 .append("','")
				 .append(url)
				 .append("','")
				 .append(target)
				 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">").append(mname).append("</a>");
			}
		}
		else
		{
			if(target.equals("mainFrame"))
			{
				boolean hasson = module.hasSonOfModule();
				String mname = module.getName(request);
				if(hasson)
				{
					datas.append("<li><a  href=\"javascript:void(0)\" ").append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
					.append("','")
					 .append(selectedID)
					 .append("','")
					 .append(framepath).append("?")
					 .append(MenuHelper.sanymenupath)
					 .append("=")
					 .append(module.getPath())
					 .append("','")
					 .append(target)
					 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">").append(mname).append("</a>");
				}
				else // 没有儿子的情况处理
				{
					if(module.getUrl() == null)
					{
						datas.append("<li><a  href=\"javascript:void(0)\"><span></span><div>").append(mname).append("</div></a>");
					}
					else//如果配置了链接，就必须要能够将链接显示出来
					{
						String url = MenuHelper.getModuleUrl(module, contextpath,  control);
						datas.append("<li><a  href=\"javascript:void(0)\" ").append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
						.append("','")
						 .append(selectedID)
						 .append("','")
						 .append(url)
						 .append("','")
						 .append(target)
						 .append("',").append(module.getOption()).append(",'").append(mname).append("')\">").append(mname).append("</a>");
					}
				}
			}
			else
			{
				datas.append("<li><a  href=\"javascript:void(0)\" ").append("\" onClick=\"navto_sany_MenuItem_window('").append(tokenurl)
				.append("','")
				 .append(module.getName(request))
				 .append("','")
				 .append(selectedID)
				 .append("','")
				 .append(module.getPath())
				 .append("','")
				 .append(contextpath)
				 .append("','")
				 .append(target)
				 .append("')\">").append(module.getName(request)).append("</a>");
			}
			
		}
		if(current_level < level)
		{
			boolean hasson = (module.getItems() != null && module.getItems().size() > 0)||(module.getSubModules() != null && module.getSubModules().size() > 0) ;
			if(hasson)
			{
				if(loginstyle.equals("3"))
					datas.append("<ul>"); // class=\"third\"
				else
					datas.append("<ul class=\"third\">"); // class=\"third\"
				current_level ++;
				ModuleQueue submodules = module.getSubModules() != null?  module.getSubModules():null;
				for(int j = 0; submodules != null && j < submodules.size(); j ++)
				{
					
					Module submodule = submodules.getModule(j);
					String target_ = submodule.getTarget() == null?"mainFrame":submodule.getTarget(); 
					renderSubMenus(submodule,datas,contextpath,target_,control,selectedID,framepath,current_level,tokenurl,loginstyle);
	
				}
				ItemQueue subitems = module.getItems() != null ?module.getItems():null;
				for(int j = 0; subitems != null && j < subitems.size(); j ++)
				{
					Item subitem = subitems.getItem(j);
					String area = subitem.getArea();
					
	
					String target_ = subitem.getTarget() == null?"mainFrame":subitem.getTarget(); 
					if(target.equals("mainFrame"))
					{
						String mname = subitem.getName(request);
						String url = MenuHelper.getItemUrl(subitem, contextpath, framepath, control);
						datas.append("<li><a  href=\"javascript:void(0)\" id=\"anchor_").append(subitem.getId()).append("\" onClick=\"navto_sany_MenuItem('").append(tokenurl)
					.append("','").append(selectedID).append("','").append(url).append("','").append(target_).append("',").append(subitem.getOption()).append(",'").append(mname).append("')\">")
							.append(mname)
							.append("</a></li>");
					}
					else
					{
						datas.append("<li><a  href=\"javascript:void(0)\" id=\"anchor_").append(subitem.getId())
						.append("\" onClick=\"navto_sany_MenuItem_window('").append(tokenurl)
					.append("','")
						.append(subitem.getName(request)).append("','")
						.append(selectedID).append("','").append(subitem.getPath()).append("','").append(contextpath)
						 .append("','").append(target_).append("')\">")
						.append(subitem.getName(request))
						.append("</a></li>");
					}
					
				}
				datas.append("</ul>");
			}
		}
		
		datas.append("</li>");
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void doFinally() {
		// TODO Auto-generated method stub
		super.doFinally();
		this.level = 3;
		this.web = "false";
		
		enableindex = true;
	}

	

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}



	public boolean isEnableindex() {
		return enableindex;
	}



	public void setEnableindex(boolean enableindex) {
		this.enableindex = enableindex;
	}
	
}
