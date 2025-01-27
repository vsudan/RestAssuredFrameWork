package test;

import data.validResponse.Playlist;
import framework.RestResource;
import io.restassured.response.Response;

import static framework.GenerateAccessToken.refreshAccessToken;

public class APIRequest {

    public static Response postRequest(Playlist requestPayload) {
        return RestResource.postRequest("users/31wyccusnupm2vznsbomgy6omiwu/playlists", refreshAccessToken(), requestPayload);
    }

    public static Response postRequest(Playlist requestPayload, String accessToken) {
        return RestResource.postRequest("users/31wyccusnupm2vznsbomgy6omiwu/playlists", accessToken, requestPayload);
    }

    public static Response getRequest(String id) {
        return RestResource.getRequest("/playlists/" + id, refreshAccessToken());
    }

    public static Response putRequest(Playlist requestPayload, String id) {
        return RestResource.putRequest("/playlists/" + id, refreshAccessToken(), requestPayload);
    }
}