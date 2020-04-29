/*
Navicat MySQL Data Transfer

Source Server         : interactive_class
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : interactive_class

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-04-29 23:14:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` varchar(36) NOT NULL,
  `class_name` varchar(60) DEFAULT NULL,
  `class_num` int(6) DEFAULT '0' COMMENT '班级人数',
  `class_introduce` varchar(255) DEFAULT NULL COMMENT '班级简介',
  `class_create_code` varchar(32) DEFAULT '' COMMENT '班级创建j标识,可以根据班级code 搜索',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除。1标识被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_class_code` (`class_create_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('18952731-e1db-4415-90ff-197ff048b6b4', '1502', '40', '1502班级简介。。。', '715596', '2020-04-25 23:48:35', '2020-04-25 23:48:35', '0');
INSERT INTO `class` VALUES ('9c68ae80-a913-435b-a434-722c97ad804f', '1501', '34', '1501班级简介。。。', '697042', '2020-04-25 23:48:17', '2020-04-25 23:48:17', '0');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_user
-- ----------------------------
INSERT INTO `class_user` VALUES ('662530fcbfba4127a4cb93028f651fd5', '18952731-e1db-4415-90ff-197ff048b6b4', null, '99b9a2f574d94a3491f8bec131d1c502', '', '2020-04-26 00:21:40', '2020-04-26 00:21:40');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(36) NOT NULL,
  `course_name` varchar(180) DEFAULT '' COMMENT '课程名称',
  `course_introduce` varchar(255) DEFAULT '' COMMENT '课程介绍',
  `course_time` varchar(50) DEFAULT '' COMMENT '课程时长',
  `course_score` varchar(80) DEFAULT '' COMMENT '课程学分',
  `class_id` varchar(255) NOT NULL DEFAULT '',
  `class_name` varchar(120) DEFAULT NULL,
  `created_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '课程创建者Id',
  `class_num` int(11) DEFAULT '0' COMMENT '上课人数',
  `begin_time` varchar(50) DEFAULT '' COMMENT '上课时间',
  `end_time` varchar(50) DEFAULT '' COMMENT '下课时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('a672f307-5e9e-40ab-a66c-3f51d0927294', 'java', 'java课程简介', '', '', '9c68ae80-a913-435b-a434-722c97ad804f', '1501', '547b89b94e0b4591a5cd72dd04fce14d', '0', '08:30:00', '10:00:00');
INSERT INTO `course` VALUES ('bbc7f50e-702a-4314-b70a-fd30e3d682eb', 'java', 'java课程简介', '', '', '18952731-e1db-4415-90ff-197ff048b6b4', '1502', '547b89b94e0b4591a5cd72dd04fce14d', '0', '08:30:00', '10:00:00');

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource` (
  `id` varchar(36) NOT NULL,
  `course_resource_name` varchar(180) DEFAULT NULL COMMENT '课程资源名称',
  `resource_des` varchar(255) DEFAULT '' COMMENT '课件资源说明',
  `resource_type` int(11) DEFAULT '1' COMMENT '课件类型1：ppt；2：视频',
  `resource_path` varchar(180) DEFAULT '' COMMENT '资源存储路径',
  `user_id` varchar(36) DEFAULT '' COMMENT '资源作者id',
  `course_id` varchar(36) DEFAULT NULL,
  `class_id` varchar(36) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_resource
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` varchar(36) NOT NULL,
  `notice_title` varchar(180) DEFAULT '' COMMENT '通知、公告标题',
  `notice_content` varchar(255) DEFAULT NULL COMMENT '通知公告内容',
  `publish_time` varchar(50) DEFAULT NULL,
  `publish_user_id` varchar(36) DEFAULT NULL,
  `class_id` varchar(36) DEFAULT NULL,
  `course_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('294771a1-a7c6-41c4-86f9-c4b88cde41a8', '公告标题1', '公告内容1', '2020-04-27 00:35:18', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('41dce5e6-a790-4d97-a0b6-18d449879c90', '12233', '444558', '2020-04-28 00:05:09', '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('4e559b3f-806b-4e0e-af0a-669ff8ef9ea8', '12233', '444558', '2020-04-28 00:06:01', '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('72daf96f-bd52-4afd-b1f5-87f44fe05e2b', '公告标题3', '公告内容3', '2020-04-27 00:35:38', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('af63d126-6359-46c8-8356-a532a1f725fb', '77777', '88888。。。。。。。。。。。。。。。。。。。。。。。。。。。', '2020-04-28 00:10:29', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('dea363de-e705-46b0-888c-8532f7c8d198', '公告标题2', '公告内容2', '2020-04-27 00:35:29', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('dfb9c3cf-4d1a-4e4f-86b8-7b978d63a4bc', '公告标题5', '公告内容5.。。。。', '2020-04-27 16:17:04', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `notice` VALUES ('f1d5682d-64f0-4abd-81ba-5924d22ba03c', '44', '444444.。。。', '2020-04-27 00:37:05', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` varchar(36) NOT NULL,
  `paper_name` varchar(180) DEFAULT NULL,
  `paper_score` varchar(120) DEFAULT NULL,
  `paper_time` varchar(50) DEFAULT '' COMMENT '答题时长',
  `answer_begin_time` varchar(50) DEFAULT NULL,
  `answer_end_time` varchar(50) DEFAULT NULL,
  `publish_user_id` varchar(36) DEFAULT '' COMMENT '试卷发布者id',
  `publish_time` varchar(50) DEFAULT NULL,
  `answer_user_id` varchar(36) DEFAULT '' COMMENT '试卷答题用户id',
  `class_id` varchar(36) DEFAULT '' COMMENT '试卷答题班级班级',
  `course_id` varchar(36) DEFAULT NULL,
  `student_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('21c77052323142f59a21d5926b157532', '3月份月考', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', null, '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb', null);
INSERT INTO `paper` VALUES ('4c0a4c72eb4c48f09eae24e36562690d', '试卷1标题', null, '90分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', null, '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb', null);
INSERT INTO `paper` VALUES ('c68ce995780c4c75a8019d2de9d26c99', '4月份月考', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', null, '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb', null);
INSERT INTO `paper` VALUES ('cf8cf6db247f44e4963d147652e87277', '试卷3标题', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', null, '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb', null);
INSERT INTO `paper` VALUES ('fb8e93ed66524df68c5b1dc5a4ce2adc', '试卷2标题', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', null, '', '18952731-e1db-4415-90ff-197ff048b6b4', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb', null);

-- ----------------------------
-- Table structure for paper_question
-- ----------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question` (
  `id` int(36) NOT NULL,
  `question_name` varchar(255) DEFAULT '' COMMENT '问题名称',
  `question_type` tinyint(2) DEFAULT '1' COMMENT '试卷题目类型。1：选择题；2：填空题',
  `question_answer` varchar(255) DEFAULT '' COMMENT '问题答案',
  `question_num` varchar(20) DEFAULT '' COMMENT '问题编号',
  `score` varchar(20) DEFAULT NULL,
  `paper_id` varchar(36) DEFAULT NULL,
  `order` smallint(6) DEFAULT '1' COMMENT '试题排序顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_question
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
  `id` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别，0：未知；1：男性；2：女性。',
  `account` varchar(255) DEFAULT '' COMMENT '登录账号',
  `password` varchar(255) DEFAULT NULL,
  `user_num` varchar(32) DEFAULT '' COMMENT '学号',
  `user_avatar` varchar(60) DEFAULT '' COMMENT '用户图像路径',
  `role_id` int(11) DEFAULT '0' COMMENT '角色id',
  `login_ip` varchar(255) DEFAULT '' COMMENT '登录ip',
  `login_time` varchar(50) DEFAULT '' COMMENT '登录时间',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '逻辑删除。1被删除',
  `is_used` tinyint(1) DEFAULT '1' COMMENT '是否禁用。0账号被禁用',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1479574d4264403ca52384e87f6c1659', '123456', '0', '123456', '123456', '123456', '', '1', '192.168.0.104', '', '2020-04-25 23:47:16', '2020-04-25 23:47:16', '0', '1');
INSERT INTO `user` VALUES ('99b9a2f574d94a3491f8bec131d1c502', '222', '0', '222', '222', '222', '', '1', '192.168.0.104', '', '2020-04-25 23:46:52', '2020-04-25 23:46:52', '0', '1');

-- ----------------------------
-- Table structure for user_sign
-- ----------------------------
DROP TABLE IF EXISTS `user_sign`;
CREATE TABLE `user_sign` (
  `id` varchar(36) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `user_id` varchar(36) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `sign_time` varchar(50) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `sign_place` varchar(255) NOT NULL,
  `sign_in` tinyint(1) NOT NULL DEFAULT '1' COMMENT '签到。1表示签到，0表示签退',
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_sign
-- ----------------------------
