

package com.frameworkset.platform.security.authentication;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.frameworkset.platform.security.authorization.AuthPrincipal;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 三一集团</p>
 * @author biaoping.yin
 * @version 1.0
 */
public abstract class ACLLoginModule implements LoginModule,Serializable {

    private static Logger log = Logger.getLogger(ACLLoginModule.class);
    protected String loginModuleName;

    /**注册表类型*/
    protected String registTable = "DB";
    /**全局subject*/
    private Subject subject;
    /**标识用户身份的凭证*/
    private Principal principal;
    /**获取用户名称和口令的回调函数*/
    private CallbackHandler callbackHandler;

    private CheckCallBack checkCallBack;
    private Credential credential;
    /**保存用户名称变量*/
    private String username;
    
    /**
     * 用户登录时要求的用户类型表
     * 如果登录接口指定了用户类型表，则只有用户的类型存在于用户类型表中才允许用户登录，否则不允许用户登录系统
     * 如果登录接口没有指定用户类型表，不需要检测用户的类型
     */
    protected String[] userTypes ;
    protected HttpServletRequest request;

    /**标识用户登录是否成功*/
    private boolean loginSuccess;
    /**登录口令*/
    private char[] password;
    
    protected boolean enableusertype(String userType)
    {
    	if(userTypes == null || userTypes.length == 0)
    		return true;
    	for(int i = 0; i < userTypes.length; i ++)
    	{
    		if(userTypes[i] != null && userTypes[i].equals(userType))
    			return true;
    	}
    	return false;
    }


