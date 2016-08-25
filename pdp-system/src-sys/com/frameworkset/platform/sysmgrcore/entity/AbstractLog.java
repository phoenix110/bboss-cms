package com.frameworkset.platform.sysmgrcore.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * A class that represents a row in the td_sm_user table. You can customize the
 * behavior of this class by editing the class, {@link User()}. WARNING: DO NOT
 * EDIT THIS FILE. This is a generated file that is synchronized * by MyEclipse
 * Hibernate tool integration.
 */
public abstract class AbstractLog  implements Serializable {
	public AbstractLog()
	{
		
	}

	/** The composite primary key value. */
	private Integer logId;

	

	/** 
	 *	操作人 
	*/
	private String operUser ;
	
	/**
	 	操作
	 */
	private String oper ;

	/** 
	 * 访问来源
	 */
	private String visitorial ;

	/**
	 * 操作时间
	 */
	private Timestamp operTime ;

	/**
	 * 来源类型
	 */
	private String type ;
	
	private String startDate;
	
	private String endDate;

	/**
	 * 备用1   
	 */
	private String remark1;

	/**
	 * 备用2
	 */
	private String remark2;

	/**
	 * 备用3
	 */
	private String remark3;

	public AbstractLog(Integer logId) {

		this.setLogId(logId);
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Timestamp getOperTime() {
		return operTime;
	}

	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}

	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisitorial() {
		return visitorial;
	}

	public void setVisitorial(String visitorial) {
		this.visitorial = visitorial;
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
        if (!(rhs instanceof Log))
            return false;
        AbstractLog that = (Log) rhs;
        if (this.getLogId() != null && that.getLogId() != null) {
            if (!this.getLogId().equals(that.getLogId())) {
                return false;
            }
        }
        return true;
    }


	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	

}