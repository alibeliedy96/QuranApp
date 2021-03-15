package com.example.quranapp.API.Model.RecitersResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecitersResponse{

	@SerializedName("reciters")
	private List<RecitersItem> reciters;

	public List<RecitersItem> getReciters(){
		return reciters;
	}
}