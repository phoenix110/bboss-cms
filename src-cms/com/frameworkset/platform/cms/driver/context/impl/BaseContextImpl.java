package com.frameworkset.platform.cms.driver.context.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import com.frameworkset.common.poolman.DBUtil;
import com.frameworkset.platform.cms.container.Template;
import com.frameworkset.platform.cms.driver.config.DriverConfiguration;
import com.frameworkset.platform.cms.driver.config.DriverConfigurationException;
import com.frameworkset.platform.cms.driver.context.Context;
import com.frameworkset.platform.cms.driver.context.FTPConfig;
import com.frameworkset.platform.cms.driver.dataloader.CMSDetailDataLoader;
import com.frameworkset.platform.cms.driver.distribute.DistributeDestination;
import com.frameworkset.platform.cms.driver.distribute.DistributeManager;
import com.frameworkset.platform.cms.driver.distribute.IndexObject;
import com.frameworkset.platform.cms.driver.htmlconverter.CMSTemplateLinkTable;
import com.frameworkset.platform.cms.driver.htmlconverter.CmsLinkProcessor.CMSLink;
import com.frameworkset.platform.cms.driver.htmlconverter.CmsLinkTable;
import com.frameworkset.platform.cms.driver.jsp.CMSRequestContext;
import com.frameworkset.platform.cms.driver.publish.PubObjectReference;
import com.frameworkset.platform.cms.driver.publish.PublishMode;
import com.frameworkset.platform.cms.driver.publish.PublishObject;
import com.frameworkset.platform.cms.driver.publish.impl.PublishMonitor;
import com.frameworkset.platform.cms.sitemanager.Site;
import com.frameworkset.platform.cms.sitemanager.SiteManagerException;
import com.frameworkset.platform.cms.sitemanager.SiteManagerImpl;
import com.frameworkset.platform.cms.util.CMSDBFunction;
import com.frameworkset.platform.cms.util.CMSUtil;

/**
 * 
 * <p>
 * Title: com.frameworkset.platform.cms.driver.context.impl.BaseContextImpl.java
 * </p>
 * 
 * <p>
 * Description: 内容管理上下文基础类
 * </p>
 * 
 * <p> 
 * Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company: iSany
 * </p>
 * 
 * @Date 2007-1-29
 * @author biaoping.yin
 * @version 1.0
 */
public abstract class BaseContextImpl implements Context {
	 
	/**
	 * 以下几个变量存放发布模版过程当中解析模版标签时分析出来的文件链接
	 * 以便发布过程执行完毕后将这些附件发布出去，并发布相关的页面（动态和静态页面）
	 */
	protected CmsLinkTable staticPageLinkTable;
	protected CmsLinkTable dynamicPageLinkTable;
	protected CMSTemplateLinkTable templateLinkTable;
	protected boolean clearFileCache = false;
	protected boolean enableRecursive = true;
	/**
	 * 文档对应的附件表
	 */
	protected CmsLinkTable contentLinkTable;

	protected int currentPublishDepth;
	protected int maxPublishDepth = -1;
	
	/**
	 * 记录发布过程中涉及到所有站点
	 */
	private Map siteDistributeDestinations;
	
	private static final Object traceObject = new Object();
	
	
	
	protected Properties properties;
	
	protected PublishObject publishObject;
	
	protected String localPublishDestination;
	
	/**
	 * 测试时使用的细览模版
	 */
	protected Template testdetailTemplate = new Template();
	/**
	 * 测试时使用的首页模版
	 */
	protected Template testindexTemplate = null;
	/**
	 * 测试时使用的概览模版
	 */
	protected Template testoutLineTemplate = null;

	protected Context parentContext;

	protected String[] publisher;

	protected PublishMonitor monitor;

	protected String rendRootPath;

	protected Context rootContext;

	protected String siteID;
	
	protected String siteDir;

	protected String dbName;

	protected int[] publishScope;

	protected int[] distributeManners;
	
	protected boolean autoCorrectHtml = false;
	
    /**
     * 站点发布的根目录
     */
	protected String sitePublishRootPath ;
	
//	/**
//	 * 判断当前的发布是否是递归发布,true标识是，false标识为不是
//	 * 由尹标平于2007.9.21注释掉，判断发布任务是否是递归任务通过publishObject来判断
//	 * 因此在这里不需要定义recursive变量
//	 */
//	protected boolean recursive = false;
	
	protected List enablePublishStatus;
	protected String publishPath;
	protected String mimeType;
	protected PublishMode publishMode;
	protected String charset = "UTF-8";
	protected String fileName;
	
	/**
	 * 发布临时文件名称
	 */
	protected String tempFileName;
	/**
	 * 发布临时文件名称
	 */
	protected String jspFileName;
	protected String fileExt;

	protected boolean[] local2ndRemote = PublishObject.DEFAULT_PUBLISH_LOCAL;

