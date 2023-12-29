package crudOperations;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PetsCrudOperations {
	String id;

	@Test(priority = 1)
	public void addNewPet() throws IOException {

		RestAssured.baseURI = "http://localhost:9090/api/v3";

		String response = given().header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(
						Paths.get(System.getProperty("user.dir") + "\\src\\test\\java\\crudOperations\\addPet.json"))))
				.when().post("/pet").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response); // Parsing Json
		id = js.getString("id");
		System.out.println(id);
		String name = js.getString("category.name");
		System.out.println(name);
	}

	@Test(priority = 2)
	public void findPetById() {

		RestAssured.baseURI = "http://localhost:9090/api/v3";

		given().header("Content-Type", "application/json").when().get("/pet/" + id).then().log().all().assertThat()
				.statusCode(200);

	}

	@Test(priority = 2)
	public void findPetsByStatus() {
		// http://localhost:9090/api/v3/pet/findByStatus?status=available

		RestAssured.baseURI = "http://localhost:9090/api/v3";

		given().header("Content-Type", "application/json").queryParam("status", "sold").when().get("/pet/findByStatus")
				.then().log().all().assertThat().statusCode(200);
	}

	@Test(priority = 2)
	public void updatePet() {
		RestAssured.baseURI = "http://localhost:9090/api/v3";
		given().header("Content-Type", "application/json").queryParam("name", "Hamester")
				.queryParam("status", "not available").when().post("/pet/" + id).then().log().all().assertThat()
				.statusCode(200);
	}

	@Test(priority = 3)
	public void deletePet() {
		RestAssured.baseURI = "http://localhost:9090/api/v3";

		given().when().delete("/pet/" + id).then().log().all().assertThat().statusCode(200);

	}

}
