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
INSERT INTO `dinh_dang_phim` VALUES ('2D','Định dạng 2D',0),('3D','Định dạng 3D',15000),('4D','Định dạng 4D',25000),('IMAX','Định đạng IMAX',40000);
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
  CONSTRAINT `FK_DOAN_LOAIDOAN` FOREIGN KEY (`loai_do_an_id`) REFERENCES `loai_do_an` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an`
--

LOCK TABLES `do_an` WRITE;
/*!40000 ALTER TABLE `do_an` DISABLE KEYS */;
INSERT INTO `do_an` VALUES ('DA001','Bắp rang bơ',_binary '','DA'),('DA002','Đùi gà',_binary '','DA'),('DA003','aasdadas',_binary '','DA'),('DA004','asd',_binary '\0','DA'),('DA005','hahaa',_binary '\0','DA'),('NU003','Coca',_binary '','NU'),('NU004','Pepsi',_binary '','NU'),('NU005','haha',_binary '\0','NU');
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
    if @prevId = NULL then
		SET @prevId := '00000';
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
  CONSTRAINT `FK_DOANCHITIET_DOAN` FOREIGN KEY (`do_an_id`) REFERENCES `do_an` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_DOANCHITIET_KICHCODOAN` FOREIGN KEY (`kich_co_do_an_id`) REFERENCES `kich_co_do_an` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an_chi_tiet`
--

LOCK TABLES `do_an_chi_tiet` WRITE;
/*!40000 ALTER TABLE `do_an_chi_tiet` DISABLE KEYS */;
INSERT INTO `do_an_chi_tiet` VALUES (1,20000,_binary '','DA001','S'),(2,30000,_binary '','DA001','M'),(3,40000,_binary '','DA001','L'),(4,89000,_binary '','DA002','S'),(5,139000,_binary '','DA002','M'),(6,169000,_binary '','DA002','L'),(7,10000,_binary '','NU003','S'),(8,150000,_binary '\0','NU003','M'),(9,25000,_binary '','NU003','L'),(10,10000,_binary '','NU004','S'),(11,15000,_binary '','NU004','M'),(12,25000,_binary '','NU004','L'),(13,20000,_binary '\0','NU005','M'),(14,10000,_binary '','NU005','S'),(15,100000,_binary '\0','NU005','L'),(16,222,_binary '','DA004','L'),(17,20000,_binary '','DA004','M');
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
  `da_chon` bit(1) NOT NULL,
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `loai_ghe_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GHE_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_GHE_LOAIGHE_idx` (`loai_ghe_id`),
  CONSTRAINT `FK_GHE_LOAIGHE` FOREIGN KEY (`loai_ghe_id`) REFERENCES `loai_ghe` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_GHE_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe_ngoi`
--

