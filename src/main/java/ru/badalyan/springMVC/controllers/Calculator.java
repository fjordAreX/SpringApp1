package ru.badalyan.springMVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Calculator {


    @GetMapping("/calculate")
    public String calculating(@RequestParam("first")Integer first, @RequestParam("second")Integer second, @RequestParam("action")String action, Model model){

        int preAnswer=0;
        switch(action){
            case "\\+":
                preAnswer = first+second;
                break;
            case "-":
                preAnswer = first-second;
                break;
            case "*":
                preAnswer = first*second;
                break;
            case "/":
                preAnswer = first/second;
                break;
        }

        String answer = String.valueOf(preAnswer);

        model.addAttribute("calculate",answer);

        return "hw/calculator";
    }

}
