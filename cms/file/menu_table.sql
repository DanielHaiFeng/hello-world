/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-11-05 23:25:22
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `menu_table` VALUES ('6', '用户管理', 'icon-ok', '2', 'pages/user.html', '1');
INSERT INTO `menu_table` VALUES ('7', '菜单管理', 'icon-ok', '2', 'pages/menu.html', '1');
INSERT INTO `menu_table` VALUES ('8', '权限管理', 'icon-ok', '2', 'pages/authorize.html', '1');
