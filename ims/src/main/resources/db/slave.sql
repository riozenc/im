/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : 127.0.0.1:3306
Source Database       : slave

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2017-07-29 09:34:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `ID` bigint(20) NOT NULL,
  `COMPANY_NO` char(15) NOT NULL,
  `COMPANY_NAME` varchar(15) default NULL,
  `COMPANY_TYPE` int(11) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`COMPANY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';

-- ----------------------------
-- Records of company_info
-- ----------------------------

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info` (
  `ID` bigint(20) NOT NULL,
  `GROUP_NO` char(15) NOT NULL,
  `GROUP_NAME` char(30) default NULL,
  `GROUP_TYPE` int(11) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`GROUP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群组信息';

-- ----------------------------
-- Records of group_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_company_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_company_rel`;
CREATE TABLE `user_company_rel` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) default NULL,
  `COMPANY_ID` bigint(20) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户公司关系表';

-- ----------------------------
-- Records of user_company_rel
-- ----------------------------

-- ----------------------------
-- Table structure for user_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_group_rel`;
CREATE TABLE `user_group_rel` (
  `ID` bigint(20) NOT NULL,
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
  `ID` bigint(20) NOT NULL,
  `USER_ID` char(20) NOT NULL,
  `PHONE` char(11) default NULL,
  `MAIL_ADDRESS` char(20) default NULL,
  `USER_NAME` char(15) default NULL,
  `SEX` int(11) default NULL,
  `IMAGE_URL` varchar(20) default NULL,
  `PHONE_MODEL` varchar(30) default NULL,
  `PHONE_MODEL_TYPE` int(11) default NULL,
  `STATUS` int(11) default NULL,
  PRIMARY KEY  (`ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
