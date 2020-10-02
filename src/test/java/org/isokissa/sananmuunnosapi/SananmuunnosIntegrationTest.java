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
        String response = restTemplate.postForObject(correctUrl + "XXX", "albert einstein", String.class);
        assertThat(getStatusCode(response)).isEqualTo(404);
    }

    @Test
    void wronglyFormattedInputFailsWith400() {
        String response = restTemplate.postForObject(correctUrl, "[1, 2, 3]", String.class);
        assertThat(getStatusCode(response)).isEqualTo(400);
    }

    @Test
    void correctlyFormattedInputGetsProcessed() {
        String response = restTemplate.postForObject(correctUrl, "\"albert einstein\"", String.class);
        assertThat(response).isEqualTo("\"eilbert anstein\"");
    }

    private int getStatusCode(String jsonReponse) {
        JSONObject responseObject = new JSONObject(jsonReponse);
        return responseObject.getInt("status");
    }
}
