package com.ccshxt.lesson21;

import java.util.List;
import java.util.Map;

public class Test {

	
	public static void main(String[] args) {
		String sql = "select * from student where college = ?";//?占位符
		List<Map<String, String>> map = DBUtils.dao().find(sql, "计算机");
		
		for (Map<String, String> map2 : map) {
			System.out.println(map2.get("Code"));
			System.out.println(map2.get("Name"));
			System.out.println(map2.get("College"));
		}
	}
	
	
}
