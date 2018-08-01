package com.kosmo.ui.dbmanager.service.sql;

import java.util.List;

import com.kosmo.ui.dbmanager.service.emp.EmpService;
import com.kosmo.ui.dbmanager.service.emp.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.impl.EmpServiceImpl;
import com.kosmo.ui.dbmanager.service.sql.impl.SqlUsageServiceImpl;

public class SqlTestApp {

	public static void main(String[] args) {
		SqlUsageService sqlService = new SqlUsageServiceImpl();
		EmpService empService = new EmpServiceImpl();
		SqlUsageVO sqlVo = new SqlUsageVO();
		
		EmpVO empVo = new EmpVO();
		empVo.setEmpno(7);
		empVo = empService.select(empVo);
		System.out.println(empVo);
		
		sqlVo.setEmp(empVo);
		sqlVo.setUseQuery("SELECT * FROM devemp");
		System.out.println(sqlService.insert(sqlVo));
		
		List list = sqlService.selectByQuery(sqlVo.getUseQuery());
		
		
		
				

	}

}
