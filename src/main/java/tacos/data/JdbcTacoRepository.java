package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);
    for (String ingredient : taco.getIngredients()) {
      saveTacoIngredient(ingredient, tacoId);
    }

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    return 0; // TODO
  }

  private void saveTacoIngredient(String ingredient, long tacoId) {
    // TODO
  }

}
