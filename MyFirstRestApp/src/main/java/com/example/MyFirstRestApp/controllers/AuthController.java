package com.example.MyFirstRestApp.controllers;


import com.example.MyFirstRestApp.models.Person;
import com.example.MyFirstRestApp.repositories.PersonRepository;
import com.example.MyFirstRestApp.security.PersonDetails;
import com.example.MyFirstRestApp.services.RegistrationService;
import com.example.MyFirstRestApp.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final PersonRepository personRepository;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator, PersonRepository personRepository) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.personRepository = personRepository;
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = ((PersonDetails)authentication.getPrincipal()).getPerson();
        Person result = personRepository.findByName(person.getName()).get();
        result.setStatus("NOT_AUTHORIZED");
        personRepository.save(result);
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        return "redirect:/auth/login";
    }
    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    @GetMapping("/reg")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "registration/registration";
    }

    @PostMapping("/reg")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors()) {
            return "registration/registration";
        }
        registrationService.register(person);
        return "redirect:/auth/login";
    }
}
