//package mk.ukim.finki.wp.lab.repository.inmemory.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Dish;
//import mk.ukim.finki.wp.lab.repository.inmemory.DishRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryDishRepository implements DishRepository {
//
//    @Override
//    public List<Dish> findAll() {
//        return DataHolder.dishes;
//    }
//
//    @Override
//    public Dish findByDishId(String dishId) {
//        return DataHolder.dishes.stream().
//                filter(dish -> dish.getDishId().equals(dishId)).
//                findFirst().
//                orElse(null);
//    }
//
//    @Override
//    public Optional<Dish> findById(Long id) {
//        return DataHolder.dishes.stream()
//                .filter(dish -> dish.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public Dish save(Dish dish) {
//        deleteById(dish.getId());
//        DataHolder.dishes.add(dish);
//        return dish;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        DataHolder.dishes.removeIf(m -> m.getId().equals(id));
//    }
//}
//
