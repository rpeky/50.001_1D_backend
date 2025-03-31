CREATE DATABASE  IF NOT EXISTS `wandr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `wandr`;
-- MySQL dump 10.13  Distrib 8.0.41, for macos15 (arm64)
--
-- Host: 127.0.0.1    Database: wandr
-- ------------------------------------------------------
-- Server version	8.0.41

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
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `activities_user_fk_idx` (`fk_created_by`),
  KEY `activities_status_fk_idx` (`status`),
  CONSTRAINT `activities_status_fk` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `activities_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` 
  (`activity_id`, `title`, `fk_created_by`, `description`, `location`, `location_link`, `price`, `thumbnail`, `status`, `created_at`, `modified_at`)
VALUES
  (1, 'Hiking in the Mountains', 1, 'A challenging hike along a scenic trail.', 'Mountain Trail', 'http://example.com/mountain', 0.0, 'hike.jpg', 1, CURRENT_TIMESTAMP, NULL),
  (2, 'City Tour', 2, 'Explore the downtown area with local guides.', 'City Center', 'http://example.com/citytour', 25.00, 'citytour.jpg', 1, CURRENT_TIMESTAMP, NULL);
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
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
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `bookmarks_users_fk_idx` (`fk_created_by`),
  KEY `bookmarks_activities_fk_idx` (`fk_activity_id`),
  CONSTRAINT `bookmarks_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookmarks_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarks`
--

LOCK TABLES `bookmarks` WRITE;
/*!40000 ALTER TABLE `bookmarks` DISABLE KEYS */;
INSERT INTO `bookmarks`
  (`bookmark_id`, `fk_created_by`, `fk_activity_id`, `created_at`, `modified_at`)
VALUES
  (1, 3, 1, CURRENT_TIMESTAMP, NULL),
  (2, 4, 2, CURRENT_TIMESTAMP, NULL);

/*!40000 ALTER TABLE `bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) NOT NULL,
  `fk_activity_id` int DEFAULT NULL,
  `fk_itinerary_id` int DEFAULT NULL,
  `fk_created_by` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comments_itineraries_idx` (`fk_itinerary_id`),
  KEY `comments_activities_idx` (`fk_activity_id`),
  KEY `comments_users_fk_idx` (`fk_created_by`),
  CONSTRAINT `comments_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments`
  (`comment_id`, `comment`, `fk_activity_id`, `fk_itinerary_id`, `fk_created_by`, `created_at`, `modified_at`)
VALUES
  (1, 'Great activity, had lots of fun!', 1, NULL, 5, CURRENT_TIMESTAMP, NULL),
  (2, 'I enjoyed this city tour very much!', 2, NULL, 6, CURRENT_TIMESTAMP, NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
  `price_range` double DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `thumbnail` varchar(45) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`itinerary_id`),
  KEY `itineraries_users_fk_idx` (`fk_created_by`),
  KEY `itineraries_status_fk_idx` (`status`),
  CONSTRAINT `itineraries_status_fk` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `itineraries_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itineraries`
--

LOCK TABLES `itineraries` WRITE;
/*!40000 ALTER TABLE `itineraries` DISABLE KEYS */;
INSERT INTO `itineraries`
  (`itinerary_id`, `title`, `fk_created_by`, `description`, `price_range`, `duration`, `thumbnail`, `status`, `created_at`, `modified_at`)
VALUES
  (1, 'Weekend Getaway', 1, 'A fun weekend trip with scenic stops.', 100.0, 2, 'getaway.jpg', 1, CURRENT_TIMESTAMP, NULL),
  (2, 'Cultural Tour', 2, 'Experience the rich local culture.', 50.0, 1, 'culture.jpg', 1, CURRENT_TIMESTAMP, NULL);
/*!40000 ALTER TABLE `itineraries` ENABLE KEYS */;
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
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`like_id`),
  KEY `likes_itineraries_fk_idx` (`fk_itinerary_id`),
  KEY `likes_activities_fk_idx` (`fk_activity_id`),
  KEY `likes_users_fk_idx` (`fk_created_by`),
  CONSTRAINT `likes_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_users_fk` FOREIGN KEY (`fk_created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes`
  (`like_id`, `fk_created_by`, `fk_activity_id`, `fk_itinerary_id`, `created_at`, `modified_at`)
VALUES
  (1, 7, 1, NULL, CURRENT_TIMESTAMP, NULL),
  (2, 8, NULL, 1, CURRENT_TIMESTAMP, NULL);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
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
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status`
VALUES 
	(1,'Active','2025-03-20 16:54:22','2025-03-20 16:54:22'),
	(2,'Completed','2025-03-20 16:54:22','2025-03-20 16:54:22'),
	(3,'Published','2025-03-20 16:54:22','2025-03-20 16:54:22'),
	(4,'On Going','2025-03-20 18:01:37',NULL);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `trips`
