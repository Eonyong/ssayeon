CREATE DATABASE  IF NOT EXISTS `ssayeon1` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssayeon1`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: spring-practice.ctvzqmwyjimr.ap-northeast-2.rds.amazonaws.com    Database: ssayeon1
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `alarm_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `url` varchar(45) DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`alarm_id`),
  KEY `fk_notification_user1_idx` (`user_id`),
  CONSTRAINT `fk_notification_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,5,'알람1','알람1url',NULL),(2,5,'알람2','알람2url',NULL),(3,5,'알람3','알람3url',NULL),(4,5,'알람4','알람4url',NULL),(5,5,'알람5','알람5url',NULL),(6,6,'test1님이 쓴 게시글에 댓글이 달렸습니다.','k6a204.p.ssafy.io//balance/4',NULL),(7,6,'test1님이 쓴 게시글에 댓글이 달렸습니다.','k6a204.p.ssafy.io//balance/4',NULL),(8,6,'test1님이 쓴 게시글에 댓글이 달렸습니다.','k6a204.p.ssafy.io/balance/4',NULL);
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `views` int DEFAULT '0',
  `likes_count` int DEFAULT '0',
  PRIMARY KEY (`article_id`),
  KEY `fk_article_user1_idx` (`user_id`),
  KEY `fk_article_category1_idx` (`category_id`),
  KEY `board_id_idx` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,5,1,1,'4피는 옛말입니다. 이제는 3피입니다.','content 수정asdfasdfbf','1111-11-11 11:11:11','2022-05-19 12:55:06',81,0),(21,1,1,1,'날 안아줘 날 눌러줘~','content','2022-05-05 23:06:00','2022-05-19 12:54:02',7,0),(22,3,1,1,'삼성 코테 왤케 어렵나요','다들 코테 어디서 공부하나요 ㅠ','2022-05-06 00:37:14','2022-05-19 12:53:59',20,0),(23,1,1,1,'다들 프로젝트 주제 뭐하기로 했어?','우리팀은 싸피 커뮤니티 게시판!!','2022-05-06 00:37:55','2022-05-19 12:54:57',40,5),(24,1,1,1,'취업시켜줘 제발!!!!!!!!!!!','취업하고싶다..','2022-05-06 00:38:40','2022-05-19 13:32:16',23,5),(25,1,2,1,'스프링 JPA 오류 코드 봐주실분','쪽지 주세요','2022-05-06 00:39:27','2022-05-19 12:55:32',3,5),(27,1,2,1,'삼전 자기소개서 2번 어떻게 써야 하나요','막막하다..','2022-05-06 00:46:08','2022-05-19 12:09:53',2,5),(28,1,3,1,'리액트 꿀팁 3개','1. 노마드코더 강의 듣기 2. 나만의 앱 만들기','2022-05-06 00:56:24','2022-05-19 12:34:49',4,4),(29,1,3,1,'프로젝트 발표 꿀팁 5개','1. 발표는 자신감있게 2. 사람들의 관심을 끌어라 3. 청중과 교류하라','2022-05-06 00:56:32','2022-05-19 13:32:00',55,3),(31,1,3,1,'JPA N+1문제에 대한 해법','1. Join Fetch 2. @EntityGraph','2022-05-06 01:05:13','2022-05-19 12:55:00',35,1),(32,1,1,1,'100만원 너무 소중하다..','치킨시켜야지','2022-05-06 01:06:13','2022-05-19 13:32:47',17,3),(33,1,1,1,'우리집 고양이 내옆에서자는중','귀엽겠지','2022-05-11 01:57:10','2022-05-19 12:53:36',12,2),(35,5,1,1,'이 편지는 영국에서 시작되었으며...','웅앵','2022-05-11 05:06:02','2022-05-19 12:53:34',21,0);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_answer`
--

DROP TABLE IF EXISTS `article_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_answer` (
  `article_answer_id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `article_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `is_selected` tinyint(1) DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`article_answer_id`),
  KEY `fk_article_answer_user1_idx` (`user_id`),
  KEY `fk_article_answer_article1_idx` (`article_id`),
  CONSTRAINT `fk_article_answer_article1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `fk_article_answer_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_answer`
--

LOCK TABLES `article_answer` WRITE;
/*!40000 ALTER TABLE `article_answer` DISABLE KEYS */;
INSERT INTO `article_answer` VALUES (3,5,25,'보냈어',0,'2022-05-07 03:04:05','2022-05-07 03:04:05'),(4,5,27,'나도 고민중..',0,'2022-05-07 03:07:46','2022-05-07 03:07:46');
/*!40000 ALTER TABLE `article_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comments`
--

DROP TABLE IF EXISTS `article_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_comments` (
  `article_comments_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `article_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`article_comments_id`),
  KEY `fk_user_has_article_article1_idx` (`article_id`),
  KEY `fk_user_has_article_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_article_article1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `fk_user_has_article_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comments`
--

