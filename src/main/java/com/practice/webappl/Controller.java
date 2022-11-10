package com.practice.webappl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserRepository repository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> userList = repository.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/dreamers")
    public String viewDreamList(Model model) {
        List<User> dreamList = repository.findAll();
        model.addAttribute("dreamList", dreamList);
        return "dream_list";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "register_success";
    }
}
