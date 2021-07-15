package com.example.server.Controller;

import com.example.server.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Search")
public class SearhContrller {
    @Autowired
    SearchService searchService;
    @GetMapping("")
    public String searchArticle(String search, Model model) {
        model.addAttribute("search", search);
        model.addAttribute("result", searchService.search(search));
        return "index";
    }

    @GetMapping("/{type}")
    public String searchType(@PathVariable String type, Model model) {

        model.addAttribute("result", searchService.searchType(type));
        return "index";
    }
}