--

DROP TABLE IF EXISTS `trips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trips` (
  `trip_id` int NOT NULL AUTO_INCREMENT,
  `fk_itinerary_id` int DEFAULT NULL,
  `fk_activity_id` int DEFAULT NULL,
  PRIMARY KEY (`trip_id`),
  KEY `trips_itinerary_fk_idx` (`fk_itinerary_id`),
  KEY `trips_activity_fk_idx` (`fk_activity_id`),
  CONSTRAINT `trips_activities_fk` FOREIGN KEY (`fk_activity_id`) REFERENCES `activities` (`activity_id`),
  CONSTRAINT `trips_itineraries_fk` FOREIGN KEY (`fk_itinerary_id`) REFERENCES `itineraries` (`itinerary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trips`
--

LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
LOCK TABLES `trips` WRITE;
INSERT INTO `trips`
  (`trip_id`, `fk_itinerary_id`, `fk_activity_id`)
VALUES
  (1, 1, 1),
  (2, 2, 2);
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
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
  `modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_uid_UNIQUE` (`user_uid`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `users_status_idx` (`status`),
  CONSTRAINT `users_status` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` 
VALUES 
	(1,'df6e8134-359a-429a-8e96-f795d944ab95','Kipper McGarahan','kmcgarahan0@state.gov',NULL,1,'2025-03-20 18:00:37',NULL),
	(2,'70154a33-2557-4ffc-9a49-13ce7cc9ab3b','Jareb Foulser','jfoulser1@arizona.edu','Face to face impactful complexity',1,'2025-03-20 18:00:37',NULL),
	(3,'6c6431f0-fe30-4968-a418-e84791a01bbd','Dianemarie Hendin','dhendin2@mozilla.org','Visionary actuating structure',1,'2025-03-20 18:00:37',NULL),
	(4,'2ce982a8-0631-47ff-801d-90d3d937063c','Adolphe Schonfeld','aschonfeld3@woothemes.com',NULL,1,'2025-03-20 18:00:37',NULL),
	(5,'d4c45bd8-9886-45ee-8b40-e8b077b9c00c','Diannne Buckthorp','dbuckthorp4@cam.ac.uk',NULL,1,'2025-03-20 18:00:37',NULL),
	(6,'aa4f1d79-0c9e-4960-b221-19c6e1a0d736','El Leeson','eleeson5@adobe.com',NULL,1,'2025-03-20 18:00:37',NULL),
	(7,'aa6902cb-693c-462c-a562-967d5c4d36c6','Craggie Ida','cida6@seesaa.net','Virtual intangible moratorium',1,'2025-03-20 18:00:37',NULL),
	(8,'d838e0f5-87d4-436c-a5d7-d8c8876afff3','Sib Crowche','scrowche7@cbslocal.com',NULL,1,'2025-03-20 18:00:37',NULL),
	(9,'ed59c94f-c6f0-4770-9a66-25a36dcd382b','Zaneta Castane','zcastane8@ucsd.edu','Visionary asymmetric policy',1,'2025-03-20 18:00:37',NULL),
	(10,'ace8681c-0b2c-45c8-bb49-d83284d76a70','Maible Corkell','mcorkell9@nytimes.com','Expanded dynamic attitude',1,'2025-03-20 18:00:37',NULL);
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

-- Dump completed on 2025-03-21  2:03:29