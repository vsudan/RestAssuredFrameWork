package test;


import data.InvalidResponse.ErrorMain;
import data.validResponse.Playlist;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyPlaylistTest {

    @Test
    public void shouldAbleToCreatePlaylist() {
        Playlist requestPayload = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Response response = APIRequest.postRequest(requestPayload);

        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        Playlist responsePayload = response.as(Playlist.class);

        assertThat(responsePayload.getName(), equalTo(responsePayload.getName()));
        assertThat(responsePayload.getDescription(), equalTo(responsePayload.getDescription()));
        assertThat(responsePayload.getPublic(), equalTo(responsePayload.getPublic()));
    }

    @Test
    public void shouldAbleToGetPlaylist() {

        Response response = APIRequest.getRequest("7igpEPe38sQkslRJfmXJ1R");

        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        Playlist responsePayload = response.as(Playlist.class);

        assertThat(responsePayload.getName(), equalTo(responsePayload.getName()));
        assertThat(responsePayload.getDescription(), equalTo(responsePayload.getDescription()));
        assertThat(responsePayload.getPublic(), equalTo(responsePayload.getPublic()));
    }

    @Test
    public void shouldAbleToUpdatePlaylist() {
        Playlist requestPayload = new Playlist()
                .setName("Vikas Sudan Sharma")
                .setDescription("Updated playlist description")
                .setPublic(false);

        Response response = APIRequest.putRequest(requestPayload, "7igpEPe38sQkslRJfmXJ1R");

        assertThat(response.getStatusCode(), equalTo(200));

    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithoutName() {
        Playlist requestPayload = new Playlist().setName("").setDescription("New playlist description").setPublic(false);

        Response response = APIRequest.postRequest(requestPayload);

        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.getContentType(), equalTo("application/json; charset=utf-8"));

        ErrorMain responsePayload = response.as(ErrorMain.class);

        assertThat(responsePayload.getErrorBase().getStatus(), equalTo(400));
        assertThat(responsePayload.getErrorBase().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotAbleToCreatePlaylistWithInvalidAccessToken() {
        Playlist requestPayload = new Playlist().setName("New Playlist").setDescription("New playlist description").setPublic(false);

        Response response = APIRequest.postRequest(requestPayload, "1234");

        assertThat(response.getStatusCode(), equalTo(401));
        assertThat(response.getContentType(), equalTo("application/json"));

        ErrorMain responsePayload = response.as(ErrorMain.class);

        assertThat(responsePayload.getErrorBase().getStatus(), equalTo(401));
        assertThat(responsePayload.getErrorBase().getMessage(), equalTo("Invalid access token"));
    }
}