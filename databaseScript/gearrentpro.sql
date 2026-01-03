-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gearrentpro
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `contact` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'PAN0','Panadura Branch','Panadura address','0771234567'),(4,'PAN','Panadura Branch','Panadura, Sri Lanka','038-2244556'),(5,'GAL','Galle Branch','Galle, Sri Lanka','091-2233445'),(6,'COL','Colombo Branch','Colombo 07, Sri Lanka','011-2345678');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price_factor` decimal(6,2) NOT NULL,
  `weekend_multiplier` decimal(6,2) NOT NULL,
  `late_fee` decimal(10,2) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (15,'Camera','Professional DSLR and mirrorless cameras',1.00,1.10,2500.00,1),(16,'Lens','Wide, telephoto and prime lenses',0.80,1.10,1500.00,1),(17,'Drone','Aerial drones for photography and video',1.50,1.20,5000.00,1),(18,'Lighting','Studio and outdoor lighting kits',0.70,1.00,1200.00,1),(19,'Audio','Microphones and audio recording equipment',0.60,1.00,1000.00,1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `nic_or_passport` varchar(20) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `membership` enum('REGULAR','SILVER','GOLD') DEFAULT 'REGULAR',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nic_or_passport` (`nic_or_passport`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Nimal Perera','901234567V','0771234567','nimal@gmail.com','Colombo','REGULAR','2026-01-03 20:12:43'),(2,'Kamal Silva','881234567V','0772233445','kamal@gmail.com','Panadura','SILVER','2026-01-03 20:12:43'),(3,'Sunil Fernando','P1234567','0719988776','sunil@gmail.com','Galle','GOLD','2026-01-03 20:12:43'),(4,'Amal Jayasinghe','931234567V','0765566778','amal@gmail.com','Kalutara','REGULAR','2026-01-03 20:12:43'),(5,'Saman Wijesinghe','871234567V','0754433221','saman@gmail.com','Negombo','SILVER','2026-01-03 20:12:43'),(6,'Ruwan Perera','941234567V','0789988776','ruwan@gmail.com','Colombo','GOLD','2026-01-03 20:12:43'),(7,'Isuru Silva','961234567V','0712233445','isuru@gmail.com','Panadura','REGULAR','2026-01-03 20:12:43'),(8,'Chathura Fernando','951234567V','0723344556','chathura@gmail.com','Galle','SILVER','2026-01-03 20:12:43'),(9,'Dinesh Bandara','901112223V','0776677889','dinesh@gmail.com','Matara','REGULAR','2026-01-03 20:12:43'),(10,'Tharindu Perera','921998877V','0761122334','tharindu@gmail.com','Kandy','GOLD','2026-01-03 20:12:43');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `branch_id` int NOT NULL,
  `category_id` int NOT NULL,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `purchase_year` int NOT NULL,
  `base_price` decimal(10,2) NOT NULL,
  `deposit` decimal(10,2) NOT NULL,
  `status` enum('AVAILABLE','RESERVED','RENTED','UNDER_MAINTENANCE') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
  CONSTRAINT `equipment_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (5,1,15,'Canon','EOS R5',2022,15000.00,100000.00,'AVAILABLE'),(6,1,15,'Sony','A7 IV',2023,14000.00,95000.00,'RENTED'),(7,1,16,'Canon','RF 24-70mm',2021,6000.00,40000.00,'AVAILABLE'),(8,1,17,'DJI','Mavic Air 2',2022,20000.00,150000.00,'UNDER_MAINTENANCE'),(13,4,15,'Nikon','Z6 II',2021,13000.00,90000.00,'AVAILABLE'),(14,4,16,'Sigma','35mm Art',2020,5000.00,35000.00,'AVAILABLE'),(15,4,17,'Godox','SL60W',2022,4000.00,25000.00,'RESERVED'),(16,4,18,'Rode','Wireless GO II',2023,3500.00,20000.00,'AVAILABLE'),(17,5,16,'Sony','FX3',2023,18000.00,120000.00,'AVAILABLE'),(18,5,17,'DJI','Mini 3 Pro',2023,16000.00,110000.00,'RENTED'),(19,5,18,'Aputure','Light Storm 120D',2021,7000.00,50000.00,'AVAILABLE'),(20,5,19,'Zoom','H6 Recorder',2022,4500.00,30000.00,'AVAILABLE');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','BRANCH_MANAGER','STAFF') NOT NULL,
  `branch_id` int DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_user_branch` (`branch_id`),
  CONSTRAINT `fk_user_branch` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,'admin','admin123','ADMIN',NULL,1),(2,'manager_pan','manager123','BRANCH_MANAGER',1,1),(3,'staff_pan','staff123','STAFF',1,1);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gearrentpro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-04  1:59:03
