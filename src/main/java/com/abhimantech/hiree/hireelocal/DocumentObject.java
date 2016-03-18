package com.abhimantech.hiree.hireelocal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentObject {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("resumeTxt")
	@Expose
	private String resumeTxt;
	@SerializedName("checksum")
	@Expose
	private String checksum;
	@SerializedName("filename")
	@Expose
	private String filename;
	
	
	public DocumentObject(String id, String email, String phone, String resumeTxt, String checksum, String filename) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.resumeTxt = resumeTxt;
		this.checksum = checksum;
		this.filename = filename;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
