-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: hoteldb
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `DISCOUNT_ID` int NOT NULL AUTO_INCREMENT,
  `DISCOUNT_NAME` varchar(45) NOT NULL,
  `DISCOUNT_PERCENTAGE` decimal(5,2) NOT NULL,
  PRIMARY KEY (`DISCOUNT_ID`),
  CONSTRAINT `discount_chk_1` CHECK (((`DISCOUNT_PERCENTAGE` >= 0) and (`DISCOUNT_PERCENTAGE` <= 100)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPLOYEE_ID` int NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(125) DEFAULT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `JOB_ID` int DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  `SALARY` decimal(8,2) DEFAULT NULL,
  `HIRE_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  UNIQUE KEY `email_unique` (`EMAIL`),
  KEY `fk_job_id` (`JOB_ID`),
  KEY `fk_hotel_id` (`HOTEL_ID`),
  CONSTRAINT `fk_hotel_id` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`),
  CONSTRAINT `fk_job_id` FOREIGN KEY (`JOB_ID`) REFERENCES `jobs` (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `GUEST_ID` int NOT NULL AUTO_INCREMENT,
  `GUEST_NAME` varchar(45) NOT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`GUEST_ID`),
  UNIQUE KEY `email_unique` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `HOTEL_ID` int NOT NULL,
  `HOTEL_NAME` varchar(45) DEFAULT NULL,
  `HOTEL_EMAIL` varchar(125) DEFAULT NULL,
  `CONTACT_NO` int DEFAULT NULL,
  `LOCATION_ID` int DEFAULT NULL,
  PRIMARY KEY (`HOTEL_ID`),
  UNIQUE KEY `hotelName_UNIQUE` (`HOTEL_NAME`),
  KEY `LOCATION_ID_idx` (`LOCATION_ID`),
  CONSTRAINT `LOCATION_ID` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`LOCATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `COUNTRY_ID` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PAYMENT_ID` int NOT NULL AUTO_INCREMENT,
  `PAYMENT_DATE` date NOT NULL,
  `AMOUNT` decimal(8,2) NOT NULL,
  `PAYMENT_METHOD_ID` int DEFAULT NULL,
  `DISCOUNT_ID` int DEFAULT NULL,
  `RESERVATION_ID` int DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_ID`),
  KEY `PAYMENT_METHOD_ID` (`PAYMENT_METHOD_ID`),
  KEY `DISCOUNT_ID` (`DISCOUNT_ID`),
  KEY `fk_payment_reservation` (`RESERVATION_ID`),
  CONSTRAINT `fk_payment_reservation` FOREIGN KEY (`RESERVATION_ID`) REFERENCES `reservation` (`RESERVATION_ID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`PAYMENT_METHOD_ID`) REFERENCES `payment_method` (`PAYMENT_METHOD_ID`),
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`DISCOUNT_ID`) REFERENCES `discount` (`DISCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `PAYMENT_METHOD_ID` int NOT NULL AUTO_INCREMENT,
  `METHOD_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`PAYMENT_METHOD_ID`),
  UNIQUE KEY `METHOD_NAME` (`METHOD_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `RESERVATION_ID` int NOT NULL AUTO_INCREMENT,
  `GUEST_ID` int NOT NULL,
  `CHECKIN_DATE` date NOT NULL,
  `CHECKOUT_DATE` date NOT NULL,
  `HOTEL_ID` int NOT NULL,
  PRIMARY KEY (`RESERVATION_ID`),
  KEY `GUEST_ID` (`GUEST_ID`),
  KEY `HOTEL_ID` (`HOTEL_ID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GUEST_ID`) REFERENCES `guest` (`GUEST_ID`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `ROOM_ID` int NOT NULL,
  `ROOM_NO` int DEFAULT NULL,
  `ROOMTYPE_ID` int DEFAULT NULL,
  `ROOM_PRICE` decimal(8,2) DEFAULT NULL,
  `ROOM_AVAILABILITY` varchar(15) DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  UNIQUE KEY `room_no_UNIQUE` (`ROOM_NO`),
  KEY `TYPE_ID_idx` (`ROOMTYPE_ID`),
  KEY `HOTEL_ID_idx` (`HOTEL_ID`),
  CONSTRAINT `HOTEL_ID` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`),
  CONSTRAINT `TYPE_ID` FOREIGN KEY (`ROOMTYPE_ID`) REFERENCES `roomtype` (`ROOMTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-13 21:34:17
