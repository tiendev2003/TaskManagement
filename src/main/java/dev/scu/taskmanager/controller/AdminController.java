package dev.scu.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdminController {

    @GetMapping("/dashboard")
    public String getDashboard() throws JsonProcessingException {

        return "views/statistics";
    }

}