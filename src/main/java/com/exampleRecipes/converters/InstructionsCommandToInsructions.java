package com.exampleRecipes.converters;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.domain.Instructions;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class InstructionsCommandToInsructions implements Converter<InstructionsCommand, Instructions> {

    private final CategoryCommandToCategory categoryConveter;
    private final EquipmentCommandToEquipment equipmentConverter;
    private final NotesCommandToNotes notesConverter;

    public InstructionsCommandToInsructions(CategoryCommandToCategory categoryConveter, EquipmentCommandToEquipment equipmentConverter, NotesCommandToNotes notesConverter) {
        this.categoryConveter = categoryConveter;
        this.equipmentConverter = equipmentConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Instructions convert(InstructionsCommand source) {
        if(source == null){
            return null;
        }

        final Instructions instructions = new Instructions();
        instructions.setId(source.getId());
        instructions.setPregTime(source.getPregTime());
        instructions.setInstTime(source.getInstTime());
        instructions.setDescription(source.getDescription());
        instructions.setDifficulty(source.getDifficulty());
        instructions.setDirections(source.getDirections());
        instructions.setServings(source.getServings());
        instructions.setSource(source.getSource());
        instructions.setUrl(source.getUrl());
        instructions.setNotes(notesConverter.convert(source.getNotes()));

        if(source.getCategories() != null && source.getCategories().size() > 0 ){
            source.getCategories()
                    .forEach(category -> instructions.getCategories().add(categoryConveter.convert(category)));
        }
        if (source.getEquipaments() != null && source.getEquipaments().size() > 0){
            source.getEquipaments()
                    .forEach(equipment -> instructions.getEquipments().add(equipmentConverter.convert(equipment)));
        }

        return instructions;
    }
}
