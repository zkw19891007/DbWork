package com.zkw.core.biz;

import org.json.JSONException;
import org.json.JSONObject;

import com.zkw.core.bean.TcmapChina;

public class ConvertToBean {
	private String jsonStr;
	public ConvertToBean(String str){
		this.jsonStr = str;
	}
	public TcmapChina jsonConvert2TcmpChina(){
//		System.out.println("Starting*********************");
		JSONObject jb = new JSONObject(jsonStr);
//		System.out.println("stringed(************************");
		String[] keys = new String[]{"province","city","administrative_code",
			"area","zip_code_sub","street","administrative_code_sub",
			"district_name","city_code","zip_code"};
		String pro = "";
		String city = "";
		String admcode = "";
		String area = "";
		String zipSub = "";
		String street = "";
		String admSub = "";
		String dis = "";
		String cityCode = "";
		String zip = "";
		try{
			pro = jb.getString(keys[0]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			city = jb.getString(keys[1]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			admcode = jb.getString(keys[2]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			area = jb.getString(keys[3]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			zipSub = jb.getString(keys[4]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			street = jb.getString(keys[5]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			admSub = jb.getString(keys[6]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			dis = jb.getString(keys[7]);
			
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			
			cityCode = jb.getString(keys[8]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		try{
			zip = jb.getString(keys[9]);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return new TcmapChina(pro,city,admcode,area,zipSub,street,admSub,dis,cityCode,zip);
	}
	public static void main(String[] args){
		String json = "{\"province\": \"\\u6e56\\u5357\", \"city\": \"\\u6e58\\u897f\\u5dde\", \"administrative_code\": \"433122105205\", \"area\": \"\\u6cf8\\u6eaa\\u53bf\", \"zip_code_sub\": \"433122105\", \"street\": \"\\u6f6d\\u6eaa\\u9547\", \"administrative_code_sub\": \"220\", \"district_name\": \"\\u5c0f\\u9642\\u6d41\\u6751\", \"city_code\": \"0743\", \"zip_code\": \"433100\"}";
		ConvertToBean cb = new ConvertToBean(json);
		TcmapChina tc = cb.jsonConvert2TcmpChina();
		System.out.println(tc.getArea());
	}
}
