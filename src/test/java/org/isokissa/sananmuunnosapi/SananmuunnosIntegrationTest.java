package org.isokissa.sananmuunnosapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SananmuunnosIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; 

    @Autowired
    private SananmuunnosController controller;

    private String urlPrefix; 
    private String correctUrl;

    @BeforeEach
    public void setup() {
        urlPrefix = "http://localhost:" + port + "/";
        correctUrl = urlPrefix + "sananmuunnos";
    }
    
    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void wrongUrlFailsWith404() {
        String response = sendRequest(correctUrl + "XXX", "albert einstein");
        assertThat(getStatusCode(response)).isEqualTo(404);
    }

    @Test
    void wronglyFormattedInputFailsWith400() {
        String response = sendRequest(correctUrl, "[1, 2, 3]");
        assertThat(getStatusCode(response)).isEqualTo(400);
    }

    @Test
    void correctlyFormattedInputGetsProcessed() {
        String response = sendRequest(correctUrl, "\"albert einstein\"");
        assertThat(response).isEqualTo("\"eilbert anstein\"");
    }

    @Test
    void correctlyFormattedInputWithUnicodeGetsProcessed() {
        String response = sendRequest(correctUrl, 
            "\"loyn kyåmwaðxcäuðmåä å å cmåvklroitvei aelomðroåšle šuä hmxäowsð aå äedjxize wuo ädh\"");
        assertThat(response).isEqualTo(
            "\"kyån loymwaðxcäuðmåä å å aevklroitvei cmålomðroåšle hmxäo šuäwsð äe aådjxize ä wuodh\"");
    }

    private String sendRequest(String url, String inputText) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");      
 
        HttpEntity<String> request = new HttpEntity<>(inputText, headers);
        String response = restTemplate.postForObject(url, request, String.class);
        return response;  
    }

    private int getStatusCode(String jsonReponse) {
        JSONObject responseObject = new JSONObject(jsonReponse);
        return responseObject.getInt("status");
    }
}
