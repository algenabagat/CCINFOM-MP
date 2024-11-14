-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `COUNTRY_ID` int NOT NULL,
  `COUNTRY_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `DISCOUNT_ID` int NOT NULL,
  `DISCOUNT_NAME` varchar(45) DEFAULT NULL,
  `DISCOUNT_PERCENTAGE` decimal(2,2) DEFAULT NULL,
  PRIMARY KEY (`DISCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` int NOT NULL,
  `EMPLOYEE_NAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(125) DEFAULT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `JOB_ID` int DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  `SALARY` decimal(8,2) DEFAULT NULL,
  `HIRE_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `HOTEL_ID_idx` (`HOTEL_ID`),
  KEY `JOB_ID_idx` (`JOB_ID`),
  CONSTRAINT `E_HOTEL_ID` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`),
  CONSTRAINT `E_JOB_ID` FOREIGN KEY (`JOB_ID`) REFERENCES `jobs` (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `GUEST_ID` int NOT NULL,
  `GUEST_NAME` varchar(45) DEFAULT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`GUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `HOTEL_ID` int NOT NULL,
  `HOTEL_NAME` varchar(45) DEFAULT NULL,
  `HOTEL_ROOM_COUNT` int DEFAULT NULL,
  `HOTEL_EMAIL` varchar(125) DEFAULT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `LOCATION_ID` int DEFAULT NULL,
  PRIMARY KEY (`HOTEL_ID`),
  KEY `LOCATION_ID_idx` (`LOCATION_ID`),
  CONSTRAINT `H_LOCATION_ID` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`LOCATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `JOB_ID` int NOT NULL,
  `JOB_NAME` varchar(45) DEFAULT NULL,
  `MIN_SALARY` decimal(8,2) DEFAULT NULL,
  `MAX_SALARY` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `LOCATION_ID` int NOT NULL,
  `STREET_ADDRESS` varchar(125) DEFAULT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `POSTAL_CODE` int DEFAULT NULL,
  `STATE_PROVINCE` varchar(45) DEFAULT NULL,
  `COUNTRY_ID` int DEFAULT NULL,
  PRIMARY KEY (`LOCATION_ID`),
  KEY `COUNTRY_ID_idx` (`COUNTRY_ID`),
  CONSTRAINT `L_COUNTRY_ID` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PAYMENT_ID` int NOT NULL,
  `RESERVATION_ID` int DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `AMOUNT` decimal(8,2) DEFAULT NULL,
  `DISCOUNT_ID` int DEFAULT NULL,
  `PAYMENT_METHOD_ID` int DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_ID`),
  KEY `DISCOUNT_ID_idx` (`DISCOUNT_ID`),
  KEY `PAYMENT_METHOD_ID_idx` (`PAYMENT_METHOD_ID`),
  KEY `RESERVATION_ID_idx` (`RESERVATION_ID`),
  CONSTRAINT `P_DISCOUNT_ID` FOREIGN KEY (`DISCOUNT_ID`) REFERENCES `discount` (`DISCOUNT_ID`),
  CONSTRAINT `P_PAYMENT_METHOD_ID` FOREIGN KEY (`PAYMENT_METHOD_ID`) REFERENCES `payment method` (`PAYMENT_METHOD_ID`),
  CONSTRAINT `P_RESERVATION_ID` FOREIGN KEY (`RESERVATION_ID`) REFERENCES `reservation` (`RESERVATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment method`
--

DROP TABLE IF EXISTS `payment method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment method` (
  `PAYMENT_METHOD_ID` int NOT NULL,
  `METHOD_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_METHOD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `RESERVATION_ID` int NOT NULL,
  `GUEST_ID` int DEFAULT NULL,
  `CHECKIN_DATE` date DEFAULT NULL,
  `CHECKOUT_DATE` date DEFAULT NULL,
  `RESERVATION_STATUS` varchar(45) DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  `PAYMENT_ID` int DEFAULT NULL,
  PRIMARY KEY (`RESERVATION_ID`),
  KEY `GUEST_ID_idx` (`GUEST_ID`),
  KEY `HOTEL_ID_idx` (`HOTEL_ID`),
  KEY `PAYMENT_ID_idx` (`PAYMENT_ID`),
  CONSTRAINT `RE_GUEST_ID` FOREIGN KEY (`GUEST_ID`) REFERENCES `guest` (`GUEST_ID`),
  CONSTRAINT `RE_HOTEL_ID` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`),
  CONSTRAINT `RE_PAYMENT_ID` FOREIGN KEY (`PAYMENT_ID`) REFERENCES `payment` (`PAYMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `ROOM_ID` int NOT NULL,
  `ROOM_NO` int DEFAULT NULL,
  `TYPE_ID` int DEFAULT NULL,
  `ROOM_PRICE` decimal(8,2) DEFAULT NULL,
  `ROOM_AVAILABILITY` varchar(15) DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  UNIQUE KEY `room_no_UNIQUE` (`ROOM_NO`),
  KEY `TYPE_ID_idx` (`TYPE_ID`),
  KEY `HOTEL_ID_idx` (`HOTEL_ID`),
  CONSTRAINT `R_HOTEL_ID` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`),
  CONSTRAINT `R_TYPE_ID` FOREIGN KEY (`TYPE_ID`) REFERENCES `roomtype` (`ROOMTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtype` (
  `ROOMTYPE_ID` int NOT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ROOMTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 10:51:11
