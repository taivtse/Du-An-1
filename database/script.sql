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
-- Dumping data for table `dinh_dang_phim`
--

LOCK TABLES `dinh_dang_phim` WRITE;
/*!40000 ALTER TABLE `dinh_dang_phim` DISABLE KEYS */;
INSERT INTO `dinh_dang_phim` VALUES ('2D','Định dạng 2D',0),('3D','Định dạng 3D',20000),('4D','Định dạng 4D',35000),('IMAX','Định dạng IMAX',50000);
/*!40000 ALTER TABLE `dinh_dang_phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `do_an`
--

LOCK TABLES `do_an` WRITE;
/*!40000 ALTER TABLE `do_an` DISABLE KEYS */;
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
-- Dumping data for table `do_an_chi_tiet`
--

LOCK TABLES `do_an_chi_tiet` WRITE;
/*!40000 ALTER TABLE `do_an_chi_tiet` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_an_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ghe_ngoi`
--

LOCK TABLES `ghe_ngoi` WRITE;
/*!40000 ALTER TABLE `ghe_ngoi` DISABLE KEYS */;
INSERT INTO `ghe_ngoi` VALUES (1,'A',1,1,'GT'),(2,'A',2,1,'GT'),(3,'A',3,1,'GT'),(4,'A',4,1,'GT'),(5,'A',5,1,'GT'),(6,'A',6,1,'GT'),(7,'A',7,1,'GT'),(8,'A',8,1,'GT'),(9,'A',9,1,'GT'),(10,'A',10,1,'GT'),(11,'A',11,1,'GT'),(12,'A',12,1,'GT'),(13,'A',13,1,'GT'),(14,'A',14,1,'GT'),(15,'A',15,1,'GT'),(16,'A',16,1,'GT'),(17,'A',17,1,'GT'),(18,'A',18,1,'GT'),(19,'B',1,1,'GT'),(20,'B',2,1,'GT'),(21,'B',3,1,'GT'),(22,'B',4,1,'GT'),(23,'B',5,1,'GT'),(24,'B',6,1,'GT'),(25,'B',7,1,'GT'),(26,'B',8,1,'GT'),(27,'B',9,1,'GT'),(28,'B',10,1,'GT'),(29,'B',11,1,'GT'),(30,'B',12,1,'GT'),(31,'B',13,1,'GT'),(32,'B',14,1,'GT'),(33,'B',15,1,'GT'),(34,'B',16,1,'GT'),(35,'B',17,1,'GT'),(36,'B',18,1,'GT'),(37,'C',1,1,'GT'),(38,'C',2,1,'GT'),(39,'C',3,1,'GT'),(40,'C',4,1,'GT'),(41,'C',5,1,'GT'),(42,'C',6,1,'GT'),(43,'C',7,1,'GT'),(44,'C',8,1,'GT'),(45,'C',9,1,'GT'),(46,'C',10,1,'GT'),(47,'C',11,1,'GT'),(48,'C',12,1,'GT'),(49,'C',13,1,'GT'),(50,'C',14,1,'GT'),(51,'C',15,1,'GT'),(52,'C',16,1,'GT'),(53,'C',17,1,'GT'),(54,'C',18,1,'GT'),(55,'D',1,1,'GT'),(56,'D',2,1,'GT'),(57,'D',3,1,'GT'),(58,'D',4,1,'GT'),(59,'D',5,1,'GT'),(60,'D',6,1,'GT'),(61,'D',7,1,'GT'),(62,'D',8,1,'GT'),(63,'D',9,1,'GT'),(64,'D',10,1,'GT'),(65,'D',11,1,'GT'),(66,'D',12,1,'GT'),(67,'D',13,1,'GT'),(68,'D',14,1,'GT'),(69,'D',15,1,'GT'),(70,'D',16,1,'GT'),(71,'D',17,1,'GT'),(72,'D',18,1,'GT'),(73,'E',1,1,'GT'),(74,'E',2,1,'GT'),(75,'E',3,1,'GT'),(76,'E',4,1,'GT'),(77,'E',5,1,'GT'),(78,'E',6,1,'GT'),(79,'E',7,1,'GT'),(80,'E',8,1,'GT'),(81,'E',9,1,'GT'),(82,'E',10,1,'GT'),(83,'E',11,1,'GT'),(84,'E',12,1,'GT'),(85,'E',13,1,'GT'),(86,'E',14,1,'GT'),(87,'E',15,1,'GT'),(88,'E',16,1,'GT'),(89,'E',17,1,'GT'),(90,'E',18,1,'GT'),(91,'F',1,1,'GT'),(92,'F',2,1,'GT'),(93,'F',3,1,'GT'),(94,'F',4,1,'GT'),(95,'F',5,1,'GT'),(96,'F',6,1,'GT'),(97,'F',7,1,'GT'),(98,'F',8,1,'GT'),(99,'F',9,1,'GT'),(100,'F',10,1,'GT'),(101,'F',11,1,'GT'),(102,'F',12,1,'GT'),(103,'F',13,1,'GT'),(104,'F',14,1,'GT'),(105,'F',15,1,'GT'),(106,'F',16,1,'GT'),(107,'F',17,1,'GT'),(108,'F',18,1,'GT'),(109,'G',1,1,'GT'),(110,'G',2,1,'GT'),(111,'G',3,1,'GT'),(112,'G',4,1,'GT'),(113,'G',5,1,'GT'),(114,'G',6,1,'GT'),(115,'G',7,1,'GT'),(116,'G',8,1,'GT'),(117,'G',9,1,'GT'),(118,'G',10,1,'GT'),(119,'G',11,1,'GT'),(120,'G',12,1,'GT'),(121,'G',13,1,'GT'),(122,'G',14,1,'GT'),(123,'G',15,1,'GT'),(124,'G',16,1,'GT'),(125,'G',17,1,'GT'),(126,'G',18,1,'GT'),(127,'H',1,1,'GT'),(128,'H',2,1,'GT'),(129,'H',3,1,'GT'),(130,'H',4,1,'GT'),(131,'H',5,1,'GT'),(132,'H',6,1,'GT'),(133,'H',7,1,'GT'),(134,'H',8,1,'GT'),(135,'H',9,1,'GT'),(136,'H',10,1,'GT'),(137,'H',11,1,'GT'),(138,'H',12,1,'GT'),(139,'H',13,1,'GT'),(140,'H',14,1,'GT'),(141,'H',15,1,'GT'),(142,'H',16,1,'GT'),(143,'H',17,1,'GT'),(144,'H',18,1,'GT'),(145,'I',1,1,'GT'),(146,'I',2,1,'GT'),(147,'I',3,1,'GT'),(148,'I',4,1,'GT'),(149,'I',5,1,'GT'),(150,'I',6,1,'GT'),(151,'I',7,1,'GT'),(152,'I',8,1,'GT'),(153,'I',9,1,'GT'),(154,'I',10,1,'GT'),(155,'I',11,1,'GT'),(156,'I',12,1,'GT'),(157,'I',13,1,'GT'),(158,'I',14,1,'GT'),(159,'I',15,1,'GT'),(160,'I',16,1,'GT'),(161,'I',17,1,'GT'),(162,'I',18,1,'GT'),(163,'J',1,1,'GT'),(164,'J',2,1,'GT'),(165,'J',3,1,'GT'),(166,'J',4,1,'GT'),(167,'J',5,1,'GT'),(168,'J',6,1,'GT'),(169,'J',7,1,'GT'),(170,'J',8,1,'GT'),(171,'J',9,1,'GT'),(172,'J',10,1,'GT'),(173,'J',11,1,'GT'),(174,'J',12,1,'GT'),(175,'J',13,1,'GT'),(176,'J',14,1,'GT'),(177,'J',15,1,'GT'),(178,'J',16,1,'GT'),(179,'J',17,1,'GT'),(180,'J',18,1,'GT'),(181,'K',1,1,'GT'),(182,'K',2,1,'GT'),(183,'K',3,1,'GT'),(184,'K',4,1,'GT'),(185,'K',5,1,'GT'),(186,'K',6,1,'GT'),(187,'K',7,1,'GT'),(188,'K',8,1,'GT'),(189,'K',9,1,'GT'),(190,'K',10,1,'GT'),(191,'K',11,1,'GT'),(192,'K',12,1,'GT'),(193,'K',13,1,'GT'),(194,'K',14,1,'GT'),(195,'K',15,1,'GT'),(196,'K',16,1,'GT'),(197,'K',17,1,'GT'),(198,'K',18,1,'GT'),(199,'L',1,1,'GT'),(200,'L',2,1,'GT'),(201,'L',3,1,'GT'),(202,'L',4,1,'GT'),(203,'L',5,1,'GT'),(204,'L',6,1,'GT'),(205,'L',7,1,'GT'),(206,'L',8,1,'GT'),(207,'L',9,1,'GT'),(208,'L',10,1,'GT'),(209,'L',11,1,'GT'),(210,'L',12,1,'GT'),(211,'L',13,1,'GT'),(212,'L',14,1,'GT'),(213,'L',15,1,'GT'),(214,'L',16,1,'GT'),(215,'L',17,1,'GT'),(216,'L',18,1,'GT'),(217,'A',1,2,'GT'),(218,'A',2,2,'GT'),(219,'A',3,2,'GT'),(220,'A',4,2,'GT'),(221,'A',5,2,'GT'),(222,'A',6,2,'GT'),(223,'A',7,2,'GT'),(224,'A',8,2,'GT'),(225,'A',9,2,'GT'),(226,'A',10,2,'GT'),(227,'A',11,2,'GT'),(228,'A',12,2,'GT'),(229,'A',13,2,'GT'),(230,'A',14,2,'GT'),(231,'A',15,2,'GT'),(232,'A',16,2,'GT'),(233,'B',1,2,'GT'),(234,'B',2,2,'GT'),(235,'B',3,2,'GT'),(236,'B',4,2,'GT'),(237,'B',5,2,'GT'),(238,'B',6,2,'GT'),(239,'B',7,2,'GT'),(240,'B',8,2,'GT'),(241,'B',9,2,'GT'),(242,'B',10,2,'GT'),(243,'B',11,2,'GT'),(244,'B',12,2,'GT'),(245,'B',13,2,'GT'),(246,'B',14,2,'GT'),(247,'B',15,2,'GT'),(248,'B',16,2,'GT'),(249,'C',1,2,'GT'),(250,'C',2,2,'GT'),(251,'C',3,2,'GT'),(252,'C',4,2,'GT'),(253,'C',5,2,'GT'),(254,'C',6,2,'GT'),(255,'C',7,2,'GT'),(256,'C',8,2,'GT'),(257,'C',9,2,'GT'),(258,'C',10,2,'GT'),(259,'C',11,2,'GT'),(260,'C',12,2,'GT'),(261,'C',13,2,'GT'),(262,'C',14,2,'GT'),(263,'C',15,2,'GT'),(264,'C',16,2,'GT'),(265,'D',1,2,'GT'),(266,'D',2,2,'GT'),(267,'D',3,2,'GT'),(268,'D',4,2,'GT'),(269,'D',5,2,'GT'),(270,'D',6,2,'GT'),(271,'D',7,2,'GT'),(272,'D',8,2,'GT'),(273,'D',9,2,'GT'),(274,'D',10,2,'GT'),(275,'D',11,2,'GT'),(276,'D',12,2,'GT'),(277,'D',13,2,'GT'),(278,'D',14,2,'GT'),(279,'D',15,2,'GT'),(280,'D',16,2,'GT'),(281,'E',1,2,'GT'),(282,'E',2,2,'GT'),(283,'E',3,2,'GT'),(284,'E',4,2,'GT'),(285,'E',5,2,'GT'),(286,'E',6,2,'GT'),(287,'E',7,2,'GT'),(288,'E',8,2,'GT'),(289,'E',9,2,'GT'),(290,'E',10,2,'GT'),(291,'E',11,2,'GT'),(292,'E',12,2,'GT'),(293,'E',13,2,'GT'),(294,'E',14,2,'GT'),(295,'E',15,2,'GT'),(296,'E',16,2,'GT'),(297,'F',1,2,'GT'),(298,'F',2,2,'GT'),(299,'F',3,2,'GT'),(300,'F',4,2,'GT'),(301,'F',5,2,'GT'),(302,'F',6,2,'GT'),(303,'F',7,2,'GT'),(304,'F',8,2,'GT'),(305,'F',9,2,'GT'),(306,'F',10,2,'GT'),(307,'F',11,2,'GT'),(308,'F',12,2,'GT'),(309,'F',13,2,'GT'),(310,'F',14,2,'GT'),(311,'F',15,2,'GT'),(312,'F',16,2,'GT'),(313,'G',1,2,'GT'),(314,'G',2,2,'GT'),(315,'G',3,2,'GT'),(316,'G',4,2,'GT'),(317,'G',5,2,'GT'),(318,'G',6,2,'GT'),(319,'G',7,2,'GT'),(320,'G',8,2,'GT'),(321,'G',9,2,'GT'),(322,'G',10,2,'GT'),(323,'G',11,2,'GT'),(324,'G',12,2,'GT'),(325,'G',13,2,'GT'),(326,'G',14,2,'GT'),(327,'G',15,2,'GT'),(328,'G',16,2,'GT'),(329,'H',1,2,'GT'),(330,'H',2,2,'GT'),(331,'H',3,2,'GT'),(332,'H',4,2,'GT'),(333,'H',5,2,'GT'),(334,'H',6,2,'GT'),(335,'H',7,2,'GT'),(336,'H',8,2,'GT'),(337,'H',9,2,'GT'),(338,'H',10,2,'GT'),(339,'H',11,2,'GT'),(340,'H',12,2,'GT'),(341,'H',13,2,'GT'),(342,'H',14,2,'GT'),(343,'H',15,2,'GT'),(344,'H',16,2,'GT'),(345,'I',1,2,'GT'),(346,'I',2,2,'GT'),(347,'I',3,2,'GT'),(348,'I',4,2,'GT'),(349,'I',5,2,'GT'),(350,'I',6,2,'GT'),(351,'I',7,2,'GT'),(352,'I',8,2,'GT'),(353,'I',9,2,'GT'),(354,'I',10,2,'GT'),(355,'I',11,2,'GT'),(356,'I',12,2,'GT'),(357,'I',13,2,'GT'),(358,'I',14,2,'GT'),(359,'I',15,2,'GT'),(360,'I',16,2,'GT'),(361,'J',1,2,'GT'),(362,'J',2,2,'GT'),(363,'J',3,2,'GT'),(364,'J',4,2,'GT'),(365,'J',5,2,'GT'),(366,'J',6,2,'GT'),(367,'J',7,2,'GT'),(368,'J',8,2,'GT'),(369,'J',9,2,'GT'),(370,'J',10,2,'GT'),(371,'J',11,2,'GT'),(372,'J',12,2,'GT'),(373,'J',13,2,'GT'),(374,'J',14,2,'GT'),(375,'J',15,2,'GT'),(376,'J',16,2,'GT');
/*!40000 ALTER TABLE `ghe_ngoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gia_ve`
--

LOCK TABLES `gia_ve` WRITE;
/*!40000 ALTER TABLE `gia_ve` DISABLE KEYS */;
INSERT INTO `gia_ve` VALUES (1,'Trẻ em',30000),(2,'Học sinh, sinh viên',45000),(3,'Người lớn',60000);
/*!40000 ALTER TABLE `gia_ve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
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
-- Dumping data for table `hoa_don_chi_tiet`
--

LOCK TABLES `hoa_don_chi_tiet` WRITE;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
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
-- Dumping data for table `kich_co_do_an`
--

LOCK TABLES `kich_co_do_an` WRITE;
/*!40000 ALTER TABLE `kich_co_do_an` DISABLE KEYS */;
INSERT INTO `kich_co_do_an` VALUES ('L','Cỡ lớn'),('M','Cỡ vừa'),('S','Cỡ nhỏ');
/*!40000 ALTER TABLE `kich_co_do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_do_an`
--

LOCK TABLES `loai_do_an` WRITE;
/*!40000 ALTER TABLE `loai_do_an` DISABLE KEYS */;
INSERT INTO `loai_do_an` VALUES ('BK','Bánh kẹo'),('DA','Đồ ăn nhanh'),('NU','Nước uống');
/*!40000 ALTER TABLE `loai_do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_ghe`
--

LOCK TABLES `loai_ghe` WRITE;
/*!40000 ALTER TABLE `loai_ghe` DISABLE KEYS */;
INSERT INTO `loai_ghe` VALUES ('GD','Ghế đôi',20000),('GT','Ghế thường',0),('GV','Ghế đặc biệt',35000);
/*!40000 ALTER TABLE `loai_ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `loai_phim`
--

LOCK TABLES `loai_phim` WRITE;
/*!40000 ALTER TABLE `loai_phim` DISABLE KEYS */;
INSERT INTO `loai_phim` VALUES (1,'Phim tình cảm'),(2,'Phim hài hước'),(3,'Phim hành động'),(4,'Phim hoạt hình'),(5,'Phim tài liệu'),(6,'Phim võ thuật'),(7,'Phim viễn tưởng'),(8,'Phim bí ẩn, siêu nhiên'),(9,'Phim tài liệu'),(10,'Phim kinh dị');
/*!40000 ALTER TABLE `loai_phim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
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
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES ('PH00001','Chàng trai năm ấy',160,0,'2018-11-09','Tiếng Việt','Sơn Tùng','Việt Nam','Hãng phim A','khong co gi','Đang chiếu',_binary '\0',NULL,1);
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
-- Dumping data for table `phong_chieu`
--

LOCK TABLES `phong_chieu` WRITE;
/*!40000 ALTER TABLE `phong_chieu` DISABLE KEYS */;
INSERT INTO `phong_chieu` VALUES (1,12,18),(2,10,16);
/*!40000 ALTER TABLE `phong_chieu` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `vai_tro`
--

LOCK TABLES `vai_tro` WRITE;
/*!40000 ALTER TABLE `vai_tro` DISABLE KEYS */;
INSERT INTO `vai_tro` VALUES ('NV','Nhân viên bán hàng'),('QL','Nhân viên quản lý'),('TR','Quản lý rạp');
/*!40000 ALTER TABLE `vai_tro` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ve_ban_BEFORE_INSERT` BEFORE INSERT ON `ve_ban` FOR EACH ROW BEGIN
	-- do dai cua chuoi so
    SET @numLenght := 8;

	-- cai dat tien to
    SET @str := 'VE';
    
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
-- Dumping data for table `ve_dat`
--

LOCK TABLES `ve_dat` WRITE;
/*!40000 ALTER TABLE `ve_dat` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_dat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'du_an_1'
--

--
-- Dumping routines for database 'du_an_1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-14 16:03:17
