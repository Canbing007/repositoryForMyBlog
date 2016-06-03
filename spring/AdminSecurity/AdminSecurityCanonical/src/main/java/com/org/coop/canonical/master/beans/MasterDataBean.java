package com.org.coop.canonical.master.beans;

import java.util.HashSet;
import java.util.Set;

public class MasterDataBean {
	private Set<CountryMasterBean> countries = new HashSet<CountryMasterBean>();
	private Set<ModuleMasterBean> modules = new HashSet<ModuleMasterBean>();
	private Set<SecurityQuestionsMasterBean> securityQuestions = new HashSet<SecurityQuestionsMasterBean>();
	private Set<DropdownMasterBean> dropdowns = new HashSet<DropdownMasterBean>();
	
	private String errorMsg;
	
	public Set<CountryMasterBean> getCountries() {
		return countries;
	}
	public void setCountries(Set<CountryMasterBean> countries) {
		this.countries = countries;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Set<ModuleMasterBean> getModules() {
		return modules;
	}
	public void setModules(Set<ModuleMasterBean> modules) {
		this.modules = modules;
	}
	public Set<SecurityQuestionsMasterBean> getSecurityQuestions() {
		return securityQuestions;
	}
	public void setSecurityQuestions(
			Set<SecurityQuestionsMasterBean> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	public Set<DropdownMasterBean> getDropdowns() {
		return dropdowns;
	}
	public void setDropdowns(Set<DropdownMasterBean> dropdowns) {
		this.dropdowns = dropdowns;
	}
}
