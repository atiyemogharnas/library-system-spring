/*
 Navicat Premium Dump SQL

 Source Server         : library
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 15/12/2024 15:28:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('BANNED','BORROWED','EXIST') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'EXIST',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'test1', 'ali', 'BORROWED');
INSERT INTO `book` VALUES (2, 'test2', 'amir', 'EXIST');
INSERT INTO `book` VALUES (3, 'test3', 'ahmad', 'BORROWED');
INSERT INTO `book` VALUES (4, 'test4', 'arash', 'EXIST');
INSERT INTO `book` VALUES (5, 'test5', 'nahid', 'EXIST');
INSERT INTO `book` VALUES (6, 'test6', 'majid', 'EXIST');
INSERT INTO `book` VALUES (7, 'test7', 'aria', 'EXIST');
INSERT INTO `book` VALUES (8, 'test8', 'farhad', 'BORROWED');
INSERT INTO `book` VALUES (9, 'test9', 'raha', 'EXIST');
INSERT INTO `book` VALUES (10, 'test10', 'armin', 'BORROWED');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `borrow_id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `returnDate` datetime NULL DEFAULT NULL,
  `loanDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`borrow_id`) USING BTREE,
  INDEX `book_id`(`book_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (1, 1, 1, '2024-12-10 10:14:02', '2024-12-04 10:14:12');
INSERT INTO `borrow` VALUES (2, 2, 1, '2024-11-15 10:14:30', '2024-11-08 10:14:36');
INSERT INTO `borrow` VALUES (3, 3, 1, '2024-12-17 10:15:03', '2024-11-25 10:15:11');
INSERT INTO `borrow` VALUES (4, 3, 2, '2024-07-01 10:15:25', '2024-06-18 10:15:43');
INSERT INTO `borrow` VALUES (5, 1, 2, '2024-11-28 10:16:36', '2024-11-24 10:16:41');
INSERT INTO `borrow` VALUES (6, 8, 2, '2024-12-14 09:58:54', '2024-12-11 10:20:20');
INSERT INTO `borrow` VALUES (7, 10, 1, '2025-01-04 10:20:31', '2024-12-11 10:20:38');
INSERT INTO `borrow` VALUES (8, 5, 1, '2024-12-11 15:11:31', '2024-11-20 10:14:02');
INSERT INTO `borrow` VALUES (10, 5, 2, '2024-12-11 15:11:31', '2024-11-01 10:14:02');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'atie', 'a@gmail.com');
INSERT INTO `user` VALUES (2, 'nafise', 'n@gmail.com');

-- ----------------------------
-- View structure for borrowbook
-- ----------------------------
DROP VIEW IF EXISTS `borrowbook`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `borrowbook` AS select `book`.`book_id` AS `book_id`,`book`.`title` AS `title`,`book`.`author` AS `author`,`borrow`.`returnDate` AS `returnDate`,`borrow`.`loanDate` AS `loanDate` from (`borrow` join `book` on((`borrow`.`book_id` = `book`.`book_id`))) where (`book`.`status` = 'BORROWED');

-- ----------------------------
-- Triggers structure for table book
-- ----------------------------
DROP TRIGGER IF EXISTS `trigger_updateDate`;
delimiter ;;
CREATE TRIGGER `trigger_updateDate` AFTER UPDATE ON `book` FOR EACH ROW BEGIN  
    UPDATE borrow 
    SET returnDate = CURRENT_TIMESTAMP
    WHERE book_id = NEW.book_id ;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
