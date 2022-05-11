package com.qa.tc.utill;

import org.json.simple.JSONObject;

public class ObjectToJson {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject info = new JSONObject();
		info.put("name", "Harsh Vegada");
		info.put("company", "Globant");
		info.put("id", "410");
		System.out.println(info);
	}

}
