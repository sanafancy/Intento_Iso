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
INSERT INTO Usuario (idUsuario, pass) VALUES (1, 'pass1'), (2, 'pass2'), (3, 'pass3');
INSERT INTO Cliente (idUsuario, nombre, apellidos, dni) VALUES 
(1, 'Juan', 'Pérez', '12345678A'),
(2, 'Ana', 'López', '23456789B'),
(3, 'Luis', 'Martínez', '34567890C');

-- Insertar restaurantes y direcciones
INSERT INTO Usuario (idUsuario, pass) VALUES (4, 'passRest1'), (5, 'passRest2'), (6, 'passRest3'), (7, 'passRest4'), (8, 'passRest5');
INSERT INTO Restaurante (idUsuario, nombre, cif) VALUES
(4, 'La Tapa Elegante', 'CIF123'),
(5, 'Ole y Sazón', 'CIF234'),
(6, 'Flamenco Bistro', 'CIF345'),
(7, 'La Cocina del Sol', 'CIF456'),
(8, 'El Patio Español', 'CIF567');

INSERT INTO Direccion (calle, numero, complemento, codigoPostal, municipio, restaurante_id) VALUES
('Calle Mayor', 1, '', 28001, 'Madrid', 4),
('Gran Vía', 2, 'Piso 1', 28013, 'Madrid', 4),
('Passeig de Gracia', 3, '', 08007, 'Barcelona', 5),
('Via del Corso', 4, 'Piso 2', 00186, 'Roma', 5),
('Friedrichstraße', 5, '', 10117, 'Berlín', 6),
('Kurfürstendamm', 6, 'Piso 3', 10707, 'Berlín', 6),
('Calle de Alcalá', 7, '', 28014, 'Madrid', 7),
('Calle de Serrano', 8, 'Piso 4', 28001, 'Madrid', 7),
('Las Ramblas', 9, '', 08002, 'Barcelona', 8),
('Calle de Aragón', 10, 'Piso 5', 08009, 'Barcelona', 8);

-- Insertar cartas de menú
INSERT INTO CartaMenu (nombre, restaurante_id) VALUES
('Tapas y Entradas', 4),
('Platos Principales', 4),
('Postres y Bebidas', 4),
('Tapas y Entradas', 5),
('Platos Principales', 5),
('Postres y Bebidas', 5),
('Tapas y Entradas', 6),
('Platos Principales', 6),
('Postres y Bebidas', 6),
('Tapas y Entradas', 7),
('Platos Principales', 7),
('Postres y Bebidas', 7),
('Tapas y Entradas', 8),
('Platos Principales', 8),
('Postres y Bebidas', 8);

-- Insertar items en los menús
INSERT INTO ItemMenu (nombre, precio, tipo, carta_menu_id) VALUES
('Café', 1.50, 'Bebida', 1),
('Tostadas', 2.00, 'Comida', 1),
('Zumo de Naranja', 2.50, 'Bebida', 1),
('Croissant', 1.80, 'Comida', 1),

('Ensalada', 4.50, 'Entrante', 2),
('Sopa', 3.50, 'Entrante', 2),
('Pollo asado', 7.50, 'Principal', 2),
('Flan', 2.00, 'Postre', 2),

('Hamburguesa', 5.50, 'Principal', 3),
('Pizza', 6.50, 'Principal', 3),
('Refresco', 1.80, 'Bebida', 3),
('Helado', 2.20, 'Postre', 3),

('Té', 1.50, 'Bebida', 4),
('Sándwich', 2.50, 'Comida', 4),
('Baguette', 3.00, 'Comida', 4),
('Panqueque', 2.50, 'Comida', 4),

('Ensaladilla', 4.00, 'Entrante', 5),
('Crema de Verduras', 3.50, 'Entrante', 5),
('Solomillo', 8.00, 'Principal', 5),
('Tarta de Queso', 2.50, 'Postre', 5),

-- Continúa con los items para los demás menús según la estructura proporcionada
('Tapas Variadas', 4.00, 'Entrante', 6),
('Gambas al Ajillo', 6.50, 'Entrante', 6),
('Paella', 10.00, 'Principal', 6),
('Crema Catalana', 3.50, 'Postre', 6),

('Gazpacho', 3.00, 'Entrante', 7),
('Pulpo a la Gallega', 9.00, 'Principal', 7),
('Vino Tinto', 2.50, 'Bebida', 7),
('Churros con Chocolate', 3.00, 'Postre', 7);

-- Añade más items a los menús de los otros restaurantes siguiendo la misma estructura

