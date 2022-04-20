package com.qa.intro_project.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qa.intro_project.data.entity.User;

@Controller
@RequestMapping("/test")
public class TestController {
	
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User get() {
        return null;
    }
    
    @GetMapping(path = "/other", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getAlt() {
        return "<h1>Hello world</h1>";
    }
}
