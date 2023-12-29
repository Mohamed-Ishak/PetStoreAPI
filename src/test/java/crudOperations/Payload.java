package crudOperations;

import org.testng.annotations.DataProvider;

public class Payload {

	// Another way to store static json file 
	public static String addPet(String petName ,String status) {
		String payload ="{\r\n"
				+ "  \"id\": 10,\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \""+petName+"\"\r\n"
				+ "  },\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \""+status+"\"\r\n"
				+ "}";
		
		return payload ;
	}
	
	@DataProvider(name = "PetsName")
	public  Object[][] getPetsByStatus(){
		return new Object[][] {{"name","rabbits"},{"name","rats"},{"name","Hamesters"}};
	}
	
//	@DataProvider
//	public void getData() {
//		new Object[][] {}
//	}
}
