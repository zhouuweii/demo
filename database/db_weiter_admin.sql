/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : db_weiter_admin

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 27/01/2021 10:24:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authentication` blob NULL,
  `refresh_token` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`  (
  `userId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clientId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expiresAt` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `lastModifiedAt` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('AdminWebApp', NULL, '$2a$10$9bEpZ/hWRQxyr5hn5wHUj.jxFpIrnOmBcWlE/g/0Zp3uNxt9QTh/S', 'app', 'authorization_code,password,refresh_token,client_credentials', NULL, NULL, 43200, 43200, NULL, NULL);

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authentication` blob NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `token_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` blob NULL,
  `authentication` blob NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深色主题theme-dark，浅色主题theme-light');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'Weiter集团', 0, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:23:21');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '华北分公司', 4, 'north', '15888888888', 'north@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:16:56');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '华东分公司', 3, 'east', '15888888888', 'east@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:19:17');
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (104, 111, '0,100,111', '市场部门', 2, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:18:56');
INSERT INTO `sys_dept` VALUES (105, 102, '0,100,102', '测试部门', 3, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:19:16');
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '周威', '15888888888', 'zhouuweii@163.com', '1', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:21:01');
INSERT INTO `sys_dept` VALUES (107, 112, '0,100,112', '运维部门', 5, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:20:02');
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '周威', '15888888888', 'zhouuweii@163.com', '0', '2', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (109, 110, '0,100,110', '财务部门', 2, 'Finance', '15888888888', 'Finance@163.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:17:33');
INSERT INTO `sys_dept` VALUES (110, 100, '0,100', '郑州总部', 1, '周威', '15888888888', 'zhouuweii@163.com', '0', '0', 'admin', '2021-01-26 17:14:15', 'admin', '2021-01-26 17:17:33');
INSERT INTO `sys_dept` VALUES (111, 100, '0,100', '华南分公司', 2, 'south', '15888888888', 'south@163.com', '0', '0', 'admin', '2021-01-26 17:15:16', 'admin', '2021-01-26 17:23:21');
INSERT INTO `sys_dept` VALUES (112, 100, '0,100', '华西分公司', 5, 'west', '15888888888', 'west@163.com', '0', '0', 'admin', '2021-01-26 17:19:50', 'admin', '2021-01-26 17:20:02');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 1, '授权码模式', 'authorization_code', 'sys_grant_type', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权码模式');
INSERT INTO `sys_dict_data` VALUES (30, 2, '密码模式', 'password', 'sys_grant_type', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '密码模式');
INSERT INTO `sys_dict_data` VALUES (31, 3, '客户端模式', 'client_credentials', 'sys_grant_type', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '客户端模式');
INSERT INTO `sys_dict_data` VALUES (32, 4, '简化模式', 'implicit', 'sys_grant_type', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '简化模式');
INSERT INTO `sys_dict_data` VALUES (33, 5, '刷新模式', 'refresh_token', 'sys_grant_type', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '刷新模式');
INSERT INTO `sys_dict_data` VALUES (34, 0, '123', '123', 'sys_grant_type', NULL, NULL, 'N', '0', 'admin', '2021-01-25 14:30:15', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '人物性别', 'sys_user_sex', '0', 'admin', '2021-01-25 15:01:37', 'admin', '2021-01-25 15:20:01', '人物性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-25 14:23:51', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-25 14:23:57', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-25 15:20:28', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '授权类型', 'sys_grant_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权类型列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示信息',
  `access_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1066 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 0, 'M', '0', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 0, 'M', '0', '1', '', 'monitor', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-27 09:04:05', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, 1, 0, 'M', '0', '1', '', 'tool', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-27 09:03:48', '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, 'Weiter', 0, 4, 'http://www.baidu.com', NULL, 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-27 09:03:09', '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 9, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 10, 'log', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', '', 1, 0, 'C', '0', '0', 'monitor:sentinel:list', 'sentinel', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '流量控制菜单');
INSERT INTO `sys_menu` VALUES (112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', '', 1, 0, 'C', '0', '0', 'monitor:nacos:list', 'nacos', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务治理菜单');
INSERT INTO `sys_menu` VALUES (113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'http://localhost:8080/swagger-ui.html', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'system/operlog/index', 1, 0, 'C', '0', '0', 'system:operlog:list', 'form', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'system/logininfor/index', 1, 0, 'C', '0', '0', 'system:logininfor:list', 'logininfor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '公告查询', 107, 1, '#', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '公告新增', 107, 2, '#', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '公告修改', 107, 3, '#', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '公告删除', 107, 4, '#', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '操作查询', 500, 1, '#', '', 1, 0, 'F', '0', '0', 'system:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '操作删除', 500, 2, '#', '', 1, 0, 'F', '0', '0', 'system:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '日志导出', 500, 4, '#', '', 1, 0, 'F', '0', '0', 'system:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '登录查询', 501, 1, '#', '', 1, 0, 'F', '0', '0', 'system:logininfor:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '登录删除', 501, 2, '#', '', 1, 0, 'F', '0', '0', 'system:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '日志导出', 501, 3, '#', '', 1, 0, 'F', '0', '0', 'system:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '在线查询', 109, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '批量强退', 109, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '单条强退', 109, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '任务查询', 110, 1, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '任务新增', 110, 2, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1056, '任务修改', 110, 3, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1057, '任务删除', 110, 4, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1058, '状态修改', 110, 5, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1059, '任务导出', 110, 7, '#', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1060, '生成查询', 115, 1, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1061, '生成修改', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1062, '生成删除', 115, 3, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1063, '导入代码', 115, 2, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1064, '预览代码', 115, 4, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1065, '生成代码', 115, 5, '#', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true} ', 0, NULL, '2021-01-26 13:00:15');
INSERT INTO `sys_oper_log` VALUES (2, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":11,\"dictName\":\"授权类型\",\"dictType\":\"sys_grant_type\",\"params\":{},\"remark\":\"授权类型列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 13:24:17');
INSERT INTO `sys_oper_log` VALUES (3, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 13:24:30');
INSERT INTO `sys_oper_log` VALUES (4, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"postId\":6,\"params\":{},\"createBy\":\"admin\",\"postName\":\"总监\",\"postCode\":\"zongjian\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:18:49');
INSERT INTO `sys_oper_log` VALUES (5, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"params\":{},\"postName\":\"总监\",\"postCode\":\"zongjian1\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增岗位失败，该岗位已存在', '2021-01-26 15:18:58');
INSERT INTO `sys_oper_log` VALUES (6, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"params\":{},\"postName\":\"总监\",\"postCode\":\"zongjian\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增岗位失败，该岗位已存在', '2021-01-26 15:19:05');
INSERT INTO `sys_oper_log` VALUES (7, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"params\":{},\"postName\":\"总监1\",\"postCode\":\"zongjian\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增岗位失败，该岗位已存在', '2021-01-26 15:19:09');
INSERT INTO `sys_oper_log` VALUES (8, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"params\":{},\"postName\":\"总监1\",\"postCode\":\"zongjian\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增岗位失败，该岗位已存在', '2021-01-26 15:19:12');
INSERT INTO `sys_oper_log` VALUES (9, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"7\",\"flag\":false,\"postId\":6,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611645528000,\"updateBy\":\"admin\",\"postName\":\"总监\",\"postCode\":\"zong\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:20:04');
INSERT INTO `sys_oper_log` VALUES (10, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"6\",\"flag\":false,\"postId\":6,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611645528000,\"updateBy\":\"admin\",\"postName\":\"总监\",\"postCode\":\"zong\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:20:10');
INSERT INTO `sys_oper_log` VALUES (11, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"6\",\"flag\":false,\"postId\":6,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611645528000,\"updateBy\":\"admin\",\"postName\":\"总监\",\"postCode\":\"zong\",\"status\":\"1\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:20:12');
INSERT INTO `sys_oper_log` VALUES (12, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/6', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:20:30');
INSERT INTO `sys_oper_log` VALUES (13, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/1,2,3,4,5', '127.0.0.1', '', NULL, 'null', 1, '错误代码：4501错误信息：该岗位已分配,不能删除', '2021-01-26 15:20:37');
INSERT INTO `sys_oper_log` VALUES (14, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/1', '127.0.0.1', '', NULL, 'null', 1, '错误代码：4501错误信息：该岗位已分配,不能删除', '2021-01-26 15:20:44');
INSERT INTO `sys_oper_log` VALUES (15, '岗位管理', 6, 'com.zw.admin.system.controller.PostController.export()', 'POST', 1, 'admin', NULL, '/post/export', '127.0.0.1', '', '{\"flag\":false,\"params\":{}}', 'null', 0, NULL, '2021-01-26 15:20:50');
INSERT INTO `sys_oper_log` VALUES (16, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 15:21:02');
INSERT INTO `sys_oper_log` VALUES (17, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"Weiter科技\",\"leader\":\"周威\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0\",\"email\":\"zhouuweii@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 16:49:40');
INSERT INTO `sys_oper_log` VALUES (18, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"Weiter集团\",\"leader\":\"周威\",\"deptId\":100,\"orderNum\":\"0\",\"delFlag\":\"0\",\"params\":{},\"parentId\":0,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0\",\"email\":\"zhouuweii@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 16:50:27');
INSERT INTO `sys_oper_log` VALUES (19, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/2', '127.0.0.1', '', NULL, 'null', 1, '错误代码：4501错误信息：该岗位已分配,不能删除', '2021-01-26 17:05:38');
INSERT INTO `sys_oper_log` VALUES (20, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/3', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:05:58');
INSERT INTO `sys_oper_log` VALUES (21, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/4', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:06:01');
INSERT INTO `sys_oper_log` VALUES (22, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/5', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:06:02');
INSERT INTO `sys_oper_log` VALUES (23, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/2', '127.0.0.1', '', NULL, 'null', 1, '错误代码：4501错误信息：该岗位已分配,不能删除', '2021-01-26 17:06:04');
INSERT INTO `sys_oper_log` VALUES (24, '岗位管理', 4, 'com.zw.admin.system.controller.PostController.remove()', 'DELETE', 1, 'admin', NULL, '/post/1', '127.0.0.1', '', NULL, 'null', 1, '错误代码：4501错误信息：该岗位已分配,不能删除', '2021-01-26 17:06:07');
INSERT INTO `sys_oper_log` VALUES (25, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"params\":{},\"postName\":\"总裁\",\"postCode\":\"CEO\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增失败，该岗位编号已存在', '2021-01-26 17:08:07');
INSERT INTO `sys_oper_log` VALUES (26, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"50\",\"flag\":false,\"params\":{},\"postName\":\"总裁\",\"postCode\":\"CEO\",\"status\":\"0\"}', 'null', 1, '错误代码：4605错误信息：新增失败，该岗位编号已存在', '2021-01-26 17:08:27');
INSERT INTO `sys_oper_log` VALUES (27, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"50\",\"flag\":false,\"remark\":\"总经理\",\"postId\":7,\"params\":{},\"createBy\":\"admin\",\"postName\":\"总经理\",\"postCode\":\"GeneralManager\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:09:08');
INSERT INTO `sys_oper_log` VALUES (28, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"52\",\"flag\":false,\"remark\":\"副总经理\",\"postId\":8,\"params\":{},\"createBy\":\"admin\",\"postName\":\"副总经理\",\"postCode\":\"ViceGeneralManager\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:09:42');
INSERT INTO `sys_oper_log` VALUES (29, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"50\",\"flag\":false,\"remark\":\"总经理GeneralManager\",\"postId\":7,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611652148000,\"updateBy\":\"admin\",\"postName\":\"总经理\",\"postCode\":\"GM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:10:05');
INSERT INTO `sys_oper_log` VALUES (30, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"52\",\"flag\":false,\"remark\":\"副总经理ViceGeneralManager\",\"postId\":8,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611652182000,\"updateBy\":\"admin\",\"postName\":\"副总经理\",\"postCode\":\"VGM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:12:05');
INSERT INTO `sys_oper_log` VALUES (31, '岗位管理', 2, 'com.zw.admin.system.controller.PostController.add()', 'POST', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"remark\":\"行政总监Administrative Director\",\"postId\":9,\"params\":{},\"createBy\":\"admin\",\"postName\":\"行政总监\",\"postCode\":\"AD\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:13:04');
INSERT INTO `sys_oper_log` VALUES (32, '部门管理', 4, 'com.zw.admin.system.controller.DeptController.remove()', 'DELETE', 1, 'admin', NULL, '/dept/108', '127.0.0.1', '', NULL, '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:13:20');
INSERT INTO `sys_oper_log` VALUES (33, '部门管理', 2, 'com.zw.admin.system.controller.DeptController.add()', 'POST', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"郑州总部\",\"leader\":\"周威\",\"orderNum\":\"1\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"phone\":\"15888888888\",\"ancestors\":\"0,100\",\"email\":\"zhouuweii@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:14:15');
INSERT INTO `sys_oper_log` VALUES (34, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"深圳总公司\",\"leader\":\"若依\",\"deptId\":101,\"orderNum\":\"3\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"ry@qq.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:14:22');
INSERT INTO `sys_oper_log` VALUES (35, '部门管理', 2, 'com.zw.admin.system.controller.DeptController.add()', 'POST', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华南总公司\",\"leader\":\"south\",\"orderNum\":\"2\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"ancestors\":\"0,100\",\"email\":\"south@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:15:16');
INSERT INTO `sys_oper_log` VALUES (36, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华东分公司\",\"leader\":\"east\",\"deptId\":102,\"orderNum\":\"2\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"east@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:16:10');
INSERT INTO `sys_oper_log` VALUES (37, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华东分公司\",\"leader\":\"east\",\"deptId\":102,\"orderNum\":\"3\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"east@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:16:17');
INSERT INTO `sys_oper_log` VALUES (38, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华北分公司\",\"leader\":\"north\",\"deptId\":101,\"orderNum\":\"4\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"north@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:16:56');
INSERT INTO `sys_oper_log` VALUES (39, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"财务部门\",\"leader\":\"Finance\",\"deptId\":109,\"orderNum\":\"2\",\"delFlag\":\"0\",\"params\":{},\"parentId\":110,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,110\",\"email\":\"Finance@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:17:33');
INSERT INTO `sys_oper_log` VALUES (40, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"市场部门\",\"leader\":\"若依\",\"deptId\":104,\"orderNum\":\"2\",\"delFlag\":\"0\",\"params\":{},\"parentId\":111,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,111\",\"email\":\"ry@qq.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:18:56');
INSERT INTO `sys_oper_log` VALUES (41, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"测试部门\",\"leader\":\"若依\",\"deptId\":105,\"orderNum\":\"3\",\"delFlag\":\"0\",\"params\":{},\"parentId\":102,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,102\",\"email\":\"ry@qq.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:19:17');
INSERT INTO `sys_oper_log` VALUES (42, '部门管理', 2, 'com.zw.admin.system.controller.DeptController.add()', 'POST', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华西分公司\",\"leader\":\"west\",\"orderNum\":\"5\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"phone\":\"15888888888\",\"ancestors\":\"0,100\",\"email\":\"west@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:19:50');
INSERT INTO `sys_oper_log` VALUES (43, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"运维部门\",\"leader\":\"若依\",\"deptId\":107,\"orderNum\":\"5\",\"delFlag\":\"0\",\"params\":{},\"parentId\":112,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,112\",\"email\":\"ry@qq.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:20:02');
INSERT INTO `sys_oper_log` VALUES (44, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华南分公司\",\"leader\":\"south\",\"deptId\":111,\"orderNum\":\"2\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1611652516000,\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"south@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:20:14');
INSERT INTO `sys_oper_log` VALUES (45, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"财务部门\",\"leader\":\"若依\",\"deptId\":106,\"orderNum\":\"4\",\"delFlag\":\"0\",\"params\":{},\"parentId\":101,\"createBy\":\"admin\",\"children\":[],\"createTime\":1521171180000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100,101\",\"email\":\"ry@qq.com\",\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:21:01');
INSERT INTO `sys_oper_log` VALUES (46, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"1\",\"flag\":false,\"remark\":\"\",\"postId\":1,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"董事长\",\"postCode\":\"ceo\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:22:51');
INSERT INTO `sys_oper_log` VALUES (47, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"2\",\"flag\":false,\"remark\":\"\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"se\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:22:56');
INSERT INTO `sys_oper_log` VALUES (48, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"0\",\"flag\":false,\"remark\":\"行政总监Administrative Director\",\"postId\":9,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611652384000,\"updateBy\":\"admin\",\"postName\":\"行政总监\",\"postCode\":\"AD\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:23:01');
INSERT INTO `sys_oper_log` VALUES (49, '部门管理', 3, 'com.zw.admin.system.controller.DeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1', '', '{\"deptName\":\"华南分公司\",\"leader\":\"south\",\"deptId\":111,\"orderNum\":\"2\",\"delFlag\":\"0\",\"params\":{},\"parentId\":100,\"createBy\":\"admin\",\"children\":[],\"createTime\":1611652516000,\"phone\":\"15888888888\",\"updateBy\":\"admin\",\"ancestors\":\"0,100\",\"email\":\"south@163.com\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 17:23:21');
INSERT INTO `sys_oper_log` VALUES (50, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:24:25');
INSERT INTO `sys_oper_log` VALUES (51, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 17:27:40');
INSERT INTO `sys_oper_log` VALUES (52, '字典类型', 10, 'com.zw.admin.system.controller.DictTypeController.clearCache()', 'DELETE', 1, 'admin', NULL, '/dict/type/clearCache', '127.0.0.1', '', NULL, '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 18:01:18');
INSERT INTO `sys_oper_log` VALUES (53, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"2\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:01:52');
INSERT INTO `sys_oper_log` VALUES (54, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"101\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:01:59');
INSERT INTO `sys_oper_log` VALUES (55, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"101\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:02:06');
INSERT INTO `sys_oper_log` VALUES (56, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"97\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:02:19');
INSERT INTO `sys_oper_log` VALUES (57, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"5\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:02:37');
INSERT INTO `sys_oper_log` VALUES (58, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"53\",\"flag\":false,\"remark\":\"行政总监Administrative Director\",\"postId\":9,\"params\":{},\"createBy\":\"admin\",\"createTime\":1611652384000,\"updateBy\":\"admin\",\"postName\":\"行政总监\",\"postCode\":\"AD\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:02:44');
INSERT INTO `sys_oper_log` VALUES (59, '岗位管理', 3, 'com.zw.admin.system.controller.PostController.edit()', 'PUT', 1, 'admin', NULL, '/post', '127.0.0.1', '', '{\"postSort\":\"60\",\"flag\":false,\"remark\":\"项目经理Project Manager\",\"postId\":2,\"params\":{},\"createBy\":\"admin\",\"createTime\":1521171180000,\"updateBy\":\"admin\",\"postName\":\"项目经理\",\"postCode\":\"PM\",\"status\":\"0\"}', '{\"code\":200,\"data\":1,\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-26 18:02:52');
INSERT INTO `sys_oper_log` VALUES (60, '用户管理', 3, 'com.zw.admin.system.controller.UserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/user/changeStatus', '127.0.0.1', '', '{\"admin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":3,\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 20:32:09');
INSERT INTO `sys_oper_log` VALUES (61, '用户管理', 3, 'com.zw.admin.system.controller.UserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/user/changeStatus', '127.0.0.1', '', '{\"admin\":false,\"updateBy\":\"admin\",\"params\":{},\"userId\":3,\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 20:32:10');
INSERT INTO `sys_oper_log` VALUES (62, '用户管理', 3, 'com.zw.admin.system.controller.UserController.edit()', 'PUT', 1, 'admin', NULL, '/user', '127.0.0.1', '', '{\"roles\":[{\"flag\":false,\"roleId\":3,\"admin\":false,\"dataScope\":\"1\",\"params\":{},\"roleSort\":\"3\",\"deptCheckStrictly\":false,\"menuCheckStrictly\":false,\"roleKey\":\"guest\",\"roleName\":\"来宾\",\"status\":\"0\"}],\"phonenumber\":\"15112341234\",\"admin\":false,\"delFlag\":\"0\",\"password\":\"\",\"updateBy\":\"admin\",\"postIds\":[],\"loginIp\":\"\",\"email\":\"\",\"nickName\":\"来宾\",\"sex\":\"0\",\"deptId\":105,\"avatar\":\"\",\"dept\":{\"deptName\":\"测试部门\",\"leader\":\"周威\",\"deptId\":105,\"orderNum\":\"3\",\"params\":{},\"parentId\":102,\"children\":[],\"status\":\"0\"},\"params\":{},\"userName\":\"guest\",\"userId\":3,\"createBy\":\"\",\"roleIds\":[3],\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-26 20:32:29');
INSERT INTO `sys_oper_log` VALUES (63, '用户管理', 6, 'com.zw.admin.system.controller.UserController.export()', 'POST', 1, 'admin', NULL, '/user/export', '127.0.0.1', '', '{\"admin\":false,\"deptId\":100,\"params\":{}}', 'null', 0, NULL, '2021-01-26 20:33:45');
INSERT INTO `sys_oper_log` VALUES (64, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":11,\"dictName\":\"授权类型\",\"dictType\":\"sys_grant_type\",\"params\":{},\"remark\":\"授权类型列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-27 08:36:54');
INSERT INTO `sys_oper_log` VALUES (65, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-27 08:36:56');
INSERT INTO `sys_oper_log` VALUES (66, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-27 08:40:14');
INSERT INTO `sys_oper_log` VALUES (67, '菜单管理', 3, 'com.zw.admin.system.controller.MenuController.edit()', 'PUT', 1, 'admin', NULL, '/menu', '127.0.0.1', '', '{\"visible\":\"0\",\"icon\":\"guide\",\"orderNum\":\"4\",\"menuName\":\"Weiter\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"http://www.baidu.com\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"0\",\"menuId\":4,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-27 09:03:11');
INSERT INTO `sys_oper_log` VALUES (68, '菜单管理', 3, 'com.zw.admin.system.controller.MenuController.edit()', 'PUT', 1, 'admin', NULL, '/menu', '127.0.0.1', '', '{\"visible\":\"0\",\"icon\":\"tool\",\"orderNum\":\"3\",\"menuName\":\"系统工具\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"tool\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":3,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-27 09:03:48');
INSERT INTO `sys_oper_log` VALUES (69, '菜单管理', 3, 'com.zw.admin.system.controller.MenuController.edit()', 'PUT', 1, 'admin', NULL, '/menu', '127.0.0.1', '', '{\"visible\":\"0\",\"icon\":\"monitor\",\"orderNum\":\"2\",\"menuName\":\"系统监控\",\"params\":{},\"parentId\":0,\"isCache\":\"0\",\"path\":\"monitor\",\"children\":[],\"createTime\":1521171180000,\"updateBy\":\"admin\",\"isFrame\":\"1\",\"menuId\":2,\"menuType\":\"M\",\"perms\":\"\",\"status\":\"1\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-27 09:04:05');
INSERT INTO `sys_oper_log` VALUES (70, '字典类型', 1, 'com.zw.admin.system.controller.DictTypeController.list()', 'GET', 1, 'admin', NULL, '/dict/type/list', '127.0.0.1', '', NULL, '{\"code\":200,\"data\":{\"rows\":[{\"createBy\":\"admin\",\"createTime\":1611558097000,\"dictId\":1,\"dictName\":\"人物性别\",\"dictType\":\"sys_user_sex\",\"params\":{},\"remark\":\"人物性别列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":2,\"dictName\":\"菜单状态\",\"dictType\":\"sys_show_hide\",\"params\":{},\"remark\":\"菜单状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":3,\"dictName\":\"系统开关\",\"dictType\":\"sys_normal_disable\",\"params\":{},\"remark\":\"系统开关列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":4,\"dictName\":\"任务状态\",\"dictType\":\"sys_job_status\",\"params\":{},\"remark\":\"任务状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":5,\"dictName\":\"任务分组\",\"dictType\":\"sys_job_group\",\"params\":{},\"remark\":\"任务分组列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":6,\"dictName\":\"系统是否\",\"dictType\":\"sys_yes_no\",\"params\":{},\"remark\":\"系统是否列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":7,\"dictName\":\"通知类型\",\"dictType\":\"sys_notice_type\",\"params\":{},\"remark\":\"通知类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":8,\"dictName\":\"通知状态\",\"dictType\":\"sys_notice_status\",\"params\":{},\"remark\":\"通知状态列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":9,\"dictName\":\"操作类型\",\"dictType\":\"sys_oper_type\",\"params\":{},\"remark\":\"操作类型列表\",\"status\":\"0\"},{\"createBy\":\"admin\",\"createTime\":1521171180000,\"dictId\":10,\"dictName\":\"系统状态\",\"dictType\":\"sys_common_status\",\"params\":{},\"remark\":\"登录状态列表\",\"status\":\"0\"}],\"total\":11},\"message\":\"操作成功！\",\"success\":true}', 0, NULL, '2021-01-27 09:07:44');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 17:22:51', '');
INSERT INTO `sys_post` VALUES (2, 'PM', '项目经理', 60, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2021-01-26 18:02:52', '项目经理Project Manager');
INSERT INTO `sys_post` VALUES (7, 'GM', '总经理', 50, '0', 'admin', '2021-01-26 17:09:08', 'admin', '2021-01-26 17:10:05', '总经理GeneralManager');
INSERT INTO `sys_post` VALUES (8, 'VGM', '副总经理', 52, '0', 'admin', '2021-01-26 17:09:42', 'admin', '2021-01-26 17:12:05', '副总经理ViceGeneralManager');
INSERT INTO `sys_post` VALUES (9, 'AD', '行政总监', 53, '0', 'admin', '2021-01-26 17:13:04', 'admin', '2021-01-26 18:02:44', '行政总监Administrative Director');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '普通角色');
INSERT INTO `sys_role` VALUES (3, '来宾', 'guest', 3, '1', 1, 1, '0', '0', '', NULL, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (2, 1062);
INSERT INTO `sys_role_menu` VALUES (2, 1063);
INSERT INTO `sys_role_menu` VALUES (2, 1064);
INSERT INTO `sys_role_menu` VALUES (2, 1065);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 105);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '威特', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'weiter', '威特', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '测试员');
INSERT INTO `sys_user` VALUES (3, 105, 'guest', '来宾', '00', '', '15112341234', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '', NULL, '', '2021-01-26 20:32:43', 'admin', '2021-01-26 20:32:29', NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);

SET FOREIGN_KEY_CHECKS = 1;
