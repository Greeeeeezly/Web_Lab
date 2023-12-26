package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.ShowOfferDto;
import com.example.springdatabasicdemo.dtos.UserRegistrationDto;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.services.AuthService;
import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.views.UserProfileView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class AuthController {
    @Autowired
    private OfferService offerService;
    @Autowired
    private ModelService modelService;
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/users/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegistrationDto);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = authService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                username,
                user.getFirstname(),
                user.getLastname(),
                user.getImageUrl()
        );
        List<ShowOfferDto> offerDtos = offerService.getAllShow();
        model.addAttribute("user", userProfileView);
        model.addAttribute("offerInfos", offerDtos);
       /* List<String> allModels = modelService.getModelNames();
        model.addAttribute("allModels", allModels);*/

        return "profile";
    }
}
