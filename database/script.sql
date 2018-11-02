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
INSERT INTO `dinh_dang_phim` VALUES ('2D','Định dạng 2D',0),('3D','Định dạng 3D',10000),('4D','Định dạng 4D',20000),('IMAX','Định đạng IMAX',30000);
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
  `loai_do_an_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DOAN_LOAIDOAN_idx` (`loai_do_an_id`),
  CONSTRAINT `FK_DOAN_LOAIDOAN` FOREIGN KEY (`loai_do_an_id`) REFERENCES `loai_do_an` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an`
--

LOCK TABLES `do_an` WRITE;
/*!40000 ALTER TABLE `do_an` DISABLE KEYS */;
/*!40000 ALTER TABLE `do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `do_an_chi_tiet`
--

DROP TABLE IF EXISTS `do_an_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `do_an_chi_tiet` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `don_gia` int(10) unsigned NOT NULL,
  `do_an_id` varchar(15) NOT NULL,
  `kich_co_do_an_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DOANCHITIET_DOAN_idx` (`do_an_id`),
  KEY `FK_DOANCHITIET_KICHCODOAN_idx` (`kich_co_do_an_id`),
  CONSTRAINT `FK_DOANCHITIET_DOAN` FOREIGN KEY (`do_an_id`) REFERENCES `do_an` (`id`),
  CONSTRAINT `FK_DOANCHITIET_KICHCODOAN` FOREIGN KEY (`kich_co_do_an_id`) REFERENCES `kich_co_do_an` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `do_an_chi_tiet`
--

LOCK TABLES `do_an_chi_tiet` WRITE;
/*!40000 ALTER TABLE `do_an_chi_tiet` DISABLE KEYS */;
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
  `trang_thai` varchar(20) NOT NULL DEFAULT 'chua_dat',
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `loai_ghe_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GHE_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_GHE_LOAIGHE_idx` (`loai_ghe_id`),
  CONSTRAINT `FK_GHE_LOAIGHE` FOREIGN KEY (`loai_ghe_id`) REFERENCES `loai_ghe` (`id`),
  CONSTRAINT `FK_GHE_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghe_ngoi`
--

LOCK TABLES `ghe_ngoi` WRITE;
/*!40000 ALTER TABLE `ghe_ngoi` DISABLE KEYS */;
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
  `nhan_vien_id` int(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HOADON_NGUOIDUNG_idx` (`nhan_vien_id`),
  CONSTRAINT `FK_HOADON_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don_chi_tiet`
--

