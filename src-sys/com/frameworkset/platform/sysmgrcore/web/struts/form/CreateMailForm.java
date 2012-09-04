//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.frameworkset.platform.sysmgrcore.web.struts.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 04-07-2006
 * 
 * XDoclet definition:
 * 
 * @struts.form name="createMailForm"
 */
public class CreateMailForm implements Serializable{

	// --------------------------------------------------------- Instance
	// Variables

	/** UserType property */
	private String userType;

	/** MailPostfix property */
	private String mailPostfix;

	/**
	 * 用户ID数组
	 */
	private String[] userIds;

	/**
	 * 所选择的机构ID
	 */
	private String orgId;

	// --------------------------------------------------------- Methods

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
	}

	/**
	 * Returns the UserType.
	 * 
	 * @return String
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Set the UserType.
	 * 
	 * @param UserType
	 *            The UserType to set
	 */
	public void setUserType(String UserType) {
		this.userType = UserType;
	}

	/**
	 * Returns the MailPostfix.
	 * 
	 * @return String
	 */
	public String getMailPostfix() {
		return mailPostfix;
	}

	/**
	 * Set the MailPostfix.
	 * 
	 * @param MailPostfix
	 *            The MailPostfix to set
	 */
	public void setMailPostfix(String MailPostfix) {
		this.mailPostfix = MailPostfix;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
