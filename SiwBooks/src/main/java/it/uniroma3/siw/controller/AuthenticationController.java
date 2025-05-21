package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Role;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.utils.SecurityUtils;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtils securityUtils;

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "formRegisterUser.html";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        return "formLogin";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        if (!securityUtils.isAuthenticated()) {
            return "index.html";
        }

        Credentials credentials = securityUtils.getCurrentCredentials(credentialsService);
        if (credentials.getRole().equals(Role.ADMIN)) {
            return "index.html";
        }

        return "index.html";
    }

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        Credentials credentials = securityUtils.getCurrentCredentials(credentialsService);
        if (credentials != null && credentials.getRole().equals(Role.ADMIN)) {
            return "index.html";
        }
        return "index.html";
    }

    @GetMapping("/personalArea")
    public String personalArea(Model model) {
        if (!securityUtils.isAuthenticated()) {
            return "login";
        }

        Credentials credentials = securityUtils.getCurrentCredentials(credentialsService);
        model.addAttribute("credentials", credentials);
        model.addAttribute("user", credentials.getUser());

        return "personalArea.html";
    }

    @PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @Valid @ModelAttribute("credentials") Credentials credentials,
                               @RequestParam("confirmPassword") String confirmPassword,
                               BindingResult credentialsBindingResult,
                               Model model) {

        if (!credentials.getPassword().equals(confirmPassword)) {
            credentialsBindingResult.rejectValue("password", "error.credentials", "Le password non coincidono");
        }

        if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(user);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "formLogin";
        }

        model.addAttribute("user", user);
        model.addAttribute("credentials", credentials);
        return "formRegisterUser.html";
    }

}
