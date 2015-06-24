/*
Navicat MySQL Data Transfer

Source Server         : walking_delicious
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : walking_delicious

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2015-06-24 15:07:25
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
  KEY `restaurant_w` (`restaurant_id`),
  CONSTRAINT `restaurant_w` FOREIGN KEY (`restaurant_id`) REFERENCES `t_restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_w` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_favorite
-- ----------------------------
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000001', 'a111111', '1');
INSERT INTO `t_favorite` VALUES ('00000000000000000000000000000024', 'a111111', '4');

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
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`restaurant_id`) REFERENCES `t_restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('00000000000000000000000000000001', 'a111111', 'baaaa23456', '3', '2015-06-24 14:27:02');
INSERT INTO `t_order` VALUES ('00000000000000000000000000000002', 'a111111', 'baaaa23456', '3', '2015-06-24 14:37:25');
INSERT INTO `t_order` VALUES ('00000000000000000000000000000003', 'a111111', 'baaaa2343336', '3', '2015-06-24 14:37:48');

-- ----------------------------
-- Table structure for `t_restaurant`
-- ----------------------------
DROP TABLE IF EXISTS `t_restaurant`;
CREATE TABLE `t_restaurant` (
  `id` char(32) NOT NULL COMMENT '数据项的id',
  `restaurant_name` varchar(32) DEFAULT NULL COMMENT '餐馆的名字',
  `restaurant_address` varchar(32) DEFAULT NULL,
  `restaurant_phone` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_restaurant
-- ----------------------------
INSERT INTO `t_restaurant` VALUES ('', 'dfgdfg', 'jgj', 'dgwer');
INSERT INTO `t_restaurant` VALUES ('1', 'sfd', 'erger', 'dfgdfg');
INSERT INTO `t_restaurant` VALUES ('4', 'avcx', 'aaaaaa', '123333333333');
INSERT INTO `t_restaurant` VALUES ('baaaa2343336', 'KFC', 'KFC', '18202720293');
INSERT INTO `t_restaurant` VALUES ('baaaa23456', 'KFC', 'KFC', '18202720293');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'a111111', 'a111111');
INSERT INTO `t_user` VALUES ('2', 'a111112', 'a111111');
INSERT INTO `t_user` VALUES ('3', 'a111113', 'a111111');
INSERT INTO `t_user` VALUES ('5', 'userboss', 'abc123456');
