/*
Navicat MySQL Data Transfer

Source Server         : mysql_root
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2020-01-03 14:00:23
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

-- ----------------------------
-- Table structure for menu_table
-- ----------------------------
DROP TABLE IF EXISTS `menu_table`;
CREATE TABLE `menu_table` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `mname` varchar(50) NOT NULL,
  `micon` varchar(20) NOT NULL,
  `mlevel` int(10) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(10) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_table
-- ----------------------------
INSERT INTO `menu_table` VALUES ('1', '系统管理', 'icon-ok', '1', '', '0');
INSERT INTO `menu_table` VALUES ('2', '员工管理', 'icon-ok', '1', null, '0');
INSERT INTO `menu_table` VALUES ('3', '部门管理', 'icon-ok', '1', null, '0');
INSERT INTO `menu_table` VALUES ('4', '财务管理', 'icon-ok', '1', null, '0');
INSERT INTO `menu_table` VALUES ('5', '商城管理', 'icon-ok', '1', null, '0');
INSERT INTO `menu_table` VALUES ('6', '用户管理', 'icon-ok', '2', '/user', '1');
INSERT INTO `menu_table` VALUES ('7', '菜单管理', 'icon-ok', '2', '/menu', '1');
INSERT INTO `menu_table` VALUES ('8', '权限管理', 'icon-ok', '2', '/authorize', '1');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `upwd` varchar(255) NOT NULL,
  `cellphone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unique_login_name` (`loginName`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('1', 'admin1', 'admin1', 'MTIzNDU2', '13502135470', '陕西省西安市丈八四路0号', null);
INSERT INTO `user_table` VALUES ('2', 'admin2', 'admin2', 'MTIzNDU2', '13502135471', '陕西省西安市丈八四路1号', null);
INSERT INTO `user_table` VALUES ('3', 'admin3', 'admin3', 'MTIzNDU2', '13502135472', '陕西省西安市丈八四路2号', null);
INSERT INTO `user_table` VALUES ('4', 'admin4', 'admin4', 'MTIzNDU2', '13502135473', '陕西省西安市丈八四路3号', null);
INSERT INTO `user_table` VALUES ('5', 'admin5', 'admin5', 'MTIzNDU2', '13502135474', '陕西省西安市丈八四路4号', null);
INSERT INTO `user_table` VALUES ('6', 'admin6', 'admin6', 'MTIzNDU2', '13502135475', '陕西省西安市丈八四路5号', null);
INSERT INTO `user_table` VALUES ('7', 'admin7', 'admin7', 'MTIzNDU2', '13502135476', '陕西省西安市丈八四路6号', 'gaga');
INSERT INTO `user_table` VALUES ('8', 'admin8', 'admin8', 'MTIzNDU2', '13502135477', '陕西省西安市丈八四路7号', null);
INSERT INTO `user_table` VALUES ('9', 'admin9', 'admin9', 'MTIzNDU2', '13502135478', '陕西省西安市丈八四路8号', null);
INSERT INTO `user_table` VALUES ('10', 'admin10', 'admin10', 'MTIzNDU2', '13502135479', '陕西省西安市丈八四路9号', null);
INSERT INTO `user_table` VALUES ('24', 'dangt', 'dangt', 'MTIzNDU2', '13501048023', '陕西省西安市科技三路8号', null);
INSERT INTO `user_table` VALUES ('25', 'super', 'super', 'c3VwZXIx', '13501048023', '陕西省西安市科技三路', null);
INSERT INTO `user_table` VALUES ('26', 'connor', 'connor', 'MTIzNDU2', '13109876543', '陕西省西安市未央区', 'ceshi');
