package com.example.quranapp.API.Model.RadioResponse;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RadiosResponse{

	@SerializedName("Radios")
	private List<RadiosItem> radios;

	public List<RadiosItem> getRadios(){
		return radios;
	}
}