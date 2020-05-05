/*
Navicat MySQL Data Transfer

Source Server         : interactive_class
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : interactive_class

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-05-05 23:47:14
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
INSERT INTO `class` VALUES ('64f5fa91-8c22-43e6-a63c-6184d84711ad', '1506', '34', '777777', '940221', '2020-05-04 20:22:20', '2020-05-04 20:22:20', '0');
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
INSERT INTO `class_user` VALUES ('47fdedb98e5c4a4aaee08689983c7176', '64f5fa91-8c22-43e6-a63c-6184d84711ad', null, '99b9a2f574d94a3491f8bec131d1c502', '99b9a2f574d94a3491f8bec131d1c502', '2020-05-04 20:22:20', '2020-05-04 20:22:20');
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
INSERT INTO `course` VALUES ('0de9e3b0-e31b-47a0-83b1-43cf30cbdf37', 'java课程', 'java课程简介', '', '20', '9c68ae80-a913-435b-a434-722c97ad804f', '1501', '99b9a2f574d94a3491f8bec131d1c502', '0', '08:20:20', '10:10:11');
INSERT INTO `course` VALUES ('799c8d7e-bc3a-41f5-9d49-d256f075058d', 'java课程', 'java课程简介', '', '8', '18952731-e1db-4415-90ff-197ff048b6b4', '1502', '99b9a2f574d94a3491f8bec131d1c502', '0', '08:20:20', '10:10:11');

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
INSERT INTO `course_resource` VALUES ('b4ce020f03724614a78ddbd0b8953e92', '演示文稿1.pptx', 'ppt、word资源', '1', '202005/759264_演示文稿1.pptx', '99b9a2f574d94a3491f8bec131d1c502', '0de9e3b0-e31b-47a0-83b1-43cf30cbdf37', '9c68ae80-a913-435b-a434-722c97ad804f', '2020-05-04 00:52:49');
INSERT INTO `course_resource` VALUES ('c1e7365e69e24c0490609c52b11e39f7', '演示文稿1.pptx', 'ppt、word资源', '1', '202005/832372_演示文稿1.pptx', '99b9a2f574d94a3491f8bec131d1c502', '799c8d7e-bc3a-41f5-9d49-d256f075058d', '18952731-e1db-4415-90ff-197ff048b6b4', '2020-05-04 01:43:54');

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
INSERT INTO `notice` VALUES ('2a4111d4-f809-4526-b569-068deef1d453', '公告1标题', '公告1内容。。。。', '2020-05-03 21:35:25', '99b9a2f574d94a3491f8bec131d1c502', '18952731-e1db-4415-90ff-197ff048b6b4', '799c8d7e-bc3a-41f5-9d49-d256f075058d');
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
INSERT INTO `paper` VALUES ('4ee2dddfa0a7483a8d573595cd903ea8', '2020年1月测试', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', '2020-31-04 08:31:52', '', '18952731-e1db-4415-90ff-197ff048b6b4', '799c8d7e-bc3a-41f5-9d49-d256f075058d', null);
INSERT INTO `paper` VALUES ('7ec8dac0c545473a92373ed31a24922a', '2019年3月份月考', null, '120分钟', null, null, '99b9a2f574d94a3491f8bec131d1c502', '2020-58-03 10:58:45', '', '18952731-e1db-4415-90ff-197ff048b6b4', '799c8d7e-bc3a-41f5-9d49-d256f075058d', null);

-- ----------------------------
-- Table structure for paper_question
-- ----------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question` (
  `id` varchar(36) NOT NULL,
  `question_name` varchar(255) DEFAULT '' COMMENT '问题名称',
  `question_type` tinyint(2) DEFAULT '1' COMMENT '试卷题目类型。1：选择题；2：填空题',
  `question_answer` varchar(255) DEFAULT '' COMMENT '问题答案',
  `question_num` varchar(20) DEFAULT '' COMMENT '问题编号',
  `score` varchar(20) DEFAULT NULL,
  `paper_id` varchar(36) DEFAULT NULL,
  `order` smallint(6) DEFAULT '1' COMMENT '试题排序顺序',
  `question_a` varchar(255) DEFAULT NULL,
  `question_b` varchar(255) DEFAULT NULL,
  `question_c` varchar(255) DEFAULT NULL,
  `question_d` varchar(255) DEFAULT NULL,
  `question_self_answer` varchar(255) DEFAULT NULL COMMENT '填空题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper_question
-- ----------------------------
INSERT INTO `paper_question` VALUES ('378c50da29b54cb8bcbde9638f2c4727', 'char类型用来表示在Unicode编码表中的字符，长度是_____个字节。', '2', '', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', null, null, null, null, null);
INSERT INTO `paper_question` VALUES ('4512d4dc70684364bd853edf0855a2e2', '安装JDK后，为了告诉计算机javac.exe和java.exe等执行文件的位置，需要配置的环境变量是：____', '2', '', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', null, null, null, null, null);
INSERT INTO `paper_question` VALUES ('4584b94932db4d59a5a21bf29f430a19', '哪一个对象可以用于获得浏览器发送的请求', '1', 'B', '', '5', '7ec8dac0c545473a92373ed31a24922a', '1', 'HttpServletResponse', 'HttpServletRequest', 'HttpServlet', 'Http', null);
INSERT INTO `paper_question` VALUES ('4c4804ba91a44e9cad69b22510beeb15', '假设web应用的文档根目录为MyApp，那么可以从哪里找到database.jar文件', '1', 'D', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', 'MyApp目录下', 'MyApp\\images目录下', 'MyApp\\WEB-INF目录下', 'MyApp\\WEB-INF\\lib目录下', null);
INSERT INTO `paper_question` VALUES ('89e5aa9b10cd487f8257a453ee8573e4', '以下哪些接口能够实现对Web访问者的身份认证？(选择1项)', '1', 'C', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', 'Http Servlet Request', 'Http Servlet Response', 'Http Session', 'Http Servlet', null);
INSERT INTO `paper_question` VALUES ('89ffb4cd68ef4f58b4882da21c380eec', '无状态会话Bean、有状态会话Bean、CMP与BMP中，哪一种Bean不需要自己书写连接数据库的代码？', '1', 'C', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', '无状态会话Bean', '有状态会话Bean', 'CMP', 'BMP', null);
INSERT INTO `paper_question` VALUES ('8ba062d3fa9a4fae9e8092c0d844f17d', 'char c =\'a\';System.out.println(c+1);运行结果为：____', '2', '', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', null, null, null, null, null);
INSERT INTO `paper_question` VALUES ('8e9e14d4d4994508b726207427a6df04', '一个Java源程序是由若干个类组成。如果源文件中有多个类时，则只能有一个类是____类，并且这个类必须与源文件名同名', '2', '', '', '5', '7ec8dac0c545473a92373ed31a24922a', '1', null, null, null, null, null);
INSERT INTO `paper_question` VALUES ('dc0b59719c464da383ee902d7ed33a9c', '从以下哪一个选项中可以获得Servlet的初始化参数', '1', 'C', '', '4', '7ec8dac0c545473a92373ed31a24922a', '1', 'Servlet', 'ServletContext', 'ServletConfig', 'GenericServlet', null);

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
INSERT INTO `user` VALUES ('99b9a2f574d94a3491f8bec131d1c502', '222', '0', '222', '222', '222', '', '1', '192.168.0.104', '2020-05-05 23:31:37', '2020-04-25 23:46:52', '2020-04-25 23:46:52', '0', '1');

-- ----------------------------
-- Table structure for user_question
-- ----------------------------
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `id` varchar(36) DEFAULT NULL,
  `question_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `my_answer` varchar(255) DEFAULT NULL COMMENT '我的答案',
  `correct_answer` varchar(255) DEFAULT NULL COMMENT '正确答案',
  `score` varchar(4) DEFAULT NULL COMMENT '所得分数',
  `create_time` varchar(20) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_question
-- ----------------------------

-- ----------------------------
-- Table structure for user_sign
-- ----------------------------
DROP TABLE IF EXISTS `user_sign`;
CREATE TABLE `user_sign` (
  `id` varchar(36) NOT NULL,
  `class_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `sign_time` varchar(50) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `sign_place` varchar(255) NOT NULL,
  `sign_in` tinyint(1) NOT NULL DEFAULT '1' COMMENT '签到。1表示签到，0表示签退',
  `ip` varchar(255) DEFAULT NULL,
  `course_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_sign
-- ----------------------------
INSERT INTO `user_sign` VALUES ('2d59b3f07fc248cea70ddf4458ce379c', '18952731-e1db-4415-90ff-197ff048b6b4', '99b9a2f574d94a3491f8bec131d1c502', '222', '17:03:42', '大悟县人民政府(府前街东)', '0', '192.168.0.104', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `user_sign` VALUES ('50816f497d97483483d7170b04b93ea6', '18952731-e1db-4415-90ff-197ff048b6b4', '99b9a2f574d94a3491f8bec131d1c502', '222', '22:13:04', 'undefined', '1', '192.168.0.104', '799c8d7e-bc3a-41f5-9d49-d256f075058d');
INSERT INTO `user_sign` VALUES ('67fc85cc07c546f0a707fd9110cd59f8', '18952731-e1db-4415-90ff-197ff048b6b4', '99b9a2f574d94a3491f8bec131d1c502', '222', '17:03:41', '大悟县人民政府(府前街东)', '1', '192.168.0.104', 'bbc7f50e-702a-4314-b70a-fd30e3d682eb');
INSERT INTO `user_sign` VALUES ('8b975fc74925400ea7aac6099c19d62e', '18952731-e1db-4415-90ff-197ff048b6b4', '99b9a2f574d94a3491f8bec131d1c502', '222', '22:13:11', 'undefined', '0', '192.168.0.104', '799c8d7e-bc3a-41f5-9d49-d256f075058d');
