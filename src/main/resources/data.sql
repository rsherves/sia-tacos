delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

insert into Ingredient (id, type, name) values ('FLTO', 'WRAP', 'Flour Tortilla');
insert into Ingredient (id, type, name) values ('COTO', 'WRAP', 'Corn Tortilla');
insert into Ingredient (id, type, name) values ('GRBF', 'PROTEIN', 'Ground Beef');
insert into Ingredient (id, type, name) values ('CARN', 'PROTEIN', 'Carnitas');
insert into Ingredient (id, type, name) values ('TMTO', 'VEGGIES', 'Diced Tomatoes');
insert into Ingredient (id, type, name) values ('LETC', 'VEGGIES', 'Lettuce');
insert into Ingredient (id, type, name) values ('CHED', 'CHEESE', 'Cheddar');
insert into Ingredient (id, type, name) values ('JACK', 'CHEESE', 'Monterrey Jack');
insert into Ingredient (id, type, name) values ('SLSA', 'SAUCE', 'Salsa');
insert into Ingredient (id, type, name) values ('SRCR', 'SAUCE', 'Sour Cream');