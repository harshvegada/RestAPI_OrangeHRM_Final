package com.qa.tc.pagespayloads.job_title_payload;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;


public class Job_Title_Delete_Pojo {

    @SuppressWarnings("unchecked")
    public static JSONObject getDeletePayload(List<String> listOfID) {
        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        for (String id : listOfID)
            array.add(id);
        data.put("data", array);
        return data;
    }
}
