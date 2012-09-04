/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Wed Feb 08 15:38:11 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;

/**
 * A class representing a composite primary key id for the td_sm_userjoborg
 * table.  This object should only be instantiated for use with instances 
 * of the Userjoborg class.
 */
public class UserjoborgKey
    implements Serializable
{
    /** The cached hash code value for this instance.  Settting to 0 triggers re-calculation. */
    private volatile int hashValue = 0;

    /** The value of the USER_ID component of this composite id. */
    private Integer userId;

    /** The value of the JOB_ID component of this composite id. */
    private String jobId;

    /** The value of the ORG_ID component of this composite id. */
    private String orgId;

    /**
     * Simple constructor of UserjoborgKey instances.
     */
    public UserjoborgKey()
    {
    }

    /**
     * Returns the value of the userId property.
     * @return String
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * @param userId
     */
    public void setUserId(Integer userId)
    {
        hashValue = 0;
        this.userId = userId;
    }

    /**
     * Returns the value of the jobId property.
     * @return String
     */
    public String getJobId()
    {
        return jobId;
    }

    /**
     * Sets the value of the jobId property.
     * @param jobId
     */
    public void setJobId(String jobId)
    {
        hashValue = 0;
        this.jobId = jobId;
    }

    /**
     * Returns the value of the orgId property.
     * @return String
     */
    public String getOrgId()
    {
        return orgId;
    }

    /**
     * Sets the value of the orgId property.
     * @param orgId
     */
    public void setOrgId(String orgId)
    {
        hashValue = 0;
        this.orgId = orgId;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the id components.
     * @param rhs
     * @return boolean
     */
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof UserjoborgKey))
            return false;
        UserjoborgKey that = (UserjoborgKey) rhs;
        if (this.getUserId() != null && that.getUserId() != null)
        {
            if (! this.getUserId().equals(that.getUserId()))
            {
                return false;
            }
        }
        if (this.getJobId() != null && that.getJobId() != null)
        {
            if (! this.getJobId().equals(that.getJobId()))
            {
                return false;
            }
        }
        if (this.getOrgId() != null && that.getOrgId() != null)
        {
            if (! this.getOrgId().equals(that.getOrgId()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern with
     * the exception of array properties (these are very unlikely primary key types).
     * @return int
     */
    public int hashCode()
    {
        if (this.hashValue == 0)
        {
            int result = 17;
            int userIdValue = this.getUserId() == null ? 0 : this.getUserId().hashCode();
            result = result * 37 + userIdValue;
            int jobIdValue = this.getJobId() == null ? 0 : this.getJobId().hashCode();
            result = result * 37 + jobIdValue;
            int orgIdValue = this.getOrgId() == null ? 0 : this.getOrgId().hashCode();
            result = result * 37 + orgIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
