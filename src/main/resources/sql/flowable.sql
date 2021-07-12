/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : ztb_workflow

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 31/05/2021 16:48:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xdc_t_flow_option
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_flow_option`;
CREATE TABLE IF NOT EXISTS `xdc_t_flow_option`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `value` int(11) NULL DEFAULT NULL COMMENT '数字',
  `value_string` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数字含义',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_flow_option
-- ----------------------------
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (999, -1, '未审核', 'system', NULL, 1622450346195, NULL, '未审核');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (1, 1, '同意', 'system', NULL, 1622450346195, NULL, '同意');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (2, 2, '退回', 'system', NULL, 1622450346195, NULL, '退回');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (3, 3, '提交', 'system', NULL, 1622450346195, NULL, '提交');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (4, 4, '取消', 'system', NULL, 1622450346195, NULL, '取消');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (5, 5, '后加签', 'system', NULL, 1622450346195, NULL, '后加签');
INSERT IGNORE INTO `xdc_t_flow_option` VALUES (6, 6, '前加签', 'system', NULL, 1622450346195, NULL, '前加签');

-- ----------------------------
-- Table structure for xdc_t_expression
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_expression`;
CREATE TABLE IF NOT EXISTS `xdc_t_expression`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标识含义',
  `type` int(11) NULL DEFAULT NULL COMMENT '标识类型',
  `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '符号',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表达式标识表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_expression
-- ----------------------------
INSERT IGNORE INTO `xdc_t_expression` VALUES (1, '加', 1, '+', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (2, '减', 2, '-', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (3, '除', 3, '/', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (4, '求余', 4, '%', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (5, '幂', 5, '^', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (6, '等于', 6, '==', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (7, '不等于', 7, '!=', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (8, '大于', 8, '>', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (9, '大于等于', 9, '>=', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (10, '小于', 10, '<', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (11, '小于等于', 11, '<=', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (12, '区间', 12, 'between', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (13, '且', 13, 'and', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (14, '或', 14, 'or', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (15, '非', 15, 'NOT', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (16, '左括号', 16, '(', 'system', NULL, 1622450346195, NULL, NULL);
INSERT IGNORE INTO `xdc_t_expression` VALUES (17, '右括号', 17, ')', 'system', NULL, 1622450346195, NULL, NULL);

-- ----------------------------
-- Table structure for xdc_t_process_status
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_status`;
CREATE TABLE IF NOT EXISTS  `xdc_t_process_status`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `mark` varchar(255) NULL DEFAULT NULL COMMENT '状态标识',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_process_status
-- ----------------------------
INSERT IGNORE INTO `xdc_t_process_status` VALUES (1, '待提交', '0', 'system', NULL, 1622450346195, NULL, '待提交');
INSERT IGNORE INTO `xdc_t_process_status` VALUES (2, '已归档', '2', 'system', NULL, 1622450346195, NULL, '已归档');
INSERT IGNORE INTO `xdc_t_process_status` VALUES (3, '已退回', '3', 'system', NULL, 1622450346195, NULL, '已退回');
INSERT IGNORE INTO `xdc_t_process_status` VALUES (4, '已取消', '4', 'system', NULL, 1622450346195, NULL, '已取消');
INSERT IGNORE INTO `xdc_t_process_status` VALUES (5, '后加签', '5', 'system', NULL, 1622450346195, NULL, '后加签');
INSERT IGNORE INTO `xdc_t_process_status` VALUES (6, '前加签', '6', 'system', NULL, 1622450346195, NULL, '前加签');


-- ----------------------------
-- Table structure for xdc_t_odd_rule
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_odd_rule`;
CREATE TABLE IF NOT EXISTS `xdc_t_odd_rule`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `prefix` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前缀',
  `algorithm` tinyint(4) NULL DEFAULT NULL COMMENT '算法（1-时间年月日时分秒，2-随机，3-自增长，4-时间年月日+自增长）',
  `auto_number` bigint(20) NOT NULL DEFAULT 1 COMMENT '自增长数（自增长算法有效）',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单号规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_odd_rule
-- ----------------------------


-- ----------------------------
-- Table structure for xdc_t_process
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process`;
CREATE TABLE IF NOT EXISTS `xdc_t_process`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程名',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_process
-- ----------------------------


