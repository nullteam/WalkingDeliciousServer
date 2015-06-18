package com.wd.main;

import com.wd.util.SqlAssembling;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sqlString = "SELECT * FROM t_user WHERE 1=1";
		SqlAssembling assembling = new SqlAssembling(sqlString);
		assembling.addRestriction("name", "zhangsan").addRestriction("shuzi", 1).addRestriction("fudian", 3.12);
		System.out.println(assembling.toString());
	}

}
