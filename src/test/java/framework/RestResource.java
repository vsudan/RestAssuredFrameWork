package framework;

import io.restassured.response.Response;

import static framework.Specifications.getRequestSpec;
import static framework.Specifications.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response postRequest(String path, String accessToken, Object requestPayload) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + accessToken).
                body(requestPayload).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response getRequest(String path, String accessToken) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + accessToken).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response putRequest(String path, String accessToken, Object requestPayload) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + accessToken).
                body(requestPayload).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }
}