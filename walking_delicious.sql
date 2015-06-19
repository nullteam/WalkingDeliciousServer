/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : walking_delicious

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-06-19 13:58:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(32) unsigned NOT NULL,
  `restaurant_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_user_id_fk` (`user_id`),
  KEY `f_restaurant_id_fk` (`restaurant_id`),
  CONSTRAINT `f_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_restaurant_id_fk` FOREIGN KEY (`restaurant_id`) REFERENCES `t_restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_favorite
-- ----------------------------
INSERT INTO `t_favorite` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据项的id',
  `user_id` int(32) unsigned NOT NULL COMMENT '用户的id关联到t_user表中',
  `restaurant_id` char(32) NOT NULL COMMENT '餐馆的id关联到t_restaurant表中',
  `order_num` int(32) unsigned NOT NULL COMMENT '订单的数量',
  `order_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '订单的时间，根据数据库时间更新',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`restaurant_id`) REFERENCES `t_restaurant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

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

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT '表中数据的ID',
  `username` varchar(12) NOT NULL COMMENT '用户名',
  `password` varchar(12) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'a111111', 'a111111');
INSERT INTO `t_user` VALUES ('2', 'a111112', 'a111111');
INSERT INTO `t_user` VALUES ('3', 'a111113', 'a111111');
INSERT INTO `t_user` VALUES ('5', 'userboss', 'abc123456');
