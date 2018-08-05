package com.kosmo.ui.developer.service.emp;

import com.kosmo.ui.developer.service.domain.EmpVO;
import com.kosmo.ui.developer.service.emp.impl.EmpServiceImpl;

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
		
		temp.setEmpno(8);
		
		System.out.println(empService.updateAuth(temp));
		
		System.out.println(empService.delete(temp));
		
		
		
	}
	
	

}
