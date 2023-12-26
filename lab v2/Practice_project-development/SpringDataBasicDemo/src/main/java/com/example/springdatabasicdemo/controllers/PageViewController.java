package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.services.PageViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PageViewController {

    private final PageViewService pageViewService;

    public PageViewController(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    @GetMapping("/pageViews")
    public String getPageViews(Model model) {
        Map<String, Long> counts = pageViewService.countOccurrences();
        model.addAttribute("counts", counts);
        return "pageviews";
    }
}