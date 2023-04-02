package com.exampleRecipes.converters;

import com.exampleRecipes.commands.EquipmentCommand;
import com.exampleRecipes.commands.UnitOfMeasureCommand;
import com.exampleRecipes.domain.Equipment;
import com.exampleRecipes.domain.Instructions;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;

import static org.junit.Assert.*;

public class EquipmentCommandToEquipmentTest {

    public static final Instructions INSTRUCTIONS = new Instructions();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    EquipmentCommandToEquipment converter;


    @Before
    public void setUp() throws Exception {
        converter = new EquipmentCommandToEquipment(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new EquipmentCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        EquipmentCommand command = new EquipmentCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Equipment equipment = converter.convert(command);

        //then
        assertNotNull(equipment);
        assertNotNull(equipment.getUam());
        assertEquals(ID_VALUE, equipment.getId());
        assertEquals(AMOUNT, equipment.getAmount());
        assertEquals(DESCRIPTION, equipment.getDescription());
        assertEquals(UOM_ID, equipment.getUam().getId());
    }
    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        EquipmentCommand command = new EquipmentCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();


        //when
        Equipment equipment = converter.convert(command);

        //then
        assertNotNull(equipment);
        assertNull(equipment.getUam());
        assertEquals(ID_VALUE, equipment.getId());
        assertEquals(AMOUNT, equipment.getAmount());
        assertEquals(DESCRIPTION, equipment.getDescription());

    }
    }
