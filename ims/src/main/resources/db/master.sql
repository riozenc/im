/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : 127.0.0.1:3306
Source Database       : master

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2017-07-29 09:34:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company_department_rel
-- ----------------------------
DROP TABLE IF EXISTS `company_department_rel`;
CREATE TABLE `company_department_rel` (
  `ID` bigint(20) NOT NULL auto_increment,
  `COMPANY_ID` bigint(20) default NULL,
  `DEPARTMENT_ID` bigint(20) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司部门关系表';

-- ----------------------------
-- Records of company_department_rel
-- ----------------------------
INSERT INTO `company_department_rel` VALUES ('1', '1', '1', '1');
INSERT INTO `company_department_rel` VALUES ('2', '1', '2', '1');
INSERT INTO `company_department_rel` VALUES ('3', '1', '3', '1');
INSERT INTO `company_department_rel` VALUES ('4', '1', '6', '1');
INSERT INTO `company_department_rel` VALUES ('5', '1', '7', '1');
INSERT INTO `company_department_rel` VALUES ('6', '1', '8', '1');
INSERT INTO `company_department_rel` VALUES ('7', '1', '9', '1');

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `ID` bigint(20) NOT NULL auto_increment,
  `COMPANY_NO` char(15) NOT NULL,
  `COMPANY_NAME` varchar(15) default NULL,
  `COMPANY_TYPE` int(11) default NULL,
  `REMARK` varchar(100) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`COMPANY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES ('1', '1', '威思顿', '1', '电气', '1');
INSERT INTO `company_info` VALUES ('2', '2', '海颐', '2', '软件', '1');

-- ----------------------------
-- Table structure for department_info
-- ----------------------------
DROP TABLE IF EXISTS `department_info`;
CREATE TABLE `department_info` (
  `ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(20) default NULL,
  `TYPE` int(11) default NULL COMMENT '是否主部门\r\n            0：主部门\r\n            1：子部门',
  `PARENT_ID` bigint(20) default NULL COMMENT '主部门上级部门是0\r\n            子部门上级部门是主部门或其他子部门',
  `FUNCTION` varchar(20) default NULL COMMENT '待定',
  `REMARK` varchar(100) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门department';

-- ----------------------------
-- Records of department_info
-- ----------------------------
INSERT INTO `department_info` VALUES ('1', '系统部', '1', '0', null, '系统部', '1');
INSERT INTO `department_info` VALUES ('2', '营销部', '2', '0', null, '营销', '1');
INSERT INTO `department_info` VALUES ('3', '工程部', '3', '0', null, '工程', '1');
INSERT INTO `department_info` VALUES ('4', '南网部', '4', '0', null, null, '1');
INSERT INTO `department_info` VALUES ('5', '电子政务部', '4', '0', null, null, '1');
INSERT INTO `department_info` VALUES ('6', '研发部', null, '1', null, null, '1');
INSERT INTO `department_info` VALUES ('7', '售前部', null, '1', null, null, '1');
INSERT INTO `department_info` VALUES ('8', '营销一部', null, '2', null, null, '1');
INSERT INTO `department_info` VALUES ('9', '营销二部', null, '2', null, null, '1');

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `ID` bigint(20) NOT NULL auto_increment,
  `GROUP_NO` char(15) NOT NULL,
  `GROUP_NAME` char(30) default NULL,
  `GROUP_TYPE` int(11) default NULL,
  `REMARK` varchar(100) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`GROUP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组信息';

-- ----------------------------
-- Records of group_info
-- ----------------------------
INSERT INTO `group_info` VALUES ('1', '1', 'LOL', '1', '游戏群', '1');
INSERT INTO `group_info` VALUES ('2', '2', 'java', '2', '学习群', '1');
INSERT INTO `group_info` VALUES ('3', '3', 'C++', '2', '学习群', '1');

-- ----------------------------
-- Table structure for user_company_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_company_rel`;
CREATE TABLE `user_company_rel` (
  `ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` bigint(20) default NULL,
  `COMPANY_ID` bigint(20) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户公司关系表';

-- ----------------------------
-- Records of user_company_rel
-- ----------------------------
INSERT INTO `user_company_rel` VALUES ('1', '9', '1', '1');

-- ----------------------------
-- Table structure for user_department_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_department_rel`;
CREATE TABLE `user_department_rel` (
  `ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` bigint(20) default NULL,
  `DEPARTMENT_ID` bigint(20) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门关系表';

-- ----------------------------
-- Records of user_department_rel
-- ----------------------------
INSERT INTO `user_department_rel` VALUES ('1', '2', '6', '1');
INSERT INTO `user_department_rel` VALUES ('2', '3', '6', '1');
INSERT INTO `user_department_rel` VALUES ('3', '4', '6', '1');
INSERT INTO `user_department_rel` VALUES ('4', '9', '6', '1');

-- ----------------------------
-- Table structure for user_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_group_rel`;
CREATE TABLE `user_group_rel` (
  `ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` bigint(20) default NULL,
  `GROUP_ID` bigint(20) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户群组关系表';

-- ----------------------------
-- Records of user_group_rel
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` char(20) NOT NULL,
  `PASSWORD` varchar(150) default NULL,
  `PHONE` char(11) default NULL,
  `MAIL_ADDRESS` char(20) default NULL,
  `USER_NAME` char(15) default NULL,
  `SEX` int(11) default NULL,
  `IMAGE_URL` varchar(20) default NULL,
  `PHONE_MODEL` varchar(30) default NULL,
  `PHONE_MODEL_TYPE` int(11) default NULL,
  `REMARK` varchar(100) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', null, '18660509556', '312109106@qq.com', '管理员', '1', null, null, null, null, '1');
INSERT INTO `user_info` VALUES ('2', 'lvjiahui', null, null, null, '吕嘉慧', '1', null, null, null, null, '1');
INSERT INTO `user_info` VALUES ('3', 'cuixinghai', null, null, null, '崔兴海', '1', null, null, null, null, '1');
INSERT INTO `user_info` VALUES ('4', 'wangqiangliang', null, null, null, '王强良', '1', null, null, null, null, '1');
INSERT INTO `user_info` VALUES ('9', 'chiziyue', 'd68cf192133795b59ce6c40a7a7e17efe2e10c957645464f9ea6495091e9a10ba26c25ed81127e251bba76f67c7c4b32877e85ee3919f9fc9e55c925439186f52e69fbeaf26a9f4b', null, null, '迟子悦', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_ip_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_ip_rel`;
CREATE TABLE `user_ip_rel` (
  `ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` bigint(20) default NULL,
  `IP` varchar(20) default NULL,
  `CREATE_DATE` datetime default NULL,
  `LAST_LOGIN_DATE` datetime default NULL,
  `UPDATE_DATE` datetime default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户IP表';

-- ----------------------------
-- Records of user_ip_rel
-- ----------------------------
