package framework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GenerateAccessToken {

    private static String accessToken;
    private static Instant expiryTime;

    public static String refreshAccessToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Generating Access Code.......");
                Response response = getTokenResponse();
                accessToken = response.path("access_token");
                int expiryTimeInSecond = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryTimeInSecond);
            }
            else {
                System.out.println("Access Code is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error: Can not generate Access Code");
        }
        return accessToken;
    }

    private static Response getTokenResponse() {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("client_id", "55b252b3146d4e84addf25c6a4896d3e");
        parameter.put("client_secret", "796a1cc4b76740a98653df3eac58097c");
        parameter.put("grant_type", "refresh_token");
        parameter.put("refresh_token", "AQAsz9YwSw-aC029MKjKj_nEuqkqrKeJ4zkmUW_8uP-sGN_NEASg33Rt7YQ5evucpm4QMiwxRKzbB_uZf7udFF31iwTfUREILycLVZ3STETYMofCv38Cz8J2FgwoJz64too");

        return given().
                baseUri("https://accounts.spotify.com")
                .contentType(ContentType.URLENC)
                .formParams(parameter)
                .log().all().
                when()
                .post("/api/token").
                then().spec(Specifications.getResponseSpec()).extract().response();
    }
}
