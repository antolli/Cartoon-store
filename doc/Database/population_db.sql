-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: fumetteria
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Jhon'),(2,'Alice'),(3,'Bob'),(4,'Peter'),(5,'Paul'),(6,'Mary');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comic`
--

DROP TABLE IF EXISTS `comic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `vol` int(11) NOT NULL,
  `edition` int(11) NOT NULL,
  `publication` date NOT NULL,
  `url_image_cover` varchar(85) DEFAULT NULL,
  `description` longtext NOT NULL,
  `stock` int(11) NOT NULL,
  `publishing_house_id` int(11) NOT NULL,
  `value` decimal(8,2) NOT NULL,
  `show_home` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comics_publishing_house1_idx` (`publishing_house_id`),
  CONSTRAINT `fk_comics_publishing_house1` FOREIGN KEY (`publishing_house_id`) REFERENCES `publishing_house` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comic`
--

LOCK TABLES `comic` WRITE;
/*!40000 ALTER TABLE `comic` DISABLE KEYS */;
INSERT INTO `comic` VALUES (1,'Avengers num. 7 ',7,0,'2015-06-01','avengers3.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',10,1,5.00,1),(2,'Sakura C.C.',1,1,'2015-05-01','sakura.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',9,2,3.00,1),(3,'Avengers num.8',8,0,'2015-07-01','avengers2.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',4,1,5.00,1),(5,'Turma da monica',1,2,'2015-09-22','capa_monica.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',2,1,2.00,1),(8,'Avengers num.9',9,0,'2015-08-22','avengers1.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',10,2,5.00,1),(10,'SpiderMan',1,1,'2015-09-09','ha.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',2,1,3.00,1),(11,'Iron Man',1,1,'2015-09-16','hf.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',2,1,5.00,1),(12,'Tsubasa',1,1,'2015-09-21','tsu.jpg','Lorem ipsum dolor sit amet, ad modo expetenda mea. Diam iracundia sea at, vix aeque mandamus no. An epicurei mandamus reformidans quo, incorrupte consectetuer et vix, usu ei platonem prodesset voluptaria. Vel ex dolore quaeque, ei eam magna albucius. Sit ferri ipsum mucius no, nam ad erat latine consectetuer, porro accommodare ullamcorper qui ad.',10,1,11.00,1);
/*!40000 ALTER TABLE `comic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comic_author`
--

DROP TABLE IF EXISTS `comic_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comic_author` (
  `type` varchar(45) NOT NULL,
  `comic_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`comic_id`,`author_id`,`type`),
  KEY `fk_comics_author_comics1_idx` (`comic_id`),
  KEY `fk_comics_author_author1_idx` (`author_id`),
  CONSTRAINT `fk_comics_author_author1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comics_author_comics1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comic_author`
--

