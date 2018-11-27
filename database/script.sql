CREATE DATABASE  IF NOT EXISTS `du_an_1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `du_an_1`;
-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: du_an_1
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dinh_dang_phim`
--

DROP TABLE IF EXISTS `dinh_dang_phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dinh_dang_phim` (
  `id` varchar(5) NOT NULL,
  `ten` varchar(20) NOT NULL,
  `phu_thu` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dinh_dang_phim`
--

LOCK TABLES `dinh_dang_phim` WRITE;
/*!40000 ALTER TABLE `dinh_dang_phim` DISABLE KEYS */;
INSERT INTO `dinh_dang_phim` VALUES ('2D','Định dạng 2D',0),('3D','Định dạng 3D',20000),('4D','Định dạng 4D',35000),('IMAX','Định dạng IMAX',50000);
/*!40000 ALTER TABLE `dinh_dang_phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `do_an`
--

DROP TABLE IF EXISTS `do_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `do_an` (
  `id` varchar(15) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `dang_ban` bit(1) NOT NULL,
  `loai_do_an_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DOAN_LOAIDOAN_idx` (`loai_do_an_id`),
  CONSTRAINT `FK_DOAN_LOAIDOAN` FOREIGN KEY (`loai_do_an_id`) REFERENCES `loai_do_an` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an`
--

LOCK TABLES `do_an` WRITE;
/*!40000 ALTER TABLE `do_an` DISABLE KEYS */;
INSERT INTO `do_an` VALUES ('DA001','Bắp Rang',_binary '\0','DA'),('DA002','Đùi Gà',_binary '','DA'),('DA003','Bắp rang bơ',_binary '\0','DA'),('NU001','Cocacola',_binary '','NU'),('NU002','Pepsi',_binary '','NU');
/*!40000 ALTER TABLE `do_an` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `do_an_BEFORE_INSERT` BEFORE INSERT ON `do_an` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 3;
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM do_an WHERE loai_do_an_id LIKE CONCAT(new.loai_do_an_id, '%')  ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(new.loai_do_an_id, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `do_an_chi_tiet`
--

DROP TABLE IF EXISTS `do_an_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `do_an_chi_tiet` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `don_gia` int(10) unsigned NOT NULL,
  `dang_ban` bit(1) DEFAULT b'1',
  `do_an_id` varchar(15) NOT NULL,
  `kich_co_do_an_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DOANCHITIET_DOAN_idx` (`do_an_id`),
  KEY `FK_DOANCHITIET_KICHCODOAN_idx` (`kich_co_do_an_id`),
  CONSTRAINT `FK_DOANCHITIET_DOAN` FOREIGN KEY (`do_an_id`) REFERENCES `do_an` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_DOANCHITIET_KICHCODOAN` FOREIGN KEY (`kich_co_do_an_id`) REFERENCES `kich_co_do_an` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an_chi_tiet`
--

LOCK TABLES `do_an_chi_tiet` WRITE;
/*!40000 ALTER TABLE `do_an_chi_tiet` DISABLE KEYS */;
INSERT INTO `do_an_chi_tiet` VALUES (1,6500,_binary '','DA003','L');
/*!40000 ALTER TABLE `do_an_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ghe_ngoi`
--

DROP TABLE IF EXISTS `ghe_ngoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ghe_ngoi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vi_tri_day` varchar(1) NOT NULL,
  `vi_tri_cot` int(10) unsigned NOT NULL,
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `loai_ghe_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GHE_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_GHE_LOAIGHE_idx` (`loai_ghe_id`),
  CONSTRAINT `FK_GHE_LOAIGHE` FOREIGN KEY (`loai_ghe_id`) REFERENCES `loai_ghe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GHE_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=709 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe_ngoi`
--

LOCK TABLES `ghe_ngoi` WRITE;
/*!40000 ALTER TABLE `ghe_ngoi` DISABLE KEYS */;
INSERT INTO `ghe_ngoi` VALUES (1,'A',1,1,'GT'),(2,'A',2,1,'GT'),(3,'A',3,1,'GT'),(4,'A',4,1,'GT'),(5,'A',5,1,'GT'),(6,'A',6,1,'GT'),(7,'A',7,1,'GT'),(8,'A',8,1,'GT'),(9,'A',9,1,'GT'),(10,'A',10,1,'GT'),(11,'A',11,1,'GT'),(12,'A',12,1,'GT'),(13,'A',13,1,'GT'),(14,'A',14,1,'GT'),(15,'A',15,1,'GT'),(16,'A',16,1,'GT'),(17,'A',17,1,'GT'),(18,'A',18,1,'GT'),(19,'B',1,1,'GT'),(20,'B',2,1,'GT'),(21,'B',3,1,'GT'),(22,'B',4,1,'GT'),(23,'B',5,1,'GT'),(24,'B',6,1,'GT'),(25,'B',7,1,'GT'),(26,'B',8,1,'GT'),(27,'B',9,1,'GT'),(28,'B',10,1,'GT'),(29,'B',11,1,'GT'),(30,'B',12,1,'GT'),(31,'B',13,1,'GT'),(32,'B',14,1,'GT'),(33,'B',15,1,'GT'),(34,'B',16,1,'GT'),(35,'B',17,1,'GT'),(36,'B',18,1,'GT'),(37,'C',1,1,'GT'),(38,'C',2,1,'GT'),(39,'C',3,1,'GT'),(40,'C',4,1,'GT'),(41,'C',5,1,'GT'),(42,'C',6,1,'GT'),(43,'C',7,1,'GT'),(44,'C',8,1,'GT'),(45,'C',9,1,'GT'),(46,'C',10,1,'GT'),(47,'C',11,1,'GT'),(48,'C',12,1,'GT'),(49,'C',13,1,'GT'),(50,'C',14,1,'GT'),(51,'C',15,1,'GT'),(52,'C',16,1,'GT'),(53,'C',17,1,'GT'),(54,'C',18,1,'GT'),(55,'D',1,1,'GT'),(56,'D',2,1,'GT'),(57,'D',3,1,'GT'),(58,'D',4,1,'GT'),(59,'D',5,1,'GT'),(60,'D',6,1,'GT'),(61,'D',7,1,'GT'),(62,'D',8,1,'GT'),(63,'D',9,1,'GT'),(64,'D',10,1,'GT'),(65,'D',11,1,'GT'),(66,'D',12,1,'GT'),(67,'D',13,1,'GT'),(68,'D',14,1,'GT'),(69,'D',15,1,'GT'),(70,'D',16,1,'GT'),(71,'D',17,1,'GT'),(72,'D',18,1,'GT'),(73,'E',1,1,'GT'),(74,'E',2,1,'GT'),(75,'E',3,1,'GT'),(76,'E',4,1,'GV'),(77,'E',5,1,'GV'),(78,'E',6,1,'GV'),(79,'E',7,1,'GV'),(80,'E',8,1,'GV'),(81,'E',9,1,'GV'),(82,'E',10,1,'GV'),(83,'E',11,1,'GV'),(84,'E',12,1,'GV'),(85,'E',13,1,'GV'),(86,'E',14,1,'GV'),(87,'E',15,1,'GV'),(88,'E',16,1,'GT'),(89,'E',17,1,'GT'),(90,'E',18,1,'GT'),(91,'F',1,1,'GT'),(92,'F',2,1,'GT'),(93,'F',3,1,'GT'),(94,'F',4,1,'GV'),(95,'F',5,1,'GV'),(96,'F',6,1,'GV'),(97,'F',7,1,'GV'),(98,'F',8,1,'GV'),(99,'F',9,1,'GV'),(100,'F',10,1,'GV'),(101,'F',11,1,'GV'),(102,'F',12,1,'GV'),(103,'F',13,1,'GV'),(104,'F',14,1,'GV'),(105,'F',15,1,'GV'),(106,'F',16,1,'GT'),(107,'F',17,1,'GT'),(108,'F',18,1,'GT'),(109,'G',1,1,'GT'),(110,'G',2,1,'GT'),(111,'G',3,1,'GT'),(112,'G',4,1,'GV'),(113,'G',5,1,'GV'),(114,'G',6,1,'GV'),(115,'G',7,1,'GV'),(116,'G',8,1,'GV'),(117,'G',9,1,'GV'),(118,'G',10,1,'GV'),(119,'G',11,1,'GV'),(120,'G',12,1,'GV'),(121,'G',13,1,'GV'),(122,'G',14,1,'GV'),(123,'G',15,1,'GV'),(124,'G',16,1,'GT'),(125,'G',17,1,'GT'),(126,'G',18,1,'GT'),(127,'H',1,1,'GT'),(128,'H',2,1,'GT'),(129,'H',3,1,'GT'),(130,'H',4,1,'GV'),(131,'H',5,1,'GV'),(132,'H',6,1,'GV'),(133,'H',7,1,'GV'),(134,'H',8,1,'GV'),(135,'H',9,1,'GV'),(136,'H',10,1,'GV'),(137,'H',11,1,'GV'),(138,'H',12,1,'GV'),(139,'H',13,1,'GV'),(140,'H',14,1,'GV'),(141,'H',15,1,'GV'),(142,'H',16,1,'GT'),(143,'H',17,1,'GT'),(144,'H',18,1,'GT'),(145,'I',1,1,'GT'),(146,'I',2,1,'GT'),(147,'I',3,1,'GT'),(148,'I',4,1,'GV'),(149,'I',5,1,'GV'),(150,'I',6,1,'GV'),(151,'I',7,1,'GV'),(152,'I',8,1,'GV'),(153,'I',9,1,'GV'),(154,'I',10,1,'GV'),(155,'I',11,1,'GV'),(156,'I',12,1,'GV'),(157,'I',13,1,'GV'),(158,'I',14,1,'GV'),(159,'I',15,1,'GV'),(160,'I',16,1,'GT'),(161,'I',17,1,'GT'),(162,'I',18,1,'GT'),(163,'J',1,1,'GT'),(164,'J',2,1,'GT'),(165,'J',3,1,'GT'),(166,'J',4,1,'GV'),(167,'J',5,1,'GV'),(168,'J',6,1,'GV'),(169,'J',7,1,'GV'),(170,'J',8,1,'GV'),(171,'J',9,1,'GV'),(172,'J',10,1,'GV'),(173,'J',11,1,'GV'),(174,'J',12,1,'GV'),(175,'J',13,1,'GV'),(176,'J',14,1,'GV'),(177,'J',15,1,'GV'),(178,'J',16,1,'GT'),(179,'J',17,1,'GT'),(180,'J',18,1,'GT'),(181,'K',1,1,'GT'),(182,'K',2,1,'GT'),(183,'K',3,1,'GT'),(184,'K',4,1,'GT'),(185,'K',5,1,'GT'),(186,'K',6,1,'GT'),(187,'K',7,1,'GT'),(188,'K',8,1,'GT'),(189,'K',9,1,'GT'),(190,'K',10,1,'GT'),(191,'K',11,1,'GT'),(192,'K',12,1,'GT'),(193,'K',13,1,'GT'),(194,'K',14,1,'GT'),(195,'K',15,1,'GT'),(196,'K',16,1,'GT'),(197,'K',17,1,'GT'),(198,'K',18,1,'GT'),(199,'L',1,1,'GT'),(200,'L',2,1,'GT'),(201,'L',3,1,'GT'),(202,'L',4,1,'GT'),(203,'L',5,1,'GT'),(204,'L',6,1,'GT'),(205,'L',7,1,'GT'),(206,'L',8,1,'GT'),(207,'L',9,1,'GT'),(208,'L',10,1,'GT'),(209,'L',11,1,'GT'),(210,'L',12,1,'GT'),(211,'L',13,1,'GT'),(212,'L',14,1,'GT'),(213,'L',15,1,'GT'),(214,'L',16,1,'GT'),(215,'L',17,1,'GT'),(216,'L',18,1,'GT'),(217,'A',1,2,'GT'),(218,'A',2,2,'GT'),(219,'A',3,2,'GT'),(220,'A',4,2,'GT'),(221,'A',5,2,'GT'),(222,'A',6,2,'GT'),(223,'A',7,2,'GT'),(224,'A',8,2,'GT'),(225,'A',9,2,'GT'),(226,'A',10,2,'GT'),(227,'A',11,2,'GT'),(228,'A',12,2,'GT'),(229,'A',13,2,'GT'),(230,'A',14,2,'GT'),(231,'A',15,2,'GT'),(232,'A',16,2,'GT'),(233,'B',1,2,'GT'),(234,'B',2,2,'GT'),(235,'B',3,2,'GT'),(236,'B',4,2,'GT'),(237,'B',5,2,'GT'),(238,'B',6,2,'GT'),(239,'B',7,2,'GT'),(240,'B',8,2,'GT'),(241,'B',9,2,'GT'),(242,'B',10,2,'GT'),(243,'B',11,2,'GT'),(244,'B',12,2,'GT'),(245,'B',13,2,'GT'),(246,'B',14,2,'GT'),(247,'B',15,2,'GT'),(248,'B',16,2,'GT'),(249,'C',1,2,'GT'),(250,'C',2,2,'GT'),(251,'C',3,2,'GT'),(252,'C',4,2,'GT'),(253,'C',5,2,'GT'),(254,'C',6,2,'GT'),(255,'C',7,2,'GT'),(256,'C',8,2,'GT'),(257,'C',9,2,'GT'),(258,'C',10,2,'GT'),(259,'C',11,2,'GT'),(260,'C',12,2,'GT'),(261,'C',13,2,'GT'),(262,'C',14,2,'GT'),(263,'C',15,2,'GT'),(264,'C',16,2,'GT'),(265,'D',1,2,'GT'),(266,'D',2,2,'GT'),(267,'D',3,2,'GT'),(268,'D',4,2,'GT'),(269,'D',5,2,'GT'),(270,'D',6,2,'GT'),(271,'D',7,2,'GT'),(272,'D',8,2,'GT'),(273,'D',9,2,'GT'),(274,'D',10,2,'GT'),(275,'D',11,2,'GT'),(276,'D',12,2,'GT'),(277,'D',13,2,'GT'),(278,'D',14,2,'GT'),(279,'D',15,2,'GT'),(280,'D',16,2,'GT'),(281,'E',1,2,'GT'),(282,'E',2,2,'GT'),(283,'E',3,2,'GT'),(284,'E',4,2,'GV'),(285,'E',5,2,'GV'),(286,'E',6,2,'GV'),(287,'E',7,2,'GV'),(288,'E',8,2,'GV'),(289,'E',9,2,'GV'),(290,'E',10,2,'GV'),(291,'E',11,2,'GV'),(292,'E',12,2,'GV'),(293,'E',13,2,'GV'),(294,'E',14,2,'GT'),(295,'E',15,2,'GT'),(296,'E',16,2,'GT'),(297,'F',1,2,'GT'),(298,'F',2,2,'GT'),(299,'F',3,2,'GT'),(300,'F',4,2,'GV'),(301,'F',5,2,'GV'),(302,'F',6,2,'GV'),(303,'F',7,2,'GV'),(304,'F',8,2,'GV'),(305,'F',9,2,'GV'),(306,'F',10,2,'GV'),(307,'F',11,2,'GV'),(308,'F',12,2,'GV'),(309,'F',13,2,'GV'),(310,'F',14,2,'GT'),(311,'F',15,2,'GT'),(312,'F',16,2,'GT'),(313,'G',1,2,'GT'),(314,'G',2,2,'GT'),(315,'G',3,2,'GT'),(316,'G',4,2,'GV'),(317,'G',5,2,'GV'),(318,'G',6,2,'GV'),(319,'G',7,2,'GV'),(320,'G',8,2,'GV'),(321,'G',9,2,'GV'),(322,'G',10,2,'GV'),(323,'G',11,2,'GV'),(324,'G',12,2,'GV'),(325,'G',13,2,'GV'),(326,'G',14,2,'GT'),(327,'G',15,2,'GT'),(328,'G',16,2,'GT'),(329,'H',1,2,'GT'),(330,'H',2,2,'GT'),(331,'H',3,2,'GT'),(332,'H',4,2,'GV'),(333,'H',5,2,'GV'),(334,'H',6,2,'GV'),(335,'H',7,2,'GV'),(336,'H',8,2,'GV'),(337,'H',9,2,'GV'),(338,'H',10,2,'GV'),(339,'H',11,2,'GV'),(340,'H',12,2,'GV'),(341,'H',13,2,'GV'),(342,'H',14,2,'GT'),(343,'H',15,2,'GT'),(344,'H',16,2,'GT'),(345,'I',1,2,'GT'),(346,'I',2,2,'GT'),(347,'I',3,2,'GT'),(348,'I',4,2,'GT'),(349,'I',5,2,'GT'),(350,'I',6,2,'GT'),(351,'I',7,2,'GT'),(352,'I',8,2,'GT'),(353,'I',9,2,'GT'),(354,'I',10,2,'GT'),(355,'I',11,2,'GT'),(356,'I',12,2,'GT'),(357,'I',13,2,'GT'),(358,'I',14,2,'GT'),(359,'I',15,2,'GT'),(360,'I',16,2,'GT'),(361,'J',1,2,'GT'),(362,'J',2,2,'GT'),(363,'J',3,2,'GT'),(364,'J',4,2,'GT'),(365,'J',5,2,'GT'),(366,'J',6,2,'GT'),(367,'J',7,2,'GT'),(368,'J',8,2,'GT'),(369,'J',9,2,'GT'),(370,'J',10,2,'GT'),(371,'J',11,2,'GT'),(372,'J',12,2,'GT'),(373,'J',13,2,'GT'),(374,'J',14,2,'GT'),(375,'J',15,2,'GT'),(376,'J',16,2,'GT'),(377,'A',1,3,'GT'),(378,'A',2,3,'GT'),(379,'A',3,3,'GT'),(380,'A',4,3,'GT'),(381,'A',5,3,'GT'),(382,'A',6,3,'GT'),(383,'A',7,3,'GT'),(384,'A',8,3,'GT'),(385,'A',9,3,'GT'),(386,'A',10,3,'GT'),(387,'A',11,3,'GT'),(388,'A',12,3,'GT'),(389,'A',13,3,'GT'),(390,'A',14,3,'GT'),(391,'B',1,3,'GT'),(392,'B',2,3,'GT'),(393,'B',3,3,'GT'),(394,'B',4,3,'GT'),(395,'B',5,3,'GT'),(396,'B',6,3,'GT'),(397,'B',7,3,'GT'),(398,'B',8,3,'GT'),(399,'B',9,3,'GT'),(400,'B',10,3,'GT'),(401,'B',11,3,'GT'),(402,'B',12,3,'GT'),(403,'B',13,3,'GT'),(404,'B',14,3,'GT'),(405,'C',1,3,'GT'),(406,'C',2,3,'GT'),(407,'C',3,3,'GT'),(408,'C',4,3,'GT'),(409,'C',5,3,'GT'),(410,'C',6,3,'GT'),(411,'C',7,3,'GT'),(412,'C',8,3,'GT'),(413,'C',9,3,'GT'),(414,'C',10,3,'GT'),(415,'C',11,3,'GT'),(416,'C',12,3,'GT'),(417,'C',13,3,'GT'),(418,'C',14,3,'GT'),(419,'D',1,3,'GT'),(420,'D',2,3,'GT'),(421,'D',3,3,'GT'),(422,'D',4,3,'GT'),(423,'D',5,3,'GT'),(424,'D',6,3,'GT'),(425,'D',7,3,'GT'),(426,'D',8,3,'GT'),(427,'D',9,3,'GT'),(428,'D',10,3,'GT'),(429,'D',11,3,'GT'),(430,'D',12,3,'GT'),(431,'D',13,3,'GT'),(432,'D',14,3,'GT'),(433,'E',1,3,'GT'),(434,'E',2,3,'GT'),(435,'E',3,3,'GT'),(436,'E',4,3,'GV'),(437,'E',5,3,'GV'),(438,'E',6,3,'GV'),(439,'E',7,3,'GV'),(440,'E',8,3,'GV'),(441,'E',9,3,'GV'),(442,'E',10,3,'GV'),(443,'E',11,3,'GV'),(444,'E',12,3,'GT'),(445,'E',13,3,'GT'),(446,'E',14,3,'GT'),(447,'F',1,3,'GT'),(448,'F',2,3,'GT'),(449,'F',3,3,'GT'),(450,'F',4,3,'GV'),(451,'F',5,3,'GV'),(452,'F',6,3,'GV'),(453,'F',7,3,'GV'),(454,'F',8,3,'GV'),(455,'F',9,3,'GV'),(456,'F',10,3,'GV'),(457,'F',11,3,'GV'),(458,'F',12,3,'GT'),(459,'F',13,3,'GT'),(460,'F',14,3,'GT'),(461,'G',1,3,'GT'),(462,'G',2,3,'GT'),(463,'G',3,3,'GT'),(464,'G',4,3,'GV'),(465,'G',5,3,'GV'),(466,'G',6,3,'GV'),(467,'G',7,3,'GV'),(468,'G',8,3,'GV'),(469,'G',9,3,'GV'),(470,'G',10,3,'GV'),(471,'G',11,3,'GV'),(472,'G',12,3,'GT'),(473,'G',13,3,'GT'),(474,'G',14,3,'GT'),(475,'H',1,3,'GT'),(476,'H',2,3,'GT'),(477,'H',3,3,'GT'),(478,'H',4,3,'GV'),(479,'H',5,3,'GV'),(480,'H',6,3,'GV'),(481,'H',7,3,'GV'),(482,'H',8,3,'GV'),(483,'H',9,3,'GV'),(484,'H',10,3,'GV'),(485,'H',11,3,'GV'),(486,'H',12,3,'GT'),(487,'H',13,3,'GT'),(488,'H',14,3,'GT'),(489,'I',1,3,'GT'),(490,'I',2,3,'GT'),(491,'I',3,3,'GT'),(492,'I',4,3,'GT'),(493,'I',5,3,'GT'),(494,'I',6,3,'GT'),(495,'I',7,3,'GT'),(496,'I',8,3,'GT'),(497,'I',9,3,'GT'),(498,'I',10,3,'GT'),(499,'I',11,3,'GT'),(500,'I',12,3,'GT'),(501,'I',13,3,'GT'),(502,'I',14,3,'GT'),(503,'J',1,3,'GT'),(504,'J',2,3,'GT'),(505,'J',3,3,'GT'),(506,'J',4,3,'GT'),(507,'J',5,3,'GT'),(508,'J',6,3,'GT'),(509,'J',7,3,'GT'),(510,'J',8,3,'GT'),(511,'J',9,3,'GT'),(512,'J',10,3,'GT'),(513,'J',11,3,'GT'),(514,'J',12,3,'GT'),(515,'J',13,3,'GT'),(516,'J',14,3,'GT'),(517,'A',1,4,'GT'),(518,'A',2,4,'GT'),(519,'A',3,4,'GT'),(520,'A',4,4,'GT'),(521,'A',5,4,'GT'),(522,'A',6,4,'GT'),(523,'A',7,4,'GT'),(524,'A',8,4,'GT'),(525,'A',9,4,'GT'),(526,'A',10,4,'GT'),(527,'A',11,4,'GT'),(528,'A',12,4,'GT'),(529,'A',13,4,'GT'),(530,'A',14,4,'GT'),(531,'A',15,4,'GT'),(532,'A',16,4,'GT'),(533,'B',1,4,'GT'),(534,'B',2,4,'GT'),(535,'B',3,4,'GT'),(536,'B',4,4,'GT'),(537,'B',5,4,'GT'),(538,'B',6,4,'GT'),(539,'B',7,4,'GT'),(540,'B',8,4,'GT'),(541,'B',9,4,'GT'),(542,'B',10,4,'GT'),(543,'B',11,4,'GT'),(544,'B',12,4,'GT'),(545,'B',13,4,'GT'),(546,'B',14,4,'GT'),(547,'B',15,4,'GT'),(548,'B',16,4,'GT'),(549,'C',1,4,'GT'),(550,'C',2,4,'GT'),(551,'C',3,4,'GT'),(552,'C',4,4,'GT'),(553,'C',5,4,'GT'),(554,'C',6,4,'GT'),(555,'C',7,4,'GT'),(556,'C',8,4,'GT'),(557,'C',9,4,'GT'),(558,'C',10,4,'GT'),(559,'C',11,4,'GT'),(560,'C',12,4,'GT'),(561,'C',13,4,'GT'),(562,'C',14,4,'GT'),(563,'C',15,4,'GT'),(564,'C',16,4,'GT'),(565,'D',1,4,'GT'),(566,'D',2,4,'GT'),(567,'D',3,4,'GT'),(568,'D',4,4,'GT'),(569,'D',5,4,'GT'),(570,'D',6,4,'GT'),(571,'D',7,4,'GT'),(572,'D',8,4,'GT'),(573,'D',9,4,'GT'),(574,'D',10,4,'GT'),(575,'D',11,4,'GT'),(576,'D',12,4,'GT'),(577,'D',13,4,'GT'),(578,'D',14,4,'GT'),(579,'D',15,4,'GT'),(580,'D',16,4,'GT'),(581,'E',1,4,'GT'),(582,'E',2,4,'GT'),(583,'E',3,4,'GT'),(584,'E',4,4,'GV'),(585,'E',5,4,'GV'),(586,'E',6,4,'GV'),(587,'E',7,4,'GV'),(588,'E',8,4,'GV'),(589,'E',9,4,'GV'),(590,'E',10,4,'GV'),(591,'E',11,4,'GV'),(592,'E',12,4,'GV'),(593,'E',13,4,'GV'),(594,'E',14,4,'GT'),(595,'E',15,4,'GT'),(596,'E',16,4,'GT'),(597,'F',1,4,'GT'),(598,'F',2,4,'GT'),(599,'F',3,4,'GT'),(600,'F',4,4,'GV'),(601,'F',5,4,'GV'),(602,'F',6,4,'GV'),(603,'F',7,4,'GV'),(604,'F',8,4,'GV'),(605,'F',9,4,'GV'),(606,'F',10,4,'GV'),(607,'F',11,4,'GV'),(608,'F',12,4,'GV'),(609,'F',13,4,'GV'),(610,'F',14,4,'GT'),(611,'F',15,4,'GT'),(612,'F',16,4,'GT'),(613,'G',1,4,'GT'),(614,'G',2,4,'GT'),(615,'G',3,4,'GT'),(616,'G',4,4,'GV'),(617,'G',5,4,'GV'),(618,'G',6,4,'GV'),(619,'G',7,4,'GV'),(620,'G',8,4,'GV'),(621,'G',9,4,'GV'),(622,'G',10,4,'GV'),(623,'G',11,4,'GV'),(624,'G',12,4,'GV'),(625,'G',13,4,'GV'),(626,'G',14,4,'GT'),(627,'G',15,4,'GT'),(628,'G',16,4,'GT'),(629,'H',1,4,'GT'),(630,'H',2,4,'GT'),(631,'H',3,4,'GT'),(632,'H',4,4,'GV'),(633,'H',5,4,'GV'),(634,'H',6,4,'GV'),(635,'H',7,4,'GV'),(636,'H',8,4,'GV'),(637,'H',9,4,'GV'),(638,'H',10,4,'GV'),(639,'H',11,4,'GV'),(640,'H',12,4,'GV'),(641,'H',13,4,'GV'),(642,'H',14,4,'GT'),(643,'H',15,4,'GT'),(644,'H',16,4,'GT'),(645,'I',1,4,'GT'),(646,'I',2,4,'GT'),(647,'I',3,4,'GT'),(648,'I',4,4,'GV'),(649,'I',5,4,'GV'),(650,'I',6,4,'GV'),(651,'I',7,4,'GV'),(652,'I',8,4,'GV'),(653,'I',9,4,'GV'),(654,'I',10,4,'GV'),(655,'I',11,4,'GV'),(656,'I',12,4,'GV'),(657,'I',13,4,'GV'),(658,'I',14,4,'GT'),(659,'I',15,4,'GT'),(660,'I',16,4,'GT'),(661,'J',1,4,'GT'),(662,'J',2,4,'GT'),(663,'J',3,4,'GT'),(664,'J',4,4,'GV'),(665,'J',5,4,'GV'),(666,'J',6,4,'GV'),(667,'J',7,4,'GV'),(668,'J',8,4,'GV'),(669,'J',9,4,'GV'),(670,'J',10,4,'GV'),(671,'J',11,4,'GV'),(672,'J',12,4,'GV'),(673,'J',13,4,'GV'),(674,'J',14,4,'GT'),(675,'J',15,4,'GT'),(676,'J',16,4,'GT'),(677,'K',1,4,'GT'),(678,'K',2,4,'GT'),(679,'K',3,4,'GT'),(680,'K',4,4,'GT'),(681,'K',5,4,'GT'),(682,'K',6,4,'GT'),(683,'K',7,4,'GT'),(684,'K',8,4,'GT'),(685,'K',9,4,'GT'),(686,'K',10,4,'GT'),(687,'K',11,4,'GT'),(688,'K',12,4,'GT'),(689,'K',13,4,'GT'),(690,'K',14,4,'GT'),(691,'K',15,4,'GT'),(692,'K',16,4,'GT'),(693,'L',1,4,'GT'),(694,'L',2,4,'GT'),(695,'L',3,4,'GT'),(696,'L',4,4,'GT'),(697,'L',5,4,'GT'),(698,'L',6,4,'GT'),(699,'L',7,4,'GT'),(700,'L',8,4,'GT'),(701,'L',9,4,'GT'),(702,'L',10,4,'GT'),(703,'L',11,4,'GT'),(704,'L',12,4,'GT'),(705,'L',13,4,'GT'),(706,'L',14,4,'GT'),(707,'L',15,4,'GT'),(708,'L',16,4,'GT');
/*!40000 ALTER TABLE `ghe_ngoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gia_ve`
--

DROP TABLE IF EXISTS `gia_ve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gia_ve` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) NOT NULL,
  `don_gia` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gia_ve`
--

LOCK TABLES `gia_ve` WRITE;
/*!40000 ALTER TABLE `gia_ve` DISABLE KEYS */;
INSERT INTO `gia_ve` VALUES (1,'Người lớn',60000),(2,'Trẻ em',30000),(3,'Học sinh, sinh viên',45000);
/*!40000 ALTER TABLE `gia_ve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hoa_don` (
  `id` varchar(15) NOT NULL,
  `ngay_ban` date NOT NULL,
  `nhan_vien_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HOADON_NGUOIDUNG_idx` (`nhan_vien_id`),
  CONSTRAINT `FK_HOADON_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES ('HD00000001','2018-11-25','NV00001'),('HD00000002','2018-11-25','NV00001');
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `hoa_don_BEFORE_INSERT` BEFORE INSERT ON `hoa_don` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 8;

	-- lay id moi duoc them vao
    SET @str := 'HD';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM hoa_don ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `hoa_don_chi_tiet`
--

DROP TABLE IF EXISTS `hoa_don_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hoa_don_chi_tiet` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `so_luong` int(10) unsigned NOT NULL,
  `tong_tien` int(10) unsigned NOT NULL,
  `do_an_chi_tiet_id` int(10) unsigned NOT NULL,
  `hoa_don_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HOADONCHITIET_HOADON_idx` (`hoa_don_id`),
  KEY `FK_HOADONCHITIET_DOAN_idx` (`do_an_chi_tiet_id`),
  CONSTRAINT `FK_HOADONCHITIET_DOANCHITIET` FOREIGN KEY (`do_an_chi_tiet_id`) REFERENCES `do_an_chi_tiet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_HOADONCHITIET_HOADON` FOREIGN KEY (`hoa_don_id`) REFERENCES `hoa_don` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don_chi_tiet`
--

LOCK TABLES `hoa_don_chi_tiet` WRITE;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` DISABLE KEYS */;
INSERT INTO `hoa_don_chi_tiet` VALUES (1,1,6500,1,'HD00000001'),(2,2,13000,1,'HD00000002');
/*!40000 ALTER TABLE `hoa_don_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `khach_hang` (
  `id` varchar(15) NOT NULL,
  `ho_ten` varchar(45) NOT NULL,
  `so_cmnd` varchar(20) NOT NULL,
  `mat_khau` varchar(45) NOT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dia_chi` varchar(100) DEFAULT NULL,
  `ngay_dang_ky` date NOT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_cmnd_UNIQUE` (`so_cmnd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES ('KH00001','Trần Vĩ Khang','123456','GB6NcSxqqe','0121546468','khang@gmail.com','CMT8','2018-11-08','2018-11-16',_binary '');
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `khach_hang_BEFORE_INSERT` BEFORE INSERT ON `khach_hang` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 5;

	-- cai dat tien to
    SET @str := 'KH';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM khach_hang ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `kich_co_do_an`
--

DROP TABLE IF EXISTS `kich_co_do_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `kich_co_do_an` (
  `id` varchar(10) NOT NULL,
  `ten` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kich_co_do_an`
--

LOCK TABLES `kich_co_do_an` WRITE;
/*!40000 ALTER TABLE `kich_co_do_an` DISABLE KEYS */;
INSERT INTO `kich_co_do_an` VALUES ('L','Cỡ lớn'),('M','Cỡ vừa'),('S','Cỡ nhỏ');
/*!40000 ALTER TABLE `kich_co_do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_do_an`
--

DROP TABLE IF EXISTS `loai_do_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loai_do_an` (
  `id` varchar(5) NOT NULL,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_do_an`
--

LOCK TABLES `loai_do_an` WRITE;
/*!40000 ALTER TABLE `loai_do_an` DISABLE KEYS */;
INSERT INTO `loai_do_an` VALUES ('BK','Bánh kẹo'),('DA','Đồ ăn nhanh'),('NU','Nước uống');
/*!40000 ALTER TABLE `loai_do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_ghe`
--

DROP TABLE IF EXISTS `loai_ghe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loai_ghe` (
  `id` varchar(2) NOT NULL,
  `ten_ghe` varchar(20) NOT NULL,
  `phu_thu` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_ghe`
--

LOCK TABLES `loai_ghe` WRITE;
/*!40000 ALTER TABLE `loai_ghe` DISABLE KEYS */;
INSERT INTO `loai_ghe` VALUES ('GT','Ghế thường',0),('GV','Ghế đặc biệt',35000);
/*!40000 ALTER TABLE `loai_ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_phim`
--

DROP TABLE IF EXISTS `loai_phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loai_phim` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_phim`
--

LOCK TABLES `loai_phim` WRITE;
/*!40000 ALTER TABLE `loai_phim` DISABLE KEYS */;
/*!40000 ALTER TABLE `loai_phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoi_dung`
--

DROP TABLE IF EXISTS `nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nguoi_dung` (
  `id` varchar(15) NOT NULL,
  `ho_ten` varchar(45) NOT NULL,
  `mat_khau` varchar(45) NOT NULL,
  `so_cmnd` varchar(20) NOT NULL,
  `so_dien_thoai` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `ngay_vao_lam` date NOT NULL,
  `gioi_tinh` bit(1) NOT NULL,
  `dang_lam` bit(1) DEFAULT b'1',
  `hinh_anh` varchar(100) DEFAULT NULL,
  `vai_tro_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_cmnd_UNIQUE` (`so_cmnd`),
  KEY `FK_NGUOIDUNG_VAITRO_idx` (`vai_tro_id`),
  CONSTRAINT `FK_NGUOIDUNG_VAITRO` FOREIGN KEY (`vai_tro_id`) REFERENCES `vai_tro` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung` VALUES ('NV00001','Trần Vĩ Khang','147852','21457878','01214577','khang@gmail.com','CMT9','2018-11-15',_binary '',_binary '',NULL,'NV'),('TR00001','Võ Thành Tài','123456','147852369','01125454','tai@gmail.com','CMT8','2018-11-14',_binary '',_binary '',NULL,'TR');
/*!40000 ALTER TABLE `nguoi_dung` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `nguoi_dung_BEFORE_INSERT` BEFORE INSERT ON `nguoi_dung` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 5;
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM nguoi_dung WHERE vai_tro_id LIKE CONCAT(new.vai_tro_id, '%')  ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(new.vai_tro_id, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phim` (
  `id` varchar(15) NOT NULL,
  `ten` varchar(100) NOT NULL,
  `thoi_luong` int(10) unsigned NOT NULL,
  `gioi_han_tuoi` int(10) unsigned NOT NULL,
  `ngay_cong_chieu` date NOT NULL,
  `ngon_ngu` varchar(20) NOT NULL,
  `dien_vien` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quoc_gia` varchar(45) NOT NULL,
  `nha_san_xuat` varchar(45) NOT NULL,
  `tom_tat` varchar(1000) DEFAULT NULL,
  `trang_thai` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `da_xoa` bit(1) DEFAULT b'0',
  `hinh_anh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loai_phim_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PHIM_LOAIPHIM_idx` (`loai_phim_id`),
  CONSTRAINT `FK_PHIM_LOAIPHIM` FOREIGN KEY (`loai_phim_id`) REFERENCES `loai_phim` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `phim_BEFORE_INSERT` BEFORE INSERT ON `phim` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 5;

	-- cai dat tien to
    SET @str := 'PH';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM phim ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `phong_chieu`
--

DROP TABLE IF EXISTS `phong_chieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phong_chieu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `so_luong_day` int(10) unsigned NOT NULL,
  `so_luong_cot` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_chieu`
--

LOCK TABLES `phong_chieu` WRITE;
/*!40000 ALTER TABLE `phong_chieu` DISABLE KEYS */;
INSERT INTO `phong_chieu` VALUES (1,12,18),(2,10,16),(3,10,14),(4,12,16);
/*!40000 ALTER TABLE `phong_chieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suat_chieu`
--

DROP TABLE IF EXISTS `suat_chieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `suat_chieu` (
  `id` varchar(15) NOT NULL,
  `gio_bat_dau` timestamp NOT NULL,
  `gio_ket_thuc` timestamp NOT NULL,
  `ngay_chieu` date NOT NULL,
  `phim_id` varchar(15) NOT NULL,
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `dinh_dang_phim_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SUATCHIEU_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_SUATCHIEU_DINHDANGPHIM_idx` (`dinh_dang_phim_id`),
  KEY `FK_SUATCHIEU_PHIM_idx` (`phim_id`),
  CONSTRAINT `FK_SUATCHIEU_DINHDANGPHIM` FOREIGN KEY (`dinh_dang_phim_id`) REFERENCES `dinh_dang_phim` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SUATCHIEU_PHIM` FOREIGN KEY (`phim_id`) REFERENCES `phim` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SUATCHIEU_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suat_chieu`
--

LOCK TABLES `suat_chieu` WRITE;
/*!40000 ALTER TABLE `suat_chieu` DISABLE KEYS */;
/*!40000 ALTER TABLE `suat_chieu` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `suat_chieu_BEFORE_INSERT` BEFORE INSERT ON `suat_chieu` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 8;

	-- cai dat tien to
    SET @str := 'SC';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM suat_chieu ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `vai_tro`
--

DROP TABLE IF EXISTS `vai_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vai_tro` (
  `id` varchar(2) NOT NULL,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vai_tro`
--

LOCK TABLES `vai_tro` WRITE;
/*!40000 ALTER TABLE `vai_tro` DISABLE KEYS */;
INSERT INTO `vai_tro` VALUES ('NV','Nhân viên bán hàng'),('QL','Nhân viên quản lý'),('TR','Quản lý rạp');
/*!40000 ALTER TABLE `vai_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve_ban`
--

DROP TABLE IF EXISTS `ve_ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ve_ban` (
  `id` varchar(15) NOT NULL,
  `ngay_ban` date NOT NULL,
  `tong_tien` int(10) unsigned NOT NULL,
  `suat_chieu_id` varchar(15) NOT NULL,
  `gia_ve_id` int(10) unsigned NOT NULL,
  `ghe_id` int(10) unsigned NOT NULL,
  `ve_dat_id` varchar(15) DEFAULT NULL,
  `nhan_vien_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VEBAN_GIAVE_idx` (`gia_ve_id`),
  KEY `FK_VEBAN_GHE_idx` (`ghe_id`),
  KEY `FK_VEBAN_NGUOIDUNG_idx` (`nhan_vien_id`),
  KEY `FK_VEBAN_SUATCHIEU_idx` (`suat_chieu_id`),
  KEY `FK_VEBAN_VEDAT` (`ve_dat_id`),
  CONSTRAINT `FK_VEBAN_GHENGOI` FOREIGN KEY (`ghe_id`) REFERENCES `ghe_ngoi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_GIAVE` FOREIGN KEY (`gia_ve_id`) REFERENCES `gia_ve` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_SUATCHIEU` FOREIGN KEY (`suat_chieu_id`) REFERENCES `suat_chieu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_VEDAT` FOREIGN KEY (`ve_dat_id`) REFERENCES `ve_dat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_ban`
--

LOCK TABLES `ve_ban` WRITE;
/*!40000 ALTER TABLE `ve_ban` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_ban` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ve_ban_BEFORE_INSERT` BEFORE INSERT ON `ve_ban` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 10;

	-- cai dat tien to
    SET @str := 'VB';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM ve_ban ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ve_dat`
--

DROP TABLE IF EXISTS `ve_dat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ve_dat` (
  `id` varchar(15) NOT NULL,
  `khach_hang_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VEDAT_KHACHANG_idx` (`khach_hang_id`),
  CONSTRAINT `FK_VEDAT_KHACHANG` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_dat`
--

LOCK TABLES `ve_dat` WRITE;
/*!40000 ALTER TABLE `ve_dat` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_dat` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ve_dat_BEFORE_INSERT` BEFORE INSERT ON `ve_dat` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 8;

	-- cai dat tien to
    SET @str := 'VD';
    
    -- lay id lon nhat trong bang
    SET @prevId := (SELECT id FROM ve_dat ORDER BY id DESC LIMIT 1);
	
    -- neu bang chua co du lieu, thi lay la 000000000
    if @prevId IS NULL then
		SET @prevId := LPAD(0, @numLenght, '0');
	END IF;
    
    -- lay phan so tu chuoi id
    SET @num := RIGHT(@prevId, @numLenght);
    
    -- tang phan so len 1 don vi
	SET @num := CAST(@num AS UNSIGNED) + 1;

	-- them so 0 vao truoc num cho du n ky tu
    SET @num := LPAD(@num, @numLenght, '0');
    
    -- cap nhat lai id moi
	SET new.id := CONCAT(@str, @num);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'du_an_1'
--

--
-- Dumping routines for database 'du_an_1'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuDoAnTheoNam` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuDoAnTheoNam`( yearValue INT )
BEGIN
	SELECT
		do_an.ten,
		loai_do_an.ten AS 'loai do an',
		hoa_don.ngay_ban,
		sum( so_luong ) AS 'So_Luong',
		sum( tong_tien ) AS 'Tong_tien' 
	FROM
		hoa_don_chi_tiet
		INNER JOIN hoa_don ON hoa_don_chi_tiet.hoa_don_id = hoa_don.id
		INNER JOIN do_an_chi_tiet ON hoa_don_chi_tiet.do_an_chi_tiet_id = do_an_chi_tiet.id
		INNER JOIN do_an ON do_an_chi_tiet.do_an_id = do_an.id
		INNER JOIN loai_do_an ON do_an.loai_do_an_id = loai_do_an.id 
	WHERE
		YEAR ( hoa_don.ngay_ban ) = yearValue 
	GROUP BY
		do_an.ten,
		loai_do_an.ten;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuDoAnTheoNgay` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuDoAnTheoNgay`( dateValue date )
BEGIN
	SELECT
		do_an.ten,
		loai_do_an.ten AS 'loai do an',
		hoa_don.ngay_ban,
		sum( so_luong ) AS 'So_Luong',
		sum( tong_tien ) AS 'Tong_tien' 
	FROM
		hoa_don_chi_tiet
		INNER JOIN hoa_don ON hoa_don_chi_tiet.hoa_don_id = hoa_don.id
		INNER JOIN do_an_chi_tiet ON hoa_don_chi_tiet.do_an_chi_tiet_id = do_an_chi_tiet.id
		INNER JOIN do_an ON do_an_chi_tiet.do_an_id = do_an.id
		INNER JOIN loai_do_an ON do_an.loai_do_an_id = loai_do_an.id 
	WHERE
		hoa_don.ngay_ban = dateValue 
	GROUP BY
		do_an.ten,
		loai_do_an.ten;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuDoAnTheoThang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuDoAnTheoThang`( monthValue INT, yearValue INT )
BEGIN
	SELECT
		do_an.ten,
		loai_do_an.ten AS 'loai do an',
		hoa_don.ngay_ban,
		sum( so_luong ) AS 'So_Luong',
		sum( tong_tien ) AS 'Tong_tien' 
	FROM
		hoa_don_chi_tiet
		INNER JOIN hoa_don ON hoa_don_chi_tiet.hoa_don_id = hoa_don.id
		INNER JOIN do_an_chi_tiet ON hoa_don_chi_tiet.do_an_chi_tiet_id = do_an_chi_tiet.id
		INNER JOIN do_an ON do_an_chi_tiet.do_an_id = do_an.id
		INNER JOIN loai_do_an ON do_an.loai_do_an_id = loai_do_an.id 
	WHERE
		MONTH ( hoa_don.ngay_ban ) = monthValue 
		AND YEAR ( hoa_don.ngay_ban ) = yearValue 
	GROUP BY
		do_an.ten,
		loai_do_an.ten;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuPhimTheoNam` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuPhimTheoNam`( yearValue INT )
BEGIN
	SELECT
		p.ten ten_phim,
		p.ngay_cong_chieu ngay_cong_chieu,
		COUNT( * ) so_luong_ve_ban,
		COALESCE ( SUM( vb.tong_tien ), 0 ) doanh_thu 
	FROM
		phim p
		JOIN suat_chieu sc ON p.id = sc.phim_id
		JOIN ve_ban vb ON vb.suat_chieu_id = sc.id 
	WHERE
		YEAR ( sc.ngay_chieu ) = yearValue 
	GROUP BY
		p.ten,
		p.ngay_cong_chieu;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuPhimTheoNgay` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuPhimTheoNgay`( dateValue date )
BEGIN
	SELECT
		p.ten ten_phim,
		p.ngay_cong_chieu ngay_cong_chieu,
		COUNT( * ) so_luong_ve_ban,
		COALESCE ( SUM( vb.tong_tien ), 0 ) doanh_thu 
	FROM
		phim p
		JOIN suat_chieu sc ON p.id = sc.phim_id
		JOIN ve_ban vb ON vb.suat_chieu_id = sc.id 
	WHERE
		sc.ngay_chieu = dateValue 
	GROUP BY
		p.ten,
		p.ngay_cong_chieu;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_DoanhThuPhimTheoThang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_DoanhThuPhimTheoThang`( monthValue INT, yearValue INT )
BEGIN
	SELECT
		p.ten ten_phim,
		p.ngay_cong_chieu ngay_cong_chieu,
		COUNT( * ) so_luong_ve_ban,
		COALESCE ( SUM( vb.tong_tien ), 0 ) doanh_thu 
	FROM
		phim p
		JOIN suat_chieu sc ON p.id = sc.phim_id
		JOIN ve_ban vb ON vb.suat_chieu_id = sc.id 
	WHERE
		MONTH ( sc.ngay_chieu ) = monthValue 
		AND YEAR ( sc.ngay_chieu ) = yearValue 
	GROUP BY
		p.ten,
		p.ngay_cong_chieu;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_TongDoanhThuTheoNam` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_TongDoanhThuTheoNam`( yearValue INT )
BEGIN
	SELECT
		thong_ke.thang_ban thang_ban,
		COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) do_an,
		COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) ve_ban,
		(
			COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) + COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) 
		) tong_doanh_thu 
	FROM
		(
		SELECT
			0 tien_ve_ban,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tien_do_an,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tong_doanh_thu,
			MONTH ( hd.ngay_ban ) thang_ban 
		FROM
			hoa_don hd
			JOIN hoa_don_chi_tiet hdct ON hdct.hoa_don_id = hd.id 
		WHERE
			YEAR ( hd.ngay_ban ) = yearValue 
		GROUP BY
			hd.ngay_ban UNION
		SELECT COALESCE
			( SUM( vb.tong_tien ), 0 ) tien_ve_ban,
			0 tien_do_an,
			COALESCE ( SUM( vb.tong_tien ), 0 ) tong_doanh_thu,
			MONTH ( vb.ngay_ban ) thang_ban 
		FROM
			ve_ban vb 
		WHERE
			YEAR ( vb.ngay_ban ) = yearValue 
		GROUP BY
			vb.ngay_ban 
		) AS thong_ke 
	GROUP BY
		thong_ke.thang_ban;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_TongDoanhThuTheoNgay` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_TongDoanhThuTheoNgay`( beginDate date, endDate date )
BEGIN
	SELECT
		thong_ke.ngay_ban ngay_ban,
		COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) do_an,
		COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) ve_ban,
		(
			COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) + COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) 
		) tong_doanh_thu 
	FROM
		(
		SELECT
			0 tien_ve_ban,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tien_do_an,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tong_doanh_thu,
			hd.ngay_ban ngay_ban 
		FROM
			hoa_don hd
			JOIN hoa_don_chi_tiet hdct ON hdct.hoa_don_id = hd.id 
		WHERE
			( hd.ngay_ban BETWEEN beginDate AND endDate ) 
		GROUP BY
			hd.ngay_ban UNION
		SELECT COALESCE
			( SUM( vb.tong_tien ), 0 ) tien_ve_ban,
			0 tien_do_an,
			COALESCE ( SUM( vb.tong_tien ), 0 ) tong_doanh_thu,
			vb.ngay_ban ngay_ban 
		FROM
			ve_ban vb 
		WHERE
			( vb.ngay_ban BETWEEN beginDate AND endDate ) 
		GROUP BY
			vb.ngay_ban 
		) AS thong_ke 
	GROUP BY
		thong_ke.ngay_ban;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_TongDoanhThuTheoThang` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_TongDoanhThuTheoThang`( monthValue INT, yearValue INT )
BEGIN
	SELECT
		thong_ke.ngay_ban ngay_ban,
		COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) do_an,
		COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) ve_ban,
		(
			COALESCE ( SUM( thong_ke.tien_ve_ban ), 0 ) + COALESCE ( SUM( thong_ke.tien_do_an ), 0 ) 
		) tong_doanh_thu 
	FROM
		(
		SELECT
			0 tien_ve_ban,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tien_do_an,
			COALESCE ( SUM( hdct.tong_tien ), 0 ) tong_doanh_thu,
			hd.ngay_ban ngay_ban 
		FROM
			hoa_don hd
			JOIN hoa_don_chi_tiet hdct ON hdct.hoa_don_id = hd.id 
		WHERE
			YEAR ( hd.ngay_ban ) = yearValue 
			AND MONTH ( hd.ngay_ban ) = monthValue 
		GROUP BY
			hd.ngay_ban UNION
		SELECT COALESCE
			( SUM( vb.tong_tien ), 0 ) tien_ve_ban,
			0 tien_do_an,
			COALESCE ( SUM( vb.tong_tien ), 0 ) tong_doanh_thu,
			vb.ngay_ban ngay_ban 
		FROM
			ve_ban vb 
		WHERE
			YEAR ( vb.ngay_ban ) = yearValue 
			AND MONTH ( vb.ngay_ban ) = monthValue 
		GROUP BY
			vb.ngay_ban 
		) AS thong_ke 
	GROUP BY
		thong_ke.ngay_ban;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-27 16:42:35
