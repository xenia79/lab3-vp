package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChefListController {

    private final ChefService chefService;

    public ChefListController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/listChefs")
    public String getChefsPage(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        return "listChefs";
    }
}

