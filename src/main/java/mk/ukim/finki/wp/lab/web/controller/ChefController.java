package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Gender;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/chefs")
public class ChefController {

    private final ChefService chefService;
    private final DishService dishService;

    public ChefController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @GetMapping
    public String getChefsPage(@RequestParam(required = false) String error, Model model) {

        if (error != null)
            model.addAttribute("error", error);
        model.addAttribute("chefs", chefService.listChefs());
        return "chefsList";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        chefService.delete(id);
        return "redirect:/chefs";
    }

    @PostMapping("/add")
    public String saveChef(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio,
                           @RequestParam Gender gender) {
        chefService.create(firstName, lastName, bio, gender);
        return "redirect:/chefs";
    }

    @PostMapping("/edit/{id}")
    public String editChef(@PathVariable Long id,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String bio,
                           @RequestParam Gender gender){
        chefService.update(id, firstName, lastName, bio, gender);
        return "redirect:/chefs";
    }

    @GetMapping("/chef-form/{id}")
    public String getEditChefForm(@PathVariable Long id, Model model)
    {
        Chef chef = chefService.findById(id);
        if(chef == null)
            return "redirect:/chefs?error=ChefNotFound.";
        model.addAttribute("chef", chef);
        model.addAttribute("genders", Gender.values());

        return "chef-form";
    }

    @GetMapping("/chef-form")
    public String getAddChefPage(Model model)
    {
        model.addAttribute("genders", Gender.values());
        return "chef-form";
    }

    @GetMapping("/dishes/{id}")
    public String getDishesByChef(@PathVariable Long id, Model model) {
        Chef chef = chefService.findById(id);
        List<Dish> dishes = dishService.listDishesByChef(id);

        model.addAttribute("chef", chef);
        model.addAttribute("dishes", dishes);

        return "dishesByChef";
    }
}

