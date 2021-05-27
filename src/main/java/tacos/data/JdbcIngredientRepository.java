package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbcTemplate.query(
        "SELECT id, name, type FROM ingredient",
        this::toIngredient);
  }

  @Override
  public Ingredient findOne(String id) {
    return jdbcTemplate.queryForObject(
        "SELECT id, name, type FROM ingredient WHERE id = ?",
        this::toIngredient,
        id);
  }

  private Ingredient toIngredient(ResultSet resultSet, int rowNum) throws SQLException {
    return new Ingredient(
        resultSet.getString("id"),
        resultSet.getString("name"),
        Ingredient.Type.valueOf(resultSet.getString("type")));
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbcTemplate.update(
        "INSERT INTO ingredient (id, name, type) VALUES (?, ?, ?)",
        ingredient.id(),
        ingredient.name(),
        ingredient.type());
    return ingredient;
  }
}
