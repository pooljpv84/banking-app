-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-02-2025 a las 05:41:20
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bancodb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL,
  `edad` int(11) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `persona_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `edad`, `estado`, `genero`, `password`, `persona_id`) VALUES
(10, 30, b'1', 'Masculino', '1234', 10),
(11, 29, b'1', 'Femenino', '5678', 11),
(12, 32, b'1', 'Masculino', '1245', 12),
(25, 32, b'1', 'Masculino', '1234', 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` bigint(20) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` float DEFAULT NULL,
  `tipo_cuenta` enum('AHORRO','CORRIENTE') DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `estado`, `numero_cuenta`, `saldo_inicial`, `tipo_cuenta`, `cliente_id`) VALUES
(1, b'1', '478758', 2000, 'AHORRO', 10),
(2, b'1', '225487', 100, 'CORRIENTE', 11),
(3, b'1', '495878', 0, 'AHORRO', 12),
(4, b'1', '496825', 540, 'AHORRO', 11),
(5, b'1', '585545', 1000, 'CORRIENTE', 10),
(6, b'1', '666666', 10, 'AHORRO', 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo_disponible` float DEFAULT NULL,
  `tipo_movimiento` enum('DEPOSITO','RETIRO') DEFAULT NULL,
  `valor` float DEFAULT NULL,
  `cuenta_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id`, `fecha`, `saldo_disponible`, `tipo_movimiento`, `valor`, `cuenta_id`) VALUES
(14, '2025-02-10 17:22:17.000000', 1425, 'RETIRO', 575, 1),
(15, '2025-02-10 17:22:26.000000', 700, 'DEPOSITO', 600, 2),
(16, '2025-02-10 17:22:31.000000', 150, 'DEPOSITO', 150, 3),
(17, '2025-02-10 17:22:36.000000', 0, 'RETIRO', 540, 4),
(18, '2025-02-10 17:30:57.000000', 10, 'DEPOSITO', 10, 4),
(19, '2025-02-11 03:48:44.000000', 30, 'DEPOSITO', 20, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` bigint(20) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `direccion`, `identificacion`, `nombre`, `telefono`) VALUES
(10, 'Otavalo sn y principal', '1723456789', 'Jose Lema', '098254785'),
(11, 'Amazonas y NNUU', '2222222222', 'Marianela Montalvo', '097548965'),
(12, '13 junio y Equinoccial', '1111111111', 'Juan Osorio', '098874587'),
(24, 'casa', '2111111111', 'paul', '999999999');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6ek8bprfp08rlc1s7xt60itfq` (`persona_id`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK65yk2321jpusl3fk96lqehrli` (`cliente_id`);

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4moe88hxuohcysas5h70mdc09` (`cuenta_id`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FKqqdwv2x70kik01nxcgkxvh8ue` FOREIGN KEY (`persona_id`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `FK65yk2321jpusl3fk96lqehrli` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`);

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `FK4moe88hxuohcysas5h70mdc09` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
