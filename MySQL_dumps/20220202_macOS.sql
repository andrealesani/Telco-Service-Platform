CREATE DATABASE  IF NOT EXISTS `db2_jpa`;
USE `db2_jpa`;
-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: localhost    Database: db2_jpa
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `auditing`
--

DROP TABLE IF EXISTS `auditing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ts_rejection` datetime NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `aud_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditing`
--

LOCK TABLES `auditing` WRITE;
/*!40000 ALTER TABLE `auditing` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `openjpa_sequence_table`
--

DROP TABLE IF EXISTS `openjpa_sequence_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `openjpa_sequence_table` (
  `ID` tinyint NOT NULL,
  `SEQUENCE_VALUE` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `openjpa_sequence_table`
--

LOCK TABLES `openjpa_sequence_table` WRITE;
/*!40000 ALTER TABLE `openjpa_sequence_table` DISABLE KEYS */;
INSERT INTO `openjpa_sequence_table` VALUES (0,151);
/*!40000 ALTER TABLE `openjpa_sequence_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optional_product`
--

DROP TABLE IF EXISTS `optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `monthly_fee` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_product`
--

LOCK TABLES `optional_product` WRITE;
/*!40000 ALTER TABLE `optional_product` DISABLE KEYS */;
INSERT INTO `optional_product` VALUES (1,'Sms news feed',12.00);
/*!40000 ALTER TABLE `optional_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL,
  `creation_ts` datetime NOT NULL,
  `is_valid` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pckg_contains`
--

DROP TABLE IF EXISTS `pckg_contains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pckg_contains` (
  `serv_pckgID` int NOT NULL,
  `servID` int NOT NULL,
  PRIMARY KEY (`serv_pckgID`,`servID`),
  KEY `pckgc_servID_idx` (`servID`),
  CONSTRAINT `pckgc_serv_pckgID` FOREIGN KEY (`serv_pckgID`) REFERENCES `service_package` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `pckgc_servID` FOREIGN KEY (`servID`) REFERENCES `service` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pckg_contains`
--

LOCK TABLES `pckg_contains` WRITE;
/*!40000 ALTER TABLE `pckg_contains` DISABLE KEYS */;
INSERT INTO `pckg_contains` VALUES (1,1);
/*!40000 ALTER TABLE `pckg_contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pckg_lasts`
--

DROP TABLE IF EXISTS `pckg_lasts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pckg_lasts` (
  `serv_pckgID` int NOT NULL,
  `valid_perID` int NOT NULL,
  PRIMARY KEY (`serv_pckgID`,`valid_perID`),
  KEY `pl_val_perID_idx` (`valid_perID`),
  CONSTRAINT `pl_serv_pckgID` FOREIGN KEY (`serv_pckgID`) REFERENCES `service_package` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `pl_val_perID` FOREIGN KEY (`valid_perID`) REFERENCES `validity_period` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pckg_lasts`
--

LOCK TABLES `pckg_lasts` WRITE;
/*!40000 ALTER TABLE `pckg_lasts` DISABLE KEYS */;
INSERT INTO `pckg_lasts` VALUES (1,1);
/*!40000 ALTER TABLE `pckg_lasts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prod_associated_with`
--

DROP TABLE IF EXISTS `prod_associated_with`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prod_associated_with` (
  `op_prodID` int NOT NULL,
  `serv_pckgID` int NOT NULL,
  PRIMARY KEY (`op_prodID`,`serv_pckgID`),
  KEY `paw_serv_pckgID_idx` (`serv_pckgID`),
  CONSTRAINT `paw_op_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `paw_serv_pckgID` FOREIGN KEY (`serv_pckgID`) REFERENCES `service_package` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_associated_with`
--

LOCK TABLES `prod_associated_with` WRITE;
/*!40000 ALTER TABLE `prod_associated_with` DISABLE KEYS */;
INSERT INTO `prod_associated_with` VALUES (1,1);
/*!40000 ALTER TABLE `prod_associated_with` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sas_contains`
--

DROP TABLE IF EXISTS `sas_contains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sas_contains` (
  `userID` int NOT NULL,
  `op_prodID` int NOT NULL,
  PRIMARY KEY (`userID`,`op_prodID`),
  KEY `sas_op_prodID_idx` (`op_prodID`),
  CONSTRAINT `sas_op_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`),
  CONSTRAINT `sas_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sas_contains`
--

LOCK TABLES `sas_contains` WRITE;
/*!40000 ALTER TABLE `sas_contains` DISABLE KEYS */;
/*!40000 ALTER TABLE `sas_contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',100);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `gb` int DEFAULT NULL,
  `extra_gb_fee` decimal(5,2) DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `extra_min_fee` decimal(5,2) DEFAULT NULL,
  `sms` int DEFAULT NULL,
  `extra_sms_fee` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Fixed internet',50,1.00,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_activation_schedule`
--

DROP TABLE IF EXISTS `service_activation_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_activation_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activation_date` datetime NOT NULL,
  `deactivation_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_activation_schedule`
--

LOCK TABLES `service_activation_schedule` WRITE;
/*!40000 ALTER TABLE `service_activation_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_activation_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package`
--

DROP TABLE IF EXISTS `service_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `subsID` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
INSERT INTO `service_package` VALUES (1,'Mega GeeGee Offer',1);
/*!40000 ALTER TABLE `service_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_includes`
--

DROP TABLE IF EXISTS `sub_includes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_includes` (
  `subsID` int NOT NULL,
  `op_prodID` int NOT NULL,
  PRIMARY KEY (`subsID`,`op_prodID`),
  KEY `si_op_prodID_idx` (`op_prodID`),
  CONSTRAINT `si_op_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `si_subsID` FOREIGN KEY (`subsID`) REFERENCES `subscription` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_includes`
--

LOCK TABLES `sub_includes` WRITE;
/*!40000 ALTER TABLE `sub_includes` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_includes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `id` int NOT NULL,
  `userID` int NOT NULL,
  `valid_perID` int NOT NULL,
  `orderID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sub_userID_idx` (`userID`),
  KEY `sub_orderID_idx` (`orderID`),
  KEY `sub_val_perID_idx` (`valid_perID`),
  CONSTRAINT `sub_orderID` FOREIGN KEY (`orderID`) REFERENCES `order` (`id`),
  CONSTRAINT `sub_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
  CONSTRAINT `sub_val_perID` FOREIGN KEY (`valid_perID`) REFERENCES `validity_period` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `is_flagged` tinyint NOT NULL,
  `serv_act_schedID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Gee','Gee','Gee',0,NULL),(101,'gigi','gigi','gigino',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validity_period`
--

DROP TABLE IF EXISTS `validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_period` (
  `id` int NOT NULL AUTO_INCREMENT,
  `months` int NOT NULL,
  `monthly_fee` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_period`
--

LOCK TABLES `validity_period` WRITE;
/*!40000 ALTER TABLE `validity_period` DISABLE KEYS */;
INSERT INTO `validity_period` VALUES (1,12,20.00);
/*!40000 ALTER TABLE `validity_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db2_jpa'
--

--
-- Dumping routines for database 'db2_jpa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-02 17:47:04
