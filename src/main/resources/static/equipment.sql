/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : equipment

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 16/10/2020 18:46:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_groups
-- ----------------------------
DROP TABLE IF EXISTS `db_groups`;
CREATE TABLE `db_groups`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `NUMBER` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '编号 记录编号，系统统一生成，全表唯一',
  `NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '名称 设备组名称',
  `DESCRIPTION` varchar(128) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '描述 设备组描述',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 记录创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 记录更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备组表 设备组信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_groups
-- ----------------------------
INSERT INTO `db_groups` VALUES (1, '第一次1', '设备组1', NULL, '1', 'A', NULL, NULL, '前所未有1', NULL);
INSERT INTO `db_groups` VALUES (2, '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `db_groups` VALUES (4, '1602842449602575', '传感组', NULL, NULL, NULL, 'CF', NULL, '穿越火线', NULL);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT ' 记录ID 记录主键，数据库自增',
  `NUMBER` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '设备编号 记录编号，系统统一生成',
  `DM_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '型号编号 设备型号编号，关联设备型号表',
  `NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备名称 设备名称',
  `DESCRIPTION` varchar(128) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备描述 设备描述',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备状态 设备状态',
  `POSITION` varchar(128) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '安装位置 设备安装位置描述',
  `MP_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '测点编号 测点编号，关联测点表',
  `DEV_SN` varchar(64) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备出厂编号 传感设备出厂编号',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '传感设备表 传感器设备信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (3, '11111', '123', '熔断器', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device` VALUES (5, '测试11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '11', NULL);
INSERT INTO `device` VALUES (7, '160276053083649218', NULL, '空气压缩机', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device` VALUES (8, '1602812321272963', NULL, '交流接触器', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '刺激战场', NULL);
INSERT INTO `device` VALUES (9, '1602812476584776', NULL, '互感器', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '天天酷跑', NULL);

-- ----------------------------
-- Table structure for device_group
-- ----------------------------
DROP TABLE IF EXISTS `device_group`;
CREATE TABLE `device_group`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `DEVICE_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备编号 设备编号',
  `GROUP_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '分组编号 分组编号',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 记录创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 记录更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备分组表 设备分组情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group
-- ----------------------------
INSERT INTO `device_group` VALUES (1, '66666', NULL, NULL, NULL, NULL, NULL, '熊二', NULL);
INSERT INTO `device_group` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, '凹头曼', NULL);
INSERT INTO `device_group` VALUES (113, '1602812321272963', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_group` VALUES (114, '1602812476584776', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for device_output
-- ----------------------------
DROP TABLE IF EXISTS `device_output`;
CREATE TABLE `device_output`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `DEVICE_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '设备编号 设备编号，关联设备表',
  `META_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '元数据编号 元数据编号，关联输出表',
  `CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '数据编码 数据编码',
  `VALUE` varchar(64) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '数据值 传感设备输出数据值',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 记录创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 记录更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备输出表 传感器设备输出数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_output
-- ----------------------------
INSERT INTO `device_output` VALUES (2, '11111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '章鱼', NULL);
INSERT INTO `device_output` VALUES (3, '1602812321272963', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_output` VALUES (4, '1602812476584776', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '光肉使命', NULL);

-- ----------------------------
-- Table structure for device_param
-- ----------------------------
DROP TABLE IF EXISTS `device_param`;
CREATE TABLE `device_param`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `DEVICE_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '设备编号 设备编码，关联设备表',
  `PARAM_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '参数编号 参数编号，关联参数表',
  `CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '参数编码 参数编码',
  `VALUE` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '参数值 参数值',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备参数表 传感器设备参数信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_param
-- ----------------------------
INSERT INTO `device_param` VALUES (1, '9090', '99', NULL, NULL, NULL, NULL, NULL, NULL, '迪迦凹头曼', NULL);
INSERT INTO `device_param` VALUES (3, '1602812321272963', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '罗维', NULL);
INSERT INTO `device_param` VALUES (4, '1602812476584776', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_param` VALUES (5, '1602812321272963', '98', NULL, NULL, '0', 'A', NULL, NULL, '塞班', NULL);

-- ----------------------------
-- Table structure for device_rulechain
-- ----------------------------
DROP TABLE IF EXISTS `device_rulechain`;
CREATE TABLE `device_rulechain`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `DEVICE_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '设备编号 设备编号，关联设备表',
  `CHAIN_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '规则链编号，关联规则链表',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 记录创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 记录更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备规则关联表 设备规则关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_rulechain
-- ----------------------------
INSERT INTO `device_rulechain` VALUES (1, '设备编号', '规则链', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_rulechain` VALUES (4, 'delete', '设备链', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_rulechain` VALUES (5, '160276053083649218', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_rulechain` VALUES (6, '1602812321272963', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_rulechain` VALUES (7, '1602812476584776', NULL, NULL, NULL, NULL, NULL, '风怒的小鸟哦', NULL);

-- ----------------------------
-- Table structure for device_tag
-- ----------------------------
DROP TABLE IF EXISTS `device_tag`;
CREATE TABLE `device_tag`  (
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID 记录主键，数据库自增',
  `DEVICE_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '设备编号 设备标签',
  `TAG_CONTENT` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标签内容 标签内容',
  `IS_DEL` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT '0' COMMENT '删除标志 删除标志，0-未删，1-已删',
  `OP_FLAG` char(1) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT 'A' COMMENT '操作标志 操作标志，A-增，D-删，U-改',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人 记录创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 记录创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '更新人 记录更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 记录更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '设备标签表 设备打标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_tag
-- ----------------------------
INSERT INTO `device_tag` VALUES (1, '标签1', NULL, NULL, NULL, NULL, NULL, '帝皇铠甲', NULL);
INSERT INTO `device_tag` VALUES (3, '标签33', NULL, NULL, NULL, NULL, NULL, '熔断器3', NULL);
INSERT INTO `device_tag` VALUES (4, '1602812321272963', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `device_tag` VALUES (5, '1602812476584776', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
