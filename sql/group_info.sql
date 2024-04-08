/*
Navicat MySQL Data Transfer

Source Server         : cyb
Source Server Version : 80030
Source Host           : localhost:3306
Source Database       : facedb

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2024-04-08 13:15:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组名字',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of group_info
-- ----------------------------
INSERT INTO `group_info` VALUES ('1', 'demo1');
INSERT INTO `group_info` VALUES ('2', 'demo2');
INSERT INTO `group_info` VALUES ('3', 'demo3');
INSERT INTO `group_info` VALUES ('4', 'demo4');
INSERT INTO `group_info` VALUES ('5', 'demo5');
INSERT INTO `group_info` VALUES ('6', 'demo6');
