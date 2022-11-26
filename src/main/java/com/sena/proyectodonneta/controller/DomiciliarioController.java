package com.sena.proyectodonneta.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.proyectodonneta.model.Domicilio;
import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.repository.DomicilioIdRepository;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.IDomicilioService;


@Controller
@RequestMapping("/domiciliario")
public class DomiciliarioController {
    
    private final Logger log = LoggerFactory.getLogger(DomiciliarioController.class);

    @Autowired
    private IDomicilioService domicilioService;


    @Autowired
	private UserService userService;

    @Autowired
    private DomicilioIdRepository domicilioIdRepository;

    @GetMapping("")
    public String listar(Model model, Long idDomicilio, HttpSession session) {


        String currentUser = SecurityUtils.getUserName();
        User user = userService.findByEmail(currentUser);

        List <Domicilio> domicilios = domicilioIdRepository.findDomiciliario(user.getId());


        model.addAttribute("username", currentUser);
        

        model.addAttribute("domicilios", domicilios);
		
        return "VistaDomiciliario/listar";
    }

}
