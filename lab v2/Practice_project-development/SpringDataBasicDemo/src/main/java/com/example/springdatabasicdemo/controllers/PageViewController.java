package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.services.PageViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageViewController {

    private final PageViewService pageViewService;

    public PageViewController(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    @GetMapping("/pageViews/all")
    public String getPageViewsAll(Model model) {
        Map<String, Long> counts = pageViewService.countOccurrences();
        model.addAttribute("counts", counts);
        return "pageviews-all";
    }
    @GetMapping("/pageViews/withNames")
    public String getPageViewsWithNames(Model model) {
        Map<String, Long> counts = pageViewService.countOccurrencesWithNames();
        Map<String, String> substrings = new HashMap<>();

        counts.forEach((key, value) -> {
            String substring = key.substring(key.lastIndexOf(' ') + 1);
            substrings.put(key, substring);
        });

        model.addAttribute("counts", counts);
        model.addAttribute("substrings", substrings);
        return "pageviews-withNames";
    }

    @GetMapping("/pageViews")
    public String pageViews(Model model){
        return "pageviews-demo";
    }
}