/*
Navicat MySQL Data Transfer

Source Server         : cyb
Source Server Version : 80030
Source Host           : localhost:3306
Source Database       : facedb

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2024-04-07 17:37:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '百度人脸库用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
  `user_group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联用户组表',
  `user_face_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户人脸地址',
  `user_isadmin` int DEFAULT '0' COMMENT '默认用户,0是用户，1是管理员',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_gruop_id` (`user_group_id`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`user_group_id`) REFERENCES `group_info` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '21', 'root', 'root', '创意炼金术士', null, '1', '15574036118');
INSERT INTO `user_info` VALUES ('2', '1', 'Alice', '1234', '星辰旅者', 'path/to/alice_face.jpg', '1', '1234567890');
INSERT INTO `user_info` VALUES ('3', '2', 'Bob', ' 1234 ', '星辰旅者', 'path/to/bob_face.jpg', '1', '0987654321');
INSERT INTO `user_info` VALUES ('4', '3', 'Charlie', ' 1234 ', '星辰旅者', 'path/to/charlie_face.jpg', '1', '1122334455');
INSERT INTO `user_info` VALUES ('5', '4', 'David', ' 1234 ', '星辰旅者', 'path/to/david_face.jpg', '1', '5566778899');
INSERT INTO `user_info` VALUES ('6', '5', 'Eve', ' 1234 ', '星辰旅者', 'path/to/eve_face.jpg', '1', '1011121314');
INSERT INTO `user_info` VALUES ('7', '6', 'Frank', ' 1234 ', '星辰旅者', 'path/to/frank_face.jpg', '1', '1516171819');
INSERT INTO `user_info` VALUES ('8', '7', 'Grace', ' 1234 ', '星辰旅者', 'path/to/grace_face.jpg', '1', '2021222324');
INSERT INTO `user_info` VALUES ('9', '8', 'Heidi', ' 1234 ', '星辰旅者', 'path/to/heidi_face.jpg', '1', '2526272830');
INSERT INTO `user_info` VALUES ('10', '9', 'Ivan', ' 1234 ', '星辰旅者', 'path/to/ivan_face.jpg', '1', '3031323334');
INSERT INTO `user_info` VALUES ('11', '10', 'Julia', ' 1234 ', '星辰旅者', 'path/to/julia_face.jpg', '1', '3536373839');
INSERT INTO `user_info` VALUES ('12', '11', 'Kevin', ' 1234 ', '星辰旅者', 'path/to/kevin_face.jpg', '1', '4041424344');
INSERT INTO `user_info` VALUES ('13', '12', 'Lisa', ' 1234 ', '星辰旅者', 'path/to/lisa_face.jpg', '1', '4546474848');
INSERT INTO `user_info` VALUES ('14', '13', 'Mike', ' 1234 ', '星辰旅者', 'path/to/mike_face.jpg', '1', '5051525354');
INSERT INTO `user_info` VALUES ('15', '14', 'Nina', ' 1234 ', '星辰旅者', 'path/to/nina_face.jpg', '1', '5556576778');
INSERT INTO `user_info` VALUES ('16', '15', 'Oscar', ' 1234 ', '星辰旅者', 'path/to/oscar_face.jpg', '1', '6061626364');
INSERT INTO `user_info` VALUES ('17', '16', 'Pete', ' 1234 ', '星辰旅者', 'path/to/pete_face.jpg', '1', '6466678899');
INSERT INTO `user_info` VALUES ('18', '17', 'Queenie', ' 1234 ', '星辰旅者', 'path/to/queenie_face.jpg', '1', '7071727374');
INSERT INTO `user_info` VALUES ('19', '18', 'Robert', ' 1234 ', '星辰旅者', 'path/to/robert_face.jpg', '1', '7575767775');
INSERT INTO `user_info` VALUES ('20', '19', 'Susan', ' 1234 ', '星辰旅者', 'path/to/susan_face.jpg', '1', '8081828384');
INSERT INTO `user_info` VALUES ('21', '20', 'Tom', ' 1234 ', '星辰旅者', 'path/to/tom_face.jpg', '1', '8586878886');