	protected CMSDetailDataLoader dataloader;

	private String projectRootPath;

	private String templateRootPath;
	
	private String styleRootPath;
	
	private String imagesRootPath;

	protected String sitePublishContext = "";
	
	protected String previewContextPath = "";
	
	protected String sitePreviewRootPath;
	protected String sitePreviewContextPath;

	protected FTPConfig FTPConfig;
	/**
	 * 站点发布时对应的域名
	 */
	protected String domain;
	
	protected String tempPublishUID;
	
	/**
	 * 用来记录引用当前发布对象的其他对象
	 */
	protected Set recursivePubObjects;
	
	/**
	 * 用来记录当前对象引用的页面元素
	 */
	protected Set refObjects;
	
	/**
	 * 用来记录索引对象，一个发布任务只存放一个Set集，
	 * 当发布任务执行完毕后
	 */
	protected Set indexObjects;
	
	/**
	 * 2009.01.22之前的默认值为false，之后改为true
	 * 默认为true，这是为了使cms系统在实时发布时
	 * 每次都能更新联动发布数据，以便系统能够智能地进行递归发布
	 * 所谓的递归发布就是指：当发布某个对象时，如果该发布对象和其他的发布对象有关联，则应该将关联的对象
	 * 一起发布
	 * 该值在批处理发布的上下文时设置为false
	 * 发布不包含标签的静态页面时设置为false
	 * 发布目录和其他样式文件和图片、多媒体文件时，设置为false
	 */
	protected boolean needRecordRefObject = true;
	
	/**
	 * 保留当前的站点信息
	 */
	protected Site site;
	
//	/**
//	 * 当前任务的contextPath
//	 */
//	protected String currentContextPath = "creatorcms";
	public BaseContextImpl()
	{
		this.charset = CMSUtil.getCharset();
	}

