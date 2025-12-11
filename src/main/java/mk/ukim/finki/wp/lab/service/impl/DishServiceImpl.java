package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.jpa.ChefRepository;
import mk.ukim.finki.wp.lab.repository.jpa.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return this.dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return this.dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        if (    dishId == null || dishId.isEmpty() ||
                name == null || name.isEmpty() ||
                cuisine == null || cuisine.isEmpty() ||
                        preparationTime < 0)
            throw new IllegalArgumentException();


        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new IllegalArgumentException());

        Dish dish = new Dish(dishId, name, cuisine, preparationTime, chef);
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        if (    dishId == null || dishId.isEmpty() ||
                name == null || name.isEmpty() ||
                cuisine == null || cuisine.isEmpty() ||
                preparationTime < 0)
            throw new IllegalArgumentException();

        Dish dish = dishRepository.findById(id).orElse(null);

        Chef chef = chefRepository.findById(chefId)
                .orElseThrow(() -> new IllegalArgumentException());

        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        dish.setChef(chef);
        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> listDishesByChef(Long chefId) {
        return dishRepository.findAllByChef_Id(chefId);
    }
}
