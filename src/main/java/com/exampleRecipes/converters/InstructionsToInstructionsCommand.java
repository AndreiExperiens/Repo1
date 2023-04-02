package com.exampleRecipes.converters;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.domain.Category;
import com.exampleRecipes.domain.Instructions;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class InstructionsToInstructionsCommand implements Converter<Instructions, InstructionsCommand> {
    private final CategoryToCategoryCommand categoryConveter;
    private final EquipmentToEquipmentCommand equipmentConverter;
    private final NotesToNotesCommand notesConverter;

    public InstructionsToInstructionsCommand(CategoryToCategoryCommand categoryConveter, EquipmentToEquipmentCommand equipmentConverter, NotesToNotesCommand notesConverter) {
        this.categoryConveter = categoryConveter;
        this.equipmentConverter = equipmentConverter;
        this.notesConverter = notesConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public InstructionsCommand convert(Instructions source) {
        if (source == null) {
            return null;
        }

        final InstructionsCommand command = new InstructionsCommand();
        command.setId(source.getId());
        command.setInstTime(source.getInstTime());
        command.setPregTime(source.getPregTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getEquipments() != null && source.getEquipments().size() > 0){
            source.getEquipments()
                    .forEach(equipment -> command.getEquipaments().add(equipmentConverter.convert(equipment)));
        }

        return command;
    }


}
