package com.metaverse.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/mymemo")
    public String home(){
        return "index";
    }
}
