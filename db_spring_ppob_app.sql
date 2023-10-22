-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 22, 2023 at 03:51 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_spring_ppob_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `test_balance`
--

CREATE TABLE `test_balance` (
  `id` bigint(20) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test_balance`
--

INSERT INTO `test_balance` (`id`, `balance`) VALUES
(1, 30),
(2, 0),
(3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `test_banner`
--

CREATE TABLE `test_banner` (
  `id` bigint(20) NOT NULL,
  `banner_image` varchar(255) DEFAULT NULL,
  `banner_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test_banner`
--

INSERT INTO `test_banner` (`id`, `banner_image`, `banner_name`, `description`) VALUES
(1, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 1', 'Lorem Ipsum Dolor sit amet'),
(2, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 2', 'Lorem Ipsum Dolor sit amet'),
(3, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 3', 'Lorem Ipsum Dolor sit amet'),
(4, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 4', 'Lorem Ipsum Dolor sit amet'),
(5, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 5', 'Lorem Ipsum Dolor sit amet'),
(6, 'https://nutech-integrasi.app/dummy.jpg', 'Banner 6', 'Lorem Ipsum Dolor sit amet');

-- --------------------------------------------------------

--
-- Table structure for table `test_membership`
--

CREATE TABLE `test_membership` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `balance_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test_membership`
--

INSERT INTO `test_membership` (`id`, `email`, `first_name`, `last_name`, `password`, `profile_image`, `balance_id`) VALUES
(1, 'a@a.a', 'a', 'a', '$2a$10$JuihZKGWk5k2vVRstgE4/eb54bClQ7bByOMrYfkjUM5cVyla/GKmq', NULL, 1),
(2, 'b@b.b', 'b', 'b', '$2a$10$DTvLPk7XJHmkpTlr9XzvluZNA76HAFQZ9blYb89COBIk9COgMxn4u', NULL, 2),
(3, 'c@c.c', 'c', 'c', '$2a$10$nzEVlXqvgfBc5ouR28xfZOg8L5cTRbj4aYhFSzfeK3rlqjs57Z3Me', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `test_service`
--

CREATE TABLE `test_service` (
  `service_code` varchar(255) NOT NULL,
  `service_icon` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `service_tariff` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `test_service`
--

INSERT INTO `test_service` (`service_code`, `service_icon`, `service_name`, `service_tariff`) VALUES
('MUSIK', 'https://nutech-integrasi.app/dummy.jpg', 'Musik Berlangganan', 50000),
('PAJAK', 'https://nutech-integrasi.app/dummy.jpg', 'Pajak PBB', 40000),
('PAKET_DATA', 'https://nutech-integrasi.app/dummy.jpg', 'Paket data', 50000),
('PDAM', 'https://nutech-integrasi.app/dummy.jpg', 'PDAM Berlangganan', 40000),
('PGN', 'https://nutech-integrasi.app/dummy.jpg', 'PGN Berlangganan', 50000),
('PLN', 'https://nutech-integrasi.app/dummy.jpg', 'Listrik', 10000),
('PULSA', 'https://nutech-integrasi.app/dummy.jpg', 'Pulsa', 40000),
('QURBAN', 'https://nutech-integrasi.app/dummy.jpg', 'Qurban', 200000),
('TV', 'https://nutech-integrasi.app/dummy.jpg', 'TV Berlangganan', 50000),
('VOUCHER_GAME', 'https://nutech-integrasi.app/dummy.jpg', 'Voucher Game', 100000),
('VOUCHER_MAKANAN', 'https://nutech-integrasi.app/dummy.jpg', 'Voucher Makanan', 100000),
('ZAKAT', 'https://nutech-integrasi.app/dummy.jpg', 'Zakat', 300000);

-- --------------------------------------------------------

--
-- Table structure for table `test_transaction`
--

CREATE TABLE `test_transaction` (
  `id` bigint(20) NOT NULL,
  `created_on` varchar(255) DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `membership_id` bigint(20) DEFAULT NULL,
  `service_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `test_balance`
--
ALTER TABLE `test_balance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test_banner`
--
ALTER TABLE `test_banner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test_membership`
--
ALTER TABLE `test_membership`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfecb1q246pfhuyr6jxdp80ixf` (`balance_id`);

--
-- Indexes for table `test_service`
--
ALTER TABLE `test_service`
  ADD PRIMARY KEY (`service_code`);

--
-- Indexes for table `test_transaction`
--
ALTER TABLE `test_transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKabm9u9r1trm33glxofcf2mkd0` (`membership_id`),
  ADD KEY `FKt3tavx1uxum14ihuhcgu3mjvu` (`service_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `test_balance`
--
ALTER TABLE `test_balance`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `test_banner`
--
ALTER TABLE `test_banner`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `test_membership`
--
ALTER TABLE `test_membership`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `test_transaction`
--
ALTER TABLE `test_transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `test_membership`
--
ALTER TABLE `test_membership`
  ADD CONSTRAINT `FKfecb1q246pfhuyr6jxdp80ixf` FOREIGN KEY (`balance_id`) REFERENCES `test_balance` (`id`);

--
-- Constraints for table `test_transaction`
--
ALTER TABLE `test_transaction`
  ADD CONSTRAINT `FKabm9u9r1trm33glxofcf2mkd0` FOREIGN KEY (`membership_id`) REFERENCES `test_membership` (`id`),
  ADD CONSTRAINT `FKt3tavx1uxum14ihuhcgu3mjvu` FOREIGN KEY (`service_code`) REFERENCES `test_service` (`service_code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
