/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Wed Feb 08 15:38:04 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;

/**
 * A class representing a composite primary key id for the td_sm_usergroup
 * table.  This object should only be instantiated for use with instances 
 * of the Usergroup class.
 */
public class UsergroupKey
    implements Serializable
{
    /** The cached hash code value for this instance.  Settting to 0 triggers re-calculation. */
    private volatile int hashValue = 0;

    /** The value of the GROUP_ID component of this composite id. */
    private int groupId;

    /** The value of the USER_ID component of this composite id. */
    private Integer userId;

    /**
     * Simple constructor of UsergroupKey instances.
     */
    public UsergroupKey()
    {
    }

    /**
     * Returns the value of the groupId property.
     * @return String
     */
    public int getGroupId()
    {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * @param groupId
     */
    public void setGroupId(int groupId)
    {
        hashValue = 0;
        this.groupId = groupId;
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
     * Implementation of the equals comparison on the basis of equality of the id components.
     * @param rhs
     * @return boolean
     */
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof UsergroupKey))
            return false;
        UsergroupKey that = (UsergroupKey) rhs;
        if (this.getGroupId() != that.getGroupId() )
        {
           return false;
        }
         if (this.getUserId() != that.getUserId())
        {
            return false;
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
           
            result = result * 37 + this.groupId;
            int userIdValue = this.getUserId() == null ? 0 : this.getUserId().hashCode();
            result = result * 37 + userIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
