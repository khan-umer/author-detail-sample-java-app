package com.example.ezobook2.domain.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorModel {


	@SerializedName("author")
	@Expose
	private String author;

	@SerializedName("download_url")
	@Expose
	private String url;


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}