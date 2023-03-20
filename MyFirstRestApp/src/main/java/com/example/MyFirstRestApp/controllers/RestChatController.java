package com.example.MyFirstRestApp.controllers;

import com.example.MyFirstRestApp.models.MessageLogin;
import com.example.MyFirstRestApp.models.Person;
import com.example.MyFirstRestApp.security.PersonDetails;
import com.example.MyFirstRestApp.services.PeronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class RestChatController {
    private final PeronService peronService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public RestChatController(PeronService peronService, SimpMessagingTemplate simpMessagingTemplate) {
        this.peronService = peronService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> featchAll(){
        List<Person> users = peronService.findAllAuthorized();
        Set<String> res = users.stream().map(Person::getName).collect(Collectors.toSet());
        return  res;
    }
    @GetMapping("/getUser")
    public String getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = ((PersonDetails)authentication.getPrincipal()).getPerson();
        return person.getName();
    }
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageLogin messageLogin) {
        System.out.println("message" + messageLogin + "to" + to);
        if (peronService.findByName(to) != null) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to,messageLogin);
        }
    }
}
