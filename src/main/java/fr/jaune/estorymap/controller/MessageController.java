package fr.jaune.estorymap.controller;

import fr.jaune.estorymap.model.Message;
import fr.jaune.estorymap.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class MessageController {

    @Autowired
    private CityService cityService;

    @GetMapping(path = "/message")
    public List<Message> getMessage() {
        return this.cityService.findAll();
    }

    @PostMapping(path = "/message")
    public @ResponseBody Message postMessage(@RequestBody(required = true) Message message) {
        return this.cityService.save(message);
    }
}
