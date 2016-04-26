package com.org.coop.canonical.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.org.coop.canonical.account.beans.GlMasterBean;
import com.org.coop.canonical.retail.beans.BankMasterBean;

public class UIModel {
	protected BranchBean branchBean;
	protected List<BranchBean> branches = new ArrayList<BranchBean>();
	protected List<BankMasterBean> bankMasters = new ArrayList<BankMasterBean>();
	protected List<GlMasterBean> glMasters = new ArrayList<GlMasterBean>();
	protected String errorMsg;
	protected String errorCode;
	private Date startDate;
	private Date endDate;
	protected int recordCount;
	protected int pageNo;
	protected int recordsPerPage;

	public BranchBean getBranchBean() {
		return branchBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<BranchBean> getBranches() {
		return branches;
	}

	public void setBranches(List<BranchBean> branches) {
		this.branches = branches;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<BankMasterBean> getBankMasters() {
		return bankMasters;
	}

	public void setBankMasters(List<BankMasterBean> bankMasters) {
		this.bankMasters = bankMasters;
	}

	public List<GlMasterBean> getGlMasters() {
		return glMasters;
	}

	public void setGlMasters(List<GlMasterBean> glMasters) {
		this.glMasters = glMasters;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