    /**
     * Initialize this LoginModule.
     * @param subject the <code>Subject</code> to be authenticated. <p>
     * @param callbackHandler a <code>CallbackHandler</code> for
     * communicating with the end user (prompting for usernames and
     * passwords, for example). <p>
     * @param sharedState state shared with other configured LoginModules.
     * <p>
     * @param options options specified in the login
     * <code>Configuration</code> for this particular
     * <code>LoginModule</code>.
     * @todo Implement this javax.security.auth.spi.LoginModule method
     * @roseuid 43FD4F86005D
     */
    public void initialize(Subject subject, CallbackHandler callbackHandler) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.loginSuccess = false;
        this.checkCallBack = new CheckCallBack();
        username = null;
        userTypes = null;
        //this.loginModuleName = null;
        //this.registTable = "DB";
        clearPassword();
    }

    /**
     * Method to authenticate a <code>Subject</code> (phase 1).
     * @throws javax.security.auth.login.LoginException if the authentication fails
     * @return true if the authentication succeeded, or false if this
     * <code>LoginModule</code> should be ignored.
     * @throws javax.security.auth.login.LoginException
     * @todo Implement this javax.security.auth.spi.LoginModule method
     * @roseuid 43FD4F8600AC
     */
    public boolean login() throws LoginException {
        //
        // Since we need input from a user, we need a callback handler
        if (callbackHandler == null) {
            throw new LoginException("No CallbackHandler defined for LoginModule" + this.getClass().getName()  );

        }
        Callback[] callbacks = new Callback[4];
        callbacks[0] = new NameCallback("Username");
        callbacks[1] = new PasswordCallback("Password", false);
        callbacks[2] = new UserTypeCallBack("UserType");
         callbacks[3] = new RequestCallBack("Request");
        
        //
        // Call the callback handler to get the username
        try {
            log.debug( this.getClass().getName() + " Login");
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            userTypes = ((UserTypeCallBack)callbacks[2]).getUserTypes();
            this.request = ((RequestCallBack)callbacks[3]).getRequest();
            char[] temp = ((PasswordCallback) callbacks[1]).getPassword();
            password = new char[temp.length];

            System.arraycopy(temp, 0, password, 0, temp.length);
            ((PasswordCallback) callbacks[1]).clearPassword();
            if(request == null)
            	loginSuccess = check(username, new String(password),checkCallBack);
            else
            	loginSuccess = check(request,username, new String(password),checkCallBack);


            if (loginSuccess) {

                log.debug( "" + this.getClass().getName() + " login SUCCESS");
                return true;
            }
            else
            {
                log.debug( "" + this.getClass().getName() + " login failed");
            }
        } catch (IOException ioe) {

            log.error(ioe.getMessage(),ioe);
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce) {
            log.error(uce.getMessage(),uce);
            throw new LoginException(uce.toString());
        }
        throw new FailedLoginException();
    }

    /**
     * Method to commit the authentication process (phase 2).
     * @throws javax.security.auth.login.LoginException if the commit fails
     * @return true if this method succeeded, or false if this
     * <code>LoginModule</code> should be ignored.
     * @throws javax.security.auth.login.LoginException
     * @todo Implement this javax.security.auth.spi.LoginModule method
     * @roseuid 43FD4F86000F
     */
    public boolean commit() throws LoginException {
        if (loginSuccess == false) {
            log.debug( "" + this.getClass().getName() + " commit  FAIL");
            return false;
        }

        // If this login module succeeded too, then add the new principal
        // to the subject (if it does not already exist)
        principal = new AuthPrincipal(username, this.subject,loginModuleName);
        /**
         * 如果登录时设置了用户的类型则保存用户的类型到上下文变量中
         */
        if(this.userTypes != null && this.userTypes.length != 0)
        	checkCallBack.setUserAttribute("LOGINCONTEXT.USERTYPE",userTypes);
        credential = new Credential(checkCallBack,loginModuleName,subject);
        
        //往subject中添加用户身份
        if (!(subject.getPrincipals().contains(principal))) {
            subject.addAuthPrincipal(principal);
        }
        //往subject中添加用户身份令牌
        subject.addCredential(credential);
        log.debug( "" + this.getClass().getName() + " commit SUCCESS");
        return true;

    }

    /**
     * Method to abort the authentication process (phase 2).
     * @throws javax.security.auth.login.LoginException if the abort fails
     * @return true if this method succeeded, or false if this
     * <code>LoginModule</code> should be ignored.
     * @throws javax.security.auth.login.LoginException
     * @todo Implement this javax.security.auth.spi.LoginModule method
     * @roseuid 43FD4F85039B
     */
    public boolean abort() throws LoginException {

        if (loginSuccess == false) {
            log.debug( "" + this.getClass().getName() + " abort FAIL");
            principal = null;
            username = null;
            this.checkCallBack = null;
            this.credential = null;
            return false;
        }
        log.debug( "" + this.getClass().getName() + " abort SUCCESS");
        logout();
        return true;

    }

    /**
     * Method which logs out a <code>Subject</code>.
     * @throws javax.security.auth.login.LoginException if the logout fails
     * @return true if this method succeeded, or false if this
     * <code>LoginModule</code> should be ignored.
     * @throws javax.security.auth.login.LoginException
     * @todo Implement this javax.security.auth.spi.LoginModule method
     * @roseuid 43FD4F8600FA
     */
    public boolean logout() throws LoginException {
        if(principal != null)
        {
            subject.getPrincipals().remove(principal);
        }
        if(credential != null)
            subject.getCredentials().remove(this.credential);
        loginSuccess = false;
        username = null;
        principal = null;
        credential = null;
        checkCallBack = null;
        log.debug( "" + this.getClass().getName() + " logout SUCCESS");
        return true;

    }

    /**
     * 对用户名和口令进行校验
     * @param userName String 用户名称
     * @param password String 用户口令
     * @param checkCallBack CheckCallBack 用户通过本变量回填用户的基本信息
     * @return boolean
     * @throws LoginException
     */
    protected abstract boolean check(String userName, String password,CheckCallBack checkCallBack) throws
            LoginException;
    
    protected abstract boolean check(HttpServletRequest request,String userName, String password,CheckCallBack checkCallBack) throws
    LoginException;

    private void clearPassword() {
        if (password == null) {
            return;
        }
        for (int i = 0; i < password.length; i++) {
            password[i] = ' ';
        }
        password = null;
    }


    /**
     * @param args
     * @roseuid 43FE53BD032C
     */
    public static void main(String[] args) {

    }

    public void setLoginModuleName(String loginModuleName) {
        this.loginModuleName = loginModuleName;
    }

    public void setRegistTable(String registTable) {
        this.registTable = registTable;
    }
    /**
     * 重置指定的用户属性
     * @param userAttribute
     * @param value
     */
    public void resetUserAttribute(HttpServletRequest request,CheckCallBack checkCallBack,String userAttribute)
    {
    	
    }
    
    /**
     * 重置用户属性
     * @param userAttribute
     * @param value
     */
    public void resetUserAttributes(HttpServletRequest request,CheckCallBack checkCallBack)
    {
    	
    }
}
