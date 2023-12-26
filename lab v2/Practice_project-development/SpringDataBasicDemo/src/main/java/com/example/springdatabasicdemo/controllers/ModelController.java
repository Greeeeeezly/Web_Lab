package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.*;
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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/models")
public class ModelController {

    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private ModelService<UUID> modelService;
    private BrandService<UUID> brandService;
    @Autowired
    public void setModelService(ModelService<UUID> modelService) {
        this.modelService = modelService;
    }
    @Autowired
    public void setBrandService(BrandService<UUID> brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/new")
    public String addModel(Model model, Principal principal) {
        LOG.log(Level.INFO, "Add model" + principal.getName());
        model.addAttribute("availableBrand", brandService.getAllShow());
        return "model-add";
    }

    @ModelAttribute("model")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @PostMapping("/new")
    public String createModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        LOG.log(Level.INFO, "Add model post" + principal.getName());
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel", bindingResult);
            return "redirect:/models/new";
        }
        modelService.register(modelModel);

        return "redirect:/models/all";
    }

    @GetMapping("/all")
    public String showAllModels(Model model, Principal principal) {
        LOG.log(Level.INFO, "Get all models " + principal.getName());
        model.addAttribute("models", modelService.getAllShow());
        return "model-all";
    }
    @GetMapping("/edit/{id}")
    public String editModel(@PathVariable UUID id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Edit model get" + principal.getName());
        model.addAttribute("availableBrands", brandService.getAllShow());
        Optional<DetailedModelDto> m  = modelService.getById(id);
        model.addAttribute("modelModel", m.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "model-edit";
    }


    @PostMapping("/edit/{id}")
    public String editModel(@Valid DetailedModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                            Principal principal) {
        LOG.log(Level.INFO, "Edit model post" + principal.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/edit/{id}";
        }
        modelService.update(modelModel);
        return "redirect:/models/all";
    }

    @GetMapping("/{id}")
    public String modelDetails(@PathVariable("id") UUID id, Model model, Principal principal) {
        LOG.log(Level.INFO, "Get model details " + principal.getName());
        Optional<DetailedModelDto> m  = modelService.getByIdDetailed(id);
        model.addAttribute("modelDetails", m.orElseThrow(() ->
                new NoSuchElementException("Value not present")));
        return "model-details";
    }
    @GetMapping("/byYear")
    public String modelsByYear(Model model, Principal principal) {
        LOG.log(Level.INFO, "Get models by year " + principal.getName());
        model.addAttribute("modelsByYear", modelService.findModelsByYear());
        return "model-byyear";
    }
    @GetMapping("delete/{id}")
    public String deleteModel(@PathVariable ("id") UUID id, Principal principal) {
        LOG.log(Level.INFO, "Delete model" + principal.getName());
        modelService.deleteById(id);
        return "redirect:/models/all";
    }
}
