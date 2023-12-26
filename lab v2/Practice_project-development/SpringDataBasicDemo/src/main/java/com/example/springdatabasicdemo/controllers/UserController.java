package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddUserDto;
import com.example.springdatabasicdemo.dtos.DetailedBrandDto;
import com.example.springdatabasicdemo.dtos.DetailedUserDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.services.UserRoleService;
import com.example.springdatabasicdemo.services.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService<UUID> userService;
    private UserRoleService userRoleService;

    @Autowired
    public void setUserService(UserService<UUID> userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserRoleService(UserRoleService<UUID> userRoleService) {
        this.userRoleService = userRoleService;
    }


    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("availableRoles", userRoleService.getAll());
        return "user-add";
    }

    @ModelAttribute("userModel")
    public UserDto initUser() {
        return new UserDto();
    }

    @PostMapping("/new")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/new";
        }
        userService.register(userModel);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllCompanies(Model model) {
        model.addAttribute("users", userService.getAllShow());
        return "user-all";
    }

    @GetMapping("/{id}")
    public String userDetails(@PathVariable("id") UUID id, Model model) {
        Optional<DetailedUserDto> u  = userService.getByIdDetailed(id);
        model.addAttribute("userDetails", u.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "user-details";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable ("id") UUID id) {
        userService.deleteById(id);
        return "redirect:/users/all";
    }



/*    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.register(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.expel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        return userService.findUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }*/
}
