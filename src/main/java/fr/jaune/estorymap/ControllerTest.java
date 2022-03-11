package fr.jaune.estorymap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping(path = "api/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test() {
        return "t'est un pd";
    }
}