	public BaseContextImpl(String siteid,Context parentContext, PublishObject publishObject,
			PublishMonitor monitor) {
		
		this.charset = CMSUtil.getCharset();
		if(parentContext != null)
		{
			this.parentContext = parentContext;
//			this.dbName = parentContext.getDBName();
			
		}
		else
		{
			this.parentContext = this;
		}
		
		try {
			site = CMSUtil.getSiteCacheManager().getSite(siteid);
			
			this.siteDir =  site.getSiteDir();
			this.siteID = siteid;
			this.dbName = site.getDbName();
			this.domain = site.getWebHttp();
			this.localPublishDestination = site.getLocalPublishPath();
			
			
			
			this.FTPConfig = new FTPConfig(site.getFtpIp(), site.getFtpPort()
					+ "", site.getFtpFolder(), site.getFtpUser(), site
					.getFtpPassword()); 
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.publishObject = publishObject;
		this.monitor = monitor;
		this.distributeManners = publishObject.getDistributeManners();
		if(distributeManners == null)
		{
			try {
				this.distributeManners = getDriverConfiguration().getCMSService()
						.getSiteManager().getSiteDistributeManners(this.siteID);
			} catch (SiteManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DriverConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.publisher = this.publishObject.getPublisher();
//		this.local2ndRemote = publishObject.getLocal2ndRemote();
		try {
			local2ndRemote = SiteManagerImpl.getSitePublishDestination(site.getPublishDestination());
		} catch (SiteManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.publishScope = this.publishObject.getPublishScope();
		
		contentLinkTable = new CmsLinkTable();
		contentOrigineTemplateLinkTable = new CMSTemplateLinkTable(new CmsLinkTable());
		this.templateLinkTable = new CMSTemplateLinkTable(new CmsLinkTable()); //发布存放文档、模版的附件地址
		this.dynamicPageLinkTable = new CmsLinkTable();//发布时存放文档、模版的动态页面地址
		this.staticPageLinkTable = new CmsLinkTable();//发布时存放文档、模版的静态页面地址
		
		/**
		 * 初始化递归发布对象列表
		 */
		this.recursivePubObjects = new TreeSet();
		
		/**
		 * 初始化引用发布对象列表
		 */
		this.refObjects = new TreeSet();
		
		
		if (this.publishObject.isRoot())
		{
			//发布的根context。
			//注意：parentContext不一定是rootContext
			this.rootContext = this;
			
			this.tempPublishUID = System.currentTimeMillis() + "";
			if(CMSUtil.enableIndex())
				this.indexObjects = new TreeSet();
		}
		
//		/**
//		 * 如果当前的发布属于递归发布，则将递归发布的变量置为true
//		 * 并且指定当前最大的发布深度为1
//		 * 由尹标平于2007.9.21注释掉，判断发布任务是否是递归任务通过publishObject来判断
//		 * 因此在这里不需要设置recursive的值
//		 */
//		if(parentContext instanceof RecursiveContext)
//		{
//			this.recursive = true;
//
//		}
//		else
//		{
//			this.recursive = this.parentContext.isRecursive(); 
//		}
		
	}
	
	
	
	/**
	 * 添加标签处理过程中分析出来的附件链接
	 * @param link
	 */
	public void addTemplateLink(CMSLink link)
	{
//		if(!this.isRootContext())
//		{
//			this.getRootContext().addTemplateLink(link);
//		}
//		else
		{
			this.templateLinkTable.addLink(link,this);
		}
	}
	
	/**
	 * 添加标签处理过程中分析出来的动态页面地址
	 * @param link
	 */
	public void addDynamicTemplateLink(CMSLink link)
	{
//		if(!this.isRootContext())
//		{
//			this.getRootContext().addDynamicTemplateLink(link);
//		}
//		else
		{
			this.dynamicPageLinkTable.addLink(link);
		}
	}
	
	
	
	/**
	 * 添加标签处理过程中分析出来的静态页面地址
	 * @param link
	 */
	public void addStaticTemplateLink(CMSLink link)
	{
//		if(!this.isRootContext())
//		{
//			this.getRootContext().addStaticTemplateLink(link);
//		}
//		else
		{
			this.staticPageLinkTable.addLink(link);
		}
	}
	

	public String getID()
	{
		return this.publishObject.getPublishObjectID();
	}
	// protected DriverConfiguration driverConfiguration;
	public PublishObject getPublishObject() {
		return publishObject;
	}

	public void setPublishObject(PublishObject publishObject) {
		this.publishObject = publishObject;
	}

	public Context getParentContext() {
		return parentContext;
	}

	public void setParentContext(Context parentContext) {
		this.parentContext = parentContext;
	}

	public String getDBName() {
		return dbName;
	}

	public String[] getPublisher() {
		if(this.parentContext == null || this.parentContext == this)
			return publisher;
		return this.parentContext.getPublisher();
	}

	public void setPublisher(String[] publisher) {
		this.publisher = publisher;
	}

	/**
	 * 获取发布的监控器
	 * 
	 * @return
	 */
	public PublishMonitor getPublishMonitor() {
		return this.monitor;
	}

	/**
	 * 获取站点的发布范围
	 * 如果是根上下文则直接返回publishScope，
	 * 否则返回父上下文中的publishScope
	 */
	public int[] getPublishScope() {
		if(this.parentContext == null || this.parentContext == this)
			return publishScope;
		else
			return parentContext.getPublishScope();
	}

	public boolean isRootContext() {

		if (publishObject == null)
			return true;
		return this.publishObject.isRoot();

	}

	public String getSiteID() {
//		if(this.parentContext == null || parentContext == this)
//			return siteID;
//		else
//			return this.parentContext.getSiteID();
		return this.siteID;
	}

	

	public List getEnablePublishStatus() {
		return enablePublishStatus;
	}

	public String getPublishPath() {

		return publishPath;
	}

//	protected String rendPath;

	/**
	 * 获取相对的显示路径
	 */
	public String getRendPath() {
		return this.publishPath;
//		return rendPath;
	}
	
	public String getAbsoluteRendPath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		return CMSUtil.getPath(this.getCMSContextPath() +  getWebSiteRootPath() + this.getRendRootPath() , this.getRendPath());
		
	}
	
	public String getAbsoluteRendRootPath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		return this.getCMSContextPath() +  getWebSiteRootPath() + this.getRendRootPath();
		
	}
	
	
	
	public String getAbsoluteProjectPath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		
		return CMSUtil.getPath(this.getCMSContextPath() +  getWebSiteRootPath() + this.getProjectRootPath(),this.getRendPath());
		
	}
	
	public String getAbsoluteProjectRootPath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		return this.getCMSContextPath() +  getWebSiteRootPath() + this.getProjectRootPath();
		
	}
	
	
	public String getAbsoluteTemplatePath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		return CMSUtil.getPath(this.getCMSContextPath() +  getWebSiteRootPath() + this.getTemplateRootPath() ,this.getRendPath() );
		
	}
	
	public String getAbsoluteTemplateRootPath()
	{
//		if(this.isRootContext())
//			return this.getRendRootPath() + "/" + this.getRendPath();
//		else
//		{
//			return this.parentContext.getAbsoluteRendPath() + "/" + this.getRendPath();
//		}
		return this.getCMSContextPath() +  getWebSiteRootPath() + this.getTemplateRootPath() ;
		
	}
	
	/**
	 * 获取站点预览文件的物理路径
	 * @return
	 */
	public String getRealRendPath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteRendPath();
	}
	
	/**
	 * 获取站点工程文件的物理路径
	 * @return
	 */
	public String getRealProjectPath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteProjectPath();
	}
	
	/**
	 * 获取站点工程文件的物理根路径
	 * @return
	 */
	public String getRealProjectRootPath()
	{
		return CMSUtil.getPath(CMSUtil.getAppRootPath() ,this.getCMSContextPath() +  getWebSiteRootPath() + this.getProjectRootPath());
	}
	
	
	/**
	 * 获取站点模版文件的物理路径
	 * @return
	 */
	public String getRealTemplatePath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteTemplatePath();
	}
	
	/**
	 * 获取站点模版文件的物理根路径
	 * @return
	 */
	public String getRealTemplateRootPath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteTemplateRootPath();
	}
	
	

	public String getMimeType() {

		return mimeType == null ? CMSUtil.getMimeType(this.fileExt) : this.mimeType;
	}
	

	public PublishMode getPublishMode() {

		return publishMode;
	}
	

	public String getCharset() {

		return charset;
	}

	public String getFileName() {
		return this.fileName;
	}
	
	

	public String getPublishTemppath() {
		return CMSUtil.getPath(CMSUtil.getWebPublishTempPath(),this.getTempPublishUID());
	}

	public DriverConfiguration getDriverConfiguration() {
		return CMSUtil.getCMSDriverConfiguration();
	}

	public int[] getDistributeManners() {
		return this.distributeManners;
	}

	/**
	 * 获取支持的发布分发器
	 * 
	 * @return
	 */
	public List getEnabledDistributes() {
		List distributes = new ArrayList();
		if (distributeManners == null) {
				distributes.add(DistributeManager
						.getDistribute(DistributeManager.HTML_DISTRIBUTE));
		} else {

			for (int i = 0; i < distributeManners.length; i++) {
				distributes.add(DistributeManager
						.getDistribute(distributeManners[i]));
			}
		}
		return distributes;
	}

	/**
	 * 获取发布文件的绝对路径
	 * 
	 * @return
	 */
	public String getPublishAbsolutePath() {
//		if (this.isRootContext())
//			return this.getPublishRootPath() + "/" + this.getPublishPath();
//		else
//			return this.parentContext.getPublishAbsolutePath() + "/"
//					+ this.getPublishPath();
		return this.getPublishRootPath() +  this.getPublishPath();
	}

	public String getPublishRootPath() {
		if(sitePublishRootPath == null)
		{
			if(this.getLocalPublishDestination()!=null && !this.getLocalPublishDestination().equals("")) 
			{
				sitePublishRootPath = this.getLocalPublishDestination();
				sitePublishContext = getDomain();
			}
			else
			{
				sitePublishRootPath = CMSUtil.getWebPublishRootPath() ;
				if(sitePublishRootPath == null || sitePublishRootPath.trim().length() != 0)
				{
					
					sitePublishContext = this.getRequestContext().getRequest().getContextPath() + "/sitepublish/" + this.getSiteDir();
					this.sitePublishRootPath = CMSUtil.getPath(CMSUtil.getAppRootPath(),"sitepublish/" + this.getSiteDir());
					
				}
			}
			
		}
		return sitePublishRootPath;
	}

	public String getPreviewContextPath()
	{
		if(this.sitePreviewContextPath == null)
			sitePreviewContextPath = "sitepreview/" + this.getSiteDir();
		return sitePreviewContextPath;
	}
	
	public String getPreviewRootPath()
	{
		if(this.sitePreviewRootPath == null)
			this.sitePreviewRootPath = CMSUtil.getPath(CMSUtil.getAppRootPath(),getPreviewContextPath());
		return sitePreviewRootPath;
	}

	public String getFileExt() {
		return this.fileExt == null ? CMSUtil.getFileExt(this.mimeType) : this.fileExt;
	}
	
	public String getCMSContextPath()
	{
		return CMSUtil.getCMSContextPath();
	}
	
	public String getWebSiteRootPath()
	{
		return CMSUtil.getWebSiteRootPath();
	}
	
	public String getWebProjectRootPath()
	{
		return CMSUtil.getWebProjectRootPath();
	}
	
	public String getWebTemplateRootPath()
	{
		return CMSUtil.getWebTemplateRootPath();
	}

	public String getRendRootPath() {
		if (rendRootPath == null) {
			
			this.rendRootPath = CMSUtil.getWebRendRootPath(getSiteDir());
		}
		return rendRootPath;
	}
	
	public String getProjectRootPath() {
		if (projectRootPath == null) {
			
			/**
			 * 站点的命名规则待定
			 */
			
			this.projectRootPath = CMSUtil.getWebProjectRootPath(getSiteDir());
		}
		return projectRootPath;
	}
	
	
	public String getTemplateRootPath() {
		if (templateRootPath == null) {
			
			this.templateRootPath = CMSUtil.getWebTemplateRootPath(getSiteDir());
		}
		return templateRootPath;
	}
	
	

	public Context getRootContext() {
		if(this.parentContext == null || this.parentContext == this)
			return this.rootContext;
		return parentContext.getRootContext();
	}
	
	
	public CMSDetailDataLoader getCMSDetailDataLoader()
	{
		return dataloader;
	}
	
	public void setCMSDetailDataLoader(CMSDetailDataLoader dataloader)
	{
		this.dataloader = dataloader;
	}
	
	public String getSiteDir()
	{
//		if(this.parentContext == null || this.parentContext == this)
//			return this.siteDir;
//		else
//			return this.parentContext.getSiteDir();
		return this.siteDir;
	}
	
	/**
	 * 获取图片库相对路径
	 * @return
	 */
	public String getAbsoluteImagesPath()
	{
		return this.getCMSContextPath() + this.getWebSiteRootPath() + this.getImagesRootPath();
	}
	
	/**
	 * 获取图片库相对路径
	 * @return
	 */
	public String getAbsoluteImagesRootPath()
	{
		return this.getCMSContextPath() + this.getWebSiteRootPath() + this.getImagesRootPath();
	}
	
	/**
	 * 获取图片库物理路径
	 * @return
	 */
	public String getRealImagesPath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteImagesPath();
	}
	
	/**
	 * 获取图片库物理路径
	 * @return
	 */
	public String getImagesRootPath()
	{
		
		if (this.imagesRootPath == null) {
			
			this.imagesRootPath = CMSUtil.getWebImagesRootPath(getSiteDir());
		}
		return imagesRootPath;
	}
	
	
	/**
	 * 获取样式库相对路径
	 * @return
	 */
	public String getAbsoluteStylePath()
	{
		return this.getCMSContextPath() + this.getWebSiteRootPath() + this.getStyleRootPath();
		
	}
	
	/**
	 * 获取样式库相对路径
	 * @return
	 */
	public String getAbsoluteStyleRootPath()
	{
		return this.getCMSContextPath() + this.getWebSiteRootPath() + this.getStyleRootPath();
		
	}
	
	/**
	 * 获取样式库物理路径
	 * @return
	 */
	public String getRealStylePath()
	{
		return CMSUtil.getAppRootPath() + this.getAbsoluteStylePath();
	}
	
	public String getStyleRootPath() {
		if (this.styleRootPath == null) {
			
			this.styleRootPath = CMSUtil.getWebStyleRootPath(getSiteDir());
		}
		return styleRootPath;
	}
	
