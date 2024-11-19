# Ramas que hay que hacer
* Feature_Asignar_Repartidor
* Feature_Modificar_Menu (esto esta lo de modificar menú y modificar carta y dar de alta el menú y la carta)
* Feature_Marca_Favorito
* Feature_Realizar _Pago (lo que nos falta de hace pedido hasta q se haga el pago)
* Feature_Realizar_Entrega(desde la notificación hasta que entregue el pedido)
# Detalles a completar
* Poner lo de buscar restaurante como índice
* Poner las restricciones que dijo Naomi
# Código sql
INSERT INTO cliente (nombre, email, telefono) VALUES ('Juan Pérez', 'juan.perez@example.com', '123456789');
INSERT INTO cliente (nombre, email, telefono) VALUES ('María García', 'maria.garcia@example.com', '987654321');
INSERT INTO cliente (nombre, email, telefono) VALUES ('Carlos López', 'carlos.lopez@example.com', '456789123');

INSERT INTO restaurante (id, cif, nombre) VALUES (1, 'CIF123A', 'La Casa del Pollo');
INSERT INTO restaurante (id, cif, nombre) VALUES (2, 'CIF123B', 'El Rincón de Pepe');
INSERT INTO restaurante (id, cif, nombre) VALUES (3, 'CIF123C', 'La Marisquería');
INSERT INTO restaurante (id, cif, nombre) VALUES (4, 'CIF123D', 'Pizzería Bella Italia');

-- Menús para La Casa del Pollo
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Desayuno', 1);
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Almuerzo', 1);

-- Menús para El Rincón de Pepe
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Cena', 2);
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Postres', 2);

-- Menús para La Marisquería
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Mariscos', 3);
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Especial', 3);

-- Menús para Pizzería Bella Italia
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Pizzas', 4);
INSERT INTO carta_menu (nombre, restaurante_id) VALUES ('Menú Pastas', 4);

-- Items para Menú Desayuno (La Casa del Pollo)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Café', 1.50, 'Bebida', 1);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Tostada', 2.00, 'Comida', 1);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Zumo de Naranja', 2.50, 'Bebida', 1);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Croissant', 1.80, 'Comida', 1);

-- Items para Menú Almuerzo (La Casa del Pollo)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Ensalada', 5.00, 'Comida', 2);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Sopa', 3.50, 'Comida', 2);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pollo Asado', 8.00, 'Comida', 2);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Agua', 1.00, 'Bebida', 2);

-- Items para Menú Cena (El Rincón de Pepe)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pizza', 7.00, 'Comida', 3);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pasta', 6.50, 'Comida', 3);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Vino', 3.00, 'Bebida', 3);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Helado', 2.50, 'Postre', 3);

-- Items para Menú Postres (El Rincón de Pepe)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Tarta de Queso', 3.50, 'Postre', 4);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Brownie', 3.00, 'Postre', 4);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Café', 1.50, 'Bebida', 4);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Té', 1.20, 'Bebida', 4);

-- Items para Menú Mariscos (La Marisquería)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Gambas', 12.00, 'Comida', 5);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Mejillones', 10.00, 'Comida', 5);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Calamares', 11.00, 'Comida', 5);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pulpo', 13.00, 'Comida', 5);

-- Items para Menú Especial (La Marisquería)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Paella', 15.00, 'Comida', 6);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Arroz Negro', 14.00, 'Comida', 6);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Fideuá', 13.50, 'Comida', 6);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Sangría', 3.50, 'Bebida', 6);

-- Items para Menú Pizzas (Pizzería Bella Italia)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pizza Margarita', 8.00, 'Comida', 7);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pizza Pepperoni', 9.00, 'Comida', 7);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pizza Cuatro Quesos', 9.50, 'Comida', 7);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Pizza Vegetariana', 8.50, 'Comida', 7);

-- Items para Menú Pastas (Pizzería Bella Italia)
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Spaghetti Carbonara', 7.50, 'Comida', 8);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Lasaña', 8.00, 'Comida', 8);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Raviolis', 7.00, 'Comida', 8);
INSERT INTO item_menu (nombre, precio, tipo, carta_menu_id) VALUES ('Tiramisú', 4.00, 'Postre', 8);

-- Direcciones para La Casa del Pollo
INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Calle del Sauce', 110, 'Local 3', 28970, 'Humanes de Madrid', 1);

INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Avenida de las Flores', 21, 'Frente al parque', 28971, 'Griñón', 1);

-- Direcciones para El Rincón de Pepe
INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Calle Mayor', 5, '1ºB', 28013, 'Madrid', 2);

INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Plaza de España', 2, '2ºD', 28750, 'San Sebastián de los Reyes', 2);

-- Direcciones para La Marisquería
INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Paseo del Mar', 75, '', 28850, 'Torrejón de Ardoz', 3);

INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Calle del Puerto', 18, '3ºA', 28042, 'Madrid', 3);

-- Direcciones para Pizzería Bella Italia
INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Calle de Italia', 200, '', 28010, 'Madrid', 4);

INSERT INTO direccion (calle, numero, complemento, codigo_postal, municipio, restaurante_id) 
VALUES ('Avenida de Europa', 15, 'Local 5', 28224, 'Pozuelo de Alarcón', 4);
