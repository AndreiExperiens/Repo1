package com.exampleRecipes.converters;

import com.exampleRecipes.commands.EquipmentCommand;
import com.exampleRecipes.domain.Equipment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EquipmentToEquipmentCommand implements Converter<Equipment, EquipmentCommand> {

  private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public EquipmentToEquipmentCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public EquipmentCommand convert(Equipment equipment) {
        if(equipment == null){
            return null;
        }
        EquipmentCommand equipmentCommand = new EquipmentCommand();
        equipmentCommand.setId(equipment.getId());
        equipmentCommand.setAmount(equipment.getAmount());
        equipmentCommand.setDescription(equipment.getDescription());
        equipmentCommand.setUnitOfMeasure(uomConverter.convert(equipment.getUam()));
        return equipmentCommand;
    }
}
