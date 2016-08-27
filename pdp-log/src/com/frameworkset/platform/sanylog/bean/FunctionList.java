package com.frameworkset.platform.sanylog.bean;

public class FunctionList {
	
	  private String id;
	private String appId;
	private String appName;
	private String functionCode;
	private String functionName;
	private float estimateOper;
	private float estimateUser;
	private float timeSpent;
	private String error;
	
	public String equal(FunctionList bean){
		if(this.appId.equals(bean.getAppId())&&this.functionCode.equals(bean.getFunctionCode())){
			if(this.timeSpent==(bean.getTimeSpent())){
				return "equal";
			}else{
				bean.setTimeSpent(this.timeSpent)   ;
				return "timediff";
			}
		}else{
			return "notequal";
		}
		
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public float getEstimateOper() {
		return estimateOper;
	}
	public void setEstimateOper(float estimateOper) {
		this.estimateOper = estimateOper;
	}
	public float getEstimateUser() {
		return estimateUser;
	}
	public void setEstimateUser(float estimateUser) {
		this.estimateUser = estimateUser;
	}
	public float getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(float timeSpent) {
		this.timeSpent = timeSpent;
	}
	
}