DROP TABLE IF EXISTS `hoa_don_chi_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hoa_don_chi_tiet` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `so_luong` int(10) unsigned NOT NULL,
  `hoa_don_id` varchar(15) NOT NULL,
  `do_an_chi_tiet_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_HOADONCHITIET_HOADON_idx` (`hoa_don_id`),
  KEY `FK_HOADONCHITIET_DOANCHITIET_idx` (`do_an_chi_tiet_id`),
  CONSTRAINT `FK_HOADONCHITIET_DOANCHITIET` FOREIGN KEY (`do_an_chi_tiet_id`) REFERENCES `do_an_chi_tiet` (`id`),
  CONSTRAINT `FK_HOADONCHITIET_HOADON` FOREIGN KEY (`hoa_don_id`) REFERENCES `hoa_don` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don_chi_tiet`
--

LOCK TABLES `hoa_don_chi_tiet` WRITE;
/*!40000 ALTER TABLE `hoa_don_chi_tiet` DISABLE KEYS */;
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
  `gioi_tinh` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_cmnd_UNIQUE` (`so_cmnd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `kich_co_do_an` VALUES ('large','Cỡ lớn'),('medium','Cỡ vừa'),('small','Cỡ nhỏ');
/*!40000 ALTER TABLE `kich_co_do_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_do_an`
--

DROP TABLE IF EXISTS `loai_do_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loai_do_an` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_do_an`
--

LOCK TABLES `loai_do_an` WRITE;
/*!40000 ALTER TABLE `loai_do_an` DISABLE KEYS */;
INSERT INTO `loai_do_an` VALUES (1,'Đồ ăn nhanh'),(2,'Đồ uống');
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
INSERT INTO `loai_ghe` VALUES ('DC','Ghế đôi',20000),('NC','Ghế thường',0),('SC','Ghế vip',30000);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `id` int(5) unsigned NOT NULL,
  `ho_ten` varchar(45) NOT NULL,
  `mat_khau` varchar(45) NOT NULL,
  `so_cmnd` varchar(20) NOT NULL,
  `so_dien_thoai` varchar(15) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dia_chi` varchar(100) DEFAULT NULL,
  `ngay_vao_lam` date NOT NULL,
  `gioi_tinh` tinyint(4) NOT NULL,
  `vai_tro_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `so_cmnd_UNIQUE` (`so_cmnd`),
  KEY `FK_NGUOIDUNG_VAITRO_idx` (`vai_tro_id`),
  CONSTRAINT `FK_NGUOIDUNG_VAITRO` FOREIGN KEY (`vai_tro_id`) REFERENCES `vai_tro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
/*!40000 ALTER TABLE `nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phim`
--

DROP TABLE IF EXISTS `phim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phim` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ten` varchar(100) NOT NULL,
  `thoi_luong` int(10) unsigned NOT NULL,
  `gioi_han_tuoi` int(10) unsigned DEFAULT NULL,
  `nam_san_xuat` int(10) unsigned DEFAULT NULL,
  `ngay_cong_chieu` date DEFAULT NULL,
  `ngon_ngu` varchar(20) DEFAULT 'Chưa cập nhật',
  `dao_dien` varchar(45) DEFAULT 'Chưa cập nhật',
  `dien_vien` varchar(100) DEFAULT 'Chưa cập nhật',
  `quoc_gia` varchar(45) DEFAULT 'Chưa cập nhật',
  `nha_san_xuat` varchar(45) DEFAULT 'Chưa cập nhật',
  `tom_tat` varchar(1000) DEFAULT 'Chưa cập nhật',
  `trang_thai` varchar(10) DEFAULT 'chua_chieu',
  `loai_phim_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PHIM_LOAIPHIM_idx` (`loai_phim_id`),
  CONSTRAINT `FK_PHIM_LOAIPHIM` FOREIGN KEY (`loai_phim_id`) REFERENCES `loai_phim` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phim`
--

LOCK TABLES `phim` WRITE;
/*!40000 ALTER TABLE `phim` DISABLE KEYS */;
/*!40000 ALTER TABLE `phim` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_chieu`
--

LOCK TABLES `phong_chieu` WRITE;
/*!40000 ALTER TABLE `phong_chieu` DISABLE KEYS */;
/*!40000 ALTER TABLE `phong_chieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suat_chieu`
--

DROP TABLE IF EXISTS `suat_chieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `suat_chieu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `gio_bat_dau` datetime NOT NULL,
  `gio_ket_thuc` datetime NOT NULL,
  `ngay_chieu` date NOT NULL,
  `phim_id` int(10) unsigned NOT NULL,
  `phong_chieu_id` int(10) unsigned NOT NULL,
  `dinh_dang_phim_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SUATCHIEU_PHIM_idx` (`phim_id`),
  KEY `FK_SUATCHIEU_PHONGCHIEU_idx` (`phong_chieu_id`),
  KEY `FK_SUATCHIEU_DINHDANGPHIM_idx` (`dinh_dang_phim_id`),
  CONSTRAINT `FK_SUATCHIEU_DINHDANGPHIM` FOREIGN KEY (`dinh_dang_phim_id`) REFERENCES `dinh_dang_phim` (`id`),
  CONSTRAINT `FK_SUATCHIEU_PHIM` FOREIGN KEY (`phim_id`) REFERENCES `phim` (`id`),
  CONSTRAINT `FK_SUATCHIEU_PHONGCHIEU` FOREIGN KEY (`phong_chieu_id`) REFERENCES `phong_chieu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suat_chieu`
--

LOCK TABLES `suat_chieu` WRITE;
/*!40000 ALTER TABLE `suat_chieu` DISABLE KEYS */;
/*!40000 ALTER TABLE `suat_chieu` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `vai_tro` VALUES ('AD','Quản lý rạp'),('MA','Ban quản lý');
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
  `tong_cong` int(10) unsigned DEFAULT '0',
  `suat_chieu_id` int(10) unsigned NOT NULL,
  `gia_ve_id` int(10) unsigned NOT NULL,
  `ghe_id` int(10) unsigned NOT NULL,
  `nhan_vien_id` int(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VEBAN_SUATCHIEU_idx` (`suat_chieu_id`),
  KEY `FK_VEBAN_GIAVE_idx` (`gia_ve_id`),
  KEY `FK_VEBAN_GHE_idx` (`ghe_id`),
  KEY `FK_VEBAN_NGUOIDUNG_idx` (`nhan_vien_id`),
  CONSTRAINT `FK_VEBAN_GHE` FOREIGN KEY (`ghe_id`) REFERENCES `ghe_ngoi` (`id`),
  CONSTRAINT `FK_VEBAN_GIAVE` FOREIGN KEY (`gia_ve_id`) REFERENCES `gia_ve` (`id`),
  CONSTRAINT `FK_VEBAN_NGUOIDUNG` FOREIGN KEY (`nhan_vien_id`) REFERENCES `nguoi_dung` (`id`),
  CONSTRAINT `FK_VEBAN_SUATCHIEU` FOREIGN KEY (`suat_chieu_id`) REFERENCES `suat_chieu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_ban`
--

LOCK TABLES `ve_ban` WRITE;
/*!40000 ALTER TABLE `ve_ban` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ve_dat`
--

DROP TABLE IF EXISTS `ve_dat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ve_dat` (
  `id` varchar(15) NOT NULL,
  `ngay_dat` date NOT NULL,
  `tong_cong` int(10) unsigned DEFAULT '0',
  `suat_chieu_id` int(10) unsigned NOT NULL,
  `gia_ve_id` int(10) unsigned NOT NULL,
  `ghe_id` int(10) unsigned NOT NULL,
  `khach_hang_id` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_VEDAT_SUATCHIEU_idx` (`suat_chieu_id`),
  KEY `FK_VEDAT_GIAVE_idx` (`gia_ve_id`),
  KEY `FK_VEDAT_GHE_idx` (`ghe_id`),
  KEY `FK_VEDAT_KHACHANG_idx` (`khach_hang_id`),
  CONSTRAINT `FK_VEDAT_GHE` FOREIGN KEY (`ghe_id`) REFERENCES `ghe_ngoi` (`id`),
  CONSTRAINT `FK_VEDAT_GIAVE` FOREIGN KEY (`gia_ve_id`) REFERENCES `gia_ve` (`id`),
  CONSTRAINT `FK_VEDAT_KHACHANG` FOREIGN KEY (`khach_hang_id`) REFERENCES `khach_hang` (`id`),
  CONSTRAINT `FK_VEDAT_SUATCHIEU` FOREIGN KEY (`suat_chieu_id`) REFERENCES `suat_chieu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ve_dat`
--

LOCK TABLES `ve_dat` WRITE;
/*!40000 ALTER TABLE `ve_dat` DISABLE KEYS */;
/*!40000 ALTER TABLE `ve_dat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-02 17:25:19