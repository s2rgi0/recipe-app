package com.xergio.recipeapp.domian;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
