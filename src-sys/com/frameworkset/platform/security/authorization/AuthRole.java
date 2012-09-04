package com.frameworkset.platform.security.authorization;

import java.io.Serializable;

/**
 * 
 * 描述: 角色信息，包括角色类型和角色名称
 * 
 * version: 1.0
 * 创建日期：2006-9-26
 * 公司：三一集团
 * @author biaoping.yin
 */
public class AuthRole implements Serializable{
	/**
	 * 角色帐号
	 */
	private String roleName;
	/**
	 * 角色id
	 */
	private String roleId;									//角色id
	
	/**
	 * 角色类型
	 */
	public static final String TYPE_ROLE = "role";
	
	/**
	 * 用户类型
	 */
	public static final String TYPE_USER = "user";
	/**
	 * 机构类型
	 */
	public static final String TYPE_ORG = "organization";			//新增类型：机构
	/**
	 * 角色类型
	 */
	private String roleType = TYPE_ROLE;
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("RoleName=" + roleName).append(",RoleType=" + roleType);
		return sb.toString();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null || !(obj instanceof AuthRole))
			return false;
		AuthRole role = (AuthRole)obj;
		
		return this.roleName.equals(role.getRoleName()) && this.roleType.equals(role.getRoleType());
	}
	
	public int hashCode()
	{
		return this.roleName.hashCode() + this.roleType.hashCode();
	}
	
	public static void main(String[] args)
	{
		System.out.println("admi".hashCode());
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
