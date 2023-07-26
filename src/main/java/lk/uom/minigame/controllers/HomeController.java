package lk.uom.minigame.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="home")
@CrossOrigin
public class HomeController {
    @GetMapping("/app")
    public String get(){
        return "Hellow App up and running";
    }
}
