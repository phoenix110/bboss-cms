/**
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.  
 */
package com.frameworkset.platform.security.authorization.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.frameworkset.platform.config.ConfigManager;
import com.frameworkset.platform.security.AccessControl;
import com.frameworkset.util.StringUtil;

/**
 * <p>Title: PermissionTokenMap.java</p>
 *
 * <p>Description: 权限相关联资源，比如菜单管理的url</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>bboss workgroup</p>
 * @Date 2013-8-13
 * @author biaoping.yin
 * @version 1.0
 */
public class PermissionTokenMap {
	public static final String RESOURCE_PARAMNAME="resource";
	/**
	 * Map<String,        PermissionTokenRegion>
	 *     resourceType(资源类型划分)       资源权限区域
	 */
	private Map<String,PermissionTokenRegion> resourcTokenMap;
	
	/**
	 * 与url相关的系统中所有资源操作许可
	 * 当进行权限检测时，首先检测url相关的所有资源许可中的任何一个有操作权限，则说明用户有权限访问相应的资源
	 */
	private Map<String,LinkPermissionToken> protectedURLLinkPermissions;
	
	/**
	 * 与url相关的系统中所有资源操作许可
	 * 当进行权限检测时，首先检测url相关的所有资源许可中的任何一个有操作权限，则说明用户有权限访问相应的资源
	 */
	private Map<String,LinkPermissionToken> nullURLLinkPermissions;
	
	/**
	 * 与url相关的系统中所有资源操作许可
	 * 当进行权限检测时，首先检测url相关的所有资源许可中的任何一个有操作权限，则说明用户有权限访问相应的资源
	 */
	private Map<String,LinkPermissionToken> unprotectedURLLinkPermissions;
	/**
	 * 受保护url缓存区大小，超过大小时protectedURLLinkPermissions将被重置
	 */
	private int protectedURLLinkPermissionLimit = 100000;
	
	
	/**
	 * 不受保护url缓存区大小，超过大小时unprotectedURLLinkPermissions将被重置
	 */
	private int unprotectedURLLinkPermissionLimit = 200000;
	/**
	 *非配置url权限缓存区大小，超过大小时nullURLLinkPermissions将被重置
	 */
	private int nullURLLinkPermissionLimit = 200000;
	
	public PermissionTokenMap ()
	{
		resourcTokenMap = new HashMap<String,PermissionTokenRegion>();
		
		protectedURLLinkPermissions = new HashMap<String,LinkPermissionToken>();
		unprotectedURLLinkPermissions = new HashMap<String,LinkPermissionToken>();
		nullURLLinkPermissions = new HashMap<String,LinkPermissionToken>();
		
	}
	public PermissionTokenMap (int protectedURLLinkPermissionLimit,int unprotectedURLLinkPermissionLimit,int nullURLLinkPermissionLimit)
	{
		resourcTokenMap = new HashMap<String,PermissionTokenRegion>();
		
		protectedURLLinkPermissions = new HashMap<String,LinkPermissionToken>();
		unprotectedURLLinkPermissions = new HashMap<String,LinkPermissionToken>();
		this.protectedURLLinkPermissionLimit = protectedURLLinkPermissionLimit;
		this.unprotectedURLLinkPermissionLimit = unprotectedURLLinkPermissionLimit;
		this.nullURLLinkPermissionLimit = nullURLLinkPermissionLimit;
		
	}

	
	public void addPermissionToken(ResourceToken url,String region,PermissionToken token)
	{
		if(url == null)
			return;
		String resourctType = token.getResourceType();
		
		PermissionTokenRegion resourceTokens = this.resourcTokenMap.get(resourctType);
		if(resourceTokens == null)
		{
			resourceTokens = new PermissionTokenRegion();
			this.resourcTokenMap.put(resourctType, resourceTokens);
		}		
		resourceTokens.addPermissionToken(url, region,token);
		
		
		
	}
	public void addUnprotectedPermissionToken(ResourceToken url,String region,PermissionToken token)
	{
		String resourctType = token.getResourceType();
		
		PermissionTokenRegion resourceTokens = this.resourcTokenMap.get(resourctType);
		if(resourceTokens == null)
		{
			resourceTokens = new PermissionTokenRegion();
			this.resourcTokenMap.put(resourctType, resourceTokens);
		}
		resourceTokens.addUnprotectedPermissionToken(url, region,token);
		
		
		
	}
	
