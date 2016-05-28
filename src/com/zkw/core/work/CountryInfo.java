package com.zkw.core.work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zkw.core.bean.TcmapChina;
import com.zkw.core.biz.ConvertToBean;
import com.zkw.core.dbwork.DbWork;

public class CountryInfo {
	public static void readDetails(String catagory,String table,String insTab){
		int count = 707968;
		int start = 706173;
		int cnt = 1000;
		while (start < count){
			try{
				ResultSet rs = DbWork.readTable(catagory,table,start,cnt);
				while (rs.next()){
					ArrayList<Object> details = new ArrayList<Object>();
					String str = rs.getString("detail");
					System.out.println(str);
					ConvertToBean cb = new ConvertToBean(str);
					TcmapChina tc = cb .jsonConvert2TcmpChina();
					details.add(tc.getProvince());
					details.add(tc.getCity());
					details.add(tc.getAdministrativeCode());
					details.add(tc.getArea());
					details.add(tc.getZipCodeSub());
					details.add(tc.getStreet());
					details.add(tc.getAdministrativeCodeSub());
					details.add(tc.getDistrictName());
					details.add(tc.getCityCode());
					details.add(tc.getZipCode());
					DbWork.insertTable(catagory, insTab, details);
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			start = start + cnt;
		}
	}
	
	public static void main(String[] args){
		CountryInfo.readDetails("address", "tcmap_china", "china_country_info");
	}
	
}
