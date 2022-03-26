package get;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class DeserializePokeman {
//for mac command+n
    @Test

    public void pokemanGet() {

        Response response = given().when().get("https://pokeapi.co/api/v2/pokemon")
                .then().extract().response();

        Assert.assertTrue(response.statusCode()==200);
        Map<String ,Object> pokemanResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(pokemanResponse);

        List<Map<String,Object>> results = (List<Map<String, Object>>) pokemanResponse.get("results");

        boolean isThereBulbasaur = false;
        for (Map<String,Object> info:results
             ) {

           // System.out.println(info.get("name"));
            if(info.get("name").toString().equals("bulbasaur")){
                isThereBulbasaur = true;
                break;
            }

        }
Assert.assertTrue("There is no bulbasaur",isThereBulbasaur);

    }
}
