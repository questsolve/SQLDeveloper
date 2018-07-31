package com.kosmo.ui.dbmanager.service;

public class EmpVO {

	private int empno;
	private String pw;
	private int isWork;
	private String job;
	private int deptno;
	private int auth;
	private String deptName;
	private String empName;
	
	public EmpVO() {
		System.out.println(this.getClass().getName());
	}
	
	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public int getEmpno() {
		return empno;
	}


	public void setEmpno(int empno) {
		this.empno = empno;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public int getIsWork() {
		return isWork;
	}


	public void setIsWork(int isWork) {
		this.isWork = isWork;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public int getDeptno() {
		return deptno;
	}


	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "EmpVO [empno=" + empno + ", pw=" + pw + ", isWork=" + isWork + ", job=" + job + ", deptno=" + deptno
				+ ", auth=" + auth + ", deptName=" + deptName + ", empName=" + empName + "]";
	}

	
	
	
}
