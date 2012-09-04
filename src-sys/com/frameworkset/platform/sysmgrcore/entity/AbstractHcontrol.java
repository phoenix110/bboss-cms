/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Wed Feb 08 15:34:56 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;

/**
 * A class that represents a row in the td_sm_hcontrol table. 
 * You can customize the behavior of this class by editing the class, {@link Hcontrol()}.
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized * by MyEclipse Hibernate tool integration.
 */
public abstract class AbstractHcontrol 
    implements Serializable
{
    /** The cached hash code value for this instance.  Settting to 0 triggers re-calculation. */
    private int hashValue = 0;

    /** The composite primary key value. */
    private String hcontrolId;

    /** The value of the simple hiedictId property. */
    private String hiedictId;

    /** The value of the simple hcontrolAction property. */
    private String hcontrolAction;

    /**
     * Simple constructor of AbstractHcontrol instances.
     */
    public AbstractHcontrol()
    {
    }

    /**
     * Constructor of AbstractHcontrol instances given a simple primary key.
     * @param hcontrolId
     */
    public AbstractHcontrol(String hcontrolId)
    {
        this.setHcontrolId(hcontrolId);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return String
     */
    public String getHcontrolId()
    {
        return hcontrolId;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param hcontrolId
     */
    public void setHcontrolId(String hcontrolId)
    {
        this.hashValue = 0;
        this.hcontrolId = hcontrolId;
    }

    /**
     * Return the value of the HIEDICT_ID column.
     * @return String
     */
    public String getHiedictId()
    {
        return this.hiedictId;
    }

    /**
     * Set the value of the HIEDICT_ID column.
     * @param hiedictId
     */
    public void setHiedictId(String hiedictId)
    {
        this.hiedictId = hiedictId;
    }

    /**
     * Return the value of the HCONTROL_ACTION column.
     * @return String
     */
    public String getHcontrolAction()
    {
        return this.hcontrolAction;
    }

    /**
     * Set the value of the HCONTROL_ACTION column.
     * @param hcontrolAction
     */
    public void setHcontrolAction(String hcontrolAction)
    {
        this.hcontrolAction = hcontrolAction;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the primary key values.
     * @param rhs
     * @return boolean
     */
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof Hcontrol))
            return false;
        Hcontrol that = (Hcontrol) rhs;
        if (this.getHcontrolId() != null && that.getHcontrolId() != null)
        {
            if (! this.getHcontrolId().equals(that.getHcontrolId()))
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
            int hcontrolIdValue = this.getHcontrolId() == null ? 0 : this.getHcontrolId().hashCode();
            result = result * 37 + hcontrolIdValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }
}
