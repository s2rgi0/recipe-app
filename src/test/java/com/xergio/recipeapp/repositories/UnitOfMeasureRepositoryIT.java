package com.xergio.recipeapp.repositories;

import com.xergio.recipeapp.domian.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    @DirtiesContext
    void findByDescription() {

        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon",unitOfMeasure.get().getDescription());

    }


    @Test
    void findByDescriptionCup() {

        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup",unitOfMeasure.get().getDescription());

    }
}