/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : todo_score

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 20/02/2020 19:18:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for workday
-- ----------------------------
DROP TABLE IF EXISTS `workday`;
CREATE TABLE `workday`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `type` int(2) NULL DEFAULT NULL COMMENT '类型',
  `day` date NULL DEFAULT NULL,
  `is_workday` tinyint(1) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workday
-- ----------------------------
INSERT INTO `workday` VALUES (1, 0, '2020-01-01', 0, NULL);
INSERT INTO `workday` VALUES (2, 1, '2020-01-24', 0, NULL);
INSERT INTO `workday` VALUES (3, 1, '2020-01-25', 0, NULL);
INSERT INTO `workday` VALUES (4, 1, '2020-01-26', 0, NULL);
INSERT INTO `workday` VALUES (5, 1, '2020-01-27', 0, NULL);
INSERT INTO `workday` VALUES (6, 1, '2020-01-28', 0, NULL);
INSERT INTO `workday` VALUES (7, 1, '2020-01-29', 0, NULL);
INSERT INTO `workday` VALUES (8, 1, '2020-01-30', 0, NULL);
INSERT INTO `workday` VALUES (9, 1, '2020-01-31', 0, NULL);
INSERT INTO `workday` VALUES (10, 2, '2020-04-04', 0, NULL);
INSERT INTO `workday` VALUES (11, 2, '2020-04-05', 0, NULL);
INSERT INTO `workday` VALUES (12, 2, '2020-04-06', 0, NULL);
INSERT INTO `workday` VALUES (13, 3, '2020-04-26', 1, NULL);
INSERT INTO `workday` VALUES (14, 3, '2020-05-01', 0, NULL);
INSERT INTO `workday` VALUES (15, 3, '2020-05-02', 0, NULL);
INSERT INTO `workday` VALUES (16, 3, '2020-05-03', 0, NULL);
INSERT INTO `workday` VALUES (17, 3, '2020-05-04', 0, NULL);
INSERT INTO `workday` VALUES (18, 3, '2020-05-05', 0, NULL);
INSERT INTO `workday` VALUES (19, 3, '2020-05-09', 1, NULL);
INSERT INTO `workday` VALUES (20, 4, '2020-06-25', 0, NULL);
INSERT INTO `workday` VALUES (21, 4, '2020-06-26', 0, NULL);
INSERT INTO `workday` VALUES (22, 4, '2020-06-27', 0, NULL);
INSERT INTO `workday` VALUES (23, 4, '2020-06-28', 1, NULL);
INSERT INTO `workday` VALUES (24, 7, '2020-09-27', 1, NULL);
INSERT INTO `workday` VALUES (25, 7, '2020-10-01', 0, NULL);
INSERT INTO `workday` VALUES (26, 7, '2020-10-02', 0, NULL);
INSERT INTO `workday` VALUES (27, 7, '2020-10-03', 0, NULL);
INSERT INTO `workday` VALUES (28, 7, '2020-10-04', 0, NULL);
INSERT INTO `workday` VALUES (29, 7, '2020-10-05', 0, NULL);
INSERT INTO `workday` VALUES (30, 7, '2020-10-06', 0, NULL);
INSERT INTO `workday` VALUES (31, 7, '2020-10-07', 0, NULL);
INSERT INTO `workday` VALUES (32, 7, '2020-10-08', 0, NULL);
INSERT INTO `workday` VALUES (33, 7, '2020-10-10', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
