package tacos;

public record Ingredient(String id, String name, Type type) {

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}