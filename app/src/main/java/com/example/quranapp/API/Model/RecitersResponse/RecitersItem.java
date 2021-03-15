package com.example.quranapp.API.Model.RecitersResponse;

import com.google.gson.annotations.SerializedName;

public class RecitersItem{

	@SerializedName("Server")
	private String server;

	@SerializedName("letter")
	private String letter;

	@SerializedName("name")
	private String name;

	@SerializedName("count")
	private String count;

	@SerializedName("suras")
	private String suras;

	@SerializedName("rewaya")
	private String rewaya;

	@SerializedName("id")
	private String id;

	public String getServer(){
		return server;
	}

	public String getLetter(){
		return letter;
	}

	public String getName(){
		return name;
	}

	public String getCount(){
		return count;
	}

	public String getSuras(){
		return suras;
	}

	public String getRewaya(){
		return rewaya;
	}

	public String getId(){
		return id;
	}
}