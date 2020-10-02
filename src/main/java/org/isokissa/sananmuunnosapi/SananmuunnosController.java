package org.isokissa.sananmuunnosapi;


import org.isokissa.sananmuunnos.SananmuunnosService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException; 


@RestController
class SananmuunnosController {

  SananmuunnosController() {
  }

  @PostMapping("/sananmuunnos")
  String randomEntry(@RequestBody String words) {
    String trimmedWords = words.trim();
    if(trimmedWords.charAt(0) != '"' || trimmedWords.charAt(trimmedWords.length() - 1) != '"') {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input, expecting a single string");
    }
    String result = SananmuunnosService.muunna(trimmedWords.substring(1, trimmedWords.length() -1));
    return JSONObject.quote(result);
  }
}