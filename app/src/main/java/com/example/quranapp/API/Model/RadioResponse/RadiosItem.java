package com.example.quranapp.API.Model.RadioResponse;

import com.google.gson.annotations.SerializedName;

public class RadiosItem{

	@SerializedName("URL")
	private String uRL;

	@SerializedName("Name")
	private String name;

	public String getURL(){
		return uRL;
	}

	public String getName(){
		return name;
	}
}