package org.isokissa.sananmuunnosapi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SananmuunnosController {

  SananmuunnosController() {
  }

  @PostMapping("/sananmuunnos")
  String randomEntry(@RequestBody String words) {
    return "petar dobio reci= " + words;
  }
}