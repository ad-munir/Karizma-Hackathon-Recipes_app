package com.recette.app.controller;

import com.recette.app.dto.AuthRequest;
import com.recette.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    @Autowired
    UserService service;

    @GetMapping("/api/home")
    public String home(){
        return "Home Page";
    }

    @GetMapping("/api/rec")
    public String recettes(){
        return "recettes Page";
    }

    @PostMapping("register")
    private ResponseEntity<String> register(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
}
