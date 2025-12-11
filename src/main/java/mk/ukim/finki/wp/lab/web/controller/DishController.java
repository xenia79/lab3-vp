package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {

        if (error != null)
            model.addAttribute("error", error);

        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }

    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId) {
        dishService.create(dishId, name, cuisine, preparationTime,chefId);
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam Long chefId){
        dishService.update(id, dishId, name, cuisine, preparationTime,chefId);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model)
    {
        Dish dish = dishService.findById(id);
        if(dish == null)
            return "redirect:/dishes?error=DishNotFound.";
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs());

        return "dish-form";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model)
    {
        model.addAttribute("chefs", chefService.listChefs());
        return "dish-form";
    }
}

