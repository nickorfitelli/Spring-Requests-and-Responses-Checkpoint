package com.example.checkpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/camelize")
    public String camelize() {
        return "Hello from Spring!";
    }

    @GetMapping("/redact")
    public String redact() {
        return "Hello from Spring!";
    }

    @PostMapping("/encode")
    public String encode() {
        return "Hello from Spring!";
    }

    @PostMapping("s/<find>/<replacement>")
    public String camel() {
        return "Hello from Spring!";
    }

}


