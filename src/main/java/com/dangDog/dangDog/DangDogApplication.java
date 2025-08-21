package com.dangDog.dangDog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DangDogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangDogApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "selam";
    }

}