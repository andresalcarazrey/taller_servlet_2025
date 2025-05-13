SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;

USE taller_db;

DROP TABLE IF EXISTS TrabajoTaller;
DROP TABLE IF EXISTS Vehiculos;
DROP TABLE IF EXISTS Mecanicos;
DROP TABLE IF EXISTS Clientes;

CREATE TABLE `Clientes` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido1` varchar(40) NOT NULL,
  `apellido2` varchar(40) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Clientes` (`dni`, `nombre`, `apellido1`, `apellido2`, `telefono`, `email`) VALUES
('11223344A', 'Juan', 'Silo', 'Senovengo', '0034676767671', 'jsilose@gmail.com'),
('22334455B', 'Eva', 'Correve', 'Ydile', '0034676767672', 'correveydile@gmail.com'),
('33445566C', 'Araceli', 'Callay', 'Come', '0034676767673', 'aracallay@gmail.com'),
('44556677D', 'Teodoro', 'Teda', 'Buenastardes', '0034676767674', 'teoteda@gmail.com');


ALTER TABLE `Clientes`
  ADD PRIMARY KEY (`dni`);






CREATE TABLE `Mecanicos` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido1` varchar(40) NOT NULL,
  `apellido2` varchar(40) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `fecha_ant` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `Mecanicos` (`dni`, `nombre`, `apellido1`, `apellido2`, `telefono`, `email`, `fecha_ant`) VALUES
('99887766Z', 'Pedro', 'Sabelo', 'Quehace', '0034645455455', 'pedritosabio@gmail.com', '12/01/2000'),
('88776655J', 'Luisa', 'Pintalo', 'Bonito', '0034656765678', 'pintaycoloreaLuisi@gmail.com','23/10/2009'),
('77665544M', 'Adrian', 'Rompe', 'Motos', '0034676222679', 'rmotos_adri@gmail.com','18/05/2012');


ALTER TABLE `Mecanicos`
  ADD PRIMARY KEY (`dni`);









CREATE TABLE `Vehiculos` (
  `matricula` varchar(9) NOT NULL,
  `anio_fabr` int NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `tipo` varchar(1) NOT NULL,
  `dni_cliente` varchar(9) NOT NULL,
  FOREIGN KEY (dni_cliente) REFERENCES Clientes(dni)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Vehiculos` (`matricula`, `anio_fabr`, `descripcion`, `tipo`, `dni_cliente`) VALUES
('3443KZM', 2018, 'Ford Kuga 1.4', 'T','11223344A'),
('8678JLM', 2017, 'Opel Astra CDI Elegance 1.7', 'T','22334455B'),
('2189MJT', 2024, 'BMW R 1300 RS', 'M','33445566C'),
('7639LBC', 2019, 'Seat Ibiza 1.4 TSI', 'C','44556677D'),
('5656MMC', 2024, 'Mercedes eVito', 'F','11223344A'),
('9232HGT', 2014, 'Yamaha MT 125', 'M','33445566C');

ALTER TABLE `Vehiculos`
  ADD PRIMARY KEY (`matricula`);




CREATE TABLE TrabajoTaller (
    codigo VARCHAR(20) PRIMARY KEY,
    descripcion TEXT,
    coste_piezas DECIMAL(10,2),
    horas_trabajo DECIMAL(5,2),
    terminado BOOLEAN,
    cobrado BOOLEAN,
    matricula_vehiculo VARCHAR(9),
    dni_mecanico VARCHAR(9),
    FOREIGN KEY (matricula_vehiculo) REFERENCES Vehiculos(matricula),
    FOREIGN KEY (dni_mecanico) REFERENCES Mecanicos(dni)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




COMMIT;
