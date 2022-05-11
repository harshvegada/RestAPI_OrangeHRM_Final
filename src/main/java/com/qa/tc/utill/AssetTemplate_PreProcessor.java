package com.qa.tc.utill;

public class AssetTemplate_PreProcessor {

//	public static void main(String[] args) throws Exception {
//		File file = new File("user_details_3101703.txt");
//		BufferedReader read = new BufferedReader(new FileReader(file));
//		String line;
//		JSONObject parentObject = new JSONObject();
//		JSONArray logicalTags = new JSONArray();
//
//		while ((line = read.readLine()) != null) {
//			JSONObject assetTypeId1 = new JSONObject();
//			assetTypeId1.put("engineeringUnitCategoryId", JSONObject.NULL);
//			assetTypeId1.put("id", line);
//			logicalTags.put(assetTypeId1);
//		}
//
//		parentObject.put("logicalTags", logicalTags);
//		parentObject.put("assetTypeId", "250994bf-303c-44a6-8095-8c35143d3f23");
//		parentObject.put("name", "myTemplate123");
//
//		System.out.println(parentObject);
//
//		File responseFile = new File("assetTempID.txt");
//		responseFile.delete();
//		FileWriter fileWriter = new FileWriter(responseFile);
//		fileWriter.write(parentObject.toString());
//		fileWriter.flush();
//	}
}
