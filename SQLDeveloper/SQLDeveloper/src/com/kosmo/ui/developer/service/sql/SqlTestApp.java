package com.kosmo.ui.developer.service.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kosmo.ui.developer.service.domain.EmpVO;
import com.kosmo.ui.developer.service.domain.SqlUsageVO;
import com.kosmo.ui.developer.service.emp.EmpService;
import com.kosmo.ui.developer.service.emp.impl.EmpServiceImpl;
import com.kosmo.ui.developer.service.sql.impl.SqlUsageServiceImpl;

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
		sqlVo.setUseQuery("SELECT * FROM devemp WHERE deptno =11");
		System.out.println(sqlService.insert(sqlVo));
		
		
		/////////////////////////
		List<Map> list = sqlService.selectByQuery(sqlVo.getUseQuery());
		Map column = (Map)list.get(0);
		List columnList = (List)column.get("column");
		
		System.out.println("------------------------------");
		System.out.println(column.get("column"));
		System.out.println(list);
		for (int i = 1; i < list.size(); i++) {
			for (int j = 0; j < columnList.size(); j++) {
				System.out.println(list.get(i).get(columnList.get(j)));
				
			}
			
		}
		///////////////////////////////////////		

	}

}
