/*
 Navicat Premium Dump SQL

 Source Server         : library
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : springlibrary

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 15/12/2024 14:49:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `book_id` int NULL DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `return_date` datetime(6) NULL DEFAULT NULL,
  `loan_date` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKigyo48dqqu73u5g7213mmt4qg`(`book_id` ASC) USING BTREE,
  INDEX `FKtlx8cbafjlyp2hgfog0bdmni3`(`user_id` ASC) USING BTREE,
  CONSTRAINT `FKigyo48dqqu73u5g7213mmt4qg` FOREIGN KEY (`book_id`) REFERENCES `library_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtlx8cbafjlyp2hgfog0bdmni3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (3, 1, 1, NULL, '2024-12-15 10:14:02.000000');
INSERT INTO `borrow` VALUES (4, 2, 1, NULL, '2024-12-15 10:14:02.000000');

-- ----------------------------
-- Table structure for library_item
-- ----------------------------
DROP TABLE IF EXISTS `library_item`;
CREATE TABLE `library_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_of_book` int NULL DEFAULT NULL,
  `reference_type` tinyint NULL DEFAULT NULL,
  `year` datetime(6) NULL DEFAULT NULL,
  `dtype` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `genre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `university` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` enum('BANNED','BORROWED','EXIST') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` enum('BOOK','MAGAZINE','REFERENCE','THESIS') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `library_item_chk_1` CHECK (`reference_type` between 0 and 1)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of library_item
-- ----------------------------
INSERT INTO `library_item` VALUES (1, NULL, NULL, '2001-01-10 10:14:02.000000', 'Thesis', 'farid', NULL, 'testThesis', 'tehran', NULL, 'THESIS');
INSERT INTO `library_item` VALUES (2, NULL, NULL, '2020-01-10 10:14:02.000000', 'Magazine', 'ahmad', 'adabi', 'testMagazine', NULL, NULL, 'MAGAZINE');
INSERT INTO `library_item` VALUES (3, 0, NULL, '2020-12-10 10:14:02.000000', 'Book', 'amir', NULL, 'test2', NULL, 'BORROWED', 'BOOK');
INSERT INTO `library_item` VALUES (4, 0, NULL, '1920-12-10 10:14:02.000000', 'Book', 'arash', NULL, 'test3', NULL, 'BORROWED', 'BOOK');
INSERT INTO `library_item` VALUES (5, 0, NULL, '2024-12-10 10:14:02.000000', 'Book', 'ali', NULL, 'test1', NULL, 'EXIST', 'BOOK');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0020826299');
INSERT INTO `user` VALUES (2, '0037101473');
INSERT INTO `user` VALUES (3, '0070543266');

SET FOREIGN_KEY_CHECKS = 1;