	public void resetPermissionByResourceType(String resourctType)
	{
		PermissionTokenRegion resourceTokens = this.resourcTokenMap.get(resourctType);
		if(resourceTokens != null)
		{
			resourceTokens.resetPermission( );
			clearAllURLLinkPermissions();
		}
	}
	
	public void resetPermissionByRegion(String resourctType,String region)
	{
		PermissionTokenRegion resourceTokens = this.resourcTokenMap.get(resourctType);
		if(resourceTokens != null)
		{
			resourceTokens.resetPermissionByRegion(  region);
			clearAllURLLinkPermissions();
		}
	}
	/**
	 * 如果有修改，需要重新加载每个url的相关权限许可，只要资源定义有调整就需要需要调用这个方法
	 */
	private void clearAllURLLinkPermissions()
	{
		synchronized(this.scanlock)//如果有修改，需要重新加载每个url的相关权限许可，只要资源定义有调整就需要需要调用这个方法
		{
			this.protectedURLLinkPermissions.clear();
			this.unprotectedURLLinkPermissions.clear();
			this.nullURLLinkPermissions.clear();
		}
	}
	
	
//	/**
//	 * 判断url资源是否有访问权限
//	 * @param url
//	 * @param resourceType 优先检测给定类型的包含url相关的资源权限
//	 * @return
//	 */
//	public boolean checkUrlPermission(String url,String resourceType)
//	{
//		if (!ConfigManager.getInstance().securityEnabled() )
//			return true;
//		PermissionTokenRegion resourceTokens = this.resourcTokenMap.get(resourceType);
//		if(resourceTokens == null)
//		{
//			if (BaseAccessManager._allowIfNoRequiredRoles(resourceType))
//				return true;
//			return true;
//		}
//		return resourceTokens.checkUrlPermission(url,resourceType);
//	}
	
	private LinkPermissionToken NULL_PERMISSIONTOKENS = new LinkPermissionToken();
	
	/**
	 * 判断url资源是否有访问权限
	 * @param url
	 * @param resourceType
	 * @return
	 */
	public boolean checkUrlPermission(String url)
	{
		if (!ConfigManager.getInstance().securityEnabled() )
			return true;
		if(AccessControl.getAccessControl().isAdmin())
			return true;
		LinkPermissionToken ptokens = scanUnprotectedUrlPermissionTokens(url);
		 AccessControl control = AccessControl.getAccessControl();
		if(ptokens != null && ptokens != NULL_PERMISSIONTOKENS)//不受保护资源，还需要判断配置的参数值是否匹配，如果参数值不匹配则继续进行有权限
		{
			
			 List<List<P>> paramConditions = ptokens.getParamConditions();
			 if(paramConditions == null || paramConditions.size() == 0)
				 return true;
			
			 for(List<P> params:paramConditions)
			 {
				int result = control.compareParams(params);//参数匹配或者，1表示参数全匹配，2表示没有设置参数					
				if(result == 1  )
				{
					return true;
				}
			 }
		}
		
		
		ptokens = scanUrlPermissionTokens(url);
		
		if(NULL_PERMISSIONTOKENS == ptokens)
			return true;
		boolean successed = false;
		for(PermissionToken token:ptokens.getPermissionTokens())
		{
			int result = control.compareParams(token);//参数匹配或者，1表示参数全匹配，2表示没有设置参数
			
			if((result == 1 || result == 2 ) )
			{
				if(token.useResourceAuthCode())
				{
					if(control.evalResource(token))
					{
						successed = true;						
						break;
						
						
					}
				}
				else if(control.checkPermission(token.getResourcedID(), token.getOperation(), token.getResourceType()))
				{
					successed = true;						
					break;
				}
			}
		}
		return successed;
		
		
				
	}
	private LinkPermissionToken _getToken(String url)
	{
		LinkPermissionToken ptokens = this.protectedURLLinkPermissions.get(url);
		if(ptokens != null)
		{
			return ptokens;
		}
		
		
		else
		{
			ptokens = this.nullURLLinkPermissions.get(url);
			return ptokens;
			
		}
		
	}
	
