package com.qa.tc.pagespayloads.admin_payload;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Employee_Delete_Pojo {
    private List<String> data = null;

    public static JSONObject getDeletePayload(List<String> listOfID) {
        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        for (String id : listOfID)
            array.add(id);
        data.put("data", array);
        return data;
    }

	@Override
	public String toString() {
		return "Employee_Delete_Pojo [data=" + data + "]";
	}

}