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
  `COUNTRY_ID` int NOT NULL AUTO_INCREMENT,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`COUNTRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'United States'),(2,'Canada'),(3,'Germany'),(4,'France'),(5,'Italy'),(6,'Australia'),(7,'Brazil'),(8,'United Kingdom'),(9,'Japan'),(10,'Switzerland');
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
  `DISCOUNT_PERCENTAGE` decimal(5,4) NOT NULL,
  PRIMARY KEY (`DISCOUNT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'DISC2024',0.1000),(2,'SUMMER30',0.3000),(3,'WINTER10',0.1000),(4,'FALL15',0.1500),(5,'SPRING20',0.2000),(6,'HOLIDAY25',0.2500),(7,'BLACKFRIDAY40',0.4000),(8,'CYBERMONDAY50',0.5000),(9,'XMAS15',0.1500),(10,'NEWYEAR2024',0.0500);
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
  `EMAIL` varchar(125) NOT NULL,
  `CONTACT_NO` varchar(15) NOT NULL,
  `JOB_ID` int NOT NULL,
  `HOTEL_ID` int NOT NULL,
  `SALARY` decimal(10,2) NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `JOB_ID` (`JOB_ID`),
  KEY `HOTEL_ID` (`HOTEL_ID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`JOB_ID`) REFERENCES `jobs` (`JOB_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'LeBron James','lebron.james@nba.com','555-1111',1,1,100000.00,'2022-01-15','2024-01-15'),(2,'Serena Williams','serena.williams@tennis.com','555-2222',2,2,42000.00,'2021-04-20','2023-04-20'),(3,'Tom Hanks','tom.hanks@hollywood.com','555-3333',3,3,65000.00,'2022-02-10','2024-02-10'),(4,'Emma Watson','emma.watson@hollywood.com','555-4444',4,4,40000.00,'2021-06-05','2023-06-05'),(5,'Chris Hemsworth','chris.hemsworth@hollywood.com','555-5555',5,5,35000.00,'2022-07-01','2024-07-01'),(6,'Zendaya','zendaya@hollywood.com','555-6666',6,6,22000.00,'2021-09-15','2023-09-15'),(7,'Leonardo DiCaprio','leo.dicaprio@hollywood.com','555-7777',7,7,48000.00,'2022-05-30','2024-05-30'),(8,'Scarlett Johansson','scarlett.johansson@hollywood.com','555-8888',8,8,75000.00,'2021-03-20','2023-03-20'),(9,'Dwayne Johnson','dwayne.johnson@hollywood.com','555-9999',9,9,55000.00,'2022-01-10','2024-01-10'),(10,'Meryl Streep','meryl.streep@hollywood.com','555-1010',10,10,34000.00,'2022-02-25','2024-02-25'),(11,'Nicole Kidman','nicole.kidman@hollywood.com','555-2020',11,1,22000.00,'2022-07-20','2024-07-20'),(12,'Hugh Jackman','hugh.jackman@hollywood.com','555-3030',12,2,45000.00,'2021-10-10','2023-10-10'),(13,'Cristiano Ronaldo','ronaldo@football.com','555-4040',13,3,80000.00,'2022-01-12','2024-01-12'),(14,'Neymar Jr.','neymar.jr@football.com','555-5050',14,4,22000.00,'2022-03-01','2024-03-01'),(15,'Kate Middleton','kate.middleton@royals.com','555-6060',15,5,38000.00,'2022-04-15','2024-04-15'),(16,'Prince William','prince.william@royals.com','555-7070',16,6,55000.00,'2021-08-10','2023-08-10'),(17,'Hidetoshi Nakata','hidetoshi.nakata@football.com','555-8080',17,7,48000.00,'2022-09-01','2024-09-01'),(18,'Shinji Kagawa','shinji.kagawa@football.com','555-9090',18,8,27000.00,'2022-06-30','2024-06-30'),(19,'Roger Federer','roger.federer@tennis.com','555-0101',19,9,45000.00,'2021-05-15','2023-05-15'),(20,'Stan Wawrinka','stan.wawrinka@tennis.com','555-1110',1,10,110000.00,'2021-12-01','2023-12-01'),(21,'Michael Scott','michael.scott@dundermifflin.com','555-1234',1,1,95000.00,'2015-01-15',NULL),(22,'Pam Beesly','pam.beesly@dundermifflin.com','555-2345',2,2,43000.00,'2015-03-18',NULL),(23,'Jim Halpert','jim.halpert@dundermifflin.com','555-3456',3,3,70000.00,'2016-05-20',NULL),(24,'Dwight Schrute','dwight.schrute@dundermifflin.com','555-4567',4,4,42000.00,'2017-07-22',NULL),(25,'Angela Martin','angela.martin@dundermifflin.com','555-5678',5,5,38000.00,'2018-09-14',NULL),(26,'Ryan Howard','ryan.howard@dundermifflin.com','555-6789',6,6,24000.00,'2019-01-11',NULL),(27,'Toby Flenderson','toby.flenderson@dundermifflin.com','555-7890',7,7,47000.00,'2020-04-23',NULL),(28,'Kelly Kapoor','kelly.kapoor@dundermifflin.com','555-8901',8,8,75000.00,'2021-02-28',NULL),(29,'Stanley Hudson','stanley.hudson@dundermifflin.com','555-9012',9,9,60000.00,'2022-07-12',NULL),(30,'Creed Bratton','creed.bratton@dundermifflin.com','555-0123',10,10,34000.00,'2023-08-06',NULL),(31,'Phyllis Vance','phyllis.vance@dundermifflin.com','555-1234',11,1,23000.00,'2020-01-01',NULL),(32,'Meredith Palmer','meredith.palmer@dundermifflin.com','555-2345',12,2,48000.00,'2017-09-05',NULL),(33,'Oscar Martinez','oscar.martinez@dundermifflin.com','555-3456',13,3,87000.00,'2019-06-10',NULL),(34,'Jan Levinson','jan.levinson@dundermifflin.com','555-4567',14,4,21000.00,'2018-11-14',NULL),(35,'Ryan Gosling','ryan.gosling@dundermifflin.com','555-5678',15,5,39000.00,'2021-12-22',NULL),(36,'Emma Stone','emma.stone@dundermifflin.com','555-6789',16,6,57000.00,'2020-05-30',NULL),(37,'Bradley Cooper','bradley.cooper@dundermifflin.com','555-7890',17,7,47000.00,'2022-01-15',NULL),(38,'Jennifer Lawrence','jennifer.lawrence@dundermifflin.com','555-8901',18,8,26000.00,'2019-08-02',NULL),(39,'Chris Pratt','chris.pratt@dundermifflin.com','555-9012',19,9,50000.00,'2021-03-21',NULL),(40,'Zoe Saldana','zoe.saldana@dundermifflin.com','555-0123',1,1,100000.00,'2023-06-18',NULL),(41,'Dave Bautista','dave.bautista@dundermifflin.com','555-1234',4,4,44000.00,'2022-02-19',NULL),(42,'Vin Diesel','vin.diesel@dundermifflin.com','555-2345',3,3,66000.00,'2020-11-02',NULL),(43,'Chris Hemsworth','chris.hemsworth@dundermifflin.com','555-3456',5,5,38000.00,'2022-08-14',NULL),(44,'Michael Jackson','michaeljackson@gmail.com','555-4567',19,1,24000.00,'2021-04-26',NULL);
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
  `CONTACT_NO` varchar(15) NOT NULL,
  `EMAIL` varchar(125) NOT NULL,
  PRIMARY KEY (`GUEST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'Kai Cenat','555-1010','kai.cenat@twitch.com'),(2,'Jisoo Kim','555-1010','jisoo.kim@gmail.com'),(3,'Lisa Manoban','555-1020','lisa.manoban@yahoo.com'),(4,'Jennie Park','555-1030','jennie.park@hotmail.com'),(5,'Rosé Smith','555-1040','rose.smith@outlook.com'),(6,'V Kim','555-1050','v.kim@aol.com'),(7,'Jungkook Jeon','555-1060','jungkook.jeon@yahoo.com'),(8,'RM Kim','555-1070','rm.kim@gmail.com'),(9,'Suga Min','555-1080','suga.min@hotmail.com'),(10,'J-Hope Jung','555-1090','jhope.jung@outlook.com'),(11,'Jimin Park','555-1100','jimin.park@gmail.com'),(12,'Kai Kim','555-1110','kai.kim@yahoo.com'),(13,'Baekhyun Byun','555-1120','baekhyun.byun@aol.com'),(14,'Seulgi Kang','555-1130','seulgi.kang@hotmail.com'),(15,'Irene Bae','555-1140','irene.bae@gmail.com'),(16,'Wendy Son','555-1150','wendy.son@yahoo.com'),(17,'Joy Park','555-1160','joy.park@outlook.com'),(18,'Yeri Kim','555-1170','yeri.kim@aol.com'),(19,'Suzy Bae','555-1180','suzy.bae@gmail.com'),(20,'Tzuyu Chou','555-1190','tzuyu.chou@yahoo.com'),(21,'Nayeon Im','555-1200','nayeon.im@hotmail.com');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `HOTEL_ID` int NOT NULL AUTO_INCREMENT,
  `HOTEL_NAME` varchar(45) NOT NULL,
  `HOTEL_EMAIL` varchar(125) NOT NULL,
  `CONTACT_NO` varchar(15) NOT NULL,
  `LOCATION_ID` int NOT NULL,
  PRIMARY KEY (`HOTEL_ID`),
  KEY `LOCATION_ID` (`LOCATION_ID`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`LOCATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'NYC Hotel','nychotelinquiries@hotmail.com','123456789',1),(2,'Sunset Boulevard Hotel','reservations@sunsetboulevardhotel.com','555-5678',1),(3,'Maple Leaf Resort','info@mapleleafresort.ca','555-2345',2),(4,'The Skyline Inn','bookings@skylineinn.ca','555-6789',2),(5,'Berliner Hotel','contact@berlinerhotel.de','555-3456',3),(6,'Berlin Central Hotel','info@berlincentralhotel.de','555-7890',3),(7,'Le Parisien Hotel','reservation@leparisien.fr','555-4567',4),(8,'The Eiffel Tower Hotel','info@eiffeltowerhotel.fr','555-8901',4),(9,'Roma Bella Hotel','contact@romabellahotel.it','555-2341',5),(10,'Colosseo Palace Hotel','booking@colosseopalace.it','555-9876',5),(11,'Sydney Harbour Hotel','info@sydneyharbourhotel.com.au','555-1230',6),(12,'Opera House Suites','contact@operahousesuites.com.au','555-5671',6),(13,'São Paulo Grand Hotel','info@spgrandhotel.com.br','555-2349',7),(14,'Cedarwood Luxury Suites','luxury@cedarwoodluxurysuites.com.br','555-8902',7),(15,'The London Ritz','info@thelondonritz.co.uk','555-9870',8),(16,'Westminster Hotel','bookings@westminsterhotel.co.uk','555-3451',8),(17,'Tokyo Bay Resort','reservations@tokyobayresort.jp','555-5678',9),(18,'Shinjuku Plaza Hotel','info@shinjukuplazahotel.jp','555-2347',9),(19,'Zurich Prestige Hotel','contact@zurichprestigehotel.ch','555-6783',10),(20,'Alpine View Hotel','info@alpineviewhotel.ch','555-2345',10);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `JOB_ID` int NOT NULL AUTO_INCREMENT,
  `JOB_NAME` varchar(45) NOT NULL,
  `MIN_SALARY` decimal(10,2) NOT NULL,
  `MAX_SALARY` decimal(10,2) NOT NULL,
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,'Hotel General Manager',50000.00,120000.00),(2,'Front Desk Supervisor',30000.00,45000.00),(3,'Food and Beverage Manager',35000.00,70000.00),(4,'Sous Chef',25000.00,45000.00),(5,'Pastry Chef',22000.00,40000.00),(6,'Bartender',15000.00,25000.00),(7,'Event Planner',25000.00,50000.00),(8,'Marketing Manager',40000.00,80000.00),(9,'Spa Manager',35000.00,60000.00),(10,'Customer Service Representative',18000.00,35000.00),(11,'Room Service Attendant',15000.00,23000.00),(12,'IT Support Specialist',25000.00,50000.00),(13,'Sales Director',45000.00,90000.00),(14,'Food and Beverage Server',15000.00,23000.00),(15,'Laundry Supervisor',22000.00,40000.00),(16,'Public Relations Manager',30000.00,60000.00),(17,'Concierge Supervisor',28000.00,50000.00),(18,'Reservations Agent',18000.00,28000.00),(19,'Maintenance Supervisor',28000.00,50000.00);
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `LOCATION_ID` int NOT NULL AUTO_INCREMENT,
  `STREET_ADDRESS` varchar(125) NOT NULL,
  `CITY` varchar(45) NOT NULL,
  `POSTAL_CODE` varchar(15) NOT NULL,
  `STATE_PROVINCE` varchar(45) NOT NULL,
  `COUNTRY_ID` int NOT NULL,
  PRIMARY KEY (`LOCATION_ID`),
  KEY `COUNTRY_ID` (`COUNTRY_ID`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'123 Main St','New York','10001','NY',1),(2,'456 Elm St','Los Angeles','90001','CA',1),(3,'789 Pine St','Berlin','10115','Berlin',2),(4,'101 Oak St','Paris','75001','Île-de-France',3),(5,'202 Maple St','Rome','00100','Lazio',4),(6,'303 Birch St','Sydney','2000','NSW',5),(7,'404 Cedar St','São Paulo','01000','SP',6),(8,'505 Willow St','London','E1 6AN','England',7),(9,'606 Pine Rd','Tokyo','100-0001','Tokyo',8),(10,'707 Redwood Ave','Zurich','8001','Zurich',9);
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
  `RESERVATION_ID` int NOT NULL,
  `PAYMENT_DATE` date NOT NULL,
  `AMOUNT` decimal(10,2) NOT NULL,
  `DISCOUNT_ID` int DEFAULT NULL,
  `PAYMENT_METHOD_ID` int DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_ID`),
  KEY `RESERVATION_ID` (`RESERVATION_ID`),
  KEY `DISCOUNT_ID` (`DISCOUNT_ID`),
  KEY `PAYMENT_METHOD_ID` (`PAYMENT_METHOD_ID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`RESERVATION_ID`) REFERENCES `reservation` (`RESERVATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`DISCOUNT_ID`) REFERENCES `discount` (`DISCOUNT_ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `payment_ibfk_3` FOREIGN KEY (`PAYMENT_METHOD_ID`) REFERENCES `payment_method` (`PAYMENT_METHOD_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  PRIMARY KEY (`PAYMENT_METHOD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Credit Card'),(2,'Debit Card'),(3,'PayPal'),(4,'Bank Transfer'),(5,'Cash'),(6,'Gift Voucher');
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
  `RESERVATION_STATUS` varchar(45) NOT NULL,
  `ROOM_ID` int NOT NULL,
  PRIMARY KEY (`RESERVATION_ID`),
  KEY `GUEST_ID` (`GUEST_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GUEST_ID`) REFERENCES `guest` (`GUEST_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `rooms` (`ROOM_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,'2024-12-01','2024-12-05','Confirmed',101),(2,2,'2024-12-02','2024-12-06','Confirmed',152),(3,3,'2024-12-03','2024-12-07','Confirmed',45),(4,4,'2024-12-04','2024-12-08','Confirmed',87),(5,5,'2024-12-05','2024-12-09','Confirmed',123),(6,6,'2024-12-06','2024-12-10','Confirmed',178),(7,7,'2024-12-07','2024-12-11','Confirmed',65),(8,8,'2024-12-08','2024-12-12','Confirmed',129),(9,9,'2024-12-09','2024-12-13','Confirmed',88),(10,10,'2024-12-10','2024-12-14','Confirmed',113),(11,11,'2024-12-11','2024-12-15','Confirmed',200),(12,12,'2024-12-12','2024-12-16','Confirmed',53),(13,13,'2024-12-13','2024-12-17','Confirmed',164),(14,14,'2024-12-14','2024-12-18','Confirmed',119),(15,15,'2024-12-15','2024-12-19','Confirmed',144),(16,16,'2024-12-16','2024-12-20','Confirmed',170),(17,17,'2024-12-17','2024-12-21','Confirmed',130),(18,18,'2024-12-18','2024-12-22','Confirmed',96),(19,19,'2024-12-19','2024-12-23','Confirmed',112),(20,20,'2024-12-20','2024-12-24','Confirmed',158),(21,21,'2024-12-21','2024-12-25','Confirmed',140);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `ROOM_ID` int NOT NULL AUTO_INCREMENT,
  `ROOM_NO` int NOT NULL,
  `TYPE_ID` int NOT NULL,
  `HOTEL_ID` int NOT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `TYPE_ID` (`TYPE_ID`),
  KEY `HOTEL_ID` (`HOTEL_ID`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`TYPE_ID`) REFERENCES `roomtype` (`ROOMTYPE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,101,1,1),(2,102,2,1),(3,103,3,1),(4,104,4,1),(5,105,5,1),(6,106,6,1),(7,107,7,1),(8,108,8,1),(9,109,9,1),(10,110,10,1),(11,111,11,1),(12,112,12,1),(13,113,1,1),(14,114,2,1),(15,115,3,1),(16,116,4,1),(17,117,5,1),(18,118,6,1),(19,119,7,1),(20,120,8,1),(21,201,9,2),(22,202,10,2),(23,203,11,2),(24,204,12,2),(25,205,1,2),(26,206,2,2),(27,207,3,2),(28,208,4,2),(29,209,5,2),(30,210,6,2),(31,211,7,2),(32,212,8,2),(33,213,9,2),(34,214,10,2),(35,215,11,2),(36,216,12,2),(37,217,1,2),(38,218,2,2),(39,219,3,2),(40,220,4,2),(41,301,5,3),(42,302,6,3),(43,303,7,3),(44,304,8,3),(45,305,9,3),(46,306,10,3),(47,307,11,3),(48,308,12,3),(49,309,1,3),(50,310,2,3),(51,311,3,3),(52,312,4,3),(53,313,5,3),(54,314,6,3),(55,315,7,3),(56,316,8,3),(57,317,9,3),(58,318,10,3),(59,319,11,3),(60,320,12,3),(61,401,1,4),(62,402,2,4),(63,403,3,4),(64,404,4,4),(65,405,5,4),(66,406,6,4),(67,407,7,4),(68,408,8,4),(69,409,9,4),(70,410,10,4),(71,411,11,4),(72,412,12,4),(73,413,1,4),(74,414,2,4),(75,415,3,4),(76,416,4,4),(77,417,5,4),(78,418,6,4),(79,419,7,4),(80,420,8,4),(81,501,9,5),(82,502,10,5),(83,503,11,5),(84,504,12,5),(85,505,1,5),(86,506,2,5),(87,507,3,5),(88,508,4,5),(89,509,5,5),(90,510,6,5),(91,511,7,5),(92,512,8,5),(93,513,9,5),(94,514,10,5),(95,515,11,5),(96,516,12,5),(97,517,1,5),(98,518,2,5),(99,519,3,5),(100,520,4,5),(101,601,5,6),(102,602,6,6),(103,603,7,6),(104,604,8,6),(105,605,9,6),(106,606,10,6),(107,607,11,6),(108,608,12,6),(109,609,1,6),(110,610,2,6),(111,611,3,6),(112,612,4,6),(113,613,5,6),(114,614,6,6),(115,615,7,6),(116,616,8,6),(117,617,9,6),(118,618,10,6),(119,619,11,6),(120,620,12,6),(121,701,1,7),(122,702,2,7),(123,703,3,7),(124,704,4,7),(125,705,5,7),(126,706,6,7),(127,707,7,7),(128,708,8,7),(129,709,9,7),(130,710,10,7),(131,711,11,7),(132,712,12,7),(133,713,1,7),(134,714,2,7),(135,715,3,7),(136,716,4,7),(137,717,5,7),(138,718,6,7),(139,719,7,7),(140,720,8,7),(141,801,9,8),(142,802,10,8),(143,803,11,8),(144,804,12,8),(145,805,1,8),(146,806,2,8),(147,807,3,8),(148,808,4,8),(149,809,5,8),(150,810,6,8),(151,811,7,8),(152,812,8,8),(153,813,9,8),(154,814,10,8),(155,815,11,8),(156,816,12,8),(157,817,1,8),(158,818,2,8),(159,819,3,8),(160,820,4,8),(161,901,5,9),(162,902,6,9),(163,903,7,9),(164,904,8,9),(165,905,9,9),(166,906,10,9),(167,907,11,9),(168,908,12,9),(169,909,1,9),(170,910,2,9),(171,911,3,9),(172,912,4,9),(173,913,5,9),(174,914,6,9),(175,915,7,9),(176,916,8,9),(177,917,9,9),(178,918,10,9),(179,919,11,9),(180,920,12,9),(181,1001,1,10),(182,1002,2,10),(183,1003,3,10),(184,1004,4,10),(185,1005,5,10),(186,1006,6,10),(187,1007,7,10),(188,1008,8,10),(189,1009,9,10),(190,1010,10,10),(191,1011,11,10),(192,1012,12,10),(193,1013,1,10),(194,1014,2,10),(195,1015,3,10),(196,1016,4,10),(197,1017,5,10),(198,1018,6,10),(199,1019,7,10),(200,1020,8,10);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtype` (
  `ROOMTYPE_ID` int NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(45) NOT NULL,
  `ROOM_PRICE` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ROOMTYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,'Single',100.00),(2,'Double',150.00),(3,'Suite',250.00),(4,'Family',200.00),(5,'Penthouse',500.00),(6,'King',300.00),(7,'Queen',250.00),(8,'Twin',180.00),(9,'Presidential',1000.00),(10,'Luxury',450.00),(11,'Budget',80.00),(12,'Deluxe',350.00),(13,'Standard',120.00),(14,'Economy',90.00);
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

-- Dump completed on 2024-11-18 13:46:01
