package com.example.server.Controller;

import com.example.server.Services.PcBuldingService;
import com.example.server.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/PcBuilding")
public class PcBuilding {
    @Autowired
    SearchService searchService;
    @Autowired
    PcBuldingService pcBuldingService;
    @GetMapping("/ChooseCPU")
    public String configration(Model model){
        model.addAttribute("listCPU",searchService.searchType("CPU"));
        return "ConfigChooseCPU";
    }
    @GetMapping("/ChooseMB/{id}")
    public String configrationPage2(@PathVariable String id, Model model, HttpSession session){
        session.setAttribute("CPU",searchService.articleById(id));
        model.addAttribute("listMB", pcBuldingService.matherboard(id));
        return "ConfigChooseMB";
    }
    @GetMapping("/ChooseRAM/{id}")
    public String configrationRAM(@PathVariable String id, Model model,HttpSession session){
        session.setAttribute("MB",searchService.articleById(id));
        model.addAttribute("listRAM", pcBuldingService.ram(id));
        return "ConfigChooseRAM";
    }
    @GetMapping("/SucessBuild/{id}")
    public String configrationfinal(@PathVariable String id, Model model,HttpSession session){
        session.setAttribute("RAM",searchService.articleById(id));
        model.addAttribute("listRAM", pcBuldingService.ram(id));
        return "ConfigChooseMBFinal";
    }
}
