package com.zkw.core.bean;

public class TcmapChina {
	private String province;
	private String city;
	private String administrativeCode;
	private String area;
	private String zipCodeSub;
	private String street;
	private String administrativeCodeSub;
	private String districtName;
	private String cityCode;
	private String zipCode;
	
	public TcmapChina(String province, String city, String administrativeCode,
			String area, String zipCodeSub, String street,
			String administrativeCodeSub, String districtName, String cityCode,
			String zipCode) {
		super();
		this.province = province;
		this.city = city;
		this.administrativeCode = administrativeCode;
		this.area = area;
		this.zipCodeSub = zipCodeSub;
		this.street = street;
		this.administrativeCodeSub = administrativeCodeSub;
		this.districtName = districtName;
		this.cityCode = cityCode;
		this.zipCode = zipCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdministrativeCode() {
		return administrativeCode;
	}
	public void setAdministrativeCode(String administrativeCode) {
		this.administrativeCode = administrativeCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getZipCodeSub() {
		return zipCodeSub;
	}
	public void setZipCodeSub(String zipCodeSub) {
		this.zipCodeSub = zipCodeSub;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAdministrativeCodeSub() {
		return administrativeCodeSub;
	}
	public void setAdministrativeCodeSub(String administrativeCodeSub) {
		this.administrativeCodeSub = administrativeCodeSub;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
