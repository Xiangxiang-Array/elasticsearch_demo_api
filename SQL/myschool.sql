/*
 Navicat Premium Data Transfer

 Source Server         : 本地  Mysql
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : myschool

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 29/08/2020 23:37:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(10) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123', '456');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('12342313443127654', '王五', '热爱运动是个大方的男孩子');
INSERT INTO `students` VALUES ('13213421341231231', '张三', '活泼开朗大方');
INSERT INTO `students` VALUES ('15432134213432131', '李四', '安静爱学习喜欢钻研');
INSERT INTO `students` VALUES ('56165164684684844', '小华', '人见人爱花见花开');
INSERT INTO `students` VALUES ('88712812731278312', '小芳', '美丽动人漂亮可爱');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subjectno` int(64) NOT NULL,
  `subjectname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classhour` int(255) NULL DEFAULT NULL,
  `gradeid` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`subjectno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, '一班', 3, 3);
INSERT INTO `subject` VALUES (2, '二班', 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
