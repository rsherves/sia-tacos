package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.Order;
import tacos.Taco;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

  private final SimpleJdbcInsert orderInserter;
  private final SimpleJdbcInsert orderTacoInserter;

  public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
    this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("Taco_Order")
        .usingGeneratedKeyColumns("id");
    this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("Taco_Order_Tacos");
  }


  @Override
  public Order save(Order order) {
    order.setPlacedAt(LocalDateTime.now());
    long orderId = saveOrderDetails(order);
    order.setId(orderId);
    List<Taco> tacos = order.getTacos();
    for (Taco taco : tacos) {
      saveOrderTaco(orderId, taco);
    }

    return order;
  }

  private long saveOrderDetails(Order order) {
    Map<String, Object> values = Map.of(
        "deliveryName", order.getName(),
        "deliveryStreet", order.getStreet(),
        "deliveryCity", order.getCity(),
        "deliveryState", order.getState(),
        "deliveryZip", order.getZip(),
        "ccNumber", order.getCcNumber(),
        "ccExpiration", order.getCcExpiration(),
        "ccCVV", order.getCcCVV(),
        "placedAt", Timestamp.valueOf(order.getPlacedAt())
    );
    return orderInserter.executeAndReturnKey(values).longValue();
  }

  private void saveOrderTaco(long orderId, Taco taco) {
    Map<String, Object> values = Map.of(
        "tacoOrder", orderId,
        "taco", taco.getId());
    orderTacoInserter.execute(values);
  }
}
