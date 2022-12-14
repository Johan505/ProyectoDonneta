package com.sena.proyectodonneta.controller;

import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.repository.UserRepository;
import com.sena.proyectodonneta.service.EmailSenderService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class ForgotPasswordController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/forgotPassword")
    public String form(){
        return "password.html";
    }

    @PostMapping("/sendEmail")
    public String validateIfExistsUser(@Valid String email){
        emailSenderService.sendSimpleEmailForgotPassword(email);
        return "redirect:/register/forgotPassword?sended=true";
    }

    @GetMapping("/restablecerClave/{email}")
    public String loadViewUpdatePassword(@PathVariable String email, Model model){
        model.addAttribute("email", email);
        return "update.html";
    }

    @PostMapping("/cambiar")
    public String updatePassword(@Valid String email,@Valid String password){
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login";
    }
}