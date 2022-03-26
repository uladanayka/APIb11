package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class FindFemaleCharacter {

    @Test
    public void FemalestarWarsTest() {

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();

        Map<String, Object> allFemaleCharacters = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String,Object>> listofCharacters = (List<Map<String, Object>>) allFemaleCharacters.get("results");
        int count =0;

        for (int i =0; i<listofCharacters.size(); i++){
            System.out.println(i);
            Map<String,Object> charMap  = listofCharacters.get(i);

            if(charMap.get("gender").equals("female")){

                ++count;
                System.out.println(count);
            }
        }
        Assert.assertTrue(count>0);
    }

    @Test
    public void deserializeWithPojo(){
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();
        StarWarsPojo deserializedResp = response.as(StarWarsPojo.class);

        Assert.assertEquals(82,deserializedResp.getCount());


    }

}