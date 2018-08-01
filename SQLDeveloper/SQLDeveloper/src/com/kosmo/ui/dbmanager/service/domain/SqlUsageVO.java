package com.kosmo.ui.dbmanager.service.domain;

public class SqlUsageVO {

	private EmpVO emp;
	private int usageNo;
	private String useQuery;
	private int downloadCount;
	
	public SqlUsageVO() {
		
	}
	
	public EmpVO getEmp() {
		return emp;
	}
	public void setEmp(EmpVO emp) {
		this.emp = emp;
	}
	public int getUsageNo() {
		return usageNo;
	}
	public void setUsageNo(int usageNo) {
		this.usageNo = usageNo;
	}
	public String getUseQuery() {
		return useQuery;
	}
	public void setUseQuery(String useQuery) {
		this.useQuery = useQuery;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	
}
