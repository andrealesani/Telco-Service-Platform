CREATE DATABASE  IF NOT EXISTS `db2_jpa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db2_jpa`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: db2_jpa
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.20.04.3

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
-- Table structure for table `OPENJPA_SEQUENCE_TABLE`
--

DROP TABLE IF EXISTS `OPENJPA_SEQUENCE_TABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OPENJPA_SEQUENCE_TABLE` (
  `ID` tinyint NOT NULL,
  `SEQUENCE_VALUE` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPENJPA_SEQUENCE_TABLE`
--

LOCK TABLES `OPENJPA_SEQUENCE_TABLE` WRITE;
/*!40000 ALTER TABLE `OPENJPA_SEQUENCE_TABLE` DISABLE KEYS */;
INSERT INTO `OPENJPA_SEQUENCE_TABLE` VALUES (0,3501);
/*!40000 ALTER TABLE `OPENJPA_SEQUENCE_TABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditing`
--

DROP TABLE IF EXISTS `auditing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rejected_amount` decimal(10,2) NOT NULL,
  `rejection_ts` datetime NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_UDITING_USER` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditing`
--

LOCK TABLES `auditing` WRITE;
/*!40000 ALTER TABLE `auditing` DISABLE KEYS */;
INSERT INTO `auditing` VALUES (1,1080.00,'2022-05-12 23:04:16',3351);
/*!40000 ALTER TABLE `auditing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optional_product`
--

DROP TABLE IF EXISTS `optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `monthly_fee` decimal(10,2) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_product`
--

LOCK TABLES `optional_product` WRITE;
/*!40000 ALTER TABLE `optional_product` DISABLE KEYS */;
INSERT INTO `optional_product` VALUES (1,5.00,'SMS News Feed'),(2,10.00,'Internet TV Channel');
/*!40000 ALTER TABLE `optional_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `optional_product_AFTER_INSERT` AFTER INSERT ON `optional_product` FOR EACH ROW BEGIN
	# SALES_REPORT_PRODUCT_SALES
	INSERT INTO sales_report_product_sales (opt_prod_id, total_sales)
	VALUES (new.id, 0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `sales_report_insolvent_users`
--

DROP TABLE IF EXISTS `sales_report_insolvent_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report_insolvent_users` (
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_sales_report_insolvent_users_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_report_insolvent_users`
--

LOCK TABLES `sales_report_insolvent_users` WRITE;
/*!40000 ALTER TABLE `sales_report_insolvent_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_report_insolvent_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_report_packages`
--

DROP TABLE IF EXISTS `sales_report_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report_packages` (
  `serv_pckg_id` int NOT NULL,
  `purchases` int DEFAULT NULL,
  `sales_value_no_products` decimal(10,2) DEFAULT NULL,
  `sales_value_with_products` decimal(10,2) DEFAULT NULL,
  `avg_num_products` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`serv_pckg_id`),
  CONSTRAINT `fk_sales_report_packages_1` FOREIGN KEY (`serv_pckg_id`) REFERENCES `service_package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_report_packages`
--

LOCK TABLES `sales_report_packages` WRITE;
/*!40000 ALTER TABLE `sales_report_packages` DISABLE KEYS */;
INSERT INTO `sales_report_packages` VALUES (3401,2,672.00,672.00,0.00),(3402,0,0.00,0.00,0.00),(3403,1,540.00,1080.00,2.00),(3404,0,0.00,0.00,0.00);
/*!40000 ALTER TABLE `sales_report_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_report_product_sales`
--

DROP TABLE IF EXISTS `sales_report_product_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report_product_sales` (
  `opt_prod_id` int NOT NULL,
  `total_sales` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`opt_prod_id`),
  CONSTRAINT `fk_sales_report_product_sales_1` FOREIGN KEY (`opt_prod_id`) REFERENCES `optional_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_report_product_sales`
--

LOCK TABLES `sales_report_product_sales` WRITE;
/*!40000 ALTER TABLE `sales_report_product_sales` DISABLE KEYS */;
INSERT INTO `sales_report_product_sales` VALUES (1,180.00),(2,360.00);
/*!40000 ALTER TABLE `sales_report_product_sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_report_suspended_orders`
--

DROP TABLE IF EXISTS `sales_report_suspended_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report_suspended_orders` (
  `sub_order_id` int NOT NULL,
  PRIMARY KEY (`sub_order_id`),
  CONSTRAINT `fk_sales_report_suspended_orders_1` FOREIGN KEY (`sub_order_id`) REFERENCES `subscription_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_report_suspended_orders`
--

LOCK TABLES `sales_report_suspended_orders` WRITE;
/*!40000 ALTER TABLE `sales_report_suspended_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_report_suspended_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_report_validity_packages`
--

DROP TABLE IF EXISTS `sales_report_validity_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_report_validity_packages` (
  `serv_pckg_id` int NOT NULL,
  `val_period_id` int NOT NULL,
  `purchases` int DEFAULT NULL,
  PRIMARY KEY (`serv_pckg_id`,`val_period_id`),
  KEY `fk_sales_report_validity_packages_1_idx` (`val_period_id`),
  CONSTRAINT `fk_sales_report_validity_packages_1` FOREIGN KEY (`val_period_id`) REFERENCES `validity_period` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_report_validity_packages_2` FOREIGN KEY (`serv_pckg_id`) REFERENCES `service_package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_report_validity_packages`
--

LOCK TABLES `sales_report_validity_packages` WRITE;
/*!40000 ALTER TABLE `sales_report_validity_packages` DISABLE KEYS */;
INSERT INTO `sales_report_validity_packages` VALUES (3401,1,1),(3401,2,1),(3402,2,0),(3402,3,0),(3403,1,0),(3403,2,0),(3403,3,1),(3404,1,0),(3404,2,0),(3404,3,0);
/*!40000 ALTER TABLE `sales_report_validity_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `extra_gb_fee` decimal(10,2) DEFAULT NULL,
  `extra_min_fee` decimal(10,2) DEFAULT NULL,
  `extra_sms_fee` decimal(10,2) DEFAULT NULL,
  `gb` int DEFAULT NULL,
  `minutes` int DEFAULT NULL,
  `sms` int DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,0.00,0.00,0.00,0,0,0,'FIXED_PHONE'),(2,0.00,0.10,0.05,0,1000,500,'MOBILE_PHONE'),(3,0.05,0.00,0.00,500,0,0,'FIXED_INTERNET'),(4,0.10,0.00,0.00,50,0,0,'MOBILE_INTERNET');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `service_BEFORE_INSERT` BEFORE INSERT ON `service` FOR EACH ROW BEGIN
	# SERVICE_TYPE_CONSTRAINTS
	IF 
		(	new.`type` = "MOBILE_PHONE"	OR new.`type`="FIXED_PHONE"	)
        AND
        (	new.gb != 0 OR new.extra_gb_fee != 0.0	)
	THEN
		SIGNAL sqlstate '45001' set message_text = 
        "This type of service cannot offer internet navigation!";
    END IF;
    IF 
		(	new.`type` = "FIXED_PHONE" OR new.`type` = "FIXED_INTERNET" OR new.`type` = "MOBILE_INTERNET"	)
        AND
        (	new.minutes != 0 OR new.extra_min_fee != 0.0	)
	THEN
		SIGNAL sqlstate '45001' set message_text = 
        "This type of service cannot offer phone call time!";
	END IF;
    IF 
		(	new.`type` = "FIXED_PHONE" OR new.`type` = "FIXED_INTERNET" OR new.`type` = "MOBILE_INTERNET"	)
        AND
        (	new.sms != 0 OR new.extra_sms_fee != 0.0)
	THEN
		SIGNAL sqlstate '45001' set message_text = 
        "This type of service cannot offer sms messages!";
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `service_package`
--

DROP TABLE IF EXISTS `service_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3405 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
INSERT INTO `service_package` VALUES (3401,'Basic'),(3402,'Family'),(3403,'Business'),(3404,'All Inclusive');
/*!40000 ALTER TABLE `service_package` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `service_package_AFTER_INSERT` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN
	# SALES_REPORT_PACKAGES
	INSERT INTO sales_report_packages (serv_pckg_id, purchases, sales_value_no_products, sales_value_with_products, avg_num_products)
	VALUES (new.id, 0, 0.0, 0.0, 0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `service_package_optional_product`
--

DROP TABLE IF EXISTS `service_package_optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_optional_product` (
  `serv_pckg_id` int DEFAULT NULL,
  `opt_prod_id` int DEFAULT NULL,
  KEY `I_SRVCDCT_ELEMENT` (`opt_prod_id`),
  KEY `I_SRVCDCT_SERV_PCKG_ID` (`serv_pckg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_optional_product`
--

LOCK TABLES `service_package_optional_product` WRITE;
/*!40000 ALTER TABLE `service_package_optional_product` DISABLE KEYS */;
INSERT INTO `service_package_optional_product` VALUES (3402,2),(3403,1),(3403,2),(3404,1),(3404,2);
/*!40000 ALTER TABLE `service_package_optional_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package_service`
--

DROP TABLE IF EXISTS `service_package_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_service` (
  `serv_pckg_id` int DEFAULT NULL,
  `service_id` int DEFAULT NULL,
  KEY `I_SRVCRVC_ELEMENT` (`service_id`),
  KEY `I_SRVCRVC_SERV_PCKG_ID` (`serv_pckg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_service`
--

LOCK TABLES `service_package_service` WRITE;
/*!40000 ALTER TABLE `service_package_service` DISABLE KEYS */;
INSERT INTO `service_package_service` VALUES (3401,2),(3401,4),(3402,1),(3402,3),(3403,3),(3403,1),(3404,4),(3404,1),(3404,2),(3404,3);
/*!40000 ALTER TABLE `service_package_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package_validity_period`
--

DROP TABLE IF EXISTS `service_package_validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_validity_period` (
  `serv_pckg_id` int DEFAULT NULL,
  `val_period_id` int DEFAULT NULL,
  KEY `I_SRVCPRD_ELEMENT` (`val_period_id`),
  KEY `I_SRVCPRD_SERV_PCKG_ID` (`serv_pckg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_validity_period`
--

LOCK TABLES `service_package_validity_period` WRITE;
/*!40000 ALTER TABLE `service_package_validity_period` DISABLE KEYS */;
INSERT INTO `service_package_validity_period` VALUES (3401,1),(3401,2),(3402,2),(3402,3),(3403,1),(3403,3),(3403,2),(3404,1),(3404,2),(3404,3);
/*!40000 ALTER TABLE `service_package_validity_period` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `service_package_validity_period_AFTER_INSERT` AFTER INSERT ON `service_package_validity_period` FOR EACH ROW BEGIN
	# SALES_REPORT_VALIDITY_PACKAGES
	INSERT INTO sales_report_validity_packages (serv_pckg_id, val_period_id, purchases)
	VALUES (new.serv_pckg_id, new.val_period_id, 0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `subscription_order`
--

DROP TABLE IF EXISTS `subscription_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creation_ts` datetime NOT NULL,
  `start_date_ts` datetime DEFAULT NULL,
  `total_value` decimal(10,2) DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `serv_pckg_id` int DEFAULT NULL,
  `val_period_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_SBSCRDR_SERVICEPACKAGE` (`serv_pckg_id`),
  KEY `I_SBSCRDR_USER` (`user_id`),
  KEY `I_SBSCRDR_VALIDITYPERIOD` (`val_period_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_order`
--

LOCK TABLES `subscription_order` WRITE;
/*!40000 ALTER TABLE `subscription_order` DISABLE KEYS */;
INSERT INTO `subscription_order` VALUES (1,'2022-05-12 22:52:04','2022-05-16 22:00:00',432.00,_binary '',3351,3401,2),(2,'2022-05-12 22:57:39','2022-05-14 22:00:00',1080.00,_binary '',3351,3403,3),(3,'2022-05-12 23:08:28','2022-05-15 22:00:00',240.00,_binary '',3451,3401,1);
/*!40000 ALTER TABLE `subscription_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `subscription_order_BEFORE_INSERT` BEFORE INSERT ON `subscription_order` FOR EACH ROW BEGIN
	# SET_CREATION_DATE
    SET new.creation_ts = now();
    
    # SET_SERVICE_TOTAL_VALUE
	SET new.total_value = 	(	(	SELECT monthly_fee 
									FROM validity_period 
									WHERE id = new.val_period_id	) 
								*
								(	SELECT months 
									FROM validity_period 
									WHERE id = new.val_period_id	)	);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `subscription_order_AFTER_UPDATE` AFTER UPDATE ON `subscription_order` FOR EACH ROW BEGIN
    IF
		new.valid = 0
	THEN 
		# SET_USER_INSOLVENT
		UPDATE `user`
		SET insolvent = 1
		WHERE id = new.user_id;
		
        # INCREASE_REJECTED_PAYMENTS
		IF	(	SELECT rejected_payments
				FROM `user`
				WHERE id = new.user_id	) < 2
		THEN
			UPDATE `user`
			SET rejected_payments = rejected_payments + 1
			WHERE id = new.user_id;
            
		# GENERATE_AUDITING
		ELSE
			UPDATE `user`
			SET rejected_payments = 0
			WHERE id = new.user_id;
            
            INSERT INTO auditing (rejected_amount, rejection_ts, user_id) 
            VALUES(new.total_value, now(), new.user_id);
		END IF;
        
        # SALES_REPORT_SUSPENDED_ORDERS
        IF
			old.valid IS null 
            OR
            old.valid = 1
		THEN
			INSERT INTO sales_report_suspended_orders (sub_order_id)
			VALUES (new.id);
        END IF;
	ELSEIF 
			new.valid = 1
            AND
            (	old.valid IS null 
				OR 
                old.valid = 0	)
    THEN
		# SET_USER_SOLVENT
		IF
			NOT EXISTS (	SELECT *
							FROM subscription_order
							WHERE valid = 0 AND user_id = new.user_id	)
		THEN
			UPDATE `user`
			SET insolvent = 0
			WHERE id = new.user_id;
		END IF;
        
        # SALES_REPORT_SUSPENDED_ORDERS
		DELETE FROM sales_report_suspended_orders
		WHERE sub_order_id = new.id;
        
        # SALES_REPORT_PACKAGES
		UPDATE sales_report_packages AS srp
		SET srp.purchases = srp.purchases + 1,
			srp.sales_value_no_products = srp.sales_value_no_products + (	SELECT months*monthly_fee
																			FROM validity_period AS vp
																			WHERE vp.id = new.val_period_id	),
			srp.sales_value_with_products = srp.sales_value_with_products + new.total_value,
			srp.avg_num_products =	(	SELECT coalesce(avg(package.prod_count), 0)
										FROM (
											SELECT count(*) AS prod_count
											FROM subscription_order_optional_product AS soop
											JOIN subscription_order AS so
											ON so.id = soop.sub_order_id
											WHERE so.serv_pckg_id = new.serv_pckg_id
											AND so.valid = 1
											GROUP BY so.id	)	package	) 
		WHERE srp.serv_pckg_id = new.serv_pckg_id;
			
		# SALES_REPORT_VALIDITY_PACKAGES
		UPDATE sales_report_validity_packages
		SET purchases = purchases + 1
		WHERE serv_pckg_id = new.serv_pckg_id
		AND val_period_id = new.val_period_id;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `subscription_order_optional_product`
--

DROP TABLE IF EXISTS `subscription_order_optional_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription_order_optional_product` (
  `sub_order_id` int DEFAULT NULL,
  `opt_prod_id` int DEFAULT NULL,
  KEY `I_SBSCDCT_ELEMENT` (`opt_prod_id`),
  KEY `I_SBSCDCT_SUB_ORDER_ID` (`sub_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_order_optional_product`
--

LOCK TABLES `subscription_order_optional_product` WRITE;
/*!40000 ALTER TABLE `subscription_order_optional_product` DISABLE KEYS */;
INSERT INTO `subscription_order_optional_product` VALUES (2,1),(2,2);
/*!40000 ALTER TABLE `subscription_order_optional_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `subscription_order_optional_product_AFTER_INSERT` AFTER INSERT ON `subscription_order_optional_product` FOR EACH ROW BEGIN
	# SET_PRODUCTS_TOTAL_VALUE
    SET @vp_id =	(	SELECT val_period_id
                        FROM subscription_order
                        WHERE id = new.sub_order_id	);
                        
    UPDATE subscription_order 
    SET total_value = 	total_value 
						+	
						(	(	SELECT monthly_fee
                                FROM optional_product
                                WHERE id = new.opt_prod_id	)
							*
                            (	SELECT months
                                FROM validity_period 
                                WHERE id = @vp_id	)	)
	WHERE id = new.sub_order_id ;
    
    # SALES_REPORT_PRODUCT_SALES
    UPDATE sales_report_product_sales 
	SET total_sales = total_sales + (	SELECT vp.months
										FROM validity_period AS vp
										JOIN subscription_order AS so
										ON vp.id = so.val_period_id
										WHERE so.id = new.sub_order_id	)
									 * 
									(	SELECT monthly_fee
										FROM optional_product
										WHERE id = new.opt_prod_id	)
	WHERE opt_prod_id = new.opt_prod_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `insolvent` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rejected_payments` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `employee` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3452 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3351,'usr@mail.com',_binary '\0','usr',0,'usr',_binary '\0'),(3352,'emp@mail.com',_binary '\0','emp',0,'emp',_binary ''),(3451,'usr2@mail.com',_binary '\0','usr2',0,'usr2',_binary '\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_AFTER_UPDATE` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
	# SALES_REPORT_INSOLVENT_USERS
	IF 
		new.insolvent != old.insolvent
	THEN
		IF 
			new.insolvent = 1
		THEN
			INSERT INTO sales_report_insolvent_users (user_id)
			VALUES (new.id);
		ELSEIF
			new.insolvent = 0
		THEN
			DELETE FROM sales_report_insolvent_users
            WHERE user_id = new.id;
        END IF;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `validity_period`
--

DROP TABLE IF EXISTS `validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_period` (
  `id` int NOT NULL AUTO_INCREMENT,
  `monthly_fee` decimal(10,2) NOT NULL,
  `months` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_period`
--

LOCK TABLES `validity_period` WRITE;
/*!40000 ALTER TABLE `validity_period` DISABLE KEYS */;
INSERT INTO `validity_period` VALUES (1,20.00,12),(2,18.00,24),(3,15.00,36);
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

-- Dump completed on 2022-05-13  1:09:43
