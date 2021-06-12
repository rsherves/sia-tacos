package tacos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

  @Id
  private final String id;

  private final String name;

  /* `@Enumerated(EnumType.STRING)` tells JPA to map enum attributes to
      `varchar` DB columns (expects `integer` columns otherwise).
      Furthermore, if JPA/Hibernate DDL is enabled, a column of type `integer`
      will be created by default. `@Enumerated(EnumType.STRING)` will result in `varchar`
      columns created instead.
      For info and alternatives see: https://www.baeldung.com/jpa-persisting-enums-in-jpa */
  @Enumerated(EnumType.STRING)
  private final Type type;

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}