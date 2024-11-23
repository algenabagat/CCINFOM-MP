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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'DISC2024',0.1000),(2,'SUMMER30',0.3000),(3,'WINTER10',0.1000),(4,'FALL15',0.1500),(5,'SPRING20',0.2000),(6,'HOLIDAY25',0.2500),(7,'BLACKFRIDAY40',0.4000),(8,'CYBERMONDAY50',0.5000),(9,'XMAS15',0.1500);
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
  `CONTACT_NO` varchar(15) DEFAULT NULL,
  `JOB_ID` int DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  `SALARY` decimal(10,2) NOT NULL,
  `HIRE_DATE` date NOT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`),
  KEY `JOB_ID` (`JOB_ID`),
  KEY `HOTEL_ID` (`HOTEL_ID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`JOB_ID`) REFERENCES `jobs` (`JOB_ID`) ON DELETE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Tom Cruise','tom.cruise@hotel1.com','212-555-0198',1,1,95000.00,'2023-01-15',NULL),(2,'Emma Stone','emma.stone@hotel1.com','212-555-0199',2,1,38000.00,'2023-02-20',NULL),(3,'Gordon Ramsay','gordon.ramsay@hotel1.com','212-555-0200',3,1,65000.00,'2023-03-10',NULL),(4,'Jamie Oliver','jamie.oliver@hotel1.com','212-555-0201',4,1,42000.00,'2023-04-01','2024-04-01'),(5,'Meryl Streep','meryl.streep@hotel1.com','212-555-0202',5,1,38000.00,'2023-05-01',NULL),(6,'Johnny Depp','johnny.depp@hotel1.com','212-555-0203',6,1,23000.00,'2023-06-01',NULL),(7,'Kylie Jenner','kylie.jenner@hotel1.com','212-555-0204',7,1,48000.00,'2023-07-01',NULL),(8,'Rihanna','rihanna@hotel1.com','212-555-0205',8,1,75000.00,'2023-08-01',NULL),(9,'Adele','adele@hotel1.com','212-555-0206',9,1,55000.00,'2023-09-01',NULL),(10,'Chris Hemsworth','chris.hemsworth@hotel1.com','212-555-0207',10,1,33000.00,'2023-10-01',NULL),(11,'Scarlett Johansson','scarlett.johansson@hotel1.com','212-555-0208',11,1,20000.00,'2023-11-01',NULL),(12,'Samuel L. Jackson','samuel.jackson@hotel1.com','212-555-0209',12,1,45000.00,'2023-12-01',NULL),(13,'Leonardo DiCaprio','leo.dicaprio@hotel1.com','212-555-0210',13,1,80000.00,'2024-01-01',NULL),(14,'Anne Hathaway','anne.hathaway@hotel1.com','212-555-0211',14,1,22000.00,'2024-02-01',NULL),(15,'Brad Pitt','brad.pitt@hotel1.com','212-555-0212',15,1,35000.00,'2024-03-01','2025-03-01'),(16,'Jennifer Lawrence','jennifer.lawrence@hotel1.com','212-555-0213',16,1,55000.00,'2024-04-01',NULL),(17,'Matthew McConaughey','matthew.mcconaughey@hotel1.com','212-555-0214',17,1,48000.00,'2024-05-01',NULL),(18,'Angelina Jolie','angelina.jolie@hotel1.com','212-555-0215',18,1,26000.00,'2024-06-01',NULL),(19,'Will Smith','will.smith@hotel1.com','212-555-0216',19,1,46000.00,'2024-07-01',NULL),(20,'Robert Downey Jr.','robert.downey@hotel2.com','416-555-0301',1,2,115000.00,'2023-02-01',NULL),(21,'Jennifer Lopez','jennifer.lopez@hotel2.com','416-555-0302',2,2,42000.00,'2023-03-10','2024-03-10'),(22,'Tom Hanks','tom.hanks@hotel2.com','416-555-0303',3,2,60000.00,'2023-04-15',NULL),(23,'Julia Roberts','julia.roberts@hotel2.com','416-555-0304',4,2,40000.00,'2023-05-05',NULL),(24,'Keanu Reeves','keanu.reeves@hotel2.com','416-555-0305',5,2,35000.00,'2023-06-01',NULL),(25,'Will Ferrell','will.ferrell@hotel2.com','416-555-0306',6,2,22000.00,'2023-07-01',NULL),(26,'Beyoncé','beyonce@hotel2.com','416-555-0307',7,2,50000.00,'2023-08-01',NULL),(27,'Dwayne Johnson','dwayne.johnson@hotel2.com','416-555-0308',8,2,75000.00,'2023-09-01',NULL),(28,'Shakira','shakira@hotel2.com','416-555-0309',9,2,53000.00,'2023-10-01',NULL),(29,'Matt Damon','matt.damon@hotel2.com','416-555-0310',10,2,34000.00,'2023-11-01',NULL),(30,'Cameron Diaz','cameron.diaz@hotel2.com','416-555-0311',11,2,20000.00,'2023-12-01',NULL),(31,'Bradley Cooper','bradley.cooper@hotel2.com','416-555-0312',12,2,45000.00,'2024-01-01',NULL),(32,'Anne Hathaway','anne.hathaway@hotel2.com','416-555-0313',13,2,85000.00,'2024-02-01',NULL),(33,'Reese Witherspoon','reese.witherspoon@hotel2.com','416-555-0314',14,2,23000.00,'2024-03-01',NULL),(34,'Matthew Perry','matthew.perry@hotel2.com','416-555-0315',15,2,35000.00,'2024-04-01',NULL),(35,'Eva Mendes','eva.mendes@hotel2.com','416-555-0316',16,2,55000.00,'2024-05-01','2025-05-01'),(36,'Zac Efron','zac.efron@hotel2.com','416-555-0317',17,2,47000.00,'2024-06-01',NULL),(37,'Johnny Depp','johnny.depp@hotel2.com','416-555-0318',18,2,27000.00,'2024-07-01',NULL),(38,'Chris Evans','chris.evans@hotel2.com','416-555-0319',19,2,46000.00,'2024-08-01',NULL),(39,'James Anderson','james.anderson@hotel3.com','030-555-0401',1,3,95000.00,'2023-01-15',NULL),(40,'Sophia Richards','sophia.richards@hotel3.com','030-555-0402',2,3,39000.00,'2023-02-10',NULL),(41,'Daniel Carter','daniel.carter@hotel3.com','030-555-0403',3,3,55000.00,'2023-03-20',NULL),(42,'Olivia Brooks','olivia.brooks@hotel3.com','030-555-0404',4,3,41000.00,'2023-04-01',NULL),(43,'Megan Scott','megan.scott@hotel3.com','030-555-0405',5,3,37000.00,'2023-05-10',NULL),(44,'Samuel Turner','samuel.turner@hotel3.com','030-555-0406',6,3,24000.00,'2023-06-05',NULL),(45,'Chloe Taylor','chloe.taylor@hotel3.com','030-555-0407',7,3,48000.00,'2023-07-15',NULL),(46,'William Hall','william.hall@hotel3.com','030-555-0408',8,3,73000.00,'2023-08-01',NULL),(47,'Emily Davis','emily.davis@hotel3.com','030-555-0409',9,3,54000.00,'2023-09-05',NULL),(48,'Lucas Wilson','lucas.wilson@hotel3.com','030-555-0410',10,3,33000.00,'2023-10-01',NULL),(49,'Hannah Evans','hannah.evans@hotel3.com','030-555-0411',11,3,22000.00,'2023-11-01',NULL),(50,'Ethan Perez','ethan.perez@hotel3.com','030-555-0412',12,3,43000.00,'2023-12-01',NULL),(51,'Charlotte White','charlotte.white@hotel3.com','030-555-0413',13,3,80000.00,'2024-01-10',NULL),(52,'Jack Martin','jack.martin@hotel3.com','030-555-0414',14,3,23000.00,'2024-02-05',NULL),(53,'Ava Robinson','ava.robinson@hotel3.com','030-555-0415',15,3,34000.00,'2024-03-01',NULL),(54,'Isaac Clark','isaac.clark@hotel3.com','030-555-0416',16,3,52000.00,'2024-04-10',NULL),(55,'Lily Young','lily.young@hotel3.com','030-555-0417',17,3,47000.00,'2024-05-05',NULL),(56,'Leo Carter','leo.carter@hotel3.com','030-555-0418',18,3,26000.00,'2024-06-01',NULL),(57,'Ella Green','ella.green@hotel3.com','030-555-0419',19,3,45000.00,'2024-07-01',NULL),(58,'Madison Cooper','madison.cooper@hotel4.com','040-555-0501',1,4,105000.00,'2022-03-12',NULL),(59,'Liam Hughes','liam.hughes@hotel4.com','040-555-0502',2,4,42000.00,'2021-05-20',NULL),(60,'Zoe Miller','zoe.miller@hotel4.com','040-555-0503',3,4,65000.00,'2020-06-10',NULL),(61,'Noah Johnson','noah.johnson@hotel4.com','040-555-0504',4,4,38000.00,'2023-01-25',NULL),(62,'Avery Simmons','avery.simmons@hotel4.com','040-555-0505',5,4,34000.00,'2022-08-30',NULL),(63,'Jackie Lee','jackie.lee@hotel4.com','040-555-0506',6,4,23000.00,'2021-07-15',NULL),(64,'Luna Gray','luna.gray@hotel4.com','040-555-0507',7,4,46000.00,'2023-09-10',NULL),(65,'Jackson Carter','jackson.carter@hotel4.com','040-555-0508',8,4,71000.00,'2020-11-20',NULL),(66,'Maya Foster','maya.foster@hotel4.com','040-555-0509',9,4,53000.00,'2022-05-15',NULL),(67,'Eliott Harris','eliott.harris@hotel4.com','040-555-0510',10,4,30000.00,'2023-02-05',NULL),(68,'Chloe Brooks','chloe.brooks@hotel4.com','040-555-0511',11,4,21000.00,'2021-04-25',NULL),(69,'Jaden White','jaden.white@hotel4.com','040-555-0512',12,4,42000.00,'2020-12-01',NULL),(70,'Nina Parker','nina.parker@hotel4.com','040-555-0513',13,4,78000.00,'2022-02-10',NULL),(71,'Tyler Morgan','tyler.morgan@hotel4.com','040-555-0514',14,4,22000.00,'2023-08-14',NULL),(72,'Grace Reed','grace.reed@hotel4.com','040-555-0515',15,4,35000.00,'2021-06-01',NULL),(73,'Ethan Lewis','ethan.lewis@hotel4.com','040-555-0516',16,4,50000.00,'2022-07-10',NULL),(74,'Madeline King','madeline.king@hotel4.com','040-555-0517',17,4,48000.00,'2023-03-22',NULL),(75,'Oliver Turner','oliver.turner@hotel4.com','040-555-0518',18,4,27000.00,'2021-09-15',NULL),(76,'Sophie Adams','sophie.adams@hotel4.com','040-555-0519',19,4,46000.00,'2022-11-25',NULL),(77,'Josefina Santos','josefina.santos@hotel5.com','02-555-1001',1,5,112000.00,'2021-06-18',NULL),(78,'Rafael Domingo','rafael.domingo@hotel5.com','02-555-1002',2,5,38000.00,'2020-09-12','2023-04-10'),(79,'Marisol Garcia','marisol.garcia@hotel5.com','02-555-1003',3,5,57000.00,'2022-03-05',NULL),(80,'Antonio Reyes','antonio.reyes@hotel5.com','02-555-1004',4,5,41000.00,'2023-01-19',NULL),(81,'Bianca Cruz','bianca.cruz@hotel5.com','02-555-1005',5,5,33000.00,'2022-08-22',NULL),(82,'Ezekiel Perez','ezekiel.perez@hotel5.com','02-555-1006',6,5,23000.00,'2021-07-29',NULL),(83,'Laila Mendoza','laila.mendoza@hotel5.com','02-555-1007',7,5,46000.00,'2023-04-02',NULL),(84,'Marco Dela Cruz','marco.delacruz@hotel5.com','02-555-1008',8,5,65000.00,'2020-10-28',NULL),(85,'Karla Tan','karla.tan@hotel5.com','02-555-1009',9,5,52000.00,'2021-12-14',NULL),(86,'David Alvarez','david.alvarez@hotel5.com','02-555-1010',10,5,29000.00,'2022-06-06','2023-11-15'),(87,'Leah Garcia','leah.garcia@hotel5.com','02-555-1011',11,5,20000.00,'2020-02-23',NULL),(88,'Rico Villanueva','rico.villanueva@hotel5.com','02-555-1012',12,5,42000.00,'2021-11-17',NULL),(89,'Sandra Lopez','sandra.lopez@hotel5.com','02-555-1013',13,5,75000.00,'2022-05-19',NULL),(90,'Paolo Santos','paolo.santos@hotel5.com','02-555-1014',14,5,21000.00,'2023-03-27',NULL),(91,'Cristina Reyes','cristina.reyes@hotel5.com','02-555-1015',15,5,34000.00,'2020-11-02','2023-05-20'),(92,'Vince Hernandez','vince.hernandez@hotel5.com','02-555-1016',16,5,48000.00,'2021-07-07',NULL),(93,'Katrina De Leon','katrina.deleon@hotel5.com','02-555-1017',17,5,45000.00,'2022-09-22',NULL),(94,'Jericho Bautista','jericho.bautista@hotel5.com','02-555-1018',18,5,27000.00,'2020-04-15',NULL),(95,'Elaine Mercado','elaine.mercado@hotel5.com','02-555-1019',19,5,44000.00,'2021-01-11',NULL),(96,'Luca Romano','luca.romano@hotel6.com','02-600-1001',1,6,105000.00,'2020-06-18',NULL),(97,'Emma Johansson','emma.johansson@hotel6.com','02-600-1002',2,6,35000.00,'2022-02-12',NULL),(98,'Noah Schmidt','noah.schmidt@hotel6.com','02-600-1003',3,6,45000.00,'2021-07-05','2023-06-30'),(99,'Sophia Müller','sophia.mueller@hotel6.com','02-600-1004',4,6,38000.00,'2023-03-02',NULL),(100,'Maximilian Weber','maximilian.weber@hotel6.com','02-600-1005',5,6,28000.00,'2022-01-08',NULL),(101,'Olivia Martin','olivia.martin@hotel6.com','02-600-1006',6,6,23000.00,'2021-11-15',NULL),(102,'Lars Müller','lars.mueller@hotel6.com','02-600-1007',7,6,49000.00,'2023-04-07',NULL),(103,'Hannah König','hannah.koenig@hotel6.com','02-600-1008',8,6,66000.00,'2020-10-23',NULL),(104,'Felix Lang','felix.lang@hotel6.com','02-600-1009',9,6,52000.00,'2022-06-29',NULL),(105,'Julia Fischer','julia.fischer@hotel6.com','02-600-1010',10,6,28000.00,'2021-04-11','2023-05-18'),(106,'Niklas Becker','niklas.becker@hotel6.com','02-600-1011',11,6,22000.00,'2023-05-06',NULL),(107,'Charlotte Braun','charlotte.braun@hotel6.com','02-600-1012',12,6,48000.00,'2021-01-18',NULL),(108,'Theo Wagner','theo.wagner@hotel6.com','02-600-1013',13,6,74000.00,'2020-09-01',NULL),(109,'Lena Becker','lena.becker@hotel6.com','02-600-1014',14,6,20000.00,'2021-11-17',NULL),(110,'Matteo Müller','matteo.mueller@hotel6.com','02-600-1015',15,6,34000.00,'2023-03-14',NULL),(111,'Mia Schneider','mia.schneider@hotel6.com','02-600-1016',16,6,47000.00,'2020-11-09',NULL),(112,'Samuel Schmidt','samuel.schmidt@hotel6.com','02-600-1017',17,6,46000.00,'2022-10-20',NULL),(113,'Emilia Fischer','emilia.fischer@hotel6.com','02-600-1018',18,6,26000.00,'2021-02-14',NULL),(114,'Erik Weber','erik.weber@hotel6.com','02-600-1019',19,6,46000.00,'2022-08-11',NULL),(115,'Giulia Rossi','giulia.rossi@hotel7.com','02-700-1001',1,7,118000.00,'2021-06-24',NULL),(116,'Alessandro Bianchi','alessandro.bianchi@hotel7.com','02-700-1002',2,7,42000.00,'2020-11-08','2023-04-01'),(117,'Francesca De Luca','francesca.deluca@hotel7.com','02-700-1003',3,7,60000.00,'2022-03-16',NULL),(118,'Riccardo Moretti','riccardo.moretti@hotel7.com','02-700-1004',4,7,43000.00,'2023-01-02',NULL),(119,'Elena Caruso','elena.caruso@hotel7.com','02-700-1005',5,7,33000.00,'2022-07-29',NULL),(120,'Matteo Lombardi','matteo.lombardi@hotel7.com','02-700-1006',6,7,24000.00,'2020-04-17',NULL),(121,'Giovanni Russo','giovanni.russo@hotel7.com','02-700-1007',7,7,47000.00,'2021-08-22',NULL),(122,'Valentina Ricci','valentina.ricci@hotel7.com','02-700-1008',8,7,65000.00,'2023-05-09',NULL),(123,'Pietro Conti','pietro.conti@hotel7.com','02-700-1009',9,7,53000.00,'2021-12-11',NULL),(124,'Anna Santoro','anna.santoro@hotel7.com','02-700-1010',10,7,27000.00,'2022-11-06','2023-08-28'),(125,'Leonardo Mancini','leonardo.mancini@hotel7.com','02-700-1011',11,7,21000.00,'2020-05-18',NULL),(126,'Isabella Martini','isabella.martini@hotel7.com','02-700-1012',12,7,45000.00,'2023-02-02',NULL),(127,'Andrea Greco','andrea.greco@hotel7.com','02-700-1013',13,7,77000.00,'2022-01-30',NULL),(128,'Alessandra Gallo','alessandra.gallo@hotel7.com','02-700-1014',14,7,20000.00,'2020-03-25',NULL),(129,'Vittoria Perri','vittoria.perri@hotel7.com','02-700-1015',15,7,35000.00,'2022-05-09',NULL),(130,'Tommaso Ricci','tommaso.ricci@hotel7.com','02-700-1016',16,7,47000.00,'2023-07-07',NULL),(131,'Giorgio Lotti','giorgio.lotti@hotel7.com','02-700-1017',17,7,46000.00,'2021-06-11',NULL),(132,'Maria Beltrami','maria.beltrami@hotel7.com','02-700-1018',18,7,28000.00,'2020-09-01',NULL),(133,'Francesco Greco','francesco.greco@hotel7.com','02-700-1019',19,7,45000.00,'2022-08-18',NULL),(134,'LeBron James','lebron.james@hotel8.com','02-800-1001',1,8,110000.00,'2020-07-01',NULL),(135,'Cristiano Ronaldo','cristiano.ronaldo@hotel8.com','02-800-1002',2,8,44000.00,'2021-05-14','2023-11-15'),(136,'Serena Williams','serena.williams@hotel8.com','02-800-1003',3,8,62000.00,'2022-03-22',NULL),(137,'Tom Brady','tom.brady@hotel8.com','02-800-1004',4,8,41000.00,'2021-08-19',NULL),(138,'Lionel Messi','lionel.messi@hotel8.com','02-800-1005',5,8,32000.00,'2020-11-09',NULL),(139,'Kobe Bryant','kobe.bryant@hotel8.com','02-800-1006',6,8,24000.00,'2023-04-02',NULL),(140,'Michael Phelps','michael.phelps@hotel8.com','02-800-1007',7,8,46000.00,'2022-06-25',NULL),(141,'Roger Federer','roger.federer@hotel8.com','02-800-1008',8,8,65000.00,'2020-05-12',NULL),(142,'Usain Bolt','usain.bolt@hotel8.com','02-800-1009',9,8,54000.00,'2021-09-06',NULL),(143,'Tiger Woods','tiger.woods@hotel8.com','02-800-1010',10,8,28000.00,'2022-01-30','2023-08-30'),(144,'Stephen Curry','stephen.curry@hotel8.com','02-800-1011',11,8,22000.00,'2023-03-15',NULL),(145,'Megan Rapinoe','megan.rapinoe@hotel8.com','02-800-1012',12,8,49000.00,'2021-06-07',NULL),(146,'Vince Carter','vince.carter@hotel8.com','02-800-1013',13,8,77000.00,'2020-12-20',NULL),(147,'Paige VanZant','paige.vanzant@hotel8.com','02-800-1014',14,8,20000.00,'2022-09-25',NULL),(148,'Giannis Antetokounmpo','giannis.antetokounmpo@hotel8.com','02-800-1015',15,8,37000.00,'2023-02-10',NULL),(149,'Allyson Felix','allyson.felix@hotel8.com','02-800-1016',16,8,47000.00,'2021-08-17',NULL),(150,'Zlatan Ibrahimović','zlatan.ibrahimovic@hotel8.com','02-800-1017',17,8,46000.00,'2022-11-08',NULL),(151,'Neymar Jr.','neymar.jr@hotel8.com','02-800-1018',18,8,28000.00,'2021-07-13',NULL),(152,'David Beckham','david.beckham@hotel8.com','02-800-1019',19,8,45000.00,'2020-04-25',NULL),(153,'Wayne Gretzky','wayne.gretzky@hotel9.com','02-900-1001',1,9,105000.00,'2020-06-15',NULL),(154,'Sidney Crosby','sidney.crosby@hotel9.com','02-900-1002',2,9,40000.00,'2021-03-18','2023-10-21'),(155,'Marie-Philippe Poulin','marie.poulin@hotel9.com','02-900-1003',3,9,58000.00,'2021-04-25',NULL),(156,'Justin Bieber','justin.bieber@hotel9.com','02-900-1004',4,9,43000.00,'2022-07-12',NULL),(157,'Drake','drake@hotel9.com','02-900-1005',5,9,31000.00,'2020-09-10',NULL),(158,'Shania Twain','shania.twain@hotel9.com','02-900-1006',6,9,24000.00,'2023-01-22',NULL),(159,'Ryan Reynolds','ryan.reynolds@hotel9.com','02-900-1007',7,9,46000.00,'2022-06-09',NULL),(160,'Rachel McAdams','rachel.mcadams@hotel9.com','02-900-1008',8,9,65000.00,'2020-11-20',NULL),(161,'Seth Rogen','seth.rogen@hotel9.com','02-900-1009',9,9,53000.00,'2021-05-13',NULL),(162,'Michael Bublé','michael.buble@hotel9.com','02-900-1010',10,9,29000.00,'2021-07-17','2023-04-20'),(163,'Katherine Heigl','katherine.heigl@hotel9.com','02-900-1011',11,9,23000.00,'2023-02-25',NULL),(164,'Ryan Gosling','ryan.gosling@hotel9.com','02-900-1012',12,9,48000.00,'2021-08-05',NULL),(165,'Celine Dion','celine.dion@hotel9.com','02-900-1013',13,9,77000.00,'2020-02-11',NULL),(166,'Alanis Morissette','alanis.morissette@hotel9.com','02-900-1014',14,9,21000.00,'2021-10-02',NULL),(167,'Paulina Gretzky','paulina.gretzky@hotel9.com','02-900-1015',15,9,37000.00,'2022-03-06',NULL),(168,'Michael J. Fox','michael.fox@hotel9.com','02-900-1016',16,9,46000.00,'2021-09-30',NULL),(169,'Keanu Reeves','keanu.reeves@hotel9.com','02-900-1017',17,9,46000.00,'2022-04-18',NULL),(170,'Ellen Page','ellen.page@hotel9.com','02-900-1018',18,9,27000.00,'2021-06-13',NULL),(171,'Tessa Virtue','tessa.virtue@hotel9.com','02-900-1019',19,9,44000.00,'2022-08-22',NULL),(172,'Roger Federer','roger.federer@hotel10.com','03-800-1001',1,10,115000.00,'2020-06-10',NULL),(173,'Stan Wawrinka','stan.wawrinka@hotel10.com','03-800-1002',2,10,43000.00,'2021-03-23',NULL),(174,'Martina Hingis','martina.hingis@hotel10.com','03-800-1003',3,10,60000.00,'2020-12-17',NULL),(175,'Bastian Baker','bastian.baker@hotel10.com','03-800-1004',4,10,41000.00,'2021-05-12',NULL),(176,'Lara Gut','lara.gut@hotel10.com','03-800-1005',5,10,37000.00,'2022-07-21',NULL),(177,'Maja Nosack','maja.nosack@hotel10.com','03-800-1006',6,10,25000.00,'2021-10-02',NULL),(178,'Xenia Tchoumitcheva','xenia.tchoumitcheva@hotel10.com','03-800-1007',7,10,45000.00,'2020-04-14',NULL),(179,'Jasmin Ouschan','jasmin.ouschan@hotel10.com','03-800-1008',8,10,66000.00,'2021-08-16',NULL),(180,'Simon Ammann','simon.ammann@hotel10.com','03-800-1009',9,10,53000.00,'2022-05-27',NULL),(181,'Benedict von der Bank','benedict.von.der.bank@hotel10.com','03-800-1010',10,10,32000.00,'2022-01-09','2023-04-15'),(182,'Mélanie de Jesus','melanie.de.jesus@hotel10.com','03-800-1011',11,10,22000.00,'2023-02-10',NULL),(183,'Nino Schurter','nino.schurter@hotel10.com','03-800-1012',12,10,48000.00,'2021-07-19',NULL),(184,'Nadine Fähndrich','nadine.faehndrich@hotel10.com','03-800-1013',13,10,75000.00,'2020-03-12',NULL),(185,'Andrea Moser','andrea.moser@hotel10.com','03-800-1014',14,10,23000.00,'2021-11-28',NULL),(186,'Sven Tütschi','sven.tuetschi@hotel10.com','03-800-1015',15,10,36000.00,'2022-04-30',NULL),(187,'Rahel Kopp','rahel.kopp@hotel10.com','03-800-1016',16,10,44000.00,'2021-06-17',NULL),(188,'Linda Zuber','linda.zuber@hotel10.com','03-800-1017',17,10,41000.00,'2022-02-19',NULL),(189,'Martin Albrecht','martin.albrecht@hotel10.com','03-800-1018',18,10,26000.00,'2021-12-04',NULL),(190,'Nicole Fessel','nicole.fessel@hotel10.com','03-800-1019',19,10,45000.00,'2022-09-16',NULL);
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
  `CONTACT_NO` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`GUEST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'Kai Cenat','09-432-1543','kai.cenat@gmail.com'),(2,'Liam Stone','09-536-2789','liam.stone@yahoo.com'),(3,'Ava Thompson','09-649-3812','ava.thompson@hotmail.com'),(4,'Lucas Rivera','09-752-4931','lucas.rivera@outlook.com'),(5,'Olivia Wright','09-863-5042','olivia.wright@gmail.com'),(6,'Mason Bennett','09-974-6153','mason.bennett@aol.com'),(7,'Sophia Clark','09-085-7264','sophia.clark@icloud.com'),(8,'James Foster','09-196-8375','james.foster@live.com'),(9,'Ella Davis','09-207-9486','ella.davis@yahoo.com'),(10,'Benjamin Carter','09-318-0597','benjamin.carter@hotmail.com'),(11,'Charlotte Hughes','09-429-1608','charlotte.hughes@gmail.com'),(12,'Alexander Baker','09-540-2719','alexander.baker@outlook.com'),(13,'Amelia Adams','09-651-3820','amelia.adams@aol.com'),(14,'William Scott','09-762-4931','william.scott@yahoo.com'),(15,'Zoe Green','09-873-6042','zoe.green@hotmail.com'),(16,'Elijah Moore','09-984-7153','elijah.moore@gmail.com'),(17,'Scarlett Hall','09-095-8264','scarlett.hall@icloud.com'),(18,'Henry Parker','09-106-9375','henry.parker@live.com'),(19,'Lily Martinez','09-217-0486','lily.martinez@gmail.com'),(20,'Ethan Rodriguez','09-328-1597','ethan.rodriguez@outlook.com'),(21,'Chloe Allen','09-439-2708','chloe.allen@yahoo.com'),(22,'Jack Johnson','09-540-3819','jack.johnson@hotmail.com'),(23,'Grace Lee','09-651-4920','grace.lee@icloud.com'),(24,'Maxwell Harris','09-762-5031','maxwell.harris@gmail.com'),(25,'Isabella Young','09-873-6142','isabella.young@aol.com'),(26,'Sebastian King','09-984-7253','sebastian.king@yahoo.com'),(27,'Amos Scott','09-095-8364','amos.scott@outlook.com'),(28,'Layla Nelson','09-106-9475','layla.nelson@gmail.com'),(29,'Gabriel Mitchell','09-217-0586','gabriel.mitchell@hotmail.com'),(30,'Madeline Cooper','09-328-1697','madeline.cooper@aol.com'),(31,'Levi Walker','09-439-2708','levi.walker@gmail.com'),(32,'Natalie Edwards','09-540-3819','natalie.edwards@icloud.com'),(33,'Charlie Ross','09-651-4920','charlie.ross@yahoo.com'),(34,'Harper Green','09-762-5031','harper.green@outlook.com'),(35,'Owen Phillips','09-873-6142','owen.phillips@live.com'),(36,'Victoria Perez','09-984-7253','victoria.perez@hotmail.com'),(37,'Samuel Morgan','09-095-8364','samuel.morgan@icloud.com'),(38,'Juliana Bell','09-106-9475','juliana.bell@gmail.com'),(39,'Henry Lee','09-217-0586','henry.lee@aol.com'),(40,'Ariana Brooks','09-328-1697','ariana.brooks@outlook.com'),(41,'Jacob Carter','09-439-2708','jacob.carter@live.com'),(42,'Mia Ross','09-540-3819','mia.ross@yahoo.com'),(43,'Evan Flores','09-651-4920','evan.flores@icloud.com'),(44,'Sophie Turner','09-762-5031','sophie.turner@gmail.com'),(45,'Cole Martin','09-873-6142','cole.martin@aol.com'),(46,'Ella Wilson','09-984-7253','ella.wilson@hotmail.com'),(47,'Ryan Adams','09-095-8364','ryan.adams@live.com'),(48,'Luca Turner','09-106-9475','luca.turner@outlook.com'),(49,'Eleanor Brooks','09-217-0586','eleanor.brooks@gmail.com'),(50,'Mason Moore','09-328-1697','mason.moore@aol.com'),(51,'Emily Clark','09-439-2708','emily.clark@yahoo.com'),(52,'George Walker','09-540-3819','george.walker@icloud.com'),(53,'Lena Young','09-651-4920','lena.young@live.com'),(54,'Leonardo Martinez','09-762-5031','leonardo.martinez@aol.com'),(55,'Sophia Collins','09-873-6142','sophia.collins@hotmail.com'),(56,'Jayden Turner','09-984-7253','jayden.turner@outlook.com'),(57,'Evelyn White','09-095-8364','evelyn.white@icloud.com'),(58,'James Harris','09-106-9475','james.harris@yahoo.com'),(59,'Freya Scott','09-217-0586','freya.scott@live.com'),(60,'Andrew Wright','09-328-1697','andrew.wright@aol.com'),(61,'Nina Parker','09-439-2708','nina.parker@gmail.com'),(62,'Maya Adams','09-540-3819','maya.adams@outlook.com'),(63,'Caleb Thompson','09-651-4920','caleb.thompson@icloud.com'),(64,'Zoe Morris','09-762-5031','zoe.morris@live.com'),(65,'Jackson Foster','09-873-6142','jackson.foster@gmail.com'),(66,'Lucas Walker','09-984-7253','lucas.walker@yahoo.com'),(67,'Hannah Carter','09-095-8364','hannah.carter@aol.com'),(68,'Logan Davis','09-106-9475','logan.davis@icloud.com'),(69,'Maya Lewis','09-217-0586','maya.lewis@gmail.com'),(70,'Elijah Hall','09-328-1697','elijah.hall@hotmail.com'),(71,'Ivy Martinez','09-439-2708','ivy.martinez@live.com'),(72,'Abigail Ross','09-540-3819','abigail.ross@outlook.com'),(73,'Evan Scott','09-651-4920','evan.scott@aol.com'),(74,'Sophia Martinez','09-762-5031','sophia.martinez@yahoo.com'),(75,'Benjamin Wright','09-873-6142','benjamin.wright@icloud.com'),(76,'Harper Scott','09-984-7253','harper.scott@gmail.com'),(77,'Madison Johnson','09-095-8364','madison.johnson@hotmail.com'),(78,'Grace Martinez','09-106-9475','grace.martinez@live.com'),(79,'Christopher King','09-217-0586','christopher.king@aol.com'),(80,'Lily Davis','09-328-1697','lily.davis@gmail.com'),(81,'Emily Martin','09-439-2708','emily.martin@outlook.com'),(82,'Charlotte Adams','09-540-3819','charlotte.adams@icloud.com'),(83,'Abigail Moore','09-651-4920','abigail.moore@yahoo.com'),(84,'William Perez','09-762-5031','william.perez@aol.com'),(85,'Maddox Carter','09-873-6142','maddox.carter@gmail.com'),(86,'Ruby Wilson','09-984-7253','ruby.wilson@live.com'),(87,'Madeline Adams','09-095-8364','madeline.adams@outlook.com'),(88,'Amos Turner','09-555-7645','amos.turner@aol.com'),(89,'Tessa King','09-555-8756','tessa.king@hotmail.com'),(90,'Dylan Scott','09-555-9867','dylan.scott@gmail.com'),(91,'Harriet Morris','09-555-0978','harriet.morris@outlook.com'),(92,'Miles Harris','09-555-2089','miles.harris@live.com'),(93,'Aiden Hall','09-555-3190','aiden.hall@icloud.com'),(94,'Nora Davis','09-555-4301','nora.davis@aol.com'),(95,'Eli Perez','09-555-5412','eli.perez@hotmail.com'),(96,'Clara Brooks','09-555-6523','clara.brooks@live.com'),(97,'Owen Young','09-555-7634','owen.young@outlook.com'),(98,'Luna Cooper','09-555-8745','luna.cooper@gmail.com'),(99,'Jaxon Moore','09-555-9856','jaxon.moore@icloud.com'),(100,'Leah Johnson','09-555-0967','leah.johnson@aol.com');
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
  `HOTEL_EMAIL` varchar(125) DEFAULT NULL,
  `CONTACT_NO` varchar(15) DEFAULT NULL,
  `LOCATION_ID` int DEFAULT NULL,
  PRIMARY KEY (`HOTEL_ID`),
  KEY `LOCATION_ID` (`LOCATION_ID`),
  CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`LOCATION_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'NYC Hotel','nychotelinquiries@hotmail.com','555-5678',1),(2,'Maple Toronto Hotel','contact@mapletoronto.ca','555-1234',2),(3,'Pine Berlin Inn','reservations@pineberlin.de','555-0982',3),(4,'Oak Paris Hotel','booking@oakparis.fr','555-4321',4),(5,'Maple Rome Suites','info@maplerome.it','555-6789',5),(6,'Birch Sydney Lodge','hello@birchsydney.au','555-1111',6),(7,'Cedar São Paulo Hotel','reservations@cedarsaopaulo.br','555-6532',7),(8,'Willow London Inn','info@willowlondon.co.uk','555-6769',8),(9,'Pine Tokyo Hotel','contact@pinetokyo.jp','555-0720',9),(10,'Redwood Zurich Hotel','info@redwoodzurich.ch','555-1124',10),(11,'Hotel dell\'Angelo','hoteldellangelo@hotmail.com','555-6327',10);
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
  `STREET_ADDRESS` varchar(125) DEFAULT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `POSTAL_CODE` varchar(15) DEFAULT NULL,
  `STATE_PROVINCE` varchar(45) DEFAULT NULL,
  `COUNTRY_ID` int DEFAULT NULL,
  PRIMARY KEY (`LOCATION_ID`),
  KEY `COUNTRY_ID` (`COUNTRY_ID`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `country` (`COUNTRY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'123 Main St','New York','10001','NY',1),(2,'456 Maple Ave','Toronto','M4B 1B4','Ontario',2),(3,'789 Pine St','Berlin','10115','Berlin',3),(4,'101 Oak St','Paris','75001','Île-de-France',4),(5,'202 Maple St','Rome','00100','Lazio',5),(6,'303 Birch St','Sydney','2000','NSW',6),(7,'404 Cedar St','São Paulo','01000','SP',7),(8,'505 Willow St','London','E1 6AN','England',8),(9,'606 Pine Rd','Tokyo','100-0001','Tokyo',9),(10,'707 Redwood Ave','Zurich','8001','Zurich',10);
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
  `RESERVATION_ID` int DEFAULT NULL,
  `PAYMENT_DATE` date NOT NULL,
  `AMOUNT` decimal(10,2) NOT NULL,
  `DISCOUNT_ID` int DEFAULT NULL,
  `PAYMENT_METHOD_ID` int DEFAULT NULL,
  PRIMARY KEY (`PAYMENT_ID`),
  KEY `RESERVATION_ID` (`RESERVATION_ID`),
  KEY `DISCOUNT_ID` (`DISCOUNT_ID`),
  KEY `PAYMENT_METHOD_ID` (`PAYMENT_METHOD_ID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`RESERVATION_ID`) REFERENCES `reservation` (`RESERVATION_ID`) ON DELETE CASCADE,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`DISCOUNT_ID`) REFERENCES `discount` (`DISCOUNT_ID`) ON DELETE CASCADE,
  CONSTRAINT `payment_ibfk_3` FOREIGN KEY (`PAYMENT_METHOD_ID`) REFERENCES `payment_method` (`PAYMENT_METHOD_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,'2024-11-23',179.82,3,1),(2,79,'2024-11-23',119.76,5,4),(3,86,'2024-11-23',300.00,NULL,2),(4,42,'2024-11-23',79.68,7,3),(5,8,'2024-11-23',1000.00,NULL,6),(6,82,'2024-11-23',1000.00,NULL,1),(7,48,'2024-11-23',447.75,8,3),(8,43,'2024-11-23',349.13,6,5),(9,89,'2024-11-23',90.00,NULL,2),(10,98,'2024-11-23',180.00,NULL,4),(11,5,'2024-11-23',119.88,1,1),(12,6,'2024-11-23',500.00,NULL,5),(13,70,'2024-11-23',120.00,NULL,6),(14,7,'2024-11-23',300.00,NULL,4),(15,13,'2024-11-23',299.70,3,2),(16,19,'2024-11-23',249.63,4,6),(17,32,'2024-11-23',350.00,NULL,3),(18,9,'2024-11-23',997.50,6,1),(19,94,'2024-11-23',300.00,NULL,6),(20,39,'2024-11-23',200.00,NULL,2),(21,77,'2024-11-23',79.92,1,3),(22,81,'2024-11-23',349.13,6,6),(23,87,'2024-11-23',499.50,1,2),(24,46,'2024-11-23',250.00,NULL,4),(25,88,'2024-11-23',180.00,NULL,6),(26,80,'2024-11-23',249.63,4,5),(27,40,'2024-11-23',79.92,3,2),(28,91,'2024-11-23',350.00,NULL,4),(29,96,'2024-11-23',249.00,7,6),(30,57,'2024-11-23',996.00,7,1),(31,27,'2024-11-23',1000.00,NULL,2),(32,67,'2024-11-23',249.00,7,6),(33,49,'2024-11-23',350.00,NULL,5),(34,12,'2024-11-23',180.00,NULL,5),(35,92,'2024-11-23',79.92,3,4),(36,3,'2024-11-23',500.00,NULL,1),(37,17,'2024-11-23',1000.00,NULL,5),(38,10,'2024-11-23',499.25,9,6),(39,78,'2024-11-23',299.55,9,3),(40,63,'2024-11-23',179.73,9,1),(41,84,'2024-11-23',200.00,NULL,2),(42,65,'2024-11-23',249.75,3,2),(43,47,'2024-11-23',249.63,4,3),(44,72,'2024-11-23',249.75,3,4),(45,16,'2024-11-23',200.00,NULL,3),(46,35,'2024-11-23',500.00,NULL,3),(47,99,'2024-11-23',300.00,NULL,5),(48,100,'2024-11-23',79.80,6,1);
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
  `GUEST_ID` int DEFAULT NULL,
  `CHECKIN_DATE` date NOT NULL,
  `CHECKOUT_DATE` date NOT NULL,
  `RESERVATION_STATUS` enum('Paid','Not Paid') NOT NULL,
  `ROOM_ID` int DEFAULT NULL,
  PRIMARY KEY (`RESERVATION_ID`),
  KEY `GUEST_ID` (`GUEST_ID`),
  KEY `ROOM_ID` (`ROOM_ID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GUEST_ID`) REFERENCES `guest` (`GUEST_ID`) ON DELETE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`ROOM_ID`) REFERENCES `rooms` (`ROOM_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,'2024-11-01','2024-11-05','Paid',121),(2,2,'2024-11-02','2024-11-06','Not Paid',137),(3,3,'2024-11-03','2024-11-07','Paid',104),(4,4,'2024-11-04','2024-11-08','Not Paid',115),(5,5,'2024-11-05','2024-11-09','Paid',140),(6,6,'2024-11-06','2024-11-10','Paid',118),(7,7,'2024-11-07','2024-11-11','Paid',133),(8,8,'2024-11-08','2024-11-12','Paid',108),(9,9,'2024-11-09','2024-11-13','Paid',122),(10,10,'2024-11-10','2024-11-14','Paid',132),(11,11,'2024-11-01','2024-11-05','Not Paid',35),(12,12,'2024-11-02','2024-11-06','Paid',21),(13,13,'2024-11-03','2024-11-07','Paid',47),(14,14,'2024-11-04','2024-11-08','Not Paid',12),(15,15,'2024-11-05','2024-11-09','Not Paid',4),(16,16,'2024-11-06','2024-11-10','Paid',17),(17,17,'2024-11-07','2024-11-11','Paid',50),(18,18,'2024-11-08','2024-11-12','Not Paid',28),(19,19,'2024-11-09','2024-11-13','Paid',30),(20,20,'2024-11-10','2024-11-14','Not Paid',19),(21,21,'2024-11-11','2024-11-15','Not Paid',63),(22,22,'2024-11-12','2024-11-16','Not Paid',72),(23,23,'2024-11-13','2024-11-17','Not Paid',55),(24,24,'2024-11-14','2024-11-18','Not Paid',81),(25,25,'2024-11-15','2024-11-19','Not Paid',58),(26,26,'2024-11-16','2024-11-20','Not Paid',92),(27,27,'2024-11-17','2024-11-21','Paid',100),(28,28,'2024-11-18','2024-11-22','Not Paid',85),(29,29,'2024-11-19','2024-11-23','Not Paid',67),(30,30,'2024-11-20','2024-11-24','Not Paid',76),(31,31,'2024-11-21','2024-11-25','Not Paid',163),(32,32,'2024-11-22','2024-11-26','Paid',175),(33,33,'2024-11-23','2024-11-27','Not Paid',157),(34,34,'2024-11-24','2024-11-28','Not Paid',191),(35,35,'2024-11-25','2024-11-29','Paid',168),(36,36,'2024-11-26','2024-11-30','Not Paid',179),(37,37,'2024-11-27','2024-12-01','Not Paid',152),(38,38,'2024-11-28','2024-12-02','Not Paid',183),(39,39,'2024-11-29','2024-12-03','Paid',195),(40,40,'2024-11-30','2024-12-04','Paid',160),(41,41,'2024-12-01','2024-12-05','Not Paid',215),(42,42,'2024-12-02','2024-12-06','Paid',224),(43,43,'2024-12-03','2024-12-07','Paid',239),(44,44,'2024-12-04','2024-12-08','Not Paid',210),(45,45,'2024-12-05','2024-12-09','Not Paid',213),(46,46,'2024-12-06','2024-12-10','Paid',220),(47,47,'2024-12-07','2024-12-11','Paid',248),(48,48,'2024-12-08','2024-12-12','Paid',237),(49,49,'2024-12-09','2024-12-13','Paid',225),(50,50,'2024-12-10','2024-12-14','Not Paid',241),(51,51,'2024-12-01','2024-12-05','Not Paid',267),(52,52,'2024-12-02','2024-12-06','Not Paid',279),(53,53,'2024-12-03','2024-12-07','Not Paid',289),(54,54,'2024-12-04','2024-12-08','Not Paid',256),(55,55,'2024-12-05','2024-12-09','Not Paid',290),(56,56,'2024-12-06','2024-12-10','Not Paid',260),(57,57,'2024-12-07','2024-12-11','Paid',272),(58,58,'2024-12-08','2024-12-12','Not Paid',258),(59,59,'2024-12-09','2024-12-13','Not Paid',280),(60,60,'2024-12-10','2024-12-14','Not Paid',296),(61,61,'2024-12-01','2024-12-05','Not Paid',312),(62,62,'2024-12-02','2024-12-06','Not Paid',324),(63,63,'2024-12-03','2024-12-07','Paid',335),(64,64,'2024-12-04','2024-12-08','Not Paid',321),(65,65,'2024-12-05','2024-12-09','Paid',330),(66,66,'2024-12-06','2024-12-10','Not Paid',308),(67,67,'2024-12-07','2024-12-11','Paid',302),(68,68,'2024-12-08','2024-12-12','Not Paid',313),(69,69,'2024-12-09','2024-12-13','Not Paid',315),(70,70,'2024-12-10','2024-12-14','Paid',340),(71,71,'2024-12-01','2024-12-05','Not Paid',372),(72,72,'2024-12-02','2024-12-06','Paid',380),(73,73,'2024-12-03','2024-12-07','Not Paid',391),(74,74,'2024-12-04','2024-12-08','Not Paid',378),(75,75,'2024-12-05','2024-12-09','Not Paid',365),(76,76,'2024-12-06','2024-12-10','Not Paid',358),(77,77,'2024-12-07','2024-12-11','Paid',374),(78,78,'2024-12-08','2024-12-12','Paid',369),(79,79,'2024-12-09','2024-12-13','Paid',390),(80,80,'2024-12-10','2024-12-14','Paid',352),(81,81,'2024-12-01','2024-12-05','Paid',425),(82,82,'2024-12-02','2024-12-06','Paid',436),(83,83,'2024-12-03','2024-12-07','Not Paid',441),(84,84,'2024-12-04','2024-12-08','Paid',417),(85,85,'2024-12-05','2024-12-09','Not Paid',408),(86,86,'2024-12-06','2024-12-10','Paid',419),(87,87,'2024-12-07','2024-12-11','Paid',432),(88,88,'2024-12-08','2024-12-12','Paid',421),(89,89,'2024-12-09','2024-12-13','Paid',427),(90,90,'2024-12-10','2024-12-14','Not Paid',438),(91,91,'2024-12-01','2024-12-05','Paid',461),(92,92,'2024-12-02','2024-12-06','Paid',474),(93,93,'2024-12-03','2024-12-07','Not Paid',483),(94,94,'2024-12-04','2024-12-08','Paid',455),(95,95,'2024-12-05','2024-12-09','Not Paid',465),(96,96,'2024-12-06','2024-12-10','Paid',470),(97,97,'2024-12-07','2024-12-11','Not Paid',491),(98,98,'2024-12-08','2024-12-12','Paid',485),(99,99,'2024-12-09','2024-12-13','Paid',497),(100,100,'2024-12-10','2024-12-14','Paid',460);
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
  `TYPE_ID` int DEFAULT NULL,
  `HOTEL_ID` int DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `TYPE_ID` (`TYPE_ID`),
  KEY `HOTEL_ID` (`HOTEL_ID`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`TYPE_ID`) REFERENCES `roomtype` (`ROOMTYPE_ID`) ON DELETE CASCADE,
  CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`HOTEL_ID`) REFERENCES `hotel` (`HOTEL_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=551 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,101,2,1),(2,102,3,1),(3,103,4,1),(4,104,5,1),(5,105,6,1),(6,106,7,1),(7,107,8,1),(8,108,9,1),(9,109,10,1),(10,110,11,1),(11,111,12,1),(12,112,13,1),(13,113,14,1),(14,114,1,1),(15,115,2,1),(16,116,3,1),(17,117,4,1),(18,118,5,1),(19,119,6,1),(20,120,7,1),(21,121,8,1),(22,122,9,1),(23,123,10,1),(24,124,11,1),(25,125,12,1),(26,126,13,1),(27,127,14,1),(28,128,1,1),(29,129,2,1),(30,130,3,1),(31,131,4,1),(32,132,5,1),(33,133,6,1),(34,134,7,1),(35,135,8,1),(36,136,9,1),(37,137,10,1),(38,138,11,1),(39,139,12,1),(40,140,13,1),(41,141,14,1),(42,142,1,1),(43,143,2,1),(44,144,3,1),(45,145,4,1),(46,146,5,1),(47,147,6,1),(48,148,7,1),(49,149,8,1),(50,150,9,1),(51,201,2,2),(52,202,3,2),(53,203,4,2),(54,204,5,2),(55,205,6,2),(56,206,7,2),(57,207,8,2),(58,208,9,2),(59,209,10,2),(60,210,11,2),(61,211,12,2),(62,212,13,2),(63,213,14,2),(64,214,1,2),(65,215,2,2),(66,216,3,2),(67,217,4,2),(68,218,5,2),(69,219,6,2),(70,220,7,2),(71,221,8,2),(72,222,9,2),(73,223,10,2),(74,224,11,2),(75,225,12,2),(76,226,13,2),(77,227,14,2),(78,228,1,2),(79,229,2,2),(80,230,3,2),(81,231,4,2),(82,232,5,2),(83,233,6,2),(84,234,7,2),(85,235,8,2),(86,236,9,2),(87,237,10,2),(88,238,11,2),(89,239,12,2),(90,240,13,2),(91,241,14,2),(92,242,1,2),(93,243,2,2),(94,244,3,2),(95,245,4,2),(96,246,5,2),(97,247,6,2),(98,248,7,2),(99,249,8,2),(100,250,9,2),(101,301,2,3),(102,302,3,3),(103,303,4,3),(104,304,5,3),(105,305,6,3),(106,306,7,3),(107,307,8,3),(108,308,9,3),(109,309,10,3),(110,310,11,3),(111,311,12,3),(112,312,13,3),(113,313,14,3),(114,314,1,3),(115,315,2,3),(116,316,3,3),(117,317,4,3),(118,318,5,3),(119,319,6,3),(120,320,7,3),(121,321,8,3),(122,322,9,3),(123,323,10,3),(124,324,11,3),(125,325,12,3),(126,326,13,3),(127,327,14,3),(128,328,1,3),(129,329,2,3),(130,330,3,3),(131,331,4,3),(132,332,5,3),(133,333,6,3),(134,334,7,3),(135,335,8,3),(136,336,9,3),(137,337,10,3),(138,338,11,3),(139,339,12,3),(140,340,13,3),(141,341,14,3),(142,342,1,3),(143,343,2,3),(144,344,3,3),(145,345,4,3),(146,346,5,3),(147,347,6,3),(148,348,7,3),(149,349,8,3),(150,350,9,3),(151,401,2,4),(152,402,3,4),(153,403,4,4),(154,404,5,4),(155,405,6,4),(156,406,7,4),(157,407,8,4),(158,408,9,4),(159,409,10,4),(160,410,11,4),(161,411,12,4),(162,412,13,4),(163,413,14,4),(164,414,1,4),(165,415,2,4),(166,416,3,4),(167,417,4,4),(168,418,5,4),(169,419,6,4),(170,420,7,4),(171,421,8,4),(172,422,9,4),(173,423,10,4),(174,424,11,4),(175,425,12,4),(176,426,13,4),(177,427,14,4),(178,428,1,4),(179,429,2,4),(180,430,3,4),(181,431,4,4),(182,432,5,4),(183,433,6,4),(184,434,7,4),(185,435,8,4),(186,436,9,4),(187,437,10,4),(188,438,11,4),(189,439,12,4),(190,440,13,4),(191,441,14,4),(192,442,1,4),(193,443,2,4),(194,444,3,4),(195,445,4,4),(196,446,5,4),(197,447,6,4),(198,448,7,4),(199,449,8,4),(200,450,9,4),(201,501,2,5),(202,502,3,5),(203,503,4,5),(204,504,5,5),(205,505,6,5),(206,506,7,5),(207,507,8,5),(208,508,9,5),(209,509,10,5),(210,510,11,5),(211,511,12,5),(212,512,13,5),(213,513,14,5),(214,514,1,5),(215,515,2,5),(216,516,3,5),(217,517,4,5),(218,518,5,5),(219,519,6,5),(220,520,7,5),(221,521,8,5),(222,522,9,5),(223,523,10,5),(224,524,11,5),(225,525,12,5),(226,526,13,5),(227,527,14,5),(228,528,1,5),(229,529,2,5),(230,530,3,5),(231,531,4,5),(232,532,5,5),(233,533,6,5),(234,534,7,5),(235,535,8,5),(236,536,9,5),(237,537,10,5),(238,538,11,5),(239,539,12,5),(240,540,13,5),(241,541,14,5),(242,542,1,5),(243,543,2,5),(244,544,3,5),(245,545,4,5),(246,546,5,5),(247,547,6,5),(248,548,7,5),(249,549,8,5),(250,550,9,5),(251,601,2,6),(252,602,3,6),(253,603,4,6),(254,604,5,6),(255,605,6,6),(256,606,7,6),(257,607,8,6),(258,608,9,6),(259,609,10,6),(260,610,11,6),(261,611,12,6),(262,612,13,6),(263,613,14,6),(264,614,1,6),(265,615,2,6),(266,616,3,6),(267,617,4,6),(268,618,5,6),(269,619,6,6),(270,620,7,6),(271,621,8,6),(272,622,9,6),(273,623,10,6),(274,624,11,6),(275,625,12,6),(276,626,13,6),(277,627,14,6),(278,628,1,6),(279,629,2,6),(280,630,3,6),(281,631,4,6),(282,632,5,6),(283,633,6,6),(284,634,7,6),(285,635,8,6),(286,636,9,6),(287,637,10,6),(288,638,11,6),(289,639,12,6),(290,640,13,6),(291,641,14,6),(292,642,1,6),(293,643,2,6),(294,644,3,6),(295,645,4,6),(296,646,5,6),(297,647,6,6),(298,648,7,6),(299,649,8,6),(300,650,9,6),(301,701,2,7),(302,702,3,7),(303,703,4,7),(304,704,5,7),(305,705,6,7),(306,706,7,7),(307,707,8,7),(308,708,9,7),(309,709,10,7),(310,710,11,7),(311,711,12,7),(312,712,13,7),(313,713,14,7),(314,714,1,7),(315,715,2,7),(316,716,3,7),(317,717,4,7),(318,718,5,7),(319,719,6,7),(320,720,7,7),(321,721,8,7),(322,722,9,7),(323,723,10,7),(324,724,11,7),(325,725,12,7),(326,726,13,7),(327,727,14,7),(328,728,1,7),(329,729,2,7),(330,730,3,7),(331,731,4,7),(332,732,5,7),(333,733,6,7),(334,734,7,7),(335,735,8,7),(336,736,9,7),(337,737,10,7),(338,738,11,7),(339,739,12,7),(340,740,13,7),(341,741,14,7),(342,742,1,7),(343,743,2,7),(344,744,3,7),(345,745,4,7),(346,746,5,7),(347,747,6,7),(348,748,7,7),(349,749,8,7),(350,750,9,7),(351,801,2,8),(352,802,3,8),(353,803,4,8),(354,804,5,8),(355,805,6,8),(356,806,7,8),(357,807,8,8),(358,808,9,8),(359,809,10,8),(360,810,11,8),(361,811,12,8),(362,812,13,8),(363,813,14,8),(364,814,1,8),(365,815,2,8),(366,816,3,8),(367,817,4,8),(368,818,5,8),(369,819,6,8),(370,820,7,8),(371,821,8,8),(372,822,9,8),(373,823,10,8),(374,824,11,8),(375,825,12,8),(376,826,13,8),(377,827,14,8),(378,828,1,8),(379,829,2,8),(380,830,3,8),(381,831,4,8),(382,832,5,8),(383,833,6,8),(384,834,7,8),(385,835,8,8),(386,836,9,8),(387,837,10,8),(388,838,11,8),(389,839,12,8),(390,840,13,8),(391,841,14,8),(392,842,1,8),(393,843,2,8),(394,844,3,8),(395,845,4,8),(396,846,5,8),(397,847,6,8),(398,848,7,8),(399,849,8,8),(400,850,9,8),(401,901,2,9),(402,902,3,9),(403,903,4,9),(404,904,5,9),(405,905,6,9),(406,906,7,9),(407,907,8,9),(408,908,9,9),(409,909,10,9),(410,910,11,9),(411,911,12,9),(412,912,13,9),(413,913,14,9),(414,914,1,9),(415,915,2,9),(416,916,3,9),(417,917,4,9),(418,918,5,9),(419,919,6,9),(420,920,7,9),(421,921,8,9),(422,922,9,9),(423,923,10,9),(424,924,11,9),(425,925,12,9),(426,926,13,9),(427,927,14,9),(428,928,1,9),(429,929,2,9),(430,930,3,9),(431,931,4,9),(432,932,5,9),(433,933,6,9),(434,934,7,9),(435,935,8,9),(436,936,9,9),(437,937,10,9),(438,938,11,9),(439,939,12,9),(440,940,13,9),(441,941,14,9),(442,942,1,9),(443,943,2,9),(444,944,3,9),(445,945,4,9),(446,946,5,9),(447,947,6,9),(448,948,7,9),(449,949,8,9),(450,950,9,9),(451,1001,2,10),(452,1002,3,10),(453,1003,4,10),(454,1004,5,10),(455,1005,6,10),(456,1006,7,10),(457,1007,8,10),(458,1008,9,10),(459,1009,10,10),(460,1010,11,10),(461,1011,12,10),(462,1012,13,10),(463,1013,14,10),(464,1014,1,10),(465,1015,2,10),(466,1016,3,10),(467,1017,4,10),(468,1018,5,10),(469,1019,6,10),(470,1020,7,10),(471,1021,8,10),(472,1022,9,10),(473,1023,10,10),(474,1024,11,10),(475,1025,12,10),(476,1026,13,10),(477,1027,14,10),(478,1028,1,10),(479,1029,2,10),(480,1030,3,10),(481,1031,4,10),(482,1032,5,10),(483,1033,6,10),(484,1034,7,10),(485,1035,8,10),(486,1036,9,10),(487,1037,10,10),(488,1038,11,10),(489,1039,12,10),(490,1040,13,10),(491,1041,14,10),(492,1042,1,10),(493,1043,2,10),(494,1044,3,10),(495,1045,4,10),(496,1046,5,10),(497,1047,6,10),(498,1048,7,10),(499,1049,8,10),(500,1050,9,10),(501,1101,2,11),(502,1102,3,11),(503,1103,4,11),(504,1104,5,11),(505,1105,6,11),(506,1106,7,11),(507,1107,8,11),(508,1108,9,11),(509,1109,10,11),(510,1110,11,11),(511,1111,12,11),(512,1112,13,11),(513,1113,14,11),(514,1114,1,11),(515,1115,2,11),(516,1116,3,11),(517,1117,4,11),(518,1118,5,11),(519,1119,6,11),(520,1120,7,11),(521,1121,8,11),(522,1122,9,11),(523,1123,10,11),(524,1124,11,11),(525,1125,12,11),(526,1126,13,11),(527,1127,14,11),(528,1128,1,11),(529,1129,2,11),(530,1130,3,11),(531,1131,4,11),(532,1132,5,11),(533,1133,6,11),(534,1134,7,11),(535,1135,8,11),(536,1136,9,11),(537,1137,10,11),(538,1138,11,11),(539,1139,12,11),(540,1140,13,11),(541,1141,14,11),(542,1142,1,11),(543,1143,2,11),(544,1144,3,11),(545,1145,4,11),(546,1146,5,11),(547,1147,6,11),(548,1148,7,11),(549,1149,8,11),(550,1150,9,11);
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

-- Dump completed on 2024-11-23 18:28:47
