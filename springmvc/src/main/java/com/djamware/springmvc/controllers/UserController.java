package com.djamware.springmvc.controllers;

import com.djamware.springmvc.models.Product;
import com.djamware.springmvc.models.User;
import com.djamware.springmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public String product(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "user";
    }

    @RequestMapping("/account")
    public String account(Model model) {
        return "account";
    }

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }
    
    @RequestMapping("/saveAccount")
    public String save(@RequestParam String email,@RequestParam String name, @RequestParam String lastName, @RequestParam String password) {
        User user= new User();
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        user.setPassword(password);
        userRepository.save(user);

        return "redirect:/product";
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
    	User user = userRepository.findByEmail(email);
        if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
        	if(user.getName().equals("admin")) {
        		return "redirect:/user";
        	}
        	return "redirect:/product";
        }else {
        	return "redirect:/error";
        }
    }
}