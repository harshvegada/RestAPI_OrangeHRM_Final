package com.qa.tc.utill;

public class JSONAppender {

//	public static void main() throws Exception {
//		File file = new File("user_details_3101703.txt");
//		BufferedReader read = new BufferedReader(new FileReader(file));
//		String line;
//		JSONObject parentObject = new JSONObject();
//		JSONArray logicalTags = new JSONArray();
//
//		while ((line = read.readLine()) != null) {
//
//			org.json.JSONObject assetTypeId1 = new JSONObject();
//			assetTypeId1.put("engineeringUnitCategoryId", "");
//			assetTypeId1.put("id", line);
//			logicalTags.put(assetTypeId1);
//		}
//
//		parentObject.put("logicalTags", logicalTags);
//		parentObject.put("assetTypeId", "${assetTypeId}");
//		parentObject.put("name", "myTemplate${randomNumber}");
//
//		System.out.println(parentObject);
//	}
//
//	public static void main(double s[]) throws Exception {
//		JSONObject object = new JSONObject();
//		object.put("parentId", "${createCustomerId}");
//		object.put("dataSourceId", "${dataSourceId}");
//
//		File file = new File("user_details_3101703.txt");
//		BufferedReader read = new BufferedReader(new FileReader(file));
//		String line;
//
//		JSONArray tagMapping = new JSONArray();
//
//		while ((line = read.readLine()) != null) {
//			JSONObject inner_tagMapping = new JSONObject();
//			inner_tagMapping.put("logicalTagId", line);
//			inner_tagMapping.put("physicalTagId", line);
//			tagMapping.put(inner_tagMapping);
//		}
//		object.put("tagMapping", tagMapping);
//		System.out.println(object);
//	}
//
//	public static void main(byte[] args) {
//		org.json.JSONObject config = new org.json.JSONObject();
//		org.json.JSONObject object = new org.json.JSONObject();
//		config.put("path", new String("modbus://120.202.200.2/1"));
//		object.put("config", config);
//		System.out.println(object);
//	}
//
//	public static void main(String a[]) throws Exception {
//		File file = new File("user_details_3101703.txt");
//		BufferedReader read = new BufferedReader(new FileReader(file));
//		String line;
//
//		JSONObject object = new JSONObject();
//		object.put("name", "CPGatewayBrHome2 ModbusTemplate3");
//		object.put("parentId", "${createCustomerId}");
//		object.put("description", "something goes here");
//
//		JSONObject metadata = new JSONObject();
//		metadata.put("phone", "4405551212");
//		object.put("metadata", metadata);
//
//		JSONObject config = new JSONObject();
//		config.put("path", new String("modbus://120.202.200.2/1"));
//		object.put("config", config);
//
//		object.put("dataProviderId", "${dataProvidersId}");
//		object.put("name", "${dataSourceTemplateId}");
//
//		JSONArray physicalTags = new JSONArray();
//
//		while ((line = read.readLine()) != null) {
//			JSONObject physicalObject = new JSONObject();
//			physicalObject.put("id", line);
//			physicalObject.put("writable", false);
//			physicalObject.put("dataTtlSeconds", 0);
//			physicalObject.put("dataType", "string");
//			JSONObject config1 = new JSONObject();
//			JSONObject internalConfig = new JSONObject();
//			internalConfig.put("symbol", "tag1 -- " + line);
//			internalConfig.put("updateRateSec", 30);
//			config1.put("config", internalConfig);
//			physicalObject.put("config", config1);
//			physicalTags.put(physicalObject);
//		}
//		object.put("physicalTags", physicalTags);
//		System.out.println(object);
//
//		File responseFile = new File("response_Test_1.txt");
//		responseFile.delete();
//		FileWriter fileWriter = new FileWriter(responseFile);
//		fileWriter.write(object.toString());
//		fileWriter.flush();
//
//		read.close();
//		fileWriter.close();
//	}
}
