/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-11-05 23:25:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorize_table
-- ----------------------------
DROP TABLE IF EXISTS `authorize_table`;
CREATE TABLE `authorize_table` (
  `uid` int(11) NOT NULL,
  `mid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of authorize_table
-- ----------------------------
INSERT INTO `authorize_table` VALUES ('25', '1');
INSERT INTO `authorize_table` VALUES ('25', '2');
INSERT INTO `authorize_table` VALUES ('25', '3');
INSERT INTO `authorize_table` VALUES ('25', '4');
INSERT INTO `authorize_table` VALUES ('25', '5');
INSERT INTO `authorize_table` VALUES ('25', '6');
INSERT INTO `authorize_table` VALUES ('25', '7');
INSERT INTO `authorize_table` VALUES ('25', '8');
