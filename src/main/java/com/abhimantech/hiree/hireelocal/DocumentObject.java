package com.abhimantech.hiree.hireelocal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentObject {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("resumeTxt")
	@Expose
	private String resumeTxt;
	
	
	
	public DocumentObject(Integer id, String email, String phone,
			String resumeTxt) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.resumeTxt = resumeTxt;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getResumeTxt() {
		return resumeTxt;
	}
	public void setResumeTxt(String resumeTxt) {
		this.resumeTxt = resumeTxt;
	}
	
	
}
