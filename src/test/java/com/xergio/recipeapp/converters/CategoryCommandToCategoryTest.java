package com.xergio.recipeapp.converters;

import com.xergio.recipeapp.commands.CategoryCommand;
import com.xergio.recipeapp.domian.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory conveter;

    @BeforeEach
    void setUp() {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}