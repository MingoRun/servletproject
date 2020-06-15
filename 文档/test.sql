/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 12/06/2020 16:01:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sage` datetime(0) NULL DEFAULT NULL,
  `Ssex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('01', '赵雷', '1990-01-01 00:00:00', '男');
INSERT INTO `student` VALUES ('02', '钱电', '1990-12-21 00:00:00', '男');
INSERT INTO `student` VALUES ('03', '孙风', '1990-05-20 00:00:00', '男');
INSERT INTO `student` VALUES ('04', '李云', '1990-08-06 00:00:00', '男');
INSERT INTO `student` VALUES ('05', '周梅', '1991-12-01 00:00:00', '女');
INSERT INTO `student` VALUES ('06', '吴兰', '1992-03-01 00:00:00', '女');
INSERT INTO `student` VALUES ('07', '郑竹', '1989-07-01 00:00:00', '女');
INSERT INTO `student` VALUES ('08', '王菊', '1990-01-20 00:00:00', '女');
INSERT INTO `student` VALUES ('09', '胡华', '1990-01-01 00:00:00', '男');
INSERT INTO `student` VALUES ('10', '王明', '1999-05-05 00:00:00', '男');
INSERT INTO `student` VALUES ('11', '李倩', '2000-02-03 00:00:00', '女');
INSERT INTO `student` VALUES ('12', '张杰', '1993-06-08 00:00:00', '男');
INSERT INTO `student` VALUES ('13', '吴磊', '2020-06-11 00:00:00', '男');
INSERT INTO `student` VALUES ('14', '李洁', '2020-06-11 00:00:00', '女');
INSERT INTO `student` VALUES ('15', '王五', '2020-06-11 00:00:00', '男');
INSERT INTO `student` VALUES ('16', '周六', '2020-06-11 00:00:00', '女');
INSERT INTO `student` VALUES ('17', '林云', '2020-06-12 00:00:00', '女');

SET FOREIGN_KEY_CHECKS = 1;
