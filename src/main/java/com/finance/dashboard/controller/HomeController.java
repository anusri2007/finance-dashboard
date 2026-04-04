package com.finance.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
    public String home() {
        return "Finance Dashboard API Running Successfully 🚀";
    }
}
