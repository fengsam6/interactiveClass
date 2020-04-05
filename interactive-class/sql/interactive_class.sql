/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : interactive_class

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-04-05 11:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(60) DEFAULT NULL,
  `class_num` int(6) DEFAULT '0' COMMENT '班级人数',
  `class_introduce` varchar(255) DEFAULT NULL COMMENT '班级简介',
  `class_create_code` varchar(32) DEFAULT '' COMMENT '班级创建j标识,可以根据班级code 搜索',
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除。1标识被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_class_code` (`class_create_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user` (
  `id` int(11) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `member_id` int(11) DEFAULT '0' COMMENT '班级成员id',
  `created_user_id` int(11) DEFAULT NULL COMMENT '班级创建者Id',
  `creat_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_user
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT '' COMMENT '角色名称',
  `desc` varchar(255) DEFAULT '' COMMENT '角色描述',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '用于逻辑删除，1标识数据被删除',
  `is_used` tinyint(255) DEFAULT '1' COMMENT '是否使用。1正在使用，0禁止使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '学生', '0', '1');
INSERT INTO `role` VALUES ('2', '老师', '拥有老师权限', '0', '1');
INSERT INTO `role` VALUES ('3', '管理员', 'admin', '0', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别，0：未知；1：男性；2：女性。',
  `account` varchar(255) DEFAULT '' COMMENT '登录账号',
  `password` varchar(255) DEFAULT NULL,
  `user_num` varchar(32) DEFAULT '' COMMENT '学号',
  `user_avater` varchar(60) DEFAULT '' COMMENT '用户图像路径',
  `role_id` int(11) DEFAULT '0' COMMENT '角色id',
  `login_ip` varchar(255) DEFAULT '' COMMENT '登录ip',
  `login_time` varchar(50) DEFAULT '' COMMENT '登录时间',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除。1被删除',
  `is_used` tinyint(1) DEFAULT '1' COMMENT '是否禁用。0账号被禁用',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_sign
-- ----------------------------
DROP TABLE IF EXISTS `user_sign`;
CREATE TABLE `user_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `sign_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sign_place` varchar(255) DEFAULT NULL,
  `sign_in` tinyint(4) DEFAULT '1' COMMENT '签到',
  `sign_out` tinyint(4) DEFAULT '0' COMMENT '签退',
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_sign
-- ----------------------------