LOCK TABLES `ghe_ngoi` WRITE;
/*!40000 ALTER TABLE `ghe_ngoi` DISABLE KEYS */;
INSERT INTO `ghe_ngoi` VALUES (1,'A',1,_binary '\0',1,'GT'),(2,'A',2,_binary '\0',1,'GT'),(3,'B',1,_binary '\0',1,'GT'),(4,'B',2,_binary '\0',1,'GT'),(5,'A',1,_binary '\0',2,'GV'),(6,'A',2,_binary '\0',2,'GT'),(7,'A',3,_binary '\0',2,'GT'),(8,'A',4,_binary '\0',2,'GD'),(9,'B',1,_binary '\0',2,'GV'),(10,'B',2,_binary '\0',2,'GT'),(11,'B',3,_binary '\0',2,'GT'),(12,'B',4,_binary '\0',2,'GD');
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
INSERT INTO `gia_ve` VALUES (1,'Trẻ em',30000),(2,'Người lớn',55000),(3,'Học sinh, sinh viên',45000);
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
  CONSTRAINT `FK_HOADON_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES ('HD00000001','2018-04-04','AD00001'),('HD00000002','2018-04-04','EM00001');
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
    if @prevId = NULL then
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
  CONSTRAINT `FK_HOADONCHITIET_DOANCHITIET` FOREIGN KEY (`do_an_chi_tiet_id`) REFERENCES `do_an_chi_tiet` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_HOADONCHITIET_HOADON` FOREIGN KEY (`hoa_don_id`) REFERENCES `hoa_don` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don_chi_tiet`
--

LOCK TABLES `hoa_don_chi_tiet` WRITE;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` DISABLE KEYS */;
INSERT INTO `hoa_don_chi_tiet` VALUES (1,2,60000,2,'HD00000001'),(2,3,267000,4,'HD00000002');
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
INSERT INTO `khach_hang` VALUES ('KH00001','Trần Lê Thanh Thinh','1234','vB7CqMsk5S','0123456789','thinhtlt@gmail.com','HCM','2017-02-02','1997-02-02',_binary '\0'),('KH00002','Trần Vĩ Khang','12345','12345','0123456789','khangtv@gmail.com','HCM','2018-02-02','1999-02-02',_binary ''),('KH00003','vo thanh tai','ad','6N1kz7R62p','adsf','ads','adfads','2018-11-22','2018-11-08',_binary '');
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
    if @prevId = NULL then
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
INSERT INTO `loai_do_an` VALUES ('DA','Đồ ăn nhanh'),('NU','Đồ uống');
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
INSERT INTO `loai_ghe` VALUES ('GD','Ghế đôi',20000),('GT','Ghế thường',0),('GV','Ghế vip',30000);
/*!40000 ALTER TABLE `loai_ghe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_phim`
--

DROP TABLE IF EXISTS `loai_phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loai_phim` (
  `id` int(10) unsigned NOT NULL,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_phim`
--

LOCK TABLES `loai_phim` WRITE;
/*!40000 ALTER TABLE `loai_phim` DISABLE KEYS */;
INSERT INTO `loai_phim` VALUES (1,'Phim hành động'),(2,'Phim hài hước');
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
  `vai_tro_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_cmnd_UNIQUE` (`so_cmnd`),
  KEY `FK_NGUOIDUNG_VAITRO_idx` (`vai_tro_id`),
  CONSTRAINT `FK_NGUOIDUNG_VAITRO` FOREIGN KEY (`vai_tro_id`) REFERENCES `vai_tro` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung` VALUES ('AD00001','Võ Thành Tài','12345','456','0123456789','taivt@gmail.com','HCM','2017-01-01',_binary '',_binary '\0','AD'),('EM00001','Lưu Tuấn Cường','12345','123','0367428198','cuonglt@gmail.com','BTH','2018-01-01',_binary '',_binary '\0','EM'),('MA00001','Trần Light','12345','789','0123456789','tranlight@gmail.com','TN','2016-01-01',_binary '\0',_binary '\0','MA');
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
    if @prevId = NULL then
		SET @prevId := '00000';
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
  `dien_vien` varchar(100) DEFAULT NULL,
  `quoc_gia` varchar(45) NOT NULL,
  `nha_san_xuat` varchar(45) NOT NULL,
  `tom_tat` varchar(1000) DEFAULT NULL,
  `trang_thai` varchar(10) NOT NULL,
  `da_xoa` bit(1) DEFAULT b'0',
  `loai_phim_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PHIM_LOAIPHIM_idx` (`loai_phim_id`),
  CONSTRAINT `FK_PHIM_LOAIPHIM` FOREIGN KEY (`loai_phim_id`) REFERENCES `loai_phim` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
INSERT INTO `phim` VALUES ('1','Phim con chó',60,18,'2018-12-14','Tiếng Việt','Japan','Việt Nam','Hãng phim A','Hấp dẫn, lên máu','Đang chiếu',_binary '\0',1),('2','Phim con người',90,6,'2018-12-01','Vietnamese','Vietnam','VIETNAM','Viet.co.ltd','Bình thường','dangchieu',_binary '\0',2),('PH00000003','adsfa',31,12,'2018-02-02','asf','asdf','asdf','asdf','asd','adsf',_binary '\0',1),('PH00000004','sda',12,123,'2018-02-02','asd','sd','ads','adsf','adsf','asd',_binary '\0',2),('PH00000005','asd',10,10,'2018-11-01','Tiếng Việt','asdfad','Nga','Hãng phim A','asdfad','Đang chiếu',_binary '\0',2),('PH00006','ad',10,10,'2018-11-16','Tiếng Việt','asdf','Anh','Hãng phim A','asdfasd','Đang chiếu',_binary '\0',2),('PH00007','asd',10,10,'2018-11-16','Tiếng Việt','dasdfas','Việt Nam','Hãng phim A','asdfas','Đang chiếu',_binary '\0',1);
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
    if @prevId = NULL then
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_chieu`
--

