CREATE DATABASE  IF NOT EXISTS `wandr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wandr`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wandr2
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `fk_created_by` int NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `location_link` varchar(256) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `thumbnail` varchar(45) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`activity_id`),
  KEY `activities_user_fk_idx` (`fk_created_by`),
  KEY `activities_status_fk_idx` (`status`),
  CONSTRAINT `activities_status_fk` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `activities_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (2,'Ocean Exploring',1,'Join us for an exciting scuba diving adventure','South Sea','https://maps.example.com/southsea',75,'https://images.example.com/diving.jpg',18,'2025-04-04 16:26:16','2025-04-04 16:26:16'),(14,'City Food Tour',13,'Explore the vibrant culinary scene with our guided city food tour.',NULL,'https://maps.example.com/downtown',47.8,'https://images.example.com/foodtour.jpg',37,'2025-04-09 19:31:10','2025-04-09 19:41:51'),(15,'Mountain Biking Challenge',16,'Experience the thrill of mountain biking on challenging trails.','Sunset Trail','https://maps.example.com/sunsettrail',55.75,'https://images.example.com/biking.jpg',38,'2025-04-09 20:06:49','2025-04-09 20:06:49'),(16,'Mountain Biking Challenge',16,'Experience the thrill of mountain biking on challenging trails.','Sunset Trail','https://maps.example.com/sunsettrail',55.75,'https://images.example.com/biking.jpg',56,'2025-04-10 10:39:01','2025-04-10 10:39:01'),(17,'Mountain Biking Challenge',16,'Experience the thrill of mountain biking on challenging trails.','Sunset Trail','https://maps.example.com/sunsettrail',55.75,'https://images.example.com/biking.jpg',57,'2025-04-10 10:41:03','2025-04-10 10:41:03'),(18,'Mountain Biking Challenge',16,'Experience the thrill of mountain biking on challenging trails.','Sunset Trail','https://maps.example.com/sunsettrail',55.75,'https://images.example.com/biking.jpg',61,'2025-04-12 10:56:18','2025-04-12 10:56:18');
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_media`
--

DROP TABLE IF EXISTS `activity_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_media` (
  `media_id` int NOT NULL AUTO_INCREMENT,
  `fk_activity_id` int DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`media_id`),
  KEY `activity_media_activities_fk` (`fk_activity_id`),
  CONSTRAINT `activity_media_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_media`
--

