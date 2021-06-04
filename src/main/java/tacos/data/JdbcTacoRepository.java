package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

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
    taco.setCreatedAt(LocalDateTime.now());
    var pscFactory = new PreparedStatementCreatorFactory(
        "INSERT INTO taco (name, createdAt) VALUES (?, ?)",
        Types.VARCHAR,
        Types.TIMESTAMP);
    pscFactory.setReturnGeneratedKeys(true);

    var psc = pscFactory.newPreparedStatementCreator(
        List.of(
            taco.getName(),
            Timestamp.valueOf(taco.getCreatedAt())));

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(psc, keyHolder);

    return keyHolder.getKey().longValue();
  }

  private void saveTacoIngredient(String ingredientId, long tacoId) {
    jdbcTemplate.update(
        "INSERT INTO taco_ingredients (taco, ingredient) VALUES (?, ?)",
        tacoId,
        ingredientId);
  }

}