LOCK TABLES `phong_chieu` WRITE;
/*!40000 ALTER TABLE `phong_chieu` DISABLE KEYS */;
INSERT INTO `phong_chieu` VALUES (1,10,18),(2,10,18);
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
  `gio_bat_dau` time NOT NULL,
  `gio_ket_thuc` time NOT NULL,
  `ngay_chieu` date NOT NULL,
  `phim_id` varchar(15) NOT NULL,
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `dinh_dang_phim_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SUATCHIEU_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_SUATCHIEU_DINHDANGPHIM_idx` (`dinh_dang_phim_id`),
  KEY `FK_SUATCHIEU_PHIM_idx` (`phim_id`),
  CONSTRAINT `FK_SUATCHIEU_DINHDANGPHIM` FOREIGN KEY (`dinh_dang_phim_id`) REFERENCES `dinh_dang_phim` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SUATCHIEU_PHIM` FOREIGN KEY (`phim_id`) REFERENCES `phim` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_SUATCHIEU_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suat_chieu`
--

LOCK TABLES `suat_chieu` WRITE;
/*!40000 ALTER TABLE `suat_chieu` DISABLE KEYS */;
INSERT INTO `suat_chieu` VALUES ('SC00000001','20:00:00','22:00:00','2018-11-11','1',1,'3D'),('SC00000002','09:00:00','11:00:00','2018-12-12','2',2,'IMAX'),('SC00000003','10:00:00','12:00:00','2018-02-02','1',2,'3D');
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
    if @prevId = NULL then
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
INSERT INTO `vai_tro` VALUES ('AD','Admin'),('EM','Nhân viên bán hàng'),('MA','Nhân viên quản lý');
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
  `nhan_vien_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VEBAN_GIAVE_idx` (`gia_ve_id`),
  KEY `FK_VEBAN_GHE_idx` (`ghe_id`),
  KEY `FK_VEBAN_NGUOIDUNG_idx` (`nhan_vien_id`),
  KEY `FK_VEBAN_SUATCHIEU_idx` (`suat_chieu_id`),
  CONSTRAINT `FK_VEBAN_GHE` FOREIGN KEY (`ghe_id`) REFERENCES `ghe_ngoi` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_GIAVE` FOREIGN KEY (`gia_ve_id`) REFERENCES `gia_ve` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_VEBAN_SUATCHIEU` FOREIGN KEY (`suat_chieu_id`) REFERENCES `suat_chieu` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_ban`
--

LOCK TABLES `ve_ban` WRITE;
/*!40000 ALTER TABLE `ve_ban` DISABLE KEYS */;
INSERT INTO `ve_ban` VALUES ('VE00000001','2018-11-11',1,'SC00000001',2,10,'AD00001'),('VE00000002','2018-12-12',1,'SC00000002',3,2,'MA00001'),('VE00000003','2018-02-02',1,'SC00000002',2,2,'MA00001');
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
    if @prevId = NULL then
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
  CONSTRAINT `FK_VEDAT_KHACHANG` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_VEDAT_VEBAN` FOREIGN KEY (`id`) REFERENCES `ve_ban` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_dat`
--

LOCK TABLES `ve_dat` WRITE;
/*!40000 ALTER TABLE `ve_dat` DISABLE KEYS */;
INSERT INTO `ve_dat` VALUES ('VE00000003','KH00001');
/*!40000 ALTER TABLE `ve_dat` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ve_dat_BEFORE_INSERT` BEFORE INSERT ON `ve_dat` FOR EACH ROW BEGIN
	
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-08 18:22:26
