package com.exampleRecipes.boostrap;



import com.exampleRecipes.domain.*;
import com.exampleRecipes.repositories.CategoryRepository;
import com.exampleRecipes.repositories.InstructionsRepository;
import com.exampleRecipes.repositories.PriceRepository;
import com.exampleRecipes.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class InstructionsBoostrap implements ApplicationListener<ContextRefreshedEvent> {
    //Fist Step
    //Cream obiecte private si finale pentru  interfetele din repositories
    //Adaugam constructorul pentru a le initializa
    private final CategoryRepository categoryRepository;
    private final InstructionsRepository instructionsRepository ;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final PriceRepository priceRepository;


    public InstructionsBoostrap(CategoryRepository categoryRepository, InstructionsRepository instructionsRepository, UnitOfMeasureRepository unitOfMeasureRepository, PriceRepository priceRepository) {
        this.categoryRepository = categoryRepository;
        this.instructionsRepository = instructionsRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.priceRepository = priceRepository;
    }

    //Third step
    // implementam metoda
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        instructionsRepository.saveAll(getInstructions());
        log.debug("Loading Boostrap Data");
    }


        //Four Step , se modificain in functie de cerinta , de ce vrem sa obtinem
       private List<Instructions> getInstructions(){

            List<Instructions> instructionst = new ArrayList<>(3);


           Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

           if(!eachUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

           if(!tableSpoonUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

           if(!teaSpoonUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

           if(!dashUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

           if(!pintUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

           if(!cupsUomOptional.isPresent()){
               throw new RuntimeException("Expected UOM Not Found");
           }

           //get optionals
           UnitOfMeasure eachUom = eachUomOptional.get();
           UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
           UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
           UnitOfMeasure dashUom = dashUomOptional.get();
           UnitOfMeasure pintUom = pintUomOptional.get();
           UnitOfMeasure cupsUom = cupsUomOptional.get();

           //get Categories
           Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

           if(!americanCategoryOptional.isPresent()){
               throw new RuntimeException("Expected Category Not Found");
           }

           Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

           if(!mexicanCategoryOptional.isPresent()){
               throw new RuntimeException("Expected Category Not Found");
           }

           Category americanCategory = americanCategoryOptional.get();
           Category mexicanCategory = mexicanCategoryOptional.get();


           //Yummy Guac
           Instructions guacInstructions = new Instructions();
           guacInstructions.setDescription("Quesadilla cu pui");
           guacInstructions.setPregTime(10);
           guacInstructions.setInstTime(15);
           guacInstructions.setDifficulty(Difficulty.MODERATE);
           guacInstructions.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                   "\n" +
                   "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                   "\n" +
                   "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                   "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                   "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                   "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                   "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                   "\n" +
                   "\n" +
                   "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

           Notes guacNotes = new Notes();
           guacNotes.setInstructNotes("Carnea de pui se taie bucățele, se pune într-un bol, se adaugă uleiul de măsline, condimentele, se masează apoi se pun la prăjit într-o tigaie și se lasă la gătit până ce se pătrunde bine și rumenește.\n" +
                           "\n" +
                           "În altă tigaie se pun la călit ardeii și ceapa verde curățată și mărunțită, până ce devine translucidă.\n" +
                           "\n" +
                           "Apoi se adaugă și bucățelele de bacon și pui, se amestecă ușor și se lasă la gătit cca. 10 minute le foc mic.\n" +
                           "\n" +
                           "Foaia de tortilla se pune la încălzit într-o tigaie non aderentă cu un strop de ulei de măsline, apoi pe jumătate din foaie se pun 2 -3 linguri de umplutură, mozzarella rasă și sos de roșii, apoi se îndoaie jumătatea liberă, se presară și se lasă la gătit ep fiecare parte cca. 3-4 minute.\n" +
                           "\n" +
                           "Se servește imediat, fierbinte, ca atare sau cu adaos de sos/salată.\n"+
                   "\n" +
                   "\n" +
                   "Read more:https://www.bucataras.ro/retete/quesadilla-cu-pui-ardei-si-ceapa-verde-94219.html");
           guacNotes.setInstructions(guacInstructions);
           guacInstructions.setNotes(guacNotes);

           guacInstructions.getEquipment().add(new Equipment("ripe avocados", new BigDecimal(2), eachUom, guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("Kosher salt ", new BigDecimal(".5"), eachUom, guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom,guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom,guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom,guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("Cilantro", new BigDecimal(2), tableSpoonUom,guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("freshly grated black pepper", new BigDecimal(2), dashUom,guacInstructions));
           guacInstructions.getEquipment().add(new Equipment("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom,guacInstructions));


           guacInstructions.getCategories().add(americanCategory);
           guacInstructions.getCategories().add(mexicanCategory);

           guacInstructions.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
           guacInstructions.setServings(4);
           guacInstructions.setSource("Simply Recipes");

           //add to return list
           instructionst.add(guacInstructions);
           //add to return list
           instructionst.add(guacInstructions);

           //Cimiceanga
           Instructions tacosInstructions2 = new Instructions();
           tacosInstructions2.setDescription("Chimichanga");
           tacosInstructions2.setPregTime(9);
           tacosInstructions2.setInstTime(20);
           tacosInstructions2.setDifficulty(Difficulty.EASY);


           tacosInstructions2.setDirections("Chimichanga is a Mexican appetizer or entre that includes a tortilla filled with a meat or bean mixture, typically deep fried to make it crispy on the outside. Chimichangas are basically deep-fried burritos. They’re usually served topped with salsa, sour cream, or guacamole, and a side of Mexican rice.\n" +
                   "\n" +
                   "When I make chimichangas, I prefer to bake them or pan fry them in a little bit of oil, because it’s healthier, and I don’t usually have time to go through the effort and mess it takes to deep fry them.");

           tacosInstructions2.setUrl("https://tastesbetterfromscratch.com/chicken-chimichangas/");
           tacosInstructions2.setServings(5);
           tacosInstructions2.setSource("Simply Recipes");

           Notes chimicangaNotes = new Notes();
           chimicangaNotes.setInstructNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                   "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                   "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                   "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                   "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                   "\n" +
                   "\n" +
                   "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
           chimicangaNotes.setInstructions(tacosInstructions2);
           tacosInstructions2.setNotes(chimicangaNotes);




           tacosInstructions2.getEquipment().add(new Equipment("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Dried Oregano", new BigDecimal(1), teapoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Dried Cumin", new BigDecimal(1), teapoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Sugar", new BigDecimal(1), teapoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Salt", new BigDecimal(".5"), teapoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosInstructions2));
           tacosInstructions2.getEquipment().add(new Equipment("small corn tortillasr", new BigDecimal(8), eachUom, tacosInstructions2));


           tacosInstructions2.getCategories().add(americanCategory);
           tacosInstructions2.getCategories().add(mexicanCategory);

           instructionst.add(tacosInstructions2);

           //Yummy Tacos
           Instructions tacosInstructions = new Instructions();
           tacosInstructions.setDescription("Spicy Grilled Chicken Taco");
           tacosInstructions.setPregTime(9);
           tacosInstructions.setInstTime(20);
           tacosInstructions.setDifficulty(Difficulty.EASY);

           tacosInstructions.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                   "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                   "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                   "\n" +
                   "\n" +
                   "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                   "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                   "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                   "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                   "\n" +
                   "\n" +
                   "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

           tacosInstructions.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
           tacosInstructions.setServings(4);
           tacosInstructions.setSource("Simply Recipes");


           Notes tacoNotes = new Notes();
           tacoNotes.setInstructNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                   "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                   "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                   "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                   "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                   "\n" +
                   "\n" +
                   "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
           tacoNotes.setInstructions(tacosInstructions);
           tacosInstructions.setNotes(tacoNotes);


           tacosInstructions.getEquipment().add(new Equipment("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Dried Oregano", new BigDecimal(1), teapoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Dried Cumin", new BigDecimal(1), teapoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Sugar", new BigDecimal(1), teapoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Salt", new BigDecimal(".5"), teapoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("finely grated orange zestr", new BigDecimal(1), tableSpoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Olive Oil", new BigDecimal(2), tableSpoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("small corn tortillasr", new BigDecimal(8), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("packed baby arugula", new BigDecimal(3), cupsUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("medium ripe avocados, slic", new BigDecimal(2), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("radishes, thinly sliced", new BigDecimal(4), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("red onion, thinly sliced", new BigDecimal(".25"), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, tacosInstructions));
           tacosInstructions.getEquipment().add(new Equipment("lime, cut into wedges", new BigDecimal(4), eachUom, tacosInstructions));

           tacosInstructions.getCategories().add(americanCategory);
           tacosInstructions.getCategories().add(mexicanCategory);

           instructionst.add(tacosInstructions);
           return instructionst;





       }
       }



