/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Wed Feb 08 15:38:11 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A class that represents a row in the td_sm_userjoborg table. You can
 * customize the behavior of this class by editing the class,
 * {@link Userjoborg()}. WARNING: DO NOT EDIT THIS FILE. This is a generated
 * file that is synchronized * by MyEclipse Hibernate tool integration.
 */
public abstract class AbstractUserjoborg implements Serializable {

	/** The simple primary key value. */
	private UserjoborgKey id = new UserjoborgKey();;

	/**
	 * 相同岗位用户顺序号
	 */
	private Integer sameJobUserSn;

	/**
	 * 岗位顺序号
	 */
	private Integer jobSn;

	/**
	 * 用户岗位机构实体所关联的用户
	 */
	private User user = null;

	/**
	 * 用户岗位机构实体所关联的岗位
	 */
	private Job job = null;

	/**
	 * 用户岗位机构实体所关联的机构
	 */
	private Organization org = null;
	
	//岗位授予时间
	private Timestamp startTime;
	
		//岗位状态信息
	private Integer  fettle;
	/**
	 * Simple constructor of AbstractUserjoborg instances.
	 */
	public AbstractUserjoborg() {
	}

	/**
	 * Constructor of AbstractUserjoborg instances given a composite primary
	 * key.
	 * 
	 * @param id
	 */
	public AbstractUserjoborg(UserjoborgKey id) {
		this.setId(id);
	}

	/**
	 * Return the composite id instance that identifies this object.
	 * 
	 * @return UserjoborgKey
	 */
	public UserjoborgKey getId() {
		return this.id;
	}

	/**
	 * Set the composite id instance that identifies this object.
	 * 
	 * @param id
	 */
	public void setId(UserjoborgKey id) {
		this.id = id;
	}

	/**
	 * Implementation of the equals comparison on the basis of equality of the
	 * primary key values.
	 * 
	 * @param rhs
	 * @return boolean
	 */
	public boolean equals(Object rhs) {
		if (rhs == null)
			return false;
		if (!(rhs instanceof Userjoborg))
			return false;
		Userjoborg that = (Userjoborg) rhs;
		if (this.getId() != null && that.getId() != null) {
			return (this.getId().equals(that.getId()));
		}
		return true;
	}


	/**
	 * @return 返回 job。
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job
	 *            要设置的 job。
	 */
	public void setJob(Job job) {
		this.job = job;

		if (id.getJobId() == null && job != null)
			id.setJobId(job.getJobId());
	}

	/**
	 * @return 返回 org。
	 */
	public Organization getOrg() {
		return org;
	}

	/**
	 * @param org
	 *            要设置的 org。
	 */
	public void setOrg(Organization org) {
		this.org = org;

		if (id.getOrgId() == null && org != null)
			id.setOrgId(org.getOrgId());
	}

	/**
	 * @return 返回 user。
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            要设置的 user。
	 */
	public void setUser(User user) {
		this.user = user;

		if (id.getUserId() == null && user != null)
			id.setUserId(user.getUserId());
	}

	public Integer getSameJobUserSn() {
		return sameJobUserSn;
	}

	public void setSameJobUserSn(Integer sameJobUserSn) {
		this.sameJobUserSn = sameJobUserSn;
	}

	public Integer getJobSn() {
		return jobSn;
	}

	public void setJobSn(Integer jobSn) {
		this.jobSn = jobSn;
	}

	public Integer getFettle() {
		return fettle;
	}

	public void setFettle(Integer fettle) {
		this.fettle = fettle;
	}

	
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	
}