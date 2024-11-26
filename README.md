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
-- Insertar clientes
INSERT INTO Usuario (ID_USUARIO, PASS) VALUES (1, 'pass1'), (2, 'pass2'), (3, 'pass3');
INSERT INTO Cliente (ID_USUARIO, NOMBRE, APELLIDOS, DNI) VALUES
(1, 'Juan', 'Pérez', '12345678A'),
(2, 'Ana', 'López', '23456789B'),
(3, 'Luis', 'Martínez', '34567890C');

-- Insertar restaurantes y direcciones
INSERT INTO Usuario (ID_USUARIO, PASS) VALUES (4, 'passRest1'), (5, 'passRest2'), (6, 'passRest3'), (7, 'passRest4'), (8, 'passRest5');
INSERT INTO Restaurante (ID_USUARIO, NOMBRE, CIF) VALUES
(4, 'La Tapa Elegante', 'CIF123'),
(5, 'Ole y Sazón', 'CIF234'),
(6, 'Flamenco Bistro', 'CIF345'),
(7, 'La Cocina del Sol', 'CIF456'),
(8, 'El Patio Español', 'CIF567');

INSERT INTO Direccion (ID, CALLE, CODIGO_POSTAL, COMPLEMENTO, MUNICIPIO, NUMERO, RESTAURANTE_ID) VALUES
(1, 'Calle Mayor', 28001, '', 'Madrid', 1, 4),
(2, 'Gran Vía', 28013, 'Piso 1', 'Madrid', 2, 4),
(3, 'Passeig de Gracia', 08007, '', 'Barcelona', 3, 5),
(4, 'Via del Corso', 00186, 'Piso 2', 'Roma', 4, 5),
(5, 'Friedrichstraße', 10117, '', 'Berlín', 5, 6),
(6, 'Kurfürstendamm', 10707, 'Piso 3', 'Berlín', 6, 6),
(7, 'Calle de Alcalá', 28014, '', 'Madrid', 7, 7),
(8, 'Calle de Serrano', 28001, 'Piso 4', 'Madrid', 8, 7),
(9, 'Las Ramblas', 08002, '', 'Barcelona', 9, 8),
(10, 'Calle de Aragón', 08009, 'Piso 5', 'Barcelona', 10, 8);

-- Insertar cartas de menú
INSERT INTO Carta_Menu (ID, NOMBRE, RESTAURANTE_ID) VALUES
(1, 'Tapas y Entradas', 4),
(2, 'Platos Principales', 4),
(3, 'Postres y Bebidas', 4),
(4, 'Tapas y Entradas', 5),
(5, 'Platos Principales', 5),
(6, 'Postres y Bebidas', 5),
(7, 'Tapas y Entradas', 6),
(8, 'Platos Principales', 6),
(9, 'Postres y Bebidas', 6),
(10, 'Tapas y Entradas', 7),
(11, 'Platos Principales', 7),
(12, 'Postres y Bebidas', 7),
(13, 'Tapas y Entradas', 8),
(14, 'Platos Principales', 8),
(15, 'Postres y Bebidas', 8);

-- Insertar items en los menús
INSERT INTO Item_Menu (ID, NOMBRE, PRECIO, TIPO, CARTA_MENU_ID) VALUES
(1, 'Café', 1.50, 'Bebida', 1),
(2, 'Tostadas', 2.00, 'Comida', 1),
(3, 'Zumo de Naranja', 2.50, 'Bebida', 1),
(4, 'Croissant', 1.80, 'Comida', 1),

(5, 'Ensalada', 4.50, 'Entrante', 2),
(6, 'Sopa', 3.50, 'Entrante', 2),
(7, 'Pollo asado', 7.50, 'Principal', 2),
(8, 'Flan', 2.00, 'Postre', 2),

(9, 'Hamburguesa', 5.50, 'Principal', 3),
(10, 'Pizza', 6.50, 'Principal', 3),
(11, 'Refresco', 1.80, 'Bebida', 3),
(12, 'Helado', 2.20, 'Postre', 3),

(13, 'Té', 1.50, 'Bebida', 4),
(14, 'Sándwich', 2.50, 'Comida', 4),
(15, 'Baguette', 3.00, 'Comida', 4),
(16, 'Panqueque', 2.50, 'Comida', 4),

(17, 'Ensaladilla', 4.00, 'Entrante', 5),
(18, 'Crema de Verduras', 3.50, 'Entrante', 5),
(19, 'Solomillo', 8.00, 'Principal', 5),
(20, 'Tarta de Queso', 2.50, 'Postre', 5),

-- Continúa con los items para los demás menús según la estructura proporcionada
(21, 'Tapas Variadas', 4.00, 'Entrante', 6),
(22, 'Gambas al Ajillo', 6.50, 'Entrante', 6),
(23, 'Paella', 10.00, 'Principal', 6),
(24, 'Crema Catalana', 3.50, 'Postre', 6),

(25, 'Gazpacho', 3.00, 'Entrante', 7),
(26, 'Pulpo a la Gallega', 9.00, 'Principal', 7),
(27, 'Vino Tinto', 2.50, 'Bebida', 7),
(28, 'Churros con Chocolate', 3.00, 'Postre', 7);

