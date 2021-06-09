package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public record Ingredient(@Id String id, String name, Type type) {

  // No args constructor required by JPA, can be private though
  // Lombok `@NoArgsConstructor` cannot be applied to records
  private Ingredient() {
    this(null, null, null);
  }

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}