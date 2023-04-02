package com.exampleRecipes.converters;

import com.exampleRecipes.commands.EquipmentCommand;
import com.exampleRecipes.domain.Equipment;
import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class EquipmentToEquipmentCommandTest {

    public static final Instructions INSTRUCTIONS = new Instructions();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long UOM_ID = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);


    EquipmentToEquipmentCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new EquipmentToEquipmentCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Equipment()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        Equipment equipment = new Equipment();
        equipment.setId(ID_VALUE);
        equipment.setInstructions(INSTRUCTIONS);
        equipment.setAmount(AMOUNT);
        equipment.setDescription(DESCRIPTION);
        equipment.setUam(null);
        //when
        EquipmentCommand equipmentCommand = converter.convert(equipment);
        //then
        assertNull(equipmentCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, equipmentCommand.getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, equipmentCommand.getAmount());
        assertEquals(DESCRIPTION, equipmentCommand.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        //given
        Equipment equipment = new Equipment();
        equipment.setId(ID_VALUE);
        equipment.setInstructions(INSTRUCTIONS);
        equipment.setAmount(AMOUNT);
        equipment.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        equipment.setUam(uom);
        //when
        EquipmentCommand ingredientCommand = converter.convert(equipment);
        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}