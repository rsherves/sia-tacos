package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = List.of(
        new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
    );
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
    model.addAttribute("design", new Taco());
    return "design";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(x -> x.type().equals(type))
        .toList();
  }

  @PostMapping
  public String processDesign(Taco design) {
    // TODO save the taco design... coming in chapter 3
    log.info("Processing Taco design: " + design);
    return "redirect:/orders/current";
  }
}
