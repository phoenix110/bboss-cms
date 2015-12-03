package com.frameworkset.platform.sysmgrcore.web.struts.action;

import java.io.Serializable;

import com.frameworkset.common.poolman.DBUtil;
import com.frameworkset.platform.sysmgrcore.manager.RoleManager;
import com.frameworkset.platform.sysmgrcore.manager.SecurityDatabase;
import com.frameworkset.platform.util.EventUtil;

/**
 * 资源管理＝＝资源操作授予＝＝角色操作的保存
 * 
 * @author 
 * @file RoleOperAction.java
 * @Created on: Apr 14, 2006
 */
public class RoleOperAction   implements Serializable {
	
	
	
	/** 资源管理中资源访问授权的页面。保存相关角色和操作的信息
	 * AJAX
	 * @param resId
	 * @param resTypeId
	 * @param opId
	 * @return
	 */
	public static String editRoleOper(String resId,String resTypeId,String opId,String checked,String title) {
	
		String[] tmp = opId.split(";");
		String roleid = tmp[0];
		String opid = tmp[1];
		DBUtil db = new DBUtil();
		if (tmp != null && tmp.length == 2) {
			try {
				RoleManager roleManager = SecurityDatabase.getRoleManager();
				if(checked != null && checked.equals("1")){
					roleManager.storeRoleresop(opid,resId,roleid,resTypeId,title,"role",true);
					try
					{
						Integer.parseInt(resId);
					}
					catch(Exception e)
					{
						//e.printStackTrace();
						return "success";
					}
					if("channel".equals(resTypeId)){
						//如果是频道，则递归给该频道的上级频道和所属站点及父站点授可见权限
						//递归给该频道的父频道授只读权限
						String str="SELECT t.channel_id FROM TD_cms_channel t START WITH "+
						" t.channel_id="+ resId +" CONNECT BY PRIOR t.parent_ID=t.channel_id";
						db.executeSelect(str);
						if(db.size()>1){
							for(int j=0;j<db.size();j++)
							{
								String parentid = db.getInt(j,"channel_id")+"";
								roleManager.storeRoleresop("write",parentid,roleid,resTypeId,title,"role",true);							
							}
						}
						//递归给该频道的所属站点及父站点授可见权限
						//查询所属站点ID
						str="select t.site_id from td_cms_channel t where t.channel_id="+resId;
						db.executeSelect(str);						 
						if(db.size()>0){
							String site_id=db.getInt(0,0)+"";
							//递归给该频道的所属站点及父站点授可见权限
							str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ site_id +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
							db.executeSelect(str);							
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,"site",name,"role",true);							
							}
							
						}
						
					}
					else if("site".equals(resTypeId)){
						//如果是站点，则递归给该站点的父站点授可见权限
						String str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ resId +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
						db.executeSelect(str);
						if(db.size()>1){
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,resTypeId,name,"role",true);							
							}
						}
					}
			
				}else if(checked != null && checked.equals("0")){
					//roleManager.deletePermissionOfRole(resId,resTypeId,roleid,"user");
					roleManager.deletePermissionOfRole(opid,resId,resTypeId,roleid,"role",true);
				}
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}else
			return "error";
	}
	/** 资源管理中资源访问授权的页面。保存相关角色和操作的信息
	 * AJAX
	 * @param resId
	 * @param resTypeId
	 * @param opId
	 * @return
	 */
	public static String editRoleOperOfUser(String resId,String resTypeId,String opId,String checked,String title) {
	
		String[] tmp = opId.split(";");
		String roleid = tmp[0];
		String opid = tmp[1];
		DBUtil db = new DBUtil();
		if (tmp != null && tmp.length == 2) {
			try {
				boolean sendevent = false;
				RoleManager roleManager = SecurityDatabase.getRoleManager();
				if(checked != null && checked.equals("1")){
					sendevent = true;
					roleManager.storeRoleresop(opid,resId,roleid,resTypeId,title,"user",false);
					if("channel".equals(resTypeId)){
						
						//如果是频道，则递归给该频道的上级频道和所属站点及父站点授可见权限
						//递归给该频道的父频道授只读权限
						String str="SELECT t.channel_id FROM TD_cms_channel t START WITH "+
						" t.channel_id="+ resId +" CONNECT BY PRIOR t.parent_ID=t.channel_id";
						db.executeSelect(str);
						if(db.size()>1){
							for(int j=0;j<db.size();j++)
							{
								String parentid = db.getInt(j,"channel_id")+"";
								roleManager.storeRoleresop("write",parentid,roleid,resTypeId,title,"user",false);							
							}
						}
						//递归给该频道的所属站点及父站点授可见权限
						//查询所属站点ID
						str="select t.site_id from td_cms_channel t where t.channel_id="+resId;
						db.executeSelect(str);						 
						if(db.size()>0){
							String site_id=db.getInt(0,0)+"";
//							递归给该频道的所属站点及父站点授可见权限
							str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ site_id +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
							db.executeSelect(str);							
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,"site",name,"user",false);							
							}
							
						}
						
					}
					else if("site".equals(resTypeId)){
						//如果是站点，则递归给该站点的父站点授可见权限
						String str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ resId +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
						db.executeSelect(str);
						if(db.size()>1){
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,resTypeId,name,"user",false);							
							}
						}
					}
			
				}else if(checked != null && checked.equals("0")){
					sendevent = true;
					//roleManager.deletePermissionOfRole(resId,resTypeId,roleid,"user");
					roleManager.deletePermissionOfRole(opid,resId,resTypeId,roleid,"user",false);
				}
				if(sendevent)
					EventUtil.sendRESOURCE_ROLE_INFO_CHANGEEvent();
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}else
			return "error";
	}
	
	public static String editRoleOperOfOrg(String resId,String resTypeId,String opId,String checked,String title) {
		
		String[] tmp = opId.split(";");
		String roleid = tmp[0];
		String opid = tmp[1];
		DBUtil db = new DBUtil();
		if (tmp != null && tmp.length == 2) {
			try {
				boolean sendevent = false;
				RoleManager roleManager = SecurityDatabase.getRoleManager();
				if(checked != null && checked.equals("1")){
					sendevent = true;
					roleManager.storeRoleresop(opid,resId,roleid,resTypeId,title,"organization",false);
					if("channel".equals(resTypeId)){
						//如果是频道，则递归给该频道的上级频道和所属站点及父站点授可见权限
						//递归给该频道的父频道授只读权限
						String str="SELECT t.channel_id FROM TD_cms_channel t START WITH "+
						" t.channel_id="+ resId +" CONNECT BY PRIOR t.parent_ID=t.channel_id";
						db.executeSelect(str);
						if(db.size()>1){
							
							for(int j=0;j<db.size();j++)
							{
								String parentid = db.getInt(j,"channel_id")+"";
								roleManager.storeRoleresop("write",parentid,roleid,resTypeId,title,"organization",false);							
							}
						}
						//递归给该频道的所属站点及父站点授可见权限
						//查询所属站点ID
						str="select t.site_id from td_cms_channel t where t.channel_id="+resId;
						db.executeSelect(str);						 
						if(db.size()>0){
							String site_id=db.getInt(0,0)+"";
							//递归给该频道的所属站点及父站点授可见权限
							str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ site_id +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
							db.executeSelect(str);							
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,"site",name,"organization",false);							
							}
							
						}
						
					}
					else if("site".equals(resTypeId)){
						//如果是站点，则递归给该站点的父站点授可见权限
						String str="SELECT t.site_id,t.name FROM TD_cms_site t START WITH "+
							 " t.site_id="+ resId +" CONNECT BY PRIOR t.mainsite_ID=t.site_id";
						db.executeSelect(str);
						if(db.size()>1){
							for(int j=0;j<db.size();j++)
							{
								String siteid = db.getInt(j,"site_id")+"";
								String name = db.getString(j,"name");
								roleManager.storeRoleresop("write",siteid,roleid,resTypeId,name,"organization",false);							
							}
						}
					}
			
				}else if(checked != null && checked.equals("0")){
					sendevent = true;
					roleManager.deletePermissionOfRole(opid,resId,resTypeId,roleid,"organization",false);
				}
				if(sendevent )
					EventUtil.sendRESOURCE_ROLE_INFO_CHANGEEvent();
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}else
			return "error";
	}
}