-- ----------------------------
-- Table structure for xdc_t_qualifier
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_qualifier`;
CREATE TABLE IF NOT EXISTS `xdc_t_qualifier`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '算法脚本',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_qualifier
-- ----------------------------


-- ----------------------------
-- Table structure for xdc_t_request
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_request`;
CREATE TABLE `xdc_t_request`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `odd_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单号',
  `process_id` bigint(20) NOT NULL COMMENT ' 流程ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `status_id` bigint(20) NULL DEFAULT NULL COMMENT '流程状态ID',
  `rule_id` bigint(20) NULL DEFAULT NULL COMMENT '单号规则ID',
  `config_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程配置版本号',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程表单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xdc_t_request
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_request_relation
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_request_relation`;
CREATE TABLE  IF NOT EXISTS `xdc_t_request_relation`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT '表单主键',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '表单父级ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_request_id`(`request_id`) USING BTREE COMMENT '表单主键ID索引',
  INDEX `idx_parent_id`(`parent_id`) USING BTREE COMMENT '表单父级主键ID索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_request_relation
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_process_config
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_id` bigint(20) NOT NULL COMMENT '流程明细表ID',
  `from_status_id` bigint(20) NOT NULL COMMENT '上一个流程状态ID',
  `to_status_id` bigint(20) NOT NULL COMMENT '目标流程状态ID',
  `to_role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `to_user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `qualifier_id` bigint(20) NULL DEFAULT NULL COMMENT '规则条件ID',
  `timeout_action` bigint(20) NULL DEFAULT NULL COMMENT '超时时间（超时后可流转下一节点）默认24小时，单位：毫秒',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程配置表' ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of xdc_t_process_config
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_process_config_line
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config_line`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config_line`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_id` bigint(20) NULL DEFAULT NULL COMMENT '流程主键',
  `from_mark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态标识',
  `to_mark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态标识',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程配置线信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xdc_t_process_config_node
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config_node`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config_node`  (
  `id` bigint(20) NOT NULL COMMENT ' 主键',
  `process_id` bigint(20) NULL DEFAULT NULL COMMENT '流程主键',
  `status_mark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程状态标识',
  `type` int(11) NULL DEFAULT NULL COMMENT '节点类型，开始: 0;结束: -1;一般: 1;会签节点:2;条件判断节点：3;查阅节点：4;子流程节点：5',
  `location_left` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '位置左， 单位\"px\"',
  `location_top` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下， 单位\"px\"',
  `ico` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态， success: 成功，warning: 警告，error: 错误，running：运行中',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '版本',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程配置节点信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xdc_t_request_flow
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_request_flow`;
CREATE TABLE IF NOT EXISTS `xdc_t_request_flow`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `request_id` bigint(20) NULL DEFAULT NULL COMMENT '表单ID',
  `from_status_id` bigint(20) NULL DEFAULT NULL COMMENT '上级状态ID',
  `to_status_id` bigint(20) NULL DEFAULT NULL COMMENT '目标状态ID',
  `from_user_id` bigint(20) NULL DEFAULT NULL COMMENT '分配者ID',
  `to_role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `to_user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `flow_option_value` int(11) NULL DEFAULT NULL COMMENT '流程操作',
  `begin_time` bigint(20) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(20) NULL DEFAULT NULL COMMENT '结束时间',
  `config_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程配置版本号',
  `created_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流转意见表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xdc_t_request_flow
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_process_config_line_template
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config_line_template`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config_line_template`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `from_mark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始',
  `to_mark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程配置线模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_process_config_line_template
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_process_config_node_template
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config_node_template`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config_node_template`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板ID',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标识',
  `type` int(11) NULL DEFAULT NULL COMMENT '节点类型',
  `location_left` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置左， 单位\"px\"',
  `location_top` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下， 单位\"px\"',
  `ico` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `state` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态， success: 成功，warning: 警告，error: 错误，running：运行中',
  `qualifier_id` bigint(20) NULL DEFAULT NULL COMMENT '条件主键',
  `timeout_action` bigint(20) NULL DEFAULT NULL COMMENT '超时时间（超时后可流转下一节点）默认24小时， 单位：毫秒',
  `to_user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `to_role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程配置节点模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_process_config_node_template
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_process_config_template
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_process_config_template`;
CREATE TABLE IF NOT EXISTS `xdc_t_process_config_template`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程配置模板信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_process_config_template
-- ----------------------------

-- ----------------------------
-- Table structure for xdc_t_user_to
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_user_to`;
CREATE TABLE  IF NOT EXISTS `xdc_t_user_to`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `mark` int(11) NULL DEFAULT NULL COMMENT '标识',
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表达式',
  `created_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `updated_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_user_to
-- ----------------------------
INSERT IGNORE INTO `xdc_t_user_to` VALUES (1, 1, NULL, NULL, NULL, NULL, NULL, '创建人直接上级');
INSERT IGNORE INTO `xdc_t_user_to` VALUES (2, 2, NULL, NULL, NULL, NULL, NULL, '审批者直接上级');
INSERT IGNORE INTO `xdc_t_user_to` VALUES (3, 3, NULL, NULL, NULL, NULL, NULL, '总经理');
INSERT IGNORE INTO `xdc_t_user_to` VALUES (4, 4, NULL, NULL, NULL, NULL, NULL, '创建人');























SET FOREIGN_KEY_CHECKS = 1;
