LOCK TABLES `comic_author` WRITE;
/*!40000 ALTER TABLE `comic_author` DISABLE KEYS */;
INSERT INTO `comic_author` VALUES ('Editor',1,1),('Finalist Art',1,2),('Writer',1,3),('Editor',2,4),('Finalist Art',2,5),('Writer',2,6),('Editor',3,2),('Finalist Art',3,2),('Writer',3,2),('Finalist Art',5,2),('Writer',5,2),('Editor',5,4),('Editor',8,1),('Finalist Art',8,1),('Writer',8,1),('Editor',10,3),('Finalist Art',10,3),('Writer',10,3),('Editor',11,1),('Finalist Art',11,1),('Writer',11,1),('Writer',12,2),('Finalist Art',12,3),('Editor',12,4);
/*!40000 ALTER TABLE `comic_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comic_genre`
--

DROP TABLE IF EXISTS `comic_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comic_genre` (
  `comic_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`comic_id`,`genre_id`),
  KEY `fk_comics_has_genre_genre1_idx` (`genre_id`),
  KEY `fk_comics_has_genre_comics1_idx` (`comic_id`),
  CONSTRAINT `fk_comics_has_genre_comics1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comics_has_genre_genre1` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comic_genre`
--

LOCK TABLES `comic_genre` WRITE;
/*!40000 ALTER TABLE `comic_genre` DISABLE KEYS */;
INSERT INTO `comic_genre` VALUES (1,3),(3,3),(5,3),(10,3),(11,3),(8,4),(12,4),(2,5);
/*!40000 ALTER TABLE `comic_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (3,'Adventure'),(4,'Romance'),(5,'Horror'),(6,'Test'),(13,'Comedy');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(300) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `comic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_note_comic1_idx` (`comic_id`),
  KEY `fk_note_user1_idx` (`user_id`),
  CONSTRAINT `fk_note_comic1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (1,'Questo fumetto è fantastico! bla bla bla bla bla. ',1,3,1),(2,'Questo fumetto non mi piace perche bla bla bla',0,3,1),(3,'This is just a test',0,5,1),(4,'Figo!',0,10,15);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `comic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notice_comic1_idx` (`comic_id`),
  CONSTRAINT `fk_notice_comic1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(16) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone_customer1_idx` (`user_id`),
  CONSTRAINT `fk_phone_customer1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (4,'99939696','cell',13),(5,'040636150','fix',13),(6,'042656869','fix',13),(7,'320 482 6762','cell',15),(8,'','fix',15),(9,'','fix',15),(10,'','fix',16),(11,'','fix',16),(12,'','fix',16),(13,'','fix',1),(14,'','cell',1),(15,'','fix',1),(16,'','fix',2),(17,'','fix',2),(18,'','fix',2),(19,'','fix',3),(20,'','cell',3),(21,'','fix',3),(22,'','fix',14),(23,'','fix',14),(24,'','fix',14),(25,'','fix',13),(26,'','fix',13),(27,'','fix',13);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_house`
--

DROP TABLE IF EXISTS `publishing_house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishing_house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(14) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_house`
--

LOCK TABLES `publishing_house` WRITE;
/*!40000 ALTER TABLE `publishing_house` DISABLE KEYS */;
INSERT INTO `publishing_house` VALUES (1,'Panini','panini@comics.com','123456789'),(2,'DC Comics','dc@comics.com','123456789');
/*!40000 ALTER TABLE `publishing_house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `comic_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `value` decimal(8,2) NOT NULL,
  `qty` int(11) NOT NULL,
  `total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserve_comic1_idx` (`comic_id`),
  KEY `fk_reserve_user1_idx` (`user_id`),
  CONSTRAINT `fk_reserve_comic1` FOREIGN KEY (`comic_id`) REFERENCES `comic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserve_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,'2015-09-23 00:00:00',1,1,1,5.00,2,10.00),(2,'2015-08-22 00:00:00',1,2,0,4.00,1,4.00),(3,'2015-07-09 00:00:00',1,1,1,2.50,2,5.00),(4,'2015-06-12 00:00:00',1,1,0,1.00,1,1.00),(6,'2015-09-25 21:26:40',1,5,0,2.00,1,2.00),(7,'2015-10-05 09:51:27',1,3,1,5.00,1,5.00),(9,'2015-10-05 10:04:08',1,2,1,3.00,1,3.00),(11,'2015-10-05 10:55:13',15,10,1,3.00,5,15.00),(13,'2015-10-14 00:00:00',15,11,1,9.00,2,18.00);
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `vol` int(11) DEFAULT NULL,
  `edition` int(11) DEFAULT NULL,
  `publication` date DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_suggestion_user1_idx` (`user_id`),
  CONSTRAINT `fk_suggestion_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
INSERT INTO `suggestion` VALUES (3,'Sakura C.C',2,2,'2010-06-11','blablablablablablabla',1),(4,'Topolino',1,1,'1940-10-08','disney',15);
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `type_user` varchar(45) CHARACTER SET utf8 NOT NULL,
  `token` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Elisa','elisa.antolli@gmail.com','Antolli','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Admin','E829E85B220FE8BD3CC8B2768039CC17D91305F8CC2C78C7BAAB6B3559EC154E'),(2,'Teste','teste.dasilva@gmail.com','da Silva','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Admin',NULL),(3,'Teste','teste.desouza@gmail.com','de Souza','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Customer',NULL),(10,'Aldo','aldoa.10lorib@alice.it','Antolli','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Customer',NULL),(13,'Anderson','anderson@gmail.com','Avila Santos','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Admin',NULL),(14,'Alice','aliceculaon@hotmail.it','Culaon','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Customer',NULL),(15,'Diego','sr71pdf@gmail.com','Pillon','9AF15B336E6A9619928537DF30B2E6A2376569FCF9D7E773ECCEDE65606529A0','Admin',NULL),(16,'teste','teste@bla.com','aleatorio','8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','Admin',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-06 17:08:11
