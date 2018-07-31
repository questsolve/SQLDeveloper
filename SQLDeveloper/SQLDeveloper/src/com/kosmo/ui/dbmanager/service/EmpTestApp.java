package com.kosmo.ui.dbmanager.service;

import com.kosmo.ui.dbmanager.service.impl.EmpServiceImpl;

public class EmpTestApp {

	public static void main(String[] args) {
		EmpService empService = new EmpServiceImpl();
		EmpVO temp = new EmpVO();
		temp.setEmpno(1);
		
		temp = empService.select(temp);
		
		System.out.println(temp);
		
		temp.setEmpName("����Ʈ��");
		temp.setPw("1122");
		temp.setJob("�������");
		temp.setDeptno(11);
		
		System.out.println(empService.insert(temp));
		
		temp.setJob("�������");
		System.out.println(empService.update(temp));
		
		
	}
	
	

}
