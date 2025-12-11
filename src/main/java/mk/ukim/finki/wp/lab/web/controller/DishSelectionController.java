package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DishSelectionController {

    private final ChefService chefService;
    private final DishService dishService;

    public DishSelectionController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @GetMapping("/dish")
    public String showDishSelection(@RequestParam Long chefId, Model model) {

        Chef chef = chefService.findById(chefId);

        model.addAttribute("chefId", chefId);
        model.addAttribute("chefName", chef.getFirstName() + " " + chef.getLastName());
        model.addAttribute("dishes", dishService.listDishes());

        return "dishesList";
    }

    @PostMapping("/dish")
    public String refreshDish(@RequestParam Long chefId) {
        return "redirect:/dish?chefId=" + chefId;
    }
}
