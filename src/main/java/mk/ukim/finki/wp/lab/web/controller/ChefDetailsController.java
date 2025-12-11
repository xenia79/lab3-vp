package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChefDetailsController {

    private final ChefService chefService;

    public ChefDetailsController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/chefDetails")
    public String showChefDetails(@RequestParam Long chefId, Model model) {

        Chef chef = chefService.findById(chefId);

        model.addAttribute("chefId", chefId);
        model.addAttribute("chefBio", chef.getBio());
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());
        model.addAttribute("dishes", chef.getDishes());

        return "chefDetails";
    }

    @PostMapping("/chefDetails")
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId) {

        chefService.addDishToChef(chefId, dishId);

        return "redirect:/chefDetails?chefId=" + chefId;
    }
}
