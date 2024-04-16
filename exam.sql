/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 16/04/2024 17:59:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_hour` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `credit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `course_name`(`course_name`) USING BTREE,
  INDEX `teacher`(`teacher`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'C语言入门', '陈蕾', '36', '4');
INSERT INTO `course` VALUES (2, 'Python语言入门', '陈蕾', '36', '4');
INSERT INTO `course` VALUES (3, '机器学习', '陈蕾', '18', '2');
INSERT INTO `course` VALUES (4, '深度学习', '邓桂骞', '36', '2');
INSERT INTO `course` VALUES (5, 'web开发与实践', '张胜礼', '36', '4');
INSERT INTO `course` VALUES (6, 'Java实战', '张胜礼', '36', '4');
INSERT INTO `course` VALUES (7, '线性代数', '黄家芳', '36', '2');
INSERT INTO `course` VALUES (8, '设计基础', '刘德华', '18', '2');
INSERT INTO `course` VALUES (9, '大学体育', '王一博', '18', '1');
INSERT INTO `course` VALUES (10, '大学英语', '肖战', '18', '1');
INSERT INTO `course` VALUES (11, '音乐进阶1', '陈哲远', '18', '1');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_index` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `node_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'el-icon-user-solid', '1', '用户管理', NULL, 0, 1);
INSERT INTO `permission` VALUES (2, 'el-icon-user-solid', '2', '用户列表', '/index/userList', 1, 2);
INSERT INTO `permission` VALUES (3, '\r\nel-icon-eleme', '3', '个人管理', NULL, 0, 3);
INSERT INTO `permission` VALUES (4, 'el-icon-info', '4', '个人信息', '/index/userSelf', 3, 4);
INSERT INTO `permission` VALUES (5, 'el-icon-eleme\r\nel-icon-info', '5', '课程管理', NULL, 0, 5);
INSERT INTO `permission` VALUES (6, 'el-icon-info\r\nel-icon-elemeel', '6', '我的课程', '/index/myCourse', 5, 6);
INSERT INTO `permission` VALUES (7, 'el-icon-info', '7', '课程列表', '/index/courseList', 5, 7);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(255) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '学生');

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES (1, 1);
INSERT INTO `role_permission_rel` VALUES (1, 2);
INSERT INTO `role_permission_rel` VALUES (1, 3);
INSERT INTO `role_permission_rel` VALUES (1, 4);
INSERT INTO `role_permission_rel` VALUES (1, 5);
INSERT INTO `role_permission_rel` VALUES (1, 6);
INSERT INTO `role_permission_rel` VALUES (1, 7);
INSERT INTO `role_permission_rel` VALUES (2, 3);
INSERT INTO `role_permission_rel` VALUES (2, 4);
INSERT INTO `role_permission_rel` VALUES (2, 5);
INSERT INTO `role_permission_rel` VALUES (2, 6);
INSERT INTO `role_permission_rel` VALUES (1, 8);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `student_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `student_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `student_number`(`student_number`) USING BTREE,
  INDEX `student_name`(`student_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'xiaoming', '20204811101', 0);
INSERT INTO `student` VALUES (2, 'xiaohong', '20204811102', 0);
INSERT INTO `student` VALUES (3, 'xiaozhang', '20204811103', 0);
INSERT INTO `student` VALUES (4, 'xaiozhao', '20204811104', 0);
INSERT INTO `student` VALUES (5, 'xiaodu', '20204811105', 0);
INSERT INTO `student` VALUES (6, 'zhangsan', '20204811106', 0);
INSERT INTO `student` VALUES (7, 'zhangdi', '20204811107', 0);
INSERT INTO `student` VALUES (8, 'zhangwu', '20204811108', 0);
INSERT INTO `student` VALUES (9, 'zhangsiyu', '20204811109', 0);
INSERT INTO `student` VALUES (10, 'gongcaixiu', '20204811110', 0);
INSERT INTO `student` VALUES (11, 'tanshishi', '20204811111', 0);
INSERT INTO `student` VALUES (12, 'tanyuyu', '20204811112', 0);
INSERT INTO `student` VALUES (13, 'zhongzhihui', '20204811113', 0);
INSERT INTO `student` VALUES (14, 'zhongnanshan', '20204811114', 0);
INSERT INTO `student` VALUES (15, 'zhongcaicai', '20204811115', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `user_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'img-1.jpg',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_state` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'img-1.jpg', '20204811101', 'admin', '1310279983@qq.com', 0);
INSERT INTO `user` VALUES (74, 'img-1.jpg', '20204811103', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (75, 'img-1.jpg', '20204811104', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (76, 'img-1.jpg', '20204811105', '111', 'D123@qq.com', 1);
INSERT INTO `user` VALUES (77, 'img-1.jpg', '20204811106', '111', 'D1310279983@163.com', 0);
INSERT INTO `user` VALUES (78, 'img-1.jpg', '20204811107', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (79, 'img-1.jpg', '20204811108', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (80, 'img-1.jpg', '20204811109', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (81, 'img-1.jpg', '20204811110', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (82, 'img-1.jpg', '20204811112', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (83, 'img-1.jpg', '20204811113', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (84, 'img-1.jpg', '20204811116', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (85, 'img-1.jpg', '20204811117', '111', 'D1234567891@qq.com', 1);
INSERT INTO `user` VALUES (86, 'img-1.jpg', '202048111018', '333', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (87, 'img-1.jpg', '20204811102', '222', '131027998311@qq.com', 1);
INSERT INTO `user` VALUES (88, 'img-1.jpg', '20204811119', '111', '131027998311@qq.com', 1);
INSERT INTO `user` VALUES (89, 'img-1.jpg', '20204811120', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (90, 'img-1.jpg', '20204811122', '555', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (91, 'img-1.jpg', '20204811199', '111', 'D1310279983@163.com', 1);
INSERT INTO `user` VALUES (92, 'img-1.jpg', '20204811111', '000', '1310279983@qq.com', 2);
INSERT INTO `user` VALUES (93, 'img-1.jpg', '20204811100', '1111', '1310279983@qq.com', 0);

-- ----------------------------
-- Table structure for user_course
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_id` int(11) NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  INDEX `course_name`(`course_name`) USING BTREE,
  INDEX `teacher`(`teacher`) USING BTREE,
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_name` FOREIGN KEY (`course_name`) REFERENCES `course` (`course_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher` FOREIGN KEY (`teacher`) REFERENCES `course` (`teacher`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES (1, '20204811101', 2, 'Python语言入门', '陈蕾');
INSERT INTO `user_course` VALUES (2, '20204811101', 1, 'C语言入门', '陈蕾');
INSERT INTO `user_course` VALUES (3, '20204811101', 4, '深度学习', '邓桂骞');
INSERT INTO `user_course` VALUES (7, '20204811103', 2, 'Python语言入门', '陈蕾');
INSERT INTO `user_course` VALUES (8, '20204811103', 4, '深度学习', '邓桂骞');
INSERT INTO `user_course` VALUES (9, '20204811104', 5, 'web开发与实践', '张胜礼');
INSERT INTO `user_course` VALUES (10, '20204811104', 6, 'Java实战', '张胜礼');
INSERT INTO `user_course` VALUES (11, '20204811105', 3, '机器学习', '陈蕾');
INSERT INTO `user_course` VALUES (12, '20204811105', 4, '深度学习', '邓桂骞');
INSERT INTO `user_course` VALUES (13, '20204811105', 6, 'Java实战', '张胜礼');
INSERT INTO `user_course` VALUES (14, '20204811106', 1, 'C语言入门', '陈蕾');
INSERT INTO `user_course` VALUES (15, '20204811106', 2, 'Python语言入门', '陈蕾');
INSERT INTO `user_course` VALUES (16, '20204811106', 4, '深度学习', '邓桂骞');
INSERT INTO `user_course` VALUES (17, '20204811106', 6, 'Java实战', '张胜礼');

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_role_rel
-- ----------------------------
INSERT INTO `user_role_rel` VALUES (1, 1);
INSERT INTO `user_role_rel` VALUES (93, 2);
INSERT INTO `user_role_rel` VALUES (92, 2);
INSERT INTO `user_role_rel` VALUES (91, 2);
INSERT INTO `user_role_rel` VALUES (90, 2);
INSERT INTO `user_role_rel` VALUES (89, 2);
INSERT INTO `user_role_rel` VALUES (88, 2);
INSERT INTO `user_role_rel` VALUES (87, 2);
INSERT INTO `user_role_rel` VALUES (86, 2);
INSERT INTO `user_role_rel` VALUES (85, 2);
INSERT INTO `user_role_rel` VALUES (84, 2);
INSERT INTO `user_role_rel` VALUES (83, 2);
INSERT INTO `user_role_rel` VALUES (82, 2);
INSERT INTO `user_role_rel` VALUES (81, 2);
INSERT INTO `user_role_rel` VALUES (80, 2);
INSERT INTO `user_role_rel` VALUES (79, 2);
INSERT INTO `user_role_rel` VALUES (78, 2);
INSERT INTO `user_role_rel` VALUES (77, 2);
INSERT INTO `user_role_rel` VALUES (76, 2);
INSERT INTO `user_role_rel` VALUES (75, 2);
INSERT INTO `user_role_rel` VALUES (74, 2);
INSERT INTO `user_role_rel` VALUES (73, 2);

SET FOREIGN_KEY_CHECKS = 1;