	private LinkPermissionToken _getUnprotedtedToken(String url)
	{
		
		
		return this.unprotectedURLLinkPermissions.get(url);
		
		
	}
	private Object scanlock = new Object();
	private Object unprotectedscanlock = new Object();
	private LinkPermissionToken scanUnprotectedUrlPermissionTokens(String url) {
		LinkPermissionToken ptokens = _getUnprotedtedToken(url);
		
		
		if(ptokens != null)
		{
			return ptokens;
		}	
		else
		{
			RID id = new RID(url);
			synchronized(unprotectedscanlock)
			{
				ptokens = _getUnprotedtedToken(url);
				if(ptokens != null)
				{
					return ptokens;
				}
				Iterator<Entry<String, PermissionTokenRegion>> resources = this.resourcTokenMap.entrySet().iterator();
				//首先进行url的未受保护资源扫描
				List<List<P>> allps = null;
				while(resources.hasNext())
				{
					Entry<String, PermissionTokenRegion> entry = resources.next();
					PermissionTokenRegion region = entry.getValue();
					List<List<P>> ps = region.isUnprotectedURL(id);
					if(ps != null)
					{
						if(allps == null)
							allps = new ArrayList<List<P>>();
						allps.addAll(ps);	
						
						
						
						break;
					}
						
				}
				if(allps != null)
				{
					ptokens = new LinkPermissionToken(url,true,null);
					if(unprotectedURLLinkPermissionLimit > 0 && this.unprotectedURLLinkPermissions.size() > this.unprotectedURLLinkPermissionLimit)
					{
						this.unprotectedURLLinkPermissions.clear();
					}
					ptokens.setParamConditions(allps);
					
					
				}
				else
				{
					ptokens = NULL_PERMISSIONTOKENS;
					
				}
				this.unprotectedURLLinkPermissions.put(url, ptokens);
				
			}
			return ptokens;
		}		
	}
	/**
	 * 扫描系统中和url相关的所有url资源
	 * @param url
	 */
	private LinkPermissionToken scanUrlPermissionTokens(String url) {
		LinkPermissionToken ptokens = _getToken(url);
		
		
		if(ptokens != null)
		{
			return ptokens;
		}	
		else
		{
			RID id = new RID(url);
			synchronized(scanlock)
			{
				ptokens = _getToken(url);
				if(ptokens != null)
				{
					return ptokens;
				}
				Iterator<Entry<String, PermissionTokenRegion>> resources = this.resourcTokenMap.entrySet().iterator();
				
				if(ptokens == null )//如果不是未受保护资源，则扫描所有区域的相关资源
				{
					resources = this.resourcTokenMap.entrySet().iterator();
					List<PermissionToken> tokes = new ArrayList<PermissionToken>();
					while(resources.hasNext())
					{
						Entry<String, PermissionTokenRegion> entry = resources.next();
						PermissionTokenRegion region = entry.getValue();
						List<PermissionToken> rtokens = region.getAllURLToken(id);
						if(rtokens != null && rtokens.size() > 0)
						{
							tokes.addAll(rtokens);
						}
							
					}
					if(tokes.size() == 0)
					{
						if(nullURLLinkPermissionLimit > 0 && this.nullURLLinkPermissions.size() > this.nullURLLinkPermissionLimit)
						{
							this.nullURLLinkPermissions.clear();
						}
						ptokens = NULL_PERMISSIONTOKENS;
						nullURLLinkPermissions.put(url, ptokens);
					}
					else
					{
						removeSamePermissions(tokes );
						ptokens = new LinkPermissionToken(url,false,tokes);
						if(protectedURLLinkPermissionLimit > 0 && this.protectedURLLinkPermissions.size() > this.protectedURLLinkPermissionLimit)
						{
							this.protectedURLLinkPermissions.clear();
						}
						protectedURLLinkPermissions.put(url, ptokens);
						
					}
					
				}
			}
			return ptokens;
		}		
	}
	/**
	 * 资源去重
	 * @param tokes
	 */
	private void removeSamePermissions(List<PermissionToken> tokes )
	{
		if(tokes.size() == 1)
			return ;
		
		List<PermissionToken> removePermissionTokens = new ArrayList<PermissionToken>();
		for(int i = 0; i < tokes.size(); i ++)
		{
			PermissionToken first = tokes.get(i);
			if(removePermissionTokens.contains(first))
				continue;
			for(int j = i + 1; j < tokes.size(); j ++)
			{	
				PermissionToken second = tokes.get(j);
				if(removePermissionTokens.contains(second))
					continue;
				
				if(first.getOperation().equals(second.getOperation()) 
						&& first.getResourcedID().equals(second.getResourcedID())
						&& first.getResourceType().equals(second.getResourceType()))
				{	
					if(issameParams(first,second))
						removePermissionTokens.add(second);
				}
			}
			
		}
		for(PermissionToken first:removePermissionTokens)
		{
			tokes.remove(first);
		}
		
		
	}
	/**
	 * 比较资源标识、资源操作、资源类型都相同的两个token的参数是否相同
	 * @param first
	 * @param second
	 * @return
	 */
	private boolean issameParams(PermissionToken first,PermissionToken second)
	{
		if(!first.hasParamCondition() && !second.hasParamCondition())
			return true;
		else if(!first.hasParamCondition() && second.hasParamCondition())
			return false;
		else if(first.hasParamCondition() && !second.hasParamCondition())
			return false;
		else
		{
			if(first.getParamConditions().size() != second.getParamConditions().size())
				return false;
			List<P> firstPS = first.getParamConditions();
			List<P> secondPS = second.getParamConditions();
			boolean contain = true;
			for(P fp:firstPS)
			{
				boolean in = false;
				for(P sp:secondPS)
				{
					if(fp.getName().equals(sp.getName()) && fp.getValue().equals(sp.getValue()))
					{
						in = true;
						break; 
					}
							
				}
				if(!in)
				{
					contain = false;
					break;
				}
				
			}
			return contain;
			
		}
	}
	public void destory() {
		if(nullURLLinkPermissions != null)
		{
			this.nullURLLinkPermissions.clear();
			nullURLLinkPermissions = null;
		}
		if(protectedURLLinkPermissions != null)
		{
			this.protectedURLLinkPermissions.clear();
			protectedURLLinkPermissions = null;
		}
		if(resourcTokenMap != null)
		{
			this.resourcTokenMap.clear();
			resourcTokenMap = null;
		}
		if(unprotectedURLLinkPermissions != null)
		{
			this.unprotectedURLLinkPermissions.clear();
			unprotectedURLLinkPermissions = null;
		}
		
	}
	
