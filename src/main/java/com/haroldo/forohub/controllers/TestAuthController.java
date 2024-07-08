package com.haroldo.forohub.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")

public class TestAuthController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String get() {
        return "Hello World Get";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')")
    public String create() {
        return "Hello World Post";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String update() {
        return "Hello World Put";
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public String delete() {
        return "Hello World Delete";
    }




}
