package org.isokissa.sananmuunnosapi;


import org.isokissa.sananmuunnos.SananmuunnosService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException; 


@RestController
class SananmuunnosController {

  SananmuunnosController() {
  }

  @GetMapping("/status")
  String status() {
    return "OK";
  }

  @PostMapping(path = "/sananmuunnos", 
               consumes = MediaType.APPLICATION_JSON_VALUE, 
               produces = MediaType.APPLICATION_JSON_VALUE)
  String sananmuunnos(@RequestBody char[] body) {
    StringBuilder words = new StringBuilder();
    words.append(body);
    System.out.println("petar original =" + words);
    String result = SananmuunnosService.muunna(words.toString());
    System.out.println("petar result =" + result);
    return JSONObject.quote(result);
  }
}