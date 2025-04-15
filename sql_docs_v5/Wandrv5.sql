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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  UNIQUE KEY `user_product_uniq` (`fk_cart_id`,`fk_prod_id`),
  KEY `cart_item_product_fk` (`fk_prod_id`),
  CONSTRAINT `cart_item_cart_fk` FOREIGN KEY (`fk_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `cart_item_product_fk` FOREIGN KEY (`fk_prod_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `product_reviews`
--

DROP TABLE IF EXISTS `product_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `fk_created_by` int DEFAULT NULL,
  `fk_prod_id` int NOT NULL,
  `rating` int NOT NULL,
  `review` varchar(256) DEFAULT NULL,
  `verified_purchase` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`review_id`),
  KEY `review_product_fk` (`fk_prod_id`),
  KEY `review_user_fk` (`fk_created_by`),
  CONSTRAINT `review_product_fk` FOREIGN KEY (`fk_prod_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `review_user_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `stock` int DEFAULT '0',
  `purchase_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_history` (
  `purchase_history_id` int NOT NULL AUTO_INCREMENT,
  `fk_user_id` int NOT NULL,
  `fk_prod_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `purchased_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`purchase_history_id`),
  KEY `fk_purchase_user` (`fk_user_id`),
  KEY `fk_purchase_product` (`fk_prod_id`),
  CONSTRAINT `fk_purchase_product` FOREIGN KEY (`fk_prod_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_purchase_user` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15 14:00:59
