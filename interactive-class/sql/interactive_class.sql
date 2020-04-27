/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : interactive_class

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-04-27 09:08:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user` (
  `id` varchar(36) NOT NULL,
  `class_id` varchar(255) NOT NULL DEFAULT '',
  `class_name` varchar(200) DEFAULT NULL,
  `member_id` varchar(36) NOT NULL DEFAULT '' COMMENT '班级成员id',
  `created_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '班级创建者Id',
  `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_class_user_id` (`class_id`,`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_user
-- ----------------------------
INSERT INTO `class_user` VALUES ('ba3cda3615e34b398b43402cda9bf6b7', 'fccb77bc-82ef-4ca7-a494-9d781cbe07f2', null, '547b89b94e0b4591a5cd72dd04fce14d', '547b89b94e0b4591a5cd72dd04fce14d', '2020-04-27 08:36:24', '2020-04-27 08:36:24');
