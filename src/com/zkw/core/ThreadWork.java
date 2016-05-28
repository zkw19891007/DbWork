package com.zkw.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zkw.core.dbwork.DbWork;

public class ThreadWork extends Thread{
	private int threadLabel;
	private int columnCnt;
	private ResultSet rs;
	public ThreadWork(int threadLabel,ResultSet rs,int cnt) {
		super();
		this.threadLabel = threadLabel;
		this.rs = rs;
		this.columnCnt = cnt;
	}
	public void run(){
		try {
			while (rs.next()){
				ArrayList inArray = new ArrayList<>();
				for (int i=1;i<=columnCnt;i++){
					inArray.add(rs.getString(i));
				}
				System.out.println("thread"+threadLabel+" insert data started");
				DbWork.insertTable("ddtdata","t_ddt_dim_city_new2", inArray);
				System.out.println("thread"+threadLabel+" insert data successed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException{
		DbWork.truncateTable("t_ddt_dim_city_new2");
		int columns = DbWork.readColumnsCnt("ddtdata","t_ddt_dim_city_new");
		ResultSet[] rss = new ResultSet[10];
		for (int i=0;i<rss.length;i++){
			rss[i] = DbWork.readTable("ddtdata","t_ddt_dim_city_new",0,100000);
			ThreadWork tw = new ThreadWork(i, rss[i], columns);
			tw.start();
		}
	}
}