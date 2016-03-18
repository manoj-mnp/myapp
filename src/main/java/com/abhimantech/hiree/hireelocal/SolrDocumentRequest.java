package com.abhimantech.hiree.hireelocal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SolrDocumentRequest {
	@SerializedName("add")
	@Expose
	private DocumentAddRequest doc;

	public SolrDocumentRequest(DocumentAddRequest doc) {
		super();
		this.doc = doc;
	}
	
}
