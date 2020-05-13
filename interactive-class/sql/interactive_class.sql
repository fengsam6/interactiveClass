/*
Navicat MySQL Data Transfer

Source Server         : interactive_class
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : interactive_class

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-05-13 19:16:32
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
INSERT INTO `class` VALUES ('914678f8-a306-45de-b633-659d545332a5', '1201', '14', '这是1201班级简介。。。', '355405', '2020-05-13 18:39:15', '2020-05-13 18:39:15', '0');
INSERT INTO `class` VALUES ('d2a0836c-d605-4787-bbce-6acb9e0e793e', '1203', '33', '这是1203班级简介。。。。', '395089', '2020-05-13 18:39:55', '2020-05-13 18:39:55', '0');
INSERT INTO `class` VALUES ('f9f3c901-64c9-4882-80d3-9a59e81aaf4d', '1202', '23', '这是1201班级简介。。。。', '375048', '2020-05-13 18:39:35', '2020-05-13 18:39:35', '0');

-- ----------------------------
-- Table structure for class_course
-- ----------------------------
DROP TABLE IF EXISTS `class_course`;
CREATE TABLE `class_course` (
  `id` varchar(36) NOT NULL,
  `class_id` varchar(36) NOT NULL,
  `course_id` varchar(36) NOT NULL,
  `select_course_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_class_course` (`class_id`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_course
-- ----------------------------
INSERT INTO `class_course` VALUES ('5d191cf228d94dd7b542ff5d4cc1f74d', 'f9f3c901-64c9-4882-80d3-9a59e81aaf4d', '08bb02b9-ef3f-4b1d-b522-af960565bda1', '2020-05-13 18:41:53');
INSERT INTO `class_course` VALUES ('914bc22d30644140a1c4f330f7955d5b', '914678f8-a306-45de-b633-659d545332a5', '08bb02b9-ef3f-4b1d-b522-af960565bda1', '2020-05-13 18:41:52');
INSERT INTO `class_course` VALUES ('e8753cd543384e2aa8f534ac85da70ea', '914678f8-a306-45de-b633-659d545332a5', '7226e74a-20c8-4cce-b8cb-129f5464d6b2', '2020-05-13 18:44:36');

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
INSERT INTO `class_user` VALUES ('3b79debf7be84b93b84c9b9e5685f7ba', '914678f8-a306-45de-b633-659d545332a5', null, '1e9ae21bfe584bc59b4934ac3a238ef9', '1e9ae21bfe584bc59b4934ac3a238ef9', '2020-05-13 18:39:15', '2020-05-13 18:39:15');
INSERT INTO `class_user` VALUES ('626d3100766f4114995886a2e169f3f8', 'f9f3c901-64c9-4882-80d3-9a59e81aaf4d', null, '8f7c4cb963f74b8d91083f9e704c3f47', '', '2020-05-13 18:46:57', '2020-05-13 18:46:57');
INSERT INTO `class_user` VALUES ('c901a2a3c01f4359b34f55758a0b5d48', 'd2a0836c-d605-4787-bbce-6acb9e0e793e', null, '1e9ae21bfe584bc59b4934ac3a238ef9', '1e9ae21bfe584bc59b4934ac3a238ef9', '2020-05-13 18:39:55', '2020-05-13 18:39:55');
INSERT INTO `class_user` VALUES ('d122ba6555a74183a5ab42bb8e74c9c1', '914678f8-a306-45de-b633-659d545332a5', null, '9625bb6310744a71ae3e4dd53c313853', '', '2020-05-13 18:42:24', '2020-05-13 18:42:24');
INSERT INTO `class_user` VALUES ('e7f2d16a6840450a8cd912d68f0bc237', 'f9f3c901-64c9-4882-80d3-9a59e81aaf4d', null, '1e9ae21bfe584bc59b4934ac3a238ef9', '1e9ae21bfe584bc59b4934ac3a238ef9', '2020-05-13 18:39:35', '2020-05-13 18:39:35');

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
  `created_user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '课程创建者Id',
  `student_num` int(11) DEFAULT '0' COMMENT '上课人数',
  `begin_time` varchar(50) DEFAULT '' COMMENT '上课时间',
  `end_time` varchar(50) DEFAULT '' COMMENT '下课时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('08bb02b9-ef3f-4b1d-b522-af960565bda1', 'JAVA课程', '', '', '21', '1e9ae21bfe584bc59b4934ac3a238ef9', '37', '2020-04-27 08:00:00', '2020-04-27 10:00:00');
INSERT INTO `course` VALUES ('7226e74a-20c8-4cce-b8cb-129f5464d6b2', 'C++课程', '', '', '45', '1e9ae21bfe584bc59b4934ac3a238ef9', '14', '2020-05-13 06:44:20', '2020-05-13 08:44:26');

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
-- Table structure for home_work
-- ----------------------------
DROP TABLE IF EXISTS `home_work`;
CREATE TABLE `home_work` (
  `id` varchar(36) NOT NULL,
  `work_title` varchar(100) DEFAULT NULL COMMENT '作业标题',
  `work_des` varchar(255) DEFAULT NULL COMMENT '作业描述',
  `attach_path` varchar(100) NOT NULL COMMENT '附件路径',
  `student_id` varchar(36) NOT NULL COMMENT '作业提交者id',
  `submit_time` varchar(60) DEFAULT NULL,
  `teacher_id` varchar(32) DEFAULT NULL,
  `course_id` varchar(36) DEFAULT NULL COMMENT '课程id',
  `class_id` varchar(36) DEFAULT NULL,
  `download_num` int(3) DEFAULT '0' COMMENT '下载次数',
  `last_down_time` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home_work
-- ----------------------------

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` varchar(36) NOT NULL COMMENT '消息id',
  `content` varchar(100) DEFAULT NULL,
  `send_id` varchar(36) DEFAULT NULL COMMENT '消息发送id',
  `send_name` varchar(80) DEFAULT '' COMMENT '发送者名字',
  `msg_type` varchar(50) DEFAULT NULL,
  `class_id` varchar(36) DEFAULT NULL,
  `send_time` varchar(80) DEFAULT '' COMMENT '发送日期',
  `to_id` varchar(36) DEFAULT '' COMMENT '消息接收者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
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
INSERT INTO `notice` VALUES ('0007395b-7bab-4c84-aa1d-3792f1c9ac66', '(JAVA)1202班课后作业', 'P38,3,4,5,6后天上午交。。', '2020-05-13 18:53:41', '1e9ae21bfe584bc59b4934ac3a238ef9', 'f9f3c901-64c9-4882-80d3-9a59e81aaf4d', '08bb02b9-ef3f-4b1d-b522-af960565bda1');
INSERT INTO `notice` VALUES ('a8b3c1d9-8b4b-4f23-99f1-1ad94db742cd', '(JAVA)1201班的公告', '课后作业：P13,4,5,6,7,8,明天中午之前提交。', '2020-05-13 18:51:27', '1e9ae21bfe584bc59b4934ac3a238ef9', '914678f8-a306-45de-b633-659d545332a5', '08bb02b9-ef3f-4b1d-b522-af960565bda1');

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
INSERT INTO `paper` VALUES ('7ce691615fe5493dbec4a564a8c8ff6d', 'JAVA测试', null, '120分钟', null, null, '1e9ae21bfe584bc59b4934ac3a238ef9', '2020-52-13 06:52:09', '', '914678f8-a306-45de-b633-659d545332a5', '08bb02b9-ef3f-4b1d-b522-af960565bda1', null);

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
INSERT INTO `paper_question` VALUES ('2f1313fa6da6464895bc03cd9ca132c8', '_____类是专门用来操作集合的工具类，提供一系列静态方法实现对各种集合的操作。', '2', 'Collections', '10', '5', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('3bd1e53530a4470dbc11975a467b163b', 'char类型用来表示在Unicode编码表中的字符，长度是_____个字节。', '2', '2', '8', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('6f15b1e573ff4116935fc16763f52271', '为一个boolean类型变量赋值时，可以使用()方式', '1', 'B', '5', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('91e7757e1acd43f789d367d0545c823d', '一个Java源程序是由若干个类组成。如果源文件中有多个类时，则只能有一个类是_____类，并且这个类必须与源文件名同名。', '2', 'public', '6', '5', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('a4c707e348bb4db6ba035b3caf7ce582', '下列javaDoc注释正确的是', '1', 'C', '4', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' /*我爱北京天安门*/', '//我爱北京天安门*/', '/**我爱北京天安门*/', '/*我爱北京天安门**/', null);
INSERT INTO `paper_question` VALUES ('ac354c845c044a53a869d98f1e6274ec', 'Java的字节码文件的扩展名是_____，是一种平台独立的二进制文件。', '2', '.class', '9', '5', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('d2b5cb29c2a5402f9c484ccfe0428d34', '安装JDK后，为了告诉计算机javac.exe和java.exe等执行文件的位置，需要配置的环境变量是_____', '2', 'path', '7', '5', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' boolean = 1;', 'boolean a = (9 >= 10);', ' boolean a=\"真\";', 'boolean a = = false;', null);
INSERT INTO `paper_question` VALUES ('e3c6460e7dc7427eadb27037c883a9e8', 'JAVA所定义的版本中不包括', '1', 'D', '1', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', 'JAVA2 EE', 'JAVA2 Card', 'JAVA2 ME', 'JAVA2 HE', null);
INSERT INTO `paper_question` VALUES ('eff8f5b70aea4647887c48e0072613a3', '变量命名规范说法正确的是', '1', 'B', '3', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', ' 变量由字母、下划线、数字、$符号随意组成；', '变量不能以数字作为开头；', 'A和a在java中是同一个变量；', '不同类型的变量，可以起相同的名字', null);
INSERT INTO `paper_question` VALUES ('ff7b6e7172ed4d1daa4537a53a7860aa', '下列说法正确的是', '1', 'A', '2', '4', '7ce691615fe5493dbec4a564a8c8ff6d', '1', 'JAVA程序的main方法必须写在类里面', 'JAVA程序中可以有多个main方法', 'JAVA程序中类名必须与文件名一样', 'JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL DEFAULT '' COMMENT '角色权限标识（要求输入英文）',
  `desc` varchar(255) DEFAULT '' COMMENT '角色描述',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '用于逻辑删除，1标识数据被删除',
  `is_used` tinyint(255) DEFAULT '1' COMMENT '是否使用。1正在使用，0禁止使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', 'student', '学生', '0', '1');
INSERT INTO `role` VALUES ('2', '老师', 'teacher', '拥有老师权限', '0', '1');
INSERT INTO `role` VALUES ('3', '管理员', 'admin', 'admin', '0', '1');

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
INSERT INTO `user` VALUES ('1e9ae21bfe584bc59b4934ac3a238ef9', '1000', '0', '1000', '1000', '1000', '', '2', '127.0.0.1', '2020-05-13 19:14:44', '2020-05-13 18:27:38', '2020-05-13 18:27:38', '0', '1');
INSERT INTO `user` VALUES ('2d7b902843dc4aa697f22f0ccfbbed2a', '1003', '0', '1003', '1003', '1003', '', '1', '192.168.3.14', '', '2020-05-13 18:28:34', '2020-05-13 18:28:34', '0', '1');
INSERT INTO `user` VALUES ('8f7c4cb963f74b8d91083f9e704c3f47', '1002', '0', '1002', '1002', '1002', '', '1', '192.168.3.14', '2020-05-13 18:46:13', '2020-05-13 18:28:12', '2020-05-13 18:28:12', '0', '1');
INSERT INTO `user` VALUES ('9625bb6310744a71ae3e4dd53c313853', '1001', '0', '1001', '1001', '1001', '', '1', '127.0.0.1', '2020-05-13 19:15:28', '2020-05-13 18:27:54', '2020-05-13 18:27:54', '0', '1');
INSERT INTO `user` VALUES ('c44f9ae5381c4d2fa51bb05b0f638dab', 'admin', '0', 'admin', 'admin', '0000', '', '3', '127.0.0.1', '2020-05-13 19:11:18', '2020-05-13 18:28:58', '2020-05-13 19:11:10', '0', '1');

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
-- Table structure for user_score
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `paper_id` varchar(32) DEFAULT NULL,
  `course_id` varchar(32) DEFAULT NULL,
  `total_score` varchar(255) DEFAULT '0' COMMENT '总分',
  `create_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_score
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
INSERT INTO `user_sign` VALUES ('48dd334b1de54fceb24e12e75fa29ca2', '914678f8-a306-45de-b633-659d545332a5', '1e9ae21bfe584bc59b4934ac3a238ef9', '1000', '18:52:36', '万柳中共北京市海淀区委员会', '0', '192.168.3.14', '08bb02b9-ef3f-4b1d-b522-af960565bda1');
INSERT INTO `user_sign` VALUES ('90cbafbdaebb4451a008c99cfe55ea86', '914678f8-a306-45de-b633-659d545332a5', '9625bb6310744a71ae3e4dd53c313853', '1001', '18:42:58', '万柳中共北京市海淀区委员会', '1', '192.168.3.14', '08bb02b9-ef3f-4b1d-b522-af960565bda1');
INSERT INTO `user_sign` VALUES ('b2fbe37cb502440c9634fb5914b19890', '914678f8-a306-45de-b633-659d545332a5', '1e9ae21bfe584bc59b4934ac3a238ef9', '1000', '18:52:34', '万柳中共北京市海淀区委员会', '1', '192.168.3.14', '08bb02b9-ef3f-4b1d-b522-af960565bda1');
INSERT INTO `user_sign` VALUES ('c7b8fc3f02f1499a834fe5c9d0f931ee', '914678f8-a306-45de-b633-659d545332a5', '9625bb6310744a71ae3e4dd53c313853', '1001', '18:42:59', '万柳中共北京市海淀区委员会', '0', '192.168.3.14', '08bb02b9-ef3f-4b1d-b522-af960565bda1');
