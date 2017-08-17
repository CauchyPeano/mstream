package de.cauchypeano.mstream.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

    @GetMapping("/")
    public String test() {
        return "tada!";
    }

}
