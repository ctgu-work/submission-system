/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : contribute_db

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 18/12/2019 17:24:50
*/

Create Database If Not Exists contribute_db;
USE contribute_db;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员昵称',
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员电话号码',
  `rank` int(2) NOT NULL COMMENT '管理员级别',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_rank
-- ----------------------------
DROP TABLE IF EXISTS `admin_rank`;
CREATE TABLE `admin_rank`  (
  `rank_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '管理员分类ID',
  `rank_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员分类详情',
  PRIMARY KEY (`rank_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `status` int(2) NOT NULL COMMENT '消息状态',
  `sender_id` int(10) NOT NULL COMMENT '发起者ID',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_status
-- ----------------------------
DROP TABLE IF EXISTS `message_status`;
CREATE TABLE `message_status`  (
  `status_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '消息状态ID',
  `status_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息状态详情',
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `paper_id` int(10) NOT NULL COMMENT '稿件ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '稿件标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '稿件内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `category` int(2) NOT NULL COMMENT '分类ID',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `user_id` int(10) NOT NULL COMMENT '投稿人ID',
  `submit_date` datetime(0) NOT NULL COMMENT '提交稿件时间',
  `specialist_id` int(10) NOT NULL COMMENT '审稿人(专家)ID',
  `check_date` datetime(0) NULL DEFAULT NULL COMMENT '审核稿件时间',
  `click_rate` int(10) NOT NULL DEFAULT 0 COMMENT '稿件点击量',
  `status` int(2) NOT NULL COMMENT '稿件当前状态',
  PRIMARY KEY (`paper_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper_category
-- ----------------------------
DROP TABLE IF EXISTS `paper_category`;
CREATE TABLE `paper_category`  (
  `category_id` int(2) NOT NULL COMMENT '稿件分类ID',
  `category_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '稿件分类详情',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper_status
-- ----------------------------
DROP TABLE IF EXISTS `paper_status`;
CREATE TABLE `paper_status`  (
  `status_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '稿件状态ID',
  `status_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '稿件状态详情',
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo`  (
  `photo_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `category` int(10) NOT NULL COMMENT '图片类别ID',
  `photo_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`photo_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for photo_category
-- ----------------------------
DROP TABLE IF EXISTS `photo_category`;
CREATE TABLE `photo_category`  (
  `category_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '图片类型ID',
  `category_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片类型详情',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for specialist
-- ----------------------------
DROP TABLE IF EXISTS `specialist`;
CREATE TABLE `specialist`  (
  `specialist_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '专家ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家姓名',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家身份证信息',
  `age` int(3) NOT NULL COMMENT '专家年龄',
  `sex` int(2) NOT NULL COMMENT '专家性别',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家昵称',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家电话号码',
  `status` int(2) NOT NULL COMMENT '专家审核状态',
  `category` int(2) NOT NULL DEFAULT 0 COMMENT '专家分类',
  PRIMARY KEY (`specialist_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for specialist_category
-- ----------------------------
DROP TABLE IF EXISTS `specialist_category`;
CREATE TABLE `specialist_category`  (
  `category_id` int(2) NOT NULL COMMENT '专家分类ID',
  `category_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家分类详情',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for specialist_status
-- ----------------------------
DROP TABLE IF EXISTS `specialist_status`;
CREATE TABLE `specialist_status`  (
  `status_id` int(2) NOT NULL COMMENT '状态ID',
  `status_detail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态详情',
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份证信息',
  `sex` int(2) NOT NULL COMMENT '用户性别(1:男，2:女)',
  `age` int(3) NOT NULL COMMENT '用户年龄',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话号码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
