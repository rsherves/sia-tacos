package tacos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private LocalDateTime createdAt;

  @NotNull
  @Size(min=5, message = "Name must be at least 5 characters long")
  private String name;

  @NotEmpty(message = "You must choose at least one ingredient")
  @ManyToMany(targetEntity = Ingredient.class)
  private List<String> ingredients = Collections.emptyList();

  @PrePersist
  void createdAt() {
    this.createdAt = LocalDateTime.now();
  }
}
