package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CatFactsTest {

    @Test
    public void setAllCatPractice() {

        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();


        List<Map<String, Object>> catFacttest = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        System.out.println(catFacttest);

        for(int i =0; i<catFacttest.size();i++){
            Map<String,Object> catFactMap = catFacttest.get(i);
            System.out.println(catFactMap.get("text"));
        }
    }


    @Test
    public void lastFactTest(){
        /*
        get https://cat-fact.herokuapp.com/facts"
        validate that the last cat fact equals to:
        Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs
         */

        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")
                .then().statusCode(200).extract().response();

        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        Map<String ,Object> lastfact = parsedResponse.get(parsedResponse.size()-1);
String actualLastFact = lastfact.get("text").toString();
String  expectedlastFact = "Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.";
        Assert.assertEquals(expectedlastFact,actualLastFact);


    }
}