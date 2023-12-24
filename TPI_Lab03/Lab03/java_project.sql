-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 12, 2023 at 03:52 PM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `cost` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `cost`, `description`, `expiration_date`, `name`) VALUES
(2, 10, 'the best', '2023-12-31 03:00:00.000000', 'bestie'),
(3, 5, 'better', '2023-12-23 03:00:00.000000', 'ex'),
(5, 30, 'порш', '2023-12-31 03:00:00.000000', 'porshe'),
(6, 10, 'Хонда', '2023-12-31 03:00:00.000000', 'Honda'),
(7, 10, 'Ламба', '2023-12-31 03:00:00.000000', 'Lamba'),
(8, 10, 'Ферра', '2023-12-31 03:00:00.000000', 'Ferra');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `activation_code` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `activation_code`, `active`, `email`, `login`, `password`, `role_id`) VALUES
(1, NULL, b'1', 'nikita.yashny@gmail.com', 'nikita', '1234', 1),
(2, NULL, b'1', 'nikita.yashny@gmail.com', 'nikitayashny', '1234', 2),
(3, NULL, b'1', 'nikita.yashny@gmail.com', 'nikitayashny2', '1234', 2),
(4, 'bdacf917-1315-45f2-8aae-e543b1cb8182', b'1', 'nikita.yashny@gmail.com', 'nikita3', '1234', 2),
(5, NULL, b'1', 'nikita.yashny@gmail.com', 'nikita4', '1234', 2),
(6, NULL, b'1', 'nikita.yashny@gmail.com', 'nikitayashny3', '1234', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_rent`
--

CREATE TABLE `user_rent` (
  `id` bigint(20) NOT NULL,
  `rent` bit(1) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_surname` varchar(255) DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_rent`
--

INSERT INTO `user_rent` (`id`, `rent`, `user_name`, `user_surname`, `car_id`, `user_id`) VALUES
(7, b'0', 'Nikita', 'Yashny', 2, 3),
(9, b'0', 'nikita', 'yashny', 3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Indexes for table `user_rent`
--
ALTER TABLE `user_rent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhxg47f6nrnw72viehgfwhx4ia` (`car_id`),
  ADD KEY `FKhq71vha18ysguh5gjdsg784cf` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_rent`
--
ALTER TABLE `user_rent`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `user_rent`
--
ALTER TABLE `user_rent`
  ADD CONSTRAINT `FKhq71vha18ysguh5gjdsg784cf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKhxg47f6nrnw72viehgfwhx4ia` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