//	public String getCurrentContextPath()
//	{
//		return this.currentContextPath;
//	}

	public boolean autoCorrectHtml() {
		return autoCorrectHtml;
	}

	public void setAutoCorrectHtml(boolean autoCorrectHtml) {
		this.autoCorrectHtml = autoCorrectHtml;
	}

	public String getSitePublishContext() {
		return sitePublishContext;
	}
	
//	public String getFullFileName()
//	{
////		this.fileExt = this.getFileExt();
////		if(this.fileExt != null && !fileExt.equals("") )
////		{
////			if(fileName.toLowerCase().endsWith("."+this.fileExt.toLowerCase()))
////				return fileName;
////			else
////				return fileName + "." + fileExt;
////		} 
//			
//			
//		return fileName;
//	}
	public String getPublishedPageUrl()
	{
		if(this.getPublishPath() != null && !this.getPublishPath().equals(""))
			return this.getSitePublishContext() + "/" + this.getPublishPath() + "/" +getFileName();
		else
			return this.getSitePublishContext() + "/" +getFileName();
	}
	
	public String getPreviewPageUrl()
	{
		if(this.getPublishPath() != null && !this.getPublishPath().equals(""))
			return this.getPreviewContextPath() + "/" + this.getPublishPath() + "/" +getFileName();
		else
			return this.getPreviewContextPath() + "/" +getFileName();
			
	}
	
	/**
	 * 获取当前任务的操作类型，如果是任务的起点直接
	 * 从publishObject中获取触发发布动作的操作类型
	 * 否则从parentContext中获取发布的发布类型
	 */
	public int getActionType()
	{
		
		if(this.parentContext == null || this.parentContext == this)
			return this.publishObject.getActionType();
		else
			return this.parentContext.getActionType();
	}
	
	
	public FTPConfig getFTPConfig()
	{
//		if(this.parentContext == null || this.parentContext == this)
			return FTPConfig;
//		else
//			return this.parentContext.getFTPConfig();
	}
	
	/**
	 * 是否远程发布
	 * @return
	 */
	public boolean[] getLocal2ndRemote()
	{
		return this.local2ndRemote;
	}
	
	public CMSRequestContext getRequestContext()
	{
		return this.publishObject.getRequestContext();
	}

	public String getTempFileName() {
		return tempFileName;
	}
	
	
	/**
	 * 获取上下文中的属性
	 * @param property
	 * @return
	 */
	public String getProperty(String key)
	{
		return this.properties.getProperty(key);
	}
	
	/**
	 * 获取上下文中所有的属性
	 * @return
	 */
	public Properties getProperties()
	{
		return (Properties)Collections.unmodifiableMap(this.properties);
	}
	/**
	 * 设置上下文属性
	 * @param name
	 * @param value
	 */
	public void setProperty(String name,String value)
	{
		this.properties.setProperty(name,value);
	}

	public CmsLinkTable getDynamicPageLinkTable() {
		return dynamicPageLinkTable;
	}

	

	public CmsLinkTable getStaticPageLinkTable() {
		return staticPageLinkTable;
	}



	public CMSTemplateLinkTable getTemplateLinkTable() {
		return templateLinkTable;
	}

	public String getLocalPublishDestination()
	{
		return this.localPublishDestination;
//		if(this.parentContext == null || this.parentContext == this)
//			return this.localPublishDestination;
//		else
//			return this.parentContext.getLocalPublishDestination();
	}
	
	public String getDomain()
	{
//		if(this.parentContext == null || this.parentContext == this)
//			return domain;
//		else
//			return this.parentContext.getDomain();
		return this.domain;
	}
	
	/**
	 * 获取站点信息
	 * @return
	 */
	public Site getSite()
	{
		return site;
//		return ((CMSContext)getRootContext().getParentContext()).getSite();
	}
	
	

	/**
	 * 获取发布引擎的临时目录的唯一标识，每个任务都有自己的唯一id，防止并发发布的冲突
	 * @return
	 */
	public String getTempPublishUID()
	{
		if(this.parentContext == null || this.parentContext == this)
			return this.tempPublishUID;
		else
			return this.parentContext.getTempPublishUID();
	}
	
	/**
	 * 获取发布模式
	 * @param isdynamic
	 * @param isprotected
	 * @return
	 */
	public PublishMode getPublishMode(int isdynamic,int isprotected)
	{
		
		
		return CMSUtil.getPublishMode(isdynamic,isprotected);
		
	}
	
	/**
	 * 获取站点发布的临时目录，用来存放发布的临时文件以及发布的中间存放结果
	 * @return
	 */
	public String getSitePublishTemppath()
	{
//		return CMSUtil.getPath(this.getPublishTemppath(),"site" + this.getSiteID());
		return CMSUtil.getPath(this.getPublishTemppath(),this.site.getSecondName());
	}
	
	/**
	 * 获取发布的深度
	 * -1，0标识不受限制
	 * 其他情况每发布一个层次，depth 自动加1，
	 * 达到最深允许的深度时，如果还存在下级子任务，不执行直接返回
	 */
	public int getCurrentPublishDepth()
	{
		return this.currentPublishDepth;
	}
	
	/**
	 * 设置发布的深度
	 * -1，0标识不受限制
	 * 其他情况每发布一个层次，depth 自动加1，
	 * 达到最深允许的深度时，如果还存在下级子任务，不执行直接返回
	 */
	public void setCurrentPublishDepth(int currentdepth)
	{
		this.currentPublishDepth = currentdepth;
	}
	
	/**
	 * 获取发布的最大允许深度
	 * -1，0标识不受限制
	 * 其他情况每发布一个层次，depth 自动加1，
	 * 达到最深允许的深度时，如果还存在下级子任务，不执行直接返回
	 */
	public int getMaxPublishDepth()
	{
		return this.maxPublishDepth;
	}
	
	/**
	 * 设置发布的最大深度
	 * @param maxPublishDepth
	 */
	public void setMaxPublishDepth(int maxPublishDepth)
	{
		this.maxPublishDepth = maxPublishDepth;
	}
	
	public boolean isRecursive()
	{
		return this.publishObject.isRecursive();
	}
	
	/**
	 * 获取和当前发布任务相关的发布对象，然后递归发布这些发布对象
	 * @return
	 */
	public Set getRecursivePubObjects()
	{
		return this.recursivePubObjects;
	}
	
	/**
	 * 添加一个递归发布对象
	 */
	public void addPubObjectReference(PubObjectReference pubObjectReference){
		if(!this.isRootContext())
		{
			this.parentContext.addPubObjectReference(pubObjectReference);
			
			
		}
		else
		{
			this.recursivePubObjects.add(pubObjectReference);
		}
	}
	

	/**
	 * 跟踪记录当前发布任务所对应的发布对象和发布对象引用的元素之间的关系
	 * @param refobj 引用对象元素标识
	 * @param reftype 引用元素类型，取值范围如下：
	 *    文档
	 *	public static final int REFOBJECTTYPE_DOCUMENT = 3;
	 *	频道
	 *	public static final int REFOBJECTTYPE_CHANNEL = 1;
	 *	
	 * 页面类型
	 *	 
	 *	public static final int REFOBJECTTYPE_PAGE = 2;
	 *	
	 * 站点类型		 
     *	public static final int REFOBJECTTYPE_SITE = 0;
     *  @see com.frameworkset.platform.cms.driver.publish.RecursivePublishManager
	 * @param site 站点英文名称
	 */
	public void recordRecursivePubObj(String refobj, int reftype, String site)
	{
		
		if(publishObject != null)
			this.publishObject.recordRecursivePubObj(refobj, reftype, site);
	}
	
	/**
	 * 添加当前发布任务的一个引用对象
	 * @param pubObjectReference
	 */
	public void addRefObject(PubObjectReference refObjectReference)
	{
		this.refObjects.add(refObjectReference);
	}
	

	/**
	 * 添加当前发布任务的引用对象集合
	 */
	public void addRefObjects(Set refObjectReferences)
	{
		this.refObjects.addAll(refObjectReferences);
		
	}
	
	/**
	 * 获取当前对象所引用的发布对象集
	 * @return
	 */
	public Set getRefObjects()
	{
		return this.refObjects;
	}
	
	public boolean enableRecursive()
	{
//		return this.publishObject.enableRecursive();
		if(this.parentContext != null && this.parentContext != this)
			return parentContext.enableRecursive();
		else
		{
			return this.enableRecursive;
		}
	}
	
	/**
	 *  抽象方法，具体的发布对象子类去实现
	 * 添加与当前任务相关的发布对象到上下文中addRecursivePubObjToContext,当所有的任务执行完成后
	 * 就执行发布过程中产生的递归任务。
	 */
	public void addRecursivePubObjToContext(Set temp_) 
	{
		if(this.isRootContext() || this.parentContext == this)
		{
			if(temp_ != null && temp_.size() > 0)
				this.recursivePubObjects.addAll(temp_);
		}
		else
		{
			this.parentContext.addRecursivePubObjToContext(temp_);
		}

	}
	
	/**
	 * 清除当前已经执行过的递归发布任务,包括页面，
	 * 频道首页 ，
	 * 站点首页，
	 * 文档细览页面
	 * @param siteid 发布对象所属的站点id
	 * @param pubobject 发布的对象标识,值的格式为pubobjcetid
	 * @param type 对象类型，0-站点，1-频道首页，2-页面,3-文档细览页面
	 */
	public void removeRecursivePubObject(String siteid,String pubobject,int type)
	{
		
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		Set set = new TreeSet();
		PubObjectReference object = new PubObjectReference();
		object.setPubobject("6");
		object.setPubobjectType(1);
		object.setPubSite("6");
		object.setReferenceObject("6");
		object.setRefobjectType(6);
		object.setRefSite("6");	
		set.add(object);
		System.out.println(set.size());
		
		PubObjectReference object1 = new PubObjectReference();
		object1.setPubobject("6");
		object1.setPubobjectType(1);
		object1.setPubSite("6");
		object1.setReferenceObject("6");
		object1.setRefobjectType(6);
		object1.setRefSite("6");
		set.add(object1);
		System.out.println(set.size());
		
		Set set1 = new TreeSet();
		PubObjectReference object2 = new PubObjectReference();
		object2.setPubobject("6");
		object2.setPubobjectType(1);
		object2.setPubSite("6");
		object2.setReferenceObject("6");
		object2.setRefobjectType(6);
		object2.setRefSite("6");	
		set.add(object2);
//		System.out.println(set.size());
		
		PubObjectReference object3 = new PubObjectReference();
		object3.setPubobject("6");
		object3.setPubobjectType(1);
		object3.setPubSite("6");
		object3.setReferenceObject("6");
		object3.setRefobjectType(6);
		object3.setRefSite("6");
		set.add(object3);
//		System.out.println(set.size());
		
		set.addAll(set1);
		System.out.println(set.size());
		
		 
		try {
			CMSDBFunction.recordpubrelation_proc("2", "2", 1,"2",1,"2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 设置是否需要记录引用元素
	 * @param b
	 */
	public void setNeedRecordRefObject(boolean b)
	{
		this.needRecordRefObject = b;
	}
	
	/**
	 * 判断是否需要记录引用元素
	 * @param b
	 */
	public boolean needRecordRefObject()
	{
		return this.needRecordRefObject;
	}
	
	/**
	 * 获取发布的请求相对路径，包含文件名称
	 * @return
	 */
	public String getRendURI()
	{
		return new StringBuffer(this.getRendPath()).append("/").append(this.fileName).toString();
	}
	
	public boolean forceStatusPublished()
	{
		if(this.isRootContext() || this.parentContext == this)
		{
			return this.getPublishObject().isForceStatusPublished();
		}
		else
		{
			return this.getParentContext().forceStatusPublished();
		}
	}
	
	
	/**
	 * 记录当前的正在发布的任务所属的站点
	 * 当由根任务触发的发布任务全部执行完毕后，
	 * 将发布过程中涉及的各个站点生成的网页分发到每个站点的对应的目的地
	 */
	public void recordSiteDistributeDestination(DistributeDestination distributeDestination)
	{
		if(this.isRootContext() || this.parentContext == this)
		{
			if(siteDistributeDestinations == null)
			{
				siteDistributeDestinations = new HashMap();
			}
			else if(siteDistributeDestinations.containsKey(distributeDestination))
			{
					return;
			}
			this.siteDistributeDestinations.put(distributeDestination,traceObject);
		}
		else
		{
			this.parentContext.recordSiteDistributeDestination(distributeDestination);
		}
	}
	
	public Set getSiteDistributeDestinations()
	{
		if(this.isRootContext() || this.parentContext == this)
		{
			if(this.siteDistributeDestinations == null)
				return null;
			Set siteDistributeDestinations_ = this.siteDistributeDestinations.keySet();
			return siteDistributeDestinations_;
		}
		else
			return this.parentContext.getSiteDistributeDestinations();
	}
	
	/**
	 * 记录发布过程中需要建立索引的对象
	 * @param indexObject
	 */
	public void addIndexObject(IndexObject indexObject)
	{
		if(!CMSUtil.enableIndex())
			return;
		if(this.isRootContext() || this.parentContext == this)
		{
			this.indexObjects.add(indexObject);
		}
		else
		{
			this.parentContext.addIndexObject(indexObject);
		}
	}
	
	/**
	 * 获取需要建立索引的对象集合
	 * @return Set<IndexObject>
	 * added 2007/10/13 PM 17:24
	 */
	public Set getIndexObjects()
	{
		if(this.isRootContext() || this.parentContext == this)
		{
			return indexObjects;
		}
		else
		{
			return this.parentContext.getIndexObjects();
		}
	}
	
	/**
	 * 释放发布过程中占用的资源
	 */
	public void destroy()
	{
		if(this.indexObjects != null)
		{
			indexObjects.clear();
			indexObjects = null;
		}
		if(this.recursivePubObjects != null)
		{
			recursivePubObjects.clear();
			recursivePubObjects = null;
		}
		
		if(this.refObjects != null)
		{
			this.refObjects.clear();
			this.refObjects = null;
		}
		
		if(this.staticPageLinkTable != null)
		{
			staticPageLinkTable.destroy();
			staticPageLinkTable = null;
			
		}
		if(this.templateLinkTable != null)
		{
			this.templateLinkTable.destroy();
			this.templateLinkTable = null;
		}
		
		if(this.dynamicPageLinkTable != null)
		{
			this.dynamicPageLinkTable.destroy() ;
			this.dynamicPageLinkTable = null;
		}
		if(this.contentLinkTable != null)
		{
			this.contentLinkTable.destroy() ;
			this.contentLinkTable = null;
		}
		if(this.contentOrigineTemplateLinkTable != null)
		{
			this.contentOrigineTemplateLinkTable.destroy() ;
			this.contentOrigineTemplateLinkTable = null;
		}
	}
	

	/**
	 * 判断是否需要强制发布link文件,对于已经发布过的连接是否需要强制重新发布
	 * 一般情况下是不需要的，但是一旦存在发布目录下的文件被破坏的情况下需要重新发布这些文件
	 * 暂时没有实现，需要重新实现
	 * @param link
	 * @return
	 */
	public boolean forcepublishLinks(CMSLink link)
	{
//		if(!this.isRootContext())
//			return parentContext.forcepublishLinks(link);
//		else
		{
			return isClearFileCache();
		}
	}

	public boolean isClearFileCache() {
		if(this.parentContext != null && this.parentContext != this)
		{
			return parentContext.isClearFileCache();
		}
		else
			return clearFileCache;
	}

	public void setClearFileCache(boolean clearFileCache) {
		this.clearFileCache = clearFileCache;
	}
	public void setEnableRecursive(boolean enableRecursive)
	{
		this.enableRecursive = enableRecursive;
	}

	public String getJspFileName() {
		return jspFileName;
	}
	
	protected CMSTemplateLinkTable contentOrigineTemplateLinkTable;

	@Override
	public CMSTemplateLinkTable getContentOrigineTemplateLinkTable() {
		// TODO Auto-generated method stub
		return contentOrigineTemplateLinkTable;
	}

	@Override
	/**
	 * 内容正文中解析出来的模板资源link到
	 */
	public void addContentOrigineTemplateLinkTable(
			CMSTemplateLinkTable linktable) {
		
		this.contentOrigineTemplateLinkTable.addLinks(linktable);
	}
	public CmsLinkTable getContentLinkTable() {
		return contentLinkTable;
	}

	public void addLink(CMSLink link) {
		this.contentLinkTable.addLink(link);

	}
}
