package com.exampleRecipes.repositories;

import com.exampleRecipes.domain.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PriceRepository extends CrudRepository<Price, Long> {

    Optional<Price> findByPrice(Integer pricesRecepi);

}
