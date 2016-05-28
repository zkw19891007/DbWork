package com.zkw.core.dbwork;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DbWork {
	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			conn = DriverManager.getConnection(url);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static String readColumns(String catalog,String tableName) throws SQLException{
		Connection conn = getConnection();
		DatabaseMetaData rsmeta = conn.getMetaData();
		ResultSet metaRs = rsmeta.getColumns(catalog, null, tableName, null);
		StringBuffer columnsStr = new StringBuffer();
		while (metaRs.next()){
			if (!metaRs.isLast()){
				columnsStr.append(metaRs.getString("COLUMN_NAME")+",");
			}else{
				columnsStr.append(metaRs.getString("COLUMN_NAME"));
			}
		}
		conn.close();
		return columnsStr.toString();
	}
	public static int readColumnsCnt(String catalog,String tableName) throws SQLException{
		String columns = readColumns(catalog, tableName);
		return columns.split(",").length;
	}
	public static ResultSet readTable(String catalog,String tableName,int start,int cnt) throws SQLException{
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		String columnsStr = readColumns(catalog, tableName);
		String rdSql = "select "+columnsStr+" from "+tableName +" limit "+start+","+cnt+"";
//		System.out.println(rdSql);
		ResultSet rs = st.executeQuery(rdSql);
		return rs;
	}
	
	public synchronized static boolean insertTable(String catalog,String tableName,ArrayList objs) throws SQLException{
		Connection conn = getConnection();
		String columnStr = readColumns(catalog, tableName);
		Statement st = conn.createStatement();
		StringBuffer valuesBuffer = new StringBuffer();
		for (int i=0;i<objs.size();i++){
			if (i == objs.size()-1){
				valuesBuffer.append("'"+objs.get(i)+"'");
			}else{
				valuesBuffer.append("'"+objs.get(i)+"'"+",");
			}
		}
		String insertSql = "insert into " + tableName 
				+ " (" + columnStr + ")" + " values("
				+ valuesBuffer.toString() +")";
		System.out.println(insertSql);
		boolean isSuccessed = st.execute(insertSql);
		return isSuccessed;
	}
	public synchronized static boolean updateTable(String catalog,String tableName,HashMap<String,String> kvs) throws SQLException{
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		StringBuffer updateSql = new StringBuffer("update " + tableName + " set ");
		Iterator<Entry<String,String>> iter = kvs.entrySet().iterator();
		while (iter.hasNext()){
			Entry<String,String> entry = iter.next();
			updateSql.append(entry.getKey()+"='"+entry.getValue());
		}
		return true;
	}
	public static boolean truncateTable(String tableName) throws SQLException{
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		String truncateSql = "truncate table "+tableName;
		return st.execute(truncateSql);
	}
	public static void main(String[] args) throws SQLException{
		ResultSet rs = readTable("ddtdata","t_ddt_dim_city_new",0,10000);
		int columnCnt = readColumnsCnt("ddtdata","t_ddt_dim_city_new");
		while (rs.next()){
			ArrayList inArray = new ArrayList<>();
			for (int i=1;i<=columnCnt;i++){
				inArray.add(rs.getString(i));
			}
			insertTable("ddtdata","t_ddt_dim_city_new2", inArray);
		}
	}
}
