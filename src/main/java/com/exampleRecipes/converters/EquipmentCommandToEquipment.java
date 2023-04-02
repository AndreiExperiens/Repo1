package com.exampleRecipes.converters;

import com.exampleRecipes.commands.EquipmentCommand;
import com.exampleRecipes.domain.Equipment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EquipmentCommandToEquipment implements Converter<EquipmentCommand, Equipment> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public EquipmentCommandToEquipment(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Equipment convert(EquipmentCommand source) {
        if(source == null){
            return null;
        }

        final Equipment equipment = new Equipment();
        equipment.setId(source.getId());
        equipment.setAmount(source.getAmount());
        equipment.setDescription(source.getDescription());
        equipment.setUam(uomConverter.convert(source.getUnitOfMeasure()));
        return equipment;

    }
}
