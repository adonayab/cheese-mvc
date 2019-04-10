package org.launchcode.cheesemvs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Cheese name can not be empty")
    @Size(min = 3, max = 15, message = "Cheese name must be 3 to 15 characters")
    private String cheeseName;

    @NotNull
    @Size(min = 1, message = "Cheese description can not be empty")
    private String cheeseDescription;

    @ManyToOne
    private Category category;

    @NotNull(message = "Choose a rating from 1 to 5")
    private int rating;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese(String cheeseName, String cheeseDescription) {
        this.cheeseName = cheeseName;
        this.cheeseDescription = cheeseDescription;
    }
    public Cheese() { }

    public int getId() {
        return id;
    }

    public String getCheeseName() {
        return cheeseName;
    }
    public void setCheeseName(String cheeseName){
        this.cheeseName = cheeseName;
    }

    public String getCheeseDescription() {
        return  cheeseDescription;
    }
    public void setCheeseDescription(String cheeseDescription) {
        this.cheeseDescription = cheeseDescription;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
