CREATE DATABASE  IF NOT EXISTS `db2_jpa`;
USE `db2_jpa`;
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: db2_jpa
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `optional_product`
--

DROP TABLE IF EXISTS `optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_product` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `monthly_fee` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_product`
--

LOCK TABLES `optional_product` WRITE;
/*!40000 ALTER TABLE `optional_product` DISABLE KEYS */;
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
  `serv_packgID` int NOT NULL,
  `serv_ID` int NOT NULL,
  PRIMARY KEY (`serv_packgID`,`serv_ID`),
  KEY `pckg_servID_idx` (`serv_ID`),
  CONSTRAINT `pckgc_serv_pckgID` FOREIGN KEY (`serv_packgID`) REFERENCES `service_package` (`id`),
  CONSTRAINT `pckgc_servID` FOREIGN KEY (`serv_ID`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pckg_contains`
--

LOCK TABLES `pckg_contains` WRITE;
/*!40000 ALTER TABLE `pckg_contains` DISABLE KEYS */;
/*!40000 ALTER TABLE `pckg_contains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pckg_has`
--

DROP TABLE IF EXISTS `pckg_has`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pckg_has` (
  `serv_pckgID` int NOT NULL,
  `val_perID` int NOT NULL,
  PRIMARY KEY (`serv_pckgID`,`val_perID`),
  KEY `pckg_valid_perID_idx` (`val_perID`),
  CONSTRAINT `pckg_serv_pckgID` FOREIGN KEY (`serv_pckgID`) REFERENCES `service_package` (`id`),
  CONSTRAINT `pckg_valid_perID` FOREIGN KEY (`val_perID`) REFERENCES `validity_period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pckg_has`
--

LOCK TABLES `pckg_has` WRITE;
/*!40000 ALTER TABLE `pckg_has` DISABLE KEYS */;
/*!40000 ALTER TABLE `pckg_has` ENABLE KEYS */;
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
  KEY `prod_serv_pckgID_idx` (`serv_pckgID`),
  CONSTRAINT `prod_op_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`),
  CONSTRAINT `prod_serv_pckgID` FOREIGN KEY (`serv_pckgID`) REFERENCES `service_package` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_associated_with`
--

LOCK TABLES `prod_associated_with` WRITE;
/*!40000 ALTER TABLE `prod_associated_with` DISABLE KEYS */;
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
  KEY `op_prodID_idx` (`op_prodID`),
  CONSTRAINT `sas_opt_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`),
  CONSTRAINT `sas_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
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
  `id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  `gb` int DEFAULT NULL,
  `extra_gb_fee` decimal(5,2) DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `extra_min_fee` decimal(5,2) DEFAULT NULL,
  `sms` int DEFAULT NULL,
  `extra_sms_fee` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
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
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `subsID` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
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
  KEY `subi_op_prodID_idx` (`op_prodID`),
  CONSTRAINT `subi_op_prodID` FOREIGN KEY (`op_prodID`) REFERENCES `optional_product` (`id`),
  CONSTRAINT `subi_subsID` FOREIGN KEY (`subsID`) REFERENCES `subscription` (`id`)
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
  KEY `sub_valid_perID_idx` (`valid_perID`),
  KEY `sub_orderID_idx` (`orderID`),
  CONSTRAINT `sub_orderID` FOREIGN KEY (`orderID`) REFERENCES `order` (`id`),
  CONSTRAINT `sub_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
  CONSTRAINT `sub_valid_perID` FOREIGN KEY (`valid_perID`) REFERENCES `validity_period` (`id`)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'giggi98','giggi98','giggi98',0,NULL),(51,'Gee','Gee','Gee',0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validity_period`
--

DROP TABLE IF EXISTS `validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_period` (
  `id` int NOT NULL,
  `months` int NOT NULL,
  `monthly_fee` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_period`
--

LOCK TABLES `validity_period` WRITE;
/*!40000 ALTER TABLE `validity_period` DISABLE KEYS */;
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

-- Dump completed on 2021-12-17 15:42:33
