package com.simpleform.user.controller;


import com.simpleform.user.model.UserModel;
import com.simpleform.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return  "user/register";
    }

    @GetMapping("/")
    public String homePage(Authentication authentication, Model model) {
        model.addAttribute("userLogin", authentication.getName());
        return  "user/home";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@Valid UserModel userModel, BindingResult result){
        if (result.hasErrors()) {
            return  "user/register";
        }

        userService.registerUser(userModel);

        return "user/login";
    }
}
