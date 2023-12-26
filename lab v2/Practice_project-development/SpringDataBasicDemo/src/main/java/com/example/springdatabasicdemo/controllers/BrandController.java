package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddBrandDto;
import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.dtos.DetailedBrandDto;
import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.services.BrandService;
import com.example.springdatabasicdemo.services.ModelService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private BrandService<UUID> brandService;
    private ModelService<UUID> modelService;

    @Autowired
    public void setModelService(ModelService<UUID> modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setBrandService(BrandService<UUID> brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/new")
    public String createBrand(Principal principal) {
        LOG.log(Level.INFO, "Add brand get" + principal.getName());
        return "brand-add";
    }
    @GetMapping("/edit/{id}")
    public String editBrand(@PathVariable UUID id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Edit brand get" + principal.getName());
        Optional<BrandDto> b  = brandService.getById(id);
        model.addAttribute("brandModel", b.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "brand-edit";
    }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @PostMapping("/new")
    public String addBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) {
        LOG.log(Level.INFO, "Add brand post" + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/new";
        }
        brandService.register(brandModel);

        return "redirect:/brands/all";
    }

    @PostMapping("/edit/{id}")
    public String editBrand(@Valid DetailedBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            Principal principal) {
        LOG.log(Level.INFO, "Edit brand post" + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/edit/{id}";
        }
        brandService.update(brandModel);
        return "redirect:/brands/all";
    }

    @GetMapping("/all")
    public String showAllBrands(Principal principal, Model model) {
        LOG.log(Level.INFO, "Get all brands" + principal.getName());
        model.addAttribute("brands", brandService.getAllShow());
        return "brand-all";
    }

    @GetMapping("/byModelCount")
    public String showTopBrand(Principal principal,Model model) {
        LOG.log(Level.INFO, "Get top brand by models" + principal.getName());
        model.addAttribute("brandsByModel", brandService.findBrandsByModels());
        return "brand-modelcount";
    }


    @GetMapping("/{id}")
    public String brandDetails(@PathVariable("id") UUID id, Principal principal, Model model) {
        LOG.log(Level.INFO, "Get brand details" + principal.getName());
        Optional<DetailedBrandDto> b  = brandService.getByIdDetailed(id);
        model.addAttribute("brandDetails", b.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "brand-details";
    }

    @GetMapping("delete/{id}")
    public String deleteBrand(@PathVariable ("id") UUID id, Principal principal) {
        LOG.log(Level.INFO, "Delete brand" + principal.getName());
        brandService.deleteById(id);
        return "redirect:/brands/all";
    }
}
