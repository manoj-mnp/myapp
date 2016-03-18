package com.abhimantech.hiree.hireelocal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentAddRequest {

	@SerializedName("doc")
	@Expose
	private DocumentObject doc;
	@SerializedName("boost")
	@Expose
	private Double boost;
	@SerializedName("overwrite")
	@Expose
	private Boolean overwrite;
	@SerializedName("commitWithin")
	@Expose
	private Integer commitWithin;
	public DocumentObject getDoc() {
		return doc;
	}
	public void setDoc(DocumentObject doc) {
		this.doc = doc;
	}
	public Double getBoost() {
		return boost;
	}
	public void setBoost(Double boost) {
		this.boost = boost;
	}
	public Boolean getOverwrite() {
		return overwrite;
	}
	public void setOverwrite(Boolean overwrite) {
		this.overwrite = overwrite;
	}
	public Integer getCommitWithin() {
		return commitWithin;
	}
	public void setCommitWithin(Integer commitWithin) {
		this.commitWithin = commitWithin;
	}
	public DocumentAddRequest(DocumentObject doc, Double boost,
			Boolean overwrite, Integer commitWithin) {
		super();
		this.doc = doc;
		this.boost = boost;
		this.overwrite = overwrite;
		this.commitWithin = commitWithin;
	}
	
	
	
}
