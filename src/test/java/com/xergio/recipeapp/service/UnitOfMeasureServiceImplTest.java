package com.xergio.recipeapp.service;

import com.xergio.recipeapp.commands.UnitOfMeasureCommand;
import com.xergio.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.xergio.recipeapp.domian.UnitOfMeasure;
import com.xergio.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {



    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    private UnitOfMeasureService service;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    private void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    private void listAllUoms() throws Exception {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        //when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
       // Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        //then
       //assertEquals(2, commands.size());
        //verify(unitOfMeasureRepository, times(1)).findAll();
    }

}