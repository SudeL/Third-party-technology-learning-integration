/*
Navicat MySQL Data Transfer

Source Server         : cyb
Source Server Version : 80030
Source Host           : localhost:3306
Source Database       : facedb

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2024-04-07 17:26:42
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
INSERT INTO `group_info` VALUES ('1', '创意炼金术士');
INSERT INTO `group_info` VALUES ('2', '创意融合实验室');
INSERT INTO `group_info` VALUES ('3', '变革之翼');
INSERT INTO `group_info` VALUES ('4', '宇宙解码者');
INSERT INTO `group_info` VALUES ('5', '无限探索团');
INSERT INTO `group_info` VALUES ('6', '时空漫游者');
INSERT INTO `group_info` VALUES ('7', '星辰旅者');
INSERT INTO `group_info` VALUES ('8', '智慧之光');
INSERT INTO `group_info` VALUES ('9', '智慧火花');
INSERT INTO `group_info` VALUES ('10', '智能先驱');
INSERT INTO `group_info` VALUES ('11', '未来守护者');
INSERT INTO `group_info` VALUES ('12', '未来构造者');
INSERT INTO `group_info` VALUES ('13', '极限挑战者');
INSERT INTO `group_info` VALUES ('14', '梦想加速器');
INSERT INTO `group_info` VALUES ('15', '梦想编织者');
INSERT INTO `group_info` VALUES ('16', '灵感触发者');
INSERT INTO `group_info` VALUES ('17', '科技领航团');
INSERT INTO `group_info` VALUES ('18', '绿野奇兵');
INSERT INTO `group_info` VALUES ('19', '蓝图导航员');
INSERT INTO `group_info` VALUES ('20', '革新先锋');
