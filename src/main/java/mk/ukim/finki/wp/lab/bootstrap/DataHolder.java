package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Gender;
import mk.ukim.finki.wp.lab.repository.jpa.ChefRepository;
import mk.ukim.finki.wp.lab.repository.jpa.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataHolder {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public DataHolder(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {

        Chef chef1 = new Chef("Gordon","Ramsay",
                "World-renowned chef known for his fiery personality.", new ArrayList<>(),Gender.MALE);

        Chef chef2 = new Chef("Massimo","Bottura",
                "Italian chef famous for modern Italian cuisine.", new ArrayList<>(), Gender.MALE);

        Chef chef3 = new Chef("Alice","Waters",
                "Pioneer of the farm-to-table movement in the USA.",new ArrayList<>(),Gender.FEMALE);

        Chef chef4 = new Chef("Heston","Blumenthal",
                "Chef known for his scientific approach to cooking.", new ArrayList<>(),Gender.MALE);

        Chef chef5 = new Chef("Dominique","Crenn",
                "Chef celebrated for artistic and creative cuisine.", new ArrayList<>(),Gender.MALE);

        chefRepository.save(chef1);
        chefRepository.save(chef2);
        chefRepository.save(chef3);
        chefRepository.save(chef4);
        chefRepository.save(chef5);

        Dish dish1 = new Dish("D01", "Scrambled Eggs", "British", 10,chef1);
        Dish dish2 = new Dish("D02", "Tiramisu", "Italian", 30,chef2);
        Dish dish3 = new Dish("D03", "Seasonal Salad", "American", 20,chef3);
        Dish dish4 = new Dish("D04", "Lobster Ravioli", "French", 80,chef4);
        Dish dish5 = new Dish("D05", "Tagliatelle al Ragu", "Italian", 60,chef5);

        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);
        dishRepository.save(dish4);
        dishRepository.save(dish5);


    }
}
