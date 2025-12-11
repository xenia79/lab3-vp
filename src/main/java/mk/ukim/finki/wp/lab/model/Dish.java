package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;
    @ManyToOne
    private Chef chef;

    public Dish() {}
    public Dish(String dishId, String name, String cuisine, int preparationTime,Chef chef) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.chef = chef;
    }
}
