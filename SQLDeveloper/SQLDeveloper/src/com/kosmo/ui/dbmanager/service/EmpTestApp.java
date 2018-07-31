package com.kosmo.ui.dbmanager.service;

import com.kosmo.ui.dbmanager.service.impl.EmpServiceImpl;

public class EmpTestApp {

	public static void main(String[] args) {
		EmpService empService = new EmpServiceImpl();
		EmpVO temp = new EmpVO();
		temp.setEmpno(1);
		
		temp = empService.select(temp);
		
		System.out.println(temp);
		
		temp.setEmpName("스마트폰");
		temp.setPw("1122");
		temp.setJob("전산계장");
		temp.setDeptno(11);
		
		System.out.println(empService.insert(temp));
		
		temp.setJob("전산과장");
		System.out.println(empService.update(temp));
		
		
	}
	
	

}