LOCK TABLES `activity_media` WRITE;
/*!40000 ALTER TABLE `activity_media` DISABLE KEYS */;
INSERT INTO `activity_media` VALUES (6,15,'image','https://cdn.travelapp.com/media/eiffel_tower.jpg','2025-04-09 20:06:49','2025-04-09 20:06:49'),(7,15,'video','https://firebasestorage.googleapis.com/v0/b/travmith-309c4.firebasestorage.app/o/baguio.mp4?alt=media&token=29d5ec9f-c27c-4e11-9b92-64fd909e079b','2025-04-09 20:06:49','2025-04-09 20:06:49'),(9,14,'image','https://randomlinkImadethatshouldntwork','2025-04-10 10:21:04','2025-04-10 10:21:04'),(10,16,'image','https://cdn.travelapp.com/media/eiffel_tower.jpg','2025-04-10 10:39:01','2025-04-10 10:39:01'),(11,16,'video','https://firebasestorage.googleapis.com/v0/b/travmith-309c4.firebasestorage.app/o/baguio.mp4?alt=media&token=29d5ec9f-c27c-4e11-9b92-64fd909e079b','2025-04-10 10:39:01','2025-04-10 10:39:01'),(12,17,'image','https://cdn.travelapp.com/media/eiffel_tower.jpg','2025-04-10 10:41:03','2025-04-10 10:41:03'),(13,17,'video','https://firebasestorage.googleapis.com/v0/b/travmith-309c4.firebasestorage.app/o/baguio.mp4?alt=media&token=29d5ec9f-c27c-4e11-9b92-64fd909e079b','2025-04-10 10:41:03','2025-04-10 10:41:03'),(14,18,'image','https://cdn.travelapp.com/media/eiffel_tower.jpg','2025-04-12 10:56:18','2025-04-12 10:56:18'),(15,18,'video','https://firebasestorage.googleapis.com/v0/b/travmith-309c4.firebasestorage.app/o/baguio.mp4?alt=media&token=29d5ec9f-c27c-4e11-9b92-64fd909e079b','2025-04-12 10:56:18','2025-04-12 10:56:18');
/*!40000 ALTER TABLE `activity_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmarks`
--

DROP TABLE IF EXISTS `bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmarks` (
  `bookmark_id` int NOT NULL AUTO_INCREMENT,
  `fk_created_by` int NOT NULL,
  `fk_activity_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`bookmark_id`),
  UNIQUE KEY `user_activity_unq` (`fk_created_by`,`fk_activity_id`),
  KEY `bookmarks_users_fk_idx` (`fk_created_by`),
  KEY `bookmarks_activities_fk_idx` (`fk_activity_id`),
  CONSTRAINT `bookmarks_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookmarks_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarks`
--

LOCK TABLES `bookmarks` WRITE;
/*!40000 ALTER TABLE `bookmarks` DISABLE KEYS */;
INSERT INTO `bookmarks` VALUES (3,17,15,'2025-04-12 07:41:27','2025-04-12 07:41:27'),(6,13,16,'2025-04-12 18:35:49','2025-04-12 18:35:49');
/*!40000 ALTER TABLE `bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `fk_user_id` int NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_user` (`fk_user_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `fk_cart_id` int NOT NULL,
  `fk_prod_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `cart_item_cart_fk` (`fk_cart_id`),
  KEY `cart_item_product_fk` (`fk_prod_id`),
  CONSTRAINT `cart_item_cart_fk` FOREIGN KEY (`fk_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `cart_item_product_fk` FOREIGN KEY (`fk_prod_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `fk_created_by` int DEFAULT NULL,
  `fk_activity_id` int DEFAULT NULL,
  `fk_itinerary_id` int DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `comments_itineraries_idx` (`fk_itinerary_id`),
  KEY `comments_activities_idx` (`fk_activity_id`),
  KEY `comments_users_fk_idx` (`fk_created_by`),
  CONSTRAINT `comments_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (5,17,NULL,14,'Tried to go to Rome, was very expensive, Idt  this person is saying the truth at all.','2025-04-11 17:19:47','2025-04-11 17:19:47'),(6,17,NULL,12,'Trip to your mom is very rude, who tf made this','2025-04-11 17:20:54','2025-04-11 17:20:54');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_activity`
--

DROP TABLE IF EXISTS `day_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `day_activity` (
  `day_activity_id` int NOT NULL AUTO_INCREMENT,
  `fk_day_id` int DEFAULT NULL,
  `fk_activity_id` int DEFAULT NULL,
  PRIMARY KEY (`day_activity_id`),
  KEY `days_activities_activities_fk` (`fk_activity_id`),
  KEY `itinerary-days_day-id_fk` (`fk_day_id`),
  CONSTRAINT `days_activities_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`),
  CONSTRAINT `itinerary_day_day_activity_fk` FOREIGN KEY (`fk_day_id`) REFERENCES `itinerary_day` (`day_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_activity`
--

LOCK TABLES `day_activity` WRITE;
/*!40000 ALTER TABLE `day_activity` DISABLE KEYS */;
INSERT INTO `day_activity` VALUES (2,3,2),(3,3,14),(4,4,15),(21,16,2),(22,16,14),(23,17,15),(36,27,15),(39,30,15),(45,35,2),(46,35,14),(47,36,15),(48,37,2),(49,37,14),(50,38,15),(51,39,15),(56,44,15),(57,45,14),(58,46,2),(59,46,14),(60,47,15),(67,52,15),(68,53,14);
/*!40000 ALTER TABLE `day_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itineraries`
--

DROP TABLE IF EXISTS `itineraries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itineraries` (
  `itinerary_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `fk_created_by` int NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `thumbnail` varchar(100) DEFAULT NULL,
  `cover_photo` varchar(100) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`itinerary_id`),
  KEY `itineraries_users_fk_idx` (`fk_created_by`),
  KEY `itineraries_status_fk_idx` (`status`),
  CONSTRAINT `itineraries_status_fk` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `itineraries_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itineraries`
--

LOCK TABLES `itineraries` WRITE;
/*!40000 ALTER TABLE `itineraries` DISABLE KEYS */;
INSERT INTO `itineraries` VALUES (3,'Trip to Paris',13,'Explore the beauty of Paris over 5 days.','https://images.unsplash.com/photo-1502602898657-3e91760cbb34?q=80&w=1000&auto=format&fit=crop',NULL,42,'2025-04-09 20:28:38','2025-04-09 20:28:38'),(7,'Trip to Rome MY ASS',16,'Discover the ancient ruins and vibrant culture of Rome over 5 unforgettable days.','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop','https://lookAtthisCoverPhoto.com',46,'2025-04-09 21:05:22','2025-04-09 22:00:46'),(8,'Trip to Ur Anus',16,'sdojbspbnfsdbnbarj gbf','https://thisisdefinitelyathumbnai.org','https://lookAtthisCoverPhoto.com',47,'2025-04-09 21:27:13','2025-04-09 22:09:59'),(9,'Trip to Rome',16,'Explore the ancient ruins and vibrant culture of Rome over 5 unforgettable days. Now with free Salsa from the Vatican City','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop','https://lookAtthisCoverPhoto.com',48,'2025-04-09 21:30:19','2025-04-10 10:17:56'),(10,'Trip to Rome',16,'Discover the ancient ruins and vibrant culture of Rome over 5 unforgettable days.','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop',NULL,49,'2025-04-09 21:35:02','2025-04-09 21:35:02'),(12,'Trip to Ur Mom',16,'Check for this update','https://thisisdefinitelyathumbnai.org',NULL,51,'2025-04-09 21:41:33','2025-04-09 22:19:27'),(13,'Trip to Rome',16,'Check for this update sdojbspbnfsdbnbarj gbf','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop','https://lookAtthisCoverPhoto.com',52,'2025-04-09 21:53:30','2025-04-09 21:53:39'),(14,'Trip to Rome',16,'Discover the ancient ruins and vibrant culture of Rome over 5 unforgettable days.','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop',NULL,53,'2025-04-09 22:20:03','2025-04-09 22:20:03'),(15,'Trip to Rome',16,'Discover the ancient ruins and vibrant culture of Rome over 5 unforgettable days.','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop',NULL,54,'2025-04-09 22:20:25','2025-04-09 22:20:25'),(16,'Trip to Paris',13,'Explore the beauty of Paris over 5 days.','https://images.unsplash.com/photo-1502602898657-3e91760cbb34?q=80&w=1000&auto=format&fit=crop',NULL,58,'2025-04-10 11:09:53','2025-04-10 11:09:53'),(18,'Trip to Rome',13,'Explore the ancient ruins and vibrant culture of Rome over 5 unforgettable days. Now with free Salsa from the Vatican City','https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop','https://lookAtthisCoverPhoto.com',60,'2025-04-12 10:56:00','2025-04-12 11:10:46');
/*!40000 ALTER TABLE `itineraries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itinerary_day`
--

DROP TABLE IF EXISTS `itinerary_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itinerary_day` (
  `fk_itinerary_id` int DEFAULT NULL,
  `day_id` int NOT NULL AUTO_INCREMENT,
  `day` int NOT NULL,
  PRIMARY KEY (`day_id`),
  KEY `itinerary_day_itineraries_fk_idx` (`fk_itinerary_id`),
  CONSTRAINT `itinerary_day_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itinerary_day`
--

LOCK TABLES `itinerary_day` WRITE;
/*!40000 ALTER TABLE `itinerary_day` DISABLE KEYS */;
INSERT INTO `itinerary_day` VALUES (3,3,1),(3,4,2),(10,16,1),(10,17,2),(13,27,1),(7,30,1),(8,35,1),(8,36,2),(14,37,1),(14,38,2),(15,39,2),(9,44,1),(9,45,2),(16,46,1),(16,47,2),(18,52,1),(18,53,2);
/*!40000 ALTER TABLE `itinerary_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` int NOT NULL AUTO_INCREMENT,
  `fk_created_by` int NOT NULL,
  `fk_activity_id` int DEFAULT NULL,
  `fk_itinerary_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `user_activity_unq` (`fk_created_by`,`fk_activity_id`),
  UNIQUE KEY `user_itinerary_unq` (`fk_created_by`,`fk_itinerary_id`),
  KEY `likes_itineraries_fk_idx` (`fk_itinerary_id`),
  KEY `likes_activities_fk_idx` (`fk_activity_id`),
  KEY `likes_users_fk_idx` (`fk_created_by`),
  CONSTRAINT `likes_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (6,17,NULL,9,'2025-04-11 10:22:39','2025-04-11 10:22:39'),(9,1,15,NULL,'2025-04-11 10:25:27','2025-04-11 10:25:27');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_review`
--

DROP TABLE IF EXISTS `product_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `fk_prod_id` int NOT NULL,
  `fk_created_by` int NOT NULL,
  `rating` int NOT NULL,
  `review` varchar(256) DEFAULT NULL,
  `verified_purchase` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`review_id`),
  KEY `review_product_fk` (`fk_prod_id`),
  KEY `review_user_fk` (`fk_created_by`),
  CONSTRAINT `review_product_fk` FOREIGN KEY (`fk_prod_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `review_user_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_review`
--

LOCK TABLES `product_review` WRITE;
/*!40000 ALTER TABLE `product_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `purchase_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings` (
  `rating_id` int NOT NULL AUTO_INCREMENT,
  `fk_created_by` int NOT NULL,
  `fk_activity_id` int DEFAULT NULL,
  `fk_itinerary_id` int DEFAULT NULL,
  `rating` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rating_id`),
  UNIQUE KEY `uniq_user_activity` (`fk_created_by`,`fk_activity_id`),
  UNIQUE KEY `uniq_user_itinerary` (`fk_created_by`,`fk_itinerary_id`),
  KEY `fk_rating_activity` (`fk_activity_id`),
  KEY `fk_rating_itinerary` (`fk_itinerary_id`),
  CONSTRAINT `fk_rating_activity` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`),
  CONSTRAINT `fk_rating_itinerary` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`),
  CONSTRAINT `fk_rating_user` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (2,17,2,NULL,5,'2025-04-12 10:52:28','2025-04-12 10:52:28'),(3,17,NULL,9,5,'2025-04-12 10:53:15','2025-04-12 10:53:15'),(4,16,NULL,9,3,'2025-04-12 10:53:42','2025-04-12 10:53:42');
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Inactive','2025-04-03 13:38:45','2025-04-03 13:51:18'),(5,'Active','2025-04-04 08:10:37','2025-04-04 08:10:37'),(18,'ACTIVE','2025-04-04 16:26:16','2025-04-04 16:26:16'),(20,'Active','2025-04-04 19:26:53','2025-04-04 19:26:53'),(30,'active','2025-04-05 12:54:30','2025-04-05 12:54:30'),(37,'Planning','2025-04-09 19:31:10','2025-04-09 19:31:10'),(38,'Planning','2025-04-09 20:06:49','2025-04-09 20:06:49'),(42,'Planning','2025-04-09 20:28:38','2025-04-09 20:28:38'),(46,'Published','2025-04-09 21:05:22','2025-04-09 22:00:46'),(47,'Published','2025-04-09 21:27:13','2025-04-09 21:58:13'),(48,'Published','2025-04-09 21:30:19','2025-04-10 10:28:51'),(49,'Planning','2025-04-09 21:35:02','2025-04-09 21:35:02'),(51,'Published','2025-04-09 21:41:33','2025-04-09 21:45:55'),(52,'Published','2025-04-09 21:53:30','2025-04-09 21:53:39'),(53,'Planning','2025-04-09 22:20:03','2025-04-09 22:20:03'),(54,'Planning','2025-04-09 22:20:25','2025-04-09 22:20:25'),(55,'Active','2025-04-10 10:31:36','2025-04-10 10:31:36'),(56,'Planning','2025-04-10 10:39:01','2025-04-10 10:39:01'),(57,'Planning','2025-04-10 10:41:03','2025-04-10 10:41:03'),(58,'Planning','2025-04-10 11:09:53','2025-04-10 11:09:53'),(60,'Published','2025-04-12 10:56:00','2025-04-12 11:10:46'),(61,'Planning','2025-04-12 10:56:18','2025-04-12 10:56:18');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_follows`
--

DROP TABLE IF EXISTS `user_follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_follows` (
  `follow_id` int NOT NULL AUTO_INCREMENT,
  `follower_id` int NOT NULL,
  `following_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`follow_id`),
  UNIQUE KEY `follower_following_uniq` (`follower_id`,`following_id`),
  KEY `user_following_fk` (`following_id`),
  CONSTRAINT `user_follower_fk` FOREIGN KEY (`follower_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_following_fk` FOREIGN KEY (`following_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_follows`
--

LOCK TABLES `user_follows` WRITE;
/*!40000 ALTER TABLE `user_follows` DISABLE KEYS */;
INSERT INTO `user_follows` VALUES (5,1,11,'2025-04-12 05:55:55'),(6,1,16,'2025-04-12 05:56:26'),(7,17,1,'2025-04-12 05:56:50'),(8,16,1,'2025-04-12 05:57:04');
/*!40000 ALTER TABLE `user_follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_settings`
--

DROP TABLE IF EXISTS `user_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_settings` (
  `user_setting_id` int NOT NULL AUTO_INCREMENT,
  `fk_user_id` int NOT NULL,
  `push_notifications_enabled` tinyint(1) DEFAULT '1',
  `email_notifications_enabled` tinyint(1) DEFAULT '1',
  `trip_updates_enabled` tinyint(1) DEFAULT '1',
  `social_updates_enabled` tinyint(1) DEFAULT '1',
  `marketing_notifications_enabled` tinyint(1) DEFAULT '0',
  `biometric_auth_enabled` tinyint(1) DEFAULT '0',
  `language` varchar(10) DEFAULT 'en',
  `theme` varchar(20) DEFAULT 'light',
  `currency` varchar(4) DEFAULT 'USD',
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_setting_id`),
  KEY `user_user_settings_fk` (`fk_user_id`),
  CONSTRAINT `user_user_settings_fk` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_settings`
--

LOCK TABLES `user_settings` WRITE;
/*!40000 ALTER TABLE `user_settings` DISABLE KEYS */;
INSERT INTO `user_settings` VALUES (1,1,1,0,1,0,0,1,'en','dark','SGD','2025-04-12 17:54:13'),(2,11,1,1,1,1,0,0,'en','light','USD','2025-04-04 08:10:37'),(3,13,1,1,1,1,0,0,'en','light','USD','2025-04-04 19:26:53'),(4,16,1,1,1,1,0,0,'en','light','USD','2025-04-05 12:54:30'),(5,17,1,1,1,1,0,0,'en','light','USD','2025-04-10 10:31:36');
/*!40000 ALTER TABLE `user_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_uid` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `bio` varchar(256) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_uid_UNIQUE` (`user_uid`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `users_status_idx` (`status`),
  CONSTRAINT `users_status` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'df6e8134-359a-429a-8e96-f795d944ab95','Kipper McGarahanoooooo','kmcgarahan0@state.gov','Hello!',1,'2025-03-20 10:00:37','2025-04-05 12:14:00'),(11,'eg7f9245-460b-530b-9f07-g806e055bc06','Jamie Carragharararr00000','Carragher@state.govdvnjndkv','Bleh bloo blah, liverpool fan',5,'2025-04-04 08:10:37','2025-04-04 09:51:31'),(13,'d21c45f3-67a8-4b12-8e90-123456abcdef','Jane Smith','janesmitty@example.com','Passionate about technology and innovative. OMG!',20,'2025-04-04 19:26:53','2025-04-05 09:10:58'),(16,'f87a2345-1234-5678-9abc-1d2e3f4a5b6c','Robert Johnsonooooo','robert.johnson@example.com.myass','An enthusiastic traveller and food lover. Definitely, yeah yeah.',30,'2025-04-05 12:54:30','2025-04-10 10:25:11'),(17,'c9d7f8a2-4b3e-4d1a-9e0b-8f7c6d5a4b3c','maria garcia','maria.garcia@example.com','avid photographer and traveler',55,'2025-04-10 10:31:36','2025-04-10 10:31:36');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-13 15:17:53
