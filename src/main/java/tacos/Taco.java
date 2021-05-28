package tacos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Taco {

  private long id;
  private LocalDateTime createdAt;

  @NotNull
  @Size(min=5, message = "Name must be at least 5 characters long")
  private String name;

  @NotEmpty(message = "You must choose at least one ingredient")
  private List<String> ingredients = Collections.emptyList();
}
