package edu.uptc.swii.usermicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/prueba1")
    public String test1(){
        return "prueba 1 exitosa";
    }
}
