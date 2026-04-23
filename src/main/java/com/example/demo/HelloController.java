package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Git + Spring Boot!";
    }
    
    @GetMapping("/info")
    public Map<String, Object> info(){
    	Map<String, Object> data = new HashMap<>();
    	data.put("name", "姓名");
    	data.put("project", "spring boot");
    	return data;
    }
}
