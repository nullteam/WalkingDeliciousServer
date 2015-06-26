/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : walking_delicious

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-06-26 09:51:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite` (
  `id` int(32) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` varchar(12) NOT NULL,
  `restaurant_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_w` (`user_id`),
  KEY `restaurant_w` (`restaurant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_favorite
-- ----------------------------
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000025', 'zhangmanman', 'baaaa23456');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000027', 'a111111', '5e53af1aa0b7ce464151c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000028', 'a111111', '5e53af1aa0b7ce4641521c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000029', 'a111111', '5e53a2f1aa0b7ce4641521c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000055', 'a111111', '07f3c8b5600e747a6240a9db');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000054', 'a111111', '4d35c85fa7a1b8d94254d731');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000041', 'a111111', '531d46957fcade6503ec01de');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000043', 'a111111', '5e53a2f1aa1c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000045', 'a111111', '5ea2f1aa1c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000046', 'a111111', '5ea1aa1c0dcid');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000047', 'a111111', '2c0bd6c542eadab343ab9a7d');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000052', 'a111111', 'c8e8f6332e66fa75aa0dd8ec');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000056', 'a111111', '0b92c31edf8103223617dc3b');

-- ----------------------------
-- Table structure for `t_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(12) NOT NULL,
  `content` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------
INSERT INTO `t_feedback` VALUES ('1', 'a111111', 'jbjbasbfkbsklbdflkbslkdgfbkergl');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(32) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` varchar(12) NOT NULL,
  `restaurant_id` char(32) NOT NULL,
  `order_num` int(32) NOT NULL,
  `order_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `restaurant_id` (`restaurant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('00000000000000000000000000000001', 'a111111', 'baaaa23456', '3', '2015-06-24 14:27:02');
INSERT INTO `t_order` VALUES ('00000000000000000000000000000002', 'a111111', 'baaaa23456', '3', '2015-06-24 14:37:25');
INSERT INTO `t_order` VALUES ('00000000000000000000000000000003', 'a111111', 'baaaa2343336', '3', '2015-06-24 14:37:48');
INSERT INTO `t_order` VALUES ('00000000000000000000000000000004', 'a111111', 'baaaa23456', '3', '2015-06-24 17:22:13');

-- ----------------------------
-- Table structure for `t_restaurant`
-- ----------------------------
DROP TABLE IF EXISTS `t_restaurant`;
CREATE TABLE `t_restaurant` (
  `id` char(32) NOT NULL COMMENT '数据项的id',
  `restaurant_name` varchar(32) DEFAULT NULL COMMENT '餐馆的名字',
  `restaurant_address` varchar(32) DEFAULT NULL,
  `restaurant_phone` varchar(32) DEFAULT NULL,
  `img_url` varchar(32) DEFAULT NULL,
  `price` double(32,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING HASH
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_restaurant
-- ----------------------------
INSERT INTO `t_restaurant` VALUES ('baaaa2343336', 'KFC', 'KFC', '18202720293', '234', null);
INSERT INTO `t_restaurant` VALUES ('baaaa23456', 'KFC', 'KFC', '18202720293', '234', null);
INSERT INTO `t_restaurant` VALUES ('5e53af1aa0b7ce464151c0dcid', 'KFC', 'KFC', '18202720293', '23', null);
INSERT INTO `t_restaurant` VALUES ('5e53af1aa0b7ce4641521c0dcid', 'KFC肯德基', 'KFC', '18202720293', 're', null);
INSERT INTO `t_restaurant` VALUES ('5e53a2f1aa0b7ce4641521c0dcid', '肯德基', 'KFC', '18202720293', 'er', null);
INSERT INTO `t_restaurant` VALUES ('07f3c8b5600e747a6240a9db', '颐和尚景酒店', '东湖新技术开发区光谷创业街66号(近华中科技大学文华学院)', '(027)59006666,(027)59723888', 'er', null);
INSERT INTO `t_restaurant` VALUES ('4d35c85fa7a1b8d94254d731', '华中科技大学东校区-学生一食堂', '华中科技大学内', '', 'er', null);
INSERT INTO `t_restaurant` VALUES ('531d46957fcade6503ec01de', '老村长私募菜(光谷创业街店)', '洪山区东湖新技术开发区光谷一路10号(新世界恒大华府)', '(027)87871877', 'er', null);
INSERT INTO `t_restaurant` VALUES ('c8e8f6332e66fa75aa0dd8ec', '简朴菜(佳园路店)', '佳园路（近珞瑜路华中科技大学东小门）', '(027)81881998', 'er', null);
INSERT INTO `t_restaurant` VALUES ('5e53a2f1aa1c0dcid', '肯德基', 'KFC', '18202720293', 'er', null);
INSERT INTO `t_restaurant` VALUES ('5ea2f1aa1c0dcid', 'KFC', 'KFC', '18202720293', 'er', null);
INSERT INTO `t_restaurant` VALUES ('5ea1aa1c0dcid', 'KFC', 'KFC', '18202720293', 'er', null);
INSERT INTO `t_restaurant` VALUES ('2c0bd6c542eadab343ab9a7d', '武汉东鑫酒店光谷店', '珞瑜东路197号', '(027)87801969', 'er', null);
INSERT INTO `t_restaurant` VALUES ('0b92c31edf8103223617dc3b', '湘鄂情(光谷店)', '武汉市洪山区关山大道111号光谷时代广场4楼(近关山街公交车站)', '(027)87597777', 'er', null);
INSERT INTO `t_restaurant` VALUES ('d219bb1dcdc5bc92f40e779a', '真功夫(光谷店)', '珞喻东路佳园路中商平价2楼', '13517213310', 're', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '表中数据的ID',
  `username` varchar(12) NOT NULL COMMENT '用户名',
  `password` varchar(12) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'a111111', 'a111111');
INSERT INTO `t_user` VALUES ('2', 'a111112', 'a111111');
INSERT INTO `t_user` VALUES ('3', 'a111113', 'a111111');
INSERT INTO `t_user` VALUES ('5', 'userboss', 'abc123456');
