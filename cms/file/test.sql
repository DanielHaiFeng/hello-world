/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-01-07 12:23:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `menu_table`
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_table
-- ----------------------------
INSERT INTO menu_table VALUES ('1', '系统管理', 'icon-ok', '1', '', '0');
INSERT INTO menu_table VALUES ('2', '员工管理', 'icon-ok', '1', null, '0');
INSERT INTO menu_table VALUES ('3', '部门管理', 'icon-ok', '1', null, '0');
INSERT INTO menu_table VALUES ('4', '财务管理', 'icon-ok', '1', null, '0');
INSERT INTO menu_table VALUES ('5', '商城管理', 'icon-ok', '1', null, '0');
INSERT INTO menu_table VALUES ('6', '用户管理', 'icon-ok', '2', 'pages/user.html', '1');
INSERT INTO menu_table VALUES ('7', '菜单管理', 'icon-ok', '2', 'pages/menu.html', '1');

-- ----------------------------
-- Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) NOT NULL,
  `upwd` varchar(255) NOT NULL,
  `cellphone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO user_table VALUES ('1', 'admin1', 'admin', '13502135470', '陕西省西安市丈八四路0号');
INSERT INTO user_table VALUES ('2', 'admin2', 'dangt1234', '13502135471', '陕西省西安市丈八四路1号');
INSERT INTO user_table VALUES ('3', 'admin3', 'admin', '13502135472', '陕西省西安市丈八四路2号');
INSERT INTO user_table VALUES ('4', 'admin4', 'admin', '13502135473', '陕西省西安市丈八四路3号');
INSERT INTO user_table VALUES ('5', 'admin5', 'admin', '13502135474', '陕西省西安市丈八四路4号');
INSERT INTO user_table VALUES ('6', 'admin6', 'admin', '13502135475', '陕西省西安市丈八四路5号');
INSERT INTO user_table VALUES ('7', 'admin7', 'admin', '13502135476', '陕西省西安市丈八四路6号');
INSERT INTO user_table VALUES ('8', 'admin8', 'admin', '13502135477', '陕西省西安市丈八四路7号');
INSERT INTO user_table VALUES ('9', 'admin9', 'admin', '13502135478', '陕西省西安市丈八四路8号');
INSERT INTO user_table VALUES ('10', 'admin10', 'admin', '13502135479', '陕西省西安市丈八四路9号');
INSERT INTO user_table VALUES ('24', 'dangt', 'dangt1324', '13501048023', '陕西省西安市科技三路8号');
