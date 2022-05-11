package com.qa.tc.utill;

public class DataSource_Controller {

//	public static void main(String[] args) throws Exception {
//		File file = new File("user_details_3101703.txt");
//		BufferedReader read = new BufferedReader(new FileReader(file));
//		String line;
//
//		JSONObject object = new JSONObject();
//		object.put("name", "CPGatewayBrHome2 ModbusTemplate3");
//		object.put("parentId", "6d7446d3-3984-4713-a9b6-1c2c91b0a72d");
//		object.put("description", "something goes here");
//
//		JSONObject metadata = new JSONObject();
//		metadata.put("phone", "4405551212");
//		object.put("metadata", metadata);
//
//		JSONObject config = new JSONObject();
//		config.put("symbol", new String("modbus://120.202.200.2/1"));
//		object.put("config", config);
//
//		object.put("dataProviderId", "11cb6cb2-adaf-471a-a69c-03ce0548eee9");
//		object.put("dataSourceTemplateId", "f77a36a-a97d-49f2-aa91-67741c72522c");
//
//		JSONArray physicalTags = new JSONArray();
//
//		while ((line = read.readLine()) != null) {
//			JSONObject physicalObject = new JSONObject();
//			physicalObject.put("id", line);
//			physicalObject.put("writable", false);
//			physicalObject.put("dataTtlSeconds", 0);
//			physicalObject.put("dataType", "string");
//			JSONObject internalConfig = new JSONObject();
//			internalConfig.put("symbol", "tag2");
//			physicalObject.put("config", internalConfig);
//			physicalTags.put(physicalObject);
//		}
//		object.put("physicalTags", physicalTags);
//		System.out.println(object);
//
//		File responseFile = new File("d_user_details_${randomNumber}.txt");
//		responseFile.delete();
//		FileWriter fileWriter = new FileWriter(responseFile);
//		fileWriter.write(object.toString());
//		fileWriter.flush();
//
//		read.close();
//		fileWriter.close();
//	}
}
