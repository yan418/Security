/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : go_app

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-07-10 11:14:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('27', '员工删除', 'emp:delete');
INSERT INTO `permission` VALUES ('28', '员工增加', 'emp:save');
INSERT INTO `permission` VALUES ('29', '员工修改', 'emp:edit');
INSERT INTO `permission` VALUES ('30', '经理删除', 'manager:delete');
INSERT INTO `permission` VALUES ('31', '经理增加', 'manager:save');
INSERT INTO `permission` VALUES ('32', '经理修改', 'manager:edit');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '经理', 'ROLE_manager');
INSERT INTO `role` VALUES ('2', '人事', 'ROLE_emp');
INSERT INTO `role` VALUES ('3', '老板', 'ROLE_boss');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('2', '27');
INSERT INTO `role_permission` VALUES ('2', '28');
INSERT INTO `role_permission` VALUES ('2', '29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `ticket` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '9d7281eeaebded0b091340cfa658a7e8', '1');
INSERT INTO `user` VALUES ('2', 'yan', '$2a$10$vnkl2DKEA92WKGYj27dJleRjUqqdgEvfYzhCU3si4wjOpvR51MVUO', '1');
INSERT INTO `user` VALUES ('3', 'zhu', '$2a$10$k.MMD95VbiQsshe.6Yrec.CY7aq9R9/imCY3aeRnuewgNrSGyLN6y', '1');
INSERT INTO `user` VALUES ('4', 'q', '$2a$10$k.MMD95VbiQsshe.6Yrec.CY7aq9R9/imCY3aeRnuewgNrSGyLN6y', '1');
INSERT INTO `user` VALUES ('5', 'w', '$2a$10$qT9IO2TgMpGfrA1BEnmJiOgTMfM1Nx9FMOVpoXKISJJ2LPPhAqiTi', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('3', '2');
INSERT INTO `user_role` VALUES ('4', '1');
INSERT INTO `user_role` VALUES ('5', '2');

-- ----------------------------
-- Table structure for version_management
-- ----------------------------
DROP TABLE IF EXISTS `version_management`;
CREATE TABLE `version_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `platform` varchar(100) DEFAULT NULL,
  `version` varchar(100) DEFAULT NULL,
  `imprint` varchar(400) DEFAULT NULL,
  `downloadLink` varchar(200) DEFAULT NULL,
  `storeAddress` varchar(200) DEFAULT NULL,
  `creationTime` varchar(100) DEFAULT NULL,
  `alterTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version_management
-- ----------------------------
INSERT INTO `version_management` VALUES ('1', '0', '1.0.0', '最新版本', 'files/af5f45e2c452f39cccd3fb67ce8bbf3e.apk	', null, null, null);
INSERT INTO `version_management` VALUES ('2', '1', '1.0.1', 'zzzz', null, 'http://goodysr.cn/lab_info?id=7	', null, null);
INSERT INTO `version_management` VALUES ('5', '0', '1111111111', null, null, null, '2020-04-12 21:07:36', null);
INSERT INTO `version_management` VALUES ('8', '1', '33', null, null, '33333', '2020-04-12 21:47:08', null);
INSERT INTO `version_management` VALUES ('9', '0', '44', null, 'C:\\fakepath\\新建文本文档.txt', null, '2020-04-12 21:47:27', null);
INSERT INTO `version_management` VALUES ('10', '0', '55', '555', 'C:\\fakepath\\sql.txt', null, '2020-04-12 21:51:12', null);
INSERT INTO `version_management` VALUES ('11', '1', '666', '666', null, '6666', '2020-04-12 21:52:28', null);
INSERT INTO `version_management` VALUES ('12', '0', '77', '777', 'C:\\fakepath\\sql.txt', null, '2020-04-12 21:54:34', null);
INSERT INTO `version_management` VALUES ('13', '0', '1', '111', 'C:\\fakepath\\新建文本文档.txt', null, '2020-04-12 22:03:15', null);
INSERT INTO `version_management` VALUES ('14', '0', '22', '22', 'F:\\tomcat\\apache-tomcat-8.5.50\\webapps\\apk\\2020\\04\\18\\1d55bb23-86a2-463e-a927-7da8b0d20239.apk', null, '2020-04-18 22:45:13', null);
INSERT INTO `version_management` VALUES ('15', '0', '222', '222', '\\apk\\2020\\04\\18\\2a056bd2-3d24-4173-91fa-d5e40a65a0eb.apk', null, '2020-04-18 22:51:23', null);
INSERT INTO `version_management` VALUES ('16', '0', '333', '333', '\\apk\\2020\\04\\18\\9407a69f-2ac2-47c8-9afc-a13133cae7e1.apk', null, '2020-04-18 23:14:13', null);
INSERT INTO `version_management` VALUES ('17', '0', '33', '3333', '\\apk\\2020\\04\\18\\9c68d762-e036-41b8-9075-f71d384bedc1.apk', null, '2020-04-18 23:29:17', null);
INSERT INTO `version_management` VALUES ('18', '0', 'fff', 'fff', '\\apk\\2020\\04\\18\\d260a961-56cd-4c80-a9a5-9395a97938a5.apk', null, '2020-04-18 23:30:25', null);
INSERT INTO `version_management` VALUES ('19', '1', '222', '2222', null, '222', '2020-04-18 23:30:43', null);
