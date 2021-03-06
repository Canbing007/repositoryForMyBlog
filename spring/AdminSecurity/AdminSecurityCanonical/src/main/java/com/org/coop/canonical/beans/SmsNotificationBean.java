package com.org.coop.canonical.beans;

import java.util.Date;


public class SmsNotificationBean {
	protected int smsId;
	protected int notificationId;
	protected int customerId;
	protected String customerName;
	protected String customerType;
	protected String smsPurpose;
	protected int branchId;
	protected String branchName;
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected String mobileNo;
	protected String email;
	private String emailSubject;
	private String emailBody;
	private String emailSuccessfulInd;
	private String smsSuccessfulInd;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected String smsSubject;
	protected Date updateDate;
	protected String updateUser;
	
	public int getSmsId() {
		return smsId;
	}
	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}
	public String getSmsPurpose() {
		return smsPurpose;
	}
	public void setSmsPurpose(String smsPurpose) {
		this.smsPurpose = smsPurpose;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassingAuthInd() {
		return passingAuthInd;
	}
	public void setPassingAuthInd(String passingAuthInd) {
		this.passingAuthInd = passingAuthInd;
	}
	public String getPassingAuthRemark() {
		return passingAuthRemark;
	}
	public void setPassingAuthRemark(String passingAuthRemark) {
		this.passingAuthRemark = passingAuthRemark;
	}
	public String getSmsSubject() {
		return smsSubject;
	}
	public void setSmsSubject(String smsSubject) {
		this.smsSubject = smsSubject;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getEmailSuccessfulInd() {
		return emailSuccessfulInd;
	}
	public void setEmailSuccessfulInd(String emailSuccessfulInd) {
		this.emailSuccessfulInd = emailSuccessfulInd;
	}
	public String getSmsSuccessfulInd() {
		return smsSuccessfulInd;
	}
	public void setSmsSuccessfulInd(String smsSuccessfulInd) {
		this.smsSuccessfulInd = smsSuccessfulInd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + smsId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmsNotificationBean other = (SmsNotificationBean) obj;
		if (smsId != other.smsId)
			return false;
		return true;
	}
}
