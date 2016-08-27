package com.sany.workflow.entity;

import java.sql.Timestamp;

import org.frameworkset.util.annotations.RequestParam;

public class Nodevariable {
	@RequestParam(name = "variable_id")
	private String id;
	@RequestParam(name = "variable_processKey")
	private String processKey;
	@RequestParam(name = "variable_node_key")
	private String node_key;
	@RequestParam(name = "variable_business_id")
	private String business_id;
	@RequestParam(name = "variable_business_type")
	private String business_type;
	@RequestParam(name = "variable_node_id")
	private String node_id;
	@RequestParam(name = "variable_param_name")
	private String param_name;
	@RequestParam(name = "variable_param_value")
	private String param_value;
	@RequestParam(name = "variable_param_type")
	private String param_type;
	@RequestParam(name = "variable_param_blob_value")
	private String param_blob_value;
	@RequestParam(name = "variable_param_clob_value")
	private String param_clob_value;
	@RequestParam(name = "variable_param_time_value")
	private Timestamp param_time_value;
	@RequestParam(name = "variable_param_row_number")
	private String param_row_number;
	@RequestParam(name = "variable_owner_type")
	private int owner_type;
	@RequestParam(name = "variable_param_des")
	private String param_des;
	@RequestParam(name = "variable_node_name")
	private String node_name;
	@RequestParam(name = "variable_is_edit_param")
	private int is_edit_param;
	@RequestParam(name = "variable_rowno_")
	private int rowno_;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	public String getParam_type() {
		return param_type;
	}

	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}

	public String getParam_blob_value() {
		return param_blob_value;
	}

	public void setParam_blob_value(String param_blob_value) {
		this.param_blob_value = param_blob_value;
	}

	public String getParam_clob_value() {
		return param_clob_value;
	}

	public void setParam_clob_value(String param_clob_value) {
		this.param_clob_value = param_clob_value;
	}

	public Timestamp getParam_time_value() {
		return param_time_value;
	}

	public void setParam_time_value(Timestamp param_time_value) {
		this.param_time_value = param_time_value;
	}

	public String getParam_row_number() {
		return param_row_number;
	}

	public void setParam_row_number(String param_row_number) {
		this.param_row_number = param_row_number;
	}

	public int getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(int owner_type) {
		this.owner_type = owner_type;
	}

	public String getNode_key() {
		return node_key;
	}

	public void setNode_key(String node_key) {
		this.node_key = node_key;
	}

	public String getParam_des() {
		return param_des;
	}

	public void setParam_des(String param_des) {
		this.param_des = param_des;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public int getIs_edit_param() {
		return is_edit_param;
	}

	public void setIs_edit_param(int is_edit_param) {
		this.is_edit_param = is_edit_param;
	}

	public int getRowno_() {
		return rowno_;
	}

	public void setRowno_(int rowno_) {
		this.rowno_ = rowno_;
	}

}
