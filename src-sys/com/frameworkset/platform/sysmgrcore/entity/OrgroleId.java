package com.frameworkset.platform.sysmgrcore.entity;

/**
 * TdSmGrouproleId generated by MyEclipse - Hibernate Tools
 */

public class OrgroleId implements java.io.Serializable {

	// Fields

	private String orgId;

	private String roleId;

	// Constructors

	/** default constructor */
	public OrgroleId() {
	}

	/** full constructor */
	public OrgroleId(String orgId, String roleId) {
		this.orgId = orgId;
		this.roleId = roleId;
	}

	// Property accessors

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrgroleId))
			return false;
		
		OrgroleId castOther = (OrgroleId) other;

		return ((this.getOrgId() == castOther.getOrgId()) || (this
				.getOrgId() != null
				&& castOther.getOrgId() != null && this.getOrgId().equals(
				castOther.getOrgId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgId() == null ? 0 : this.getOrgId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}