package demo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.given;


public class Tests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testDemoGet() {
        Response res = given().when().get("/api/users/2");

        System.out.println(res.getBody().asString());

        // Validate - Remember to must-have keyword then()
        res.then().statusCode(200);
        res.then().body("data.id", equalTo(2));
        res.then().body("data.email", equalTo("janet.weaver@reqres.in"));

    }

    @Test
    public void testDemoPost() {
        JSONObject jObject = new JSONObject();
        jObject.put("name", "Alice");
        jObject.put("job", "QA Testing API");

        Response res =
            given().
                header("Content-type", "application/json").
                body(jObject.toString()).
            when().post("/api/users");

        System.out.println(res.getBody().asString());

        res.then().body("", equalTo(1));
    }

    @Test
    public void testDemoUpdate() {
        JSONObject jObject = new JSONObject();
        jObject.put("name", "Suzi");
        jObject.put("job", "QA Manual");

        Response res =
                given().
                    header("Content-type", "application/json").
                    body(jObject.toString()).
                when().put("/api/users/2");

        System.out.println(res.getBody().asString());
    }

    @Test
    public void testDemoDelete() {
        Response res =
            given().when().delete("/api/user/2");

        // Validate
        res.then().statusCode(204);
    }
}
