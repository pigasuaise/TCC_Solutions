package com.example.apptccnodata.Citations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CitationsContoller {
    @GetMapping("/citacoes")
    public String showCitations() {
        return "citacoes";
    }
    
}