	public static  ResourceToken buildResourceToken(String url)
	{
		if(url == null || url.equals(""))
			return null;
		ResourceToken resourceToken = new ResourceToken();
		if(!url.startsWith("/"))
		{
			url = "/"+url;
		}
		int idx = url.indexOf("?");
		//解析url资源中对应的参数，作为权限判断的依据，除了识别url外，有权限外，还需要识别参数的值是否匹配，这样才能决定是否有访问权限
		String params = null;
		if(idx > 0)
		{
			params = url.substring(idx+1);
			List<P> ps = evalparamcondition(  params);
			resourceToken.setParamConditions(ps);
			url = url.substring(0,idx);
			 
		}
		
		idx = url.indexOf("{");
		if(idx > 0)
		{
			String temp = url;
			url = temp.substring(0,idx);
			String condition = temp.substring(idx);
			condition = condition.replace('|', ',');
			HashMap map = StringUtil.json2Object(condition,HashMap.class);
			if(map != null && map.size() > 0)
				resourceToken.setConditions(map);
			
		}
		resourceToken.setUrl(url);
		return resourceToken;
	}
	
	public static List<P> evalparamcondition(String params)
	{
		if(params == null || params.equals(""))
			return null;
		String[] ps = params.split("&");
		
		List<P> params_ = new ArrayList<P>();
		for(int i = 0; i < ps.length; i++)
		{
			String param = ps[i];
			String[] param_ = param.split("=");
			if(param_.length == 2)
			{
				if(!(param_[1].contains("#[") && param_[1].contains("]")))//忽略参数变量条件，否则无法正确识别权限
				{
					P p = new P();
					p.setName(param_[0]);
					p.setValue(param_[1]);
					params_.add(p);
					
				}
			}
			else
			{
				P p = new P();
				p.setName(param_[0]);
				p.setValue("");
				params_.add(p);
			}
		}
		if(params_.size() > 0)
			return params_;
		else 
			return null;
	}

}
