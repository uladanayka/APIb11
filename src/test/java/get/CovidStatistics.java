package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CovidStatistics {

    @Test
    public void covidtest(){

        Response response = RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")
                .then().statusCode(200).extract().response();

        Map<String,Object> covidStatisResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        //System.out.println(covidStatisResponse);
        int affectedCountries = (int) covidStatisResponse.get("affectedCountries");
        Assert.assertEquals(227,covidStatisResponse.get("affectedCountries"));

    }
}
