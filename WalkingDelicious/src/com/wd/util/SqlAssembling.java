package com.wd.util;

public class SqlAssembling {
	
	private String sqlString;
	private String tableName;
	//private final String SQL_ADD ="INSERT INTO ? VALUES (?)";
	//private final String SQL_DELETE ="DELETE * FROM ? WHERE 1=1";
	//private final String SQL_UPDATE = "UPDATE ? SET ? WHERE 1=1";
	//private final String SQL_SELECT = "SELECT * FROM ? WHERE 1=1";
	public SqlAssembling() {
		// TODO Auto-generated constructor stub
	}
	
	public SqlAssembling(String sqlString){
		this.sqlString=sqlString;
	}

	/**
	 * @return the sqlString
	 */
	public String getSqlString() {
		return sqlString;
	}

	/**
	 * @param sqlString the sqlString to set
	 */
	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public  SqlAssembling addRestriction(String fied,String value){
		if(fied!=null && value!=null && !fied.equals("")&&!value.equals("")){
			sqlString+=" AND "+fied+"=\'"+value+"\'";
		}
		return this;
	}
	
	public  SqlAssembling addRestriction(String fied,Integer value){
		if(fied!=null && value!=null && !fied.equals("")&&!value.equals("")){
			sqlString+=" AND "+fied+"="+value;
		}
		return this;
	} 
	public  SqlAssembling addRestriction(String fied,Float value){
		if(fied!=null && value!=null && !fied.equals("")&&!value.equals("")){
			sqlString+=" AND "+fied+"="+value;
		}
		return this;
	}
	public  SqlAssembling addRestriction(String field,Double value){
		if(field!=null && value!=null && !field.equals("")&&!value.equals("")){
			sqlString+=" AND "+field+"="+value;
		}
		return this;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return sqlString;
	}
}
