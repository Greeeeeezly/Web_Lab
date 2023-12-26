package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.services.ModelService;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.services.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Autowired
    private OfferService<UUID> offerService;
    @Autowired
    private ModelService<UUID> modelService;
    @Autowired
    private UserService<UUID> userService;

    public OfferController(OfferService offerService, ModelService modelService, UserService userService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addOffer(@Valid DetailedOfferDto offerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) {
        LOG.log(Level.INFO, "Add offer post" + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        offerService.register(offerDto, principal);

        return "redirect:/offers/all";
    }
  /*  @ModelAttribute("offerModelUpdate")
    public DetailedOfferDto initOffer() {
        return new DetailedOfferDto();
    }*/

    @PostMapping("/edit/{id}")
    public String editOffer(@Valid DetailedOfferDto offerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        LOG.log(Level.INFO, "Edit offer post" + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModelUpdate", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModelUpdate",
                    bindingResult);
            return "redirect:/offers/edit/{id}";
        }
        offerService.update(offerDto);
        return "redirect:/offers/all";
    }

    @GetMapping("/add")
    public String addOffer(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show offers " + principal.getName());
        model.addAttribute("availableModels", modelService.getAllShow());
        model.addAttribute("availableUsers", userService.getAllShow());
        return "offer-add";
    }

    @ModelAttribute("offerModel")
    public AddOfferDto initModel() {
        return new AddOfferDto();
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable UUID id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Edit offer " + principal.getName());
        Optional<DetailedOfferDto> o  = offerService.getById(id);
        model.addAttribute("offerModelUpdate", o.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "offer-edit";
    }

    @GetMapping("/all-sortedByPrice")
    public String showAllOffersByPrice(Model model, @RequestParam(required = false) String sortByPrice, Principal principal) {
        LOG.log(Level.INFO, "Get sorted by price " + principal.getName());
        List<ShowOfferDto> offerInfos;
        if ("asc".equals(sortByPrice) ||"desc".equals(sortByPrice)){
            offerInfos = offerService.getAllByPrice(sortByPrice);
        } else{
            offerInfos = offerService.getAllShow();
        }
        model.addAttribute("offerInfos", offerInfos);
        return "offers-all";
    }

    @GetMapping("/all-sortedByMileage")
    public String showAllOffersByMileage(Model model, @RequestParam(required = false) String sort, Principal principal) {
        LOG.log(Level.INFO, "Get sorted by mileage " + principal.getName());
        List<ShowOfferDto> offerInfos;

        if ("asc".equals(sort)|| "desc".equals(sort)){
            offerInfos = offerService.getAllByMileage(sort);
        } else{
            offerInfos = offerService.getAllShow();
        }
        model.addAttribute("offerInfos", offerInfos);
        return "offers-all";
    }

    @GetMapping("/all-sortedByYear")
    public String showAllOffersByYear(Model model, @RequestParam(required = false) String sort, Principal principal) {
        LOG.log(Level.INFO, "Get sorted by year " + principal.getName());
        List<ShowOfferDto> offerInfos;

        if ("asc".equals(sort) || "desc".equals(sort)) {
            offerInfos = offerService.getAllByYear(sort);
        } else {
            offerInfos = offerService.getAllShow();
        }
        model.addAttribute("offerInfos", offerInfos);
        return "offers-all";
    }

    @GetMapping("/all-sortedByModel")
    public String showAllOffersByModel(Model model, @RequestParam String name, Principal principal) {
        LOG.log(Level.INFO, "Get sorted by model " + principal.getName());
        List<String> allModels = modelService.getModelNames();
        model.addAttribute("allModels", allModels);
        List<ShowOfferDto> offerInfos = offerService.findAllByModel(name);
        model.addAttribute("offerInfos", offerInfos);
        return "offers-all";
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID id, Principal principal) {
        offerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable("id") UUID id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Get offer details " + principal.getName());
        Optional<DetailedOfferDto> o = offerService.getByIdDetailed(id);
        model.addAttribute("offerDetails", o.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "offer-details";
    }

    @GetMapping("/all")
    public String getAllOffers(Model model, Principal principal) {
        LOG.log(Level.INFO, "Get all offers " + principal.getName());
        model.addAttribute("offerInfos", offerService.getAllShow());
        List<String> allModels = modelService.getModelNames();
        model.addAttribute("allModels", allModels);
        return "offers-all";
    }

    @GetMapping("delete/{id}")
    public String deleteBrand(@PathVariable("id") UUID id, Principal principal) {
        LOG.log(Level.INFO, "Delete offer " + principal.getName());
        offerService.deleteById(id);
        return "redirect:/offers/all";
    }
}