LOCK TABLES `article_comments` WRITE;
/*!40000 ALTER TABLE `article_comments` DISABLE KEYS */;
INSERT INTO `article_comments` VALUES (1,1,1,'5피는 없나요?',NULL,NULL),(3,5,1,'니가 삼피면 난 이피다','2022-05-06 03:46:17','2022-05-06 04:07:40'),(4,5,1,'퍼가요~!','2022-05-11 05:33:40','2022-05-11 05:33:40'),(5,3,1,'퍼가요~!(222)','2022-05-11 05:34:13','2022-05-11 05:34:13'),(7,5,1,'노잼추','2022-05-18 07:13:05','2022-05-18 07:13:05'),(8,5,1,'ㄴ? 뭐가 노잼 재밌는데..','2022-05-18 07:17:28','2022-05-18 07:17:28'),(9,5,1,'댓글 테스트','2022-05-18 07:26:27','2022-05-18 07:26:27'),(10,5,1,'댓글 작성','2022-05-18 07:27:18','2022-05-18 07:27:18'),(11,5,1,'? 왜 덧글이 세번 써지지???','2022-05-18 07:38:18','2022-05-18 07:38:18'),(12,5,24,'ㅇㅈ','2022-05-18 14:10:54','2022-05-18 14:10:54'),(13,5,24,'삼성한테 나 강제 분양하고 싶다...','2022-05-18 14:25:52','2022-05-18 14:25:52'),(14,5,32,'ㅋㅋㅋ','2022-05-18 17:07:46','2022-05-18 17:07:46'),(15,43,22,'리트코드도 있어요!','2022-05-19 01:27:55','2022-05-19 01:27:55'),(16,5,1,'내용','2022-05-19 02:54:47','2022-05-19 02:54:47'),(17,43,1,'ㅋㅋㅋㅋㅋㅋㅋㅋ','2022-05-19 05:10:59','2022-05-19 05:10:59'),(18,43,32,'ㄹㅇ.. 돈 벌기 힘들다ㅜㅜ','2022-05-19 05:12:27','2022-05-19 05:12:27'),(19,43,24,'나도 취업하고싶다','2022-05-19 05:12:54','2022-05-19 05:12:54'),(21,40,31,'꿀팁이네요','2022-05-19 05:25:50','2022-05-19 05:25:50'),(22,5,22,'백준, 프로그래머스여','2022-05-19 12:29:19','2022-05-19 12:29:19'),(23,44,35,'그래서 편지 어디로 간건데...','2022-05-19 12:50:48','2022-05-19 12:50:48'),(24,5,33,'사진 ㄱㄱ','2022-05-19 12:53:10','2022-05-19 12:53:10'),(25,5,35,'내 마음속♥','2022-05-19 12:53:30','2022-05-19 12:53:30');
/*!40000 ALTER TABLE `article_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comments_likes`
--

DROP TABLE IF EXISTS `article_comments_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_comments_likes` (
  `article_comments_likes_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `article_comments_id` bigint NOT NULL,
  PRIMARY KEY (`article_comments_likes_id`),
  KEY `fk_user_has_article_comments_user1_idx` (`user_id`),
  KEY `article_comment_id_idx` (`article_comments_id`),
  CONSTRAINT `article_comment_id` FOREIGN KEY (`article_comments_id`) REFERENCES `article_comments` (`article_comments_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_article_comments_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comments_likes`
--

LOCK TABLES `article_comments_likes` WRITE;
/*!40000 ALTER TABLE `article_comments_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_comments_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_has_tag`
--

DROP TABLE IF EXISTS `article_has_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_has_tag` (
  `article_has_tag_id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`article_has_tag_id`),
  KEY `article_id_idx` (`article_id`),
  KEY `tag_id_idx` (`tag_id`),
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_has_tag`
--

LOCK TABLES `article_has_tag` WRITE;
/*!40000 ALTER TABLE `article_has_tag` DISABLE KEYS */;
INSERT INTO `article_has_tag` VALUES (3,21,1),(4,21,2),(5,21,3),(9,23,1),(10,23,2),(11,23,3),(12,24,1),(13,24,2),(14,24,3),(15,25,1),(16,25,2),(17,25,3),(21,22,1),(22,22,2),(23,22,3),(24,27,1),(25,27,2),(26,27,3),(29,29,1),(30,29,2),(33,31,1),(34,31,2),(35,32,1),(36,32,2),(37,33,1),(38,33,2),(41,35,1),(42,35,2);
/*!40000 ALTER TABLE `article_has_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_likes`
--

DROP TABLE IF EXISTS `article_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_likes` (
  `article_likes_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `article_id` bigint NOT NULL,
  PRIMARY KEY (`article_likes_id`),
  KEY `fk_user_has_article_article2_idx` (`article_id`),
  KEY `fk_user_has_article_user2_idx` (`user_id`),
  CONSTRAINT `fk_user_has_article_article2` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_article_user2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_likes`
--

LOCK TABLES `article_likes` WRITE;
/*!40000 ALTER TABLE `article_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_scrap`
--

DROP TABLE IF EXISTS `article_scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_scrap` (
  `scrap_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `article_id` bigint NOT NULL,
  PRIMARY KEY (`scrap_id`),
  KEY `fk_user_has_article_article3_idx` (`article_id`),
  KEY `fk_user_has_article_user3_idx` (`user_id`),
  CONSTRAINT `fk_user_has_article_article3` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `fk_user_has_article_user3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_scrap`
--

LOCK TABLES `article_scrap` WRITE;
/*!40000 ALTER TABLE `article_scrap` DISABLE KEYS */;
INSERT INTO `article_scrap` VALUES (2,5,1);
/*!40000 ALTER TABLE `article_scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balance`
--

DROP TABLE IF EXISTS `balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balance` (
  `balance_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `left_description` varchar(45) NOT NULL,
  `right_description` varchar(45) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`balance_id`),
  KEY `fk_balance_user1_idx` (`user_id`),
  CONSTRAINT `fk_balance_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance`
--

LOCK TABLES `balance` WRITE;
/*!40000 ALTER TABLE `balance` DISABLE KEYS */;
INSERT INTO `balance` VALUES (1,3,'연봉 4천 원하는 일 vs 연봉 6천 원하지 않는 일','1번 left 글 내용','1번 right 글 내용','2022-04-28 14:55:55','2022-04-28 14:55:55'),(4,6,'4번글 내용','4번 left 글 내용','4번 right 글 내용','2022-04-28 14:55:55','2022-05-03 15:53:48'),(9,1,'9번글 내용 9번글 내용 9번글 내용 9번글 내용 9번글 내용','9번 left 글 내용','9번 right 글 내용','2022-05-05 23:22:10','2022-05-05 23:22:10'),(10,1,'10번글 내용','아이폰','갤럭시','2022-05-05 23:22:10','2022-05-05 23:22:10'),(11,1,'프론트 vs 백엔드','프론트','백','2022-05-05 23:22:10','2022-05-05 23:22:10'),(12,1,'ssafy 1번 더 하기 vs 중소기업 취직하기','ssafy 1번 더 하기 ','중소기업 취직하기','2022-05-05 23:22:10','2022-05-05 23:22:10'),(14,5,'원하는 얼굴 vs 원하는 몸매','원하는 얼굴','원하는 몸매','2022-05-18 13:55:29','2022-05-18 13:55:29'),(15,5,'일주일 안 씻기 vs 매일 5번 씻기','일주일 안 씻기','매일 5번 씻기','2022-05-18 13:59:46','2022-05-18 13:59:46'),(16,5,'토맛토마토 vs 토마토맛토','토맛토마토 ','토마토맛토','2022-05-18 13:59:49','2022-05-18 13:59:49'),(17,5,'매일 고구마 먹기 vs 매일 감자 먹기','매일 고구마 먹기','매일 감자 먹기','2022-05-18 14:04:44','2022-05-18 14:04:44'),(18,5,'연봉 4천 원하는 일 vs 연봉 6천 원하지 않는 일','연봉 4천 원하는 일','연봉 6천 원하지 않는 일','2022-05-18 14:04:55','2022-05-18 14:04:55'),(19,5,'평생 떡볶이 먹기 vs 평생 떡볶이 안 먹기','평생 떡볶이 먹기','평생 떡볶이 안 먹기','2022-05-18 14:33:00','2022-05-18 14:33:00'),(20,5,'엽떡 vs 신전떡','엽떡','신전떡','2022-05-18 08:38:44','2022-05-18 08:38:44');
/*!40000 ALTER TABLE `balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balance_comments`
--

DROP TABLE IF EXISTS `balance_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balance_comments` (
  `balance_comments_id` bigint NOT NULL AUTO_INCREMENT,
  `balance_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`balance_comments_id`),
  KEY `fk_balance_has_user_user2_idx` (`user_id`),
  KEY `fk_balance_has_user_balance2_idx` (`balance_id`),
  CONSTRAINT `fk_balance_has_user_balance2` FOREIGN KEY (`balance_id`) REFERENCES `balance` (`balance_id`),
  CONSTRAINT `fk_balance_has_user_user2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance_comments`
--

LOCK TABLES `balance_comments` WRITE;
/*!40000 ALTER TABLE `balance_comments` DISABLE KEYS */;
INSERT INTO `balance_comments` VALUES (1,4,6,'test-05-03','2022-05-03 14:53:23','2022-05-03 14:53:23'),(2,4,6,'test-05-03','2022-05-05 23:19:18','2022-05-05 23:19:18'),(3,4,39,'test-05-05','2022-05-05 23:22:44','2022-05-05 23:22:44'),(4,4,39,'zz','2022-05-06 00:27:08','2022-05-06 00:28:15');
/*!40000 ALTER TABLE `balance_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balance_comments_likes`
--

DROP TABLE IF EXISTS `balance_comments_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balance_comments_likes` (
  `balance_comments_likes_id` bigint NOT NULL,
  `balance_comments_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`balance_comments_likes_id`),
  KEY `fk_balance_comments_has_user_user1_idx` (`user_id`),
  KEY `fk_balance_comments_has_user_balance_comments1_idx` (`balance_comments_id`),
  CONSTRAINT `fk_balance_comments_has_user_balance_comments1` FOREIGN KEY (`balance_comments_id`) REFERENCES `balance_comments` (`balance_comments_id`),
  CONSTRAINT `fk_balance_comments_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance_comments_likes`
--

LOCK TABLES `balance_comments_likes` WRITE;
/*!40000 ALTER TABLE `balance_comments_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `balance_comments_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balance_selected`
--

DROP TABLE IF EXISTS `balance_selected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balance_selected` (
  `balance_selected_id` bigint NOT NULL AUTO_INCREMENT,
  `balance_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `select_target` tinyint(1) NOT NULL,
  PRIMARY KEY (`balance_selected_id`),
  KEY `fk_balance_has_user_user1_idx` (`user_id`),
  KEY `fk_balance_has_user_balance1_idx` (`balance_id`),
  CONSTRAINT `fk_balance_has_user_balance1` FOREIGN KEY (`balance_id`) REFERENCES `balance` (`balance_id`),
  CONSTRAINT `fk_balance_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance_selected`
--

LOCK TABLES `balance_selected` WRITE;
/*!40000 ALTER TABLE `balance_selected` DISABLE KEYS */;
INSERT INTO `balance_selected` VALUES (1,12,6,0),(2,12,39,0),(11,12,5,0),(12,11,5,0),(13,10,5,0),(14,9,5,1),(15,19,5,1),(16,18,5,0),(17,17,5,0),(18,20,5,0),(23,16,5,0),(24,15,5,1),(25,14,5,0),(26,20,39,1),(27,19,39,1),(28,18,39,0),(29,17,39,1),(30,15,39,1),(31,16,39,1),(32,17,40,1),(33,19,43,1),(34,20,44,1),(35,19,44,1),(36,18,44,1),(37,15,44,0),(38,16,44,1),(39,17,44,1),(40,14,44,0),(41,10,44,1),(42,11,44,1),(43,12,44,0),(44,20,40,1),(45,19,40,1),(46,16,40,0),(47,12,40,0),(48,12,43,1),(49,14,40,0),(50,15,40,0),(51,18,40,1),(52,11,40,1),(53,16,43,1),(54,17,43,0),(55,20,43,0),(56,18,43,0);
/*!40000 ALTER TABLE `balance_selected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'자유'),(2,'질문'),(3,'꿀팁');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'test');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_has_board`
--

DROP TABLE IF EXISTS `category_has_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_has_board` (
  `category_has_board_id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`category_has_board_id`),
  KEY `fk_category_has_board_board1_idx` (`board_id`),
  KEY `fk_category_has_board_category1_idx` (`category_id`),
  CONSTRAINT `fk_category_has_board_board1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `fk_category_has_board_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_has_board`
--

LOCK TABLES `category_has_board` WRITE;
/*!40000 ALTER TABLE `category_has_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_has_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` bigint NOT NULL,
  `receiver_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `fk_user_has_user_user2_idx` (`receiver_id`),
  KEY `fk_user_has_user_user1_idx` (`sender_id`),
  CONSTRAINT `fk_user_has_user_user1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_user_has_user_user2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,5,14,'ㅎㅇㅎㅇ','2022-05-09 10:34:59','2022-05-09 10:34:59',0),(2,5,14,'나는 김싸피','2022-05-09 10:35:20','2022-05-09 10:35:20',0),(3,5,14,'메시지 전송 테스트중','2022-05-09 10:35:26','2022-05-09 10:35:26',0),(4,14,5,'안녕 김싸피','2022-05-09 10:36:44','2022-05-09 10:36:44',1),(5,14,5,'나는 이싸피','2022-05-09 10:36:56','2022-05-09 10:36:56',1),(6,5,6,'테스트1 유저에게 메시지 전송!!','2022-05-09 10:39:17','2022-05-09 10:39:17',0),(7,37,6,'테스트1 유저에게 메시지 전송!!','2022-05-12 06:14:10','2022-05-12 06:14:10',0),(8,6,39,'안녕 김싸피','2022-05-12 06:14:55','2022-05-12 06:16:00',1),(9,37,6,'dksdjsklasdjkl','2022-05-17 04:57:16','2022-05-17 04:57:16',0),(10,37,6,'aasdadd','2022-05-17 05:35:25','2022-05-17 05:35:25',0),(11,37,6,'dksdjsklasdjkl','2022-05-17 05:41:33','2022-05-17 05:41:33',0),(12,37,6,'sdsdsds','2022-05-17 05:45:55','2022-05-17 05:45:55',0),(13,37,6,'안녕','2022-05-17 05:47:43','2022-05-17 05:47:43',0),(14,37,6,'sdsdsd','2022-05-17 05:48:21','2022-05-17 05:48:21',0),(15,37,6,'바보야','2022-05-17 05:48:42','2022-05-17 05:48:42',0),(16,37,6,'안녕하세요','2022-05-17 07:37:53','2022-05-17 07:37:53',0),(17,37,6,'','2022-05-17 15:12:43','2022-05-17 15:12:43',0),(18,37,6,'d','2022-05-17 15:27:39','2022-05-17 15:27:39',0),(19,37,6,'안녕하세요','2022-05-18 01:14:43','2022-05-18 01:14:43',0),(20,5,6,'안녕','2022-05-18 08:22:06','2022-05-18 08:22:06',0),(21,5,6,'ㅋㅋㄹㅃㅃ','2022-05-18 08:22:22','2022-05-18 08:22:22',0),(22,5,6,'ㅎㅇ','2022-05-18 17:35:34','2022-05-18 17:35:34',0),(23,5,44,'메시지입니다','2022-05-19 12:20:34','0000-00-00 00:00:00',0),(24,44,5,'안녕하세요','2022-05-19 12:22:00','2022-05-19 12:22:00',0),(25,37,44,'원빈씨 뭐하세요','2022-05-19 12:22:09','0000-00-00 00:00:00',0),(26,44,37,'ㅎㅇ','2022-05-19 12:22:31','2022-05-19 12:22:31',0),(27,5,44,'ㅎㅇㅎㅇ','2022-05-19 12:54:41','2022-05-19 12:54:41',0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `views` int DEFAULT '0',
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'SSAYEON 서비스 개시','2022.05.20 (금) SSAYEON 서비스를 정식 개시했습니다. 많관부','2022-05-10 09:47:25','2022-05-19 13:32:34',45),(2,'2022.05.23 (월) 서비스 점검 안내','서비스 점검시 일부 기능을 이용하실 수 없습니다. 일시: 2022.05.23 (월) 01:00-06:00','2022-05-10 09:47:42','2022-05-19 13:32:34',72),(3,'밸런스 게임 UI 개선','밸런스 게임 UI가 일부 개선되었습니다.','2022-05-10 09:47:47','2022-05-20 00:24:17',70),(4,'선호도 조사 게시판 개설!','많은 관심 부탁드립니다.','2022-05-10 09:47:52','2022-05-20 00:24:17',73),(5,'댓글 UI 개선','댓글을 더욱 보기 쉽게 UI가 개선되었습니다.','2022-05-10 09:47:56','2022-05-20 00:24:17',58),(6,'모임 게시판 신설 예정!!','모임 게시판을 통해 SSAFY인들과의 교류가 활발해졌으면 합니다! 많은 기대 부탁드려요!','2022-05-10 09:49:14','2022-05-20 00:23:39',49);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference`
--

DROP TABLE IF EXISTS `preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference` (
  `preference_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`preference_id`),
  KEY `fk_preference_user1_idx` (`user_id`),
  CONSTRAINT `fk_preference_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference`
--

LOCK TABLES `preference` WRITE;
/*!40000 ALTER TABLE `preference` DISABLE KEYS */;
INSERT INTO `preference` VALUES (22,5,'코테 볼 때 주력 언어는?','2022-05-19 02:15:12','2022-05-19 02:15:12'),(24,5,'선호하는 FE 프레임워크','2022-05-19 02:17:25','2022-05-19 02:17:25'),(25,5,'원하는 신입 최소 연봉 마지노선은?','2022-05-19 02:22:25','2022-05-19 02:22:25'),(26,5,'다들 어떤 기업 제일 가고 싶어?','2022-05-19 02:24:35','2022-05-19 02:24:35'),(27,5,'다들 백준 티어가 어떻게 되시나요?','2022-05-19 02:27:35','2022-05-19 02:27:35'),(29,44,'일주일에 운동 얼마나 자주 하시나요?','2022-05-19 12:45:58','2022-05-19 12:45:58'),(30,39,'신입으로 가고 싶은 기업은?','2022-05-19 12:49:31','2022-05-19 12:49:31'),(31,39,'디지털 노마드가 된다면 어디서..?','2022-05-19 12:50:32','2022-05-19 12:50:32');
/*!40000 ALTER TABLE `preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference_comments`
--

DROP TABLE IF EXISTS `preference_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference_comments` (
  `preference_comments_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `preference_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`preference_comments_id`),
  KEY `fk_user_has_preference_preference1_idx` (`preference_id`),
  KEY `fk_user_has_preference_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_preference_preference1` FOREIGN KEY (`preference_id`) REFERENCES `preference` (`preference_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_has_preference_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference_comments`
--

LOCK TABLES `preference_comments` WRITE;
/*!40000 ALTER TABLE `preference_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `preference_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference_comments_likes`
--

DROP TABLE IF EXISTS `preference_comments_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference_comments_likes` (
  `preference_comments_likes_id` bigint NOT NULL AUTO_INCREMENT,
  `preference_comments_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`preference_comments_likes_id`),
  KEY `fk_preference_comments_has_user_user1_idx` (`user_id`),
  KEY `fk_preference_comments_has_user_preference_comments1_idx` (`preference_comments_id`),
  CONSTRAINT `fk_preference_comments_has_user_preference_comments1` FOREIGN KEY (`preference_comments_id`) REFERENCES `preference_comments` (`preference_comments_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_preference_comments_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference_comments_likes`
--

LOCK TABLES `preference_comments_likes` WRITE;
/*!40000 ALTER TABLE `preference_comments_likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `preference_comments_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference_options`
--

DROP TABLE IF EXISTS `preference_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference_options` (
  `preference_options_id` bigint NOT NULL AUTO_INCREMENT,
  `preference_id` bigint NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`preference_options_id`),
  KEY `fk_user_has_preference1_preference1_idx` (`preference_id`),
  CONSTRAINT `fk_user_has_preference1_preference1` FOREIGN KEY (`preference_id`) REFERENCES `preference` (`preference_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference_options`
--

LOCK TABLES `preference_options` WRITE;
/*!40000 ALTER TABLE `preference_options` DISABLE KEYS */;
INSERT INTO `preference_options` VALUES (72,22,'C'),(73,22,'C++'),(74,22,'JAVA'),(75,22,'JAVASCRIPT'),(76,22,'PYTHON'),(79,24,'React'),(80,24,'Angular'),(81,24,'Vue'),(82,25,'3500미만'),(83,25,'3500이상 4000미만'),(84,25,'4000이상 4500미만'),(85,25,'4500이상 5000미만'),(86,25,'5000이상'),(87,26,'대기업 SI'),(88,26,'금융권'),(89,26,'IT기업'),(90,26,'스타트업'),(91,27,'Bronze'),(92,27,'Silver'),(93,27,'Gold'),(94,27,'Platinum'),(95,27,'Diamond'),(96,27,'Ruby'),(103,29,'0일'),(104,29,'1~2일'),(105,29,'3~4일'),(106,29,'5일이상'),(107,30,'스타트업'),(108,30,'대기업'),(109,30,'좋좋소'),(110,30,'외국계'),(111,31,'제주도'),(112,31,'해외'),(113,31,'역시 사무실이지..!'),(114,31,'화장실...');
/*!40000 ALTER TABLE `preference_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference_options_user_selected`
--

DROP TABLE IF EXISTS `preference_options_user_selected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference_options_user_selected` (
  `preference_options_user_selected_id` bigint NOT NULL AUTO_INCREMENT,
  `preference_id` bigint NOT NULL,
  `preference_options_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`preference_options_user_selected_id`),
  KEY `fk_preference_options_has_user_user1_idx` (`user_id`),
  KEY `fk_preference_options_has_user_preference_options1_idx` (`preference_options_id`),
  KEY `fk_preference_options_user_selected_preference1_idx` (`preference_id`),
  CONSTRAINT `fk_preference_options_has_user_preference_options1` FOREIGN KEY (`preference_options_id`) REFERENCES `preference_options` (`preference_options_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_preference_options_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL,
  CONSTRAINT `fk_preference_options_user_selected_preference1` FOREIGN KEY (`preference_id`) REFERENCES `preference` (`preference_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=371 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference_options_user_selected`
--

LOCK TABLES `preference_options_user_selected` WRITE;
/*!40000 ALTER TABLE `preference_options_user_selected` DISABLE KEYS */;
INSERT INTO `preference_options_user_selected` VALUES (331,27,96,40),(334,22,76,43),(340,27,93,43),(344,24,79,5),(345,22,76,5),(346,27,93,5),(347,26,89,5),(348,25,86,5),(354,25,85,44),(357,29,105,39),(369,27,96,44),(370,30,108,44);
/*!40000 ALTER TABLE `preference_options_user_selected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `count` bigint DEFAULT '0',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'jpa',1),(2,'java',2),(3,'spring',3);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tech_stack`
--

DROP TABLE IF EXISTS `tech_stack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tech_stack` (
  `tech_stack_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`tech_stack_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tech_stack`
--

LOCK TABLES `tech_stack` WRITE;
/*!40000 ALTER TABLE `tech_stack` DISABLE KEYS */;
INSERT INTO `tech_stack` VALUES (5,'python'),(6,'java');
/*!40000 ALTER TABLE `tech_stack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `class_id` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `company` varchar(45) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `is_alarm` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'싸피최고','test','test','1','se','qw','qw',0),(3,'삼성전자임원','test_user','test_user','12341234','1234','test_user','test_user',0),(5,'삼피','ssafy','ssafy@ssafy.com','ssafy','$2a$10$p6XJTUs/ZfdzCjtJG/cgauZXJpbFYhPVPfOS7CqJYruvu7rdKE9Pe','삼성','http://ssayeon.s3.ap-northeast-2.amazonaws.com/profile/8988a918-7936-4047-a7b4-5c669902e627_rn_image_picker_lib_temp_f5a5ca41-a569-4e11-b2fe-9a8e7fec84be.jpg',0),(6,'test1','t1','t1@naver.com','12341234','$2a$10$SnMcMPEDPweZL58UhBdo/.Mtd9yXiy8o7nE0YTjXn9LZXu2jsDFry',NULL,NULL,0),(14,'ssafy1','ssafy1','ssafy1@ssafy.com','ssafy1','$2a$10$VXrPLvSBcZzTz81oLmtnSuUATpTBintmgwvS0pOSrGFHbSBBx6cCq',NULL,NULL,0),(36,'testttttt','name','email@gmail.com','111','$2a$10$9kPYiaGoG9Qs5FiPV87oiuAWufgKqRSxmqU9pFQYASIVKDp.yr09.',NULL,NULL,0),(37,'!!iii!!!!','ssafy코치','a@gmail.com','1234','$2a$10$0bo4MusnAm6J3Fbeua8px.NbbZfeKjF39xnAUla.QpLLqwZ7qImXO',NULL,NULL,0),(39,'도망가고싶다','정언용','unjoo94@naver.com','0644656','$2a$10$udQWf.HLXFZxAw7XshxWpe0oorvg72B.UqOfe6EWm2x7EG1KVTAuC',NULL,NULL,0),(40,'싸피화이팅','박소율','psa9456@naver.com','0647065','$2a$10$ZgY/oWDv.6a4OEU6BQtNyu/h9485wTAyXDvHPsshNinZ2/2uhQS.a',NULL,NULL,0),(41,'jj','최정민','chlwjdals98@gmail.com','0642563','$2a$10$CbcE7pdE27V9M5WRFJPJuu0ztguEOX.PMlGeGM88BkOjvhXLDwPPK',NULL,NULL,0),(42,'취업시켜주3','임형준','gudwnsrh100@naver.com','0649032','$2a$10$sNL6FRu55y/wBVSLAEQL4.SGeMBRSbWyEK0K0dHwFeR15NkiP5WPK',NULL,NULL,0),(43,'kjw','김종우','kjwkjw@naver.com','0644375','$2a$10$uIZp/qnvcxE0gtvqk.2juOLwbh2iT4rLyHAfbhGQelQeaSEd3XXxa',NULL,NULL,0),(44,'원빈','강동석','kds2048@naver.com','0646999','$2a$10$eapYn/UX.gam4fE2DnPQz.uU./TmZVwdgimVlbTy2.g0aRVyExWLy',NULL,NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_certification`
--

DROP TABLE IF EXISTS `user_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_certification` (
  `user_certification_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `class_id` varchar(45) NOT NULL,
  PRIMARY KEY (`user_certification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=702 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_certification`
--

LOCK TABLES `user_certification` WRITE;
/*!40000 ALTER TABLE `user_certification` DISABLE KEYS */;
INSERT INTO `user_certification` VALUES (1,'김성현','0613232'),(2,'김준우','0616292'),(3,'김근태','0619717'),(4,'김일환','0618779'),(5,'김성준','0618916'),(6,'김채련','0619330'),(7,'황선주','0615657'),(8,'박예진','0613548'),(9,'이윤우','0616685'),(10,'손민기','0611248'),(11,'김성현','0610727'),(12,'서예진','0611513'),(13,'김동유','0617869'),(14,'심은조','0617531'),(15,'박설','0619942'),(16,'송상빈','0612343'),(17,'김도연','0619430'),(18,'강민우','0619033'),(19,'김수민','0610110'),(20,'김경민','0613124'),(21,'이현정','0617757'),(22,'박성건','0615261'),(23,'최지수','0613777'),(24,'정우철','0616730'),(25,'성당현','0617775'),(26,'김신아','0612994'),(27,'정현수','0612499'),(28,'오현지','0613169'),(29,'오윤진','0616848'),(30,'윤설','0612432'),(31,'이승규','0618896'),(32,'김지원','0618960'),(33,'임혁','0617685'),(34,'하현서','0617731'),(35,'이정언','0616614'),(36,'이주용','0617124'),(37,'이윤영','0616296'),(38,'윤성빈','0616360'),(39,'하지애','0616114'),(40,'김승환','0617020'),(41,'김은서','0617860'),(42,'고재현','0615190'),(43,'이윤준','0619941'),(44,'문찬송','0618912'),(45,'김규성','0618587'),(46,'노건우','0618213'),(47,'황이레','0619669'),(48,'박재현','0612363'),(49,'최다운','0615102'),(50,'김혜인','0614964'),(51,'김형준','0618730'),(52,'이종준','0615247'),(53,'조은지','0612223'),(54,'이동현','0618422'),(55,'김준석','0617253'),(56,'강승수','0612131'),(57,'양승우','0617268'),(58,'김동익','0612749'),(59,'김희섭','0619146'),(60,'박주한','0610821'),(61,'박주윤','0614572'),(62,'최윤석','0613131'),(63,'박소희','0615871'),(64,'윤영선','0618032'),(65,'장소명','0611263'),(66,'김소희','0610124'),(67,'최범규','0613999'),(68,'이다예','0611492'),(69,'김준형','0610472'),(70,'김홍중','0618471'),(71,'유진주','0617467'),(72,'정재현','0611282'),(73,'서은민','0617325'),(74,'최강현','0612566'),(75,'장다빈','0612902'),(76,'최수한','0610971'),(77,'전웅재','0613314'),(78,'이명원','0618774'),(79,'이제민','0615746'),(80,'강민철','0611859'),(81,'박상우','0615499'),(82,'한윤희','0618824'),(83,'이승현','0612587'),(84,'한규정','0619599'),(85,'백지영','0614729'),(86,'김현송','0614331'),(87,'오지훈','0619726'),(88,'이병석','0612087'),(89,'박주빈','0613271'),(90,'김병완','0610082'),(91,'김소은','0613517'),(92,'박소진','0617204'),(93,'정정채','0613350'),(94,'정동균','0618034'),(95,'허범','0614091'),(96,'상진수','0614087'),(97,'채성원','0618198'),(98,'이윤식','0615955'),(99,'이재권','0611094'),(100,'김현수','0610544'),(101,'박상훈','0616020'),(102,'윤지영','0615914'),(103,'장지영','0613511'),(104,'유병재','0615180'),(105,'김도현','0619976'),(106,'김동일','0619606'),(107,'이정은','0618252'),(108,'김나영','0613347'),(109,'안상현','0618962'),(110,'이은성','0613194'),(111,'이남수','0617905'),(112,'장영하','0614350'),(113,'김태민','0619209'),(114,'박한빈','0614141'),(115,'황은빈','0618665'),(116,'장정훈','0617081'),(117,'허영민','0618895'),(118,'유한욱','0616052'),(119,'변희성','0619136'),(120,'박창건','0627459'),(121,'김시은','0626232'),(122,'윤혜윤','0621576'),(123,'김상희','0627180'),(124,'장성준','0621234'),(125,'김은준','0626985'),(126,'박준용','0627378'),(127,'김수호','0628765'),(128,'홍지원','0622551'),(129,'박명우','0620774'),(130,'서상용','0620804'),(131,'문예영','0626390'),(132,'박승원','0629027'),(133,'장세영','0627864'),(134,'권영준','0624318'),(135,'이준영','0627527'),(136,'임현모','0624742'),(137,'양아름','0626324'),(138,'허유진','0626346'),(139,'이석규','0622096'),(140,'김영훈','0621437'),(141,'김혜리','0628207'),(142,'장수원','0624665'),(143,'김형우','0628349'),(144,'최현석','0620265'),(145,'고우영','0628242'),(146,'김예진','0620399'),(147,'김민정','0621672'),(148,'이영준','0629476'),(149,'방수영','0626984'),(150,'이진곤','0628337'),(151,'최영빈','0627236'),(152,'윤수현','0620070'),(153,'박소미','0626618'),(154,'김병준','0624282'),(155,'김서인','0624160'),(156,'남근호','0622725'),(157,'김주환','0624161'),(158,'김덕규','0625405'),(159,'윤효전','0628579'),(160,'홍태균','0626727'),(161,'박유정','0622277'),(162,'육현동','0628825'),(163,'조가예','0627676'),(164,'하욱현','0623396'),(165,'김길웅','0620451'),(166,'이상현','0623731'),(167,'김혜진','0628924'),(168,'윤은채','0627235'),(169,'이지연','0628888'),(170,'맹주영','0624999'),(171,'김도연','0626991'),(172,'이종현','0622512'),(173,'박기성','0624974'),(174,'임혜영','0628786'),(175,'김민성','0624995'),(176,'나요셉','0620029'),(177,'문관필','0621284'),(178,'정인하','0629590'),(179,'이승호','0621687'),(180,'정경훈','0627956'),(181,'김준하','0620042'),(182,'김민정','0620433'),(183,'지수연','0628384'),(184,'이유진','0624216'),(185,'지현배','0627166'),(186,'배준호','0625838'),(187,'김민수','0629659'),(188,'최주은','0627084'),(189,'이정민','0622267'),(190,'최현정','0627793'),(191,'최지우','0627088'),(192,'윤종목','0624650'),(193,'이보연','0628835'),(194,'정종혁','0624560'),(195,'이수진','0622511'),(196,'김성우','0628734'),(197,'서지원','0621102'),(198,'권연주','0623250'),(199,'이현석','0620280'),(200,'박건형','0624025'),(201,'윤승일','0621581'),(202,'윤재성','0628186'),(203,'장영윤','0626580'),(204,'박동진','0620884'),(205,'전창기','0622758'),(206,'홍령기','0624641'),(207,'우동진','0625882'),(208,'방호진','0620294'),(209,'박지유','0621097'),(210,'이수형','0626849'),(211,'최기운','0621826'),(212,'이승관','0629389'),(213,'최형오','0625893'),(214,'김동현','0620702'),(215,'최진영','0627555'),(216,'김현수','0623704'),(217,'강진구','0627826'),(218,'서지원','0625000'),(219,'이찬희','0623637'),(220,'이승기','0620802'),(221,'김다예','0625186'),(222,'이수환','0628693'),(223,'최대호','0621099'),(224,'방기진','0628855'),(225,'최현민','0628005'),(226,'이명성','0627452'),(227,'윤기재','0628863'),(228,'강태훈','0620996'),(229,'권도형','0629717'),(230,'박현진','0621690'),(231,'오동근','0623004'),(232,'박우경','0626297'),(233,'우상준','0622179'),(234,'이재성','0629878'),(235,'이우영','0627745'),(236,'이재희','0625123'),(237,'정인석','0628501'),(238,'조준영','0628452'),(239,'김다은','0626723'),(240,'이인섭','0622283'),(241,'신인호','0623756'),(242,'임기태','0627072'),(243,'이홍준','0621607'),(244,'조태연','0629426'),(245,'최성석','0626044'),(246,'김종현','0622248'),(247,'박예정','0624068'),(248,'이하림','0622188'),(249,'송경희','0623650'),(250,'김주향','0633642'),(251,'이정필','0636027'),(252,'김두회','0637984'),(253,'이동철','0631343'),(254,'정채은','0634026'),(255,'김석원','0630679'),(256,'김남욱','0637939'),(257,'이동호','0637133'),(258,'한채은','0637011'),(259,'경규동','0631305'),(260,'이가은','0637475'),(261,'김경동','0632487'),(262,'김주호','0635603'),(263,'김정빈','0636795'),(264,'박소미','0630616'),(265,'박해인','0636117'),(266,'김지언','0637755'),(267,'이호열','0639982'),(268,'윤동희','0631346'),(269,'곽명필','0633571'),(270,'권영현','0638316'),(271,'박창현','0632114'),(272,'김응철','0630514'),(273,'안정석','0639829'),(274,'김수용','0636074'),(275,'안세연','0639089'),(276,'유채린','0634755'),(277,'임채은','0637313'),(278,'홍성원','0613289'),(279,'박다원','0634478'),(280,'윤원정','0637922'),(281,'이건희','0638338'),(282,'유현수','0637916'),(283,'정명수','0631246'),(284,'이주현','0638525'),(285,'한선규','0636743'),(286,'송진섭','0639755'),(287,'이재만','0631497'),(288,'배나영','0635413'),(289,'박윤지','0631707'),(290,'우정연','0635521'),(291,'백철연','0637938'),(292,'황승연','0636768'),(293,'오수경','0630079'),(294,'이제훈','0636071'),(295,'이소영','0632859'),(296,'정지욱','0638613'),(297,'정희연','0634175'),(298,'이소라','0639300'),(299,'허범','0632683'),(300,'김치우','0632927'),(301,'신미래','0638427'),(302,'이상백','0639719'),(303,'오재문','0630704'),(304,'정은이','0635241'),(305,'김승수','0632171'),(306,'이준형','0632609'),(307,'홍석준','0636320'),(308,'이상윤','0634631'),(309,'최유미','0636510'),(310,'박민주','0635524'),(311,'이예나','0635526'),(312,'오용록','0633607'),(313,'이주형','0631520'),(314,'박아람','0635236'),(315,'윤지영','0632855'),(316,'한상우','0638249'),(317,'정하은','0638326'),(318,'장성태','0633719'),(319,'안재현','0633036'),(320,'김중재','0633208'),(321,'박찬의','0636376'),(322,'박혜준','0634573'),(323,'김민현','0634723'),(324,'서형준','0633460'),(325,'김유진','0638072'),(326,'이소현','0637780'),(327,'김지수','0632024'),(328,'김두회','0632684'),(329,'김진용','0635999'),(330,'김태호','0631616'),(331,'최시열','0632765'),(332,'권오범','0630381'),(333,'송예인','0636846'),(334,'길기호','0634529'),(335,'손모은','0636338'),(336,'김나린','0637445'),(337,'김어진','0634300'),(338,'곽현준','0638143'),(339,'이지순','0633079'),(340,'남궁휘','0636519'),(341,'오서하','0633933'),(342,'한승훈','0632313'),(343,'최승연','0632864'),(344,'안현호','0634048'),(345,'윤숙','0633391'),(346,'김정윤','0638286'),(347,'황보라','0639146'),(348,'현병욱','0634605'),(349,'신동호','0635698'),(350,'현종일','0638133'),(351,'장지빈','0632425'),(352,'전영서','0632754'),(353,'한우리','0630368'),(354,'홍종현','0639175'),(355,'원유진','0631162'),(356,'김유정','0636785'),(357,'민선규','0638445'),(358,'유우식','0636187'),(359,'오은진','0630959'),(360,'고원영','0631130'),(361,'조용구','0635587'),(362,'오윤기','0635082'),(363,'이진석','0638517'),(364,'김순요','0632687'),(365,'백민아','0635145'),(366,'안예지','0637791'),(367,'이아현','0636214'),(368,'정재호','0650620'),(369,'김지슬','0658156'),(370,'임창현','0655340'),(371,'손한기','0656912'),(372,'이우철','0652778'),(373,'이정원','0650771'),(374,'전호정','0657455'),(375,'김태현','0657164'),(376,'강소현','0650016'),(377,'최상후','0657543'),(378,'윤찬호','0654314'),(379,'조은누리','0656777'),(380,'반형동','0658475'),(381,'차상훈','0655210'),(382,'박영찬','0655088'),(383,'임경훈','0654767'),(384,'김현민','0655811'),(385,'양지훈','0658081'),(386,'김윤지','0659360'),(387,'김영기','0654286'),(388,'강동옥','0653636'),(389,'박동준','0659800'),(390,'어서빈','0657286'),(391,'이정현','0654483'),(392,'안영원','0657048'),(393,'시승우','0650315'),(394,'지수민','0650644'),(395,'조현아','0651232'),(396,'손은성','0655904'),(397,'박상준','0654233'),(398,'정인수','0653891'),(399,'최이삭','0655891'),(400,'이상엽','0650349'),(401,'정순일','0656028'),(402,'이승훈','0656129'),(403,'최영진','0650986'),(404,'김창민','0659698'),(405,'최명재','0654602'),(406,'김동현','0656429'),(407,'최소희','0651494'),(408,'장영남','0654290'),(409,'장현진','0656415'),(410,'김광희','0657571'),(411,'김재욱','0654880'),(412,'조성현','0654707'),(413,'정윤정','0658791'),(414,'장하석','0654249'),(415,'문슬기','0659431'),(416,'장원종','0658262'),(417,'한성희','0654639'),(418,'곽동현','0658579'),(419,'조해성','0658880'),(420,'우윤식','0658665'),(421,'박진성','0657158'),(422,'류기탁','0659035'),(423,'진은정','0651506'),(424,'양재빈','0659826'),(425,'오제노','0657841'),(426,'정성우','0657088'),(427,'장예찬','0654663'),(428,'오세헌','0650196'),(429,'김동주','0657188'),(430,'김현태','0653544'),(431,'배소원','0655581'),(432,'조영현','0655215'),(433,'강광은','0659901'),(434,'임현홍','0657163'),(435,'김범주','0654125'),(436,'이혜진','0650733'),(437,'박성은','0650845'),(438,'박준영','0655768'),(439,'윤관','0656833'),(440,'박수아','0658883'),(441,'최호준','0656114'),(442,'성아영','0655348'),(443,'전양희','0655966'),(444,'양석조','0658008'),(445,'김도훈','0652047'),(446,'박신영','0654762'),(447,'박대언','0656105'),(448,'홍지범','0652346'),(449,'김용희','0646632'),(450,'박기범','0646625'),(451,'천재원','0645817'),(452,'이서영','0649438'),(453,'제진명','0646556'),(454,'김승규','0642903'),(455,'정원식','0641963'),(456,'정주헌','0649861'),(457,'이다예','0645218'),(458,'김종우','0644375'),(459,'안지애','0642351'),(460,'김동현','0640696'),(461,'박현우','0648233'),(462,'염형덕','0644871'),(463,'이수민','0647234'),(464,'손창현','0643155'),(465,'윤영철','0644648'),(466,'김유민','0644282'),(467,'나지엽','0644738'),(468,'안재영','0648577'),(469,'전슬민','0645164'),(470,'강동원','0643952'),(471,'전건하','0646721'),(472,'최종현','0646153'),(473,'이정음','0644652'),(474,'엄희성','0646599'),(475,'조영운','0642972'),(476,'박상현','0641727'),(477,'노문택','0647446'),(478,'장준범','0646805'),(479,'채연희','0642705'),(480,'진형준','0642037'),(481,'연진우','0648765'),(482,'이예원','0649931'),(483,'송민주','0642384'),(484,'공윤환','0648212'),(485,'왕수련','0642234'),(486,'손상준','0643514'),(487,'인주비','0647648'),(488,'이민규','0647681'),(489,'심아윤','0644222'),(490,'유소연','0641732'),(491,'류대성','0641611'),(492,'김도현','0648476'),(493,'최다연','0642684'),(494,'오지영','0642767'),(495,'최대치','0647492'),(496,'임영택','0644415'),(497,'배하은','0644752'),(498,'최부성','0640649'),(499,'남정현','0643833'),(500,'박상일','0648242'),(501,'박주미','0641826'),(502,'최상진','0648889'),(503,'강진','0643163'),(504,'허은아','0640270'),(505,'김다영','0646234'),(506,'송민수','0643027'),(507,'정현정','0647898'),(508,'김수연','0644245'),(509,'김현규','0645904'),(510,'황수진','0641745'),(511,'김민우','0640458'),(512,'김민창','0647695'),(513,'구련아','0649449'),(514,'김정연','0641512'),(515,'이성재','0642272'),(516,'채예은','0648907'),(517,'정유환','0649476'),(518,'김윤하','0645025'),(519,'김영진','0646819'),(520,'한지희','0643639'),(521,'정해윤','0646024'),(522,'김경협','0645089'),(523,'양원동','0641477'),(524,'홍승기','0644182'),(525,'임재현','0643440'),(526,'한정섭','0642055'),(527,'김나윤','0643913'),(528,'민성재','0645733'),(529,'안채현','0643251'),(530,'김도연','0642952'),(531,'유민상','0649345'),(532,'최영운','0640090'),(533,'이은영','0647841'),(534,'김응주','0649678'),(535,'최광호','0647845'),(536,'위연주','0648099'),(537,'노하윤','0646136'),(538,'이선민','0643457'),(539,'우윤석','0644936'),(540,'형다은','0644683'),(541,'김수민','0647806'),(542,'김민채','0646914'),(543,'이호진','0647129'),(544,'김중우','0643943'),(545,'조용문','0649469'),(546,'이지윤','0647188'),(547,'이재경','0641801'),(548,'연승은','0647687'),(549,'주지환','0648851'),(550,'김경석','0640493'),(551,'김정혁','0647153'),(552,'김우찬','0646512'),(553,'강동석','0646999'),(554,'김은선','0643824'),(555,'김민준','0649698'),(556,'이아영','0641187'),(557,'배지환','0643455'),(558,'고주희','0641870'),(559,'윤혜구','0645368'),(560,'장의현','0640411'),(561,'조항준','0645036'),(562,'임형준','0649032'),(563,'한지윤','0642945'),(564,'이현우','0647392'),(565,'한슬기','0641693'),(566,'이정민','0645374'),(567,'김혜란','0643949'),(568,'정지영','0641720'),(569,'최혜린','0648126'),(570,'정구아','0647219'),(571,'서승원','0648368'),(572,'김경현','0642357'),(573,'윤창목','0649019'),(574,'배용한','0640592'),(575,'노태현','0642995'),(576,'문준호','0642165'),(577,'류완수','0640988'),(578,'남은성','0649878'),(579,'유혜승','0643768'),(580,'오나연','0645575'),(581,'김명섭','0645799'),(582,'김선민','0643338'),(583,'서우림','0645311'),(584,'이정훈','0644722'),(585,'진민규','0649708'),(586,'손영배','0648390'),(587,'김은송','0640214'),(588,'이호성','0641237'),(589,'박성호','0642158'),(590,'윤희영','0642981'),(591,'김민수','0646095'),(592,'유재룡','0646439'),(593,'최재진','0648199'),(594,'이건','0642013'),(595,'임지환','0642124'),(596,'김한주','0641051'),(597,'박정환','0645387'),(598,'황정준','0648703'),(599,'강민수','0646331'),(600,'조원빈','0648748'),(601,'이상우','0646633'),(602,'김보민','0642783'),(603,'김동규','0646342'),(604,'김태훈','0641259'),(605,'신지우','0643669'),(606,'윤홍림','0644038'),(607,'이호형','0641445'),(608,'주영호','0645783'),(609,'김혜지','0641284'),(610,'정홍진','0646423'),(611,'소정은','0641695'),(612,'이여진','0643844'),(613,'경혜안','0640851'),(614,'김민관','0649135'),(615,'이명주','0648372'),(616,'서형준','0644085'),(617,'송예진','0646554'),(618,'이윤기','0645104'),(619,'허건영','0645110'),(620,'허재석','0644434'),(621,'이재영','0647797'),(622,'윤혜원','0645238'),(623,'박찬혁','0640502'),(624,'이혜민','0649407'),(625,'김남훈','0642465'),(626,'김종범','0649294'),(627,'조은솔','0640484'),(628,'최정민','0642563'),(629,'박홍규','0647693'),(630,'조성한','0644888'),(631,'김태균','0644041'),(632,'송지호','0634333'),(633,'정찬','0649400'),(634,'이규은','0648940'),(635,'안영진','0641819'),(636,'정지민','0644162'),(637,'이언호','0645902'),(638,'장성우','0646032'),(639,'최일권','0644877'),(640,'장효정','0648620'),(641,'정언용','0644656'),(642,'권도혁','0642072'),(643,'이상원','0641883'),(644,'김나경','0646460'),(645,'이진행','0617351'),(646,'홍종규','0642182'),(647,'방의진','0645057'),(648,'강수현','0644097'),(649,'임건호','0648698'),(650,'형준혁','0645623'),(651,'이진희','0649121'),(652,'정산하','0647271'),(653,'이효림','0643986'),(654,'김한나','0649706'),(655,'송다솔','0641882'),(656,'최재훈','0647677'),(657,'맹동열','0649515'),(658,'김윤하','0642131'),(659,'이광진','0647466'),(660,'김영후','0643194'),(661,'이현식','0645810'),(662,'손수연','0646615'),(663,'박지후','0644634'),(664,'백승윤','0644395'),(665,'천민우','0643627'),(666,'이상훈','0648939'),(667,'임다훈','0649559'),(668,'김승현','0646025'),(669,'박소율','0647065'),(670,'한혜성','0642150'),(671,'승나연','0645032'),(672,'최하영','0642332'),(673,'김경한','0642108'),(674,'김은지','0646180'),(675,'강민구','0649859'),(676,'김기솔','0641311'),(677,'이재근','0642872'),(678,'박종선','0640669'),(679,'이정환','0645249'),(680,'이지우','0641370'),(681,'김한길','0646573'),(682,'전현민','0642494'),(683,'서민기','0646723'),(684,'최소원','0649746'),(685,'이인규','0644815'),(686,'이동준','0647396'),(687,'하윤주','0648605'),(688,'이수림','0643383'),(689,'오재우','0644838'),(690,'최윤수','0645752'),(691,'오윤택','0642567'),(692,'박세진','0643652'),(693,'김하영','0646069'),(694,'이정아','0640694'),(695,'이민정','0644303'),(696,'박건우','0643587'),(697,'김형준','0645186'),(698,'선민기','0639736'),(699,'황소현','0644693'),(700,'손형선','0645202'),(701,'최현규','0640166');
/*!40000 ALTER TABLE `user_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_tech_stack`
--

DROP TABLE IF EXISTS `user_has_tech_stack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_tech_stack` (
  `user_has_tech_stack_id` bigint NOT NULL AUTO_INCREMENT,
  `tech_stack_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`user_has_tech_stack_id`),
  KEY `fk_tech_stack_has_user_user1_idx` (`user_id`),
  KEY `fk_tech_stack_has_user_tech_stack_idx` (`tech_stack_id`),
  CONSTRAINT `fk_tech_stack_has_user_tech_stack` FOREIGN KEY (`tech_stack_id`) REFERENCES `tech_stack` (`tech_stack_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_tech_stack_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_tech_stack`
--

LOCK TABLES `user_has_tech_stack` WRITE;
/*!40000 ALTER TABLE `user_has_tech_stack` DISABLE KEYS */;
INSERT INTO `user_has_tech_stack` VALUES (35,5,5),(36,6,5);
/*!40000 ALTER TABLE `user_has_tech_stack` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 10:04:57
