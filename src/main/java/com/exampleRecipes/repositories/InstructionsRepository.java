package com.exampleRecipes.repositories;

import com.exampleRecipes.domain.Instructions;
import org.springframework.data.repository.CrudRepository;

public interface InstructionsRepository extends CrudRepository<Instructions,Long> {


}
