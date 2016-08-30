/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Wed Feb 08 15:37:24 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;

/**
 * A class representing a composite primary key id for the td_sm_roleresop
 * table. This object should only be instantiated for use with instances of the
 * Roleresop class.
 */
public class OpergpryKey implements Serializable {
	/**
	 * The cached hash code value for this instance. Settting to 0 triggers
	 * re-calculation.
	 */
	private volatile int hashValue = 0;

	/** The value of the OP_ID component of this composite id. */
	private String opId;

	/** The value of the RESTYPE_ID component of this composite id. */
	private String restypeId;

	/** The value of the GROUP_ID component of this composite id. */
	private String groupId;

	/**
	 * Simple constructor of RoleresopKey instances.
	 */
	public OpergpryKey() {
	}

	/**
	 * Returns the value of the opId property.
	 * 
	 * @return String
	 */
	public String getOpId() {
		return opId;
	}

	/**
	 * Sets the value of the opId property.
	 * 
	 * @param opId
	 */
	public void setOpId(String opId) {
		hashValue = 0;
		this.opId = opId;
	}

	/**
	 * Returns the value of the resId property.
	 * 
	 * @return String
	 */
	public String getRestypeId() {
		return restypeId;
	}

	/**
	 * Sets the value of the resId property.
	 * 
	 * @param resId
	 */
	public void setRestypeId(String restypeId) {
		hashValue = 0;
		this.restypeId = restypeId;
	}

	/**
	 * Returns the value of the roleId property.
	 * 
	 * @return String
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Sets the value of the roleId property.
	 * 
	 * @param roleId
	 */
	public void setGroupId(String groupId) {
		hashValue = 0;
		this.groupId = groupId;
	}

	/**
	 * Implementation of the equals comparison on the basis of equality of the
	 * id components.
	 * 
	 * @param rhs
	 * @return boolean
	 */
	public boolean equals(Object rhs) {
		if (rhs == null)
			return false;
		if (!(rhs instanceof RoleresopKey))
			return false;
		OpergpryKey that = (OpergpryKey) rhs;
		if (this.getOpId() != null && that.getOpId() != null) {
			if (!this.getOpId().equals(that.getOpId())) {
				return false;
			}
		}
		if (this.getRestypeId() != null && that.getRestypeId() != null) {
			if (!this.getRestypeId().equals(that.getRestypeId())) {
				return false;
			}
		}
		if (this.getGroupId() != null && that.getGroupId() != null) {
			if (!this.getGroupId().equals(that.getGroupId())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Implementation of the hashCode method conforming to the Bloch pattern
	 * with the exception of array properties (these are very unlikely primary
	 * key types).
	 * 
	 * @return int
	 */
	public int hashCode() {
		if (this.hashValue == 0) {
			int result = 17;
			int opIdValue = this.getOpId() == null ? 0 : this.getOpId()
					.hashCode();
			result = result * 37 + opIdValue;
			int restypeIdValue = this.getRestypeId() == null ? 0 : this
					.getRestypeId().hashCode();
			result = result * 37 + restypeIdValue;
			int groupIdValue = this.getGroupId() == null ? 0 : this
					.getGroupId().hashCode();
			result = result * 37 + groupIdValue;
			this.hashValue = result;
		}
		return this.hashValue;
	}
}